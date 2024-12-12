/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.manHinhChinh;

import entity.NhanVien;
import gui.KhachHangNhanVien.Frame_XemThongTinNhanVien;
import gui.KhachHangNhanVien.Panel_QuanLyNhanVien;
import gui.KhachHangNhanVien.PhanCaNhanVien;
import gui.KhachHangNhanVien.QuanLyKhachHang;
import gui.KhachHangNhanVien.XemThongTinNhanVien;
import gui.ban.Form_QuanLyBan;

import gui.dangNhap.Login;
import gui.datBan.ManHinh_DatBan;
import gui.hoaDon.DanhSachHoaDon;
import gui.monAn.Form_QuanLyMon;
import gui.thongKe.ThongKeDoanhThu;
import gui.thongKe.ThongKeKhachHang;
import gui.thongKe.ThongKeNhanVien;
import gui.thongKe.ThongKeSanPham;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Admin
 */
public class ManHinhChinh extends javax.swing.JFrame {
    NhanVien nhanvien;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    
    /**
     * Creates new form ManHinhChinh
     */
    public ManHinhChinh(NhanVien nhanvien) {
        this.nhanvien = nhanvien;
        
        initComponents();
        addPopupmenu();
//        tenNhanVien.setText(this.nhanvien.getTenNV());
        btn_dangxuat.setText(this.nhanvien.getTenNV());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        anhManHinhChinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Thai_restaurant_resized_900x600.png")));
     //   anh.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/nha-hang-2-tang-dep-phong-cach-tan-co-dien (1).jpg")));
     //   logo.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/LogonhànàngThái__3_-removebg-prevoew (4).png")));
        anh.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/nha-hang-2-tang-dep-phong-cach-tan-co-dien (1).jpg")));

        btn_qly_ban.setBackground(Color.white);
        btn_TrangChu.setBackground(Color.YELLOW);
        btn_DatBan.setBackground(Color.white);
        btn_ThongKe.setBackground(Color.white);
        btn_qlyKhachHang.setBackground(Color.white);
        btn_qly_mon.setBackground(Color.white);
        btn_qlyNhanVien.setBackground(Color.white);
        btn_hdsd.setBackground(Color.white);
        
        btn_TrangChu.setBorder(new LineBorder(Color.YELLOW, 2));
        btn_qly_ban.setBorder(new LineBorder(Color.YELLOW, 2));
        btn_DatBan.setBorder(new LineBorder(Color.YELLOW, 2));
        btn_ThongKe.setBorder(new LineBorder(Color.YELLOW, 2));
        btn_qlyKhachHang.setBorder(new LineBorder(Color.YELLOW, 2));
        btn_qly_mon.setBorder(new LineBorder(Color.YELLOW, 2));
        btn_qlyNhanVien.setBorder(new LineBorder(Color.YELLOW, 2));
        btn_hdsd.setBorder(new LineBorder(Color.YELLOW, 2));
        
        
       
        
    }
    
