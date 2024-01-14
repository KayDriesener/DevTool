package sql;

import dto.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbQueries {

    /**
     * Holt alle Benutzer aus der Datenbank und gibt sie in einer Liste zurück. Im Fehlerfall wird eine SQLException geworfen.
     * @return Liste von Benutzern
     * @throws SQLException
     */
    public ArrayList<User> getUsers() throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("SELECT id, vorname, nachname, email, username FROM user;");
        ResultSet rs = ps.executeQuery();

        ArrayList<User> userList = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setVorname(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setUserName(rs.getString(5));
            userList.add(user);
        }

        return userList;
    }
}
