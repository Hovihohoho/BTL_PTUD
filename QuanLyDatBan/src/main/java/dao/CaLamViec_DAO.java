package dao;

import connectDB.ConnectDB;
import entity.CaLamViec;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaLamViec_DAO {

    // Thêm ca làm việc mới vào cơ sở dữ liệu
    public boolean addCaLamViec(CaLamViec caLamViec) {
        String sql = "INSERT INTO CaLamViec(maCa, tenCa, gioBatDau, gioKetThuc) VALUES(?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            ConnectDB.getInstance().connect();  // Establish the connection
            connection = ConnectDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, caLamViec.getMaCa());
            statement.setString(2, caLamViec.getTenCa());
            statement.setString(3, caLamViec.getGioBatDau());
            statement.setString(4, caLamViec.getGioKetThuc());
            return statement.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        } finally {
            closeResources(statement, connection);
        }
    }

    // Cập nhật ca làm việc
    public boolean updateCaLamViec(CaLamViec caLamViec) {
        String sql = "UPDATE CaLamViec SET tenCa = ?, gioBatDau = ?, gioKetThuc = ? WHERE maCa = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            ConnectDB.getInstance().connect();  // Establish the connection
            connection = ConnectDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, caLamViec.getTenCa());
            statement.setString(2, caLamViec.getGioBatDau());
            statement.setString(3, caLamViec.getGioKetThuc());
            statement.setString(4, caLamViec.getMaCa());
            return statement.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        } finally {
            closeResources(statement, connection);
        }
    }

    // Xóa ca làm việc
    public boolean deleteCaLamViec(String maCa) {
        String sql = "DELETE FROM CaLamViec WHERE maCa = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            ConnectDB.getInstance().connect();  // Establish the connection
            connection = ConnectDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, maCa);
            return statement.executeUpdate() > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        } finally {
            closeResources(statement, connection);
        }
    }

    // Lấy danh sách tất cả các ca làm việc
    public List<CaLamViec> getAllCaLamViec() {
        List<CaLamViec> list = new ArrayList<>();
        String sql = "SELECT * FROM CaLamViec";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            ConnectDB.getInstance().connect();  // Establish the connection
            connection = ConnectDB.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String maCa = resultSet.getString("maCa");
                String tenCa = resultSet.getString("tenCa");
                String gioBatDau = resultSet.getString("gioBatDau");
                String gioKetThuc = resultSet.getString("gioKetThuc");
                list.add(new CaLamViec(maCa, tenCa, gioBatDau, gioKetThuc));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, statement, connection);
        }

        return list; // Trả về danh sách ca làm việc
    }

    // Tìm ca làm việc theo mã
    public CaLamViec getCaLamViecByMa(String maCa) {
        String sql = "SELECT * FROM CaLamViec WHERE maCa = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            ConnectDB.getInstance().connect();  // Establish the connection
            connection = ConnectDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, maCa);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String tenCa = resultSet.getString("tenCa");
                String gioBatDau = resultSet.getString("gioBatDau");
                String gioKetThuc = resultSet.getString("gioKetThuc");
                return new CaLamViec(maCa, tenCa, gioBatDau, gioKetThuc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, statement, connection);
        }
        return null; // Trả về null nếu không tìm thấy ca làm việc
    }

    // Helper method to close resources
    private void closeResources(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeResources(Statement stmt, Connection conn) {
        closeResources(null, stmt, conn);
    }
}
