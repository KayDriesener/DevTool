package prog;

import dto.Fahrzeug;
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

public class EditFahrzeugWindow extends JFrame {
    Logger log = LoggerFactory.getLogger(this.getClass());
    JTable tableZm = new JTable();
    JTable tableTrailer = new JTable();

    public EditFahrzeugWindow() {
        setTitle("Fahrzeug Bearbeiten");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("");
        setIconImage(icon.getImage());

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das User management
        JLabel headline = new JLabel("FAHRZEUGMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Fahrzeug Bearbeiten");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);

        // Mittleres Panel mit GridLayout für die Tabelle
        JPanel middlePanel = new JPanel(new GridLayout(0, 1, 5, 5));

        // Erstellen des Arrays für die "Zugmaschinen" und Fehlerbehandlung
        ArrayList<Fahrzeug> zmList = null;
        try {
            zmList = new DbQueries().getFahrzeugZm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Beim Abrufen der Fahrzeugdaten ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }

        // Daten aus der Datenbank abrufen
        Object[][] zmData = null;
        if (zmList != null) {
            int attributeCount = Fahrzeug.class.getDeclaredFields().length;
            zmData = new Object[zmList.size()][attributeCount];
            int cnt = 0;
            for (Fahrzeug fahrzeug : zmList) {
                zmData[cnt][0] = fahrzeug.getId();
                zmData[cnt][1] = fahrzeug.getAnbieter();
                zmData[cnt][2] = fahrzeug.getKennzeichen();
                zmData[cnt][3] = fahrzeug.getArt();
                zmData[cnt][4] = fahrzeug.getMiete();
                zmData[cnt][5] = fahrzeug.getPruefungen();
                zmData[cnt][6] = fahrzeug.getTuef();
                zmData[cnt][7] = fahrzeug.getKostenstelle();
                cnt++;
            }
        }

        // Erstellen des Arrays für die "Trailer" und Fehlerbehandlung
        ArrayList<Fahrzeug> tList = null;
        try {
            tList = new DbQueries().getFahrzeugT();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Beim Abrufen der Fahrzeugdaten ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }

        // Daten aus der Datenbank abrufen
        Object[][] dataTrailer = null;
        if (tList != null) {
            int attributeCount = Fahrzeug.class.getDeclaredFields().length;
            dataTrailer = new Object[tList.size()][attributeCount];
            int cnt = 0;
            for (Fahrzeug fahrzeug : tList) {
                dataTrailer[cnt][0] = fahrzeug.getId();
                dataTrailer[cnt][1] = fahrzeug.getAnbieter();
                dataTrailer[cnt][2] = fahrzeug.getKennzeichen();
                dataTrailer[cnt][3] = fahrzeug.getArt();
                dataTrailer[cnt][4] = fahrzeug.getMiete();
                dataTrailer[cnt][5] = fahrzeug.getPruefungen();
                dataTrailer[cnt][6] = fahrzeug.getTuef();
                dataTrailer[cnt][7] = fahrzeug.getKostenstelle();
                cnt++;
            }
        }

        // Benutzerdefinierte Spaltenüberschriften
        Object[] columnNamesZm = {"ID", "Anbieter", "Kennzeichen", "Art", "Miete", "Prüfungen", "TüV", "Kostenstelle"};
        Object[] columnNamesTrailer = {"ID", "Anbieter", "Kennzeichen", "Art", "Miete", "Prüfungen", "TüV", "Kostenstelle"};

        // Erstellen der Tabellen für die Zugmaschinen und die Trailer
        JTable tableZm = new JTable(zmData, columnNamesZm);
        JTable tableTrailer = new JTable(dataTrailer, columnNamesTrailer);

        /*
         * Tooltip für die Zellen in der Tabelle für die Zugmaschinen bei Mouseover.
         */
        tableZm.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                int row = tableZm.rowAtPoint(point);
                int col = tableZm.columnAtPoint(point);

                if (row >= 0) {
                    Object value = tableZm.getValueAt(row, col);
                    tableZm.setToolTipText((value != null ? value.toString() : null));
                }

            }
        });
        /*
         * Tooltip für die Zellen in der Tabelle bei Mouseover.
         */
        tableTrailer.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                int row = tableTrailer.rowAtPoint(point);
                int col = tableTrailer.columnAtPoint(point);

                if (row >= 0) {
                    Object value = tableTrailer.getValueAt(row, col);
                    tableTrailer.setToolTipText((value != null ? value.toString() : null));
                }

            }
        });

        // Tabelle auf die Spalten aufteilen
        JScrollPane scrollPaneZm = new JScrollPane(tableZm);
        JScrollPane scrollPaneTrailer = new JScrollPane(tableTrailer);
        scrollPaneZm.setPreferredSize(new Dimension(400, 200)); // Größe anpassen

        // Tabellen dem panel zuweisen
        middlePanel.add(scrollPaneZm);
        middlePanel.add(scrollPaneTrailer);

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Buttons für Aktionen
        JButton saveButton = new JButton("Speichern");
        JButton deleteButton = new JButton("Löschen");
        JButton closeButton = new JButton("Schließen");

        saveButton.addActionListener(_ -> saveFahrzeug());
        closeButton.addActionListener(_ -> close());
        deleteButton.addActionListener(_ -> {
            if (tableZm.getSelectedRow() != -1){
                deleteFahrzeug(tableZm);
            } else if (tableTrailer.getSelectedRow() != -1) {
                deleteFahrzeug(tableTrailer);
            } else {
                JOptionPane.showMessageDialog(this, "Bitte ein Fahrzeug zum löschen auswählen!");
            }
        });

        // Buttons dem Panel zuweisen
        bottomPanel.add(deleteButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(closeButton);

        // Haupt panel mit BorderLayout und zuweisung zum main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

    }

    private void saveFahrzeug() {
        JOptionPane.showMessageDialog(this, "Das Fahrzeug wurde gespeichert.");
    }

    private void deleteFahrzeug(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Bitte ein Fahrzeug zum löschen auswählen!");
        }
        int option = JOptionPane.showConfirmDialog(this, "Möchten Sie dieses Fahrzeug löschen?");
        if (option == JOptionPane.YES_OPTION){
            try {
                int fahrzeugId = (int)  table.getValueAt(selectedRow, 0);
                if (table == tableZm){
                    new DbStatements().deleteFahrzeugZm(fahrzeugId);
                } else if (table == tableTrailer) {
                    new DbStatements().deleteFahrzeugT(fahrzeugId);
                }
                Updates.updateTableFahrzeugZM(tableZm);
                Updates.updateTableFahrzeugT(tableTrailer);
                JOptionPane.showMessageDialog(this, "Fahrzeug erfolgreich gelöscht");
            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Ein Fehler ist beim Löschen des Fahrzeugs aufgetreten");
                log.error(STR."Fehler beim Löschen des Fahrzeugs!\{ex.getMessage()}");
            }
        }
    }

    private void close() {
        dispose();
    }

}
