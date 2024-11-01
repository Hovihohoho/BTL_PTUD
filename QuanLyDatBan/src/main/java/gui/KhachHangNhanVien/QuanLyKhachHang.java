package gui.KhachHangNhanVien;

import dao.KhachHang_DAO;
import entity.KhachHang;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class QuanLyKhachHang extends JFrame {
    // Khai báo các thành phần giao diện
    private JTextField txtCustomerID, txtCustomerName, txtPhoneNumber, txtEmail;
    private JTextArea txtReservationHistory;
    private JButton btnAdd, btnEdit, btnDelete, btnSearch, btnFilter;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JLabel lblAvatar; // Ảnh đại diện
    private Map<String, ImageIcon> avatarMap; // Lưu trữ mã KH và ảnh đại diện

    // Các thành phần giao diện bổ sung
    private JTextArea txtStats, txtNote;
    private JComboBox<String> filterCombo;

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
        loadKhachHangData();

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
        
        
        
        // Thêm các thành phần bổ sung
        JLabel lblStats = new JLabel("Thống kê khách hàng:");
        lblStats.setBounds(950, 250, 200, 30);
        lblStats.setForeground(Color.BLUE);
        add(lblStats);

        txtStats = new JTextArea("Số khách hàng hiện có: " + tableModel.getRowCount());
        txtStats.setBounds(950, 280, 200, 60);
        txtStats.setEditable(false);
        add(txtStats);

        JLabel lblNote = new JLabel("Yêu cầu đặc biệt của khách hàng:");
        lblNote.setBounds(950, 360, 200, 30);
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

        filterCombo = new JComboBox<>(new String[]{"Tất cả", "Khách hàng thân thiết", "Khách hàng vãng lai"});
        filterCombo.setBounds(950, 540, 200, 30);
        add(filterCombo);

        btnFilter = new JButton("Lọc");
        btnFilter.setBounds(950, 580, 80, 30);
        btnFilter.setBackground(Color.MAGENTA);
        btnFilter.setForeground(Color.WHITE);
        add(btnFilter);

        btnAdd.addActionListener(e -> addCustomer());
        btnEdit.addActionListener(e -> editCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());
        btnSearch.addActionListener(e -> searchCustomer());
        btnFilter.addActionListener(e -> filterCustomer());
        //tnViewHistory.addActionListener(e -> viewReservationHistory());

        avatarMap = new HashMap<>();
        addSampleCustomers();
    }
    private void viewReservationHistory() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String reservationHistory = (String) tableModel.getValueAt(selectedRow, 4);
            
            // Tạo một JDialog để hiển thị chi tiết lịch sử đặt bàn
            JDialog dialog = new JDialog(this, "Chi tiết Lịch sử Đặt Bàn", true);
            dialog.setSize(400, 300);
            dialog.setLocationRelativeTo(this);
            JTextArea historyArea = new JTextArea(reservationHistory);
            historyArea.setLineWrap(true);
            historyArea.setWrapStyleWord(true);
            historyArea.setEditable(false);
            dialog.add(new JScrollPane(historyArea));
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để xem lịch sử đặt bàn.");
        }
    }

    private void filterCustomer() {
        String filterOption = (String) filterCombo.getSelectedItem();
        String searchText = txtPhoneNumber.getText().trim(); // Sử dụng SDT làm ví dụ, bạn có thể thay đổi cho phù hợp

        DefaultTableModel filteredModel = new DefaultTableModel();
        filteredModel.setColumnIdentifiers(new String[]{"Mã KH", "Tên KH", "SDT", "Email", "Lịch sử đặt bàn"});

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String phone = tableModel.getValueAt(i, 2).toString();
            if (filterOption.equals("Tất cả") || (filterOption.equals("Khách hàng thân thiết") && phone.contains(searchText))) {
                filteredModel.addRow(new Object[]{
                        tableModel.getValueAt(i, 0),
                        tableModel.getValueAt(i, 1),
                        tableModel.getValueAt(i, 2),
                        tableModel.getValueAt(i, 3),
                        tableModel.getValueAt(i, 4)
                });
            }
        }

        customerTable.setModel(filteredModel);
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
    // Example format: "Time, Date | Time, Date | ..."
    String[][] customers = {
        {"KH001", "Nguyễn Văn A", "0912345678", "nva@gmail.com", " 22/10/2024 | 23/10/2024"},
        {"KH002", "Trần Thị B", "0987654321", "ttb@gmail.com", " 22/10/2024"}
    };
    
    String imagePath = "C:\\Users\\Hoviihohoho\\Desktop\\DatBan\\QuanLyDatBan\\src\\main\\java\\icon\\";
    for (String[] customer : customers) {
        // Format reservation history with total bookings count
        String formattedHistory = formatReservationHistory(customer[4]);
        tableModel.addRow(new Object[]{customer[0], customer[1], customer[2], customer[3], formattedHistory});
        
        // Load and add avatar
        ImageIcon icon = loadImage(imagePath + customer[0] + "01.jpg");
        avatarMap.put(customer[0], icon);
    }
    txtStats.setText("Tổng số khách đặt bàn hiện tại: " + tableModel.getRowCount());
}

