/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ChiTietYeuCau_DAO {
    private Connection conn;

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
}
