import java.awt.*;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class MainWindow extends JFrame {
    private JTree tree;

    public MainWindow() {
        setTitle("Hauptmenü");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Hauptmenü");
        addCategory(rootNode, "User management", "Neuer User", "User bearbeiten");
        addCategory(rootNode, "Fahrzeugmanagement", "Neues Fahrzeug", "Fahrzeug Bearbeiten");
        addCategory(rootNode, "Transportmanagement", "Neuer Transport", "Transport Bearbeiten");
        addCategory(rootNode, "Rechnungswesen", "Rechnung Erstellen", "Rechnung Bearbeiten");
        addCategory(rootNode, "Kundenmanagement", "Kunde Anlegen", "Kunde Bearbeiten");
        addCategory(rootNode, "Konfiguration", "Preise Paletten", "Entfernungen");
        addCategory(rootNode, "Auswertungen", "Fahrzeuge", "Kunden");

        tree = new JTree(rootNode);
        JScrollPane treeScrollPane = new JScrollPane(tree);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, createRightPanel());
        splitPane.setResizeWeight(0.25);

        add(splitPane);

        // Hinzufügen des TreeSelectionListeners
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null && selectedNode.isLeaf()) {
                    String category = selectedNode.getParent().toString();
                    openWindow(category, selectedNode.toString());
                    tree.clearSelection();
                }
            }
        });

        setVisible(true);
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout(0, 0));

        // Fügen Sie hier das Bild oder andere Komponenten auf der rechten Seite hinzu
        ImageIcon imageIcon = new ImageIcon("knt.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        rightPanel.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
        rightPanel.add(imageLabel, BorderLayout.CENTER);

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

    private void openWindow(String category, String buttonText){
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
        }
    }

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

}
// TODO LOGOUT BUTTON -> con.close() && openLoginWindow() && dispose()