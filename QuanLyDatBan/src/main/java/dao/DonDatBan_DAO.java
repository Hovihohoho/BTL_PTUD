package dao;

import entity.DonDatBan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonDatBan_DAO {
    private Connection con;
    
    // Thêm một đơn đặt bàn vào cơ sở dữ liệu
    public boolean insertDonDatBan(DonDatBan donDatBan) {
        String sql = "INSERT INTO don_dat_ban (maHD, soLuongKhach) VALUES (?, ?)";
        try (
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, donDatBan.getMaHD());
            ps.setInt(2, donDatBan.getSoLuongKhach());
            return ps.executeUpdate() > 0;  // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy tất cả đơn đặt bàn từ cơ sở dữ liệu
    public List<DonDatBan> getAllDonDatBan() {
        List<DonDatBan> list = new ArrayList<>();
        String sql = "SELECT * FROM don_dat_ban";
        try (
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String maHD = rs.getString("maHD");
                int soLuongKhach = rs.getInt("soLuongKhach");
                DonDatBan donDatBan = new DonDatBan(maHD, soLuongKhach);
                list.add(donDatBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Cập nhật số lượng khách cho đơn đặt bàn
    public boolean updateDonDatBan(DonDatBan donDatBan) {
        String sql = "UPDATE don_dat_ban SET soLuongKhach = ? WHERE maHD = ?";
        try (
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, donDatBan.getSoLuongKhach());
            ps.setString(2, donDatBan.getMaHD());
            return ps.executeUpdate() > 0;  // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa đơn đặt bàn khỏi cơ sở dữ liệu
    public boolean deleteDonDatBan(String maHD) {
        String sql = "DELETE FROM don_dat_ban WHERE maHD = ?";
        try (
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maHD);
            return ps.executeUpdate() > 0;  // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm đơn đặt bàn theo maHD
    public DonDatBan findDonDatBanByMaHD(String maHD) {
        String sql = "SELECT * FROM don_dat_ban WHERE maHD = ?";
        try (
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maHD);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int soLuongKhach = rs.getInt("soLuongKhach");
                    return new DonDatBan(maHD, soLuongKhach);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
