import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    public static MySqlconnector mySqlconnector;

    public LoginWindow(MySqlconnector mySqlconnector) {
        super("Login");
        LoginWindow.mySqlconnector = mySqlconnector;
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
                String username = getUsername();
                String password = getPassword();

                try {
                    mySqlconnector.connect(username, password);
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

    public String getUsername() {
        return usernameField.getText();
    }

    public void setUsername(String username) {
        usernameField.setText(username);
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setPassword(String password) {
        passwordField.setText(password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            MySqlconnector mySqlconnector = new MySqlconnector("jdbc:mysql://localhost:3306/test");

            @Override
            public void run() {
                new LoginWindow(mySqlconnector);
            }
        });
    }
}