    private void addPopupmenu(){
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
          
        jMenuItem1.setText("Thông tin chi tiết");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Đăng xuất");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);
        
        btn_dangxuat.setText("Tên Nhân Viên");
        btn_dangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangxuatActionPerformed(evt);
            }
        });

    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_TrangChu = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        btn_qly_ban = new javax.swing.JButton();
        btn_qly_mon = new javax.swing.JButton();
        btn_DatBan = new javax.swing.JButton();
        btn_qlyKhachHang = new javax.swing.JButton();
        btn_qlyNhanVien = new javax.swing.JButton();
        btn_ThongKe = new javax.swing.JButton();
        btn_hdsd = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_dangxuat = new javax.swing.JButton();
        P_chucNang = new javax.swing.JPanel();
        anh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        btn_TrangChu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_TrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home (1).png"))); // NOI18N
        btn_TrangChu.setMnemonic('1');
        btn_TrangChu.setText("Trang chủ");
        btn_TrangChu.setToolTipText("ALT+1");
        btn_TrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TrangChuActionPerformed(evt);
            }
        });

        logo.setForeground(new java.awt.Color(0, 255, 255));
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/LogonhàhàngThái__3_-removebg-preview (5).png"))); // NOI18N

        btn_qly_ban.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_qly_ban.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user-gear (1).png"))); // NOI18N
        btn_qly_ban.setMnemonic('2');
        btn_qly_ban.setText("Quản lý bàn");
        btn_qly_ban.setToolTipText("ALT+2");
        btn_qly_ban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qly_banActionPerformed(evt);
            }
        });

        btn_qly_mon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_qly_mon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dinner (1).png"))); // NOI18N
        btn_qly_mon.setMnemonic('3');
        btn_qly_mon.setText("Quản lý món ăn");
        btn_qly_mon.setToolTipText("ALT+3");
        btn_qly_mon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qly_monActionPerformed(evt);
            }
        });

        btn_DatBan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_DatBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/computer-set-on-a-table (1).png"))); // NOI18N
        btn_DatBan.setMnemonic('4');
        btn_DatBan.setText("Đặt bàn");
        btn_DatBan.setToolTipText("ALT+4");
        btn_DatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DatBanActionPerformed(evt);
            }
        });

        btn_qlyKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_qlyKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hr-group (1).png"))); // NOI18N
        btn_qlyKhachHang.setMnemonic('5');
        btn_qlyKhachHang.setText("Quản lý khách hàng");
        btn_qlyKhachHang.setToolTipText("ALT+5");
        btn_qlyKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qlyKhachHangActionPerformed(evt);
            }
        });

        btn_qlyNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_qlyNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user-bag (1).png"))); // NOI18N
        btn_qlyNhanVien.setMnemonic('6');
        btn_qlyNhanVien.setText("Quản lý nhân viên");
        btn_qlyNhanVien.setToolTipText("ALT+6");
        btn_qlyNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qlyNhanVienActionPerformed(evt);
            }
        });

        btn_ThongKe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_ThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/graph (1).png"))); // NOI18N
        btn_ThongKe.setMnemonic('7');
        btn_ThongKe.setText("Thống kê");
        btn_ThongKe.setToolTipText("ALT+7");
        btn_ThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThongKeActionPerformed(evt);
            }
        });

        btn_hdsd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_hdsd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/information-button (1).png"))); // NOI18N
        btn_hdsd.setMnemonic('I');
        btn_hdsd.setText("About us");
        btn_hdsd.setToolTipText("ALT+I");
        btn_hdsd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hdsdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_hdsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ThongKe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_qlyNhanVien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_qlyKhachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(btn_DatBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_qly_mon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_qly_ban, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_TrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btn_TrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_qly_ban, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_qly_mon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_DatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_qlyKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_qlyNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_hdsd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jLabel3.setFont(new java.awt.Font("Vladimir Script", 3, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 51));
        jLabel3.setText("Thái Restaurant");

        btn_dangxuat.setBackground(new java.awt.Color(0, 102, 102));
        btn_dangxuat.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        btn_dangxuat.setForeground(new java.awt.Color(255, 255, 255));
        btn_dangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profile-user (1).png"))); // NOI18N
        btn_dangxuat.setText("Tên Nhân Viên");
        btn_dangxuat.setBorder(null);
        btn_dangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangxuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(359, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(340, 340, 340)
                .addComponent(btn_dangxuat)
                .addGap(36, 36, 36))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_dangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        P_chucNang.setBackground(new java.awt.Color(255, 255, 255));

        anh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/nha-hang-2-tang-dep-phong-cach-tan-co-dien (1) (1).jpg"))); // NOI18N

        javax.swing.GroupLayout P_chucNangLayout = new javax.swing.GroupLayout(P_chucNang);
        P_chucNang.setLayout(P_chucNangLayout);
        P_chucNangLayout.setHorizontalGroup(
            P_chucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_chucNangLayout.createSequentialGroup()
                .addComponent(anh, javax.swing.GroupLayout.PREFERRED_SIZE, 1209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        P_chucNangLayout.setVerticalGroup(
            P_chucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(anh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(P_chucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P_chucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_qly_banActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qly_banActionPerformed
        // TODO add your handling code here:
        Form_QuanLyBan qlBan = new Form_QuanLyBan();
        
   
        
        JTabbedPane tabqlBan = new JTabbedPane();
        tabqlBan.addTab("Quản lý bàn", qlBan);

        
        P_chucNang.removeAll();
        P_chucNang.setLayout(new java.awt.BorderLayout());
        P_chucNang.add(tabqlBan);
        P_chucNang.validate();
        P_chucNang.repaint();
        
        
        btn_qly_ban.setBackground(Color.YELLOW);
        btn_TrangChu.setBackground(Color.white);
        btn_DatBan.setBackground(Color.white);
        btn_ThongKe.setBackground(Color.white);
        btn_qlyKhachHang.setBackground(Color.white);
        btn_qly_mon.setBackground(Color.white);
        btn_qlyNhanVien.setBackground(Color.white);
        btn_hdsd.setBackground(Color.white);
        
        btn_TrangChu.setForeground(Color.black);
        btn_DatBan.setForeground(Color.black);
        btn_ThongKe.setForeground(Color.black);
        btn_qlyKhachHang.setForeground(Color.black);
        btn_qlyNhanVien.setForeground(Color.black);
        btn_qly_mon.setForeground(Color.black);
        btn_hdsd.setForeground(Color.black);
        btn_qly_ban.setForeground(Color.black);
    }//GEN-LAST:event_btn_qly_banActionPerformed

    private void btn_qly_monActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qly_monActionPerformed

            Form_QuanLyMon ftm = new Form_QuanLyMon();
            
//        
        JTabbedPane tabQlyMon = new JTabbedPane();
        tabQlyMon.addTab("Quản lý món", ftm);
        
        P_chucNang.removeAll();
        P_chucNang.setLayout(new java.awt.BorderLayout());
        P_chucNang.add(tabQlyMon);
        P_chucNang.validate();
        P_chucNang.repaint();
        
        
        btn_qly_ban.setBackground(Color.white);
        btn_TrangChu.setBackground(Color.white);
        btn_DatBan.setBackground(Color.white);
        btn_ThongKe.setBackground(Color.white);
        btn_qlyKhachHang.setBackground(Color.white);
        btn_qly_mon.setBackground(Color.YELLOW);
        btn_qlyNhanVien.setBackground(Color.white);
        btn_hdsd.setBackground(Color.white);
        
        btn_TrangChu.setForeground(Color.black);
        btn_DatBan.setForeground(Color.black);
        btn_ThongKe.setForeground(Color.black);
        btn_qlyKhachHang.setForeground(Color.black);
        btn_qlyNhanVien.setForeground(Color.black);
        btn_qly_mon.setForeground(Color.black);
        btn_hdsd.setForeground(Color.black);
        btn_qly_ban.setForeground(Color.black);
    }//GEN-LAST:event_btn_qly_monActionPerformed

    private void btn_TrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TrangChuActionPerformed
                    // TODO add your handling code here:        
        P_chucNang.removeAll();
        P_chucNang.setLayout(new java.awt.BorderLayout());
        
       // P_chucNang = new javax.swing.JLabel();
       // anh.add(P_chucNang);
        anh.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/nha-hang-2-tang-dep-phong-cach-tan-co-dien (1).jpg")));
       P_chucNang.add(anh, java.awt.BorderLayout.CENTER);
        P_chucNang.validate();
        P_chucNang.repaint();
        
        btn_qly_ban.setBackground(Color.white);
        btn_TrangChu.setBackground(Color.YELLOW);
        btn_DatBan.setBackground(Color.white);
        btn_ThongKe.setBackground(Color.white);
        btn_qlyKhachHang.setBackground(Color.white);
        btn_qly_mon.setBackground(Color.white);
        btn_qlyNhanVien.setBackground(Color.white);
        btn_hdsd.setBackground(Color.white);
        
        btn_DatBan.setForeground(Color.black);
        btn_qly_ban.setForeground(Color.black);
        btn_ThongKe.setForeground(Color.black);
        btn_qlyKhachHang.setForeground(Color.black);
        btn_qlyNhanVien.setForeground(Color.black);
        btn_qly_mon.setForeground(Color.black);
        btn_hdsd.setForeground(Color.black);
        btn_TrangChu.setForeground(Color.black);
    }//GEN-LAST:event_btn_TrangChuActionPerformed

    private void btn_DatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DatBanActionPerformed
        // TODO add your handling code here:
        ManHinh_DatBan db = new ManHinh_DatBan(nhanvien);
        
        JTabbedPane tabDatBan = new JTabbedPane();
        tabDatBan.addTab("Đặt bàn", db);
        
        P_chucNang.removeAll();
        P_chucNang.setLayout(new java.awt.BorderLayout());
        P_chucNang.add(tabDatBan);
        P_chucNang.validate();
        P_chucNang.repaint();
        
        
         btn_qly_ban.setBackground(Color.white);
        btn_TrangChu.setBackground(Color.white);
        btn_DatBan.setBackground(Color.YELLOW);
        btn_ThongKe.setBackground(Color.white);
        btn_qlyKhachHang.setBackground(Color.white);
        btn_qly_mon.setBackground(Color.white);
        btn_qlyNhanVien.setBackground(Color.white);
        btn_hdsd.setBackground(Color.white);
        
        btn_TrangChu.setForeground(Color.black);
        btn_DatBan.setForeground(Color.black);
        btn_ThongKe.setForeground(Color.black);
        btn_qlyKhachHang.setForeground(Color.black);
        btn_qlyNhanVien.setForeground(Color.black);
        btn_qly_mon.setForeground(Color.black);
        btn_hdsd.setForeground(Color.black);
        btn_qly_ban.setForeground(Color.black);
    }//GEN-LAST:event_btn_DatBanActionPerformed

    private void btn_ThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThongKeActionPerformed
        // TODO add your handling code here:
        ThongKeDoanhThu tkdt = new ThongKeDoanhThu();
        ThongKeKhachHang tkkh = new ThongKeKhachHang();
        ThongKeNhanVien tknv = new ThongKeNhanVien();
        ThongKeSanPham tksp = new ThongKeSanPham();
        DanhSachHoaDon dshd = new DanhSachHoaDon();
        
        JTabbedPane tabThongKe = new JTabbedPane();
        tabThongKe.addTab("Danh Sách Hóa Đơn", dshd);
        tabThongKe.addTab("Thống kê Doanh thu", tkdt);
        tabThongKe.addTab("Thống kê Khách hàng", tkkh);
        tabThongKe.addTab("Thống kê Nhân viên", tknv);
        tabThongKe.addTab("Thống kê Sản phẩm", tksp);
        
        P_chucNang.removeAll();
        P_chucNang.setLayout(new java.awt.BorderLayout());
        P_chucNang.add(tabThongKe);
        P_chucNang.validate();
        P_chucNang.repaint();
        
         btn_qly_ban.setBackground(Color.white);
        btn_TrangChu.setBackground(Color.white);
        btn_DatBan.setBackground(Color.white);
        btn_ThongKe.setBackground(Color.YELLOW);
        btn_qlyKhachHang.setBackground(Color.white);
        btn_qly_mon.setBackground(Color.white);
        btn_qlyNhanVien.setBackground(Color.white);
        btn_hdsd.setBackground(Color.white);
        
        btn_TrangChu.setForeground(Color.black);
        btn_DatBan.setForeground(Color.black);
        btn_ThongKe.setForeground(Color.black);
        btn_qlyKhachHang.setForeground(Color.black);
        btn_qlyNhanVien.setForeground(Color.black);
        btn_qly_mon.setForeground(Color.black);
        btn_hdsd.setForeground(Color.black);
        btn_qly_ban.setForeground(Color.black);
    }//GEN-LAST:event_btn_ThongKeActionPerformed

    private void btn_qlyKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qlyKhachHangActionPerformed
        try {
            // TODO add your handling code here:
            QuanLyKhachHang qlkh = new QuanLyKhachHang();
            
            JTabbedPane tabKhachHang = new JTabbedPane();
            tabKhachHang.add("Quản lý khách hàng", qlkh);
            
            P_chucNang.removeAll();
            P_chucNang.setLayout(new java.awt.BorderLayout());
            P_chucNang.add(tabKhachHang);
            P_chucNang.validate();
            P_chucNang.repaint();
            
             btn_qly_ban.setBackground(Color.white);
        btn_TrangChu.setBackground(Color.white);
        btn_DatBan.setBackground(Color.white);
        btn_ThongKe.setBackground(Color.white);
        btn_qlyKhachHang.setBackground(Color.YELLOW);
        btn_qly_mon.setBackground(Color.white);
        btn_qlyNhanVien.setBackground(Color.white);
        btn_hdsd.setBackground(Color.white);
        

            btn_TrangChu.setForeground(Color.black);
            btn_DatBan.setForeground(Color.black);
            btn_qly_mon.setForeground(Color.black);
            btn_ThongKe.setForeground(Color.black);
            btn_qlyNhanVien.setForeground(Color.black);
            btn_qly_ban.setForeground(Color.black);
            btn_hdsd.setForeground(Color.black);
        } catch (SQLException ex) {
            Logger.getLogger(ManHinhChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_qlyKhachHangActionPerformed

    private void btn_qlyNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qlyNhanVienActionPerformed
        // TODO add your handling code here:
        PhanCaNhanVien pcnv = new PhanCaNhanVien();
        XemThongTinNhanVien xemtt = new XemThongTinNhanVien(this.nhanvien.getMaNV());
        JTabbedPane tabNhanVien = new JTabbedPane();
        tabNhanVien.add("Xem thông tin nhân viên", xemtt);
        tabNhanVien.add("Phân ca nhân viên", pcnv);
        
        P_chucNang.removeAll();
        P_chucNang.setLayout(new java.awt.BorderLayout());
        P_chucNang.add(tabNhanVien);
        P_chucNang.validate();
        P_chucNang.repaint();
        
        btn_qlyNhanVien.setBackground(Color.YELLOW);
        btn_qlyNhanVien.setForeground(Color.black);
        
        btn_TrangChu.setBackground(Color.white);
        btn_DatBan.setBackground(Color.white);
        btn_qly_mon.setBackground(Color.white);
        btn_qlyKhachHang.setBackground(Color.white);
        btn_qly_ban.setBackground(Color.white);
        btn_ThongKe.setBackground(Color.white);
        btn_hdsd.setBackground(Color.white);
        
        btn_TrangChu.setForeground(Color.black);
        btn_DatBan.setForeground(Color.black);
        btn_qly_mon.setForeground(Color.black);
        btn_qlyKhachHang.setForeground(Color.black);
        btn_ThongKe.setForeground(Color.black);
        btn_qly_ban.setForeground(Color.black);
        btn_hdsd.setForeground(Color.black);
    }//GEN-LAST:event_btn_qlyNhanVienActionPerformed

    private void btn_hdsdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hdsdActionPerformed
        // TODO add your handling code here:
        
        btn_hdsd.setBackground(Color.YELLOW);
        btn_hdsd.setForeground(Color.black);
        
        btn_TrangChu.setBackground(Color.white);
        btn_DatBan.setBackground(Color.white);
        btn_qly_mon.setBackground(Color.white);
        btn_qlyKhachHang.setBackground(Color.white);
        btn_qly_ban.setBackground(Color.white);
        btn_ThongKe.setBackground(Color.white);
        btn_qlyNhanVien.setBackground(Color.white);
        
        btn_TrangChu.setForeground(Color.black);
        btn_DatBan.setForeground(Color.black);
        btn_qly_mon.setForeground(Color.black);
        btn_qlyKhachHang.setForeground(Color.black);
        btn_ThongKe.setForeground(Color.black);
        btn_qly_ban.setForeground(Color.black);
        btn_qlyNhanVien.setForeground(Color.black);
    }//GEN-LAST:event_btn_hdsdActionPerformed

    private void btn_dangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangxuatActionPerformed
        // TODO add your handling code here:
        jPopupMenu1.show(btn_dangxuat, 0, btn_dangxuat.getHeight());
    }//GEN-LAST:event_btn_dangxuatActionPerformed
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String maTK = Login.getLoggedInMaTK(); // Lấy mã tài khoản từ lớp quản lý đăng nhập
        Frame_XemThongTinNhanVien nvForm = new Frame_XemThongTinNhanVien(this.nhanvien.getMaNV());
        nvForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nvForm.setVisible(true);
        nvForm.setLocationRelativeTo(this);
    }                                          

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        JOptionPane.showMessageDialog(this, "Đăng xuất thành công!");
        this.dispose(); // Đóng form hiện tại
        Login Frame_login = new Login();
        Frame_login.setLocationRelativeTo(null);
        Frame_login.setVisible(true);
    } 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ManHinhChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel P_chucNang;
    private javax.swing.JLabel anh;
    private javax.swing.JButton btn_DatBan;
    private javax.swing.JButton btn_ThongKe;
    private javax.swing.JButton btn_TrangChu;
    private javax.swing.JButton btn_dangxuat;
    private javax.swing.JButton btn_hdsd;
    private javax.swing.JButton btn_qlyKhachHang;
    private javax.swing.JButton btn_qlyNhanVien;
    private javax.swing.JButton btn_qly_ban;
    private javax.swing.JButton btn_qly_mon;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
