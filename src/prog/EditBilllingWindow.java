package prog;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.awt.event.MouseEvent;


public class EditBilllingWindow extends JFrame {

    JTable table;

    public EditBilllingWindow() {
        setTitle("Rechnung Bearbeiten");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("");
        setIconImage(icon.getImage());

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das Usermanagement
        JLabel headline = new JLabel("RECHNUNGSMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Rechnung Bearbeiten");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);

        // Mittleres Panel mit GridLayout für die Tabelle
        JPanel middlePanel = new JPanel(new GridLayout(1, 1, 5, 5));

        /*
         * Benutzerdefinierte Daten für die Tabelle aus der Datenbank
         * TODO Datensatz aus der Datenbank abrufen
         */
        Object[][] data = {
                {100101, "DD-MM-YYYY", "K&N", "21,149"},
                {100102, "DD-MM-YYYY", "Tesa", "31,149"},
                {100103, "DD-MM-YYYY", "Beiersdorf", "11,149"},
        };

        // Benutzerdefinierte Spaltenüberschriften
        Object[] columnNames = {"RechnungsNr.", "Datum", "Firma", "Betrag in €"};

        /*
         * Tooltip für die Zellen in der Tabelle bei Mouseover.
         */
        table.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                int col = table.columnAtPoint(point);

                if (row >= 0) {
                    Object value = table.getValueAt(row, col);
                    table.setToolTipText((value != null ? value.toString() : null));
                }

            }
        });

        table =  new JTable(data, columnNames){
            public String getToolTipText(MouseEvent e) {
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                String valueAtMousePointer = getValueAt(rowIndex, colIndex).toString();


        table = new JTable(data, columnNames);
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
        JButton closeButton = new JButton("Schließen");
        JButton printButton = new JButton("Drucken");

        // Aktionen für die Buttons hinzufügen (Dummy-Implementierung)
        saveButton.addActionListener(_ -> saveBill());
        deleteButton.addActionListener(_ -> deleteBill());
        printButton.addActionListener(_ -> printBill());
        closeButton.addActionListener(_ -> close());

        // Hinzufügen der Buttons zum bottom panel
        bottomPanel.add(deleteButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(printButton);
        bottomPanel.add(closeButton);

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
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(this, "Fehler beim Drucken des Datensatzes");
        }
    }

    private void close() {
        dispose();
    }
}
