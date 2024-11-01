package dao;

import connectDB.ConnectDB;
import entity.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
            closeResources(ps, ps, conn);  // Ensure resources are closed
        }
        return false;
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
            closeResources(ps, ps, conn);  // Ensure resources are closed
        }
        return false;
    }
    
    public List<KhachHang> getAllKhachHang() {
    List<KhachHang> listKhachHang = new ArrayList<>();
    
    try {
        Connection conn = ConnectDB.getInstance().getConnection();
        String sql = "SELECT maKH, tenKH, sdt, email, lichSuDatBan FROM KhachHang"; // Cập nhật tên cột nếu cần
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            String maKH = rs.getString("maKH");
            String tenKH = rs.getString("tenKH");
            String soDienThoai = rs.getString("soDienThoai");
            String gioiTinh = rs.getString("gioiTinh");
            String diaChi = rs.getString("diaChi");
            
            listKhachHang.add(new KhachHang(maKH, tenKH, soDienThoai, gioiTinh, diaChi)); // Cập nhật tham số nếu cần
        }
        
        rs.close();
        ps.close();
        conn.close();
            
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return listKhachHang;
}





    // Cập nhật thông tin khách hàng
   
    // Xóa khách hàng khỏi cơ sở dữ liệu
    public boolean deleteKhachHang(String maKH) {
        String sql = "DELETE FROM khach_hang WHERE maKH = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            ConnectDB.getInstance().getConnection();  // Establish the connection
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maKH);
            return ps.executeUpdate() > 0;  // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(ps, ps, conn);  // Ensure resources are closed
        }
        return false;
    }
    private void closeResources(PreparedStatement ps, PreparedStatement ps1, Connection conn) {
        closeResources(null, ps, conn);
    }

    KhachHang findKhachHangByMaKH(String maKH) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
