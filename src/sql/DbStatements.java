package sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

public class DbStatements {

    /*
     *
     * Fügt einen neue Datensaetze der Datenbank hinzu. Im Fehlerfall wird eine SQLException geworfen.
     * @throws SQLException
     *
     * Gliederung:
     *  -Add
     *  -Edit
     *  -Delete
     *
     */

    /*
     *  ADD
     */
    public void addUser(String vorname, String nachname, String email, String username) throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO user (vorname, nachname, email, username) VALUES (?, ?, ?, ?);");
        ps.setString(1, vorname);
        ps.setString(2, nachname);
        ps.setString(3, email);
        ps.setString(4, username);
        ps.execute();
    }
    public void addKunde(String firma, String strasse, String nummer, String postleitzahl, String ort, String abteilung, String ansprechpartner, String telefonnummer, String email, String bemerkungen) throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO kunde (firma, strasse, nummer, postleitzahl, ort, abteilung, ansprechpartner, telefonnummer, email, bemerkungen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        ps.setString(1, firma);
        ps.setString(2, strasse);
        ps.setInt(3, Integer.parseInt(nummer));
        ps.setInt(4, Integer.parseInt(postleitzahl));
        ps.setString(5, ort);
        ps.setString(6, abteilung);
        ps.setString(7, ansprechpartner);
        ps.setInt(8, Integer.parseInt(telefonnummer));
        ps.setString(9, email);
        ps.setString(10, bemerkungen);
        ps.execute();
    }
    public void addFahrzeugZm(String anbieter, String kennzeichen, String art, Float miete, Date pruefungen, Date tuef, Integer kostenstelle) throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO fahrzeug_zm (anbieter, kennzeichen, art, miete, pruefungen, tuef, kostenstelle) VALUES (?, ?, ?, ?, ?, ?, ?);");
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
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO fahrzeug_t (anbieter, kennzeichen, art, miete, pruefungen, tuef, kostenstelle) VALUES (?, ?, ?, ?, ?, ?, ?);");
        ps.setString(1, anbieter);
        ps.setString(2, kennzeichen);
        ps.setString(3, art);
        ps.setFloat(4, miete);
        ps.setDate(5, (java.sql.Date) pruefungen);
        ps.setDate(6, (java.sql.Date) tuef);
        ps.setInt(7, kostenstelle);
        ps.execute();
    }
    public  void  addShipping(int bdf_referenz, Date datum, String absender, String empfaenger, Date beladung_s, Date beladung_e, Date entladen_s, Date entladen_e, int stellplaetze, int anzahl, boolean liquid, boolean adr, boolean rundlauf, String bemerkung) throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO transport (bdf_referenz, datum, absender, empfaenger, beladung_s, beladung_e, entladen_s, entladen_e, stellplaetze, anzahl, liquid, adr, rundlauf, bemerkung) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        ps.setInt(1, bdf_referenz);
        ps.setDate(2, (java.sql.Date) datum);
        ps.setString(3, absender);
        ps.setString(4, empfaenger);
        ps.setDate(5, (java.sql.Date) beladung_s);
        ps.setDate(6, (java.sql.Date) beladung_e);
        ps.setDate(7, (java.sql.Date) entladen_s);
        ps.setDate(8, (java.sql.Date) entladen_e);
        ps.setInt(9, stellplaetze);
        ps.setInt(10, anzahl);
        ps.setBoolean(11, liquid);
        ps.setBoolean(12, adr);
        ps.setBoolean(13, rundlauf);
        ps.setString(14, bemerkung);
        ps.execute();
    }
    /*
     *  EDIT
     */

    public void editBdfReferenz(int bdf_referenz) throws SQLException{
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("UPDATE transport SET bdf_referenz =? WHERE bdf_referenz =?");
        ps.setInt(1, bdf_referenz);
        ps.execute();
    }

    /*
     *  DELETE
     */

    public void deleteShipping(int bdf_reference) throws SQLException{
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("DELETE FROM transport WHERE bdf_referenz = ?");
        ps.setInt(1, bdf_reference);
        ps.executeUpdate();
    }
}

