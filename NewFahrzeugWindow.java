import javax.swing.*;
import java.awt.*;

public class NewFahrzeugWindow extends JFrame {
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
        JTextField tanbieter = new JTextField();
        middlePanel.add(anbieter);
        middlePanel.add(tanbieter);
        JLabel regPlate = new JLabel("Kennzeichen");
        JTextField tRegPlate = new JTextField();
        middlePanel.add(regPlate);
        middlePanel.add(tRegPlate);
        JLabel art = new JLabel("ZM/ Trailer");
        JTextField tArt = new JTextField();
        middlePanel.add(art);
        middlePanel.add(tArt);
        JLabel fine = new JLabel("Miete/ Tag");
        JTextField tfine = new JTextField();
        middlePanel.add(fine);
        middlePanel.add(tfine);  
        JLabel sp = new JLabel("Sicherheitsprüfung fällig am");
        JTextField tSp = new JTextField();
        middlePanel.add(sp);
        middlePanel.add(tSp);
        JLabel tuev = new JLabel("TÜV gültig bis");
        JTextField tTuev = new JTextField();
        middlePanel.add(tuev);
        middlePanel.add(tTuev);
        JLabel kst = new JLabel("Kostenstelle");
        JTextField tKst = new JTextField();
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
        // Prep.Statement Fahrzeug Neu
        JOptionPane.showMessageDialog(this, "Datensatz gespeichert!");
    }

    private void goMainMenue() {
        new MainWindow();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NewFahrzeugWindow());
    }
}
