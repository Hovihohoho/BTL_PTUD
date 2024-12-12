/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.KhachHangNhanVien;

import dao.KhachHang_DAO;
import entity.KhachHang;
import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.time.Clock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableCellRenderer;

public class QuanLyKhachHang extends javax.swing.JPanel {
    private JTable tableKhachHang;
    private DefaultTableModel tableModel;
    private KhachHang_DAO khachHangDao;
    private ButtonGroup group;

    public QuanLyKhachHang() throws SQLException {
        initComponents();
        // Nhóm radio button lại
//        ButtonGroup group = new ButtonGroup();
//        group.add(rbtnNam);
//        group.add(rbtnNu);
//        rbtnNam.setSelected(false);
//        rbtnNu.setSelected(false);
    
        khachHangDao = new KhachHang_DAO(); // Khởi tạo đối tượng DAO
        tableModel = new DefaultTableModel();
        // Lấy DefaultTableModel từ tableKhachHang
        tableKhachHang = new JTable(tableModel);
        
        
        table_thongTinKH.setFont(new Font("Arial", Font.PLAIN, 16));
        table_thongTinKH.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table_thongTinKH.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table_thongTinKH.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_thongTinKH.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table_thongTinKH.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tableModel = (DefaultTableModel) table_thongTinKH.getModel()  ; // Lấy table cua bang can them du lieu
        loadTable(); 
    }
    
