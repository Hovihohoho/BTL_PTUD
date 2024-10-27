package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	private static final ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public static Connection getConnection() {
		return con;
	}

	public Connection connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyDatBan;encrypt=false;trustServerCertificate=true";
                String user = "sa";
                String password = "sa1";
                con = DriverManager.getConnection(url, user, password);
                return con; // Trả về đối tượng Connection
	}

	public void disconnect() throws SQLException {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}