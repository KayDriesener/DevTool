import sql.MySqlConnector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setSize(400, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Abstandshalter
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    private void openMainWindow() {
        new MainWindow();
    }

    public static void main(String[] args) {
        // Den logger initialiseren. Er könnte bei Bedarf so konfiguriert werden, dass er ein Logfile ins Dateisystem schreibt.
        System.setProperty(org.slf4j.simple.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");

        SwingUtilities.invokeLater(() -> new LoginWindow());
    }
}
