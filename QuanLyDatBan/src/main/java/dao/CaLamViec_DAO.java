package dao;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import entity.CaLamViec;
import connectDB.ConnectDB;

public class CaLamViec_DAO {

    // Phương thức lấy tất cả ca làm việc từ cơ sở dữ liệu
    public List<CaLamViec> getAllCaLam() {
        List<CaLamViec> danhSachCaLam = new ArrayList<>();
        String sql = "SELECT * FROM CaLamViec";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            // Duyệt qua tất cả các bản ghi trong ResultSet
            while (rs.next()) {
                String maCa = rs.getString("maCa");
                String buoiLam = rs.getString("buoiLam");
                String gioBatDau = rs.getString("gioBatDau");
                String gioKetThuc = rs.getString("gioKetThuc");

                // Loại bỏ phần thập phân nếu có (ví dụ: "08:00:00.0000000" => "08:00:00")
                gioBatDau = gioBatDau.split("\\.")[0];
                gioKetThuc = gioKetThuc.split("\\.")[0];

                // Định dạng thời gian (HH:mm:ss)
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime gioBatDauTime = LocalTime.parse(gioBatDau, formatter);
                LocalTime gioKetThucTime = LocalTime.parse(gioKetThuc, formatter);

                // Tạo đối tượng CaLamViec từ dữ liệu truy vấn được
                CaLamViec ca = new CaLamViec(maCa, buoiLam, gioBatDauTime, gioKetThucTime);
                
                // Thêm vào danh sách
                danhSachCaLam.add(ca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachCaLam;
    }

    // Phương thức thêm ca làm việc mới vào cơ sở dữ liệu
    public boolean themCaLam(CaLamViec ca) {
        String sql = "INSERT INTO CaLamViec (maCa, buoiLam, gioBatDau, gioKetThuc) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thiết lập các tham số trong câu lệnh SQL
            ps.setString(1, ca.getMaCa());
            ps.setString(2, ca.getBuoiLam());
//            ps.setString(3, ca.getGioBatDau().toString()); // Chuyển LocalTime thành chuỗi
//            ps.setString(4, ca.getGioKetThuc().toString()); // Chuyển LocalTime thành chuỗi
            ps.setTime(3, Time.valueOf(ca.getGioBatDau())); // Sử dụng Time cho cột TIME
            ps.setTime(4, Time.valueOf(ca.getGioKetThuc())); // Sử dụng Time cho cột TIME

            // Thực hiện câu lệnh
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean checkMaCaExists(String maCa) {
    String sql = "SELECT COUNT(*) FROM CaLamViec WHERE maCa = ?";
    try (Connection conn = ConnectDB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, maCa);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    // Phương thức sửa ca làm việc
    public boolean suaCaLam(CaLamViec ca) {
        // Cập nhật thông tin ca làm việc mà không thay đổi mã ca
    String sql = "UPDATE CaLamViec SET buoiLam = ?, gioBatDau = ?, gioKetThuc = ? WHERE maCa = ?";
    try (Connection conn = ConnectDB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Thiết lập các tham số trong câu lệnh SQL
        ps.setString(1, ca.getBuoiLam()); // Cập nhật buổi làm
        ps.setString(2, ca.getGioBatDau().toString()); // Chuyển LocalTime thành chuỗi
        ps.setString(3, ca.getGioKetThuc().toString()); // Chuyển LocalTime thành chuỗi
        ps.setString(4, ca.getMaCa()); // Điều kiện WHERE với maCa cũ

        // Thực hiện câu lệnh
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
    }

    // Phương thức xóa ca làm việc
    public boolean xoaCaLam(String maCa) {
        String sql = "DELETE FROM CaLamViec WHERE maCa = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thiết lập tham số maCa
            ps.setString(1, maCa);

            // Thực hiện câu lệnh
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phương thức tìm ca làm việc theo mã
    public CaLamViec timCaLam(String maCa) {
        String sql = "SELECT * FROM CaLamViec WHERE maCa = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maCa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String buoiLam = rs.getString("buoiLam");
                String gioBatDau = rs.getString("gioBatDau");
                String gioKetThuc = rs.getString("gioKetThuc");

                // Loại bỏ phần thập phân nếu có
                gioBatDau = gioBatDau.split("\\.")[0];
                gioKetThuc = gioKetThuc.split("\\.")[0];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime gioBatDauTime = LocalTime.parse(gioBatDau, formatter);
                LocalTime gioKetThucTime = LocalTime.parse(gioKetThuc, formatter);

                return new CaLamViec(maCa, buoiLam, gioBatDauTime, gioKetThucTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
