package bloodbank;

import java.sql.*;
class ConnectionManager {

    private final String driverName = "com.mysql.jdbc.Driver";
    private final String connectionUrl = "jdbc:mysql://localhost:3306/bloodbank?verifyServerCertificate=false&useSSL=true";
    private final String userName = "root";
    private final String userPass = "password";

    private Connection conn = null;

    protected ConnectionManager() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    protected Connection createConnection() {
        try {
            conn = DriverManager.getConnection(connectionUrl, userName, userPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    protected void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
