import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbStatements;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class NewUserWindow extends JFrame {

    Logger log = LoggerFactory.getLogger(this.getClass());
    JTextField tfName;
    JTextField tsName;
    JTextField tEMail;
    JTextField tUName;

    public NewUserWindow() {
        setTitle("Neuer User");
        setSize(800, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5)); // Unbegrenzte Zeilen, 2 Spalten, Abstand zwischen den Zellen: 5 Pixel

        // Titel für das Usermanagement
        JLabel headline = new JLabel("USERMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel subHeadline = new JLabel("Neuer Benutzer");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(headline);
        panel.add(subHeadline);

        // Feld für den Namen
        JLabel fname = new JLabel("Name");
        tfName = new JTextField();
        panel.add(fname);
        panel.add(tfName);

        // Feld für den Nachnamen
        JLabel sName = new JLabel("Nachname");
        tsName = new JTextField();
        panel.add(sName);
        panel.add(tsName);

        // Feld für die E-Mail
        JLabel email = new JLabel("EMail");
        tEMail = new JTextField();
        panel.add(email);
        panel.add(tEMail);

        // Feld für den Benutzernamen
        JLabel uName = new JLabel("Username");
        tUName = new JTextField();
        panel.add(uName);
        panel.add(tUName);

        // Feld für das Passwort
        JLabel pwd = new JLabel("Password");
        JPasswordField uPwd = new JPasswordField();
        panel.add(pwd);
        panel.add(uPwd);

        // Checkboxen für die verschiedenen Admin-Rollen
        JCheckBox dba = new JCheckBox("DBA", false);
        JCheckBox mta = new JCheckBox("Maintenance Admin", false);
        JCheckBox pa = new JCheckBox("Process Admin", false);
        JCheckBox ua = new JCheckBox("User Admin", false);
        JCheckBox sa = new JCheckBox("Security Admin", false);
        JCheckBox ma = new JCheckBox("Monitor Admin", false);
        JCheckBox dbm = new JCheckBox("DB Admin", false);
        JCheckBox dbd = new JCheckBox("DB Designer", false);
        JCheckBox rm = new JCheckBox("Replication Manager", false);
        JCheckBox ba = new JCheckBox("Backup Admin", false);

        // Fügt Checkboxen zum Panel hinzu
        panel.add(dba);
        panel.add(mta);
        panel.add(pa);
        panel.add(ua);
        panel.add(sa);
        panel.add(ma);
        panel.add(dbm);
        panel.add(dbd);
        panel.add(rm);
        panel.add(ba);

        // Button zum Speichern des neuen Benutzers
        JButton saveButton = new JButton("Speichern");
        JButton backButton = new JButton("Hauptmenü");
        saveButton.addActionListener(e -> saveUser());
        backButton.addActionListener(f -> goMainMenue());
        panel.add(saveButton);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    private void saveUser() {
        try {
            new DbStatements().addUser(tfName.getText(), tsName.getText(), tEMail.getText(), tUName.getText());
            JOptionPane.showMessageDialog(this, "Datensatz gespeichert!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Beim Anlegen eines neuen Benutzers ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }
    }
    private void goMainMenue(){
        new MainWindow();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NewUserWindow());
    }
}
