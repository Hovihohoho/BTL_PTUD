/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.ChiTietYeuCau;
import entity.MonAn;
import entity.YeuCauKhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChiTietYeuCau_DAO {
    private Connection conn;
    private YeuCauKhachHang yeuCauKhachHang;
    private YeuCauKhachHang_DAO yeuCauDAO;
    private MonAn_DAO monDAO;
    private MonAn monAn;

    public ChiTietYeuCau_DAO() throws SQLException {
        conn = ConnectDB.getInstance().connect();
    }

    // Lưu chi tiết yêu cầu vào bảng ChiTietYeuCau
    public void luuChiTietYeuCau(String maYeuCau, String maMonAn, int soLuong, String ghiChu) throws SQLException {
        String sql = "INSERT INTO ChiTietYeuCau (maYeuCau, maMonAn, soLuong, ghiChu) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maYeuCau);
            stmt.setString(2, maMonAn);
            stmt.setInt(3, soLuong);
            stmt.setString(4, ghiChu);
            stmt.executeUpdate();
        }
    }
    public List<ChiTietYeuCau> getChiTietYeuCauByMaYeuCau(String maYeuCau) {
        List<ChiTietYeuCau> dsChiTietYeuCau = new ArrayList<>();
        String sql = "SELECT ctyc.maMonAn, ma.tenMonAn, ctyc.soLuong, ctyc.ghiChu, ma.giaTien " +
                     "FROM ChiTietYeuCau ctyc " +
                     "JOIN MonAn ma ON ctyc.maMonAn = ma.maMonAn " +
                     "WHERE ctyc.maYeuCau = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maYeuCau);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy dữ liệu từ ResultSet
                    String maMonAn = rs.getString("maMonAn").trim();
                    String tenMonAn = rs.getString("tenMonAn").trim();
                    int soLuong = rs.getInt("soLuong");
                    String ghiChu = rs.getString("ghiChu");
                    double giaTien = rs.getDouble("giaTien");

                    // Tạo đối tượng MonAn
                    monDAO = new MonAn_DAO();
                    monAn = monDAO.findMonAnByMaMonAn(maMonAn);
                    
                    yeuCauDAO = new YeuCauKhachHang_DAO();
                    yeuCauKhachHang = yeuCauDAO.getYeuCauByMaYeuCau(maYeuCau);
                    
                    // Tạo đối tượng ChiTietYeuCau
                    ChiTietYeuCau chiTietYeuCau = new ChiTietYeuCau(yeuCauKhachHang, monAn, soLuong, ghiChu);

                    // Thêm vào danh sách
                    dsChiTietYeuCau.add(chiTietYeuCau);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsChiTietYeuCau;
    }
}
