/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.thongKe;

import dao.HoaDon_DAO;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thanh Phuong
 */
public class ThongKeNhanVien extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeNhanVien
     */
    public ThongKeNhanVien() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboBoxThongKe = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnThongKe = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableThongKe = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Thống kê theo:");

        comboBoxThongKe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboBoxThongKe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày hôm nay", "Ngày hôm qua", "Theo tuần", "Theo tháng", "Theo năm" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/revenue (1).png"))); // NOI18N
        jLabel10.setText("Tổng tiền bán được");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTongTien.setText(" ");
        txtTongTien.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        btnThongKe.setBackground(new java.awt.Color(0, 255, 255));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Static (1).png"))); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(752, 752, 752)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(comboBoxThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(144, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(289, 289, 289)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(comboBoxThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThongKe))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongTien))
                        .addGap(34, 34, 34))))
        );

        tableThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Tổng số hóa đơn", "Tổng tiền hóa đơn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableThongKe);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Thông tin nhân viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
 // Lấy lựa chọn từ comboBoxThongKe
       String luaChon = comboBoxThongKe.getSelectedItem().toString();
       LocalDate ngayBatDau = null;
       LocalDate ngayKetThuc = null;

       // Xác định khoảng thời gian
       switch (luaChon) {
           case "Ngày hôm nay":
               ngayBatDau = LocalDate.now();
               ngayKetThuc = LocalDate.now();
               break;
           case "Ngày hôm qua":
               ngayBatDau = LocalDate.now().minusDays(1);
               ngayKetThuc = LocalDate.now().minusDays(1);
               break;
           case "Theo tuần":
               ngayBatDau = LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().getValue() - 1); // Đầu tuần
               ngayKetThuc = LocalDate.now(); // Hôm nay
               break;
           case "Theo tháng":
               ngayBatDau = LocalDate.now().withDayOfMonth(1); // Đầu tháng
               ngayKetThuc = LocalDate.now(); // Hôm nay
               break;
           case "Theo năm":
               ngayBatDau = LocalDate.now().withDayOfYear(1); // Đầu năm
               ngayKetThuc = LocalDate.now(); // Hôm nay
               break;
           default:
               JOptionPane.showMessageDialog(this, "Vui lòng chọn loại thống kê hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
               return;
       }

       // Gọi DAO để lấy dữ liệu thống kê
       HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
       ArrayList<Object[]> danhSachThongKe = hoaDonDAO.LayThongKe(luaChon, ngayBatDau, ngayKetThuc);

       // Hiển thị dữ liệu vào bảng
       DefaultTableModel model = (DefaultTableModel) tableThongKe.getModel();
       model.setRowCount(0); // Xóa dữ liệu cũ
       for (Object[] row : danhSachThongKe) {
           model.addRow(row);
       }

       // Gọi DAO để tính tổng tiền
       double tongTien = hoaDonDAO.tinhTongTien(ngayBatDau, ngayKetThuc);

       // Định dạng và hiển thị tổng tiền
       NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
       String tongTienFormatted = currencyFormat.format(tongTien);
       txtTongTien.setText(tongTienFormatted);

       // Hiển thị thông báo
       JOptionPane.showMessageDialog(this, "Thống kê hoàn tất!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnThongKeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThongKe;
    private javax.swing.JComboBox<String> comboBoxThongKe;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableThongKe;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}

