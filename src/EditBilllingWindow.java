import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* feature/nsc/drucken
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.awt.event.MouseEvent;
main*/

public class EditBilllingWindow extends JFrame {

    JTable table;

    public EditBilllingWindow() {
        setTitle("Rechnung Bearbeiten");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das Usermanagement
        JLabel headline = new JLabel("USERMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Rechnung Bearbeiten");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);

        // Mittleres Panel mit GridLayout für die Tabelle
        JPanel middlePanel = new JPanel(new GridLayout(1, 1, 5, 5));

        /*
         * Benutzerdefinierte Daten für die Tabelle aus der Datenbank "SHEMA DisTool
         * TABLE user"
         */
        /* TODO DATUM UND BETRAG EINFÜGEN */
        Object[][] data = {
                {100101, "DD-MM-YYYY", "K&N", "21,149"},
                {100102, "DD-MM-YYYY", "Tesa", "31,149"},
                {100103, "DD-MM-YYYY", "Beiersdorf", "11,149"},
                // Anbindung an die Datenbank. Get Text.
        };

        // Benutzerdefinierte Spaltenüberschriften
        Object[] columnNames = {"RechnungsNr.", "Datum", "Firma", "Betrag"};

        table =  new JTable(data, columnNames){
            public String getToolTipText(MouseEvent e) {
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                String valueAtMousePointer = getValueAt(rowIndex, colIndex).toString();

/* feature/nsc/drucken
        table = new JTable(data, columnNames);
                if(colIndex == 1 && valueAtMousePointer.isEmpty()) {
                    return "Wert in Spalte 2 fehlerhaft: Der eingegebene Wert darf nicht leer sein!";
                }

                return null;
            }
        };
 main*/

        // Tabelle auf die Spalten aufteilen
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200)); // Größe anpassen

        middlePanel.add(scrollPane);

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Buttons für Aktionen
        JButton saveButton = new JButton("Speichern");
        JButton deleteButton = new JButton("Löschen");
        JButton mainMenuButton = new JButton("Hauptmenü");
        JButton printButton = new JButton("Drucken");

        // Aktionen für die Buttons hinzufügen (Dummy-Implementierung)
        saveButton.addActionListener(e -> saveBill());
        deleteButton.addActionListener(d -> deleteBill());
        printButton.addActionListener(f -> printBill());
        mainMenuButton.addActionListener(g -> goMainMenu());

        // Hinzufügen der Buttons zum bottom panel
        bottomPanel.add(deleteButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(printButton);
        bottomPanel.add(mainMenuButton);

        // Hauptpanel mit BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Hinzufügen der panel's zum main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void deleteBill() {
        // TODO PrepStatement
        JOptionPane.showMessageDialog(this, "Datensatz GELÖSCHT!");
    }

    private void saveBill() {
        // TODO Prep.Statement
        JOptionPane.showMessageDialog(this, "Datensatz GESPEICHERT!");
    }

    private void printBill() {
        MessageFormat header = new MessageFormat("Rechnungen");
        MessageFormat footer = new MessageFormat("Seite 1");
        PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
        // Querformat
        set.add(OrientationRequested.LANDSCAPE);
        try {
            table.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, false);
            JOptionPane.showMessageDialog(this, "Datensatz GEDRUCKT!");
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(this, "Fehler beim Drucken des Datensatzes");
        }
    }

    private void goMainMenu() {
        new MainWindow();
        dispose();
    }
}
