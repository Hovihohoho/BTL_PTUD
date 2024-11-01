package dao;


import entity.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;

public class TaiKhoan_DAO {
    private Connection con;

    public TaiKhoan_DAO() throws SQLException {
        con = ConnectDB.getInstance().getConnection();
    }

    public ResultSet getResultSet(String StoreName) throws Exception {
        ResultSet rs = null;
        try {
            String callStore;
            callStore = "{Call " + StoreName + "}";
            CallableStatement cs = this.con.prepareCall(callStore);
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }

    public List<TaiKhoan> getLS() {
        List<TaiKhoan> ds = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT tenTK, matKhau FROM TaiKhoan";  // Câu lệnh SQL chỉ lấy tên tài khoản và mật khẩu
            pstmt = con.prepareStatement(sql);                       // Chuẩn bị câu lệnh SQL
            rs = pstmt.executeQuery();                                // Thực thi câu lệnh và nhận kết quả

            // Duyệt qua kết quả và thêm vào danh sách
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString("tenTK"), rs.getString("matKhau"));
                ds.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng các tài nguyên
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ds;  // Trả về danh sách tài khoản (chỉ có tên tài khoản và mật khẩu)
    }

    public boolean addTaiKhoan(TaiKhoan taiKhoan) {
        try {
            PreparedStatement nvAdd = con.prepareStatement("INSERT INTO TAIKHOAN ([tenTK],[matKhau]) VALUES(?,?)");
            nvAdd.setString(1, taiKhoan.gettenTK());
            nvAdd.setString(2, taiKhoan.getmatKhau());
            int n = nvAdd.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteTK(String maTK) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from TAIKHOAN where MATK = ?");
            stmt.setString(1, maTK);
            int n = stmt.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public TaiKhoan TimKiemMa(String tenTK, String matKhau) {
        TaiKhoan lt = null;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from TAIKHOAN where tenTK = ? and matKhau = ?");
            stmt.setString(1, tenTK);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lt = new TaiKhoan(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lt;
    }
    public boolean login(String tenTaiKhoan, String matKhau) {
        String sql = "SELECT * FROM TaiKhoan WHERE tenTK = ? AND matKhau = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, tenTaiKhoan);
            pstmt.setString(2, matKhau);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();  // Nếu tìm thấy kết quả, tài khoản và mật khẩu đúng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Nếu không tìm thấy kết quả, tài khoản hoặc mật khẩu sai
    }
    public void dangKy(String hoTen, String taiKhoan, String matKhau) throws SQLException {
        Connection conn = ConnectDB.getConnection();
        PreparedStatement pst = null;

        try {
            String sql = "INSERT INTO TaiKhoan (maTK, tenTk, chucVu, matKhau) VALUES (?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "TK000");
            pst.setString(2, taiKhoan);
            pst.setString(3, "Nhân viên");
            pst.setString(4, matKhau);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng PreparedStatement để tránh rò rỉ tài nguyên
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
