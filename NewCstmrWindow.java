import javax.swing.*;
import java.awt.*;

public class NewCstmrWindow extends JFrame {
    public NewCstmrWindow() {
        setTitle("Kunden Anlegen");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Unbegrenzte Zeilen, 2 Spalten, Abstand zwischen den Zellen: 5 Pixel
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        // Titel für das Usermanagement
        JLabel headline = new JLabel("KUNDENMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel subHeadline = new JLabel("Neuer Kunde");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(headline);
        panel.add(subHeadline);

        JButton saveButton = new JButton("Speichern");
        JButton backButton = new JButton("Hauptmenü");
        backButton.addActionListener(f -> goMainMenue());
        saveButton.addActionListener(e -> saveCstmr());

        // Feld für den Firmennamen
        JLabel fname = new JLabel("Firma");
        JTextField tfName = new JTextField();
        panel.add(fname);
        panel.add(tfName);

        // Feld für die Straße
        JLabel sStr = new JLabel("Straße");
        JTextField tsStrs = new JTextField();
        panel.add(sStr);
        panel.add(tsStrs);

        // Feld für die Hausnummer
        JLabel hnr = new JLabel("Nr.");
        JTextField tHnr = new JTextField();
        panel.add(hnr);
        panel.add(tHnr);

        // Feld für die PlZ
        JLabel uPlz = new JLabel("PlZ");
        JTextField tPlz = new JTextField();
        panel.add(uPlz);
        panel.add(tPlz);

        // Feld für den Ort
        JLabel ort = new JLabel("Ort");
        JTextField uOrt = new JTextField();
        panel.add(ort);
        panel.add(uOrt);

        // Feld für die Abteilung
        JLabel abt = new JLabel("Abteilung");
        JTextField tAbt = new JTextField();
        panel.add(abt);
        panel.add(tAbt);

        // Feld für den Ansprechpartner
        JLabel resPers = new JLabel("Ansprechpartner");
        JTextField tResPers = new JTextField();
        panel.add(resPers);
        panel.add(tResPers);

        // Feld für die Erreichbarkeit Telefon
        JLabel fon = new JLabel("Telefon");
        JTextField tFon = new JTextField();
        panel.add(fon);
        panel.add(tFon);

        // Feld für die Erreichbarkeit EMail
        JLabel eMail = new JLabel("EMail");
        JTextField tEMail = new JTextField();

        panel.add(eMail);
        panel.add(tEMail);

        // Textfeld für Bemerkungen
        //TODO Scrollbar machen und die Textfelxer schmaler
        JLabel note = new JLabel("Bemerkungen");
        JTextArea tNote = new JTextArea();
        panel.add(note);
        panel.add(tNote);

        add(panel);
        panel.add(saveButton);
        panel.add(backButton);
        setVisible(true);

    }

    private void saveCstmr() {
        // Prep.Statement Kunde Neu
        JOptionPane.showMessageDialog(this, "Datensatz GESPEICHERT!");
    }

    private void goMainMenue() {
        new MainWindow();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NewCstmrWindow());
    }
}
