import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Hauptmenü");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridBagLayout());

        // Usermanagement
        addSection(topPanel, "User management", "Neuer User", "User Bearbeiten");

        // Fahrzeugmanagement
        addSection(topPanel, "Fahrzeugmanagement", "Neues Fahrzeug", "Fahrzeug Bearbeiten");

        // Transportmanagement
        addSection(topPanel, "Transportmanagement", "Neuer Transport", "Transport Bearbeiten");

        // Rechnungswesen
        addSection(topPanel, "Rechnungswesen", "Rechnung Erstellen", "Rechnung Bearbeiten");

        // Kundenmanagement
        addSection(topPanel, "Kundenmanagement", "Kunden Anlegen", "Kundendaten Bearbeiten");

        //Configuration
        addSection(topPanel, "Configuration", "Preise Paletten", "Entfernungen");

        //Auswertungen
        addSection(topPanel, "Auswertung");

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
        SwingUtilities.invokeLater(NewUserWindow::new);
    }

    private void openEditUserWindow() {
        SwingUtilities.invokeLater(EditUserWindow::new);
    }

    private void openFahrzeugWindow() {
        SwingUtilities.invokeLater(NewFahrzeugWindow::new);
    }

    private void openEditFahrzeugWindow() {
        SwingUtilities.invokeLater(EditFahrzeugWindow::new);
    }

    private void openTransportWindow() {
        SwingUtilities.invokeLater(NewTransportWindow::new);
    }

    private void openEditTransportWindow() {
        SwingUtilities.invokeLater(EditTransportWindow::new);
    }

    private void openNewBillingWindow() {
        SwingUtilities.invokeLater(NewBillingWindow::new);
    }

    private void openEditBillingWindow() {
        SwingUtilities.invokeLater(EditBilllingWindow::new);
    }

    private void openNewCustomerWindow() {
        SwingUtilities.invokeLater(NewCustomerWindow::new);
    }

    private void openEditCustomerWindow() {
        SwingUtilities.invokeLater(EditCustomerWindow::new);
    }

    //TODO Nur zu Testzwecken. Im Liveprogramm aus allen Fenstern entfernen.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
// TODO LOGOUTBUTTON -> con.close() && openLoginWindow() && dispose()