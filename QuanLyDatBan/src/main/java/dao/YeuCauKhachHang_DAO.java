package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.YeuCauKhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class YeuCauKhachHang_DAO {
    private Connection conn;

    public YeuCauKhachHang_DAO() throws SQLException {
        conn = ConnectDB.getInstance().connect();
    }

    // Lưu yêu cầu vào bảng YeuCau
    public void luuYeuCau(String maYeuCau, KhachHang kh) throws SQLException {ConnectDB connectDB = new ConnectDB();
        String sql = "INSERT INTO YeuCauKhachHang (maYeuCau, maKH) VALUES (?, ?)";    
        conn = connectDB.connect();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, maYeuCau);
        ps.setString(2, kh.getMaKH());
        ps.executeUpdate();
    }
    
    public String getMaxMaYeuCau() throws SQLException {
        String sql = "SELECT MAX(maYeuCau) FROM YeuCauKhachHang";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);  // Trả về mã yêu cầu lớn nhất
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Trả về null nếu không có dữ liệu
    }
    
    public String generateNextMaYeuCau() throws SQLException {
        String maxMaYeuCau = getMaxMaYeuCau();
        String nextMaYeuCau = "YC001";  // Mặc định nếu không có mã yêu cầu nào trong CSDL

        if (maxMaYeuCau != null) {
            // Lấy phần số sau tiền tố YC và tăng lên 1
            maxMaYeuCau = maxMaYeuCau.trim();
            String numberPart = maxMaYeuCau.substring(2);  // Lấy phần sau "YC"
            numberPart = numberPart.trim();
            int nextNumber = Integer.parseInt(numberPart) + 1;  // Tăng số lên 1
            nextMaYeuCau = "YC" + String.format("%03d", nextNumber);  // Tạo mã yêu cầu mới với 3 chữ số
        }

        return nextMaYeuCau;
    }
    
    public YeuCauKhachHang getYeuCauByMaYeuCau(String maYeuCau) {
        String sql = "SELECT yc.maYeuCau, kh.maKH, kh.tenKH, kh.sDT, kh.diaChi, kh.gioiTinh " +
                     "FROM YeuCauKhachHang yc " +
                     "JOIN KhachHang kh ON yc.maKH = kh.maKH " +
                     "WHERE yc.maYeuCau = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maYeuCau);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Lấy thông tin khách hàng
                    String maKH = rs.getString("maKH").trim();
                    String tenKH = rs.getString("tenKH").trim();
                    String sDT = rs.getString("sDT").trim();
                    String diaChi = rs.getString("diaChi").trim();
                    String gioiTinh = rs.getString("gioiTinh").trim();

                    // Tạo đối tượng KhachHang
                    KhachHang khachHang = new KhachHang(maKH, tenKH, sDT, diaChi, gioiTinh);

                    // Tạo đối tượng YeuCauKhachHang
                    YeuCauKhachHang yeuCau = new YeuCauKhachHang(maYeuCau, khachHang);
                    return yeuCau;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
}
}
