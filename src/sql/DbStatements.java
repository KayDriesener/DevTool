package sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class DbStatements {

    /**
     * Fügt einen neue Datensaetze der Datenbank hinzu. Im Fehlerfall wird eine SQLException geworfen.
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
    public void addKunde(String firma, String strasse, Integer nummer, Integer postleitzahl, String ort, String abteilung, String ansprechpartner, Integer telefonnummer, String email, String bemerkungen) throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO kunde (firma, strasse, nummer, postleitzahl, ort, abteilung, ansprechpartner, telefonnummer, email, bemerkungen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        ps.setString(1, firma);
        ps.setString(2, strasse);
        ps.setInt(3, nummer);
        ps.setInt(4, postleitzahl);
        ps.setString(5, ort);
        ps.setString(6, abteilung);
        ps.setString(7, ansprechpartner);
        ps.setInt(8, telefonnummer);
        ps.setString(9, email);
        ps.setString(10, bemerkungen);
        ps.execute();
    }
    public void addFahrzeugZm(String anbieter, String kennzeichen, String art, Float miete, Date pruefungen, Date tuef, Integer kostenstelle) throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO fahrzeugzm (anbieter, kennzeichen, art, miete, pruefungen, tuef, kostenstelle) VALUES (?, ?, ?, ?, ?, ?, ?);");
        ps.setString(1, anbieter);
        ps.setString(2, kennzeichen);
        ps.setString(3, art);
        ps.setFloat(4, miete);
        ps.setDate(5, (java.sql.Date) pruefungen);
        ps.setDate(6, (java.sql.Date) tuef);
        ps.setInt(7, kostenstelle);
        ps.execute();
    }
    public void addFahrzeugT(String anbieter, String kennzeichen, String art, Float miete, Date pruefungen, Date tuef, Integer kostenstelle) throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO fahrzeugt (anbieter, kennzeichen, art, miete, pruefungen, tuef, kostenstelle) VALUES (?, ?, ?, ?, ?, ?, ?);");
        ps.setString(1, anbieter);
        ps.setString(2, kennzeichen);
        ps.setString(3, art);
        ps.setFloat(4, miete);
        ps.setDate(5, (java.sql.Date) pruefungen);
        ps.setDate(6, (java.sql.Date) tuef);
        ps.setInt(7, kostenstelle);
        ps.execute();
    }
}
