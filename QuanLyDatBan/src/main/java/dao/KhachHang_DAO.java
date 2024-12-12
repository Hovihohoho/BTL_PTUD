package dao;

import connectDB.ConnectDB;
import entity.KhachHang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class KhachHang_DAO {
    private Connection con;
//    private final Connection con = ConnectDB.getInstance().connect();
    public KhachHang_DAO() throws SQLException {
        // Lấy kết nối từ lớp DatabaseConnection
        con = ConnectDB.getInstance().connect();
    }
    // Thêm khách hàng mới vào cơ sở dữ liệu
        public boolean insertKhachHang(KhachHang khachHang) {
            String sql = "INSERT INTO KhachHang (maKH, tenKH, sDT, diaChi, gioiTinh) VALUES (?, ?, ?, ?, ?)";
            try (
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, khachHang.getMaKH());
                ps.setString(2, khachHang.getTenKH());
                ps.setString(3, khachHang.getSDT());
                ps.setString(4, khachHang.getDiaChi());
                ps.setString(5, khachHang.getGioiTinh());

                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        // Cập nhật thông tin khách hàng
        public boolean updateKhachHang(KhachHang khachHang) {
            String sql = "UPDATE KhachHang SET tenKH = ?, sDT = ?, diaChi = ?, gioiTinh = ? WHERE maKH = ?";
            try (
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, khachHang.getTenKH());
                ps.setString(2, khachHang.getSDT());
                ps.setString(3, khachHang.getDiaChi());
                ps.setString(4, khachHang.getGioiTinh());
                ps.setString(5, khachHang.getMaKH());

                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        // Lấy danh sách tất cả khách hàng
        public List<KhachHang> getAllKhachHang() {
            List<KhachHang> listKhachHang = new ArrayList<>();
            String sql = "SELECT maKH, tenKH, sDT, diaChi, gioiTinh FROM KhachHang";

            try (
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    String maKH = rs.getString("maKH");
                    String tenKH = rs.getString("tenKH");
                    String sDT = rs.getString("sDT");
                    String diaChi = rs.getString("diaChi");
                    String gioiTinh = rs.getString("gioiTinh");

                    listKhachHang.add(new KhachHang(maKH, tenKH, sDT, diaChi, gioiTinh));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return listKhachHang;
        }

        // Xóa khách hàng khỏi cơ sở dữ liệu
        public boolean deleteKhachHang(String maKH) {
       // Kết nối tới cơ sở dữ liệu
    String checkYeuCauQuery = "SELECT COUNT(*) FROM YeuCauKhachHang WHERE maKH = ?";
    String deleteYeuCauQuery = "DELETE FROM YeuCauKhachHang WHERE maKH = ?";
    String deleteKhachHangQuery = "DELETE FROM KhachHang WHERE maKH = ?";

    try (Connection conn = ConnectDB.getInstance().connect()) {
        
        // Bước 1: Kiểm tra xem có yêu cầu nào liên quan đến khách hàng này không
        try (PreparedStatement psCheck = conn.prepareStatement(checkYeuCauQuery)) {
            psCheck.setString(1, maKH);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Nếu có yêu cầu, không thể xóa khách hàng
                JOptionPane.showMessageDialog(null, "Không thể xóa khách hàng vì còn yêu cầu liên quan.");
                return false;
            }
        }

        // Bước 2: Xóa các yêu cầu liên quan đến khách hàng này
        try (PreparedStatement psDeleteYeuCau = conn.prepareStatement(deleteYeuCauQuery)) {
            psDeleteYeuCau.setString(1, maKH);
            psDeleteYeuCau.executeUpdate();
        }

        // Bước 3: Xóa khách hàng
        try (PreparedStatement psDeleteKhachHang = conn.prepareStatement(deleteKhachHangQuery)) {
            psDeleteKhachHang.setString(1, maKH);
            int rowsAffected = psDeleteKhachHang.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công!");
                return true; // Thành công
            } else {
                JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại!");
                return false; // Thất bại
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xóa khách hàng!");
        return false; // Thất bại nếu có lỗi
    }}

        // Tìm khách hàng theo mã khách hàng
        public KhachHang findKhachHangByMaKH(String maKH) {
            String sql = "SELECT maKH, tenKH, sDT, diaChi, gioiTinh FROM KhachHang WHERE maKH = ?";

            try (
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, maKH);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String tenKH = rs.getString("tenKH");
                    String sDT = rs.getString("sDT");
                    String diaChi = rs.getString("diaChi");
                    String gioiTinh = rs.getString("gioiTinh");

                    return new KhachHang(maKH, tenKH, sDT, diaChi, gioiTinh);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        public KhachHang findKhachHangBySDT(String sDT) {
            String sql = "SELECT maKH, tenKH, sDT, diaChi, gioiTinh FROM KhachHang WHERE sDT = ?";
            
            // Kiểm tra xem kết nối có đang đóng hay không
            try {
                if (con == null || con.isClosed()) {
                    // Nếu kết nối đã bị đóng, hãy mở lại hoặc lấy kết nối mới
                    con = ConnectDB.getInstance().connect();
                }
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, sDT); // Sử dụng chỉ mục 1 vì đây là tham số duy nhất

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        String maKH = rs.getString("maKH");
                        String tenKH = rs.getString("tenKH");
                        String diaChi = rs.getString("diaChi");
                        String gioiTinh = rs.getString("gioiTinh");

                        return new KhachHang(maKH, tenKH, sDT, diaChi, gioiTinh);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu kết nối không hợp lệ
        }
            return null; // Trả về null nếu không tìm thấy khách hàng
        }
        
        public KhachHang getKhachHangByMa(String maKH) {
            String sql = "SELECT maKH, tenKH, sDT, diaChi, gioiTinh FROM KhachHang WHERE maKH = ?";
            KhachHang khachHang = null;
            
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                // Đặt giá trị cho tham số maKH
                ps.setString(1, maKH);

                // Thực thi câu lệnh SQL
                ResultSet rs = ps.executeQuery();

                // Nếu có kết quả trả về, khởi tạo đối tượng KhachHang
                if (rs.next()) {
                    String tenKH = rs.getString("tenKH");
                    String sdt = rs.getString("sDT");
                    String diaChi = rs.getString("diaChi");
                    String gioiTinh = rs.getString("gioiTinh");

                    // Tạo đối tượng KhachHang với thông tin lấy được
                    khachHang = new KhachHang(maKH, tenKH, sdt, diaChi, gioiTinh);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // In lỗi ra nếu có ngoại lệ
            }

            return khachHang; // Trả về đối tượng KhachHang, hoặc null nếu không tìm thấy
        }

        public boolean checkMaKhachHangExists(String maKH) {
        String sql = "SELECT COUNT(*) FROM KhachHang WHERE maKH = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu có bản ghi nào đó, trả về true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean suaKhachHang(String maKH, String tenKH, String sdt, String diaChi, String gioiTinh) {
        String query = "UPDATE KhachHang SET tenKH = ?, sdt = ?, diaChi = ?, gioiTinh = ? WHERE maKH = ?";
        try (Connection conn = ConnectDB.getInstance().connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            // Thiết lập các tham số
            ps.setString(1, tenKH);
            ps.setString(2, sdt);
            ps.setString(3, diaChi);
            ps.setString(4, gioiTinh);
            ps.setString(5, maKH);

            // Thực thi truy vấn
            int affectedRows = ps.executeUpdate();

            // Kiểm tra xem có dòng nào bị ảnh hưởng
            return affectedRows > 0; // Trả về true nếu thành công
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console
            return false; // Trả về false nếu xảy ra lỗi
        }
    }       
}
