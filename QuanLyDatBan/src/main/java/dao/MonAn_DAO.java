package dao;

import connectDB.ConnectDB;
import entity.LoaiMon;
import entity.MonAn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonAn_DAO {

    public String getLastMaMonAn() {
        String sql = "SELECT TOP 1 maMonAn FROM MonAn ORDER BY maMonAn DESC";
        String lastMaMonAn = null;

        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                lastMaMonAn = rs.getString("maMonAn");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger
        }
        return lastMaMonAn;
    }

    public boolean checkMaMonAnExists(String maMonAn) {
        String sql = "SELECT COUNT(*) FROM MonAn WHERE maMonAn = ?";
        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maMonAn);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger
        }
        return false;
    }

    public boolean insertMonAn(MonAn monAn) {
        String sql = "INSERT INTO MonAn (maMonAn, tenMonAn, thongTinMon, trangThaiMonAn, giaTien, maLoai) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, monAn.getMaMonAn().trim());
            ps.setString(2, monAn.getTenMonAn().trim());
            ps.setString(3, monAn.getThongTinMon().trim());
            ps.setString(4, monAn.getTrangThaiMon().trim());
            ps.setDouble(5, monAn.getGiaTien());
            ps.setString(6, monAn.getLoai_mon() != null ? monAn.getLoai_mon().getMaLoai() : null);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger
        }
        return false;
    }

    public List<MonAn> getAllMonAn() {
        List<MonAn> list = new ArrayList<>();
        String sql = "SELECT * FROM MonAn";

        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maMonAn = rs.getString("maMonAn");
                String tenMonAn = rs.getString("tenMonAn");
                String thongTinMon = rs.getString("thongTinMon");
                String trangThaiMon = rs.getString("trangThaiMonAn");
                double giaTien = rs.getDouble("giaTien");
                String maLoai = rs.getString("maLoai");

                LoaiMon loaiMon = getLoaiMonByMa(maLoai); // Implement this method
                MonAn monAn = new MonAn(maMonAn, tenMonAn, thongTinMon, trangThaiMon, giaTien, loaiMon);
                list.add(monAn);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger
        }
        return list;
    }

    private LoaiMon getLoaiMonByMa(String maLoai) {
        String sql = "SELECT * FROM LoaiMon WHERE maLoai = ?";
        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maLoai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new LoaiMon(rs.getString("maLoai"), rs.getString("tenLoai"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger
        }
        return null; // Return null if not found
    }

    public boolean updateMonAn(MonAn monAn) {
        String sql = "UPDATE MonAn SET tenMonAn = ?, thongTinMon = ?, trangThaiMonAn = ?, giaTien = ?, maLoai = ? WHERE maMonAn = ?";

        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, monAn.getTenMonAn());
            ps.setString(2, monAn.getThongTinMon());
            ps.setString(3, monAn.getTrangThaiMon());
            ps.setDouble(4, monAn.getGiaTien());
            ps.setString(5, monAn.getLoai_mon().getMaLoai());
            ps.setString(6, monAn.getMaMonAn());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger
        }
        return false;
    }

    public MonAn findMonAnByMaMonAn(String maMonAn) {
        String sql = "SELECT m.*, l.maLoai, l.tenLoai FROM MonAn m JOIN LoaiMon l ON m.maLoai = l.maLoai WHERE m.maMonAn = ?";

        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maMonAn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tenMonAn = rs.getString("tenMonAn");
                    String thongTinMon = rs.getString("thongTinMon");
                    String trangThaiMon = rs.getString("trangThaiMonAn");
                    double giaTien = rs.getDouble("giaTien");
                    String maLoai = rs.getString("maLoai");
                    String tenLoai = rs.getString("tenLoai");
                    LoaiMon loaiMon = new LoaiMon(maLoai, tenLoai);

                    return new MonAn(maMonAn, tenMonAn, thongTinMon, trangThaiMon, giaTien, loaiMon);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger
        }
        return null;
    }

    public boolean deleteMonAn(String maMonAn) {
        String sql = "DELETE FROM MonAn WHERE maMonAn = ?";

        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maMonAn);
            return ps.executeUpdate() > 0; // Return true if deletion is successful
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger
        }
        return false;
    }

    public List<MonAn> searchMonAn(String keyword) {
        String sql = "SELECT * FROM MonAn WHERE maMonAn LIKE ? OR tenMonAn LIKE ?";
        List<MonAn> result = new ArrayList<>();

        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maMonAn = rs.getString("maMonAn");
                    String tenMonAn = rs.getString("tenMonAn");
                    String thongTinMon = rs.getString("thongTinMon");
                    String trangThaiMon = rs.getString("trangThaiMonAn");
                    double giaTien = rs.getDouble("giaTien");
                    String maLoai = rs.getString("maLoai");

                    LoaiMon loaiMon = getLoaiMonByMa(maLoai); // Implement to fetch LoaiMon
                    MonAn monAn = new MonAn(maMonAn, tenMonAn, thongTinMon, trangThaiMon, giaTien, loaiMon);
                    result.add(monAn);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger
        }
        return result; // Return list of found dishes
    }
    
    public List<LoaiMon> getAllLoaiMon() {
        List<LoaiMon> list = new ArrayList<>();
        String sql = "SELECT maLoai, tenLoai FROM LoaiMon"; // Giả sử bạn có bảng LoaiMon
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ConnectDB connectDB = new ConnectDB();
            con = connectDB.connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String maLoai = rs.getString("maLoai");
                String tenLoai = rs.getString("tenLoai");
                list.add(new LoaiMon(maLoai, tenLoai));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, ps, con);
        }
        return list;
    }

    private void closeResources(ResultSet rs, PreparedStatement ps, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
