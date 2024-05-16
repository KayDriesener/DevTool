import dto.Dispo;
import dto.Shipping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbQueries;
import sql.DbStatements;


import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Disposition extends JFrame {
    Logger log = LoggerFactory.getLogger(this.getClass());

    // Konstruktor für die Disposition-Klasse
    public Disposition() {
        // Einstellungen für das JFrame-Fenster
        setTitle("Disposition");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        ImageIcon icon = new ImageIcon("src/media/kunIco.jpg");
        setIconImage(icon.getImage());

        // Oberstes Panel mit BorderLayout für den Titel
        JPanel topPanel = new JPanel(new BorderLayout());

        /*
        * Titel für das Dispositionsmanagement
        * Labels dem "topPanel" hinzufügen
        * Beim Hinzufügen wird das Layout mit übergeben
         */
        JLabel headline = new JLabel("DISPOSITIONSMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Disposition Anlegen");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);

        // Mittleres Panel mit GridLayout für die JTable
        JPanel middlePanel = new JPanel(new GridLayout(1, 1, 5, 5));

        ArrayList<Dispo> dispoList = null;
        try {
            dispoList = new DbQueries().getDispo();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Beim Abrufen der Dispositionen ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }

        /*
         * Erstellen der Tabelle
         */
        Object[][] dataDispo = null;
            if (dispoList != null){
                int attributeCount = Dispo.class.getDeclaredFields().length;
                dataDispo = new Object[dispoList.size()][attributeCount];
                int cnt = 0;
                for (Dispo dispo : dispoList){
                    dataDispo[cnt][0] = dispo.isDisponiert();
                    dataDispo[cnt][1] = dispo.getKn_Referenz();
                    dataDispo[cnt][2] = dispo.getBdf_referenz();
                    dataDispo[cnt][3] = dispo.getFahrzeugzm();
                    dataDispo[cnt][4] = dispo.getFahrzeugt();
                }
            }
        Object[] columnNamesDispo = {"Auswahl", "K&N Referenz", "BDF Referenz", "Zugmaschine", "Trailer"};

        // Erstellen des TableModels mit Checkbox-Renderer
        DefaultTableModel model = new DefaultTableModel(dataDispo, columnNamesDispo) {
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

        // Tabelle aus NewTransportWindow abrufen.
        NewTransportWindow newTransportWindow = new NewTransportWindow();
        JTable transportTable = newTransportWindow.getTransportTable();
        new JScrollPane(transportTable);

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Erstellen der Buttons und Zuweisung der ActionListener
        JButton dispoBtn = new JButton("Disponieren");
        JButton backButton = new JButton("Zurück");
        backButton.addActionListener(_ -> goBack());
        //dispoBtn.addActionListener(_ -> saveDispo());

        // Dem bottomPanel zuweisen
        bottomPanel.add(dispoBtn);
        bottomPanel.add(backButton);

        // Erstellung des Haupt panels mit BorderLayout
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
    /*
     * Richtiges übergeben der Daten.
     * Bei Auswahl der Checkbox sollen param's aus addDispo() an die Datenbank übergeben werden.
     */
    /*private void saveDispo() {
        // boolean disponiert =
        String kn_referenz = LieferscheinNummernGenerator.generiereNummer();
        //String bdf_referenz =
        //String fahrzeug_zm =
        //String fahrzeug_t =
        new DbStatements().addDispo(disponiert, kn_referenz, bdf_referenz, fahrzeug_zm, fahrzeug_t);
        JOptionPane.showMessageDialog(this, STR."Transport Angelegt mit der Referenz \{kNlieferscheinnummer}!");
    }
    */
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

}
