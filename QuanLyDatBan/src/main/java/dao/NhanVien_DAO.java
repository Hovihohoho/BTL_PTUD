package dao;

import java.sql.*;

import java.util.*;
import connectDB.ConnectDB;
import entity.CaLamViec;
import entity.NhanVien;
import entity.TaiKhoan;
import java.time.LocalDate;
import java.time.LocalTime;

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
        CaLamViec calam = calamviecDAO.timCaLam(maCa);
        return calam;
    }

    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> listNhanVien = new ArrayList<>();
        
        try {
            // Kết nối đến SQL Server qua file connectDB
            Connection conn = ConnectDB.getConnection();
            
            // Cập nhật câu truy vấn để lấy đủ thông tin cho TaiKhoan và CaLamViec
            String sql = "SELECT nv.maNV, nv.tenNV, nv.sdt, nv.email, nv.ngayVaoLam, " +
                         "tk.maTK, tk.tenTK, tk.chucVu, tk.matKhau, " +
                         "clv.maCa, clv.buoiLam, clv.gioBatDau, clv.gioKetThuc " +
                         "FROM NhanVien nv " +
                         "JOIN TaiKhoan tk ON nv.maTK = tk.maTK " +
                         "JOIN CaLamViec clv ON nv.maCa = clv.maCa";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            // Thực thi câu truy vấn
            ResultSet rs = ps.executeQuery();
            
            // Xử lý kết quả
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                
                // Thông tin tài khoản
                String maTK = rs.getString("maTK");
                String tenTK = rs.getString("tenTK");
                String chucVu = rs.getString("chucVu");
                String matKhau = rs.getString("matKhau");
                TaiKhoan taiKhoan = new TaiKhoan(maTK, tenTK, chucVu, matKhau);

                // Thông tin ca làm việc
                String maCa = rs.getString("maCa");
                String buoiLam = rs.getString("buoiLam");
                String gioBatDauStr = rs.getString("gioBatDau");
                String gioKetThucStr = rs.getString("gioKetThuc");

                // Chuyển đổi chuỗi thành LocalTime
                LocalTime gioBatDau = LocalTime.parse(gioBatDauStr);
                LocalTime gioKetThuc = LocalTime.parse(gioKetThucStr);

                CaLamViec caLamViec = new CaLamViec(maCa, buoiLam, gioBatDau, gioKetThuc);
                
                // Thêm nhân viên vào danh sách
                listNhanVien.add(new NhanVien(maNV, taiKhoan, caLamViec, tenNV, sdt, email, ngayVaoLam));
            }
            
            // Đóng kết nối và tài nguyên
            rs.close();
            ps.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listNhanVien;
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
                        CaLamViec Calv = caDAO.timCaLam(maCa); // giả sử CaLamViec là Enum

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
    public boolean updateNhanVien(NhanVien nhanVien) {
        try {
        if (con == null || con.isClosed()) {
                // Nếu kết nối đã bị đóng, hãy mở lại hoặc lấy kết nối mới
                con = ConnectDB.getInstance().connect();
            }
                String sql = "UPDATE NhanVien SET maCa = ?, tenNV = ?, sDT = ?, email = ?, ngayVaoLam = ? WHERE maNV = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, nhanVien.getMaCa().getMaCa().trim());
                    ps.setString(2, nhanVien.getTenNV());
                    ps.setString(3, nhanVien.getsDT());
                    ps.setString(4, nhanVien.getEmail());
                    ps.setDate(5, java.sql.Date.valueOf(nhanVien.getNgayVaoLam()));
                    ps.setString(6, nhanVien.getMaNV());

                    return ps.executeUpdate() > 0;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();  // In lỗi nếu kết nối không hợp lệ
        }
        return false;
}
}
