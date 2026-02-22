package ui;

import model.Client;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Art Gallery Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header info
        Client user = SessionManager.getInstance().getCurrentClient();
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        header.add(new JLabel("Current User: " + (user != null ? user.getNom() : "Guest")), BorderLayout.WEST);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> {
            SessionManager.getInstance().setCurrentClient(null);
            dispose();
            new LoginFrame().setVisible(true);
        });
        header.add(logoutBtn, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // Tabbed Pane
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Artworks & Store", new OeuvrePanel());
        tabs.addTab("Client List", new ClientPanel());
        tabs.addTab("Sales History & Search", new VentePanel());

        add(tabs, BorderLayout.CENTER);
    }
}
