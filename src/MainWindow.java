import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Übersicht");
        setSize(900, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridBagLayout());

        // Usermanagement
        addSection(topPanel, "Usermanagement", "Neuer User", "User Bearbeiten");

        // Fahrzeugmanagement
        addSection(topPanel, "Fahrzeugmanagement", "Neues Fahrzeug", "Fahrzeug Bearbeiten");

        // Transportmanagement
        addSection(topPanel, "Transportmanagement", "Neuer Transport", "Transport Bearbeiten");

        // Rechnungswesen
        addSection(topPanel, "Rechnungswesen", "Rechnung Erstellen", "Rechnung Bearbeiten");

        // Kundenmanagement
        addSection(topPanel, "Kundenmanagement", "Kunden Anlegen", "Kundendaten Bearbeiten");

        add(topPanel);
        setVisible(true);
    }

    private void addSection(JPanel panel, String label, String... buttons) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel sectionLabel = new JLabel(label);
        gbc.gridy++;
        panel.add(sectionLabel, gbc);

        for (String buttonText : buttons) {
            JButton button = createButton(buttonText);
            button.addActionListener(e -> buttonClicked(buttonText));
            gbc.gridy++;
            panel.add(button, gbc);
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 30));
        return button;
    }

    private void buttonClicked(String buttonText) {
        switch (buttonText) {
            case "Neuer User":
                openUserWindow();
                break;
            case "User Bearbeiten":
                openEditUserWindow();
                break;
            case "Neues Fahrzeug":
                openFahrzeugWindow();
                break;
            case "Fahrzeug Bearbeiten":
                openEditFahrzeugWindow();
                break;
            case "Neuer Transport":
                openTransportWindow();
                break;
            case "Transport Bearbeiten":
                openEditTransportWindow();
                break;
            case "Rechnung Erstellen":
                openNewBillingWindow();
                break;
            case "Rechnung Bearbeiten":
                openEditBillingWindow();
                break;
            case "Kunden Anlegen":
                openNewCustomerWindow();
                break;
            case "Kundendaten Bearbeiten":
                openEditCustomerWindow();
                break;
        }
        setVisible(false);
    }

    private void openUserWindow() {
        SwingUtilities.invokeLater(() -> new NewUserWindow());
    }

    private void openEditUserWindow() {
        SwingUtilities.invokeLater(() -> new EditUserWindow());
    }

    private void openFahrzeugWindow() {
        SwingUtilities.invokeLater(() -> new NewFahrzeugWindow());
    }

    private void openEditFahrzeugWindow() {
        SwingUtilities.invokeLater(() -> new EditFahrzeugWindow());
    }

    private void openTransportWindow() {
        SwingUtilities.invokeLater(() -> new NewTransportWindow());
    }

    private void openEditTransportWindow() {
        SwingUtilities.invokeLater(() -> new EditTransportWindow());
    }

    private void openNewBillingWindow() {
        SwingUtilities.invokeLater(() -> new NewBillingWindow());
    }

    private void openEditBillingWindow() {
        SwingUtilities.invokeLater(() -> new EditBilllingWindow());
    }

    private void openNewCustomerWindow() {
        SwingUtilities.invokeLater(() -> new NewCustomerWindow());
    }

    private void openEditCustomerWindow() {
        SwingUtilities.invokeLater(() -> new EditCustomerWindow());
    }

    //TODO Nur zu Testzwecken. Im Liveprogramm aus allen Fenstern entfernen.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}
// TODO LOGOUTBUTTON -> con.close() && openLoginWindow() && dispose()