
package gui.KhachHangNhanVien;

import dao.NhanVien_DAO;
import entity.NhanVien;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class QuanLyNhanVien extends JFrame {
    private JTextField txtCustomerID, txtCustomerName, txtPhoneNumber, txtEmail;
    private JTextArea txtReservationHistory;
    private JButton btnAdd, btnEdit, btnDelete, btnSearch, btnFilter, btnThoat;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JLabel lblAvatar; 
    private Map<String, ImageIcon> avatarMap;

    private JTextArea txtStats, txtNote;
    private JComboBox<String> filterCombo;

    public QuanLyNhanVien() throws SQLException {
        setTitle("QUẢN LÝ NHÂN VIÊN");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(230, 240, 255));

        // Tạo các nhãn và trường nhập liệu
        JLabel lblCustomerID = new JLabel("Mã NV:");
        lblCustomerID.setBounds(20, 20, 100, 30);
        lblCustomerID.setForeground(Color.BLUE);
        add(lblCustomerID);

        txtCustomerID = new JTextField();
        txtCustomerID.setBounds(120, 20, 150, 30);
        add(txtCustomerID);

        JLabel lblCustomerName = new JLabel("Họ NV:");
        lblCustomerName.setBounds(20, 60, 100, 30);
        lblCustomerName.setForeground(Color.BLUE);
        add(lblCustomerName);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(120, 60, 150, 30);
        add(txtCustomerName);

        JLabel lblPhoneNumber = new JLabel("SDT:");
        lblPhoneNumber.setBounds(20, 100, 100, 30);
        lblPhoneNumber.setForeground(Color.BLUE);
        add(lblPhoneNumber);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setBounds(120, 100, 150, 30);
        add(txtPhoneNumber);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 140, 100, 30);
        lblEmail.setForeground(Color.BLUE);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 140, 150, 30);
        add(txtEmail);

        JLabel lblReservationHistory = new JLabel("Ngày vào làm việc:");
        lblReservationHistory.setBounds(10, 170, 150, 40);
        lblReservationHistory.setForeground(Color.BLUE);
        add(lblReservationHistory);

        txtReservationHistory = new JTextArea();
        JScrollPane historyScrollPane = new JScrollPane(txtReservationHistory);
        historyScrollPane.setBounds(120, 180, 150, 100);
        add(historyScrollPane);

        customerTable = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã NV", "Tên NV", "SDT", "Email", "Ngày vào làm việc"});
        customerTable.setModel(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(customerTable);
        tableScrollPane.setBounds(300, 20, 600, 400);
        add(tableScrollPane);
        loadNhanVienData();
        customerTable.getSelectionModel().addListSelectionListener(e -> displayAvatar());

        lblAvatar = new JLabel();
        lblAvatar.setBounds(950, 20, 200, 200);
        lblAvatar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(lblAvatar);

        // Sử dụng JPanel với BoxLayout để căn chỉnh các nút theo dòng riêng
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBounds(20, 300, 100, 200);
        add(buttonPanel);

        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnSearch = new JButton("Tìm kiếm");
        btnThoat = new JButton("Thoat");

        // Thêm nút vào buttonPanel, mỗi nút nằm trên một dòng riêng
        buttonPanel.add(btnAdd);
        buttonPanel.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các nút
        buttonPanel.add(btnEdit);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnDelete);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnSearch);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnThoat);

        JLabel lblStats = new JLabel("Tổng số nhân viên:");
        lblStats.setBounds(950, 250, 200, 30);
        lblStats.setForeground(Color.BLUE);
        add(lblStats);

        txtStats = new JTextArea("Số nhân viên hiện có: " + tableModel.getRowCount());
        txtStats.setBounds(950, 280, 200, 60);
        txtStats.setEditable(false);
        add(txtStats);

        JLabel lblNote = new JLabel("Số ngày nghỉ của nhân viên trong tháng");
        lblNote.setBounds(950, 360, 300, 30);
        lblNote.setForeground(Color.BLUE);
        add(lblNote);

        txtNote = new JTextArea();
        JScrollPane noteScrollPane = new JScrollPane(txtNote);
        noteScrollPane.setBounds(950, 390, 200, 100);
        add(noteScrollPane);

        JLabel lblFilter = new JLabel("Bộ lọc nâng cao:");
        lblFilter.setBounds(950, 510, 200, 30);
        lblFilter.setForeground(Color.BLUE);
        add(lblFilter);

        filterCombo = new JComboBox<>(new String[]{"Tất cả", "Đã có ca làm việc", "Chưa có ca làm việc"});
        filterCombo.setBounds(950, 540, 200, 30);
        add(filterCombo);

        btnFilter = new JButton("Lọc");
        btnFilter.setBounds(950, 580, 80, 30);
        add(btnFilter);

        btnAdd.addActionListener(e -> addCustomer());
        btnEdit.addActionListener(e -> editCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());
        btnSearch.addActionListener(e -> searchCustomer());
        btnFilter.addActionListener(e -> filterCustomer());

        avatarMap = new HashMap<>();

        addSampleCustomers();
    }
    private void filterCustomer() {
    String filterOption = (String) filterCombo.getSelectedItem();
    String searchText = txtPhoneNumber.getText().trim(); // Sử dụng SDT làm ví dụ, bạn có thể thay đổi cho phù hợp

    // Xóa tất cả các hàng trong bảng tạm thời
    DefaultTableModel filteredModel = new DefaultTableModel();
    filteredModel.setColumnIdentifiers(new String[]{"Mã NV", "Tên NV", "SDT", "Email", "Ngày vào làm việc"});

    for (int i = 0; i < tableModel.getRowCount(); i++) {
        String phone = tableModel.getValueAt(i, 2).toString();
        String email = tableModel.getValueAt(i, 3).toString();

        // Kiểm tra tùy chọn lọc và thêm các hàng khớp với điều kiện
        if (filterOption.equals("Có ca làm việc") && phone.contains(searchText)) {
            filteredModel.addRow(new Object[]{tableModel.getValueAt(i, 0), tableModel.getValueAt(i, 1), phone, email, tableModel.getValueAt(i, 4)});
        } else if (filterOption.equals("Còn trống") && email.contains(searchText)) {
            filteredModel.addRow(new Object[]{tableModel.getValueAt(i, 0), tableModel.getValueAt(i, 1), phone, email, tableModel.getValueAt(i, 4)});
        } else if (filterOption.equals("Tất cả")) {
            filteredModel.addRow(new Object[]{tableModel.getValueAt(i, 0), tableModel.getValueAt(i, 1), phone, email, tableModel.getValueAt(i, 4)});
        }
    }

    // Cập nhật bảng với dữ liệu đã lọc
    //customerTable.setModel(filteredModel);
    customerTable.getSelectionModel().addListSelectionListener(e -> displayCustomerStatistics());
}
    

