import dto.Kunde;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbQueries;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NewBillingWindow extends JFrame {
    Logger log = LoggerFactory.getLogger(this.getClass());
    JTextField billingNumber;
    JTextField date;
    private final JComboBox<String> recipientComboBox;
    JTextField total;

    public NewBillingWindow() {
        setTitle("Neue Rechnung");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/media/kunIco.jpg");
        setIconImage(icon.getImage());

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel midPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        JPanel bottomPanel = new JPanel(new FlowLayout());
        // Titel für das Transportmanagement
        JLabel headline = new JLabel("Rechnungswesen");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Rechnung Anlegen");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);
        /*
         *   TODO
         *      Frontend erstellen
         *      K&N-Lieferscheinnummer als Rechnungsnummer [x]
         *      Rechnungsdatum (Auto. Generate bei Rechnungsstellung?)
         *      Firma == Empfänger
         *      Betrag errechnet sich aus Fahrzeug, Stellplätze, Preis p.Palette & Entfernung
         *                          !!!GRÖßEN ÜBERARBEITEN!!!
         */

        JLabel invoiceNumber = new JLabel("Rechnungsnummer");
        billingNumber = new JTextField();
        JLabel invoiceDate = new JLabel("Rechnungsdatum");
        date = new JTextField();
        JLabel recipient = new JLabel("Empfänger:");
        recipientComboBox = new JComboBox<>();
        populateRecipientComboBox();
        JLabel totalAmount = new JLabel("Betrag in Euro");
        total = new JTextField();

        midPanel.add(invoiceNumber);
        midPanel.add(billingNumber);
        midPanel.add(invoiceDate);
        midPanel.add(date);
        midPanel.add(recipient);
        midPanel.add(recipientComboBox);
        midPanel.add(totalAmount);
        midPanel.add(total);

        JButton saveButton = new JButton("Speichern");
        JButton closeButton = new JButton("Schließen");
        closeButton.addActionListener(_ -> close());
        saveButton.addActionListener(_ -> saveBill());

        bottomPanel.add(saveButton);
        bottomPanel.add(closeButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(midPanel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);

    }
    private void populateRecipientComboBox(){
        try {
            ArrayList<Kunde> recipientList = new DbQueries().getKunden();
            if (recipientList != null && !recipientList.isEmpty()){
                recipientComboBox.removeAllItems();
                recipientComboBox.addItem("Empfänger wählen");

                for (Kunde shipper : recipientList){
                    recipientComboBox.addItem(String.valueOf(shipper));
                }
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, STR."Fehler beim abrufen der Datensätze Empfänger!\n\{ex.getMessage()}");
            log.error(STR."Fehler beim abrufen der Datensätze für Empfänger!\{ex.getMessage()}");
        }
    }

    private void saveBill() {
        // Prep. Statement Rechnung Neu
        JOptionPane.showMessageDialog(this, "Datensatz gespeichert!");
    }

    private void close() {
        dispose();
    }

}
