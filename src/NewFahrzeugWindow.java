import helpers.Parsing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbStatements;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;



public class NewFahrzeugWindow extends JFrame {
    private final JComboBox<String> artComboBox;
    Logger log = LoggerFactory.getLogger(this.getClass());
    JTextField tAnbieter;
    JTextField tRegPlate;
    JTextField tFine;
    JTextField tSp;
    JTextField tTuev;
    JTextField tKst;



    public NewFahrzeugWindow() {
        setTitle("Neues Fahrzeug");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das User management
        JLabel headline = new JLabel("FAHRZEUGMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Fahrzeug Anlegen");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);

        // Mittleres Panel mit Gridlayout
        JPanel middlePanel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        JLabel anbieter = new JLabel("Anbieter");
        tAnbieter = new JTextField();
        middlePanel.add(anbieter);
        middlePanel.add(tAnbieter);
        JLabel regPlate = new JLabel("Kennzeichen");
        tRegPlate = new JTextField();
        middlePanel.add(regPlate);
        middlePanel.add(tRegPlate);
        JLabel art = new JLabel("ZM/ Trailer");
        // Combo box statt Text field. Vorgaben für eine einfachere Nutzbarkeit. (Fehler minimieren)
        String[] options = {"Bitte auswählen", "Zugmaschine", "Trailer"};
        artComboBox = new JComboBox<>(options);
        middlePanel.add(art);
        middlePanel.add(artComboBox);
        JLabel fine = new JLabel("Miete/ Tag in Euro");
        tFine = new JTextField();
        middlePanel.add(fine);
        middlePanel.add(tFine);
        JLabel sp = new JLabel("Sicherheitsprüfung fällig am");
        tSp = new JTextField();
        middlePanel.add(sp);
        middlePanel.add(tSp);
        JLabel tuev = new JLabel("TÜV gültig bis");
        tTuev = new JTextField();
        middlePanel.add(tuev);
        middlePanel.add(tTuev);
        JLabel kst = new JLabel("Kostenstelle");
        tKst = new JTextField();
        middlePanel.add(kst);
        middlePanel.add(tKst); 

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Erstellen der Buttons und Zuweisung des bottom panels
        JButton saveButton = new JButton("Speichern");
        JButton closeButton = new JButton("Schließen");
        closeButton.addActionListener(_ -> close());
        saveButton.addActionListener(_ -> saveFahrzeug());

        bottomPanel.add(saveButton);
        bottomPanel.add(closeButton);
         

        // Haupt panel mit BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

    }

    // Übergeben der Daten an die Datenbank.
    private void saveFahrzeug() {
        String selectedOption = (String) artComboBox.getSelectedItem();

        try {
            /*
             * Parsen der Datentypen um eine fehlerfreie Datenverarbeitung zu gewährleisten.
             * Date parser sind ausgelagert, um den Code lesbarer und kleiner zu halten.
             */
            String anbieter = tAnbieter.getText();
            String regPlate = tRegPlate.getText();
            float fine = Float.parseFloat(tFine.getText());
            java.sql.Date spDate = Parsing.parseDate(tSp.getText());
            java.sql.Date tuevDate = Parsing.parseDate(tTuev.getText());
            Integer kst = Integer.parseInt(tKst.getText());

            /*
            * Abfrage was in der ComboBox ausgewählt wurde.
            * Je nach Ergebnis der Abfrage (equals(selectedOption)) wird der Datensatz an die entsprechende Tabelle übertragen.
            * Zuweisung der Daten zu der entsprechenden Datenbank, nach Ergebnis der Kontrollstruktur
            * und Fehlerbehandlung.
            */
            if ("Zugmaschine".equals(selectedOption)) {
                try {
                    new DbStatements().addFahrzeugZm(anbieter, regPlate, selectedOption, fine, spDate, tuevDate, kst);
                    JOptionPane.showMessageDialog(this, "Datensatz gespeichert!");
                }catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Beim Anlegen der Zugmaschine ist ein Fehler aufgetreten.");
                    log.error(e.getMessage());
                }
            } else if ("Trailer".equals(selectedOption)) {
                try {
                    new DbStatements().addFahrzeugT(anbieter, regPlate, selectedOption, fine, spDate, tuevDate, kst);
                    JOptionPane.showMessageDialog(this, "Datensatz gespeichert!");
                }catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Beim Anlegen des Trailers ist ein Fehler aufgetreten.");
                    log.error(e.getMessage());
                }
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Ungültiges Zahlenformat!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ungültiges Datumsformat! dd.MM.yyyy", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void close() {
        dispose();
    }
}