// Phương thức tính toán tổng số lần đặt bàn
private void displayCustomerStatistics() {
    int selectedRow = customerTable.getSelectedRow();
    if (selectedRow >= 0) {
        String reservationHistory = (String) tableModel.getValueAt(selectedRow, 4);
        
        // Đếm tổng số lần đặt bàn dựa trên dấu phẩy phân cách (giả định mỗi lần đặt bàn được phân cách bằng dấu phẩy)
        int bookingCount = reservationHistory.split(",").length;

        // Cập nhật số lần đặt bàn của khách hàng hiện tại
        txtStats.setText("Số lần đặt bàn của khách hàng này: " + bookingCount);
    }
}

    private void addSampleCustomers() {
        String[][] customers = {
                {"KH001", "Nguyễn Văn A", "0912345678", "nva@gmail.com", " 22/10/2024"},
                {"KH002", "Trần Thị B", "0987654321", "ttb@gmail.com", " 22/10/2024"},
                
        };
        String imagePath = "C:/Study/PTUD/GUI/src/icon.GUI/";
        for (String[] customer : customers) {
            tableModel.addRow(customer);
            ImageIcon icon = loadImage(imagePath + customer[0] + "01.jpg");
            avatarMap.put(customer[0], icon);
        }
        txtStats.setText("Tổng số nhân viên hiện tại: " + tableModel.getRowCount());
    }

    private ImageIcon loadImage(String path) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            return new ImageIcon(path);
        } else {
            System.err.println("Chưa cập nhật ảnh: " + path);
            return null;
        }
    }

    private void displayAvatar() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String customerID = (String) tableModel.getValueAt(selectedRow, 0);
            ImageIcon avatar = avatarMap.get(customerID);
            if (avatar != null) {
                Image scaledImage = avatar.getImage().getScaledInstance(lblAvatar.getWidth(), lblAvatar.getHeight(), Image.SCALE_SMOOTH);
                lblAvatar.setIcon(new ImageIcon(scaledImage));
            } else {
                lblAvatar.setIcon(null);
            }
        }
    }


    private void addCustomer() {
        String customerID = txtCustomerID.getText();
        String customerName = txtCustomerName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String reservationHistory = txtReservationHistory.getText();

        tableModel.addRow(new Object[]{customerID, customerName, phoneNumber, email, reservationHistory});
        clearFields();
    }

    private void editCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.setValueAt(txtCustomerID.getText(), selectedRow, 0);
            tableModel.setValueAt(txtCustomerName.getText(), selectedRow, 1);
            tableModel.setValueAt(txtPhoneNumber.getText(), selectedRow, 2);
            tableModel.setValueAt(txtEmail.getText(), selectedRow, 3);
            tableModel.setValueAt(txtReservationHistory.getText(), selectedRow, 4);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Chọn nhân viên để sửa.");
        }
    }

    private void deleteCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            lblAvatar.setIcon(null);
        } else {
            JOptionPane.showMessageDialog(this, "Chọn nhân viên để xóa.");
        }
    }

    private void searchCustomer() {
        String keyword = JOptionPane.showInputDialog(this, "Nhập Mã NV hoặc Tên NV để tìm kiếm:");
        if (keyword != null && !keyword.isEmpty()) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String customerID = (String) tableModel.getValueAt(i, 0);
                String customerName = (String) tableModel.getValueAt(i, 1);
                if (customerID.contains(keyword) || customerName.contains(keyword)) {
                    customerTable.setRowSelectionInterval(i, i);
                    break;
                }
            }
        }
    }

    private void clearFields() {
        txtCustomerID.setText("");
        txtCustomerName.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        txtReservationHistory.setText("");
    }
   
    private void loadNhanVienData() throws SQLException {
        NhanVien_DAO nhanVienDAO = new NhanVien_DAO();
        List<NhanVien> listNhanVien = nhanVienDAO.getAllNhanVien();
        
        // Thêm dữ liệu vào bảng
        for (NhanVien nv : listNhanVien) {
            tableModel.addRow(new Object[]{
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getsDT(),
                nv.getEmail(),
                nv.getNgayVaoLam() // Giả sử phương thức này trả về LocalDate
            });
        }
    }
    public static void main(String[] args) throws SQLException {
        QuanLyNhanVien qlnv = new QuanLyNhanVien();
        qlnv.setVisible(true);
    }
}
