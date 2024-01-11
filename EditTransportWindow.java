import javax.swing.*;
import java.awt.*;

public class EditTransportWindow extends JFrame {
    public EditTransportWindow() {
        setTitle("Transport Bearbeiten");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5)); // Unbegrenzte Zeilen, 2 Spalten, Abstand zwischen den Zellen: 5 Pixel

        JButton saveButton = new JButton("Speichern");
        JButton backButton = new JButton("Hauptmenü");
        backButton.addActionListener(f -> goMainMenue());
        saveButton.addActionListener(e -> saveTransport());

        add(panel);
        panel.add(backButton);
        setVisible(true);

    }

    private void saveTransport() {
        // Prep.Statement Transport Bearbeiten
        JOptionPane.showMessageDialog(this, "Datensatz gespeichert!");
    }

    private void goMainMenue() {
        new MainWindow();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditTransportWindow());
    }
}
