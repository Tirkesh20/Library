package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBuilder {

    private static final String DB_URL = "jdbc:mysql://localhost:5432/myDb";
    private static final String USER = "root";
    private static final String PASS = "1234";
    public static Connection connection ;

    private ConnectionBuilder() {

    }
    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
    public static Connection getConnection() {
        return connection;
    }

}
