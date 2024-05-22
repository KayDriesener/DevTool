package prog;

import dto.Shipping;
import helpers.Updates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbQueries;
import sql.DbStatements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditTransportWindow extends JFrame {
    JTable transportTable;
    Logger log = LoggerFactory.getLogger(this.getClass());

    public EditTransportWindow() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        setTitle("Transport Bearbeiten");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/media/kunIco.jpg");
        setIconImage(icon.getImage());

        // Titel für das Transportmanagement
        JLabel headline = new JLabel("TRANSPORTMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Transport Bearbeiten");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);


        /*
         * Abrufen der Datensätze aus der Datenbank.
         */
        ArrayList<Shipping> transportList = null;
        try {
            transportList = new DbQueries().getShipping();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Beim Abrufen der Transportdaten ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }

        /*
         * Tabelle erstellen und die Daten aus der Tabelle abfragen.
         * TRUE / FALSE-Werte werden mit "? "Ja" : "Nein"" umgeschrieben.
         */

        Object[][] dataTransport = null;
        if (transportList != null) {
            int attributeCount = Shipping.class.getDeclaredFields().length;
            dataTransport = new Object[transportList.size()][attributeCount];
            int cnt = 0;
            for (Shipping shipping : transportList) {
                dataTransport[cnt][0] = shipping.getBdf_referenz();
                dataTransport[cnt][1] = shipping.getDatum();
                dataTransport[cnt][2] = shipping.getKn_referenz();
                dataTransport[cnt][3] = shipping.getAbsender();
                dataTransport[cnt][4] = shipping.getEmpfaenger();
                dataTransport[cnt][5] = shipping.getBeladung_s();
                dataTransport[cnt][6] = shipping.getBeladung_e();
                dataTransport[cnt][7] = shipping.getEntladen_s();
                dataTransport[cnt][8] = shipping.getEntladen_e();
                dataTransport[cnt][9] = shipping.getStellplaetze();
                dataTransport[cnt][10] = shipping.getAnzahl();
                dataTransport[cnt][11] = shipping.isLiquid() ? "Ja" : "Nein";
                dataTransport[cnt][12] = shipping.isAdr() ? "Ja" : "Nein";
                dataTransport[cnt][13] = shipping.isRundlauf() ? "Ja" : "Nein";
                dataTransport[cnt][14] = shipping.getBemerkung();
                cnt++;
            }
        }
        /*
         * Spaltenüberschriften und Tabelle erstellen.
         * Übergabe der Transportdaten und den Tabellenüberschriften bei der Generierung der Tabelle.
         */
        Object[] columnNamesTransport = {"BDF Referenz", "Datum", "K&N Referenz", "Absender", "Empfänger", "Beladung Start", "Ende", "Entladen Start", "Ende", "Stellplätze (EP)", "Anzahl EPal", "LQ", "ADR", "Rundlauf", "Bemerkung"};
        transportTable = new JTable(dataTransport, columnNamesTransport);
        JScrollPane scrollPaneDataTransport = new JScrollPane(transportTable);

        /*
         * Tooltip für die Zellen in der Tabelle bei Mouseover.
         */
        transportTable.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                int row = transportTable.rowAtPoint(point);
                int col = transportTable.columnAtPoint(point);

                if (row >= 0) {
                    Object value = transportTable.getValueAt(row, col);
                    transportTable.setToolTipText((value != null ? value.toString() : null));
                }

            }
        });

        // Größe der Tabelle anpassen.
        scrollPaneDataTransport.setPreferredSize(new Dimension(400, 200));

        JButton editButton = new JButton("Bearbeiten");
        JButton deleteButton = new JButton("Löschen");
        JButton closeButton = new JButton("Schließen");
        closeButton.addActionListener(_ -> close());
        deleteButton.addActionListener(_ -> deleteTransport());

        bottomPanel.add(editButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(closeButton);


        add(mainPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPaneDataTransport, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);

    }

    // Bearbeiten von Transporten.
    private void editTransport() {
        //TODO p.Statement einfügen
    }
    //Löschen von Transporten und Fehlermanagement.
    public void deleteTransport() {
        int selectedRow = transportTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Bitte einen Transport zum Löschen auswählen!");
            return;
        }
        int option = JOptionPane.showConfirmDialog(this, "Möchten Sie diesen Transport wirklich löschen?");
        if (option == JOptionPane.YES_OPTION) {
            try {
                int bdfReference = (int) transportTable.getValueAt(selectedRow, 0);
                new DbStatements().deleteShipping(bdfReference);
                Updates.updateTransportTable(transportTable);
                JOptionPane.showMessageDialog(this, "Transport erfolgreich gelöscht!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ein Fehler ist beim Löschen des Transportes aufgetreten");
                log.error(STR."Fehler beim Löschen des Transportes!\{ex.getMessage()}");
            }
        }
    }

    private void close() {
        dispose();
    }

}
