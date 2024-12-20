/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.card;

import dao.MonAn_DAO;
import entity.MonAn;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Admin
 */
public class Card_Mon extends javax.swing.JPanel {
    private MonAn monAn;
    private MonAn_DAO monDAO;
    private CardMonListener listener;

    /**
     * Creates new form Card_Mon
     */
    public Card_Mon() {
        initComponents();
        setupSpinner();
    }
    
    public Card_Mon(MonAn monAn) {
        initComponents();
        this.monAn = monAn; // Gán món ăn
        setDataMon(monAn);
        setupSpinner();
    }
    
    public void setDataMon(MonAn monAn){
        DecimalFormat df = new DecimalFormat("#,### VND");
        this.monAn = monAn;
        lbTenMon.setText("Tên món: " + monAn.getTenMonAn());
        lb_Gia.setText("Giá: " + df.format(monAn.getGiaTien()));

        // Xử lý ảnh cho món ăn
        String maMonAn = monAn.getMaMonAn().trim(); // Lấy mã món ăn
        String fileName = "icon/monAn/mon_" + maMonAn.substring(1) + ".png"; // Tạo tên tệp ảnh

        // Lấy đường dẫn ảnh từ tài nguyên
        URL imageUrl = getClass().getClassLoader().getResource(fileName);

        if (imageUrl != null) {
            // Nếu ảnh tồn tại, gán ảnh vào JLabel
            ImageIcon icon = new ImageIcon(imageUrl);
            img.setIcon(icon);
        } else {
            // Nếu không tìm thấy ảnh, gán ảnh mặc định
            ImageIcon defaultIcon = new ImageIcon(getClass().getClassLoader().getResource("icon/monAn/no_image_icon.png"));
            img.setIcon(defaultIcon);
        }
    }
    
    private void setupSpinner() {
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        spinner_soluong.setModel(model); // Gán model cho JSpinner
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card_Mon = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        btn_ThemMon = new javax.swing.JButton();
        lb_Gia = new javax.swing.JLabel();
        lbTenMon = new javax.swing.JLabel();
        spinner_soluong = new javax.swing.JSpinner();
        lb_sl = new javax.swing.JLabel();

        card_Mon.setBackground(new java.awt.Color(255, 255, 255));
        card_Mon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        img.setBackground(new java.awt.Color(255, 255, 255));
        img.setOpaque(true);

        btn_ThemMon.setBackground(new java.awt.Color(0, 153, 153));
        btn_ThemMon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_ThemMon.setForeground(new java.awt.Color(255, 255, 255));
        btn_ThemMon.setText("Thêm");
        btn_ThemMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemMonActionPerformed(evt);
            }
        });

        lb_Gia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_Gia.setForeground(new java.awt.Color(51, 153, 0));
        lb_Gia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Gia.setText("Value");

        lbTenMon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbTenMon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTenMon.setText("Title");

        lb_sl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_sl.setText("Số Lượng:");

        javax.swing.GroupLayout card_MonLayout = new javax.swing.GroupLayout(card_Mon);
        card_Mon.setLayout(card_MonLayout);
        card_MonLayout.setHorizontalGroup(
            card_MonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card_MonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(card_MonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Gia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, card_MonLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lb_sl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinner_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addGap(12, 12, 12)))
                .addContainerGap())
            .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_ThemMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        card_MonLayout.setVerticalGroup(
            card_MonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card_MonLayout.createSequentialGroup()
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(card_MonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinner_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_sl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ThemMon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(card_Mon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(card_Mon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public interface CardMonListener {
        void onAddMon(MonAn monAn, int soLuong); // Phương thức sẽ được gọi khi nhấn nút "Thêm"
    }

    // Constructor và các phương thức khác...

    public void setCardMonListener(CardMonListener listener) {
        this.listener = listener; // Thiết lập listener
    }
    
    public MonAn getMonAn() {
        return this.monAn;
    }
    
    private void btn_ThemMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemMonActionPerformed
        if (listener != null) { // Kiểm tra nếu listener đã được thiết lập
            int soLuong = (Integer) spinner_soluong.getValue(); // Lấy số lượng từ spinner
            listener.onAddMon(monAn, soLuong); // Gọi phương thức listener
        }
    }//GEN-LAST:event_btn_ThemMonActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ThemMon;
    private javax.swing.JPanel card_Mon;
    private javax.swing.JLabel img;
    private javax.swing.JLabel lbTenMon;
    private javax.swing.JLabel lb_Gia;
    private javax.swing.JLabel lb_sl;
    private javax.swing.JSpinner spinner_soluong;
    // End of variables declaration//GEN-END:variables
}
