/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.ban;

import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import entity.Ban;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class LichSuDatBan extends javax.swing.JPanel implements UpdateListener {
    private HoaDon hoaDon;
    private HoaDon_DAO hoaDon_DAO;
    private NhanVien nhanVien;
    private NhanVien_DAO nhanVien_DAO;
    private KhachHang khachHang;
    private Ban ban;
    private List<HoaDon> dshd;
    
    /**
     * Creates new form LichSuDatBan
     */
    public LichSuDatBan() {
        try {
            initComponents();
            hoaDon_DAO = new HoaDon_DAO();
            nhanVien_DAO = new NhanVien_DAO();
            dshd = hoaDon_DAO.getAllHoaDon();
            loadDataToTable(dshd);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        ChucNang = new javax.swing.JPanel();
        TimKiem = new javax.swing.JPanel();
        btn_reset = new javax.swing.JButton();
        text_sdt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_TimSDT = new javax.swing.JButton();
        btn_chiTiet = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        text_maBan = new javax.swing.JTextField();
        btn_TimMaBan = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        text_TimMaNV = new javax.swing.JTextField();
        btn_TimMaNV = new javax.swing.JButton();
        Loc = new javax.swing.JPanel();
        r_choXuLy = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        r_daXuLy = new javax.swing.JRadioButton();
        r_daHuy = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        btn_homnay = new javax.swing.JButton();
        btn_thangNay = new javax.swing.JButton();
        combobox_ngayDat = new javax.swing.JComboBox<>();
        P_table = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_LichSuDatBan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        ChucNang.setBackground(new java.awt.Color(255, 255, 255));

        TimKiem.setBackground(new java.awt.Color(255, 255, 255));
        TimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        btn_reset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_reset.setText("Làm mới");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        text_sdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nhập số điện thoại khách hàng: ");

        btn_TimSDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TimSDT.setText("Tìm");
        btn_TimSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimSDTActionPerformed(evt);
            }
        });

        btn_chiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_chiTiet.setText("Chi tiết");
        btn_chiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chiTietActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Nhập mã bàn:");

        btn_TimMaBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TimMaBan.setText("Tìm");
        btn_TimMaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimMaBanActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Nhập mã nhân viên:");

        text_TimMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btn_TimMaNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TimMaNV.setText("Tìm");
        btn_TimMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimMaNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TimKiemLayout = new javax.swing.GroupLayout(TimKiem);
        TimKiem.setLayout(TimKiemLayout);
        TimKiemLayout.setHorizontalGroup(
            TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TimKiemLayout.createSequentialGroup()
                        .addGroup(TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(text_maBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(text_sdt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TimKiemLayout.createSequentialGroup()
                                .addComponent(btn_TimSDT)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(TimKiemLayout.createSequentialGroup()
                                .addComponent(btn_TimMaBan)
                                .addGap(52, 52, 52)
                                .addGroup(TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TimKiemLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(btn_chiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_reset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(TimKiemLayout.createSequentialGroup()
                        .addGroup(TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addGroup(TimKiemLayout.createSequentialGroup()
                                .addComponent(text_TimMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_TimMaNV)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        TimKiemLayout.setVerticalGroup(
            TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TimKiemLayout.createSequentialGroup()
                        .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_chiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TimKiemLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_TimSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_maBan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_TimMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_TimMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_TimMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        Loc.setBackground(new java.awt.Color(255, 255, 255));
        Loc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        r_choXuLy.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(r_choXuLy);
        r_choXuLy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        r_choXuLy.setText("Chờ xử lý");
        r_choXuLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_choXuLyActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Trạng thái:");

        r_daXuLy.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(r_daXuLy);
        r_daXuLy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        r_daXuLy.setText("Đã xử lý");
        r_daXuLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_daXuLyActionPerformed(evt);
            }
        });

        r_daHuy.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(r_daHuy);
        r_daHuy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        r_daHuy.setText("Đã hủy");
        r_daHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_daHuyActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Ngày đặt:");

        btn_homnay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_homnay.setText("Hôm nay");
        btn_homnay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_homnayActionPerformed(evt);
            }
        });

        btn_thangNay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_thangNay.setText("Tháng này");
        btn_thangNay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thangNayActionPerformed(evt);
            }
        });

        combobox_ngayDat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        combobox_ngayDat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Chọn --", "Cũ nhất", "Mới nhất" }));
        combobox_ngayDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox_ngayDatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LocLayout = new javax.swing.GroupLayout(Loc);
        Loc.setLayout(LocLayout);
        LocLayout.setHorizontalGroup(
            LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_thangNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addComponent(btn_homnay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(combobox_ngayDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LocLayout.createSequentialGroup()
                        .addGroup(LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(r_choXuLy)
                            .addComponent(r_daXuLy))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(LocLayout.createSequentialGroup()
                        .addComponent(r_daHuy)
                        .addGap(33, 194, Short.MAX_VALUE))))
        );
        LocLayout.setVerticalGroup(
            LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(r_choXuLy)
                    .addComponent(combobox_ngayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LocLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(r_daXuLy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(r_daHuy))
                    .addGroup(LocLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btn_homnay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_thangNay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ChucNangLayout = new javax.swing.GroupLayout(ChucNang);
        ChucNang.setLayout(ChucNangLayout);
        ChucNangLayout.setHorizontalGroup(
            ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Loc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ChucNangLayout.setVerticalGroup(
            ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Loc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ChucNangLayout.createSequentialGroup()
                        .addComponent(TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))))
        );

        P_table.setBackground(new java.awt.Color(255, 255, 255));

        table_LichSuDatBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Khách hàng", "Số điện thoại", "Mã hóa đơn", "Bàn", "Ngày đặt bàn", "Ngày nhận bàn", "Trạng thái", "Nhân viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_LichSuDatBan.setRowHeight(35);
        jScrollPane1.setViewportView(table_LichSuDatBan);
        if (table_LichSuDatBan.getColumnModel().getColumnCount() > 0) {
            table_LichSuDatBan.getColumnModel().getColumn(0).setMinWidth(160);
            table_LichSuDatBan.getColumnModel().getColumn(1).setMinWidth(140);
            table_LichSuDatBan.getColumnModel().getColumn(2).setMinWidth(200);
            table_LichSuDatBan.getColumnModel().getColumn(3).setMinWidth(80);
            table_LichSuDatBan.getColumnModel().getColumn(3).setMaxWidth(150);
            table_LichSuDatBan.getColumnModel().getColumn(4).setMinWidth(140);
            table_LichSuDatBan.getColumnModel().getColumn(5).setMinWidth(140);
            table_LichSuDatBan.getColumnModel().getColumn(6).setMinWidth(140);
            table_LichSuDatBan.getColumnModel().getColumn(7).setMinWidth(160);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Lịch sử đặt bàn");

        javax.swing.GroupLayout P_tableLayout = new javax.swing.GroupLayout(P_table);
        P_table.setLayout(P_tableLayout);
        P_tableLayout.setHorizontalGroup(
            P_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P_tableLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_tableLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        P_tableLayout.setVerticalGroup(
            P_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(P_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public void loadDataToTable(List<HoaDon> dshd){
        table_LichSuDatBan.setFont(new Font("Arial", Font.PLAIN, 16));
        table_LichSuDatBan.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table_LichSuDatBan.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table_LichSuDatBan.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table_LichSuDatBan.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table_LichSuDatBan.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        table_LichSuDatBan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table_LichSuDatBan.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table_LichSuDatBan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_LichSuDatBan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table_LichSuDatBan.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
            
        DefaultTableModel model = (DefaultTableModel) table_LichSuDatBan.getModel();
        model.setRowCount(0);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        
        for (HoaDon hoaDon1 : dshd) {
            model.addRow(new Object[] {
                hoaDon1.getYeucau().getKh().getTenKH(), 
                hoaDon1.getYeucau().getKh().getSDT(), 
                hoaDon1.getMaHD(), 
                hoaDon1.getBan().getMaBan(), 
                dtf.format(hoaDon1.getThoiGianTao()), 
                dtf.format(hoaDon1.getNgayDatBan()),
                hoaDon1.getTrangThaiHoaDon(),
                hoaDon1.getNhanVien().getTenNV()
            });
        }
    }
    
    @Override
    public void updatetable(){
        try {
            // TODO add your handling code here:
            combobox_ngayDat.setSelectedIndex(0);
            
            text_sdt.setText("");
            text_maBan.setText("");
            text_TimMaNV.setText("");
            
            r_choXuLy.setSelected(false);
            r_daHuy.setSelected(false);
            r_daXuLy.setSelected(false);
            
            dshd = hoaDon_DAO.getAllHoaDon();
            loadDataToTable(dshd);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        try {
            // TODO add your handling code here:
            combobox_ngayDat.setSelectedIndex(0);
            
            text_sdt.setText("");
            text_maBan.setText("");
            text_TimMaNV.setText("");
            
            r_choXuLy.setSelected(false);
            r_daHuy.setSelected(false);
            r_daXuLy.setSelected(false);
            
            dshd = hoaDon_DAO.getAllHoaDon();
            loadDataToTable(dshd);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_TimSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimSDTActionPerformed
        // TODO add your handling code here:
        String sdtTimKiem = text_sdt.getText().trim();

        if (sdtTimKiem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại!");
            return;  // Nếu không có số điện thoại nhập vào thì không làm gì
        }

        try {
            // Gọi phương thức tìm kiếm hóa đơn theo số điện thoại của khách hàng
            List<entity.HoaDon> dsHoaDon = hoaDon_DAO.timHoaDonTheoSdt(sdtTimKiem);
            loadDataToTable(dsHoaDon);

        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm hóa đơn: " + ex.getMessage());
        }
    }//GEN-LAST:event_btn_TimSDTActionPerformed

    private void btn_TimMaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimMaBanActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        String maBanTK = text_maBan.getText().trim();

        if (maBanTK.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã bàn!");
            return;  // Nếu không có số điện thoại nhập vào thì không làm gì
        }

        try {
            // Gọi phương thức tìm kiếm hóa đơn theo số điện thoại của khách hàng
            List<entity.HoaDon> dsHoaDon = hoaDon_DAO.timHoaDonTheoMaBan(maBanTK);
            loadDataToTable(dsHoaDon);

        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm hóa đơn: " + ex.getMessage());
        }
    }//GEN-LAST:event_btn_TimMaBanActionPerformed

    private void btn_chiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chiTietActionPerformed
        int selectedRow = table_LichSuDatBan.getSelectedRow();
        if (selectedRow != -1) { 
            String maHD = (String) table_LichSuDatBan.getValueAt(selectedRow, 2);
            
            hoaDon = hoaDon_DAO.getHoaDonByMaHD(maHD);
            Frame_ChiTietLichSu chitiet = new Frame_ChiTietLichSu(hoaDon, this);
            chitiet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            chitiet.setVisible(true);
            chitiet.setLocationRelativeTo(null);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đơn đặt từ bảng!");
        }
    }//GEN-LAST:event_btn_chiTietActionPerformed

    private void btn_TimMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimMaNVActionPerformed
        try {
            String maNV = text_TimMaNV.getText().trim();
            if (maNV.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên!");
                return;  // Nếu không có số điện thoại nhập vào thì không làm gì
            }
            nhanVien = nhanVien_DAO.getNhanVienByMa(maNV);
            
            List<HoaDon> dsHoaDon = hoaDon_DAO.getHoaDonTheoNhanVien(nhanVien);
            if (dsHoaDon.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có hóa đơn nào được lập bởi nhân viên này!");
            }
            
            loadDataToTable(dsHoaDon);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btn_TimMaNVActionPerformed

    private void combobox_ngayDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox_ngayDatActionPerformed
        // TODO add your handling code here:
        try {
            String luaChon = combobox_ngayDat.getSelectedItem().toString();
            
            List<HoaDon> dsHoaDon = hoaDon_DAO.getAllHoaDon();
            
            if ("Cũ nhất".equals(luaChon)) {
                dsHoaDon.sort(Comparator.comparing(HoaDon::getThoiGianTao, Comparator.nullsLast(Comparator.naturalOrder())));
            } else if ("Mới nhất".equals(luaChon)) {
                dsHoaDon.sort(Comparator.comparing(HoaDon::getThoiGianTao, Comparator.nullsLast(Comparator.naturalOrder())).reversed());
            }
            
            loadDataToTable(dsHoaDon);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_combobox_ngayDatActionPerformed

    private void btn_homnayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_homnayActionPerformed
        try {
            // TODO add your handling code here:
            LocalDate today = LocalDate.now();
            
            // Lấy danh sách hóa đơn hôm nay từ DAO
            List<HoaDon> dsHoaDonHomNay = hoaDon_DAO.getHoaDonHomNay(today);
            if (dsHoaDonHomNay.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có hóa đơn nào được tạo hôm nay!");
            }
            
            loadDataToTable(dsHoaDonHomNay);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_homnayActionPerformed

    private void r_choXuLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_choXuLyActionPerformed
        // TODO add your handling code here:
        try {
            List<HoaDon> dsHoaDonDaHuy = hoaDon_DAO.getHoaDonTheoTrangThai("Chờ xử lý");
            if (dsHoaDonDaHuy.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có hóa đơn nào có trạng thái 'Chờ xử lý'!");
            }
            loadDataToTable(dsHoaDonDaHuy);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_r_choXuLyActionPerformed

    private void r_daXuLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_daXuLyActionPerformed
        // TODO add your handling code here:
        try {
            List<HoaDon> dsHoaDonDaHuy = hoaDon_DAO.getHoaDonTheoTrangThai("Đã xử lý");
            if (dsHoaDonDaHuy.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có hóa đơn nào có trạng thái 'Đã xử lý'!");
            }
            loadDataToTable(dsHoaDonDaHuy);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_r_daXuLyActionPerformed

    private void r_daHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_daHuyActionPerformed
        try {
            List<HoaDon> dsHoaDonDaHuy = hoaDon_DAO.getHoaDonTheoTrangThai("Đã hủy");
            if (dsHoaDonDaHuy.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có hóa đơn nào có trạng thái 'Đã hủy'!");
            }
            loadDataToTable(dsHoaDonDaHuy);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_r_daHuyActionPerformed

    private void btn_thangNayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thangNayActionPerformed
        // TODO add your handling code here:
        try {
            List<HoaDon> dsHoaDonThangNay = hoaDon_DAO.getHoaDonTrongThangHienTai();
            if (dsHoaDonThangNay.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có hóa đơn nào được tạo hôm nay!");
            }
            
            loadDataToTable(dsHoaDonThangNay);
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_thangNayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChucNang;
    private javax.swing.JPanel Loc;
    private javax.swing.JPanel P_table;
    private javax.swing.JPanel TimKiem;
    private javax.swing.JButton btn_TimMaBan;
    private javax.swing.JButton btn_TimMaNV;
    private javax.swing.JButton btn_TimSDT;
    private javax.swing.JButton btn_chiTiet;
    private javax.swing.JButton btn_homnay;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_thangNay;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> combobox_ngayDat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton r_choXuLy;
    private javax.swing.JRadioButton r_daHuy;
    private javax.swing.JRadioButton r_daXuLy;
    private javax.swing.JTable table_LichSuDatBan;
    private javax.swing.JTextField text_TimMaNV;
    private javax.swing.JTextField text_maBan;
    private javax.swing.JTextField text_sdt;
    // End of variables declaration//GEN-END:variables
}