import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTransportWindow extends JFrame {

        Lieferscheinnummerngenerator manager = new Lieferscheinnummerngenerator();
        String kNlieferscheinnummer = Lieferscheinnummerngenerator.nummernGenerator();

        public NewTransportWindow() {
                setTitle("Neuer Transport");
                setExtendedState(Frame.MAXIMIZED_BOTH);
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
                setResizable(true);

                // Oberstes Panel mit BorderLayout
                JPanel topPanel = new JPanel(new BorderLayout());

                // Titel für das Transportmanagement
                JLabel headline = new JLabel("TRANSPORTMANAGEMENT");
                headline.setFont(new Font("Arial", Font.BOLD, 20));
                JLabel subHeadline = new JLabel("Transport Anlegen");
                subHeadline.setFont(new Font("Arial", Font.BOLD, 18));
                topPanel.add(headline, BorderLayout.NORTH);
                topPanel.add(subHeadline, BorderLayout.CENTER);

                // Datumsgenerator für Jlabel Datum
                JLabel printDate = new JLabel();
                printDate.setFont(new Font("Arial", Font.PLAIN, 12));
                printDate.setHorizontalAlignment(SwingConstants.CENTER);
                updateDate(printDate);
                Timer timer = new Timer(1000, e -> updateDate(printDate));
                timer.start();

                // MiddlePannel
                JPanel middlePanel = new JPanel();
                // GroupLayout
                GroupLayout middleGroup = new GroupLayout(middlePanel);
                middlePanel.setLayout(middleGroup);
                // AutoGap
                middleGroup.setAutoCreateGaps(true);
                middleGroup.setAutoCreateContainerGaps(true);
                // Erste Group (Reihe)
                JLabel knReferenz = new JLabel("K&N Referenz:");
                JTextField knReferenzText = new JTextField(kNlieferscheinnummer);
                knReferenzText.setEditable(false);
                JLabel shipperLabel = new JLabel("Absender:");
                JTextField shipperText = new JTextField();
                JLabel loadBegin = new JLabel("Beladung Start:");
                JTextField loadBeginTime = new JTextField();
                JLabel loadEnd = new JLabel("bis:");
                JTextField loadEndTime = new JTextField();
                JLabel pitches = new JLabel("Stellplätze (EP):");
                JTextField pitchesText = new JTextField();
                JCheckBox liquid = new JCheckBox("LQ");

                // Zweite Group (Reihe)
                JLabel date = new JLabel("Datum:");
                JLabel reference = new JLabel("BDF Referenz:");
                JTextField referenceText = new JTextField();
                JLabel recipient = new JLabel("Empfänger:");
                JTextField recipientText = new JTextField();
                JLabel dischargeBegin = new JLabel("Entladen Start:");
                JTextField dischargeBeginText = new JTextField();
                JLabel dischargeEnd = new JLabel("bis:");
                JTextField dischargeEndText = new JTextField();
                JLabel eupal = new JLabel("Anzahl EuroPal:");
                JTextField epalText = new JTextField();
                JCheckBox adrBox = new JCheckBox("ADR");

                // Dritte Group (Reihe)
                JLabel comment = new JLabel("Bemerkung");
                JTextField commentText = new JTextField();
                JCheckBox roundtrip = new JCheckBox("Rundlauf");

                // Vierte Group (Reihe (Tabelle))
                Object[][] dataTableTransport = { { "Beim Anlegen NICHT Update", "Kommt aus der Dispo", "Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag","Testeintrag" } };
                Object[] collumNamesTransport = { "Datum", "K&N Referenz", "BDF Referenz", "Absender", "Empfänger", "Beladung Start", "Ende", "Entladen Start", "Ende", "Stellplätze (EP)", "Anzahl EPal", "LQ", "ADR", "Rundlauf" };
                JTable tableDataTransport = new JTable(dataTableTransport, collumNamesTransport);
                JScrollPane scrollPaneDataTransport = new JScrollPane(tableDataTransport);
                // Größe anpassen
                scrollPaneDataTransport.setPreferredSize(new Dimension(400, 200));

                middleGroup.setHorizontalGroup(
                                middleGroup.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(middleGroup.createSequentialGroup()
                                                                .addComponent(knReferenz)
                                                                .addComponent(knReferenzText)
                                                                .addComponent(shipperLabel)
                                                                .addComponent(shipperText)
                                                                .addComponent(loadBegin)
                                                                .addComponent(loadBeginTime)
                                                                .addComponent(loadEnd)
                                                                .addComponent(loadEndTime)
                                                                .addComponent(pitches)
                                                                .addComponent(pitchesText)
                                                                .addComponent(liquid))
                                                .addGroup(middleGroup.createSequentialGroup()
                                                                .addComponent(reference)
                                                                .addComponent(referenceText)
                                                                .addComponent(recipient)
                                                                .addComponent(recipientText)
                                                                .addComponent(dischargeBegin)
                                                                .addComponent(dischargeBeginText)
                                                                .addComponent(dischargeEnd)
                                                                .addComponent(dischargeEndText)
                                                                .addComponent(eupal)
                                                                .addComponent(epalText)
                                                                .addComponent(adrBox))
                                                .addGroup(middleGroup.createSequentialGroup()
                                                                .addComponent(date)
                                                                .addComponent(printDate)
                                                                .addComponent(comment)
                                                                .addComponent(commentText)
                                                                .addComponent(roundtrip))
                                                .addGroup(middleGroup.createSequentialGroup()
                                                                .addComponent(scrollPaneDataTransport)));

                middleGroup.setVerticalGroup(
                                middleGroup.createSequentialGroup()
                                                .addGroup(middleGroup
                                                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(knReferenz)
                                                                .addComponent(knReferenzText)
                                                                .addComponent(shipperLabel)
                                                                .addComponent(shipperText)
                                                                .addComponent(loadBegin)
                                                                .addComponent(loadBeginTime)
                                                                .addComponent(loadEnd)
                                                                .addComponent(loadEndTime)
                                                                .addComponent(pitches)
                                                                .addComponent(pitchesText)
                                                                .addComponent(liquid))
                                                .addGroup(middleGroup
                                                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(reference)
                                                                .addComponent(referenceText)
                                                                .addComponent(recipient)
                                                                .addComponent(recipientText)
                                                                .addComponent(dischargeBegin)
                                                                .addComponent(dischargeBeginText)
                                                                .addComponent(dischargeEnd)
                                                                .addComponent(dischargeEndText)
                                                                .addComponent(eupal)
                                                                .addComponent(epalText)
                                                                .addComponent(adrBox))
                                                .addGroup(middleGroup
                                                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(date)
                                                                .addComponent(printDate)
                                                                .addComponent(comment)
                                                                .addComponent(commentText)
                                                                .addComponent(roundtrip))
                                                .addGroup(middleGroup
                                                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(liquid))
                                                .addGroup(middleGroup
                                                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(adrBox))
                                                .addGroup(middleGroup
                                                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(roundtrip))
                                                .addComponent(scrollPaneDataTransport));

                // Unteres Panel mit FlowLayout für die Buttons
                JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
                // Erstellen der Buttons und Zuweisung der ActionListner
                JButton saveButton = new JButton("Anlegen");
                JButton backButton = new JButton("Hauptmenü");
                JButton dispoButton = new JButton("Disposition");
                backButton.addActionListener(f -> goMainMenue());
                saveButton.addActionListener(e -> saveTransport());
                dispoButton.addActionListener(g -> goDisposition());
                // Dem bottomPannel zuweisen
                bottomPanel.add(saveButton);
                bottomPanel.add(backButton);
                bottomPanel.add(dispoButton);
                // Erstellung des Hauptpanel mit BorderLayout
                JPanel mainPanel = new JPanel(new BorderLayout());
                // Zuweisung der anderen Pannels zum mainPanel
                mainPanel.add(topPanel, BorderLayout.NORTH);
                mainPanel.add(middlePanel);
                mainPanel.add(bottomPanel, BorderLayout.SOUTH);
                // mainPannel dem Femster zuweisen und anzeigen lassen
                add(mainPanel);
                setVisible(true);

        }

        private void updateDate(JLabel label) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                String formattedDate = dateFormat.format(new Date());
                label.setText(formattedDate);
        }

        private void saveTransport() {
                // Prep.Statement Transport Neu
                JOptionPane.showMessageDialog(this, "Transport angelegt!");
        }

        private void goDisposition(){
                new Disposition();
                dispose();
        }

        private void goMainMenue() {
                new MainWindow();
                dispose();
        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(NewTransportWindow::new);
        }
}
