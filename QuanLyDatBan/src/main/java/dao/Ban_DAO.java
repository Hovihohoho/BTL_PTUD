/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.LoaiBan;
import entity.NhanVien;
import gui.card.Card_Ban;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Ban_DAO {
    private static final String GET_ALL_BAN = "SELECT * FROM Ban";
    private static final String GET_BAN_BY_MABAN = "SELECT * FROM Ban WHERE maBan = ?";
    private static final String INSERT_BAN = "INSERT INTO Ban (maBan, maLoai, trangThaiBan, soLuongGhe, viTri) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_BAN = "UPDATE Ban SET maLoai = ?, trangThaiBan = ?, soLuongGhe = ?, viTri = ? WHERE maBan = ?";
    private static final String DELETE_BAN = "DELETE FROM Ban WHERE maBan = ?";
    public List<Card_Ban> taoDanhSachTheoMa(String maBan, NhanVien nhanvien) {
        List<Card_Ban> danhSachCardBan = new ArrayList<>();
        String sql = "SELECT maBan, maLoai, trangThaiBan, soLuongGhe, viTri FROM Ban WHERE maBan = ?";

        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Thiết lập giá trị của tham số maBan trong câu truy vấn
            ps.setString(1, maBan);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy dữ liệu từ ResultSet
                    String maBanResult = rs.getString("maBan");
                    LoaiBan loaiBan = new LoaiBan(rs.getString("maLoai"));
                    String trangThaiBan = rs.getString("trangThaiBan");
                    int soLuongGhe = rs.getInt("soLuongGhe");
                    String viTri = rs.getString("viTri");

                    // Tạo đối tượng Ban và sau đó là Card_Ban
                    Ban ban = new Ban(maBanResult, loaiBan, trangThaiBan, soLuongGhe, viTri);
                    Card_Ban cardBan = new Card_Ban();

                    // Thiết lập dữ liệu cho mỗi Card_Ban
                    cardBan.setBanData(ban, nhanvien);

                    // Thêm Card_Ban vào danh sách
                    danhSachCardBan.add(cardBan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachCardBan;
    }
    
    public List<Ban> layDanhSachBan() {
        List<Ban> danhSachBan = new ArrayList<>();
        String sql = "SELECT maBan, maLoai, trangThaiBan, soLuongGhe, viTri FROM Ban";

        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maBan = rs.getString("maBan");
                LoaiBan loaiBan = new LoaiBan(rs.getString("maLoai"));
                String trangThaiBan = rs.getString("trangThaiBan");
                int soLuongGhe = rs.getInt("soLuongGhe");
                String viTri = rs.getString("viTri");

                Ban ban = new Ban(maBan, loaiBan, trangThaiBan, soLuongGhe, viTri);
                danhSachBan.add(ban);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachBan;
    }
    
    public List<Ban> layDanhSachBanTheoTang(String tang) {
        List<Ban> danhSachBan = new ArrayList<>();
        String sql = "SELECT maBan, maLoai, trangThaiBan, soLuongGhe, viTri FROM Ban WHERE viTri = ?";

        try (Connection con = ConnectDB.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Thiết lập giá trị cho tham số tầng trong câu truy vấn
            ps.setString(1, tang);

            // Thực thi truy vấn và lấy kết quả
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maBan = rs.getString("maBan");
                    LoaiBan loaiBan = new LoaiBan(rs.getString("maLoai"));
                    String trangThaiBan = rs.getString("trangThaiBan");
                    int soLuongGhe = rs.getInt("soLuongGhe");
                    String viTri = rs.getString("viTri");

                    // Tạo đối tượng Ban từ dữ liệu và thêm vào danh sách
                    Ban ban = new Ban(maBan, loaiBan, trangThaiBan, soLuongGhe, viTri);
                    danhSachBan.add(ban);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachBan;
    }
    
    public List<Ban> layDanhSachBanTheoTangVaSoLuongGhe(String tang, int soLuongGhe) {
        // Lấy danh sách bàn theo tầng trước
        List<Ban> danhSachBanTheoTang = layDanhSachBanTheoTang(tang);

        // Lọc danh sách theo số lượng ghế
        List<Ban> danhSachBanTheoSoLuongGhe = new ArrayList<>();
        for (Ban ban : danhSachBanTheoTang) {
            if (ban.getSoGhe()== soLuongGhe) {
                danhSachBanTheoSoLuongGhe.add(ban);
            }
        }

        return danhSachBanTheoSoLuongGhe;
    }
    public List<Card_Ban> taoDanhSachTangvaGhe(String tang, int ghe, NhanVien nhanvien) {
        List<Card_Ban> danhSachCardBan = new ArrayList<>();
        List<Ban> danhSachBan = layDanhSachBanTheoTangVaSoLuongGhe(tang, ghe); // Lấy dữ liệu từ cơ sở dữ liệu

        for (Ban ban : danhSachBan) {
            Card_Ban cardBan = new Card_Ban();

            // Thiết lập dữ liệu cho mỗi Card_Ban
            cardBan.setBanData(ban, nhanvien);
            
            danhSachCardBan.add(cardBan); // Thêm vào danh sách card
        }

        return danhSachCardBan;
    }
    
    public List<Ban> layDanhSachBanTheoTangVaTrangThai(String tang, String trangthai) {
        // Lấy danh sách bàn theo tầng trước
        List<Ban> danhSachBanTheoTang = layDanhSachBanTheoTang(tang);

        // Lọc danh sách theo số lượng ghế
        List<Ban> danhSachBanTheoSoLuongGhe = new ArrayList<>();
        for (Ban ban : danhSachBanTheoTang) {
            if (ban.getTrangThaiBan().equalsIgnoreCase(trangthai)) {
                danhSachBanTheoSoLuongGhe.add(ban);
            }
        }

        return danhSachBanTheoSoLuongGhe;
    }
    public List<Card_Ban> taoDanhSachTangvaTrangThai(String tang, String trangthai, NhanVien nhanvien) {
        List<Card_Ban> danhSachCardBan = new ArrayList<>();
        List<Ban> danhSachBan = layDanhSachBanTheoTangVaTrangThai(tang, trangthai); // Lấy dữ liệu từ cơ sở dữ liệu

        for (Ban ban : danhSachBan) {
            Card_Ban cardBan = new Card_Ban();

            // Thiết lập dữ liệu cho mỗi Card_Ban
            cardBan.setBanData(ban, nhanvien);
            
            danhSachCardBan.add(cardBan); // Thêm vào danh sách card
        }

        return danhSachCardBan;
    }
    
    public List<Ban> layDanhSachBanTheoTangGheVaTrangThai(String tang, int ghe, String trangthai) {
        // Lấy danh sách bàn theo tầng trước
        List<Ban> danhSachBanTheoTang = layDanhSachBanTheoTangVaTrangThai(tang, trangthai);

        // Lọc danh sách theo số lượng ghế
        List<Ban> danhSachBan = new ArrayList<>();
        for (Ban ban : danhSachBanTheoTang) {
            if (ban.getSoGhe()==ghe) {
                danhSachBan.add(ban);
            }
        }

        return danhSachBan;
    }
    public List<Card_Ban> taoDanhSachTangGheVaTrangThai(String tang, int ghe, String trangthai, NhanVien nhanvien) {
        List<Card_Ban> danhSachCardBan = new ArrayList<>();
        List<Ban> danhSachBan = layDanhSachBanTheoTangGheVaTrangThai(tang, ghe, trangthai); // Lấy dữ liệu từ cơ sở dữ liệu

        for (Ban ban : danhSachBan) {
            Card_Ban cardBan = new Card_Ban();

            // Thiết lập dữ liệu cho mỗi Card_Ban
            cardBan.setBanData(ban, nhanvien);
            
            danhSachCardBan.add(cardBan); // Thêm vào danh sách card
        }

        return danhSachCardBan;
    }
    
    public List<Card_Ban> taoDanhSachTang(String tang, NhanVien nhanvien) {
        List<Card_Ban> danhSachCardBan = new ArrayList<>();
        List<Ban> danhSachBan = layDanhSachBanTheoTang(tang); // Lấy dữ liệu từ cơ sở dữ liệu

        for (Ban ban : danhSachBan) {
            Card_Ban cardBan = new Card_Ban();

            // Thiết lập dữ liệu cho mỗi Card_Ban
            cardBan.setBanData(ban, nhanvien);
            
            danhSachCardBan.add(cardBan); // Thêm vào danh sách card
        }

        return danhSachCardBan;
    }
    
    public String getTang(String maBan){
        List<Ban> danhSachBan = layDanhSachBan();
        
        for (Ban ban : danhSachBan) {
            if(ban.getMaBan().equalsIgnoreCase(maBan)){
                return ban.getViTri();
            }
        }
        return null;
    }
    public static List<Ban> getAllBan() throws SQLException {
        List<Ban> bans = new ArrayList<>();
        try (Connection conn = ConnectDB.getInstance().connect();
             PreparedStatement stmt = conn.prepareStatement(GET_ALL_BAN)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maBan = rs.getString("maBan");
                LoaiBan loaiBan = new LoaiBan(rs.getString("maLoai"));
                String trangThaiBan = rs.getString("trangThaiBan");
                int soLuongGhe = rs.getInt("soLuongGhe");
                String viTri = rs.getString("viTri");

                Ban ban = new Ban(maBan, loaiBan, trangThaiBan, soLuongGhe, viTri);
                bans.add(ban);
            }
        }
        catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách bàn: " + e.getMessage());
        }
        return bans;
    }
    public static void loadBanToTable(JTable jTable) throws SQLException {
        try{
            List<Ban> bans = getAllBan(); // Lấy danh sách tất cả các bản ghi từ SQL Server
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            model.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng

            // Thêm dữ liệu vào bảng
            for (Ban ban : bans) {
                LoaiBan loaiBan = ban.getLoaiBan(); // Lấy loại bàn từ đối tượng Ban
                model.addRow(new Object[]{
                    ban.getMaBan(),
                    loaiBan.getMaLoai(), // Sử dụng giá trị của loại bàn
                    ban.getTrangThaiBan(),
                    ban.getSoGhe(),
                    ban.getViTri()
                });
            }
        }
        catch (Exception e) {
            System.out.println("Lỗi khi tải dữ liệu vào bảng: " + e.getMessage());
        }
       }
    public static Ban getBanByMaBan(String maBan) throws SQLException {
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_BAN_BY_MABAN)) {
            stmt.setString(1, maBan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                
                LoaiBan loaiBan = new LoaiBan(rs.getString("maLoai"));
                String trangThaiBan = rs.getString("trangThaiBan");
                int soLuongGhe = rs.getInt("soLuongGhe");
                String viTri = rs.getString("viTri");

                Ban ban = new Ban(maBan, loaiBan, trangThaiBan, soLuongGhe, viTri);
                return ban;
            }
        }
        return null;
    }

    public static void addBan(Ban ban) throws SQLException {
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_BAN)) {
            LoaiBan loaiBan = ban.getLoaiBan();
            stmt.setString(1, ban.getMaBan());
            stmt.setString(2, loaiBan.getMaLoai());
            stmt.setString(3, ban.getTrangThaiBan());
            stmt.setInt(4, ban.getSoGhe());
            stmt.setString(5, ban.getViTri());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Thêm thông báo lỗi nếu cần
            throw new SQLException("Không thể thêm bàn: " + e.getMessage());
        }
    }

    public static void updateBan(Ban ban) throws SQLException {
        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(UPDATE_BAN)) {
                LoaiBan loaiBan = ban.getLoaiBan(); 
                stmt.setString(1, loaiBan.getMaLoai());
                stmt.setString(2, ban.getTrangThaiBan());
                stmt.setInt(3, ban.getSoGhe());
                stmt.setString(4, ban.getViTri());
                stmt.setString(5, ban.getMaBan());
                stmt.executeUpdate();
            }
        }

    public static void deleteBan(String maBan) throws SQLException {
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_BAN)) {
            stmt.setString(1, maBan);
            stmt.executeUpdate();
        }
    }
    
    public static List<Ban> filterBans(String maLoai, String trangThai) throws SQLException {
        List<Ban> bans = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Ban WHERE 1=1");

        // Kiểm tra điều kiện mã bàn
        if (maLoai != null && !maLoai.isEmpty()) {
            sql.append(" AND maLoai = ?");
        }
        // Kiểm tra điều kiện tình trạng
        if (trangThai != null && !trangThai.isEmpty()) {
            sql.append(" AND trangThaiBan = ?");
        }

        try (Connection conn = ConnectDB.getInstance().connect();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (maLoai != null && !maLoai.isEmpty()) {
                stmt.setString(index++, maLoai);
            }
            if (trangThai != null && !trangThai.isEmpty()) {
                stmt.setString(index++, trangThai);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                String maBan = rs.getString("maBan");
                LoaiBan loaiBan = new LoaiBan(maLoai);
                String trangThaiBan = rs.getString("trangThaiBan");
                int soLuongGhe = rs.getInt("soLuongGhe");
                String viTri = rs.getString("viTri");

                Ban ban = new Ban(maBan, loaiBan, trangThaiBan, soLuongGhe, viTri);
                bans.add(ban);
            }
        }
        return bans;
    }
    public static List<Ban> getdsBanTheoMa(String maLoai) throws SQLException {
        List<Ban> bans = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Ban WHERE 1=1");

        // Kiểm tra điều kiện mã bàn
        if (maLoai != null && !maLoai.isEmpty()) {
            sql.append(" AND maLoai = ?");
        }

        try (Connection conn = ConnectDB.getInstance().connect();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (maLoai != null && !maLoai.isEmpty()) {
                stmt.setString(index++, maLoai);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                String maBan = rs.getString("maBan");
                LoaiBan loaiBan = new LoaiBan(maLoai);
                String trangThaiBan = rs.getString("trangThaiBan");
                int soLuongGhe = rs.getInt("soLuongGhe");
                String viTri = rs.getString("viTri");

                Ban ban = new Ban(maBan, loaiBan, trangThaiBan, soLuongGhe, viTri);
                bans.add(ban);
            }
        }
        return bans;
    }
    
    public void updateTrangThaiBan(Ban ban) {
        String sql = "UPDATE Ban SET trangThaiBan = ? WHERE maBan = ?";

        try (Connection conn = ConnectDB.getInstance().connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ban.getTrangThaiBan());
            ps.setString(2, ban.getMaBan());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
