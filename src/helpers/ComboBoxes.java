package helpers;

import dto.Fahrzeug;
import dto.Kunde;
import sql.DbQueries;

import javax.swing.*;
import java.util.ArrayList;

public class ComboBoxes {
    /*
     *Abrufen der Datensätze um die ComboBoxen zu füllen.
     *In den Klassen der dto's wurden die Methoden toString mit entsprechenden return eingefügt um die korrekten Datensätze anzuzeigen.
     *Bei einem Fehler wird eine Fehlermeldung ausgegeben und das Programm wird weiter ausgeführt.
     */
    public static void populateRecipientComboBox(JComboBox<String> recipientComboBox){
        try {
            ArrayList<Kunde> recipientList = new DbQueries().getKunden();
            if (recipientList != null && !recipientList.isEmpty()){
                recipientComboBox.removeAllItems();
                recipientComboBox.addItem("Empfänger wählen");

                for (Kunde shipper : recipientList){
                    recipientComboBox.addItem(String.valueOf(shipper));
                }
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, STR."Fehler beim abrufen der Datensätze Empfänger!\n\{ex.getMessage()}");
        }
    }
    public static void populateShipperComboBox(JComboBox<String> shipperComboBox){
        try {
            ArrayList<Kunde> shipperList = new DbQueries().getKunden();
            if (shipperList != null && !shipperList.isEmpty()){
                shipperComboBox.removeAllItems();
                shipperComboBox.addItem("Absender wählen");

                for (Kunde shipper : shipperList){
                    shipperComboBox.addItem(String.valueOf(shipper));
                }
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, STR."Fehler beim abrufen der Datensätze für Absender!\n\{ex.getMessage()}");
        }
    }
    public static void populateZugmaschine(JComboBox<String> zugmaschine){
        try {
            ArrayList<Fahrzeug> fahrzeugZ = new DbQueries().getFahrzeugZm();
            if (fahrzeugZ != null && !fahrzeugZ.isEmpty()){
                zugmaschine.removeAllItems();
                zugmaschine.addItem("Zugmaschine wählen.");

                for (Fahrzeug zm : fahrzeugZ){
                    zugmaschine.addItem(String.valueOf(zm));
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, STR."Fehler beim abrufen der Datensätze für Zugmashine!\n\{ex.getMessage()}");
        }
    }
    public static void populateTrailer(JComboBox<String> trailer){
        try {
            ArrayList<Fahrzeug> fahrzeugT = new DbQueries().getFahrzeugT();
            if (fahrzeugT != null && !fahrzeugT.isEmpty()){
                trailer.removeAllItems();
                trailer.addItem("Trailer wählen.");

                for (Fahrzeug tr : fahrzeugT){
                    trailer.addItem(String.valueOf(tr));
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, STR."Fehler beim abrufen der Datensätze für Trailer!\n\{ex.getMessage()}");
        }
    }
}
