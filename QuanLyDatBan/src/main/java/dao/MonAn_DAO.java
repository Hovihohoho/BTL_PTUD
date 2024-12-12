package dao;

import connectDB.ConnectDB;
import entity.LoaiMon;
import entity.MonAn;
import gui.card.Card_Mon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonAn_DAO {
    Connection conn;
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
    
    public List<Card_Mon> taoDanhSachCardMon() {
        List<Card_Mon> cardList = new ArrayList<>();
        List<MonAn> monAnList = getAllMonAn();

        for (MonAn monAn : monAnList) {
            Card_Mon card = new Card_Mon(monAn);
            cardList.add(card);
        }
        return cardList;
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
    
    public List<Card_Mon> createCardMonList() {
        List<Card_Mon> cardMonList = new ArrayList<>();
        List<MonAn> monAnList = getAllMonAn(); // Gọi phương thức để lấy danh sách món ăn
        
        for (MonAn monAn : monAnList) {
            Card_Mon cardMon = new Card_Mon(monAn); // Tạo đối tượng Card_Mon cho mỗi món ăn
            cardMonList.add(cardMon); // Thêm vào danh sách cardMonList
        }

        return cardMonList; // Trả về danh sách các Card_Mon
    }
            
    public List<Card_Mon> createCardLoaiMon(String loaiMon) {
        List<Card_Mon> cardMonList = new ArrayList<>();
        List<MonAn> monAnList = getAllMonAn(); // Gọi phương thức để lấy danh sách món ăn
        
        for (MonAn monAn : monAnList) {
            if(monAn.getLoai_mon().getTenLoai().equalsIgnoreCase(loaiMon)){
                Card_Mon cardMon = new Card_Mon(monAn); // Tạo đối tượng Card_Mon cho mỗi món ăn
                cardMonList.add(cardMon); // Thêm vào danh sách cardMonList
            }
        }

        return cardMonList; // Trả về danh sách các Card_Mon
    }
    
    
    
    public List<Card_Mon> createCardLoaiMontheoTrangThai(String trangThai) {
        List<Card_Mon> cardMonList = new ArrayList<>();
        List<MonAn> monAnList = getAllMonAn(); // Gọi phương thức để lấy danh sách món ăn
        
        for (MonAn monAn : monAnList) {
            if(monAn.getTrangThaiMon().equalsIgnoreCase(trangThai)){
                Card_Mon cardMon = new Card_Mon(monAn); // Tạo đối tượng Card_Mon cho mỗi món ăn
                cardMonList.add(cardMon); // Thêm vào danh sách cardMonList
            }
        }

        return cardMonList; // Trả về danh sách các Card_Mon
    }        
            
    public MonAn getMonAnByTen(String tenMonAn) throws SQLException {
        String sql = "SELECT maMonAn, tenMonAn, thongTinMon, trangThaiMonAn, giaTien, maLoai " +
                     "FROM MonAn WHERE tenMonAn = ?";  // Câu lệnh SQL tìm kiếm theo tên món ăn
        
        Connection con = null;
        PreparedStatement ps = null;
        
        
        ConnectDB connectDB = new ConnectDB();
        try {
            con = connectDB.connect();
        } catch (SQLException ex) {
            Logger.getLogger(MonAn_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            ps = con.prepareStatement(sql);
            // Set giá trị cho tham số trong câu truy vấn
            ps.setString(1, tenMonAn);

            // Thực thi truy vấn và nhận kết quả
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Tạo đối tượng MonAn và gán các giá trị từ ResultSet
                String maMonAn = rs.getString("maMonAn");
                String thongTinMon = rs.getString("thongTinMon");
                String trangThaiMonAn = rs.getString("trangThaiMonAn");
                double giaTien = rs.getDouble("giaTien");
                String maLoai = rs.getString("maLoai");
                LoaiMon loaiMon = getLoaiMonByMa(maLoai); // Implement to fetch LoaiMon
                
                // Trả về đối tượng MonAn
                return new MonAn(maMonAn, tenMonAn, thongTinMon, trangThaiMonAn, giaTien, loaiMon);
            }

        // Nếu không tìm thấy món ăn, trả về null
        return null;
    }
    public MonAn getMonAnByMa(String maMonAn) throws SQLException {
        String sql = "SELECT maMonAn, tenMonAn, thongTinMon, trangThaiMonAn, giaTien, maLoai " +
                     "FROM MonAn WHERE maMonAn = ?";  // Câu lệnh SQL tìm kiếm theo tên món ăn
        
        Connection con = null;
        PreparedStatement ps = null;
        
        
        ConnectDB connectDB = new ConnectDB();
        try {
            con = connectDB.connect();
        } catch (SQLException ex) {
            Logger.getLogger(MonAn_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            ps = con.prepareStatement(sql);
            // Set giá trị cho tham số trong câu truy vấn
            ps.setString(1, maMonAn);

            // Thực thi truy vấn và nhận kết quả
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Tạo đối tượng MonAn và gán các giá trị từ ResultSet
                String tenMonAn = rs.getString("tenMonAn");
                String thongTinMon = rs.getString("thongTinMon");
                String trangThaiMonAn = rs.getString("trangThaiMonAn");
                double giaTien = rs.getDouble("giaTien");
                String maLoai = rs.getString("maLoai");
                LoaiMon loaiMon = getLoaiMonByMa(maLoai); // Implement to fetch LoaiMon
                
                // Trả về đối tượng MonAn
                return new MonAn(maMonAn, tenMonAn, thongTinMon, trangThaiMonAn, giaTien, loaiMon);
            }

        // Nếu không tìm thấy món ăn, trả về null
        return null;
    }
    
    private void closeResources(ResultSet rs, PreparedStatement ps, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
