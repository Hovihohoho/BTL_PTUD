/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.datBan;

import com.toedter.calendar.JDateChooser;
import dao.Ban_DAO;
import dao.ChiTietYeuCau_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.MonAn_DAO;
import dao.NhanVien_DAO;
import dao.YeuCauKhachHang_DAO;
import entity.Ban;
import entity.HoaDon;
import entity.KhachHang;
import entity.MonAn;
import entity.NhanVien;
import entity.YeuCauKhachHang;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Frame_LuuHD extends javax.swing.JFrame {
    
    private KhachHang khachhang;
    private NhanVien nhanvien; 
    private Ban ban;
    private YeuCauKhachHang yeucau;
    
    private JDateChooser ngayNhanBan;
    private JSpinner timeSpinner;
    
    private DefaultTableModel tableModel;
    private List<Object[]> dsMonAn;
    
    private MonAn_DAO monDAO;
    private YeuCauKhachHang_DAO yeucauDAO;
    private ChiTietYeuCau_DAO chiTietYeuCauDAO;
    private KhachHang_DAO khachhang_dao;
    private HoaDon_DAO hoaDonDAO;
    private NhanVien_DAO nhanVienDAO;
    private Ban_DAO banDAO;
    
    DecimalFormat df = new DecimalFormat("#,### VNĐ");
    private double tongTien;
    /**
     * Creates new form Frame_LuuHD
     */
    public Frame_LuuHD() {
        initComponents();
    }
    
    public Frame_LuuHD(Ban ban, String gio, String ngay, List<Object[]> dsMonAn, NhanVien nhanvien, double tongTien) throws SQLException {
        
        banDAO = new Ban_DAO();
        nhanVienDAO = new NhanVien_DAO();
        chiTietYeuCauDAO = new ChiTietYeuCau_DAO();
        khachhang_dao = new KhachHang_DAO();
        khachhang = khachhang_dao.getKhachHangByMa("KH000");
        this.nhanvien = nhanvien;
        monDAO = new MonAn_DAO();
        this.dsMonAn = dsMonAn;
        this.ban = ban;
        this.tongTien = tongTien;
        initYeuCau();
        
        initComponents();
        setLocationRelativeTo(null);
        initDateTimeChooser();
        loadDataToTable();
        
        lb_loaiBan.setText(ban.getLoaiBan().getTenLoai());
        lb_viTri.setText(ban.getViTri());
        lb_Trangthai.setText(ban.getTrangThaiBan());
        String sghe = "" + ban.getSoGhe();
        lb_soGhe.setText(sghe);
        lb_ngayDat.setText(ngay);
        lb_gio.setText(gio);
        
        tongTienMon.setText(df.format(this.tongTien));
        tongTienMon.setHorizontalAlignment(SwingConstants.RIGHT);
        tienThue.setText(df.format(this.tongTien*0.1));
        tienThue.setHorizontalAlignment(SwingConstants.RIGHT);
        tongThanhToan.setText(df.format(this.tongTien + this.tongTien*0.1));
        tongThanhToan.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void initDateTimeChooser() {
        ngayNhanBan = new JDateChooser();
        ngayNhanBan.setDateFormatString("dd/MM/yyyy");
        ngayNhanBan.setBounds(0, 0, 150, 30);

        // Thiết lập ngày tối thiểu là hôm nay
        Date today = new Date();
        ngayNhanBan.setMinSelectableDate(today);

        // Thêm JDateChooser vào Panel
        P_ngayNhan.add(ngayNhanBan);

        // Tạo JSpinner để chọn giờ
        SpinnerDateModel timeModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(timeModel);
        timeSpinner.setBounds(160, 0, 80, 30); // Đặt vị trí và kích thước
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);

        // Thiết lập giá trị mặc định là giờ hiện tại
        timeSpinner.setValue(new Date());

        // Thêm JSpinner vào Panel
        P_ngayNhan.add(timeSpinner);
    }
    
    private void loadDataToTable() {
        tableMonAn.setFont(new Font("Arial", Font.PLAIN, 16));
        tableMonAn.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tableMonAn.setRowHeight(30);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        tableMonAn.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableMonAn.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableMonAn.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableMonAn.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tableModel = (DefaultTableModel) tableMonAn.getModel();
        tableModel.setRowCount(0); 
        
        DecimalFormat df0 = new DecimalFormat("#,###");
        
        for (Object[] row : dsMonAn) {
            if (row[3] instanceof Double) {
                row[3] = df0.format((Double) row[3]);   // dinh dang lai cot thu 3 (don gia)
            }
            if (row[4] instanceof Double) {
                row[4] = df0.format((Double) row[4]);
            }
            tableModel.addRow(row);
        }
    }
    
    public void initYeuCau() {
        try {
            yeucauDAO = new YeuCauKhachHang_DAO();
            String maYeuCau = yeucauDAO.generateNextMaYeuCau();  // Tạo mã yêu cầu mới
            yeucau = new YeuCauKhachHang(maYeuCau, khachhang);  // Tạo đối tượng YeuCauKhachHang
        } catch (SQLException e) {
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_tenKH = new javax.swing.JLabel();
        t_soDienthoai = new javax.swing.JTextField();
        btn_timSDT = new javax.swing.JButton();
        lb_ngayDat = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lb_loaiBan = new javax.swing.JLabel();
        lb_viTri = new javax.swing.JLabel();
        lb_Trangthai = new javax.swing.JLabel();
        lb_soGhe = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_GhiChu = new javax.swing.JTextArea();
        lb_gio = new javax.swing.JLabel();
        P_ngayNhan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        soLuongKhach_Spinner = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JSeparator();
        LuuVaXuatHD = new javax.swing.JButton();
        LuuHD = new javax.swing.JButton();
        Huy = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        P_ChucNang = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMonAn = new javax.swing.JTable();
        lb_dsma = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tongTienMon = new javax.swing.JLabel();
        tienThue = new javax.swing.JLabel();
        tongThanhToan = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tên khách hàng:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Số điện thoại:");

        lb_tenKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        t_soDienthoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        t_soDienthoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_soDienthoaiActionPerformed(evt);
            }
        });

        btn_timSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_timSDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search (1).jpg"))); // NOI18N
        btn_timSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timSDTActionPerformed(evt);
            }
        });

        lb_ngayDat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Bàn"));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Loại Bàn");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Vị trí");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Trạng thái");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Số ghế");

        lb_soGhe.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_viTri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_loaiBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_Trangthai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_soGhe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_Trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_loaiBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_viTri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_soGhe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Ghi chú");

        ta_GhiChu.setColumns(20);
        ta_GhiChu.setRows(5);
        jScrollPane1.setViewportView(ta_GhiChu);

        P_ngayNhan.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout P_ngayNhanLayout = new javax.swing.GroupLayout(P_ngayNhan);
        P_ngayNhan.setLayout(P_ngayNhanLayout);
        P_ngayNhanLayout.setHorizontalGroup(
            P_ngayNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        P_ngayNhanLayout.setVerticalGroup(
            P_ngayNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ngày hẹn:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày đặt:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Số lượng khách:");

        LuuVaXuatHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LuuVaXuatHD.setText("Lưu và xuất HD");
        LuuVaXuatHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LuuVaXuatHDActionPerformed(evt);
            }
        });

        LuuHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LuuHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/details (1).jpg"))); // NOI18N
        LuuHD.setText("Lưu HD");
        LuuHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LuuHDActionPerformed(evt);
            }
        });

        Huy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Huy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete (1).jpg"))); // NOI18N
        Huy.setText("Hủy");
        Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(lb_ngayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lb_gio, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1)))
                        .addContainerGap(9, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lb_tenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t_soDienthoai, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                                .addGap(15, 15, 15)
                                .addComponent(btn_timSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(P_ngayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(soLuongKhach_Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jSeparator2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LuuVaXuatHD)
                .addGap(18, 18, 18)
                .addComponent(LuuHD)
                .addGap(18, 18, 18)
                .addComponent(Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_tenKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_soDienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_timSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soLuongKhach_Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(P_ngayNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_ngayDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_gio, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LuuHD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LuuVaXuatHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        P_ChucNang.setBackground(new java.awt.Color(255, 255, 255));

        tableMonAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên món ăn", "S.L", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane2.setViewportView(tableMonAn);
        if (tableMonAn.getColumnModel().getColumnCount() > 0) {
            tableMonAn.getColumnModel().getColumn(0).setMaxWidth(50);
            tableMonAn.getColumnModel().getColumn(2).setMaxWidth(50);
        }

        lb_dsma.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_dsma.setForeground(new java.awt.Color(0, 102, 102));
        lb_dsma.setText("Danh sách món ăn");
        lb_dsma.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng tiền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tongTienMon.setBackground(new java.awt.Color(255, 255, 255));
        tongTienMon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tongTienMon.setForeground(new java.awt.Color(0, 153, 153));
        tongTienMon.setText("jLabel10");
        tongTienMon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tienThue.setBackground(new java.awt.Color(255, 255, 255));
        tienThue.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tienThue.setForeground(new java.awt.Color(0, 153, 153));
        tienThue.setText("jLabel11");
        tienThue.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tongThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        tongThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tongThanhToan.setForeground(new java.awt.Color(0, 153, 153));
        tongThanhToan.setText("jLabel12");
        tongThanhToan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel13.setText("Tổng tiền món ăn:");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel15.setText("Tiền thuế:");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel16.setText("Tổng Thanh Toán:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tongTienMon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(tienThue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tongThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tongTienMon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tienThue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tongThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout P_ChucNangLayout = new javax.swing.GroupLayout(P_ChucNang);
        P_ChucNang.setLayout(P_ChucNangLayout);
        P_ChucNangLayout.setHorizontalGroup(
            P_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_ChucNangLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb_dsma)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(P_ChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        P_ChucNangLayout.setVerticalGroup(
            P_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_ChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_dsma, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(P_ChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addComponent(P_ChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void t_soDienthoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_soDienthoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_soDienthoaiActionPerformed

    private void HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_HuyActionPerformed

    private String layMaMon(String tenMon){
        try {
            MonAn monAn = monDAO.getMonAnByTen(tenMon);
            return monAn.getMaMonAn();
        } catch (SQLException ex) {
            Logger.getLogger(Frame_LuuHD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void LuuHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LuuHDActionPerformed
        // TODO add your handling code here:
        
        try {
            yeucauDAO.luuYeuCau(yeucau.getMaYeuCau(), khachhang);
        } catch (SQLException ex) {
            Logger.getLogger(Frame_LuuHD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < tableMonAn.getRowCount(); i++) {
            
            String tenMon = (String) tableMonAn.getValueAt(i, 1); 
            
            int soLuong = (Integer) tableMonAn.getValueAt(i, 2);
            String ghiChu = ta_GhiChu.getText();
            String maMonAn = layMaMon(tenMon);
            
            try {
                chiTietYeuCauDAO.luuChiTietYeuCau(yeucau.getMaYeuCau(), maMonAn, soLuong, ghiChu);  // Lưu chi tiết yêu cầu
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        String maBan = ban.getMaBan();
        int soLuongKhach = (Integer) soLuongKhach_Spinner.getValue();
        java.sql.Date thoiGianTao = new java.sql.Date(System.currentTimeMillis()); // Lấy ngày hiện tại
        java.sql.Date ngayDatBan = new java.sql.Date(ngayNhanBan.getDate().getTime()); // Lấy ngày từ JDateChooser
        
        // Gọi phương thức lưu hóa đơn
        hoaDonDAO = new HoaDon_DAO();
        String maHD = hoaDonDAO.getMaHDMoi(this.nhanvien);
        String trangThaiHoaDon = "Chờ xử lý";
        try {
            boolean success = hoaDonDAO.luuHoaDon(maHD, yeucau.getMaYeuCau(), nhanvien.getMaNV(), maBan, soLuongKhach, thoiGianTao, ngayDatBan, trangThaiHoaDon);

            if (success) {
                this.ban.setTrangThaiBan("Đặt trước");
                banDAO.updateBan(ban);
                JOptionPane.showMessageDialog(this, "Đặt bàn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Lưu hóa đơn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi lưu hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }//GEN-LAST:event_LuuHDActionPerformed

    private void btn_timSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timSDTActionPerformed
        // TODO add your handling code here:
        String sdt = t_soDienthoai.getText();
        khachhang = khachhang_dao.findKhachHangBySDT(sdt);
        
        if(khachhang == null){
            lb_tenKH.setText("Không tồn tại");
            khachhang = khachhang_dao.getKhachHangByMa("KH000");
        }
        else{
            lb_tenKH.setText(khachhang.getTenKH());
        }
    }//GEN-LAST:event_btn_timSDTActionPerformed

    private void LuuVaXuatHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LuuVaXuatHDActionPerformed
        
    }//GEN-LAST:event_LuuVaXuatHDActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Huy;
    private javax.swing.JButton LuuHD;
    private javax.swing.JButton LuuVaXuatHD;
    private javax.swing.JPanel P_ChucNang;
    private javax.swing.JPanel P_ngayNhan;
    private javax.swing.JButton btn_timSDT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lb_Trangthai;
    private javax.swing.JLabel lb_dsma;
    private javax.swing.JLabel lb_gio;
    private javax.swing.JLabel lb_loaiBan;
    private javax.swing.JLabel lb_ngayDat;
    private javax.swing.JLabel lb_soGhe;
    private javax.swing.JLabel lb_tenKH;
    private javax.swing.JLabel lb_viTri;
    private javax.swing.JSpinner soLuongKhach_Spinner;
    private javax.swing.JTextField t_soDienthoai;
    private javax.swing.JTextArea ta_GhiChu;
    private javax.swing.JTable tableMonAn;
    private javax.swing.JLabel tienThue;
    private javax.swing.JLabel tongThanhToan;
    private javax.swing.JLabel tongTienMon;
    // End of variables declaration//GEN-END:variables
}
