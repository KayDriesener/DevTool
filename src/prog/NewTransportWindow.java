package prog;

import dto.Shipping;

import helpers.ComboBoxes;
import helpers.Parsing;
import helpers.Updates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbQueries;
import sql.DbStatements;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class NewTransportWindow extends JFrame {
    JComboBox<String> shipperComboBox;
    JComboBox<String> recipientComboBox;
    JTable transportTable;
    Logger log = LoggerFactory.getLogger(this.getClass());
    JTextField knReferenceText;
    JTextField loadBeginTime;
    JTextField loadEndTime;
    JTextField pitchesText;
    JCheckBox liquid;
    JTextField bdf_referenceText;
    JTextField dischargeBeginText;
    JTextField dischargeEndText;
    JTextField epalText;
    JCheckBox adrBox;
    JTextField commentText;
    JCheckBox roundtrip;
    JTextField printDate;
    private boolean disponiert;

    public NewTransportWindow() {
        setTitle("Neuer Transport");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        ImageIcon icon = new ImageIcon("");
        setIconImage(icon.getImage());

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das Transportmanagement
        JLabel headline = new JLabel("TRANSPORTMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Transport Anlegen");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);

        // MiddlePannel
        JPanel middlePanel = new JPanel();

        // Layoutmanager. Grouplayout ist ausgewählt.
        GroupLayout middleGroup = new GroupLayout(middlePanel);
        middlePanel.setLayout(middleGroup);

        // Automatische Abstände
        middleGroup.setAutoCreateGaps(true);
        middleGroup.setAutoCreateContainerGaps(true);

        /*
         * Erste Gruppe (Reihe)
         * Tooltip für Eingabefelder mit einer Formatierung wie Uhrzeit, Datum und gesperrten Eingaben
         * wurden eingefügt.
         */
        JLabel knReferenz = new JLabel("K&N Referenz:");
        knReferenceText = new JTextField();
        knReferenceText.setToolTipText("Wird bei der Disposition vergeben!");
        knReferenceText.setEditable(false);
        shipperComboBox = new JComboBox<>();
        ComboBoxes.populateShipperComboBox(shipperComboBox);
        JLabel loadBegin = new JLabel("Beladung Start:");
        loadBeginTime = new JTextField();
        loadBeginTime.setToolTipText("HH:mm");
        JLabel loadEnd = new JLabel("bis:");
        loadEndTime = new JTextField();
        loadEndTime.setToolTipText("HH:mm");
        JLabel pitches = new JLabel("Stellplätze (EP):");
        pitchesText = new JTextField();
        liquid = new JCheckBox("LQ");

        /*
         * Zweite Gruppe (Reihe)
         * Tooltip für Eingabefelder mit einer Formatierung wie Uhrzeit und Datum
         * wurden eingefügt.
         */
        JLabel date = new JLabel("Datum:");
        printDate = new JTextField();
        printDate.setToolTipText("Im Format dd.mm.YYYY");
        JLabel bdf_reference = new JLabel("BDF Referenz:");
        bdf_referenceText = new JTextField();
        bdf_referenceText.setToolTipText("TA-Nummer");
        recipientComboBox = new JComboBox<>();
        ComboBoxes.populateRecipientComboBox(recipientComboBox);
        JLabel dischargeBegin = new JLabel("Entladen Start:");
        dischargeBeginText = new JTextField();
        dischargeBeginText.setToolTipText("HH:mm");
        JLabel dischargeEnd = new JLabel("bis:");
        dischargeEndText = new JTextField();
        dischargeEndText.setToolTipText("HH:mm");
        JLabel eupal = new JLabel("Anzahl EuroPal:");
        epalText = new JTextField();
        adrBox = new JCheckBox("ADR");

        // Dritte Gruppe (Reihe)
        JLabel comment = new JLabel("Bemerkung");
        commentText = new JTextField();
        roundtrip = new JCheckBox("Rundlauf");

        /*
         * Vierte Gruppe (Reihe (Tabelle))
         * Anlegen einer ArrayList<> aus der Klasse "Shipping" mit var. Bezeichnung "transportList".
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
         * TRUE / FALSE-Werte werden mit →? "Ja" : "Nein"← umgeschrieben.
         */
        Object[][] dataTransport = null;
        if (transportList != null) {
            int attributeCount = Shipping.class.getDeclaredFields().length;
            dataTransport = new Object[transportList.size()][attributeCount];
            int cnt = 0;
            for (Shipping shipping : transportList) {
                dataTransport[cnt][0] = shipping.isDisponiert() ? "Ja" : "Nein";
                dataTransport[cnt][1] = shipping.getBdf_referenz();
                dataTransport[cnt][2] = shipping.getDatum();
                dataTransport[cnt][3] = shipping.getKn_referenz();
                dataTransport[cnt][4] = shipping.getAbsender();
                dataTransport[cnt][5] = shipping.getEmpfaenger();
                dataTransport[cnt][6] = shipping.getBeladung_s();
                dataTransport[cnt][7] = shipping.getBeladung_e();
                dataTransport[cnt][8] = shipping.getEntladen_s();
                dataTransport[cnt][9] = shipping.getEntladen_e();
                dataTransport[cnt][10] = shipping.getStellplaetze();
                dataTransport[cnt][11] = shipping.getAnzahl();
                dataTransport[cnt][12] = shipping.isLiquid() ? "Ja" : "Nein";
                dataTransport[cnt][13] = shipping.isAdr() ? "Ja" : "Nein";
                dataTransport[cnt][14] = shipping.isRundlauf() ? "Ja" : "Nein";
                dataTransport[cnt][15] = shipping.getBemerkung();
                cnt++;
            }
        }
        Object[] columnNamesTransport = {"Disponiert", "BDF Referenz", "Datum", "K&N Referenz", "Absender", "Empfänger", "Beladung Start", "Ende", "Entladen Start", "Ende", "Stellplätze (EP)", "Anzahl EPal", "LQ", "ADR", "Rundlauf", "Bemerkung"};
        transportTable = new JTable(dataTransport, columnNamesTransport);
        JScrollPane scrollPaneDataTransport = new JScrollPane(transportTable);
        middlePanel.add(scrollPaneDataTransport);

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

        //Erstellen des GroupLayouts
        middleGroup.setHorizontalGroup(
                middleGroup.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(middleGroup.createSequentialGroup()
                                .addComponent(knReferenz)
                                .addComponent(knReferenceText)
                                .addComponent(shipperComboBox)
                                .addComponent(loadBegin)
                                .addComponent(loadBeginTime)
                                .addComponent(loadEnd)
                                .addComponent(loadEndTime)
                                .addComponent(pitches)
                                .addComponent(pitchesText)
                                .addComponent(liquid))
                        .addGroup(middleGroup.createSequentialGroup()
                                .addComponent(bdf_reference)
                                .addComponent(bdf_referenceText)
                                .addComponent(recipientComboBox)
                                .addComponent(dischargeBegin)
                                .addComponent(dischargeBeginText)
                                .addComponent(dischargeEnd)
                                .addComponent(dischargeEndText)
                                .addComponent(eupal)
                                .addComponent(epalText)
                                .addComponent(adrBox))
                        .addGroup(middleGroup.createSequentialGroup()
                                .addComponent(date)
                                .addComponent(printDate)
                                .addComponent(comment)
                                .addComponent(commentText)
                                .addComponent(roundtrip))
                        .addGroup(middleGroup.createSequentialGroup()
                                .addComponent(scrollPaneDataTransport)));

        middleGroup.setVerticalGroup(
                middleGroup.createSequentialGroup()
                        .addGroup(middleGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(knReferenz)
                                .addComponent(knReferenceText)
                                .addComponent(shipperComboBox)
                                .addComponent(loadBegin)
                                .addComponent(loadBeginTime)
                                .addComponent(loadEnd)
                                .addComponent(loadEndTime)
                                .addComponent(pitches)
                                .addComponent(pitchesText)
                                .addComponent(liquid))
                        .addGroup(middleGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(bdf_reference)
                                .addComponent(bdf_referenceText)
                                .addComponent(recipientComboBox)
                                .addComponent(dischargeBegin)
                                .addComponent(dischargeBeginText)
                                .addComponent(dischargeEnd)
                                .addComponent(dischargeEndText)
                                .addComponent(eupal)
                                .addComponent(epalText)
                                .addComponent(adrBox))
                        .addGroup(middleGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(date)
                                .addComponent(printDate)
                                .addComponent(comment)
                                .addComponent(commentText)
                                .addComponent(roundtrip))
                        .addGroup(middleGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(liquid))
                        .addGroup(middleGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(adrBox))
                        .addGroup(middleGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(roundtrip))
                        .addComponent(scrollPaneDataTransport));

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Erstellen der Buttons und Zuweisung der ActionListener
        JButton saveButton = new JButton("Anlegen");
        JButton closeButton = new JButton("Schließen");
        JButton dispoButton = new JButton("Disposition");
        closeButton.addActionListener(_ -> close());
        saveButton.addActionListener(_ -> saveTransport());
        dispoButton.addActionListener(_ -> goDisposition());

        // Dem bottomPanel zuweisen
        bottomPanel.add(saveButton);
        bottomPanel.add(dispoButton);
        bottomPanel.add(closeButton);

        // Erstellung des Haupt panel mit BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Zuweisung der anderen Panels zum mainPanel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // mainPanel dem Fenster zuweisen und anzeigen lassen
        add(mainPanel);
        setVisible(true);

    }

    // Parsen und speichern der eingegebenen Daten
    private void saveTransport() {
        String selectedOptionShipper = (String) shipperComboBox.getSelectedItem();
        String selectedOptionRecipient = (String) recipientComboBox.getSelectedItem();

        try {
            /*
             * Parsen der eingegebenen Daten, um diese mit der Datenbank zu verarbeiten.
             * Methoden zum Parsen wurden in helpers. Parsing ausgelagert, um den Code übersichtlicher zu gestalten.
             * Die Methode zum Aktualisieren der Tabelle nach dem Hinzufügen eines Datensatzes wurde in die helpers. Updates ausgelagert.
             */
            Time lBegin = Parsing.parseTime(loadBeginTime.getText());
            Time lEnd = Parsing.parseTime(loadEndTime.getText());
            int pitches = Integer.parseInt(pitchesText.getText());
            boolean isliquid = liquid.isSelected();
            String bdfRef = bdf_referenceText.getText();
            Time dcBegin = Parsing.parseTime(dischargeBeginText.getText());
            Time dcEnd = Parsing.parseTime(dischargeEndText.getText());
            int epal = Integer.parseInt(epalText.getText());
            boolean isAdr = adrBox.isSelected();
            String comment = commentText.getText();
            boolean isRoundtrip = roundtrip.isSelected();
            Date date = Parsing.parseDate(printDate.getText());

            // Aufrufen des Statements zum Übergeben der Daten an die Datenbank incl. Update funktion und Fehlerbehandlung
            new DbStatements().addShipping(disponiert, bdfRef, date, selectedOptionShipper, selectedOptionRecipient, lBegin, lEnd, dcBegin, dcEnd, pitches, epal, isliquid, isAdr, isRoundtrip, comment);
            Updates.updateTransportTable(transportTable);
            JOptionPane.showMessageDialog(this, "Transport angelegt!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, STR."Ein unerwarteter Fehler ist aufgetreten!\{ex.getMessage()}");
            log.error(STR."Ein unerwarteter Fehler ist aufgetreten!\{ex.getMessage()}");
        }
    }
    static class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        CheckBoxRenderer() {
            setHorizontalAlignment(JCheckBox.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected){
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground() );
            }

            if (value instanceof Boolean){
                setSelected((Boolean) value);
            } else if (value instanceof Integer){
                setSelected(((Integer) value) != 0);
            } else {
                setSelected(false);
            }
            return this;
        }
    }



    private void goDisposition() {
        new DispositionWindow();
        dispose();
    }

    // Öffnet das Hauptmenü und schließt das aktuelle Fenster um Ressourcen freizugeben.
    private void close() {
        dispose();
    }

}