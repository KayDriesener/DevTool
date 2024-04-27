package sql;

import dto.Fahrzeug;
import dto.Kunde;
import dto.User;
import dto.Shipping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbQueries {

    /*
     * Holt die Datensätze aus der Datenbank und gibt sie in einer Liste zurück. Im Fehlerfall wird eine SQLException geworfen.
     *
     * @return Liste von Datensetzen
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

    public ArrayList<Kunde> getKunden() throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("SELECT id, firma, strasse, nummer, postleitzahl, ort, abteilung, ansprechpartner, telefonnummer, email, bemerkungen FROM kunde;");
        ResultSet rs = ps.executeQuery();

        ArrayList<Kunde> kundenList = new ArrayList<>();
        while (rs.next()) {
            Kunde kunde = new Kunde();
            kunde.setId(rs.getInt(1));
            kunde.setFirma(rs.getString(2));
            kunde.setStrasse(rs.getString(3));
            kunde.setNummer(rs.getString(4));
            kunde.setPostleitzahl(rs.getInt(5));
            kunde.setOrt(rs.getString(6));
            kunde.setAbteilung(rs.getString(7));
            kunde.setAnsprechpartner(rs.getString(8));
            kunde.setTelefonnummer(rs.getInt(9));
            kunde.seteMail(rs.getString(10));
            kunde.setBemerkungen(rs.getString(11));
            kundenList.add(kunde);
        }

        return kundenList;
    }

    public ArrayList<Fahrzeug> getFahrzeugZm() throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("SELECT id, anbieter, kennzeichen, art, miete, pruefungen, tuef, kostenstelle FROM fahrzeug_zm;");
        ResultSet rs = ps.executeQuery();

        ArrayList<Fahrzeug> fahrzeugListZm = new ArrayList<>();
        while (rs.next()) {
            Fahrzeug fahrzeug = new Fahrzeug();
            fahrzeug.setId(rs.getInt(1));
            fahrzeug.setAnbieter(rs.getString(2));
            fahrzeug.setKennzeichen(rs.getString(3));
            fahrzeug.setArt(rs.getString(4));
            fahrzeug.setMiete(rs.getFloat(5));
            fahrzeug.setPruefungen(rs.getDate(6));
            fahrzeug.setTuef(rs.getDate(7));
            fahrzeug.setKostenstelle(rs.getInt(8));
            fahrzeugListZm.add(fahrzeug);
        }

        return fahrzeugListZm;
    }

    public ArrayList<Fahrzeug> getFahrzeugT() throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("SELECT id, anbieter, kennzeichen, art, miete, pruefungen, tuef, kostenstelle FROM fahrzeug_t;");
        ResultSet rs = ps.executeQuery();

        ArrayList<Fahrzeug> fahrzeugListT = new ArrayList<>();
        while (rs.next()) {
            Fahrzeug fahrzeug = new Fahrzeug();
            fahrzeug.setId(rs.getInt(1));
            fahrzeug.setAnbieter(rs.getString(2));
            fahrzeug.setKennzeichen(rs.getString(3));
            fahrzeug.setArt(rs.getString(4));
            fahrzeug.setMiete(rs.getFloat(5));
            fahrzeug.setPruefungen(rs.getDate(6));
            fahrzeug.setTuef(rs.getDate(7));
            fahrzeug.setKostenstelle(rs.getInt(8));
            fahrzeugListT.add(fahrzeug);
        }

        return fahrzeugListT;
    }

    public ArrayList<Shipping> getShipping() throws SQLException {
        PreparedStatement ps = MySqlConnector.dbConnection.prepareStatement("SELECT bdf_referenz, datum, kn_referenz, absender, empfaenger, beladung_s, beladung_e, entladen_s, entladen_e, stellplaetze, anzahl, liquid, adr, rundlauf, bemerkung FROM transport;");
        ResultSet rs = ps.executeQuery();

        ArrayList<Shipping> shippingList = new ArrayList<>();
        while (rs.next()) {
            Shipping shipping = new Shipping();
            shipping.setBdf_referenz(rs.getInt(1));
            shipping.setDatum(rs.getDate(2));
            shipping.setKn_referenz(rs.getInt(3));
            shipping.setAbsender(rs.getString(4));
            shipping.setEmpfaenger(rs.getString(5));
            shipping.setBeladung_s(rs.getTime(6));
            shipping.setBeladung_e(rs.getTime(7));
            shipping.setEntladen_s(rs.getTime(8));
            shipping.setEntladen_e(rs.getTime(9));
            shipping.setStellplaetze(rs.getInt(10));
            shipping.setAnzahl(rs.getInt(11));
            shipping.setLiquid(rs.getBoolean(12));
            shipping.setAdr(rs.getBoolean(13));
            shipping.setRundlauf(rs.getBoolean(14));
            shipping.setBemerkung(rs.getString(15));
            shippingList.add(shipping);
        }
        return shippingList;
    }

}