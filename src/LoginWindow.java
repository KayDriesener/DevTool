import sql.MySqlConnector;

import java.awt.*;
import javax.swing.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginWindow() {
        super("Login");
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Login");
        setSize(350, 150);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Erstellen des panels mit Gridlayout( 5 Reihen, 2 Spalten)
        JPanel panel = new JPanel(new GridLayout(5, 2));

        // Erstellen der Komponenten
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = getLoginButton();

        // Komponenten dem panel zuweisen. Leere JLabel dienen als Abstandshalter um das Layout zu ordnen.
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);
        panel.add(new JLabel());

        add(panel);
        setVisible(true);
    }

    /*
    * "Login" Button Objekt erstellen mit der Prüfung auf Richtigkeit der eingegebenen Parameter.
    * Parameter werden mit den Datenbankusern abgeglichen.
    * Bei falschen Eingaben wird eine Fehlerbehandlung ausgeführt.
     */
    private JButton getLoginButton() {
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                MySqlConnector.connect(username, password);
                openMainWindow();
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(LoginWindow.this, "Login failed!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        return loginButton;
    }

    private void openMainWindow() {
        new MainWindow();
    }

    public static void main(String[] args) {
        // Den logger initialisieren. Er könnte bei Bedarf so konfiguriert werden, dass er ein Logfile ins Dateisystem schreibt.
        System.setProperty(org.slf4j.simple.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");

        SwingUtilities.invokeLater(LoginWindow::new);
    }
}
