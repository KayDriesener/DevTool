package helpers;

import dto.Fahrzeug;
import dto.Kunde;
import dto.Shipping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbQueries;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class Updates {

    /*
     * Ausgelagerte Update funktionen um den Code der entsprechenden Klassen lesbarer zu halten.
     */

    /*
     * TransportWindow
     */
    public static void updateTransportTable(JTable transportTable) {
        try {

            ArrayList<Shipping> updatedTransportList = new DbQueries().getShipping();

            if (updatedTransportList != null) {
                int attributeCount = Shipping.class.getDeclaredFields().length;
                Object[][] updatedDataTransport = new Object[updatedTransportList.size()][attributeCount];
                int cnt = 0;
                for (Shipping shipping : updatedTransportList) {
                    updatedDataTransport[cnt][0] = shipping.getBdf_referenz();
                    updatedDataTransport[cnt][1] = shipping.getDatum();
                    updatedDataTransport[cnt][2] = shipping.getKn_referenz();
                    updatedDataTransport[cnt][3] = shipping.getAbsender();
                    updatedDataTransport[cnt][4] = shipping.getEmpfaenger();
                    updatedDataTransport[cnt][5] = shipping.getBeladung_s();
                    updatedDataTransport[cnt][6] = shipping.getBeladung_e();
                    updatedDataTransport[cnt][7] = shipping.getEntladen_s();
                    updatedDataTransport[cnt][8] = shipping.getEntladen_e();
                    updatedDataTransport[cnt][9] = shipping.getStellplaetze();
                    updatedDataTransport[cnt][10] = shipping.getAnzahl();
                    updatedDataTransport[cnt][11] = shipping.isLiquid();
                    updatedDataTransport[cnt][12] = shipping.isAdr();
                    updatedDataTransport[cnt][13] = shipping.isRundlauf();
                    updatedDataTransport[cnt][14] = shipping.getBemerkung();
                    cnt++;
                }
                DefaultTableModel model = new DefaultTableModel(updatedDataTransport, getTransportColumnNames());
                transportTable.setModel(model);
                model.fireTableDataChanged();
            }
        } catch (SQLException e) {
            e.getMessage();

        }
    }

    private static Object[] getTransportColumnNames() {
        return new Object[]{"BDF Referenz", "Datum", "K&N Referenz", "Absender", "Empfänger", "Beladung Start", "Ende", "Entladen Start", "Ende", "Stellplätze (EP)", "Anzahl EPal", "LQ", "ADR", "Rundlauf", "Bemerkung"};
    }

    /*
     * CustomerWindow
     */
    public static void updateCustomerTable(JTable kundenList) {
        try {
            ArrayList<Kunde> updatedCustomerList = new DbQueries().getKunden();
            if (updatedCustomerList != null) {
                int attributeCount = Kunde.class.getDeclaredFields().length;
                Object[][] updatedDataCustomer = new Object[updatedCustomerList.size()][attributeCount];
                int cnt = 0;
                for (Kunde kunde : updatedCustomerList) {
                    updatedDataCustomer[cnt][0] = kunde.getId();
                    updatedDataCustomer[cnt][1] = kunde.getFirma();
                    updatedDataCustomer[cnt][2] = kunde.getStrasse();
                    updatedDataCustomer[cnt][3] = kunde.getNummer();
                    updatedDataCustomer[cnt][4] = kunde.getPostleitzahl();
                    updatedDataCustomer[cnt][5] = kunde.getOrt();
                    updatedDataCustomer[cnt][6] = kunde.getAbteilung();
                    updatedDataCustomer[cnt][7] = kunde.getAnsprechpartner();
                    updatedDataCustomer[cnt][8] = kunde.getTelefonnummer();
                    updatedDataCustomer[cnt][9] = kunde.geteMail();
                    updatedDataCustomer[cnt][10] = kunde.getBemerkungen();
                    cnt++;
                }
                DefaultTableModel model = new DefaultTableModel(updatedDataCustomer, getCustomerColumnNames());
                kundenList.setModel(model);
                model.fireTableDataChanged();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    private static Object[] getCustomerColumnNames() {
        return new Object[]{"ID", "Firma", "Straße", "Nr.", "PlZ", "Ort", "Abteilung", "Ansprechpartner", "Telefon", "EMail", "Bemerkungen"};
    }

    /*
     * FahrzeugWindow (Fahrzeug_T(railer))
     */
    public static void updateTableFahrzeugT(JTable tList) {
        try {
            ArrayList<Fahrzeug> updatedListFahrzeugT = new DbQueries().getFahrzeugT();
            if (updatedListFahrzeugT != null) {
                int attributeCount = Fahrzeug.class.getDeclaredFields().length;
                Object[][] updatedDataFahrzeugT = new Object[updatedListFahrzeugT.size()][attributeCount];
                int cnt = 0;
                for (Fahrzeug fahrzeug : updatedListFahrzeugT) {
                    updatedDataFahrzeugT[cnt][0] = fahrzeug.getId();
                    updatedDataFahrzeugT[cnt][1] = fahrzeug.getAnbieter();
                    updatedDataFahrzeugT[cnt][2] = fahrzeug.getKennzeichen();
                    updatedDataFahrzeugT[cnt][3] = fahrzeug.getArt();
                    updatedDataFahrzeugT[cnt][4] = fahrzeug.getMiete();
                    updatedDataFahrzeugT[cnt][5] = fahrzeug.getPruefungen();
                    updatedDataFahrzeugT[cnt][6] = fahrzeug.getTuef();
                    updatedDataFahrzeugT[cnt][7] = fahrzeug.getKostenstelle();
                    cnt++;
                }
                DefaultTableModel model = new DefaultTableModel(updatedDataFahrzeugT, getColumnNamesTrailer());
                tList.setModel(model);
                model.fireTableDataChanged();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    private static Object[] getColumnNamesTrailer(){
        return new Object[] {"ID", "Anbieter", "Kennzeichen", "Art", "Miete", "Prüfungen", "TüV", "Kostenstelle"};
    }
    /*
     * FahrzeugWindow (Fahrzeug_Z(ug)M(aschiene))
     */
    public static void updateTableFahrzeugZM(JTable zmList) {
        try {
            ArrayList<Fahrzeug> updatedListFahrzeugZM = new DbQueries().getFahrzeugZm();
            if (updatedListFahrzeugZM != null) {
                int attributeCount = Fahrzeug.class.getDeclaredFields().length;
                Object[][] updatedDataFahrzeugZM = new Object[updatedListFahrzeugZM.size()][attributeCount];
                int cnt = 0;
                for (Fahrzeug fahrzeug : updatedListFahrzeugZM) {
                    updatedDataFahrzeugZM[cnt][0] = fahrzeug.getId();
                    updatedDataFahrzeugZM[cnt][1] = fahrzeug.getAnbieter();
                    updatedDataFahrzeugZM[cnt][2] = fahrzeug.getKennzeichen();
                    updatedDataFahrzeugZM[cnt][3] = fahrzeug.getArt();
                    updatedDataFahrzeugZM[cnt][4] = fahrzeug.getMiete();
                    updatedDataFahrzeugZM[cnt][5] = fahrzeug.getPruefungen();
                    updatedDataFahrzeugZM[cnt][6] = fahrzeug.getTuef();
                    updatedDataFahrzeugZM[cnt][7] = fahrzeug.getKostenstelle();
                    cnt++;
                }
                DefaultTableModel model = new DefaultTableModel(updatedDataFahrzeugZM, getColumnNamesZM());
                zmList.setModel(model);
                model.fireTableDataChanged();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    private static Object[] getColumnNamesZM(){
        return new Object[] {"ID", "Anbieter", "Kennzeichen", "Art", "Miete", "Prüfungen", "TüV", "Kostenstelle"};
    }
}
