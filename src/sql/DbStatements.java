package sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbStatements {

    /**
     * Fügt einen neuen Benutzer der Datenbank hinzu. Im Fehlerfall wird eine SQLException geworfen.
     * @throws SQLException
     */
    public void addUser(String vorname, String nachname, String email, String username) throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO user (vorname, nachname, email, username) VALUES (?, ?, ?, ?);");
        ps.setString(1, vorname);
        ps.setString(2, nachname);
        ps.setString(3, email);
        ps.setString(4, username);
        ps.execute();
    }
}
