package ui;

import model.Client;
import services.ClientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientPanel extends JPanel {
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private ClientService clientService = new ClientService();

    public ClientPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table
        String[] columnNames = { "ID", "Name", "Email" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        clientTable = new JTable(tableModel);
        add(new JScrollPane(clientTable), BorderLayout.CENTER);

        // Refresh Button
        JButton refreshBtn = new JButton("Refresh Clients");
        refreshBtn.addActionListener(e -> loadClients());
        add(refreshBtn, BorderLayout.SOUTH);

        loadClients();
    }

    private void loadClients() {
        tableModel.setRowCount(0);
        try {
            List<Client> clients = clientService.showClients();
            for (Client c : clients) {
                tableModel.addRow(new Object[] { c.getId_client(), c.getNom(), c.getEmail() });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading clients: " + e.getMessage());
        }
    }
}
