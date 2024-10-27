package dao;

import connectDB.ConnectDB;
import entity.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHang_DAO {

    // Thêm khách hàng mới vào cơ sở dữ liệu
    public boolean insertKhachHang(KhachHang khachHang) {
        String sql = "INSERT INTO khach_hang (maKH, tenKH, soDienThoai, diaChi) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            ConnectDB.getInstance().connect();  // Establish the connection
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, khachHang.getMaKH());
            ps.setString(2, khachHang.getTenKH());
            ps.setString(3, khachHang.getSoDienThoai());
            ps.setString(4, khachHang.getDiaChi());
            return ps.executeUpdate() > 0;  // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(ps, conn);  // Ensure resources are closed
        }
        return false;
    }

    // Lấy danh sách tất cả khách hàng từ cơ sở dữ liệu
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ConnectDB.getInstance().connect();  // Establish the connection
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("tenKH");
                String soDienThoai = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");

                KhachHang khachHang = new KhachHang(maKH, tenKH, soDienThoai, diaChi);
                list.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, ps, conn);  // Ensure resources are closed
        }
        return list;
    }

    // Cập nhật thông tin khách hàng
    public boolean updateKhachHang(KhachHang khachHang) {
        String sql = "UPDATE khach_hang SET tenKH = ?, soDienThoai = ?, diaChi = ? WHERE maKH = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            ConnectDB.getInstance().connect();  // Establish the connection
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, khachHang.getTenKH());
            ps.setString(2, khachHang.getSoDienThoai());
            ps.setString(3, khachHang.getDiaChi());
            ps.setString(4, khachHang.getMaKH());
            return ps.executeUpdate() > 0;  // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(ps, conn);  // Ensure resources are closed
        }
        return false;
    }

    // Xóa khách hàng khỏi cơ sở dữ liệu
    public boolean deleteKhachHang(String maKH) {
        String sql = "DELETE FROM khach_hang WHERE maKH = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            ConnectDB.getInstance().connect();  // Establish the connection
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maKH);
            return ps.executeUpdate() > 0;  // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(ps, conn);  // Ensure resources are closed
        }
        return false;
    }

    // Tìm khách hàng theo maKH
    public KhachHang findKhachHangByMaKH(String maKH) {
        String sql = "SELECT * FROM khach_hang WHERE maKH = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ConnectDB.getInstance().connect();  // Establish the connection
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maKH);
            rs = ps.executeQuery();
            if (rs.next()) {
                String tenKH = rs.getString("tenKH");
                String soDienThoai = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");
                return new KhachHang(maKH, tenKH, soDienThoai, diaChi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, ps, conn);  // Ensure resources are closed
        }
        return null;
    }

    // Helper method to close resources
    private void closeResources(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeResources(PreparedStatement ps, Connection conn) {
        closeResources(null, ps, conn);
    }
}
