package com.dr.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
//import com.dr.app.WebPageLayoutAdmin;

public class AdminLogin {
    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/USER"; // Change to your database URL
    private static final String USER = "root"; // Change to your database username
    private static final String PASSWORD = "Rudraksh2005."; // Change to your database password

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel loginPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminLogin::new);
    }

    public AdminLogin() {
        frame = new JFrame("Admin Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        mainPanel = new BackgroundPanel(); // Custom background panel
        mainPanel.setLayout(new GridBagLayout());

        loginPanel = createLoginPanel(); // Create login panel
        mainPanel.add(loginPanel); // Add login panel to the main panel

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE); // Set the background color to white
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);

        // Add an empty vertical space at the top to center the title better
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0; // This helps center the title vertically
        panel.add(Box.createVerticalStrut(20), gbc);

        // Add the title label
        JLabel title = new JLabel("Admin Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0; // Reset weight
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);

        // Add "Username" label and text field
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField usernameField = new JTextField(10);
        panel.add(usernameField, gbc);

        // Add "Password" label and text field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JPasswordField passwordField = new JPasswordField(10);
        panel.add(passwordField, gbc);

        // Add login button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (checkAdminCredentials(username, password)) {
                    frame.dispose();  // Close the login window
                    WebPageLayoutAdmin.main(new String[0]);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials. Access denied.");
                }
            }
        });

        panel.add(loginButton, gbc);

        // Add another empty vertical space at the bottom
        gbc.gridy = 5;
        gbc.weighty = 1.0;
        panel.add(Box.createVerticalStrut(20), gbc);

        return panel;
    }

    private boolean checkAdminCredentials(String username, String password) {
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try {
            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver"); 
    
            // Establish the database connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
    
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            return resultSet.next(); // Returns true if a row is found
    
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    
        return false;
    }

    // Custom JPanel for displaying background image
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon("C:\\Users\\Rudraksh\\OneDrive\\Desktop\\DR\\src\\main\\java\\Resources\\images (20).jpg").getImage(); // Change to your image path
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
