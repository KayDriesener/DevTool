package sql;

import dto.Dispo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
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
        ps.setString(3, nummer);
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
    public void addShipping(boolean disponiert, String bdf_referenz, Date datum, String absender, String empfaenger, Time beladung_s, Time beladung_e, Time entladen_s, Time entladen_e, int stellplaetze, int anzahl, boolean liquid, boolean adr, boolean rundlauf, String bemerkung) throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO transport (disponiert, bdf_referenz, datum, absender, empfaenger, beladung_s, beladung_e, entladen_s, entladen_e, stellplaetze, anzahl, liquid, adr, rundlauf, bemerkung) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        ps.setBoolean(1, disponiert);
        ps.setString(2, bdf_referenz);
        ps.setDate(3, (java.sql.Date) datum);
        ps.setString(4, absender);
        ps.setString(5, empfaenger);
        ps.setTime(6, beladung_s);
        ps.setTime(7, beladung_e);
        ps.setTime(8, entladen_s);
        ps.setTime(9, entladen_e);
        ps.setInt(10, stellplaetze);
        ps.setInt(11, anzahl);
        ps.setBoolean(12, liquid);
        ps.setBoolean(13, adr);
        ps.setBoolean(14, rundlauf);
        ps.setString(15, bemerkung);
        ps.execute();
    }
    public void addDispo(boolean disponiert, String kn_referenz, String bdf_referenz, int fahrzeug_zm, int fahrzeug_t) throws SQLException{
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO dispo(disponiert, kn_referenz, bdf_referenz, fahrzeug_zm, fahrzeug_t) VALUE(?, ?, ?, ?, ?);");
        ps.setBoolean(1, disponiert);
        ps.setString(2, kn_referenz);
        ps.setString(3, bdf_referenz);
        ps.setInt(4, fahrzeug_zm);
        ps.setInt(5, fahrzeug_t);
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

    // Benutzer
    public void deleteUser(int id) throws SQLException{
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("DELETE FROM user WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    // Fahrzeuge
    public void deleteFahrzeugZm(int id) throws SQLException{
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("DELETE FROM fahrzeug_zm WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    public void deleteFahrzeugT(int id) throws SQLException{
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("DELETE FROM fahrzeug_t WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    // Transport
    public void deleteShipping(int bdf_reference) throws SQLException{
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("DELETE FROM transport WHERE bdf_referenz = ?");
        ps.setInt(1, bdf_reference);
        ps.executeUpdate();
    }
    // Rechnung

    // Kunde
    public void deleteCustomer(int id) throws SQLException{
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("DELETE FROM kunde WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void addDispo(Dispo dispo) throws SQLException{
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("INSERT INTO dispo(disponiert, kn_referenz, bdf_referenz, fahrzeug_zm, fahrzeug_t) VALUE(?, ?, ?, ?, ?);");
        ps.setBoolean(1, dispo.isDisponiert());
        ps.setString(2, dispo.getKn_Referenz());
        ps.setString(3, dispo.getBdf_referenz());
        ps.setString(4, dispo.getFahrzeugzm());
        ps.setString(5, dispo.getFahrzeugt());
        ps.execute();
    }
}

