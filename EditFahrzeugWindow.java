import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditFahrzeugWindow extends JFrame {
    public EditFahrzeugWindow() {
        setTitle("Fahrzeug Bearbeiten");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das Usermanagement
        JLabel headline = new JLabel("FAHRZEUGMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Fahrzeug Bearbeiten");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);

        // Mittleres Panel mit GridLayout für die Tabelle
        JPanel middlePanel = new JPanel(new GridLayout(0, 1, 5, 5));

        Object[][] dataZm = {
                { "MTS1", "MTS", "Zugmaschiene", "564,00"},
                // Anbindung an die Datenbank. Get Text.
        };

        Object[][] dataTrailer = {
            {"WL-QY 1667", "TIP", "Trailer", "Koffer", "29,63", "Datum", "Datum"},
            { "HH-TP 5534", "TIP", "Trailer", "Plane", "23,29", "Datum", "Datum"},
        };

        // Benutzerdefinierte Spaltenüberschriften
        Object[] columnNamesZm = { "Kennzeichen", "Anbieter", "Art",  "Miete"};
        Object[] columnNamesTrailer = { "Kennzeichen", "Anbieter", "Art", "Typ", "Miete", "Sicherheitsprüfung", "TÜV"};

        JTable tableZm = new JTable(dataZm, columnNamesZm);
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

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a){
                saveFahrzeug();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent b) {
                deleteFahrzeug();
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent g) {
                goMainMenue();
            }
        });


        bottomPanel.add(deleteButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(mainMenuButton);

        // Hauptpanel mit BorderLayout
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
