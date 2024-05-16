import sql.MySqlConnector;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class MainWindow extends JFrame {
    private final JTree tree;


    public MainWindow() {
        setTitle("Hauptmenü");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/media/kunIco.jpg");
        setIconImage(icon.getImage());

        /*
         * Erstellen des Menüs in der JTree (BaumStruktur) Variante um die Übersicht und Usability (Look and Feel) für den Nutzer zu gewährleisten.
         */
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Hauptmenü");
        addCategory(rootNode, "User management", "Neuer User", "User bearbeiten");
        addCategory(rootNode, "Fahrzeugmanagement", "Neues Fahrzeug", "Fahrzeug Bearbeiten");
        addCategory(rootNode, "Transportmanagement", "Neuer Transport", "Transport Bearbeiten", "Disposition");
        addCategory(rootNode, "Rechnungswesen", "Rechnung Erstellen", "Rechnung Bearbeiten");
        addCategory(rootNode, "Kundenmanagement", "Kunde Anlegen", "Kunde Bearbeiten");
        addCategory(rootNode, "Konfiguration", "Preise Paletten", "Entfernungen");
        addCategory(rootNode, "Auswertungen", "Fahrzeuge", "Kunden");
        addCategory(rootNode, "Logout", "Abmelden");

        tree = new JTree(rootNode);
        JScrollPane treeScrollPane = new JScrollPane(tree);

        /*
         * Teilung des Panels um das Aussehen des Fensters durch Hinzufügen eines Bildes in der createRightPanel aufzulockern.
         */
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, createRightPanel());
        splitPane.setResizeWeight(0.25);

        add(splitPane);

        /*
         * Hinzufügen des TreeSelectionListeners, der auf das angeklickte Objekt im tree reagiert.
         * Die RuntimeException fängt den Fehler durch den Logout auf.
         */
        tree.addTreeSelectionListener(_ -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null && selectedNode.isLeaf()) {
                String category = selectedNode.getParent().toString();
                try {
                    openWindow(category, selectedNode.toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                tree.clearSelection();
            }
        });

        setVisible(true);
    }

    // TODO BORDERLAYOUT ÜBERARBEITEN!
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout(0, 0));

        /*
         * Einfügen eines Bildes in das rechte Splitpane.
         */
        ImageIcon imageIcon = new ImageIcon("src/media/knt.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        rightPanel.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
        rightPanel.add(imageLabel);

        return rightPanel;
    }

    private void addCategory(DefaultMutableTreeNode parent, String categoryName, String... buttons) {
        DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(categoryName);
        parent.add(categoryNode);
        for (String buttonText : buttons) {
            DefaultMutableTreeNode buttonNode = new DefaultMutableTreeNode(buttonText);
            categoryNode.add(buttonNode);
        }
    }

    /*
     * Methode, die auf das jeweils angeklickte Objekt die korrekte Methode aufruft.
     * Die SQLException dient zum Auffangen des möglicherweise auftretenden Logout-Fehlers in der Methode openLoginWindow().
     * (MySqlConnector.dbConnection.close();)
     */
    private void openWindow(String category, String buttonText) throws SQLException {
        switch (category){
            case "User management":
                switch (buttonText){
                    case "Neuer User":
                        openUserWindow();
                        break;
                    case "User bearbeiten":
                        openEditUserWindow();
                        break;
                } break;
            case "Fahrzeugmanagement":
                switch (buttonText){
                    case "Neues Fahrzeug":
                        openFahrzeugWindow();
                        break;
                    case "Fahrzeug Bearbeiten":
                        openEditFahrzeugWindow();
                        break;
                } break;
            case "Transportmanagement":
                switch (buttonText){
                    case "Neuer Transport":
                        openTransportWindow();
                        break;
                    case "Transport Bearbeiten":
                        openEditTransportWindow();
                        break;
                    case "Disposition":
                        openDisposition();
                        break;
                } break;
            case "Rechnungswesen":
                switch (buttonText){
                    case "Rechnung Erstellen":
                        openNewBillingWindow();
                        break;
                    case "Rechnung Bearbeiten":
                        openEditBillingWindow();
                        break;
                } break;
            case "Kundenmanagement":
                switch (buttonText){
                    case "Kunde Anlegen":
                        openNewCustomerWindow();
                        break;
                    case "Kunde Bearbeiten":
                        openEditCustomerWindow();
                        break;
                } break;
            case "Konfiguration":
                switch(buttonText){
                    case "Preise Paletten":
                        //Platzhalter für das Fenster mit den Paletten preisen
                        break;
                    case "Entfernungen":
                        //Platzhalter für das Fenster mit den Entfernungen
                        break;
                } break;
            case "Auswertungen":
                switch (buttonText){
                    case "Fahrzeuge":
                        //Platzhalter für das Auswertungsfenster "Fahrzeuge"
                        break;
                    case "Kunden":
                        //Platzhalter für das Auswertungsfenster "Kunden"
                        break;
                } break;
            case "Logout":
                // Es wurde bewusst ein switch gewählt, da es die Erweiterbarkeit vereinfacht. (if-Statement muss nicht umgeschrieben werden)
                switch (buttonText){
                    case "Abmelden":
                        openLoginWindow();
                        dispose();
                        break;
                }break;
        }
    }

    /*
     * Funktionen zum öffen der entsprechenden Fenster. Lambda-Expression um den Code übersichtlicher zu gestalten.
     */
    private void openUserWindow() {
        SwingUtilities.invokeLater(NewUserWindow::new);
    }

    private void openEditUserWindow() {
        SwingUtilities.invokeLater(EditUserWindow::new);
    }

    private void openFahrzeugWindow() {
        SwingUtilities.invokeLater(NewFahrzeugWindow::new);
    }

    private void openEditFahrzeugWindow() {
        SwingUtilities.invokeLater(EditFahrzeugWindow::new);
    }

    private void openTransportWindow() {
        SwingUtilities.invokeLater(NewTransportWindow::new);
    }

    private void openEditTransportWindow() {
        SwingUtilities.invokeLater(EditTransportWindow::new);
    }

    private void openDisposition() throws  SQLException {
        SwingUtilities.invokeLater(Disposition::new);
    }

    private void openNewBillingWindow() {
        SwingUtilities.invokeLater(NewBillingWindow::new);
    }

    private void openEditBillingWindow() {
        SwingUtilities.invokeLater(EditBilllingWindow::new);
    }

    private void openNewCustomerWindow() {
        SwingUtilities.invokeLater(NewCustomerWindow::new);
    }

    private void openEditCustomerWindow() {
        SwingUtilities.invokeLater(EditCustomerWindow::new);
    }
    private void openLoginWindow() throws SQLException {
        MySqlConnector.dbConnection.close();
        SwingUtilities.invokeLater(LoginWindow::new);
    }

}
