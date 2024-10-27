package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static ConnectDB instance;
    private static Connection connection;

    public ConnectDB() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyDatBan;encrypt=true;trustServerCertificate=true";
        try {
            connection = DriverManager.getConnection(url , "sa", "nhocconpro1202");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized static ConnectDB getInstance() {
        if(instance == null)
            instance = new ConnectDB();
        return instance;
    }

    public static Connection getConnection() {
        return connection;
    }
}
