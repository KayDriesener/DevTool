package prog;

import dto.Dispo;
import dto.Shipping;
import helpers.ComboBoxes;
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

public class DispositionWindow extends JFrame {
    Logger log = LoggerFactory.getLogger(this.getClass());
    JTable transportTable;

    // Konstruktor für die Disposition-Klasse
    public DispositionWindow() {
        // Einstellungen für das JFrame-Fenster
        setTitle("Disposition");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        ImageIcon icon = new ImageIcon("");
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

        ArrayList<Shipping> transportList = null;
        try {
            transportList = new DbQueries().getShipping();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Beim Abrufen der Transportdaten ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }

        Object[][] dataTransport = null;
        if (transportList != null) {
            int attributeCount = Shipping.class.getDeclaredFields().length;
            dataTransport = new Object[transportList.size()][attributeCount];
            int cnt = 0;
            for (Shipping shipping : transportList) {
                dataTransport[cnt][0] = shipping.isDisponiert();
                dataTransport[cnt][1] = shipping.getBdf_referenz();
                dataTransport[cnt][2] = shipping.getDatum();
                dataTransport[cnt][3] = shipping.getKn_referenz();
                dataTransport[cnt][4] = shipping.getAbsender();
                dataTransport[cnt][5] = shipping.getEmpfaenger();
                dataTransport[cnt][6] = shipping.getBeladung_s();
                dataTransport[cnt][7] = shipping.getBeladung_e();
                dataTransport[cnt][8] = shipping.getEntladen_s();
                dataTransport[cnt][9] = shipping.getEntladen_e();
                dataTransport[cnt][10] = shipping.getStellplaetze();
                dataTransport[cnt][11] = shipping.getAnzahl();
                dataTransport[cnt][12] = shipping.isLiquid() ? "Ja" : "Nein";
                dataTransport[cnt][13] = shipping.isAdr() ? "Ja" : "Nein";
                dataTransport[cnt][14] = shipping.isRundlauf() ? "Ja" : "Nein";
                dataTransport[cnt][15] = shipping.getBemerkung();
                cnt++;
            }
        }
        Object[] columnNamesTransport = {"Disponieren", "BDF Referenz", "Datum", "K&N Referenz", "Absender", "Empfänger", "Beladung Start", "Ende", "Entladen Start", "Ende", "Stellplätze (EP)", "Anzahl EPal", "LQ", "ADR", "Rundlauf", "Bemerkung"};

        // Erstellen des TableModels mit Checkbox-Renderer
        DefaultTableModel model = new DefaultTableModel(dataTransport, columnNamesTransport) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : Object.class;
            }
        };


        // Erstellen der JTable und Zuweisung des Checkbox-Renderers
        transportTable = new JTable(model);
        transportTable.getColumnModel().getColumn(0).setCellRenderer(new CheckBoxRenderer());
        transportTable.setPreferredScrollableViewportSize(new Dimension(400, 200));
        JScrollPane scrollPaneDataDispo = new JScrollPane(transportTable);
        middlePanel.add(scrollPaneDataDispo);

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Erstellen der Buttons und Zuweisung der ActionListener
        JButton dispoBtn = new JButton("Disponieren");
        JButton backButton = new JButton("Zurück");
        backButton.addActionListener(_ -> goBack());
        dispoBtn.addActionListener(_ -> dispoTransport());

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

    //Dispo-Methode
    private void  dispoTransport(){
        ArrayList<Integer> selectedRows = new ArrayList<>();
        for (int i = 0; i < transportTable.getRowCount(); i++){
            Boolean isSelected = (Boolean) transportTable.getValueAt(i, 0);
            if (isSelected != null && isSelected){
                selectedRows.add(i);
            }
        }
        if (selectedRows.isEmpty()){
            JOptionPane.showMessageDialog(this, "Bitte mindestens einen Transport auswählen!");
            return;
        }
        JComboBox<String> zmComboBox = new JComboBox<>();
        JComboBox<String> tComboBox = new JComboBox<>();
        ComboBoxes.populateZugmaschine(zmComboBox);
        ComboBoxes.populateTrailer(tComboBox);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Zugmaschiene"));
        panel.add(zmComboBox);
        panel.add(new JLabel("Trailer"));
        panel.add(tComboBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Zugmaschiene und Trailer auswählen!", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            String zugmaschiene = (String) zmComboBox.getSelectedItem();
            String trailer = (String) tComboBox.getSelectedItem();

            for (int row : selectedRows){
                String knReferenz = (String) transportTable.getValueAt(row, 3);
                String bdfReferenz = (String) transportTable.getValueAt(row, 1);

                Dispo dispo = new Dispo();
                try {
                    new DbStatements().addDispo(dispo);
                    JOptionPane.showMessageDialog(this, "Dispo gespeichert");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Ein unerwarteter Fehler ist aufgetreten!");
                    log.error(e.getMessage());
                }
            }
        }
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
            if (isSelected){
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground() );
            }

            if (value instanceof Boolean){
                setSelected((Boolean) value);
            } else if (value instanceof Integer){
                setSelected(((Integer) value) != 0);
            } else {
                setSelected(false);
            }
            return this;
        }
    }

}
