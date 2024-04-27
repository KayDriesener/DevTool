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
        ImageIcon icon = new ImageIcon("src/media/kunIco.jpg");
        setIconImage(icon.getImage());

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5)); // Unbegrenzte Zeilen, 2 Spalten, Abstand zwischen den Zellen: 5 Pixel

        // Titel für das User management
        JLabel headline = new JLabel("USER MANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel subHeadline = new JLabel("Neuen Benutzer beantragen.");
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

        // Checkboxen für die verschiedenen Rollen
        JCheckBox select = new JCheckBox("Select", false);
        JCheckBox insert = new JCheckBox("Insert", false);
        JCheckBox update = new JCheckBox("Update", false);
        JCheckBox delete = new JCheckBox("Delete", false);
        JCheckBox execute = new JCheckBox("Execute", false);
        JCheckBox all = new JCheckBox("Alles auswählen", false);


        // Fügt Checkboxen dem Panel hinzu
        panel.add(select);
        panel.add(insert);
        panel.add(update);
        panel.add(delete);
        panel.add(execute);
        panel.add(all);

        // Button erstellen und dem panel zuweisen
        JButton saveButton = new JButton("Speichern");
        JButton closeButton = new JButton("Schließen");
        saveButton.addActionListener(e -> saveUser());
        closeButton.addActionListener(f -> close());
        panel.add(saveButton);
        panel.add(closeButton);

        add(panel);
        setVisible(true);
    }

    // Übermitteln der Daten an die Datenbank und Fehlerbehandlung
    private void saveUser() {
        try {
            new DbStatements().addUser(tfName.getText(), tsName.getText(), tEMail.getText(), tUName.getText());
            JOptionPane.showMessageDialog(this, "Datensatz gespeichert!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Beim Anlegen eines neuen Benutzers ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }    }
    private void close(){
        dispose();
    }
}
