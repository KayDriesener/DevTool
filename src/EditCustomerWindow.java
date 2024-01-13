import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCustomerWindow extends JFrame {

    public EditCustomerWindow() {
        setTitle("Kunde Bearbeiten");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das Usermanagement
        JLabel headline = new JLabel("KUNDENMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Kunde Bearbeiten");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);

        // Mittleres Panel mit GridLayout für die Tabelle
        JPanel middlePanel = new JPanel(new GridLayout(1, 1, 5, 5));

        /*  Benutzerdefinierte Daten für die Tabelle aus der Datenbank "SHEMA DisTool
        TABLE user" */
        Object[][] data = {
                { 1, "K&N", "Heykenaukamp", "10", "21149", "Hamburg", "AuV", "Mustermann", "012 123456", "blha@blha.de"},
                { 2, "Tesa", "Heykenaukamp", "10", "21149", "Hamburg", "AuV", "Mustermann", "012 123456", "blha@blha.de"},
                { 3, "Beiersdorf", "Heykenaukamp", "10", "21149", "Hamburg", "AuV", "Mustermann", "012 123456", "blha@blha.de"},
                // Anbindung an die Datenbank. Get Text.
        };

        // Benutzerdefinierte Spaltenüberschriften
        Object[] columnNames = { "ID", "Firma", "Straße", "Nr.", "PlZ", "Ort", "Abteilung", "Ansprechpartner", "Telefon", "EMail"};

        JTable table = new JTable(data, columnNames);

        // Tabelle auf die Spalten aufteilen
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200)); // Größe anpassen

        middlePanel.add(scrollPane);

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Buttons für Aktionen
        JButton saveButton = new JButton("Speichern");
        JButton deleteButton = new JButton("Löschen");
        JButton mainMenuButton = new JButton("Hauptmenü");

        // Aktionen für die Buttons hinzufügen (Dummy-Implementierung)
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d){
                deleteUser();
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goMainMenu();
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

    private void deleteUser(){
        //TODO PrepStatement
        JOptionPane.showMessageDialog(this, "Datensatz GELÖSCHT!");
    }

    private void saveUser() {
        //TODO Prep.Statement User
        JOptionPane.showMessageDialog(this, "Datensatz GESPEICHERT!");
    }

    private void goMainMenu() {
        new MainWindow();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditCustomerWindow());
    }
}
