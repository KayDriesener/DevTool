import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUserWindow extends JFrame {

    public EditUserWindow() {
        setTitle("Edit User");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das Usermanagement
        JLabel newUser = new JLabel("USERMANAGEMENT");
        newUser.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel newUserEdit = new JLabel("Benutzer Bearbeiten");
        newUserEdit.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(newUser, BorderLayout.NORTH);
        topPanel.add(newUserEdit, BorderLayout.CENTER);

        // Mittleres Panel mit GridLayout für die Tabelle
        JPanel middlePanel = new JPanel(new GridLayout(1, 1, 5, 5));

        // Benutzerdefinierte Daten für die Tabelle aus der Datenbank "SHEMA DisTool TABLE user"
        Object[][] data = {
                {1, "Max", "Mustermann", "max@example.com", "max123"},
                {2, "Anna", "Musterfrau", "anna@example.com", "anna456"},
                // Anbindung an die Datenbank. Get Text.
        };

        // Benutzerdefinierte Spaltenüberschriften
        Object[] columnNames = {"ID", "Name", "Nachname", "EMail", "Username"};

        JTable table = new JTable(data, columnNames);

        // Tabelle auf die Spalten aufteilen
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200)); // Größe anpassen

        middlePanel.add(scrollPane);

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Buttons für Aktionen
        JButton deleteButton = new JButton("Löschen");
        JButton saveButton = new JButton("Speichern");
        JButton mainMenuButton = new JButton("Hauptmenü");

        // Aktionen für die Buttons hinzufügen (Dummy-Implementierung)
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent m) {
                goMainMenu();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                deleteUser();
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

    private void deleteUser() {
        // TODO Prep.Stmt
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
        SwingUtilities.invokeLater(() -> new EditUserWindow());
    }
}
