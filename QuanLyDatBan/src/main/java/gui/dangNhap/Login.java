
package gui.dangNhap;

import gui.dangNhap.SignUp;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;
import gui.manHinhChinh.ManHinhChinh;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JFrame;

public class Login extends javax.swing.JFrame {
    private NhanVien nhanVien;
    private TaiKhoan taiKhoan;
    private TaiKhoan_DAO taiKhoanDAO;
    private NhanVien_DAO nhanVienDAO;
 
    public Login() {
        try {
            initComponents();
            taiKhoanDAO = new TaiKhoan_DAO();
            nhanVienDAO = new NhanVien_DAO();
            T_TaiKhoan.setText("hao123A");
            Password_f.setText("1234567Aa");
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String loggedInMaTK;

    public static void setLoggedInMaTK(String maTK) {
        loggedInMaTK = maTK;
    }

    public static String getLoggedInMaTK() {
        return loggedInMaTK;
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DangNhap = new javax.swing.JPanel();
        Logo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        P_DangNhap = new javax.swing.JPanel();
        L_DangNhap = new javax.swing.JLabel();
        L_TaiKhoan = new javax.swing.JLabel();
        T_TaiKhoan = new javax.swing.JTextField();
        L_MatKhau = new javax.swing.JLabel();
        Password_f = new javax.swing.JPasswordField();
        Btn_dangNhap = new javax.swing.JButton();
        L_toDangKy = new javax.swing.JLabel();
        Btn_DangKy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");

        DangNhap.setBackground(new java.awt.Color(255, 255, 255));
        DangNhap.setPreferredSize(new java.awt.Dimension(800, 500));
        DangNhap.setLayout(null);

        Logo.setBackground(new java.awt.Color(0, 102, 102));
        Logo.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel6.setFont(new java.awt.Font("Segoe Script", 3, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("Thai Restaurant");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/6214510_elephant_evernote_logo_icon (1).png"))); // NOI18N

        javax.swing.GroupLayout LogoLayout = new javax.swing.GroupLayout(Logo);
        Logo.setLayout(LogoLayout);
        LogoLayout.setHorizontalGroup(
            LogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LogoLayout.createSequentialGroup()
                .addGroup(LogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LogoLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LogoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        LogoLayout.setVerticalGroup(
            LogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogoLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel6)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        DangNhap.add(Logo);
        Logo.setBounds(0, 0, 410, 500);

        P_DangNhap.setBackground(new java.awt.Color(255, 255, 255));
        P_DangNhap.setMinimumSize(new java.awt.Dimension(400, 500));

        L_DangNhap.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        L_DangNhap.setForeground(new java.awt.Color(0, 102, 102));
        L_DangNhap.setText("Đăng Nhập");

        L_TaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        L_TaiKhoan.setText("Tài Khoản");

        T_TaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T_TaiKhoanActionPerformed(evt);
            }
        });

        L_MatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        L_MatKhau.setText("Mật Khẩu");

        Btn_dangNhap.setBackground(new java.awt.Color(0, 102, 102));
        Btn_dangNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_dangNhap.setForeground(new java.awt.Color(255, 255, 255));
        Btn_dangNhap.setMnemonic('a');
        Btn_dangNhap.setText("Đăng Nhập");
        Btn_dangNhap.setToolTipText("Nhấn ALT+a để đăng nhập");
        Btn_dangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_dangNhapActionPerformed(evt);
            }
        });

        L_toDangKy.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        L_toDangKy.setText("Chưa có tài khoản ?");

        Btn_DangKy.setBackground(new java.awt.Color(255, 255, 0));
        Btn_DangKy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_DangKy.setMnemonic('d');
        Btn_DangKy.setText("Đăng Ký");
        Btn_DangKy.setToolTipText("Nhấn ALT+d để đăng ký");
        Btn_DangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_DangKyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout P_DangNhapLayout = new javax.swing.GroupLayout(P_DangNhap);
        P_DangNhap.setLayout(P_DangNhapLayout);
        P_DangNhapLayout.setHorizontalGroup(
            P_DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_DangNhapLayout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(L_DangNhap)
                .addGap(99, 99, 99))
            .addGroup(P_DangNhapLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(P_DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btn_dangNhap)
                    .addGroup(P_DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(T_TaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addComponent(Password_f)
                        .addComponent(L_TaiKhoan)
                        .addComponent(L_MatKhau))
                    .addGroup(P_DangNhapLayout.createSequentialGroup()
                        .addComponent(L_toDangKy)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_DangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        P_DangNhapLayout.setVerticalGroup(
            P_DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_DangNhapLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(L_DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(L_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(L_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password_f, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(P_DangNhapLayout.createSequentialGroup()
                        .addComponent(Btn_dangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))
                    .addGroup(P_DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Btn_DangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L_toDangKy)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        DangNhap.add(P_DangNhap);
        P_DangNhap.setBounds(410, 0, 390, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void T_TaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T_TaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T_TaiKhoanActionPerformed

    private void Btn_dangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_dangNhapActionPerformed
        String tenTK = T_TaiKhoan.getText();
        String matKhau = new String(Password_f.getPassword());
        if (!Pattern.matches("^[a-zA-Z0-9]{5,20}$", tenTK)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Tên tài khoản phải từ 5-20 ký tự và chỉ chứa chữ cái hoặc số.", "Lỗi định dạng", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$", matKhau)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Mật khẩu phải từ 8-20 ký tự, bao gồm ít nhất một chữ cái in hoa, một chữ cái thường và một số.", "Lỗi định dạng", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (taiKhoanDAO.login(tenTK, matKhau)) {
            // Nếu đăng nhập thành công, mở Menu
            taiKhoan = taiKhoanDAO.findTaiKhoanByTenTK(tenTK);
            nhanVien = nhanVienDAO.findNhanVienByTaiKhoan(taiKhoan);
            ManHinhChinh mn = new ManHinhChinh(nhanVien);
            mn.setVisible(true);
            this.dispose(); // Đóng cửa sổ đăng nhập
        } else {
            // Nếu đăng nhập thất bại, hiển thị thông báo lỗi
            javax.swing.JOptionPane.showMessageDialog(this, "Tên tài khoản hoặc mật khẩu không đúng!", "Lỗi đăng nhập", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Btn_dangNhapActionPerformed
    
    private void Btn_DangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DangKyActionPerformed
        SignUp SignUpFrame = new SignUp();
        SignUpFrame.setVisible(true);
        SignUpFrame.pack();
        SignUpFrame.setLocationRelativeTo(null);
        SignUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_Btn_DangKyActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_DangKy;
    private javax.swing.JButton Btn_dangNhap;
    private javax.swing.JPanel DangNhap;
    private javax.swing.JLabel L_DangNhap;
    private javax.swing.JLabel L_MatKhau;
    private javax.swing.JLabel L_TaiKhoan;
    private javax.swing.JLabel L_toDangKy;
    private javax.swing.JPanel Logo;
    private javax.swing.JPanel P_DangNhap;
    private javax.swing.JPasswordField Password_f;
    private javax.swing.JTextField T_TaiKhoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
