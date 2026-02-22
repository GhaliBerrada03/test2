package ui;

import model.Client;
import services.ClientService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JButton loginButton;
    private ClientService clientService = new ClientService();

    public LoginFrame() {
        setTitle("Art Gallery - Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel emailLabel = new JLabel("Enter your Email:");
        emailField = new JTextField();
        loginButton = new JButton("Login");

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an email", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            List<Client> clients = clientService.showClients();
            Client foundClient = null;
            for (Client c : clients) {
                if (c.getEmail().equalsIgnoreCase(email)) {
                    foundClient = c;
                    break;
                }
            }

            if (foundClient != null) {
                SessionManager.getInstance().setCurrentClient(foundClient);
                JOptionPane.showMessageDialog(this, "Welcome " + foundClient.getNom() + "!");
                this.dispose();
                new MainFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Client not found with email: " + email, "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error during login: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}

class SessionManager {
    private static SessionManager instance;
    private Client currentClient;

    private SessionManager() {
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public boolean isLoggedIn() {
        return currentClient != null;
    }
}
