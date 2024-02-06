import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

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
                { 100101, "DD-MM-YYYY", "K&N", "21,149" },
                { 100102, "DD-MM-YYYY", "Tesa", "31,149" },
                { 100103, "DD-MM-YYYY", "Beiersdorf", "11,149" },
                // Anbindung an die Datenbank. Get Text.
        };

        // Benutzerdefinierte Spaltenüberschriften
        Object[] columnNames = { "RechnungsNr.", "Datum", "Firma", "Betrag" };

        table =  new JTable(data, columnNames){
            public String getToolTipText(MouseEvent e) {
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                String valueAtMousePointer = getValueAt(rowIndex, colIndex).toString();

                if(colIndex == 1 && valueAtMousePointer.isEmpty()) {
                    return "Wert in Spalte 2 fehlerhaft: Der eingegebene Wert darf nicht leer sein!";
                }

                return null;
            }
        };

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
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBill();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                deleteBill();
            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                printBill();
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent g) {
                goMainMenu();
            }
        });

        bottomPanel.add(deleteButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(printButton);
        bottomPanel.add(mainMenuButton);

        // Hauptpanel mit BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

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
        // TODO PrepStatement
        JOptionPane.showMessageDialog(this, "Datensatz GEDRUCKT!");
    }

    private void goMainMenu() {
        new MainWindow();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditBilllingWindow());
    }
}