// Helper method to format reservation history with total booking count
private String formatReservationHistory(String history) {
    String[] bookings = history.split("\\|"); // Assuming each booking is separated by '|'
    int totalBookings = bookings.length;
    
    // Combine booking details with total count at the end
    StringBuilder formattedHistory = new StringBuilder(history);
    formattedHistory.append("\nTổng số lần đặt bàn: ").append(totalBookings);
    return formattedHistory.toString();
}

    private ImageIcon loadImage(String path) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            return new ImageIcon(path);
        } else {
            System.err.println("Ảnh không tồn tại: " + path);
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
    
    


       private boolean isValidCustomerData() {
        // Validate Customer ID format: KHnnnn
        String customerID = txtCustomerID.getText().trim();
        if (!customerID.matches("KH\\d{3}")) {
            JOptionPane.showMessageDialog(this, "Mã KH phải có dạng KHnnnn, Ví dụ: KH0xx");
            return false;
        }

        // Validate Customer Name: only letters, spaces, and Vietnamese characters
        String customerName = txtCustomerName.getText().trim();
        if (!customerName.matches("[\\p{L}\\s]+")) {
            JOptionPane.showMessageDialog(this, "Tên KH chỉ được chứa ký tự chữ cái, dấu cách, và các ký tự tiếng Việt có dấu.");
            return false;
        }

        // Validate Phone Number: exactly 11 digits, no spaces or special characters
        String phoneNumber = txtPhoneNumber.getText().trim();
        if (!phoneNumber.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có độ dài đúng 11 ký tự và chỉ chứa các ký tự số.");
            return false;
        }

        // Validate Email: max 50 characters, format example@domain.xxx
        String email = txtEmail.getText().trim();
        if (email.length() > 50 || !email.matches("^[\\w-\\.]+@[\\w-\\.]+\\.[a-z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Email phải có dạng example@domain.xxx và tối đa 50 ký tự.");
            return false;
        }

        return true;
    }

    private void addCustomer() {
        if (isValidCustomerData()) {
            String customerID = txtCustomerID.getText().trim();
            String customerName = txtCustomerName.getText().trim();
            String phoneNumber = txtPhoneNumber.getText().trim();
            String email = txtEmail.getText().trim();
            String reservationHistory = txtReservationHistory.getText().trim();

            tableModel.addRow(new Object[]{customerID, customerName, phoneNumber, email, reservationHistory});
            clearFields();
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
        }
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
            JOptionPane.showMessageDialog(this, "Chọn khách hàng để sửa.");
        }
    }

    private void deleteCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            lblAvatar.setIcon(null);
        } else {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng để xóa.");
        }
    }

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

    private void clearFields() {
        txtCustomerID.setText("");
        txtCustomerName.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        txtReservationHistory.setText("");
    }
    private void loadKhachHangData() {
    KhachHang_DAO khachHangDAO = new KhachHang_DAO();
    List<KhachHang> khachHangList = khachHangDAO.getAllKhachHang();
    
    // Thêm dữ liệu vào bảng0
    for (KhachHang khachHang : khachHangList) {
        tableModel.addRow(new Object[]{
            khachHang.getMaKH(),
            khachHang.getTenKH(),
            khachHang.getSoDienThoai(),
            khachHang.getDiaChi(),
            khachHang.getGioiTinh()
        });
    }
}

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuanLyKhachHang().setVisible(true));
    }
}