    private void loadKhachHangData() {
    // Lấy DefaultTableModel từ JTable
    DefaultTableModel tableModel = (DefaultTableModel) table_thongTinKH.getModel();

    // Clear dữ liệu cũ trong bảng
    tableModel.setRowCount(0);

    // Truy vấn dữ liệu từ cơ sở dữ liệu
   // khachHangDao khachHangDAO = new KhachHangDAO();
    List<KhachHang> danhSachKhachHang = khachHangDao.getAllKhachHang();

    // Thêm dữ liệu mới vào bảng
    for (KhachHang kh : danhSachKhachHang) {
        Object[] row = new Object[]{
            kh.getMaKH(),
            kh.getTenKH(),
            kh.getSDT(),
            kh.getDiaChi(),
            kh.getGioiTinh()
        };
        tableModel.addRow(row);
    }
}

    
    public void loadTable() {
         if (tableModel == null) {
        System.err.println("tableModel is null!"); 
        return;
         }
        tableModel.setRowCount(0);
        // Lấy danh sách khách hàng từ CSDL
        List<KhachHang> khachHangs = khachHangDao.getAllKhachHang();  
        // Thêm dữ liệu vào table model
        for (KhachHang kh : khachHangs) {
            Object[] row = {kh.getMaKH(), kh.getTenKH(), kh.getSDT(), kh.getDiaChi(), kh.getGioiTinh()};
            tableModel.addRow(row);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        khachHang_2 = new javax.swing.JPanel();
        lablel_1 = new javax.swing.JPanel();
        maKH = new javax.swing.JLabel();
        tenKH = new javax.swing.JLabel();
        diaChi = new javax.swing.JLabel();
        sdt = new javax.swing.JLabel();
        gioiTinh = new javax.swing.JLabel();
        rbtnNam = new javax.swing.JRadioButton();
        rbtnNu = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        button_cn = new javax.swing.JPanel();
        them = new javax.swing.JButton();
        sua = new javax.swing.JButton();
        xoa = new javax.swing.JButton();
        lamMoi = new javax.swing.JButton();
        timKiem = new javax.swing.JButton();
        luu = new javax.swing.JButton();
        table = new javax.swing.JScrollPane();
        table_thongTinKH = new javax.swing.JTable();
        thongTinKH_label = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        khachHang_2.setBackground(new java.awt.Color(255, 255, 255));

        lablel_1.setBackground(new java.awt.Color(255, 255, 255));
        lablel_1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        maKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        maKH.setText("Mã khách hàng:");

        tenKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tenKH.setText("Tên khách hàng:");

        diaChi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        diaChi.setText("Địa chỉ:");

        sdt.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        sdt.setText("Số điện thoại:");

        gioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        gioiTinh.setText("Giới tính:");

        buttonGroup1.add(rbtnNam);
        rbtnNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rbtnNam.setText("Nam");
        rbtnNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnNu);
        rbtnNu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rbtnNu.setText("Nữ");

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSDT.setText(" ");

        txtMaKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaKH.setText(" ");

        txtTenKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenKH.setText(" ");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDiaChi.setText(" ");

        javax.swing.GroupLayout lablel_1Layout = new javax.swing.GroupLayout(lablel_1);
        lablel_1.setLayout(lablel_1Layout);
        lablel_1Layout.setHorizontalGroup(
            lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lablel_1Layout.createSequentialGroup()
                .addGroup(lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lablel_1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(gioiTinh)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnNam)
                        .addGap(37, 37, 37)
                        .addComponent(rbtnNu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(lablel_1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maKH)
                            .addComponent(sdt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKH)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tenKH)
                            .addComponent(diaChi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDiaChi)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))))
                .addContainerGap())
        );
        lablel_1Layout.setVerticalGroup(
            lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lablel_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tenKH)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(maKH)))
                .addGap(18, 18, 18)
                .addGroup(lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sdt)
                        .addComponent(diaChi)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(lablel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gioiTinh)
                    .addComponent(rbtnNam)
                    .addComponent(rbtnNu))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        button_cn.setBackground(new java.awt.Color(255, 255, 255));

        them.setBackground(new java.awt.Color(204, 255, 204));
        them.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus (1).png"))); // NOI18N
        them.setText("Thêm");
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themActionPerformed(evt);
            }
        });

        sua.setBackground(new java.awt.Color(255, 255, 204));
        sua.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tools (1).png"))); // NOI18N
        sua.setText("Sửa");
        sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaActionPerformed(evt);
            }
        });

        xoa.setBackground(new java.awt.Color(204, 204, 204));
        xoa.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete (1).png"))); // NOI18N
        xoa.setText("Xóa");
        xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaActionPerformed(evt);
            }
        });

        lamMoi.setBackground(new java.awt.Color(255, 204, 255));
        lamMoi.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sync (1).png"))); // NOI18N
        lamMoi.setText("Làm mới");
        lamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lamMoiActionPerformed(evt);
            }
        });

        timKiem.setBackground(new java.awt.Color(204, 255, 255));
        timKiem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        timKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-interface-symbol (1).png"))); // NOI18N
        timKiem.setText("Tìm Kiếm");
        timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemActionPerformed(evt);
            }
        });

        luu.setBackground(new java.awt.Color(153, 255, 153));
        luu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bookmark (2).png"))); // NOI18N
        luu.setText("Lưu");
        luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout button_cnLayout = new javax.swing.GroupLayout(button_cn);
        button_cn.setLayout(button_cnLayout);
        button_cnLayout.setHorizontalGroup(
            button_cnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_cnLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(button_cnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sua, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(button_cnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(xoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(them, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(button_cnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lamMoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(luu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timKiem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        button_cnLayout.setVerticalGroup(
            button_cnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_cnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(button_cnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(them, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(button_cnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(button_cnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(luu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout khachHang_2Layout = new javax.swing.GroupLayout(khachHang_2);
        khachHang_2.setLayout(khachHang_2Layout);
        khachHang_2Layout.setHorizontalGroup(
            khachHang_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachHang_2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lablel_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_cn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        khachHang_2Layout.setVerticalGroup(
            khachHang_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachHang_2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(khachHang_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_cn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(khachHang_2Layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addComponent(lablel_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        table_thongTinKH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table_thongTinKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Giới tính"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.setViewportView(table_thongTinKH);

        thongTinKH_label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        thongTinKH_label.setForeground(new java.awt.Color(0, 102, 102));
        thongTinKH_label.setText("Thông tin khách hàng:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(thongTinKH_label, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(khachHang_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(table))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(khachHang_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thongTinKH_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(table, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 959, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void rbtnNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnNamActionPerformed
    public void addKhachHang(KhachHang khachHang) {
    if (khachHangDao.insertKhachHang(khachHang)) {
        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
        // Thêm khách hàng vào bảng trên giao diện
        DefaultTableModel model = (DefaultTableModel) table_thongTinKH.getModel();
        model.addRow(new Object[]{
            khachHang.getMaKH(), 
            khachHang.getTenKH(), 
            khachHang.getSDT(), 
            khachHang.getDiaChi(), 
            khachHang.getGioiTinh()
        });
    } else {
        JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!");
    }
}

    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        // Lấy thông tin từ các trường nhập liệu
    String maKH = txtMaKH.getText().trim();
    String tenKH = txtTenKH.getText().trim();
    String sDT = txtSDT.getText().trim();
    String diaChi = txtDiaChi.getText().trim();
        String gioiTinh = "";

    // Lấy giới tính
        if (rbtnNam.isSelected()) {
            gioiTinh = "Nam";
        } else if (rbtnNu.isSelected()) {
            gioiTinh = "Nữ";
        }

    // Kiểm tra lỗi nhập liệu
    if (maKH.isEmpty() || tenKH.isEmpty() || sDT.isEmpty() || diaChi.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin khách hàng!");
        return;
    }

   // KhachHang_DAO khachHangDao = new KhachHang_DAO();

        // Kiểm tra xem mã khách hàng đã tồn tại chưa
        if (khachHangDao.checkMaKhachHangExists(maKH)) {
            JOptionPane.showMessageDialog(null, "Mã khách hàng đã tồn tại! Vui lòng nhập mã khác.");
            return;
        }

        // Tạo đối tượng KhachHang từ thông tin đã nhập
        KhachHang khachHang = new KhachHang(maKH, tenKH, sDT, diaChi, gioiTinh);

        // Gọi phương thức addKhachHang để thêm vào cơ sở dữ liệu
    if (khachHangDao.insertKhachHang(khachHang)) {
        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
        // Cập nhật lại bảng thông tin khách hàng
        loadKhachHangData();
    } else {
        JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!");
    }//GEN-LAST:event_themActionPerformed
    
   
}
 
    private void suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaActionPerformed
        
                // Kiểm tra xem có dòng nào được chọn trong bảng không
            int selectedRow = table_thongTinKH.getSelectedRow();
            if (selectedRow >= 0) {
        // Lấy thông tin khách hàng từ giao diện
            String maKH = table_thongTinKH.getValueAt(selectedRow, 0).toString();
        String tenKH = txtTenKH.getText().trim();
        String sdt = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String gioiTinh = rbtnNam.isSelected() ? "Nam" : "Nu";

        // Gọi hàm DAO để sửa thông tin khách hàng
       // KhachHang_DAO khachHangDAO = new KhachHangDAO();
        boolean isSuccess = khachHangDao.suaKhachHang(maKH, tenKH, sdt, diaChi, gioiTinh);

        // Hiển thị kết quả
        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "Sửa thông tin khách hàng thành công!");
            // Tải lại dữ liệu vào bảng
            loadKhachHangData();
            } else {
            JOptionPane.showMessageDialog(this, "Sửa thông tin khách hàng thất bại!");
            }
        } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để sửa!");
        }
    }//GEN-LAST:event_suaActionPerformed
    public void deleteKhachHang(String maKH) {
         // Lấy dòng đang được chọn từ bảng
    int selectedRow = table_thongTinKH.getSelectedRow();
        if (selectedRow != -1) {
        maKH = table_thongTinKH.getValueAt(selectedRow, 0).toString(); // Lấy mã khách hàng từ bảng
        
        // Gọi phương thức xóa từ KhachHangDao
        if (khachHangDao.deleteKhachHang(maKH)) {
            JOptionPane.showMessageDialog(null, "Xóa khách hàng và các yêu cầu liên quan thành công!");
            // Xóa hàng trong bảng
            DefaultTableModel model = (DefaultTableModel) table_thongTinKH.getModel();
            model.removeRow(selectedRow); // Xóa hàng đã chọn
        } else {
            JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại!");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một khách hàng để xóa!");
    }
}

    private void xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaActionPerformed
         String maKH = txtMaKH.getText(); // Lấy mã khách hàng cần xóa
         deleteKhachHang(maKH);
    }//GEN-LAST:event_xoaActionPerformed
    public void clearForm() {
    txtMaKH.setText("");
    txtTenKH.setText("");
    txtSDT.setText("");
    txtDiaChi.setText("");

    // Clear selection of radio buttons
    buttonGroup1.clearSelection();
    loadKhachHangData();
}
    private void lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lamMoiActionPerformed
        clearForm();
    }//GEN-LAST:event_lamMoiActionPerformed
    public void findKhachHang(String maKH) {
    KhachHang kh = khachHangDao.findKhachHangByMaKH(maKH);
    DefaultTableModel tableModel = (DefaultTableModel) table_thongTinKH.getModel(); // Lấy model của bảng

    // Clear các dòng hiện tại trong bảng
    tableModel.setRowCount(0); 

    if (kh != null) {
        // Cập nhật các trường nhập liệu
        txtMaKH.setText(kh.getMaKH());
        txtTenKH.setText(kh.getTenKH());
        txtSDT.setText(kh.getSDT());
        txtDiaChi.setText(kh.getDiaChi());

        if (kh.getGioiTinh().equals("Nam")) {
            rbtnNam.setSelected(true);
        } else {
            rbtnNu.setSelected(true);
        }

        // Thêm thông tin của khách hàng tìm được vào bảng
        Object[] row = new Object[] {
            kh.getMaKH(), 
            kh.getTenKH(), 
            kh.getSDT(), 
            kh.getDiaChi(), 
            kh.getGioiTinh()
        };
        tableModel.addRow(row); // Thêm vào bảng
    } else {
        // Hiển thị thông báo nếu không tìm thấy khách hàng
        JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng!");
    }
}

    private void timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemActionPerformed
        String maKH = txtMaKH.getText().trim(); // Lấy mã khách hàng để tìm
        findKhachHang(maKH);
    }//GEN-LAST:event_timKiemActionPerformed

    private void luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luuActionPerformed
         // Lấy thông tin từ các trường nhập liệu
        String maKH = txtMaKH.getText(); 
        String tenKH = txtTenKH.getText(); 
        String sdt = txtSDT.getText(); 
        String diaChi = txtDiaChi.getText(); 
        String gioiTinh = rbtnNam.isSelected() ? "Nam" : "Nữ"; 

        // Tạo đối tượng KhachHang mới với thông tin đã nhập
        KhachHang khachHang = new KhachHang(maKH, tenKH, sdt, diaChi, gioiTinh);

        // Gọi phương thức cập nhật trong KhachHangDao
        if (khachHangDao.updateKhachHang(khachHang)) {
            // Cập nhật lại bảng hiển thị thông tin
            DefaultTableModel model = (DefaultTableModel) table_thongTinKH.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 0).equals(maKH)) { 
                    model.setValueAt(tenKH, i, 1); 
                    model.setValueAt(sdt, i, 2); 
                    model.setValueAt(diaChi, i, 3); 
                    model.setValueAt(gioiTinh, i, 4); 
                    break;
                }
            }
            JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thất bại!");
        }
    }//GEN-LAST:event_luuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel button_cn;
    private javax.swing.JLabel diaChi;
    private javax.swing.JLabel gioiTinh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel khachHang_2;
    private javax.swing.JPanel lablel_1;
    private javax.swing.JButton lamMoi;
    private javax.swing.JButton luu;
    private javax.swing.JLabel maKH;
    private javax.swing.JRadioButton rbtnNam;
    private javax.swing.JRadioButton rbtnNu;
    private javax.swing.JLabel sdt;
    private javax.swing.JButton sua;
    private javax.swing.JScrollPane table;
    private javax.swing.JTable table_thongTinKH;
    private javax.swing.JLabel tenKH;
    private javax.swing.JButton them;
    private javax.swing.JLabel thongTinKH_label;
    private javax.swing.JButton timKiem;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JButton xoa;
    // End of variables declaration//GEN-END:variables
}
