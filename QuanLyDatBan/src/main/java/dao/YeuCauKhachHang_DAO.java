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

    // Thêm yêu cầu khách hàng mới vào cơ sở dữ liệu
    public boolean insertYeuCauKhachHang(YeuCauKhachHang yeuCauKhachHang) {
        String sql = "INSERT INTO yeu_cau_khach_hang (maYeuCau, maKH, noiDungYeuCau) VALUES (?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, yeuCauKhachHang.getMaYeuCau());
            ps.setString(2, yeuCauKhachHang.getKh().getMaKH());
            ps.setString(3, yeuCauKhachHang.getNoiDungYeuCau());

            return ps.executeUpdate() > 0;  // Return true if insert is successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(ps, con);  // Close resources
        }
        return false;
    }

    // Lấy danh sách tất cả yêu cầu khách hàng từ cơ sở dữ liệu
    public List<YeuCauKhachHang> getAllYeuCauKhachHang() {
        List<YeuCauKhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM yeu_cau_khach_hang";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String maYeuCau = rs.getString("maYeuCau");
                String maKH = rs.getString("maKH");
                String noiDungYeuCau = rs.getString("noiDungYeuCau");

                // Lấy thông tin khách hàng liên quan từ bảng KhachHang
                KhachHang_DAO khachHangDAO = new KhachHang_DAO();
                KhachHang khachHang = khachHangDAO.findKhachHangByMaKH(maKH);

                YeuCauKhachHang yeuCauKhachHang = new YeuCauKhachHang(maYeuCau, khachHang, noiDungYeuCau);
                list.add(yeuCauKhachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, ps, con);  // Close resources
        }
        return list;
    }

    // Cập nhật yêu cầu khách hàng
    public boolean updateYeuCauKhachHang(YeuCauKhachHang yeuCauKhachHang) {
        String sql = "UPDATE yeu_cau_khach_hang SET maKH = ?, noiDungYeuCau = ? WHERE maYeuCau = ?";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, yeuCauKhachHang.getKh().getMaKH());
            ps.setString(2, yeuCauKhachHang.getNoiDungYeuCau());
            ps.setString(3, yeuCauKhachHang.getMaYeuCau());

            return ps.executeUpdate() > 0;  // Return true if update is successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(ps, con);  // Close resources
        }
        return false;
    }

    // Xóa yêu cầu khách hàng khỏi cơ sở dữ liệu
    public boolean deleteYeuCauKhachHang(String maYeuCau) {
        String sql = "DELETE FROM yeu_cau_khach_hang WHERE maYeuCau = ?";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, maYeuCau);

            return ps.executeUpdate() > 0;  // Return true if deletion is successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(ps, con);  // Close resources
        }
        return false;
    }

    // Tìm yêu cầu khách hàng theo maYeuCau
    public YeuCauKhachHang findYeuCauKhachHangByMaYeuCau(String maYeuCau) {
        String sql = "SELECT * FROM yeu_cau_khach_hang WHERE maYeuCau = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maYeuCau);

            rs = ps.executeQuery();

            if (rs.next()) {
                String maKH = rs.getString("maKH");
                String noiDungYeuCau = rs.getString("noiDungYeuCau");

                // Lấy thông tin khách hàng liên quan từ bảng KhachHang
                KhachHang_DAO khachHangDAO = new KhachHang_DAO();
                KhachHang khachHang = khachHangDAO.findKhachHangByMaKH(maKH);

                return new YeuCauKhachHang(maYeuCau, khachHang, noiDungYeuCau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, ps, con);  // Close resources
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
