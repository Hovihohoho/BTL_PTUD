package dao;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.File;
import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import connectDB.ConnectDB;
import static dao.Ban_DAO.getBanByMaBan;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Ban;
import entity.ChiTietYeuCau;
import entity.MonAn;
import entity.YeuCauKhachHang;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HoaDon_DAO {
    private static final String INSERT_HOA_DON_SQL = "INSERT INTO HoaDon (maHD, maYeuCau, maNV, maBan, soLuongKhach, thoiGianTao, ngayDatBan, trangThaiHoaDon) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    Connection conn = null;
    PreparedStatement pstmt = null;
    private TaoMaHoaDon tmhd = new TaoMaHoaDon();
    
    NhanVien nhanVien;
    YeuCauKhachHang_DAO yeuCauKhachHang_DAO;
    YeuCauKhachHang yeuCau;
    Ban_DAO banDAO;
    Ban ban;
    
    public String getMaHDMoi(NhanVien nhanvien){
        return tmhd.generateMaHoaDon(nhanvien);
    }
    
    public boolean luuHoaDon(String maHD, String maYeuCau, String maNV, String maBan, int soLuongKhach, Date thoiGianTao, Date ngayDatBan, String trangThaiHoaDon) throws SQLException {
        
        try {
            conn = ConnectDB.getInstance().connect();
            pstmt = conn.prepareStatement(INSERT_HOA_DON_SQL);

            pstmt.setString(1, maHD);
            pstmt.setString(2, maYeuCau);
            pstmt.setString(3, maNV);
            pstmt.setString(4, maBan);
            pstmt.setInt(5, soLuongKhach);
            pstmt.setDate(6, thoiGianTao);
            pstmt.setDate(7, ngayDatBan);
            pstmt.setString(8, trangThaiHoaDon);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }
    
    public int getLastMaHDFromDB() {
        int lastMaHD = 0;

        String sql = "SELECT MAX(CAST(SUBSTRING(maHD, 3, 4) AS INT)) FROM HoaDon";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                lastMaHD = rs.getInt(1); // Lấy giá trị từ cột đầu tiên
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastMaHD;
    }
    
    public double getTongTienHoaDon(HoaDon hoaDon) {
        double tongTien = 0;  // Khởi tạo biến tổng tiền

        try {
            conn = ConnectDB.getInstance().connect();

            // Truy vấn cơ sở dữ liệu để tính tổng tiền
            String sql = "SELECT " +
                         "(SELECT SUM(ctyc.soLuong * ma.giaTien) " +
                         " FROM ChiTietYeuCau ctyc " +
                         " JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn " +
                         " WHERE ctyc.maYeuCau = hd.maYeuCau) AS tongTien " +
                         "FROM HoaDon hd " +
                         "WHERE hd.maHD = ?";  // Lọc theo mã hóa đơn

            // Chuẩn bị statement và thực thi truy vấn
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, hoaDon.getMaHD());  // Gán mã hóa đơn vào truy vấn
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        tongTien = rs.getDouble("tongTien");  // Lấy giá trị tổng tiền từ truy vấn
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tongTien;  // Trả về tổng tiền
    }

    
    public List<HoaDon> getAllHoaDon() throws SQLException {
        conn = ConnectDB.getInstance().connect();
        List<HoaDon> dsHoaDon = new ArrayList<>();
        String sql =    "SELECT hd.maHD, hd.thoiGianTao, hd.ngayDatBan, hd.soLuongKhach, hd.trangThaiHoaDon, " +
                        "nv.tenNV, kh.tenKH, hd.maYeuCau, b.maBan, " +
                        "(SELECT SUM(ctyc.soLuong * ma.giaTien) FROM ChiTietYeuCau ctyc " +
                        "JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn WHERE ctyc.maYeuCau = hd.maYeuCau) AS tongTien " +
                        "FROM HoaDon hd " +
                        "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                        "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                        "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                        "JOIN Ban b ON hd.maBan = b.maBan";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maHD = rs.getString("maHD").trim();
                String tenNV = rs.getString("tenNV").trim();
                String tenKH = rs.getString("tenKH").trim();
                String maYeuCau = rs.getString("maYeuCau").trim();
                String maBan = rs.getString("maBan").trim();
                LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();
                int soLuongKhach = rs.getInt("soLuongKhach");
                double tongTien = rs.getDouble("tongTien");
                String trangThai = rs.getString("trangThaiHoaDon");

                // Khởi tạo các đối tượng liên quan
                NhanVien nhanVien = new NhanVien();
                nhanVien.setTenNV(tenNV);
                
                YeuCauKhachHang_DAO yeuCau_DAO = new YeuCauKhachHang_DAO();
                YeuCauKhachHang yeucau = yeuCau_DAO.getYeuCauByMaYeuCau(maYeuCau);
                
                Ban_DAO banDAO = new Ban_DAO();
                Ban ban = banDAO.getBanByMaBan(maBan);

                // Tạo đối tượng HoaDon
                HoaDon hoaDon = new HoaDon(maHD, yeucau, nhanVien, ban, soLuongKhach, thoiGianTao, ngayDatBan, trangThai);
                hoaDon.setTongTien(tongTien);

                dsHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }
    
    public List<HoaDon> timHoaDonTheoSdt(String sdt) throws SQLException {
        List<HoaDon> dsHoaDon = new ArrayList<>();

        // SQL truy vấn hóa đơn theo số điện thoại của khách hàng
        String sql = "SELECT hd.maHD, hd.thoiGianTao, hd.ngayDatBan, hd.soLuongKhach, hd.trangThaiHoaDon, " +
                     "nv.tenNV, kh.tenKH, hd.maYeuCau, b.maBan, " +
                     "(SELECT SUM(ctyc.soLuong * ma.giaTien) FROM ChiTietYeuCau ctyc " +
                     "JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn WHERE ctyc.maYeuCau = hd.maYeuCau) AS tongTien " +
                     "FROM HoaDon hd " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                     "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                     "JOIN Ban b ON hd.maBan = b.maBan " +
                     "WHERE kh.sDT = ?";  // Điều kiện tìm kiếm theo số điện thoại của khách hàng

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sdt);  // Set số điện thoại vào câu truy vấn

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maHD = rs.getString("maHD");
                    String tenNV = rs.getString("tenNV");
                    String tenKH = rs.getString("tenKH");
                    String maYeuCau = rs.getString("maYeuCau");
                    String maBan = rs.getString("maBan");
                    LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                    LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();
                    int soLuongKhach = rs.getInt("soLuongKhach");
                    double tongTien = rs.getDouble("tongTien");
                    String trangThai = rs.getString("trangThaiHoaDon");

                    // Khởi tạo các đối tượng liên quan
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setTenNV(tenNV);

                    YeuCauKhachHang_DAO yeuCau_DAO = new YeuCauKhachHang_DAO();
                    YeuCauKhachHang yeucau = yeuCau_DAO.getYeuCauByMaYeuCau(maYeuCau);

                    Ban_DAO banDAO = new Ban_DAO();
                    Ban ban = banDAO.getBanByMaBan(maBan);

                    // Tạo đối tượng HoaDon
                    HoaDon hoaDon = new HoaDon(maHD, yeucau, nhanVien, ban, soLuongKhach, thoiGianTao, ngayDatBan, trangThai);
                    hoaDon.setTongTien(tongTien); // Gán tổng tiền vào hóa đơn

                    dsHoaDon.add(hoaDon);
                }
            }
        }

        return dsHoaDon;
    }
    
    public List<HoaDon> timHoaDonTheoMaBan(String maBan) throws SQLException {
        List<HoaDon> dsHoaDon = new ArrayList<>();

        // SQL truy vấn hóa đơn theo mã bàn
        String sql = "SELECT hd.maHD, hd.thoiGianTao, hd.ngayDatBan, hd.soLuongKhach, hd.trangThaiHoaDon, " +
                     "nv.tenNV, kh.tenKH, hd.maYeuCau, b.maBan, " +
                     "(SELECT SUM(ctyc.soLuong * ma.giaTien) FROM ChiTietYeuCau ctyc " +
                     "JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn WHERE ctyc.maYeuCau = hd.maYeuCau) AS tongTien " +
                     "FROM HoaDon hd " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                     "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN Ban b ON hd.maBan = b.maBan " +
                     "WHERE b.maBan = ?";  // Điều kiện tìm kiếm theo mã bàn

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maBan); // Set mã bàn vào câu truy vấn

                try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maHD = rs.getString("maHD");
                    String tenNV = rs.getString("tenNV");
                    String tenKH = rs.getString("tenKH");
                    String maYeuCau = rs.getString("maYeuCau");
                        LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                        LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();
                    int soLuongKhach = rs.getInt("soLuongKhach");
                    double tongTien = rs.getDouble("tongTien");
                        String trangThai = rs.getString("trangThaiHoaDon");

                    // Khởi tạo các đối tượng liên quan
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setTenNV(tenNV);

                    YeuCauKhachHang_DAO yeuCau_DAO = new YeuCauKhachHang_DAO();
                    YeuCauKhachHang yeucau = yeuCau_DAO.getYeuCauByMaYeuCau(maYeuCau);

                    Ban_DAO banDAO = new Ban_DAO();
                    Ban ban = banDAO.getBanByMaBan(maBan);

                        // Tạo đối tượng HoaDon
                    HoaDon hoaDon = new HoaDon(maHD, yeucau, nhanVien, ban, soLuongKhach, thoiGianTao, ngayDatBan, trangThai);
                    hoaDon.setTongTien(tongTien); // Gán tổng tiền vào hóa đơn

                    dsHoaDon.add(hoaDon);
                    }
                }
            }

        return dsHoaDon;
        }

    public HoaDon getHoaDonByMaBan(String maBan) {
    HoaDon hoaDon = null;
    String sql = "SELECT hd.maHD, hd.maYeuCau, hd.maNV, hd.maBan, hd.soLuongKhach, hd.thoiGianTao, hd.ngayDatBan, hd.trangThaiHoaDon, " +
                 "nv.tenNV, kh.tenKH " +
                 "FROM HoaDon hd " +
                 "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                 "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                 "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                 "JOIN Ban b ON hd.maBan = b.maBan " +
                 "WHERE hd.maBan = ?";

    try {
        // Kiểm tra và mở lại kết nối nếu cần
        if (conn == null || conn.isClosed()) {
            System.out.println("Kết nối bị đóng. Đang mở lại...");
            conn = ConnectDB.getInstance().connect();
        }

        // Chuẩn bị và thực thi câu lệnh truy vấn
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maBan);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Lấy dữ liệu từ ResultSet
                    String maHD = rs.getString("maHD").trim();
                    String maYeuCau = rs.getString("maYeuCau").trim();
                    String maNV = rs.getString("maNV").trim();
                    int soLuongKhach = rs.getInt("soLuongKhach");
                    LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                    LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();
                    String trangThai = rs.getString("trangThaiHoaDon");

                    // Lấy thông tin từ các DAO khác
                    NhanVien_DAO nhanvienDAO = new NhanVien_DAO();
                    NhanVien nhanVien = nhanvienDAO.getNhanVienByMa(maNV);

                    YeuCauKhachHang_DAO yeuCauKhachHangDAO = new YeuCauKhachHang_DAO();
                    YeuCauKhachHang yeuCau = yeuCauKhachHangDAO.getYeuCauByMaYeuCau(maYeuCau);

                    Ban_DAO banDAO = new Ban_DAO();
                    Ban ban = banDAO.getBanByMaBan(maBan);

                    // Khởi tạo đối tượng HoaDon
                    hoaDon = new HoaDon(maHD, yeuCau, nhanVien, ban, soLuongKhach, thoiGianTao, ngayDatBan, trangThai);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
    return hoaDon;
}
    
    public HoaDon getHoaDonByMaHD(String maHD) {
    HoaDon hoaDon = null;
    String sql = "SELECT hd.maHD, hd.maYeuCau, hd.maNV, hd.maBan, hd.soLuongKhach, hd.thoiGianTao, hd.ngayDatBan, hd.trangThaiHoaDon, " +
                 "nv.tenNV, kh.tenKH " +
                 "FROM HoaDon hd " +
                 "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                 "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                 "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                 "JOIN Ban b ON hd.maBan = b.maBan " +
                     "WHERE hd.maHD = ?";
    try {
        if (conn == null || conn.isClosed()) {
                // Nếu kết nối đã bị đóng, hãy mở lại hoặc lấy kết nối mới
            conn = ConnectDB.getInstance().connect();
        }
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, maHD);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Lấy dữ liệu từ ResultSet
                    String maYeuCau = rs.getString("maYeuCau").trim();
                    String maNV = rs.getString("maNV").trim();
                        String maBan = rs.getString("maBan").trim();
                    int soLuongKhach = rs.getInt("soLuongKhach");
                    LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                    LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();
                    String trangThai = rs.getString("trangThaiHoaDon");

                    // Lấy thông tin từ các DAO khác
                    NhanVien_DAO nhanvienDAO = new NhanVien_DAO();
                        nhanVien = nhanvienDAO.getNhanVienByMa(maNV);

                        // Tạo đối tượng KhachHang thông qua YeuCauKhachHang
                        yeuCauKhachHang_DAO = new YeuCauKhachHang_DAO();
                        yeuCau = yeuCauKhachHang_DAO.getYeuCauByMaYeuCau(maYeuCau);

                        // Tạo đối tượng Ban
                        banDAO = new Ban_DAO();
                        ban = banDAO.getBanByMaBan(maBan);

                        // Tạo đối tượng HoaDon
                    hoaDon = new HoaDon(maHD, yeuCau, nhanVien, ban, soLuongKhach, thoiGianTao, ngayDatBan, trangThai);
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
        }
    } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu kết nối không hợp lệ
    }
    return hoaDon;
}
    public void exportHoaDonToPDF(HoaDon hoaDon, List<ChiTietYeuCau> dsChiTietYeuCau, String filePath) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            // Khởi tạo PdfWriter và PdfDocument
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Sử dụng font hỗ trợ Unicode
            String fontPath = "src/main/resources/fonts/arial.ttf";
            PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H, pdfDoc);
            
            // Đặt font cho document
            document.setFont(font);

            Paragraph title = new Paragraph("Thái restaurant")
                    .setFont(font)
                    .setBold()
                    .setFontSize(24)
                    .setSpacingRatio(2)
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            // Thông tin hóa đơn
            Paragraph infoNhaHangKhachHang = new Paragraph(
                    String.format("%42s %30s\n", "Nhân viên: " + hoaDon.getNhanVien().getTenNV()
                                               , "Bàn: " + hoaDon.getBan().getMaBan()) + 
                    String.format("%42s %30s\n", "Mã hóa đơn: " + hoaDon.getMaHD()
                                               , "Ngày lập: " + dtf.format(hoaDon.getThoiGianTao()))
                    + "Tên khách hàng: " + hoaDon.getYeucau().getKh().getTenKH() + "\n\n")
                    .setFont(font)
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.LEFT);
            document.add(infoNhaHangKhachHang);
            
            // Bảng chi tiết món ăn
            float[] columnWidths = {2, 4, 2, 2, 3};
            Table table = new Table(columnWidths);
            
            // Header bảng
            table.addCell(new Cell().add(new Paragraph("STT")
                    .setFont(font)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER) 
                    .setFontSize(16))
                    .setBorder(Border.NO_BORDER)
                    .setBorderTop(new SolidBorder(1))
                    .setBorderBottom(new SolidBorder(1)));

            table.addCell(new Cell().add(new Paragraph("Tên món ăn")
                    .setFont(font)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(16))
                    .setBorder(Border.NO_BORDER)
                    .setBorderTop(new SolidBorder(1))
                    .setBorderBottom(new SolidBorder(1)));
            
            table.addCell(new Cell().add(new Paragraph("Số lượng")
                    .setFont(font)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(16))
                    .setBorder(Border.NO_BORDER)
                    .setBorderTop(new SolidBorder(1))
                    .setBorderBottom(new SolidBorder(1)));
            
            table.addCell(new Cell().add(new Paragraph("Đơn giá")
                    .setFont(font)
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(16))
                    .setBorder(Border.NO_BORDER)
                    .setBorderTop(new SolidBorder(1))
                    .setBorderBottom(new SolidBorder(1)));
            
            table.addCell(new Cell().add(new Paragraph("Thành tiền")
                    .setFont(font)
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(16))
                    .setBorder(Border.NO_BORDER)
                    .setBorderTop(new SolidBorder(1))
                    .setBorderBottom(new SolidBorder(1)));

            // Thêm dữ liệu vào bảng
            int stt = 1;
            double tongTien = 0;
            double thanhTien = 0;
            for (ChiTietYeuCau chiTiet : dsChiTietYeuCau) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(stt++)))
                        .setFont(font)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(16)
                        .setBorder(Border.NO_BORDER));
                
                table.addCell(new Cell().add(new Paragraph("\t" + chiTiet.getMonAn().getTenMonAn()))
                        .setFont(font)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setWidth(220)
                        .setFontSize(16)
                        .setBorder(Border.NO_BORDER));
                
                table.addCell(new Cell().add(new Paragraph(String.valueOf(chiTiet.getSoLuong())))
                        .setFont(font)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(16)
                        .setBorder(Border.NO_BORDER));
                
                table.addCell(new Cell().add(new Paragraph(String.format("%,.0f\t", chiTiet.getMonAn().getGiaTien())))
                        .setFont(font)
                        .setTextAlignment(TextAlignment.RIGHT)
                        .setWidth(80)
                        .setFontSize(16)
                        .setBorder(Border.NO_BORDER));
                
                thanhTien = chiTiet.getSoLuong() * chiTiet.getMonAn().getGiaTien();
                tongTien += thanhTien;
                table.addCell(new Cell().add(new Paragraph(String.format("%,.0f\t", thanhTien)))
                        .setFont(font)
                        .setTextAlignment(TextAlignment.RIGHT)
                        .setWidth(100)
                        .setFontSize(16)
                        .setBorder(Border.NO_BORDER));
            }    
            
