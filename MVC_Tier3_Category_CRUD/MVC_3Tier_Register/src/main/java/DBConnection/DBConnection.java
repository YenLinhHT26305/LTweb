package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private final String host = "LAPTOP-PSVNV44T"; // Tên máy
    private final String instanceName = "MSSQLSERVER1"; // Để trống "" nếu là default instance
    private final String dbName = "LoginRegister";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "linhhtyl0";

    public Connection getConnectionW() throws Exception {
        String serverName;
        if (instanceName == null || instanceName.trim().isEmpty()) {
            // Không có instance → dùng host
            serverName = host;
        } else {
            // Có instance → host\\instance
            serverName = host + "\\" + instanceName;
        }

        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                   + ";databaseName=" + dbName
                   + ";encrypt=true;trustServerCertificate=true";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
}
