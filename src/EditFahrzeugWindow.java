import dto.Fahrzeug;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbQueries;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditFahrzeugWindow extends JFrame {
    Logger log = LoggerFactory.getLogger(this.getClass());
    public EditFahrzeugWindow() {
        setTitle("Fahrzeug Bearbeiten");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

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

        ArrayList<Fahrzeug> zmList = null;
        try {
            zmList = new DbQueries().getFahrzeugZm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Beim Abrufen der Fahrzeugdaten ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }

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

        ArrayList<Fahrzeug> tList = null;
        try {
            tList = new DbQueries().getFahrzeugT();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Beim Abrufen der Fahrzeugdaten ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }

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
        Object[] columnNamesZm = {"ID", "Anbieter", "Kennzeichen", "Art", "Miete", "Prüfungen", "Tüv", "Kostenstelle"};
        Object[] columnNamesTrailer = {"ID", "Anbieter", "Kennzeichen", "Art", "Miete", "Prüfungen", "Tüv", "Kostenstelle"};

        JTable tableZm = new JTable(zmData, columnNamesZm);
        JTable tableTrailer = new JTable(dataTrailer, columnNamesTrailer);

        // Tabelle auf die Spalten aufteilen
        JScrollPane scrollPaneZm = new JScrollPane(tableZm);
        JScrollPane scrollPaneTrailer = new JScrollPane(tableTrailer);
        scrollPaneZm.setPreferredSize(new Dimension(400, 200)); // Größe anpassen

        middlePanel.add(scrollPaneZm);
        middlePanel.add(scrollPaneTrailer);

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Buttons für Aktionen
        JButton saveButton = new JButton("Speichern");
        JButton deleteButton = new JButton("Löschen");
        JButton mainMenuButton = new JButton("Hauptmenü");

        saveButton.addActionListener(a -> saveFahrzeug());
        deleteButton.addActionListener(b -> deleteFahrzeug());
        mainMenuButton.addActionListener(g -> goMainMenue());


        bottomPanel.add(deleteButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(mainMenuButton);

        // Haupt panel mit BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

    }

    private void saveFahrzeug() {
        // Prep.Statement Rechnung
        JOptionPane.showMessageDialog(this, "Datensatz GESPEICHERT!");
    }

    private void deleteFahrzeug() {
        JOptionPane.showMessageDialog(this, "Datensatz GELÖSCHT");
    }

    private void goMainMenue() {
        new MainWindow();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EditFahrzeugWindow::new);
    }
}
