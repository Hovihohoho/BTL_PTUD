/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.datBan;

import gui.card.Card_Mon;
import dao.MonAn_DAO;
import entity.Ban;
import entity.MonAn;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Frame_GoiMon extends javax.swing.JFrame {
    private MonAn_DAO monDAO;
    private DefaultTableModel tableModel;
    private int stt=1;
    private double tongTien;
    private double thue;
    private GoiMonListener listener;
    /**
     * Creates new form Frame_GoiMon
     */
    public Frame_GoiMon(GoiMonListener listener, Ban ban) {
        initComponents();
        monDAO = new MonAn_DAO();
        setSize(1200, 800);
        setLocationRelativeTo(null); // Đặt JFrame ở giữa màn hình
        
        lb_ban.setText(ban.getMaBan());
        Card_container.setLayout(new java.awt.GridLayout(0, 3, 10, 10));
        // set font va co chu cho table
        table_monAn.setFont(new Font("Arial", Font.PLAIN, 16));
        table_monAn.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table_monAn.setRowHeight(30);
        // can giua cho 2 cot stt va sl
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table_monAn.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_monAn.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        this.listener = listener;
        tableModel = (DefaultTableModel) table_monAn.getModel();
        
        hienThiDanhSachMon();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        P_ChonMon = new javax.swing.JPanel();
        P_loc = new javax.swing.JPanel();
        combo_loaiMon = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        combo_trangthai = new javax.swing.JComboBox<>();
        P_btn = new javax.swing.JPanel();
        them = new javax.swing.JButton();
        Huy = new javax.swing.JButton();
        Sua = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Scroll_dsMonAn = new javax.swing.JScrollPane();
        Card_container = new javax.swing.JPanel();
        ds_MA = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lb_ban = new javax.swing.JLabel();
        P_TongTien = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lb_thue = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lb_TongTien = new javax.swing.JLabel();
        Scroll_table = new javax.swing.JScrollPane();
        table_monAn = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        P_ChonMon.setBackground(new java.awt.Color(255, 255, 255));

        P_loc.setBackground(new java.awt.Color(255, 255, 255));

        combo_loaiMon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn--", "Đồ uống", "Món khô", "Món nước" }));
        combo_loaiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_loaiMonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Loại món:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Trạng thái");

        combo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn--", "Có sẵn", "Không có sẵn" }));
        combo_trangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_trangthaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout P_locLayout = new javax.swing.GroupLayout(P_loc);
        P_loc.setLayout(P_locLayout);
        P_locLayout.setHorizontalGroup(
            P_locLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_locLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_loaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        P_locLayout.setVerticalGroup(
            P_locLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_locLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(P_locLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_loaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        P_btn.setBackground(new java.awt.Color(255, 255, 255));

        them.setBackground(new java.awt.Color(255, 51, 51));
        them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus (1).png"))); // NOI18N
        them.setText("Thêm");
        them.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themActionPerformed(evt);
            }
        });

        Huy.setBackground(new java.awt.Color(255, 51, 51));
        Huy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Huy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cancel (3) (1).png"))); // NOI18N
        Huy.setText("Hủy");
        Huy.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HuyActionPerformed(evt);
            }
        });

        Sua.setBackground(new java.awt.Color(255, 51, 51));
        Sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tools (1).png"))); // NOI18N
        Sua.setText("Sửa số lượng");
        Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete (1).png"))); // NOI18N
        jButton1.setText("Xóa món");

        javax.swing.GroupLayout P_btnLayout = new javax.swing.GroupLayout(P_btn);
        P_btn.setLayout(P_btnLayout);
        P_btnLayout.setHorizontalGroup(
            P_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_btnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(them, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Huy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        P_btnLayout.setVerticalGroup(
            P_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_btnLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(P_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Huy, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        Scroll_dsMonAn.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Card_container.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout Card_containerLayout = new javax.swing.GroupLayout(Card_container);
        Card_container.setLayout(Card_containerLayout);
        Card_containerLayout.setHorizontalGroup(
            Card_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
        );
        Card_containerLayout.setVerticalGroup(
            Card_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        Scroll_dsMonAn.setViewportView(Card_container);

        javax.swing.GroupLayout P_ChonMonLayout = new javax.swing.GroupLayout(P_ChonMon);
        P_ChonMon.setLayout(P_ChonMonLayout);
        P_ChonMonLayout.setHorizontalGroup(
            P_ChonMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_ChonMonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_ChonMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(P_loc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(P_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Scroll_dsMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        P_ChonMonLayout.setVerticalGroup(
            P_ChonMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_ChonMonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(P_loc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Scroll_dsMonAn, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ds_MA.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Bàn:");

        lb_ban.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_ban.setForeground(new java.awt.Color(51, 153, 0));
        lb_ban.setText("DEMO");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_ban)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_ban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        P_TongTien.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Thuế:");

        lb_thue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_thue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tổng tiền:");

        lb_TongTien.setBackground(new java.awt.Color(255, 255, 255));
        lb_TongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_TongTien.setForeground(new java.awt.Color(255, 51, 51));
        lb_TongTien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout P_TongTienLayout = new javax.swing.GroupLayout(P_TongTien);
        P_TongTien.setLayout(P_TongTienLayout);
        P_TongTienLayout.setHorizontalGroup(
            P_TongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_TongTienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_thue, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        P_TongTienLayout.setVerticalGroup(
            P_TongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_TongTienLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(P_TongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_thue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        table_monAn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        table_monAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên món ăn", "S.L", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_monAn.setRowHeight(35);
        Scroll_table.setViewportView(table_monAn);
        if (table_monAn.getColumnModel().getColumnCount() > 0) {
            table_monAn.getColumnModel().getColumn(0).setMinWidth(35);
            table_monAn.getColumnModel().getColumn(0).setMaxWidth(35);
            table_monAn.getColumnModel().getColumn(2).setMaxWidth(60);
            table_monAn.getColumnModel().getColumn(3).setMaxWidth(300);
        }

        javax.swing.GroupLayout ds_MALayout = new javax.swing.GroupLayout(ds_MA);
        ds_MA.setLayout(ds_MALayout);
        ds_MALayout.setHorizontalGroup(
            ds_MALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ds_MALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ds_MALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(P_TongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Scroll_table, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
                .addContainerGap())
        );
        ds_MALayout.setVerticalGroup(
            ds_MALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ds_MALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Scroll_table, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(P_ChonMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ds_MA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(P_ChonMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ds_MA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
//        Map<String, Object[]> monAnMap = new HashMap<>();
//        for (int i = 0; i < tableModel.getRowCount(); i++) {
//            String tenMon = (String) tableModel.getValueAt(i, 1);  // Tên món ăn
//            int soLuong = (Integer) tableModel.getValueAt(i, 2);    // Số lượng
//            double donGia = (Double) tableModel.getValueAt(i, 3);   // Đơn giá
//
//            if (monAnMap.containsKey(tenMon)) {
//                Object[] existingRow = monAnMap.get(tenMon);
//                existingRow[2] = (int) existingRow[2] + soLuong;    // Cộng dồn số lượng
//                existingRow[4] = (double) existingRow[4] + (donGia * soLuong); // Cộng dồn thành tiền
//            } else {
//                Object[] rowData = {
//                    i+1,  // STT
//                    tenMon,
//                    soLuong,
//                    donGia,
//                    donGia * soLuong
//                };
//                monAnMap.put(tenMon, rowData);
//            }
//        }
//
//        // Sau đó chuyển từ Map thành List
//        List<Object[]> dsMonAn = new ArrayList<>(monAnMap.values());
//
//        // Gọi callback để chuyển dữ liệu sang DatBan
//        if (listener != null) {
//            listener.onGoiMonCompleted(dsMonAn);
//        }
//        this.dispose();
        
        //cach cu
            List<Object[]> dsMonAn = new ArrayList<>();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Object[] rowData = {
                    tableModel.getValueAt(i, 0),  // STT
                    tableModel.getValueAt(i, 1),  // Tên món
                    tableModel.getValueAt(i, 2),  // Số lượng
                    tableModel.getValueAt(i, 3),  // Đơn giá
                    tableModel.getValueAt(i, 4)   // Thành tiền
                };
                dsMonAn.add(rowData);
            }

            // Gọi callback để chuyển dữ liệu sang DatBan
            if (listener != null) {
                listener.onGoiMonCompleted(dsMonAn, thue, tongTien);
            }

            // Đóng Frame_GoiMon sau khi hoàn tất
            this.dispose();
    }//GEN-LAST:event_themActionPerformed
    
    public interface GoiMonListener {
        void onGoiMonCompleted(List<Object[]> dsMonAn, double thue, double tongTien); // Truyền danh sách món ăn đã chọn
    }
    
    private void HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_HuyActionPerformed

    private void SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaActionPerformed
        // TODO add your handling code here:
        // Lấy dòng được chọn
        int selectedRow = table_monAn.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món cần sửa số lượng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy số lượng mới từ người dùng
        String newQuantityStr = JOptionPane.showInputDialog(this, "Nhập số lượng mới:");
        if (newQuantityStr == null) {
            return; // Người dùng nhấn Cancel
        }

        int newQuantity;
        try {
            newQuantity = Integer.parseInt(newQuantityStr);
            if (newQuantity <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cập nhật số lượng và thành tiền của món trong bảng
        double donGia = (double) tableModel.getValueAt(selectedRow, 3);
        double thanhTienMoi = donGia * newQuantity;

        // Cập nhật số lượng mới và thành tiền vào bảng
        tableModel.setValueAt(newQuantity, selectedRow, 2); // Cột số lượng
        tableModel.setValueAt(thanhTienMoi, selectedRow, 4); // Cột thành tiền

        // Tính lại tổng tiền và thuế
        capNhatTongTienSauKhiSua();
    }//GEN-LAST:event_SuaActionPerformed

    private void combo_loaiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_loaiMonActionPerformed
        // TODO add your handling code here:
        String loaiMon = (String) combo_loaiMon.getSelectedItem();
        hienThiDanhSachMon(loaiMon);
    }//GEN-LAST:event_combo_loaiMonActionPerformed
    
    private void hienThiDanhSachMon(String loaiMon){
        MonAn_DAO monAnDAO = new MonAn_DAO();
        List<Card_Mon> cardList = monAnDAO.createCardLoaiMon(loaiMon);

        Card_container.removeAll(); // Xóa tất cả các card cũ

        for (Card_Mon card : cardList) {
            // Thiết lập listener cho từng Card_Mon
            card.setCardMonListener(new Card_Mon.CardMonListener() {
                @Override
                public void onAddMon(MonAn monAn, int soLuong) {
                    // Thêm món ăn vào table_monAn
                    addMonToTable(monAn, soLuong);
                }
            });
            Card_container.add(card);
        }

        Card_container.revalidate();
        Card_container.repaint();
    }
    
    private void combo_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_trangthaiActionPerformed
        // TODO add your handling code here:
        String trangthai = (String) combo_trangthai.getSelectedItem();
        hienThiDanhSachMontheoTrangThai(trangthai);
    }//GEN-LAST:event_combo_trangthaiActionPerformed
    
    private void hienThiDanhSachMontheoTrangThai(String trangThai){
        MonAn_DAO monAnDAO = new MonAn_DAO();
        List<Card_Mon> cardList = monAnDAO.createCardLoaiMontheoTrangThai(trangThai);

        Card_container.removeAll(); // Xóa tất cả các card cũ

        for (Card_Mon card : cardList) {
            // Thiết lập listener cho từng Card_Mon
            card.setCardMonListener(new Card_Mon.CardMonListener() {
                @Override
                public void onAddMon(MonAn monAn, int soLuong) {
                    // Thêm món ăn vào table_monAn
                    addMonToTable(monAn, soLuong);
                }
            });
            Card_container.add(card);
        }

        Card_container.revalidate();
        Card_container.repaint();
    }
    
    private void capNhatTongTienSauKhiSua() {
        DecimalFormat df = new DecimalFormat("#,###");

        // Đặt lại thuế và tổng tiền về 0
        tongTien = 0;
        thue = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int soLuong = (int) tableModel.getValueAt(i, 2);
            double donGia = (double) tableModel.getValueAt(i, 3);
            double thanhTien = soLuong * donGia;

            // Tính thuế cho từng món
            double thueMon = thanhTien * 0.1;
            thue += thueMon;
            tongTien += thanhTien + thueMon;
        }

        // Cập nhật tổng tiền và thuế vào các nhãn
        lb_thue.setText(df.format(thue));
        lb_TongTien.setText(df.format(tongTien));
    }
    
    public void addMonToTable(MonAn monAn, int soLuong) {
        boolean monAnTonTai = false;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String tenMonAnTrongTable = (String) tableModel.getValueAt(i, 1); // Cột 1: Tên món ăn
            if (tenMonAnTrongTable.equals(monAn.getTenMonAn())) {
                // Nếu món ăn đã tồn tại trong bảng
                int soLuongHienTai = (int) tableModel.getValueAt(i, 2); // Cột 2: Số lượng
                int soLuongMoi = soLuongHienTai + soLuong; // Cộng thêm số lượng mới

                double donGia = monAn.getGiaTien();
                double tongTienMoi = donGia * soLuongMoi; // Tính tổng tiền mới cho món ăn này

                // Cập nhật lại số lượng và tổng tiền trong bảng
                tableModel.setValueAt(soLuongMoi, i, 2); // Cột 2: Số lượng
                tableModel.setValueAt(tongTienMoi, i, 4); // Cột 4: Tổng tiền

                // Cập nhật tổng tiền chung
                capNhatTongTien(monAn, soLuong);
                monAnTonTai = true;
                break;
            }
        }

        // Nếu món ăn chưa có trong bảng, thêm dòng mới
        if (!monAnTonTai) {
            double donGia = monAn.getGiaTien();
            tableModel.addRow(new Object[]{
                stt++, 
                monAn.getTenMonAn(), 
                soLuong, 
                donGia, 
                donGia * soLuong
            });
            capNhatTongTien(monAn, soLuong);
        }
    }
    
    public void capNhatTongTien(MonAn monAn, int soLuong){
        DecimalFormat df = new DecimalFormat("#,###");
        double donGia = monAn.getGiaTien();
        
        double thueMonht = donGia*soLuong*0.1;
        thue += thueMonht;
        tongTien += donGia*soLuong + thueMonht;
        
        lb_thue.setText(df.format(thue));
        lb_TongTien.setText(df.format(tongTien));
    }
    
    private void hienThiDanhSachMon(){
        MonAn_DAO monAnDAO = new MonAn_DAO();
        List<Card_Mon> cardList = monAnDAO.createCardMonList();

        Card_container.removeAll(); // Xóa tất cả các card cũ

        for (Card_Mon card : cardList) {
            // Thiết lập listener cho từng Card_Mon
            card.setCardMonListener(new Card_Mon.CardMonListener() {
                @Override
                public void onAddMon(MonAn monAn, int soLuong) {
                    // Thêm món ăn vào table_monAn
                    addMonToTable(monAn, soLuong);
                }
            });
            Card_container.add(card);
        }

        Card_container.revalidate();
        Card_container.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Card_container;
    private javax.swing.JButton Huy;
    private javax.swing.JPanel P_ChonMon;
    private javax.swing.JPanel P_TongTien;
    private javax.swing.JPanel P_btn;
    private javax.swing.JPanel P_loc;
    private javax.swing.JScrollPane Scroll_dsMonAn;
    private javax.swing.JScrollPane Scroll_table;
    private javax.swing.JButton Sua;
    private javax.swing.JComboBox<String> combo_loaiMon;
    private javax.swing.JComboBox<String> combo_trangthai;
    private javax.swing.JPanel ds_MA;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lb_TongTien;
    private javax.swing.JLabel lb_ban;
    private javax.swing.JLabel lb_thue;
    private javax.swing.JTable table_monAn;
    private javax.swing.JButton them;
    // End of variables declaration//GEN-END:variables
}
