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
    
    public boolean login(String tenTK, String matKhau) {
		    String sql = "SELECT * FROM TaiKhoan WHERE tenTK = ? AND matKhau = ?";
		    Connection con = null;
		    PreparedStatement statement = null;
		    
		    try {
		        con = ConnectDB.getConnection();
		        if (con == null) {
		            ConnectDB.getInstance().connect(); // Thiết lập lại kết nối nếu nó null
		            con = ConnectDB.getConnection();
		        }
		        
		        statement = con.prepareStatement(sql);
		        statement.setString(1, tenTK);
		        statement.setString(2, matKhau);
		        
		        ResultSet rs = statement.executeQuery();
		        return rs.next();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (statement != null) statement.close();
		            if (con != null) con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return false;
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
    
    public void dangKy(String hoTen, String taiKhoan, String matKhau) throws SQLException {
        PreparedStatement pst = null;

        try {
            String maTaiKhoan = generateNewMaTaiKhoan();  // Lấy mã tài khoản mới
            String sql = "INSERT INTO TaiKhoan (maTK, tenTk, chucVu, matKhau) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, maTaiKhoan);
            pst.setString(2, taiKhoan);
            pst.setString(3, "Nhân viên");
            pst.setString(4, matKhau);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String generateNewMaTaiKhoan() throws SQLException {
        String query = "SELECT TOP 1 maTK FROM TaiKhoan ORDER BY maTK DESC";
        try (PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String lastId = rs.getString("maTK");
                int newId = Integer.parseInt(lastId.replace("TK", "").trim()) + 1;
                return String.format("TK%03d", newId); // Tạo mã mới với định dạng TKxxx
            } else {
                return "TK001"; // Trường hợp bảng chưa có dữ liệu
            }
        }
    }
    public TaiKhoan findTaiKhoanByTenTK(String tenTK) {
        String sql = "SELECT maTK, tenTK, chucVu, matKhau FROM TaiKhoan WHERE tenTK = ?";
        try {
            if (con == null || con.isClosed()) {
                // Nếu kết nối đã bị đóng, hãy mở lại hoặc lấy kết nối mới
                con = ConnectDB.getInstance().connect();
            }
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, tenTK); // Thiết lập giá trị cho tham số truy vấn
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        String maTK = rs.getString("maTK");
                        String tenTaiKhoan = rs.getString("tenTK");
                        String chucVu = rs.getString("chucVu");
                        String matKhau = rs.getString("matKhau");

                        // Tạo và trả về đối tượng TaiKhoan
                        return new TaiKhoan(maTK, tenTaiKhoan, chucVu, matKhau);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu kết nối không hợp lệ
        }
        
        return null; // Trả về null nếu không tìm thấy tài khoản
    }
    
    public TaiKhoan findTaiKhoanByMaTK(String maTK) {
        String sql = "SELECT maTK, tenTK, chucVu, matKhau FROM TaiKhoan WHERE maTK = ?";
        try {
            if (con == null || con.isClosed()) {
                // Nếu kết nối đã bị đóng, hãy mở lại hoặc lấy kết nối mới
                con = ConnectDB.getInstance().connect();
            }
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, maTK); // Thiết lập giá trị cho tham số truy vấn
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        String tenTaiKhoan = rs.getString("tenTK");
                        String chucVu = rs.getString("chucVu");
                        String matKhau = rs.getString("matKhau");

                        // Tạo và trả về đối tượng TaiKhoan
                        return new TaiKhoan(maTK, tenTaiKhoan, chucVu, matKhau);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu kết nối không hợp lệ
        }
        
        return null; // Trả về null nếu không tìm thấy tài khoản
    }
}