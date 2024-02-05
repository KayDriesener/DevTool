import dto.Shipping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbQueries;
import sql.DbStatements;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class NewTransportWindow extends JFrame {
    private final JComboBox<String> shipperComboBox;
    private final JComboBox<String> recipientComboBox;
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

    public NewTransportWindow() {
        setTitle("Neuer Transport");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

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

        // GroupLayout
        GroupLayout middleGroup = new GroupLayout(middlePanel);
        middlePanel.setLayout(middleGroup);

        // AutoGap
        middleGroup.setAutoCreateGaps(true);
        middleGroup.setAutoCreateContainerGaps(true);

        // Erste Gruppe (Reihe)
        JLabel knReferenz = new JLabel("K&N Referenz:");
        knReferenceText = new JTextField();
        knReferenceText.setEditable(false);
        JLabel shipperLabel = new JLabel("Absender:");
        String[] shipperOptions = {"Absender wählen"};
        shipperComboBox = new JComboBox<>(shipperOptions);
        JLabel loadBegin = new JLabel("Beladung Start:");
        loadBeginTime = new JTextField();
        JLabel loadEnd = new JLabel("bis:");
        loadEndTime = new JTextField();
        JLabel pitches = new JLabel("Stellplätze (EP):");
        pitchesText = new JTextField();
        liquid = new JCheckBox("LQ");

        // Zweite Gruppe (Reihe)
        JLabel date = new JLabel("Datum:");
        printDate = new JTextField();
        JLabel bdf_reference = new JLabel("BDF Referenz:");
        bdf_referenceText = new JTextField();
        JLabel recipient = new JLabel("Empfänger:");
        String[] recipientOptions = {"Empfänger wählen"};
        recipientComboBox = new JComboBox<>(recipientOptions);
        JLabel dischargeBegin = new JLabel("Entladen Start:");
        dischargeBeginText = new JTextField();
        JLabel dischargeEnd = new JLabel("bis:");
        dischargeEndText = new JTextField();
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
                dataTransport[cnt][11] = shipping.isLiquid();
                dataTransport[cnt][12] = shipping.isAdr();
                dataTransport[cnt][13] = shipping.isRundlauf();
                dataTransport[cnt][14] = shipping.getBemerkung();
                cnt++;
            }
        }
        Object[] columnNamesTransport = {"BDF Referenz", "Datum", "K&N Referenz", "Absender", "Empfänger", "Beladung Start", "Ende", "Entladen Start", "Ende", "Stellplätze (EP)", "Anzahl EPal", "LQ", "ADR", "Rundlauf", "Bemerkung"};
        JTable transportTable = new JTable(dataTransport, columnNamesTransport);
        JScrollPane scrollPaneDataTransport = new JScrollPane(transportTable);

        // Größe anpassen
        scrollPaneDataTransport.setPreferredSize(new Dimension(400, 200));

        middleGroup.setHorizontalGroup(
                middleGroup.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(middleGroup.createSequentialGroup()
                                .addComponent(knReferenz)
                                .addComponent(knReferenceText)
                                .addComponent(shipperLabel)
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
                                .addComponent(recipient)
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
                        .addGroup(middleGroup
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(knReferenz)
                                .addComponent(knReferenceText)
                                .addComponent(shipperLabel)
                                .addComponent(shipperComboBox)
                                .addComponent(loadBegin)
                                .addComponent(loadBeginTime)
                                .addComponent(loadEnd)
                                .addComponent(loadEndTime)
                                .addComponent(pitches)
                                .addComponent(pitchesText)
                                .addComponent(liquid))
                        .addGroup(middleGroup
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(bdf_reference)
                                .addComponent(bdf_referenceText)
                                .addComponent(recipient)
                                .addComponent(recipientComboBox)
                                .addComponent(dischargeBegin)
                                .addComponent(dischargeBeginText)
                                .addComponent(dischargeEnd)
                                .addComponent(dischargeEndText)
                                .addComponent(eupal)
                                .addComponent(epalText)
                                .addComponent(adrBox))
                        .addGroup(middleGroup
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(date)
                                .addComponent(printDate)
                                .addComponent(comment)
                                .addComponent(commentText)
                                .addComponent(roundtrip))
                        .addGroup(middleGroup
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(liquid))
                        .addGroup(middleGroup
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(adrBox))
                        .addGroup(middleGroup
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(roundtrip))
                        .addComponent(scrollPaneDataTransport));

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Erstellen der Buttons und Zuweisung der ActionListener
        JButton saveButton = new JButton("Anlegen");
        JButton backButton = new JButton("Hauptmenü");
        JButton dispoButton = new JButton("Disposition");
        backButton.addActionListener(f -> goMainMenue());
        saveButton.addActionListener(e -> saveTransport());
        dispoButton.addActionListener(g -> goDisposition());

        // Dem bottomPanel zuweisen
        bottomPanel.add(saveButton);
        bottomPanel.add(dispoButton);
        bottomPanel.add(backButton);

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


    private void saveTransport() {
        String selectedOptionShipper = (String) shipperComboBox.getSelectedItem();
        String selectedOptionRecipient = (String) recipientComboBox.getSelectedItem();

        try {
            java.sql.Date lBegin = parseDateTime(loadBeginTime.getText());
            java.sql.Date lEnd = parseDateTime(loadEndTime.getText());
            int pitches = Integer.parseInt(pitchesText.getText());
            boolean isliquid = liquid.isSelected();
            int bdfRef = Integer.parseInt(bdf_referenceText.getText());
            java.sql.Date dcBegin = parseDateTime(dischargeBeginText.getText());
            java.sql.Date dcEnd = parseDateTime(dischargeEndText.getText());
            int epal = Integer.parseInt(epalText.getText());
            boolean isAdr = adrBox.isSelected();
            String comment = commentText.getText();
            boolean isRoundtrip = roundtrip.isSelected();
            java.sql.Date date = parseDate(printDate.getText());

            new DbStatements().addShipping(bdfRef, date, selectedOptionShipper, selectedOptionRecipient, lBegin, lEnd, dcBegin, dcEnd, pitches, epal, isliquid, isAdr, isRoundtrip, comment);
            JOptionPane.showMessageDialog(this, "Transport angelegt!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ein unerwarteter Fehler ist aufgetreten!");
            log.error(e.getMessage());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ungültiges Datumsformat! dd.MM.yyyy hh:mm", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    private java.sql.Date parseDateTime(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        java.util.Date utilDate = dateFormat.parse(dateString);
        return new java.sql.Date(utilDate.getTime());
    }

    private java.sql.Date parseDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date utilDate = dateFormat.parse(dateString);
        return new java.sql.Date(utilDate.getTime());
    }

    private void goDisposition() {
        new Disposition();
        dispose();
    }

    private void goMainMenue() {
        new MainWindow();
        dispose();
    }

}