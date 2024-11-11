/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import connectDB.ConnectDB;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class TaoMaHoaDon {

    // Hàm tạo mã hóa đơn
    public String generateMaHoaDon(NhanVien nhanvien) {
         // Truy vấn để lấy mã hóa đơn lớn nhất hiện tại
        String sql = "SELECT MAX(maHD) AS maxMaHD FROM HoaDon";
        String nextMaHD = "HD0001";
        
        try (Connection conn = ConnectDB.getInstance().connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

           if (rs.next()) {
               String maxMaHD = rs.getString("maxMaHD");
               if (maxMaHD != null) {
                   // Tạo mã hóa đơn tiếp theo bằng cách tách phần số và tăng giá trị lên
                   String prefix = maxMaHD.substring(0, 6); // "HD0002"
                   int number = Integer.parseInt(maxMaHD.substring(2, 6)); // "0002" -> 2
                   int nextNumber = number + 1;
                   // Tạo mã hóa đơn mới với định dạng tương tự "HD0003"
                   nextMaHD = maxMaHD.substring(0, 2) + String.format("%04d", nextNumber); // Bạn có thể thay đổi phần này theo yêu cầu
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
         // Lấy ngày hiện tại
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMM");
        String ngayThang = dateFormat.format(new Date()); // Ví dụ: "2009" cho ngày 20 tháng 9

        // Lấy ca làm việc (giả sử ca làm việc là S, C, T)
        char caLamViec = getCaLamViec();

        // Lấy 3 ký tự cuối của mã nhân viên
        String maNV1 = nhanvien.getMaNV().trim();
        String maNVShort = maNV1.substring(maNV1.length() - 3); // Giả sử maNV có dạng "NV001", lấy "001"

        // Tạo mã hóa đơn
        return (nextMaHD + caLamViec + ngayThang + maNVShort);
    }

    // Hàm lấy ca làm việc, ví dụ: "S", "C", "T"
    private char getCaLamViec() {
        // Ví dụ: logic lấy ca làm việc
        Date now = new Date();
        int hour = now.getHours();
        
        if (hour >= 6 && hour < 12) {
            return 'S';  // Ca sáng
        } else if (hour >= 12 && hour < 18) {
            return 'C';  // Ca chiều
        } else {
            return 'T';  // Ca tối
        }
    }

    // Hàm lấy mã hóa đơn tiếp theo (nnnn)
    private String getNextMaHoaDon() {
        // Giả sử bạn truy vấn từ CSDL để lấy mã hóa đơn mới nhất
        int lastMaHD = getLastMaHDFromDB(); // Trả về số hóa đơn cao nhất từ CSDL
        int nextMaHD = lastMaHD + 1; // Tăng số hóa đơn lên

        // Chuyển số hóa đơn thành chuỗi với 4 chữ số
        return String.format("%04d", nextMaHD); // Ví dụ: "0001", "0002", ...
    }

    // Giả lập hàm lấy mã hóa đơn cuối cùng từ cơ sở dữ liệu (thực tế cần thực hiện truy vấn)
    private int getLastMaHDFromDB() {
        // Truy vấn CSDL để lấy mã hóa đơn cao nhất, ví dụ:
        // SELECT MAX(maHD) FROM HoaDon
        return 1; // Giả sử là "1"
    }
}
