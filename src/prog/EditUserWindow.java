package prog;

import dto.User;
import helpers.Updates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbQueries;
import sql.DbStatements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditUserWindow extends JFrame {

    Logger log = LoggerFactory.getLogger(this.getClass());
    JTable usertable;

    public EditUserWindow() {
        setTitle("Edit User");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/media/kunIco.jpg");
        setIconImage(icon.getImage());

        // Oberstes Panel mit BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Titel für das User management
        JLabel newUser = new JLabel("USER MANAGEMENT");
        newUser.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel newUserEdit = new JLabel("Benutzer Bearbeiten");
        newUserEdit.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(newUser, BorderLayout.NORTH);
        topPanel.add(newUserEdit, BorderLayout.CENTER);

        // Mittleres Panel mit GridLayout für die Tabelle
        JPanel middlePanel = new JPanel(new GridLayout(1, 1, 5, 5));

        // Benutzer aus der Datenbank abrufen
        ArrayList<User> userList = null;
        try {
            userList = new DbQueries().getUsers();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Beim Abrufen aller Benutzer ist ein Fehler aufgetreten.");
            log.error(e.getMessage());
        }

        // Benutzerdaten in das TableModel übertragen
        Object[][] tableData = null;
        if (userList != null) {
            // Die Anzahl der Attribute muss mit der Spaltenanzahl der collumnNames übereinstimmen!
            int attributeCount = User.class.getDeclaredFields().length;
            tableData = new Object[userList.size()][attributeCount];
            int cnt = 0;
            for (User user : userList) {
                tableData[cnt][0] = user.getId();
                tableData[cnt][1] = user.getVorname();
                tableData[cnt][2] = user.getName();
                tableData[cnt][3] = user.getEmail();
                tableData[cnt][4] = user.getUserName();
                cnt++;
            }
        }

        /*
         * Benutzerdefinierte Spaltenüberschriften → Anzahl der Überschriften,
         * muss mit der Anzahl der Attribute in der Datei User übereinstimmen!
         */

        Object[] userColumnNames = {"ID", "Name", "Nachname", "EMail", "Username"};
        assert tableData != null;
        usertable = new JTable(tableData, userColumnNames);

        /*
         * Tooltip für die Zellen in der Tabelle bei Mouseover.
         */
        usertable.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                int row = usertable.rowAtPoint(point);
                int col = usertable.columnAtPoint(point);

                if (row >= 0) {
                    Object value = usertable.getValueAt(row, col);
                    usertable.setToolTipText((value != null ? value.toString() : null));
                }

            }
        });

        // Tabelle auf die Spalten aufteilen
        JScrollPane scrollPane = new JScrollPane(usertable);
        scrollPane.setPreferredSize(new Dimension(400, 200)); // Größe anpassen

        middlePanel.add(scrollPane);

        // Unteres Panel mit FlowLayout für die Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Buttons für Aktionen
        JButton deleteButton = new JButton("Löschen");
        JButton saveButton = new JButton("Speichern");
        JButton closeButton = new JButton("Schließen");

        // Aktion listener den Buttons zuweisen
        saveButton.addActionListener(_ -> saveUser());
        closeButton.addActionListener(_ -> close());
        deleteButton.addActionListener(_ -> deleteUser());

        bottomPanel.add(deleteButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(closeButton);

        // Haupt panel mit BorderLayout und zuweisen der anderen panels
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void deleteUser() {
        int selectedRow = usertable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Bitte einen User zum Löschen auswählen!");
            return;
        }
        int option = JOptionPane.showConfirmDialog(this, "Möchten Sie diesen User wirklich löschen?");
        if (option == JOptionPane.YES_OPTION) {
            try {
                int id = (int) usertable.getValueAt(selectedRow, 0);
                new DbStatements().deleteCustomer(id);
                Updates.updateCustomerTable(usertable);
                JOptionPane.showMessageDialog(this, "User erfolgreich gelöscht!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ein Fehler ist beim Löschen der Userdaten aufgetreten");
                log.error(STR."Fehler beim Löschen der Userdaten!\{ex.getMessage()}");
            }
        }
    }

    private void saveUser() {
        //TODO Prep.Statement User
        JOptionPane.showMessageDialog(this, "Datensatz GESPEICHERT!");
    }

    private void close() {
        dispose();
    }
}