//            table.setWidthPercentage(100);  // Điều chỉnh tỷ lệ phần trăm chiều rộng
//            table.setAutoLayout();
            document.add(table);
            
            DecimalFormat df = new DecimalFormat("#,###");
            
            Paragraph infoThanhTien = new Paragraph(
                "\n Thành tiền: " + df.format(tongTien))
                .setFont(font)
                .setBold()
                .setFontSize(16)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorderTop(new SolidBorder(1));
            document.add(infoThanhTien);
            
            Paragraph infoThue = new Paragraph(
                "Thuế: " + df.format(tongTien*0.1))
                .setFont(font)
                .setBold()
                .setFontSize(16)
                .setTextAlignment(TextAlignment.RIGHT);
            document.add(infoThue);
            
            
            tongTien = tongTien + tongTien*0.1;
            Paragraph infoTongTien = new Paragraph(
                "Tổng tiền: " + df.format(tongTien))
                .setFont(font)
                .setBold()
                .setFontSize(16)
                .setTextAlignment(TextAlignment.RIGHT);
            document.add(infoTongTien);
            
            // Đóng document
            document.close();
        
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<HoaDon> getHoaDonHomNay(LocalDate today) throws SQLException {
        List<HoaDon> dsHoaDon = new ArrayList<>();

        // Câu lệnh SQL để lấy danh sách hóa đơn theo ngày
        String sql = "SELECT hd.maHD, hd.thoiGianTao, hd.ngayDatBan, hd.soLuongKhach, hd.trangThaiHoaDon, " +
                     "nv.tenNV, kh.tenKH, hd.maYeuCau, b.maBan, " +
                     "(SELECT SUM(ctyc.soLuong * ma.giaTien) FROM ChiTietYeuCau ctyc " +
                     "JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn WHERE ctyc.maYeuCau = hd.maYeuCau) AS tongTien " +
                     "FROM HoaDon hd " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                     "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                     "JOIN Ban b ON hd.maBan = b.maBan " +
                     "WHERE CAST(hd.thoiGianTao AS DATE) = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(today)); // Gán giá trị ngày hôm nay

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maHD = rs.getString("maHD");
                    String tenNV = rs.getString("tenNV");
                    String tenKH = rs.getString("tenKH");
                    String maYeuCau = rs.getString("maYeuCau");
                    String maBan = rs.getString("maBan");
                    LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                    LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();
                    int soLuongKhach = rs.getInt("soLuongKhach");
                    double tongTien = rs.getDouble("tongTien");
                    String trangThai = rs.getString("trangThaiHoaDon");

                    // Tạo đối tượng NhanVien, YeuCauKhachHang và Ban từ dữ liệu
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setTenNV(tenNV);

                    YeuCauKhachHang_DAO yeuCau_DAO = new YeuCauKhachHang_DAO();
                    YeuCauKhachHang yeucau = yeuCau_DAO.getYeuCauByMaYeuCau(maYeuCau);

                    Ban_DAO banDAO = new Ban_DAO();
                    Ban ban = banDAO.getBanByMaBan(maBan);

                    // Tạo đối tượng HoaDon
                    HoaDon hoaDon = new HoaDon(maHD, yeucau, nhanVien, ban, soLuongKhach, thoiGianTao, ngayDatBan, trangThai);
                    hoaDon.setTongTien(tongTien);

                    dsHoaDon.add(hoaDon);
}
            }
        }
        return dsHoaDon;
    }
    public List<HoaDon> getHoaDonTrongThangHienTai() throws SQLException {
        List<HoaDon> dsHoaDon = new ArrayList<>();

        // SQL truy vấn hóa đơn trong tháng hiện tại
        String sql = "SELECT hd.maHD, hd.thoiGianTao, hd.ngayDatBan, hd.soLuongKhach, hd.trangThaiHoaDon, " +
                     "nv.tenNV, kh.tenKH, hd.maYeuCau, b.maBan, " +
                     "(SELECT SUM(ctyc.soLuong * ma.giaTien) FROM ChiTietYeuCau ctyc " +
                     "JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn WHERE ctyc.maYeuCau = hd.maYeuCau) AS tongTien " +
                     "FROM HoaDon hd " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                     "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                     "JOIN Ban b ON hd.maBan = b.maBan " +
                     "WHERE MONTH(hd.thoiGianTao) = MONTH(GETDATE()) " +
                     "AND YEAR(hd.thoiGianTao) = YEAR(GETDATE())";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maHD = rs.getString("maHD");
                    String tenNV = rs.getString("tenNV");
                    String tenKH = rs.getString("tenKH");
                    String maYeuCau = rs.getString("maYeuCau");
                    String maBan = rs.getString("maBan");
                    LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                    LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();
                    int soLuongKhach = rs.getInt("soLuongKhach");
                    double tongTien = rs.getDouble("tongTien");
                    String trangThai = rs.getString("trangThaiHoaDon");

                    // Tạo đối tượng NhanVien, YeuCauKhachHang và Ban từ dữ liệu
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setTenNV(tenNV);

                    YeuCauKhachHang_DAO yeuCau_DAO = new YeuCauKhachHang_DAO();
                    YeuCauKhachHang yeucau = yeuCau_DAO.getYeuCauByMaYeuCau(maYeuCau);

                    Ban_DAO banDAO = new Ban_DAO();
                    Ban ban = banDAO.getBanByMaBan(maBan);

                    // Tạo đối tượng HoaDon
                    HoaDon hoaDon = new HoaDon(maHD, yeucau, nhanVien, ban, soLuongKhach, thoiGianTao, ngayDatBan, trangThai);
                    hoaDon.setTongTien(tongTien);

                    dsHoaDon.add(hoaDon);
                }
            }
        }

        return dsHoaDon;
    }

    
    public List<HoaDon> getHoaDonTheoTrangThai(String trangThai) throws SQLException {
        List<HoaDon> dsHoaDon = new ArrayList<>();

        String sql = "SELECT hd.maHD, hd.thoiGianTao, hd.ngayDatBan, hd.soLuongKhach, hd.trangThaiHoaDon, " +
                     "nv.tenNV, kh.tenKH, hd.maYeuCau, b.maBan, " +
                     "(SELECT SUM(ctyc.soLuong * ma.giaTien) FROM ChiTietYeuCau ctyc " +
                     "JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn WHERE ctyc.maYeuCau = hd.maYeuCau) AS tongTien " +
                     "FROM HoaDon hd " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                     "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                     "JOIN Ban b ON hd.maBan = b.maBan " +
                     "WHERE hd.trangThaiHoaDon = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThai);  // Gán trạng thái vào câu truy vấn

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maHD = rs.getString("maHD");
                    String tenNV = rs.getString("tenNV");
                    String tenKH = rs.getString("tenKH");
                    String maYeuCau = rs.getString("maYeuCau");
                    String maBan = rs.getString("maBan");
                    LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                    LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();
                    int soLuongKhach = rs.getInt("soLuongKhach");
                    double tongTien = rs.getDouble("tongTien");

                    // Tạo đối tượng NhanVien, YeuCauKhachHang và Ban từ dữ liệu
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setTenNV(tenNV);

                    YeuCauKhachHang_DAO yeuCau_DAO = new YeuCauKhachHang_DAO();
                    YeuCauKhachHang yeucau = yeuCau_DAO.getYeuCauByMaYeuCau(maYeuCau);

                    Ban_DAO banDAO = new Ban_DAO();
                    Ban ban = banDAO.getBanByMaBan(maBan);

                    // Tạo đối tượng HoaDon
                    HoaDon hoaDon = new HoaDon(maHD, yeucau, nhanVien, ban, soLuongKhach, thoiGianTao, ngayDatBan, trangThai);
                    hoaDon.setTongTien(tongTien);

                    dsHoaDon.add(hoaDon);
                }
            }
        }
        return dsHoaDon;
    }
    
    public List<HoaDon> getHoaDonTheoNhanVien(NhanVien nhanVien) throws SQLException {
        List<HoaDon> dsHoaDon = new ArrayList<>();

        // SQL truy vấn hóa đơn theo mã nhân viên
        String sql = "SELECT hd.maHD, hd.thoiGianTao, hd.ngayDatBan, hd.soLuongKhach, hd.trangThaiHoaDon, " +
                     "nv.tenNV, kh.tenKH, hd.maYeuCau, b.maBan, " +
                     "(SELECT SUM(ctyc.soLuong * ma.giaTien) FROM ChiTietYeuCau ctyc " +
                     "JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn WHERE ctyc.maYeuCau = hd.maYeuCau) AS tongTien " +
                     "FROM HoaDon hd " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                     "JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                     "JOIN Ban b ON hd.maBan = b.maBan " +
                     "WHERE nv.maNV = ?";  // Điều kiện tìm kiếm theo mã nhân viên

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nhanVien.getMaNV());  // Set mã nhân viên vào câu truy vấn

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maHD = rs.getString("maHD");
                    String tenNV = rs.getString("tenNV");
                    String tenKH = rs.getString("tenKH");
                    String maYeuCau = rs.getString("maYeuCau");
                    String maBan = rs.getString("maBan");
                    LocalDate thoiGianTao = rs.getDate("thoiGianTao").toLocalDate();
                    LocalDate ngayDatBan = rs.getDate("ngayDatBan").toLocalDate();
                    int soLuongKhach = rs.getInt("soLuongKhach");
                    double tongTien = rs.getDouble("tongTien");
                    String trangThai = rs.getString("trangThaiHoaDon");

                    // Khởi tạo các đối tượng liên quan
                    NhanVien nv = new NhanVien();
                    nv.setTenNV(tenNV);

                    YeuCauKhachHang_DAO yeuCau_DAO = new YeuCauKhachHang_DAO();
                    YeuCauKhachHang yeucau = yeuCau_DAO.getYeuCauByMaYeuCau(maYeuCau);

                    Ban_DAO banDAO = new Ban_DAO();
                    Ban ban = banDAO.getBanByMaBan(maBan);

                    // Tạo đối tượng HoaDon
                    HoaDon hoaDon = new HoaDon(maHD, yeucau, nv, ban, soLuongKhach, thoiGianTao, ngayDatBan, trangThai);
                    hoaDon.setTongTien(tongTien);

                    dsHoaDon.add(hoaDon);
                }
            }
        }

        return dsHoaDon;
    }
    
    public boolean upDateTrangThaiHoaDon(HoaDon hoaDon){
        try {
            if (conn == null || conn.isClosed()) {
                // Nếu kết nối đã bị đóng, hãy mở lại hoặc lấy kết nối mới
                conn = ConnectDB.getInstance().connect();
            }
            String sql = "UPDATE HoaDon SET trangThaiHoaDon = ? WHERE maHD = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                
                ps.setString(1, hoaDon.getTrangThaiHoaDon());
                ps.setString(2, hoaDon.getMaHD());
                banDAO = new Ban_DAO();
                banDAO.updateTrangThaiBan(hoaDon.getBan());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
public ArrayList<Object[]> LayThongKe(String loaiThongKe, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
    ArrayList<Object[]> danhSachThongKe = new ArrayList<>();
    String query = "SELECT " +
                   "nv.maNV, nv.tenNV, nv.sDT, " +
                   "COUNT(hd.maHD) AS TongHoaDon, " +
                   "SUM(COALESCE(ctyc.soLuong * ma.giaTien, 0)) AS TongTien " +
                   "FROM HoaDon hd " +
                   "INNER JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                   "INNER JOIN ChiTietYeuCau ctyc ON yckh.maYeuCau = ctyc.maYeuCau " +
                   "INNER JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn " +
                   "INNER JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                   "WHERE hd.thoiGianTao BETWEEN ? AND ? " +
                   "GROUP BY nv.maNV, nv.tenNV, nv.sDT";

    try (Connection conn = ConnectDB.getInstance().connect()) {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setDate(1, java.sql.Date.valueOf(ngayBatDau));
        ps.setDate(2, java.sql.Date.valueOf(ngayKetThuc));

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Object[] row = new Object[]{
                rs.getString("maNV"),
                rs.getString("tenNV"),
                rs.getString("sDT"),
                rs.getInt("TongHoaDon"),
                rs.getDouble("TongTien")
            };
            danhSachThongKe.add(row);
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy dữ liệu thống kê: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    return danhSachThongKe;
}



    
    public double tinhTongTien(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
    double tongTien = 0;
    String query = "SELECT SUM(COALESCE(ctyc.soLuong * ma.giaTien, 0)) AS TongTien " +
                   "FROM HoaDon hd " +
                   "INNER JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                   "INNER JOIN ChiTietYeuCau ctyc ON yckh.maYeuCau = ctyc.maYeuCau " +
                   "INNER JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn " +
                   "WHERE hd.thoiGianTao BETWEEN ? AND ?";

    try (Connection conn = ConnectDB.getInstance().connect();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setDate(1, java.sql.Date.valueOf(ngayBatDau));
        ps.setDate(2, java.sql.Date.valueOf(ngayKetThuc));

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            tongTien = rs.getDouble("TongTien");
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi tính tổng tiền: " + e.getMessage());
    }

    return tongTien;
}
    
    public static void thongKeDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc, DefaultTableModel tableModel, JLabel totalRevenueLabel, String thongKeTheo) {
    // Khai báo tổng doanh thu
    double tongDoanhThu = 0;
    String query = "SELECT hd.maHD, nv.tenNV, kh.tenKH, hd.thoiGianTao, SUM(ctyc.soLuong * ma.giaTien) AS TongTien " +
                   "FROM HoaDon hd " +
                   "INNER JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                   "INNER JOIN YeuCauKhachHang yckh ON hd.maYeuCau = yckh.maYeuCau " +
                   "INNER JOIN KhachHang kh ON yckh.maKH = kh.maKH " +
                   "INNER JOIN ChiTietYeuCau ctyc ON yckh.maYeuCau = ctyc.maYeuCau " +
                   "INNER JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn " +
                   "WHERE hd.thoiGianTao BETWEEN ? AND ? " +
                   "GROUP BY hd.maHD, nv.tenNV, kh.tenKH, hd.thoiGianTao";

    // Tính toán ngày bắt đầu và kết thúc dựa trên lựa chọn trong ComboBox
    switch (thongKeTheo) {
        case "Ngày hôm nay":
            ngayBatDau = LocalDate.now();
            ngayKetThuc = LocalDate.now();
            break;

        case "Ngày hôm qua":
            ngayBatDau = LocalDate.now().minusDays(1);
            ngayKetThuc = LocalDate.now().minusDays(1);
            break;

        case "Theo tuần":
            ngayBatDau = LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().getValue() - 1); // Thứ 2 của tuần hiện tại
            ngayKetThuc = LocalDate.now().plusDays(7 - LocalDate.now().getDayOfWeek().getValue()); // Chủ nhật của tuần hiện tại
            break;

        case "Theo tháng":
            ngayBatDau = LocalDate.now().withDayOfMonth(1); // Ngày đầu tháng
            ngayKetThuc = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()); // Ngày cuối tháng
            break;

        case "Theo năm":
            ngayBatDau = LocalDate.now().withDayOfYear(1); // Ngày đầu năm
            ngayKetThuc = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear()); // Ngày cuối năm
            break;

        default:
            return; // Nếu không chọn đúng option, không làm gì
    }

    try (Connection conn = ConnectDB.getInstance().connect()) {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setDate(1, java.sql.Date.valueOf(ngayBatDau)); // Ngày bắt đầu
        ps.setDate(2, java.sql.Date.valueOf(ngayKetThuc)); // Ngày kết thúc

        ResultSet rs = ps.executeQuery();

        // Xóa hết các dòng dữ liệu cũ trong tableModel
        tableModel.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#,###.00");
        // Lặp qua ResultSet để lấy dữ liệu
        while (rs.next()) {
            String maHD = rs.getString("maHD");
            String tenNV = rs.getString("tenNV");
            String tenKH = rs.getString("tenKH");
            Date ngayLapHoaDon = rs.getDate("thoiGianTao");
            double tongTien = rs.getDouble("TongTien");

            // Cập nhật tổng doanh thu
            tongDoanhThu += tongTien;

            // Thêm dữ liệu vào tableModel
            tableModel.addRow(new Object[]{
                maHD,               // Mã hóa đơn
                tenNV,              // Tên nhân viên
                tenKH,              // Tên khách hàng
                ngayLapHoaDon,      // Ngày lập hóa đơn
                tongTien            // Tổng tiền
            });
        }

        // Cập nhật tổng doanh thu lên giao diện người dùng
        totalRevenueLabel.setText("" + df.format(tongDoanhThu) + " VND");

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}


