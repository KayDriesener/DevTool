package prog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbStatements;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class NewCustomerWindow extends JFrame {
    Logger log = LoggerFactory.getLogger(this.getClass());
    JTextField tfName;
    JTextField tsStrs;
    JTextField tHnr;
    JTextField tPlz;
    JTextField uOrt;
    JTextField tAbt;
    JTextField tResPers;
    JTextField tFon;
    JTextField tEMail;
    JTextArea tNote;

    public NewCustomerWindow() {
        setTitle("Kunden Anlegen");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon icon = new ImageIcon("src/media/kunIco.jpg");
        setIconImage(icon.getImage());

        // Unbegrenzte Zeilen -----, 2 Spalten |||, Abstand zwischen den Zellen: 5 Pixel ↔
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        // Titel für das User management
        JLabel headline = new JLabel("KUNDENMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel subHeadline = new JLabel("Neuer Kunde");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(headline);
        panel.add(subHeadline);

        JButton saveButton = new JButton("Speichern");
        JButton closeButton = new JButton("Schließen");
        closeButton.addActionListener(_ -> close());
        saveButton.addActionListener(_ -> saveCstmr());

        // Feld für den Firmennamen
        JLabel fname = new JLabel("Firma");
        tfName = new JTextField();
        panel.add(fname);
        panel.add(tfName);

        // Feld für die Straße
        JLabel sStr = new JLabel("Straße");
        tsStrs = new JTextField();
        panel.add(sStr);
        panel.add(tsStrs);

        // Feld für die Hausnummer
        JLabel hnr = new JLabel("Nr.");
        tHnr = new JTextField();
        panel.add(hnr);
        panel.add(tHnr);

        // Feld für die PlZ
        JLabel uPlz = new JLabel("PlZ");
        tPlz = new JTextField();
        panel.add(uPlz);
        panel.add(tPlz);

        // Feld für den Ort
        JLabel ort = new JLabel("Ort");
        uOrt = new JTextField();
        panel.add(ort);
        panel.add(uOrt);

        // Feld für die Abteilung
        JLabel abt = new JLabel("Abteilung");
        tAbt = new JTextField();
        panel.add(abt);
        panel.add(tAbt);

        // Feld für den Ansprechpartner
        JLabel resPers = new JLabel("Ansprechpartner");
        tResPers = new JTextField();
        panel.add(resPers);
        panel.add(tResPers);

        // Feld für die Erreichbarkeit Telefon
        JLabel fon = new JLabel("Telefon");
        tFon = new JTextField();
        panel.add(fon);
        panel.add(tFon);

        // Feld für die Erreichbarkeit E-Mail
        JLabel eMail = new JLabel("EMail");
        tEMail = new JTextField();
        panel.add(eMail);
        panel.add(tEMail);

        // Textfeld für Bemerkungen
        JLabel note = new JLabel("Bemerkungen");
        tNote = new JTextArea();
        panel.add(note);
        panel.add(tNote);

        add(panel);
        panel.add(saveButton);
        panel.add(closeButton);
        setVisible(true);

    }

    /*
     * Übergeben der eingegebenen Daten an die Datenbanktabelle über ein prepared Statement
     * in "DbStatements.java"
     */
    private void saveCstmr() {
        try {
            new DbStatements().addKunde(tfName.getText(), tsStrs.getText(), tHnr.getText(), tPlz.getText(), uOrt.getText(), tAbt.getText(), tResPers.getText(), tFon.getText(), tEMail.getText(), tNote.getText());
            JOptionPane.showMessageDialog(this, "Datensatz gespeichert!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Beim Anlegen eines neuen Kunden ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }
    }

    private void close() {
        dispose();
    }

}
