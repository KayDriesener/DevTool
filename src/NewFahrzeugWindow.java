import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;


public class NewFahrzeugWindow extends JFrame {
    private JComboBox<String> artComboBox;
    Logger log = LoggerFactory.getLogger(this.getClass());
    JTextField tAnbieter;
    JTextField tRegPlate;
    JTextField tFine;
    JTextField tSp;
    JTextField tTuev;
    JTextField tKst;



    public NewFahrzeugWindow() {
        setTitle("Neues Fahrzeug");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das Usermanagement
        JLabel headline = new JLabel("FAHRZEUGMANAGEMENT");
        headline.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subHeadline = new JLabel("Fahrzeug Anlegen");
        subHeadline.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(headline, BorderLayout.NORTH);
        topPanel.add(subHeadline, BorderLayout.CENTER);

        // Mittleres Panel mit Gridlayout
        JPanel middlePanel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        JLabel anbieter = new JLabel("Anbieter");
        tAnbieter = new JTextField();
        middlePanel.add(anbieter);
        middlePanel.add(tAnbieter);
        JLabel regPlate = new JLabel("Kennzeichen");
        tRegPlate = new JTextField();
        middlePanel.add(regPlate);
        middlePanel.add(tRegPlate);
        JLabel art = new JLabel("ZM/ Trailer");
        // Combobox statt Textfield
        String[] options = {"Bitte auswählen", "Zugmaschine", "Trailer"};
        artComboBox = new JComboBox<>(options);
        middlePanel.add(art);
        middlePanel.add(artComboBox);
        JLabel fine = new JLabel("Miete/ Tag");
        tFine = new JTextField();
        middlePanel.add(fine);
        middlePanel.add(tFine);
        JLabel sp = new JLabel("Sicherheitsprüfung fällig am");
        tSp = new JTextField();
        middlePanel.add(sp);
        middlePanel.add(tSp);
        JLabel tuev = new JLabel("TÜV gültig bis");
        tTuev = new JTextField();
        middlePanel.add(tuev);
        middlePanel.add(tTuev);
        JLabel kst = new JLabel("Kostenstelle");
        tKst = new JTextField();
        middlePanel.add(kst);
        middlePanel.add(tKst); 

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton saveButton = new JButton("Speichern");
        JButton backButton = new JButton("Hauptmenü");
        backButton.addActionListener(f -> goMainMenue());
        saveButton.addActionListener(e -> saveFahrzeug());

        bottomPanel.add(saveButton);
        bottomPanel.add(backButton);
         

        // Hauptpanel mit BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

    }

    private void saveFahrzeug() {
        String selectedOption = (String) artComboBox.getSelectedItem();

        if ("Zugmaschine".equals(selectedOption)) {

        } else if ("Trailer".equals(selectedOption)) {

        }
    }

    private void goMainMenue() {
        new MainWindow();
        dispose();
    }
}
