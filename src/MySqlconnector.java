import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlconnector {
    private String url;

    public MySqlconnector(String url) {
        this.url = url;
    }

    public Connection connect(String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
