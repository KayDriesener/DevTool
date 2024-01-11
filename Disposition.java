import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Disposition extends JFrame {

    // Konstruktor für die Disposition-Klasse
    public Disposition() {
        // Einstellungen für das JFrame-Fenster
        setTitle("Disposition");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Oberstes Panel mit BorderLayout für den Titel
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das Dispositionsmanagement
        JLabel headline = new JLabel("DISPOSITIONSMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Disposition Anlegen");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);

        // Mittleres Panel mit GridLayout für die JTable
        JPanel middlePanel = new JPanel(new GridLayout(1, 1, 5, 5));

        // Daten für die JTable und Spaltennamen
        Object[][] dataTableDispo = { { false, "Beim Anlegen NICHT Update", "Kommt aus der Dispo", "Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag" } };
        Object[] collumNamesDispo = { "Auswahl", "Datum", "K&N Referenz", "BDF Referenz", "Absender", "Empfänger", "Beladung Start", "Ende", "Entladen Start", "Ende", "Stellplätze (EP)", "Anzahl EPal", "LQ", "ADR", "Rundlauf" };

        // Erstellen des TableModels mit Checkbox-Renderer
        DefaultTableModel model = new DefaultTableModel(dataTableDispo, collumNamesDispo) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : Object.class;
            }
        };

        // Erstellen der JTable und Zuweisung des Checkbox-Renderers
        JTable tableDataDispo = new JTable(model);
        tableDataDispo.getColumnModel().getColumn(0).setCellRenderer(new CheckBoxRenderer());
        tableDataDispo.setPreferredScrollableViewportSize(new Dimension(400, 200));
        JScrollPane scrollPaneDataDispo = new JScrollPane(tableDataDispo);
        middlePanel.add(scrollPaneDataDispo);

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Erstellen der Buttons und Zuweisung der ActionListener
        JButton dispoBtn = new JButton("Disponieren");
        JButton backButton = new JButton("Zurück");
        backButton.addActionListener(f -> goBack());
        dispoBtn.addActionListener(e -> saveDispo());

        // Dem bottomPanel zuweisen
        bottomPanel.add(dispoBtn);
        bottomPanel.add(backButton);

        // Erstellung des Hauptpanels mit BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Zuweisung der anderen Panels zum mainPanel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // mainPanel dem Fenster zuweisen und anzeigen lassen
        add(mainPanel);
        setVisible(true);
    }

    // Methode zum Speichern der Disposition
    private void saveDispo() {
        Lieferscheinnummerngenerator manager = new Lieferscheinnummerngenerator();
        String kNlieferscheinnummer = Lieferscheinnummerngenerator.nummernGenerator();
        // Prep.Statement Transport speichern
        JOptionPane.showMessageDialog(this, "Transport Angelegt mit der Referenz " + kNlieferscheinnummer +"!");
    }

    // Methode zum Zurückkehren
    private void goBack() {
        new NewTransportWindow();
        dispose();
    }

    // Checkbox-Renderer-Klasse außerhalb der Disposition-Klasse
    static class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        CheckBoxRenderer() {
            setHorizontalAlignment(JCheckBox.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected(value != null && (Boolean) value);
            return this;
        }
    }

    // TODO Live entfernen
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Disposition::new);
    }
}
