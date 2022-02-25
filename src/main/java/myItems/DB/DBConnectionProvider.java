package myItems.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private final String URL = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/my_items?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "root";

    private static DBConnectionProvider instance = new DBConnectionProvider();
    private Connection connection;


    private DBConnectionProvider() {
        try {
            Class.forName(URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public static DBConnectionProvider getInstance() {
        return instance;
    }

    public Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                try {
                    connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