public static void thongKeTopMonAnBánChay(LocalDate ngayBatDau, LocalDate ngayKetThuc, DefaultTableModel tableModel, String thongKeTheo) {
        // Cập nhật ngày bắt đầu và ngày kết thúc theo lựa chọn thống kê
        switch (thongKeTheo) {
            case "Ngày hôm nay":
                ngayBatDau = LocalDate.now();
                ngayKetThuc = LocalDate.now();
                break;
            case "Ngày hôm qua":
                ngayBatDau = LocalDate.now().minusDays(1);
                ngayKetThuc = LocalDate.now().minusDays(1);
                break;
            case "Theo tuần":
                ngayBatDau = LocalDate.now().minusWeeks(1);
                ngayKetThuc = LocalDate.now();
                break;
            case "Theo tháng":
                ngayBatDau = LocalDate.now().minusMonths(1);
                ngayKetThuc = LocalDate.now();
                break;
            case "Theo năm":
                ngayBatDau = LocalDate.now().minusYears(1);
                ngayKetThuc = LocalDate.now();
                break;
            default:
                ngayBatDau = LocalDate.now();
                ngayKetThuc = LocalDate.now();
                break;
        }

        String query = "SELECT TOP 10 ma.maMonAn, ma.tenMonAn, SUM(ctyc.soLuong) AS soLuongBan " +
               "FROM ChiTietYeuCau ctyc " +
               "INNER JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn " +
               "INNER JOIN YeuCauKhachHang yckh ON ctyc.maYeuCau = yckh.maYeuCau " +
               "INNER JOIN HoaDon hd ON yckh.maYeuCau = hd.maYeuCau " +
               "WHERE CAST(hd.thoiGianTao AS DATE) BETWEEN ? AND ? " +
               "GROUP BY ma.maMonAn, ma.tenMonAn " +
               "ORDER BY soLuongBan DESC";


        try (Connection conn = ConnectDB.getInstance().connect()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1, java.sql.Date.valueOf(ngayBatDau)); // Ngày bắt đầu
            ps.setDate(2, java.sql.Date.valueOf(ngayKetThuc)); // Ngày kết thúc

            ResultSet rs = ps.executeQuery();

            // Clear data cũ trong bảng trước khi thêm dữ liệu mới
            tableModel.setRowCount(0);  // Đặt lại bảng, xóa các dòng hiện có

            // Duyệt qua kết quả và thêm vào bảng
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("maMonAn"),  // Mã món ăn
                    rs.getString("tenMonAn"), // Tên món ăn
                    rs.getInt("soLuongBan")   // Số lượng bán
                };
                tableModel.addRow(row); // Thêm hàng vào bảng
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
