package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection con = null;
    private static final ConnectDB instance = new ConnectDB();

    public ConnectDB() {
        // private constructor để ngăn chặn tạo thêm đối tượng bên ngoài lớp này
    }

    public static ConnectDB getInstance() {
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            instance.connect(); // Tự động kết nối lại nếu kết nối bị đóng hoặc chưa được khởi tạo
        }
        return con;
    }

    public Connection connect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyDatBan;encrypt=false;trustServerCertificate=true";
        String user = "sa";
        String password = "sa1";
//        if (con == null || con.isClosed()) { // Chỉ tạo kết nối nếu chưa có
            con = DriverManager.getConnection(url, user, password);
//        }
        return con;
    }

    public void disconnect() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con = null; // Đặt lại con để kiểm tra trong lần gọi tiếp theo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
