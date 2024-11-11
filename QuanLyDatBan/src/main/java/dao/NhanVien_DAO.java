package dao;

import java.sql.*;

import java.util.*;
import connectDB.ConnectDB;
import entity.CaLamViec;
import entity.NhanVien;
import entity.TaiKhoan;
import java.time.LocalDate;

public class NhanVien_DAO {
    private Connection con;

    public NhanVien_DAO() throws SQLException {
        con = ConnectDB.getInstance().getConnection();
    }
    public NhanVien getNhanVienByMa(String maNV) {
        // Câu lệnh SQL để truy vấn thông tin nhân viên từ bảng NhanVien
        String sql = "SELECT maNV, maTK, maCa, tenNV, sdt, email, ngayVaoLam FROM NhanVien WHERE maNV = ?";
        
        // Kết nối cơ sở dữ liệu và truy vấn
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, maNV); // Thiết lập giá trị cho tham số trong câu lệnh SQL
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Đọc dữ liệu từ ResultSet
                    String tenNV = rs.getString("tenNV");
                    String sDT = rs.getString("sdt");
                    String maCa = rs.getString("maCa");
                    String email = rs.getString("email");
                    LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                    String maTK = rs.getString("maTK"); // Lấy mã tài khoản
                    
                    // Tạo đối tượng TaiKhoan từ mã tài khoản (maTK)
                    TaiKhoan maTaiKhoan = getTaiKhoanByMa(maTK);
                    
                    // Tạo đối tượng CaLamViec từ mã ca làm việc (nếu có)
                    CaLamViec maCaLamViec = getCaLamViecByMa(maCa);
                    
                    // Tạo đối tượng NhanVien với constructor đầy đủ
                    NhanVien nhanVien = new NhanVien(maNV, maTaiKhoan, maCaLamViec, tenNV, sDT, email, ngayVaoLam);
                    
                    // Trả về đối tượng NhanVien
                    return nhanVien;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Nếu không tìm thấy nhân viên, trả về null
        return null;
    }

    // Phương thức lấy thông tin tài khoản từ bảng TaiKhoan
    private TaiKhoan getTaiKhoanByMa(String maTK) {
        String sql = "SELECT * FROM TaiKhoan WHERE maTK = ?";
        
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, maTK); // Thiết lập giá trị cho tham số trong câu lệnh SQL
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Tạo đối tượng TaiKhoan từ kết quả truy vấn
                    String tenTK = rs.getString("tenTK");
                    String chucVu = rs.getString("chucVu");
                    String matKhau = rs.getString("matKhau");
                    // Khởi tạo đối tượng TaiKhoan (giả sử constructor có tên tài khoản và mật khẩu)
                    return new TaiKhoan(maTK, tenTK, chucVu, matKhau);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    // Phương thức lấy thông tin ca làm việc từ bảng CaLamViec
    private CaLamViec getCaLamViecByMa(String maCa) {
        CaLamViec_DAO calamviecDAO = new CaLamViec_DAO();
        CaLamViec calam = calamviecDAO.getCaLamViecByMa(maCa);
        return calam;
    }

    public List<NhanVien> getAllNhanVien() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public NhanVien findNhanVienByTaiKhoan(TaiKhoan taiKhoan) {
        String sql = "SELECT maNV, tenNV, sDT, email, ngayVaoLam, maCa FROM NhanVien WHERE maTK = ?";
        try {
            if (con == null || con.isClosed()) {
                // Nếu kết nối đã bị đóng, hãy mở lại hoặc lấy kết nối mới
                con = ConnectDB.getInstance().connect();
            }
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, taiKhoan.getMaTK()); // Thiết lập giá trị cho tham số truy vấn

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        String maNV = rs.getString("maNV");
                        String tenNV = rs.getString("tenNV");
                        String sDT = rs.getString("sDT");
                        String email = rs.getString("email");
                        LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                        String maCa = rs.getString("maCa");
                        CaLamViec_DAO caDAO = new CaLamViec_DAO();
                        CaLamViec Calv = caDAO.getCaLamViecByMa(maCa); // giả sử CaLamViec là Enum

                        // Trả về đối tượng NhanVien
                        return new NhanVien(maNV, taiKhoan, Calv, tenNV, sDT, email, ngayVaoLam);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();  // In lỗi nếu kết nối không hợp lệ
        }
        return null; // Trả về null nếu không tìm thấy nhân viên
    }
}
