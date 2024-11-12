package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import connectDB.ConnectDB;
import static dao.Ban_DAO.getBanByMaBan;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Ban;
import entity.MonAn;
import entity.YeuCauKhachHang;
import java.time.LocalDate;

public class HoaDon_DAO {
    private static final String INSERT_HOA_DON_SQL = "INSERT INTO HoaDon (maHD, maYeuCau, maNV, maBan, soLuongKhach, thoiGianTao, ngayDatBan) VALUES (?, ?, ?, ?, ?, ?, ?)";
    Connection conn = null;
    PreparedStatement pstmt = null;
    private TaoMaHoaDon tmhd = new TaoMaHoaDon();
    
    NhanVien nhanVien;
    YeuCauKhachHang_DAO yeuCauKhachHang_DAO;
    YeuCauKhachHang yeuCau;
    Ban_DAO banDAO;
    Ban ban;
    
    public String getMaHDMoi(NhanVien nhanvien){
        return tmhd.generateMaHoaDon(nhanvien);
    }
    
    public boolean luuHoaDon(String maHD, String maYeuCau, String maNV, String maBan, int soLuongKhach, Date thoiGianTao, Date ngayDatBan) throws SQLException {
        
        try {
            conn = ConnectDB.getInstance().connect();
            pstmt = conn.prepareStatement(INSERT_HOA_DON_SQL);

            pstmt.setString(1, maHD);
            pstmt.setString(2, maYeuCau);
            pstmt.setString(3, maNV);
            pstmt.setString(4, maBan);
            pstmt.setInt(5, soLuongKhach);
            pstmt.setDate(6, thoiGianTao);
            pstmt.setDate(7, ngayDatBan);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }
    
    public int getLastMaHDFromDB() {
        int lastMaHD = 0;

        String sql = "SELECT MAX(CAST(SUBSTRING(maHD, 3, 4) AS INT)) FROM HoaDon";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                lastMaHD = rs.getInt(1); // Lấy giá trị từ cột đầu tiên
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastMaHD;
    }
    
    public List<HoaDon> getAllHoaDon() throws SQLException {
        conn = ConnectDB.getInstance().connect();
        List<HoaDon> dsHoaDon = new ArrayList<>();
        String sql =    "SELECT hd.maHD, hd.thoiGianTao, hd.ngayDatBan, hd.soLuongKhach, " +
                        "nv.tenNV, kh.tenKH, hd.maYeuCau, b.maBan, " +
                        "(SELECT SUM(ctyc.soLuong * ma.giaTien) FROM ChiTietYeuCau ctyc " +
                        "JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn WHERE ctyc.maYeuCau = hd.maYeuCau) AS tongTien " +
                        "FROM HoaDon hd " +
                        "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                        "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                        "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                        "JOIN Ban b ON hd.maBan = b.maBan";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maHD = rs.getString("maHD").trim();
                String tenNV = rs.getString("tenNV").trim();
                String tenKH = rs.getString("tenKH").trim();
                String maYeuCau = rs.getString("maYeuCau").trim();
                String maBan = rs.getString("maBan").trim();
                LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();
                int soLuongKhach = rs.getInt("soLuongKhach");
                double tongTien = rs.getDouble("tongTien");

                // Khởi tạo các đối tượng liên quan
                NhanVien nhanVien = new NhanVien();
                nhanVien.setTenNV(tenNV);
                
                YeuCauKhachHang_DAO yeuCau_DAO = new YeuCauKhachHang_DAO();
                YeuCauKhachHang yeucau = yeuCau_DAO.getYeuCauByMaYeuCau(maYeuCau);
                
                Ban_DAO banDAO = new Ban_DAO();
                Ban ban = banDAO.getBanByMaBan("maBan");

                // Tạo đối tượng HoaDon
                HoaDon hoaDon = new HoaDon(maHD, yeucau, nhanVien, ban, soLuongKhach, thoiGianTao, ngayDatBan);
                hoaDon.setTongTien(tongTien);

                dsHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(dsHoaDon.size());
        return dsHoaDon;
    }
    
    public List<HoaDon> timHoaDonTheoSdt(String sdt) throws SQLException {
        List<HoaDon> dsHoaDon = new ArrayList<>();

        // SQL truy vấn hóa đơn theo số điện thoại của khách hàng
        String sql = "SELECT hd.maHD, hd.thoiGianTao, hd.ngayDatBan, hd.soLuongKhach, " +
                     "nv.tenNV, kh.tenKH, hd.maYeuCau, b.maBan, " +
                     "(SELECT SUM(ctyc.soLuong * ma.giaTien) FROM ChiTietYeuCau ctyc " +
                     "JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn WHERE ctyc.maYeuCau = hd.maYeuCau) AS tongTien " +
                     "FROM HoaDon hd " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                     "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                     "JOIN Ban b ON hd.maBan = b.maBan " +
                     "WHERE kh.sDT = ?";  // Điều kiện tìm kiếm theo số điện thoại của khách hàng

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sdt);  // Set số điện thoại vào câu truy vấn

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maHD = rs.getString("maHD");
                    String tenNV = rs.getString("tenNV");
                    String tenKH = rs.getString("tenKH");
                    String maYeuCau = rs.getString("maYeuCau");
                    LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                    LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();
                    int soLuongKhach = rs.getInt("soLuongKhach");
                    double tongTien = rs.getDouble("tongTien");

                    // Khởi tạo các đối tượng liên quan
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setTenNV(tenNV);

                    YeuCauKhachHang_DAO yeuCau_DAO = new YeuCauKhachHang_DAO();
                    YeuCauKhachHang yeucau = yeuCau_DAO.getYeuCauByMaYeuCau(maYeuCau);

                    Ban_DAO banDAO = new Ban_DAO();
                    Ban ban = banDAO.getBanByMaBan("maBan");

                    // Tạo đối tượng HoaDon
                    HoaDon hoaDon = new HoaDon(maHD, yeucau, nhanVien, ban, soLuongKhach, thoiGianTao, ngayDatBan);
                    hoaDon.setTongTien(tongTien); // Gán tổng tiền vào hóa đơn

                    dsHoaDon.add(hoaDon);
                }
            }
        }

        return dsHoaDon;
    }
    
    public HoaDon getHoaDonByMaHD(String maHD) {
        HoaDon hoaDon = null;
        String sql = "SELECT hd.maHD, hd.maYeuCau, hd.maNV, hd.maBan, hd.soLuongKhach, hd.thoiGianTao, hd.ngayDatBan, " +
                     "nv.tenNV, kh.tenKH " +
                     "FROM HoaDon hd " +
                     "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                     "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN Ban b ON hd.maBan = b.maBan " +
                     "WHERE hd.maHD = ?";
        try {
            if (conn == null || conn.isClosed()) {
                // Nếu kết nối đã bị đóng, hãy mở lại hoặc lấy kết nối mới
                conn = ConnectDB.getInstance().connect();
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, maHD);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Lấy dữ liệu từ ResultSet
                        String maYeuCau = rs.getString("maYeuCau").trim();
                        String maNV = rs.getString("maNV").trim();
                        String maBan = rs.getString("maBan").trim();
                        int soLuongKhach = rs.getInt("soLuongKhach");
                        LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                        LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();

                        // Tạo đối tượng NhanVien
                        NhanVien_DAO nhanvienDAO = new NhanVien_DAO();
                        nhanVien = nhanvienDAO.getNhanVienByMa(maNV);

                        // Tạo đối tượng KhachHang thông qua YeuCauKhachHang
                        yeuCauKhachHang_DAO = new YeuCauKhachHang_DAO();
                        yeuCau = yeuCauKhachHang_DAO.getYeuCauByMaYeuCau(maYeuCau);

                        // Tạo đối tượng Ban
                        banDAO = new Ban_DAO();
                        ban = banDAO.getBanByMaBan(maBan);

                        // Tạo đối tượng HoaDon
                        hoaDon = new HoaDon(maHD, yeuCau, nhanVien, ban, soLuongKhach, thoiGianTao, ngayDatBan);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu kết nối không hợp lệ
        }
        return hoaDon;
    }

}
