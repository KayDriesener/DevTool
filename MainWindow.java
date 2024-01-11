import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Übersicht");
        setSize(900, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel toppanel = new JPanel(new GridBagLayout());

        // Usermanagement
        addSection(toppanel, "Usermanagement", "Neuer User", "User Bearbeiten");

        // Fahrzeugmanagement
        addSection(toppanel, "Fahrzeugmanagement", "Neues Fahrzeug", "Fahrzeug Bearbeiten");

        // Transportmanagement
        addSection(toppanel, "Transportmanagement", "Neuer Transport", "Transport Bearbeiten");

        // Rechnungswesen
        addSection(toppanel, "Rechnungswesen", "Rechnung Erstellen", "Rechnung Bearbeiten");

        // Kundenmanagement
        addSection(toppanel, "Kundenmanagement", "Kunden Anlegen", "Kundendaten Bearbeiten");

        add(toppanel);
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
                setVisible(false);
                break;
            case "User Bearbeiten":
                openEditUserWindow();
                setVisible(false);
                break;
            case "Neues Fahrzeug":
                openFahrzeugWindow();
                setVisible(false);
                break;
            case "Fahrzeug Bearbeiten":
                openEditFahrzeugWindnow();
                setVisible(false);
                break;
            case "Neuer Transport":
                openTransportWindow();
                setVisible(false);
                break;
            case "Transport Bearbeiten":
                openEditTransportWindow();
                setVisible(false);
                break;
            case "Rechnung Erstellen":
                openNewBillingWidnow();
                setVisible(false);
                break;
            case "Rechnung Bearbeiten":
                openEditBillingWindow();
                setVisible(false);
                break;
            case "Kunden Anlegen":
                openNewCstmrWindow();
                setVisible(false);
                break;
            case "Kundendaten Bearbeiten":
                openEditCstmrWindow();
                setVisible(false);
                break;
        }
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

    private void openEditFahrzeugWindnow() {
        SwingUtilities.invokeLater(() -> new EditFahrzeugWindow());
    }

    private void openTransportWindow() {
        SwingUtilities.invokeLater(() -> new NewTransportWindow());
    }

    private void openEditTransportWindow() {
        SwingUtilities.invokeLater(() -> new EditTransportWindow());
    }

    private void openNewBillingWidnow() {
        SwingUtilities.invokeLater(() -> new NewBillingWindow());
    }

    private void openEditBillingWindow() {
        SwingUtilities.invokeLater(() -> new EditBilllingWindow());
    }

    private void openNewCstmrWindow() {
        SwingUtilities.invokeLater(() -> new NewCstmrWindow());
    }

    private void openEditCstmrWindow() {
        SwingUtilities.invokeLater(() -> new EditCstmrWindow());
    }

    //TODO Nur zu Testzwecken. Im Liveprogramm aus allen Fenstern entfernen.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
// TODO LOGOUTBUTTON -> con.close() && openLoginWindow() && dispose()