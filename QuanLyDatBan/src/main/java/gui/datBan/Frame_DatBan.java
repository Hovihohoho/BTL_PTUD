/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.datBan;

import gui.datBan.Frame_GoiMon;
import entity.Ban;
import entity.NhanVien;
import entity.YeuCauKhachHang;
import gui.datBan.Frame_GoiMon.GoiMonListener;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Frame_DatBan extends javax.swing.JFrame implements GoiMonListener{
    private Ban ban;
    private DefaultTableModel dsMonModel;
    private double thue;
    private double tongTien;
    private NhanVien nhanvien;
    /**
     * Creates new form DatBan
     */
    public Frame_DatBan() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        
        T_dsMon.setFont(new Font("Arial", Font.PLAIN, 18));
        T_dsMon.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        T_dsMon.setRowHeight(30);
        dsMonModel = (DefaultTableModel) T_dsMon.getModel();
        
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        // Khởi tạo Timer cập nhật mỗi giây
        Timer timer = new Timer(1000, e -> {
            Date now = new Date();
            l_ngay.setText("Ngày: " + dateFormat.format(now));
            l_gio.setText("Giờ: " + timeFormat.format(now));
        });
        timer.start();
    }
    
     public Frame_DatBan(Ban ban, NhanVien nhanvien) {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.ban = ban;
        this.nhanvien = nhanvien;
        
        T_dsMon.setFont(new Font("Arial", Font.PLAIN, 20));
        T_dsMon.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        T_dsMon.setRowHeight(30);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        T_dsMon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        T_dsMon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        dsMonModel = (DefaultTableModel) T_dsMon.getModel();
        l_tenNV.setText(this.nhanvien.getTenNV());
        l_maban.setText(this.ban.getMaBan());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        // Khởi tạo Timer cập nhật mỗi giây
        Timer timer = new Timer(1000, e -> {
            Date now = new Date();
            l_ngay.setText("Ngày: " + dateFormat.format(now));
            l_gio.setText("Giờ: " + timeFormat.format(now));
        });
        timer.start();
    }
     
    public void setDsMonAn(List<Object[]> dsMonAn) {
        dsMonModel.setRowCount(0); // Xóa các dòng cũ (nếu có)
        for (Object[] row : dsMonAn) {
            dsMonModel.addRow(row);
        }
    }
    
    @Override
    public void onGoiMonCompleted(List<Object[]> dsMonAn, double thue, double tongTien) {
        this.thue += thue;
        this.tongTien += tongTien;
        DecimalFormat df = new DecimalFormat("#,### ");

        // Duyệt qua danh sách món ăn đã chọn từ Frame_GoiMon
        for (Object[] monMoi : dsMonAn) {
            String tenMonMoi = (String) monMoi[1];
            int soLuongMoi = (int) monMoi[2];
            double donGia = (double) monMoi[3];
            boolean daTonTai = false;

            // Kiểm tra xem món ăn đã tồn tại trong bảng DatBan chưa
            for (int i = 0; i < dsMonModel.getRowCount(); i++) {
                String tenMonTrongBang = (String) dsMonModel.getValueAt(i, 1); // Cột 1: Tên món ăn
                if (tenMonTrongBang.equals(tenMonMoi)) {
                    // Nếu món ăn đã tồn tại, tăng số lượng và cập nhật tổng tiền
                    int soLuongHienTai = (int) dsMonModel.getValueAt(i, 2);
                    int soLuongMoiCapNhat = soLuongHienTai + soLuongMoi;
                    dsMonModel.setValueAt(soLuongMoiCapNhat, i, 2); // Cập nhật cột 2: Số lượng

                    // Cập nhật lại thành tiền cho dòng
                    double thanhTienMoi = donGia * soLuongMoiCapNhat;
                    dsMonModel.setValueAt(thanhTienMoi, i, 4); // Cập nhật cột 4: Thành tiền
                    daTonTai = true;
                    break;
                }
            }

            // Nếu món ăn chưa có trong bảng, thêm dòng mới
            if (!daTonTai) {
                dsMonModel.addRow(new Object[]{
                    dsMonModel.getRowCount() + 1, // STT mới
                    tenMonMoi,
                    soLuongMoi,
                    donGia,
                    donGia * soLuongMoi
                });
            }
        }

        // Cập nhật hiển thị cho tổng tiền và thuế
        L_thue.setText(df.format(this.thue));
        L_tongTien.setText(df.format(this.tongTien));
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        P_DonDat = new javax.swing.JPanel();
        P_HoaDon = new javax.swing.JPanel();
        P_TongTien = new javax.swing.JPanel();
        P_t = new javax.swing.JPanel();
        L_tongTien = new javax.swing.JLabel();
        lt_tongtien = new javax.swing.JLabel();
        L_thue = new javax.swing.JLabel();
        lt_tax = new javax.swing.JLabel();
        scrolltable = new javax.swing.JScrollPane();
        T_dsMon = new javax.swing.JTable();
        header = new javax.swing.JPanel();
        L_nv = new javax.swing.JLabel();
        l_tenNV = new javax.swing.JLabel();
        L_ban = new javax.swing.JLabel();
        l_maban = new javax.swing.JLabel();
        l_ngay = new javax.swing.JLabel();
        l_gio = new javax.swing.JLabel();
        button_bar = new javax.swing.JPanel();
        Btn_HuyBang = new javax.swing.JButton();
        Btn_DatBan = new javax.swing.JButton();
        Btn_ChuyenBan = new javax.swing.JButton();
        Btn_ThemMon = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        l_dsma = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        P_DonDat.setBackground(new java.awt.Color(255, 255, 255));

        P_HoaDon.setBackground(new java.awt.Color(255, 255, 255));

        P_TongTien.setBackground(new java.awt.Color(255, 255, 255));

        P_t.setBackground(new java.awt.Color(204, 255, 204));

        L_tongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        L_tongTien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        L_tongTien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lt_tongtien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lt_tongtien.setText("Tổng Tiền");

        L_thue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        L_thue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lt_tax.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lt_tax.setText("Thuế");

        javax.swing.GroupLayout P_tLayout = new javax.swing.GroupLayout(P_t);
        P_t.setLayout(P_tLayout);
        P_tLayout.setHorizontalGroup(
            P_tLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_tLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(lt_tax, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(L_thue, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lt_tongtien)
                .addGap(18, 18, 18)
                .addComponent(L_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        P_tLayout.setVerticalGroup(
            P_tLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_tLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_tLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lt_tongtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(L_tongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(L_thue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lt_tax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout P_TongTienLayout = new javax.swing.GroupLayout(P_TongTien);
        P_TongTien.setLayout(P_TongTienLayout);
        P_TongTienLayout.setHorizontalGroup(
            P_TongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_TongTienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(P_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );
        P_TongTienLayout.setVerticalGroup(
            P_TongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(P_t, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        scrolltable.setBackground(new java.awt.Color(255, 255, 255));
        scrolltable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                scrolltableComponentHidden(evt);
            }
        });

        T_dsMon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        T_dsMon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        T_dsMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên món ăn", "số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
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
        T_dsMon.setGridColor(new java.awt.Color(0, 0, 0));
        T_dsMon.setRowHeight(30);
        scrolltable.setViewportView(T_dsMon);

        javax.swing.GroupLayout P_HoaDonLayout = new javax.swing.GroupLayout(P_HoaDon);
        P_HoaDon.setLayout(P_HoaDonLayout);
        P_HoaDonLayout.setHorizontalGroup(
            P_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(P_TongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_HoaDonLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(scrolltable)
                .addGap(120, 120, 120))
        );
        P_HoaDonLayout.setVerticalGroup(
            P_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_HoaDonLayout.createSequentialGroup()
                .addComponent(scrolltable, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        header.setBackground(new java.awt.Color(255, 255, 255));

        L_nv.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        L_nv.setText("Nhân viên: ");

        l_tenNV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        l_tenNV.setForeground(new java.awt.Color(0, 153, 51));
        l_tenNV.setText("demo");
        l_tenNV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        l_tenNV.setVerifyInputWhenFocusTarget(false);

        L_ban.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        L_ban.setText("Bàn:");

        l_maban.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        l_maban.setForeground(new java.awt.Color(0, 153, 0));
        l_maban.setText("DEMO");

        l_ngay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        l_gio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(L_ban)
                .addGap(12, 12, 12)
                .addComponent(l_maban, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(L_nv, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(l_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_gio, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l_gio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L_nv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l_maban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(L_ban))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        button_bar.setBackground(new java.awt.Color(255, 255, 255));

        Btn_HuyBang.setBackground(new java.awt.Color(255, 102, 51));
        Btn_HuyBang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_HuyBang.setForeground(new java.awt.Color(255, 255, 255));
        Btn_HuyBang.setText("Hủy");
        Btn_HuyBang.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Btn_HuyBang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_HuyBangActionPerformed(evt);
            }
        });

        Btn_DatBan.setBackground(new java.awt.Color(153, 255, 153));
        Btn_DatBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_DatBan.setText("Đặt Bàn");
        Btn_DatBan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Btn_DatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_DatBanActionPerformed(evt);
            }
        });
        Btn_DatBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_DatBanKeyPressed(evt);
            }
        });

        Btn_ChuyenBan.setBackground(new java.awt.Color(255, 204, 255));
        Btn_ChuyenBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_ChuyenBan.setText("Chuyển Bàn");
        Btn_ChuyenBan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Btn_ChuyenBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ChuyenBanActionPerformed(evt);
            }
        });

        Btn_ThemMon.setBackground(new java.awt.Color(255, 255, 204));
        Btn_ThemMon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_ThemMon.setText("Thêm Món");
        Btn_ThemMon.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Btn_ThemMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ThemMonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout button_barLayout = new javax.swing.GroupLayout(button_bar);
        button_bar.setLayout(button_barLayout);
        button_barLayout.setHorizontalGroup(
            button_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_barLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Btn_ThemMon)
                .addGap(18, 18, 18)
                .addComponent(Btn_ChuyenBan)
                .addGap(18, 18, 18)
                .addComponent(Btn_HuyBang, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btn_DatBan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        button_barLayout.setVerticalGroup(
            button_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, button_barLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(button_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(Btn_ChuyenBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_ThemMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_HuyBang, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_DatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        l_dsma.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        l_dsma.setText("Danh sách món ăn:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(l_dsma, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l_dsma, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout P_DonDatLayout = new javax.swing.GroupLayout(P_DonDat);
        P_DonDat.setLayout(P_DonDatLayout);
        P_DonDatLayout.setHorizontalGroup(
            P_DonDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(P_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(P_DonDatLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(button_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator2)
        );
        P_DonDatLayout.setVerticalGroup(
            P_DonDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_DonDatLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(button_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(P_DonDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(P_DonDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_HuyBangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_HuyBangActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_Btn_HuyBangActionPerformed
    
    
    
    private void Btn_DatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DatBanActionPerformed
        
        String gio = l_gio.getText();
        String ngay = l_ngay.getText();

        // Lấy danh sách món ăn từ bảng hiện tại
        List<Object[]> dsMonAn = new ArrayList<>();
        for (int i = 0; i < dsMonModel.getRowCount(); i++) {
            Object[] rowData = {
                dsMonModel.getValueAt(i, 0),  // STT
                dsMonModel.getValueAt(i, 1),  // Tên món
                dsMonModel.getValueAt(i, 2),  // Số lượng
                dsMonModel.getValueAt(i, 3),  // Đơn giá
                dsMonModel.getValueAt(i, 4)   // Thành tiền
            };
            dsMonAn.add(rowData);
        }

        // Truyền danh sách món ăn sang Frame_LuuHD
        try {
            Frame_LuuHD flhd = new Frame_LuuHD(ban, gio, ngay, dsMonAn, nhanvien, this.tongTien-this.thue);
            flhd.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Frame_DatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Btn_DatBanActionPerformed

    private void Btn_DatBanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_DatBanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_DatBanKeyPressed

    private void Btn_ChuyenBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ChuyenBanActionPerformed
        this.dispose();
    }//GEN-LAST:event_Btn_ChuyenBanActionPerformed

    private void scrolltableComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_scrolltableComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_scrolltableComponentHidden

    private void Btn_ThemMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ThemMonActionPerformed
        // TODO add your handling code here:
        Frame_GoiMon fgm = new Frame_GoiMon(this, ban);
        fgm.setVisible(true);
    }//GEN-LAST:event_Btn_ThemMonActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_ChuyenBan;
    private javax.swing.JButton Btn_DatBan;
    private javax.swing.JButton Btn_HuyBang;
    private javax.swing.JButton Btn_ThemMon;
    private javax.swing.JLabel L_ban;
    private javax.swing.JLabel L_nv;
    private javax.swing.JLabel L_thue;
    private javax.swing.JLabel L_tongTien;
    private javax.swing.JPanel P_DonDat;
    private javax.swing.JPanel P_HoaDon;
    private javax.swing.JPanel P_TongTien;
    private javax.swing.JPanel P_t;
    private javax.swing.JTable T_dsMon;
    private javax.swing.JPanel button_bar;
    private javax.swing.JPanel header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel l_dsma;
    private javax.swing.JLabel l_gio;
    private javax.swing.JLabel l_maban;
    private javax.swing.JLabel l_ngay;
    private javax.swing.JLabel l_tenNV;
    private javax.swing.JLabel lt_tax;
    private javax.swing.JLabel lt_tongtien;
    private javax.swing.JScrollPane scrolltable;
    // End of variables declaration//GEN-END:variables
}
