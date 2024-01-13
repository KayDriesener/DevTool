package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {
    private static final String JDBC_CONNECTION_STRING = "jdbc:mysql://localhost:3306/test";
    public static Connection dbConnection;

    public static void connect(String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(JDBC_CONNECTION_STRING, username, password);
    }
}
