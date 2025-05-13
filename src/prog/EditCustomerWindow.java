package prog;

import dto.Kunde;
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

public class EditCustomerWindow extends JFrame {
    JTable kundenTable = new JTable();
    Logger log = LoggerFactory.getLogger(this.getClass());

    public EditCustomerWindow() {
        setTitle("Kunde Bearbeiten");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("");
        setIconImage(icon.getImage());

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das User management
        JLabel headline = new JLabel("KUNDENMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Kunde Bearbeiten");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);

        // Mittleres Panel mit GridLayout für die Tabelle
        JPanel middlePanel = new JPanel(new GridLayout(1, 1, 5, 5));

        /*
        * Kundenliste aus der Datenbank abrufen
        * Erstellen eines leeren Arrays "kundenList"
        * Neue Abfrage der Tabelle mittels eines Prepares Statements aus der "DbQueries.java"
         */
        ArrayList<Kunde> kundenList = null;
        try {
            kundenList = new DbQueries().getKunden();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Beim Abrufen der Kundendaten ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }

        /*
        * Befüllen der Zellen mit den Daten aus der Datenbanktabelle
         */
        Object[][] kundenData = null;
        if(kundenList != null) {
            int attributeCount = Kunde.class.getDeclaredFields().length;
            kundenData = new Object[kundenList.size()][attributeCount];
            int cnt = 0;
            for (Kunde kunde : kundenList) {
                kundenData[cnt][0] = kunde.getId();
                kundenData[cnt][1] = kunde.getFirma();
                kundenData[cnt][2] = kunde.getStrasse();
                kundenData[cnt][3] = kunde.getNummer();
                kundenData[cnt][4] = kunde.getPostleitzahl();
                kundenData[cnt][5] = kunde.getOrt();
                kundenData[cnt][6] = kunde.getAbteilung();
                kundenData[cnt][7] = kunde.getAnsprechpartner();
                kundenData[cnt][8] = kunde.getTelefonnummer();
                kundenData[cnt][9] = kunde.geteMail();
                kundenData[cnt][10] = kunde.getBemerkungen();
                cnt++;
            }
        }

        // Benutzerdefinierte Spaltenüberschriften für die Tabelle
        Object[] columnNames = { "ID", "Firma", "Straße", "Nr.", "PlZ", "Ort", "Abteilung", "Ansprechpartner", "Telefon", "EMail", "Bemerkungen"};

        kundenTable = new JTable(kundenData, columnNames);

        /*
         * Tooltip für die Zellen in der Tabelle bei Mouseover.
         */
       kundenTable.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                int row = kundenTable.rowAtPoint(point);
                int col = kundenTable.columnAtPoint(point);

                if (row >= 0) {
                    Object value = kundenTable.getValueAt(row, col);
                    kundenTable.setToolTipText((value != null ? value.toString() : null));
                }

            }
        });

        // Tabelle auf die Spalten aufteilen
        JScrollPane scrollPane = new JScrollPane(kundenTable);

        // Größe der Tabelle anpassen und dem "middlePanel" hinzufügen
        scrollPane.setPreferredSize(new Dimension(400, 200));

        middlePanel.add(scrollPane);

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Buttons für Aktionen
        JButton saveButton = new JButton("Speichern");
        JButton deleteButton = new JButton("Löschen");
        JButton closeButton = new JButton("Schließen");

        // Aktionen für die Buttons hinzufügen (Dummy-Implementierung)
        saveButton.addActionListener(_ -> saveCustomer());
        deleteButton.addActionListener(_ -> deleteCustomer());
        closeButton.addActionListener(_ -> close());

        // Buttons dem bottom panel zuweisen
        bottomPanel.add(deleteButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(closeButton);

        // Haupt panel mit BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Alle Panels dem main panel zuweisen
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public void deleteCustomer() {
        int selectedRow = kundenTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Bitte einen Kunden zum Löschen auswählen!");
            return;
        }
        int option = JOptionPane.showConfirmDialog(this, "Möchten Sie diesen Kunden wirklich löschen?");
        if (option == JOptionPane.YES_OPTION) {
            try {
                int bdfReference = (int) kundenTable.getValueAt(selectedRow, 0);
                new DbStatements().deleteCustomer(bdfReference);
                Updates.updateCustomerTable(kundenTable);
                JOptionPane.showMessageDialog(this, "Kundendaten erfolgreich gelöscht!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ein Fehler ist beim Löschen der Kundendaten aufgetreten");
                log.error(STR."Fehler beim Löschen der Kundendaten!\{ex.getMessage()}");
            }
        }
    }

    private void saveCustomer() {
        //TODO Prep.Statement Cutomer
        JOptionPane.showMessageDialog(this, "Datensatz GESPEICHERT!");
    }

    private void close() {
        dispose();
    }

}
