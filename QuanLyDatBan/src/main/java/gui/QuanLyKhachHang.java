package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class QuanLyKhachHang extends JFrame {
    // Khai báo các thành phần giao diện
    private JTextField txtCustomerID, txtCustomerName, txtPhoneNumber, txtEmail;
    private JTextArea txtReservationHistory;
    private JButton btnAdd, btnEdit, btnDelete, btnSearch;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JLabel lblAvatar; // Ảnh đại diện
    private Map<String, ImageIcon> avatarMap; // Lưu trữ mã KH và ảnh đại diện

    public QuanLyKhachHang() {
        setTitle("QUẢN LÝ KHÁCH HÀNG");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Đặt màu nền cho JFrame
        getContentPane().setBackground(new Color(230, 240, 255));

        // Tạo các nhãn và trường nhập liệu
        JLabel lblCustomerID = new JLabel("Mã KH:");
        lblCustomerID.setBounds(20, 20, 100, 30);
        lblCustomerID.setForeground(Color.BLUE); // Đặt màu chữ
        add(lblCustomerID);

        txtCustomerID = new JTextField();
        txtCustomerID.setBounds(120, 20, 150, 30);
        add(txtCustomerID);

        JLabel lblCustomerName = new JLabel("Tên KH:");
        lblCustomerName.setBounds(20, 60, 100, 30);
        lblCustomerName.setForeground(Color.BLUE); // Đặt màu chữ
        add(lblCustomerName);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(120, 60, 150, 30);
        add(txtCustomerName);

        JLabel lblPhoneNumber = new JLabel("SDT:");
        lblPhoneNumber.setBounds(20, 100, 100, 30);
        lblPhoneNumber.setForeground(Color.BLUE); // Đặt màu chữ
        add(lblPhoneNumber);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setBounds(120, 100, 150, 30);
        add(txtPhoneNumber);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 140, 100, 30);
        lblEmail.setForeground(Color.BLUE); // Đặt màu chữ
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 140, 150, 30);
        add(txtEmail);

        JLabel lblReservationHistory = new JLabel("Lịch sử đặt bàn:");
        lblReservationHistory.setBounds(20, 180, 150, 30);
        lblReservationHistory.setForeground(Color.BLUE); // Đặt màu chữ
        add(lblReservationHistory);

        txtReservationHistory = new JTextArea();
        JScrollPane historyScrollPane = new JScrollPane(txtReservationHistory);
        historyScrollPane.setBounds(120, 180, 150, 100);
        add(historyScrollPane);

        // Tạo bảng hiển thị danh sách khách hàng
        customerTable = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã KH", "Tên KH", "SDT", "Email", "Lịch sử đặt bàn"});
        customerTable.setModel(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(customerTable);
        tableScrollPane.setBounds(300, 20, 600, 400); // Kích thước bảng
        add(tableScrollPane);

        // Thêm sự kiện khi chọn hàng trong bảng
        customerTable.getSelectionModel().addListSelectionListener(e -> displayAvatar());

        // Ảnh đại diện
        lblAvatar = new JLabel();
        lblAvatar.setBounds(950, 20, 200, 200); // Kích thước khung cho ảnh
        lblAvatar.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Thêm viền cho ảnh
        add(lblAvatar);

        // Các nút chức năng
        btnAdd = new JButton("Thêm");
        btnAdd.setBounds(20, 300, 80, 30);
        btnAdd.setBackground(Color.GREEN); // Đặt màu nền cho nút
        btnAdd.setForeground(Color.WHITE); // Đặt màu chữ cho nút
        add(btnAdd);

        btnEdit = new JButton("Sửa");
        btnEdit.setBounds(110, 300, 80, 30);
        btnEdit.setBackground(Color.ORANGE);
        btnEdit.setForeground(Color.WHITE);
        add(btnEdit);

        btnDelete = new JButton("Xóa");
        btnDelete.setBounds(200, 300, 80, 30);
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        add(btnDelete);

        btnSearch = new JButton("Tìm kiếm");
        btnSearch.setBounds(110, 350, 100, 30);
        btnSearch.setBackground(Color.CYAN);
        btnSearch.setForeground(Color.WHITE);
        add(btnSearch);

        // Thêm sự kiện cho các nút bấm
        btnAdd.addActionListener(e -> addCustomer());
        btnEdit.addActionListener(e -> editCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());
        btnSearch.addActionListener(e -> searchCustomer());

        // Khởi tạo bản đồ lưu ảnh đại diện
        avatarMap = new HashMap<>();

        // Thêm khách hàng mẫu
        addSampleCustomers();
    }

    // Phương thức thêm khách hàng mẫu
    private void addSampleCustomers() {
        String[][] customers = {
                {"KH001", "Nguyễn Văn A", "0912345678", "nva@gmail.com", "8:00 p.m, 22/10/2024"},
                {"KH002", "Trần Thị B", "0987654321", "ttb@gmail.com", "8:00 p.m, 22/10/2024"},
                {"KH003", "Lê Văn C", "0911222333", "lvc@gmail.com", "8:00 p.m, 22/10/2024"},
                 {"KH004", "Nguyễn Văn D", "0912345678", "na@gmail.com", "8:00 p.m, 22/10/2024"},
                {"KH005", "Trần Thị E", "0987654321", "tt@gmail.com", "8:00 p.m, 22/10/2024"},
                {"KH006", "Lê Văn F", "0911222333", "lv@gmail.com", "8:00 p.m, 22/10/2024"}

        };
//        String imagePath = "C:/Study/PTUD/GUI/src/icon.GUI/"; // Đường dẫn ảnh
//        // Thêm các dòng vào bảng và gán ảnh đại diện
//        for (String[] customer : customers) {
//            tableModel.addRow(customer);
//            ImageIcon icon = loadImage(imagePath + customer[0] + "01.jpg");
//            avatarMap.put(customer[0], icon);
//        }
    }

    // Phương thức để tải ảnh và xử lý lỗi
    private ImageIcon loadImage(String path) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            return new ImageIcon(path);
        } else {
            System.err.println("Ảnh không tồn tại: " + path);
            return null; // Trả về null nếu ảnh không tồn tại
        }
    }

    // Phương thức hiển thị ảnh đại diện khi chọn khách hàng
    private void displayAvatar() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String customerID = (String) tableModel.getValueAt(selectedRow, 0);
            ImageIcon avatar = avatarMap.get(customerID);
            if (avatar != null) {
                // Thay đổi kích thước ảnh trước khi hiển thị
                Image scaledImage = avatar.getImage().getScaledInstance(lblAvatar.getWidth(), lblAvatar.getHeight(), Image.SCALE_SMOOTH);
                lblAvatar.setIcon(new ImageIcon(scaledImage));
            } else {
                lblAvatar.setIcon(null); // Nếu không có ảnh
            }
        }
    }

    // Phương thức thêm khách hàng
    private void addCustomer() {
        String customerID = txtCustomerID.getText();
        String customerName = txtCustomerName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String reservationHistory = txtReservationHistory.getText();

        tableModel.addRow(new Object[]{customerID, customerName, phoneNumber, email, reservationHistory});

        // Thêm ảnh đại diện
//        avatarMap.put(customerID, loadImage("C:/Study/PTUD/GUI/src/icon.GUI/" + customerID + "01.jpg"));

        // Xóa trắng các trường sau khi thêm
        clearFields();
    }

    // Phương thức sửa thông tin khách hàng
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
            JOptionPane.showMessageDialog(this, "Chọn khách hàng để sửa.");
        }
    }

    // Phương thức xóa khách hàng
    private void deleteCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String customerID = (String) tableModel.getValueAt(selectedRow, 0);
            tableModel.removeRow(selectedRow);
            avatarMap.remove(customerID); // Xóa ảnh đại diện
            lblAvatar.setIcon(null); // Xóa ảnh trên giao diện
        } else {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng để xóa.");
        }
    }

    // Phương thức tìm kiếm khách hàng (dựa trên Mã KH hoặc Tên KH)
    private void searchCustomer() {
        String keyword = JOptionPane.showInputDialog(this, "Nhập Mã KH hoặc Tên KH để tìm kiếm:");
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

    // Phương thức xóa trắng các trường nhập liệu
    private void clearFields() {
        txtCustomerID.setText("");
        txtCustomerName.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        txtReservationHistory.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuanLyKhachHang().setVisible(true));
    }
}