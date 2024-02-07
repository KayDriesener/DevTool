import javax.swing.*;
import java.awt.*;

public class NewBillingWindow extends JFrame {
    public NewBillingWindow() {
        setTitle("Neue Rechnung");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5)); // Unbegrenzte Zeilen, 2 Spalten, Abstand zwischen den Zellen: 5 Pixel

        JButton saveButton = new JButton("Speichern");
        JButton backButton = new JButton("Hauptmenü");
        backButton.addActionListener(f -> goMainMenue());
        saveButton.addActionListener(e -> saveBill());

        add(panel);
        panel.add(backButton);
        setVisible(true);

    }

    private void saveBill() {
        // Prep. Statement Rechnung Neu
        JOptionPane.showMessageDialog(this, "Datensatz gespeichert!");
    }

    private void goMainMenue() {
        new MainWindow();
        dispose();
    }

}
