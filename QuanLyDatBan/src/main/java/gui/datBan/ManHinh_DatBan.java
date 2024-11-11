/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.datBan;

import gui.card.Card_Ban;
import dao.Ban_DAO;
import entity.NhanVien;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class ManHinh_DatBan extends javax.swing.JPanel {
    private Ban_DAO banDAO;
    private int t_count = 1;
    private NhanVien nhanvien;
    /**
     * Creates new form P_DatBan
     */
    public ManHinh_DatBan(NhanVien nhanvien) {
        initComponents();
        this.nhanvien = nhanvien;
        banDAO = new Ban_DAO();
        String str = "Tầng " + t_count;
        tang.setText(str);
        
        btn_tim.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Desktop\\netbeans_tutor\\QuanLyDatBan\\src\\main\\java\\icon\\search.png")); // NOI18N
        
        hienThiDanhSachBan(str);
        btn_truoc.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        lb_tim = new javax.swing.JLabel();
        t_tim = new javax.swing.JTextField();
        btn_tim = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        combo_trangthai = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        combo_soghe = new javax.swing.JComboBox<>();
        tang = new javax.swing.JLabel();
        btn_loc = new javax.swing.JButton();
        footer = new javax.swing.JPanel();
        btn_truoc = new javax.swing.JButton();
        btn_sau = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btn_reset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        P_Scroll = new javax.swing.JScrollPane();
        P_dsB = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(255, 255, 255));

        lb_tim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_tim.setText("Nhập mã bàn");

        btn_tim.setBorder(null);
        btn_tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Trạng thái");

        combo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Chọn --", "Trống", "Có khách", "Đặt trước" }));
        combo_trangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_trangthaiActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Số lượng ghế");

        combo_soghe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Chọn --", "2", "4", "6", "8" }));
        combo_soghe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_sogheActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(combo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(combo_soghe, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_soghe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jLabel2.getAccessibleContext().setAccessibleDescription("");

        tang.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        tang.setForeground(new java.awt.Color(102, 153, 255));
        tang.setText("Tầng");

        btn_loc.setText("Lọc");
        btn_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_locActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_tim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_loc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(tang, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(797, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(t_tim, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(lb_tim, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                    .addComponent(btn_tim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_loc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tang)
                .addContainerGap())
        );

        btn_truoc.setText("Trang trước");
        btn_truoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_truocActionPerformed(evt);
            }
        });

        btn_sau.setText("Trang sau");
        btn_sau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sauActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_truoc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_sau, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_reset)
                .addGap(20, 20, 20))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_reset, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(footerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(footerLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_sau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_truoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        P_Scroll.setBackground(new java.awt.Color(255, 255, 255));
        P_Scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        P_Scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        P_dsB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout P_dsBLayout = new javax.swing.GroupLayout(P_dsB);
        P_dsB.setLayout(P_dsBLayout);
        P_dsBLayout.setHorizontalGroup(
            P_dsBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 959, Short.MAX_VALUE)
        );
        P_dsBLayout.setVerticalGroup(
            P_dsBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );

        P_Scroll.setViewportView(P_dsB);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(P_Scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(70, 70, 70))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P_Scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(footer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sauActionPerformed
        // Giảm số tầng khi nhấn nút
        if (t_count < 4) { // Kiểm tra để không giảm tầng xuống 0
            t_count += 1;
            String str = "Tầng " + t_count;
            tang.setText(str);
            hienThiDanhSachBan(str);
            btn_truoc.setEnabled(true);
        }
        
        if (t_count >= 3) {
            btn_sau.setEnabled(false);
        } else {
            btn_sau.setEnabled(true);
        }
    }//GEN-LAST:event_btn_sauActionPerformed

    private void btn_truocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_truocActionPerformed
        // Giảm số tầng khi nhấn nút
        if (t_count > 1) { // Kiểm tra để không giảm tầng xuống 0
            t_count -= 1;
            String str = "Tầng " + t_count;
            tang.setText(str);
            hienThiDanhSachBan(str);
            btn_sau.setEnabled(true);
        }
        
        if (t_count > 1) {
            btn_truoc.setEnabled(true);
        } else {
            btn_truoc.setEnabled(false);
        }
    }//GEN-LAST:event_btn_truocActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        t_count = 1;
        String str = "Tầng " + t_count;
        tang.setText(str);
        hienThiDanhSachBan(str);
        btn_truoc.setEnabled(false);
        btn_sau.setEnabled(true);
        
    }//GEN-LAST:event_btn_resetActionPerformed

    private void combo_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_trangthaiActionPerformed
        // TODO add your handling code here:
        String trangthai = (String) combo_trangthai.getSelectedItem();
        String str = "Tầng " + t_count;
        tang.setText(str);
        hienThiDanhSachBan(str, trangthai);
    }//GEN-LAST:event_combo_trangthaiActionPerformed

    private void combo_sogheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_sogheActionPerformed
        // TODO add your handling code here:
        int soghe = Integer.parseInt(combo_soghe.getSelectedItem().toString());
        String str = "Tầng " + t_count;
        tang.setText(str);
        hienThiDanhSachBan(str, soghe);
    }//GEN-LAST:event_combo_sogheActionPerformed

    private void btn_timActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timActionPerformed
        P_dsB.removeAll(); // Xoá tất cả các phần tử trong P_dsB
        String maBan = t_tim.getText();
        List<Card_Ban> danhSachCardBan = banDAO.taoDanhSachTheoMa(maBan, nhanvien);
        
        tang.setText(null);
        btn_truoc.setEnabled(false);
        btn_sau.setEnabled(false);
        
        P_dsB.setLayout(new java.awt.GridLayout(0, 5, 10, 10));

        for (Card_Ban cardBan : danhSachCardBan) {
            P_dsB.add(cardBan);
        }

        P_dsB.revalidate();
        P_dsB.repaint();
    }//GEN-LAST:event_btn_timActionPerformed

    private void btn_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_locActionPerformed
        // TODO add your handling code here:
        int soghe = Integer.parseInt(combo_soghe.getSelectedItem().toString());
        String trangthai = (String) combo_trangthai.getSelectedItem();
        String str = "Tầng " + t_count;
        tang.setText(str);
        hienThiDanhSachBan(str, soghe, trangthai);
    }//GEN-LAST:event_btn_locActionPerformed
    
    private void hienThiDanhSachBan(String tang, int ghe, String trangthai){
        P_dsB.removeAll(); // Xoá tất cả các phần tử trong P_dsB

        // Lấy danh sách Card_Ban từ Ban_DAO
        List<Card_Ban> danhSachCardBan = banDAO.taoDanhSachTangGheVaTrangThai(tang, ghe, trangthai, nhanvien);

        // Thiết lập layout cho P_dsB
        P_dsB.setLayout(new java.awt.GridLayout(0, 5, 10, 10));

        // Thêm từng Card_Ban vào P_dsB
        for (Card_Ban cardBan : danhSachCardBan) {
            P_dsB.add(cardBan);
        }

        // Cập nhật lại P_dsB sau khi thêm các phần tử mới
        P_dsB.revalidate();
        P_dsB.repaint();
    }
    
    private void hienThiDanhSachBan(String tang, int ghe){
        P_dsB.removeAll(); // Xoá tất cả các phần tử trong P_dsB

        // Lấy danh sách Card_Ban từ Ban_DAO
        List<Card_Ban> danhSachCardBan = banDAO.taoDanhSachTangvaGhe(tang, ghe, nhanvien);

        // Thiết lập layout cho P_dsB
        P_dsB.setLayout(new java.awt.GridLayout(0, 5, 10, 10));

        // Thêm từng Card_Ban vào P_dsB
        for (Card_Ban cardBan : danhSachCardBan) {
            P_dsB.add(cardBan);
        }

        // Cập nhật lại P_dsB sau khi thêm các phần tử mới
        P_dsB.revalidate();
        P_dsB.repaint();
    }
    
    private void hienThiDanhSachBan(String tang, String trangthai){
        P_dsB.removeAll(); // Xoá tất cả các phần tử trong P_dsB

        // Lấy danh sách Card_Ban từ Ban_DAO
        List<Card_Ban> danhSachCardBan = banDAO.taoDanhSachTangvaTrangThai(tang, trangthai, nhanvien);

        // Thiết lập layout cho P_dsB
        P_dsB.setLayout(new java.awt.GridLayout(0, 5, 10, 10));

        // Thêm từng Card_Ban vào P_dsB
        for (Card_Ban cardBan : danhSachCardBan) {
            P_dsB.add(cardBan);
        }

        // Cập nhật lại P_dsB sau khi thêm các phần tử mới
        P_dsB.revalidate();
        P_dsB.repaint();
    }
    
    private void hienThiDanhSachBan(String tang) {
        P_dsB.removeAll(); // Xoá tất cả các phần tử trong P_dsB

        // Lấy danh sách Card_Ban từ Ban_DAO
        List<Card_Ban> danhSachCardBan = banDAO.taoDanhSachTang(tang, nhanvien);

        // Thiết lập layout cho P_dsB
        P_dsB.setLayout(new java.awt.GridLayout(0, 5, 10, 10));

        // Thêm từng Card_Ban vào P_dsB
        for (Card_Ban cardBan : danhSachCardBan) {
            P_dsB.add(cardBan);
        }

        // Cập nhật lại P_dsB sau khi thêm các phần tử mới
        P_dsB.revalidate();
        P_dsB.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane P_Scroll;
    private javax.swing.JPanel P_dsB;
    private javax.swing.JButton btn_loc;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_sau;
    private javax.swing.JButton btn_tim;
    private javax.swing.JButton btn_truoc;
    private javax.swing.JComboBox<String> combo_soghe;
    private javax.swing.JComboBox<String> combo_trangthai;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lb_tim;
    private javax.swing.JTextField t_tim;
    private javax.swing.JLabel tang;
    // End of variables declaration//GEN-END:variables
}