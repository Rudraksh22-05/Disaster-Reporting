package com.dr.app;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
import java.sql.*;
//import javax.swing.border.Border;
//import java.net.URL;
//import com.dr.app.WebPageLayout;

public class UserLogin {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/USER?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Rudraksh2005.";

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JPanel createAccountPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserLogin::new);
    }

    public UserLogin() {
        frame = new JFrame("User Login And Create new Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
    
        mainPanel = new BackgroundPanel();  // Correctly initialize BackgroundPanel
        mainPanel.setLayout(new GridBagLayout());
    
        loginPanel = createLoginPanel();
        createAccountPanel = createAccountPanel();
    
        mainPanel.add(loginPanel);  // Add login panel to the main panel
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(300, 300));
        
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5); // Add padding to all components
        
        // Add an empty vertical space at the top to center the title better
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0; // This helps center the title vertically
        panel.add(Box.createVerticalStrut(20), gbc);
    
        // Add the title label
        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0; // Reset weight so it only applies to spacing rows
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
        // In the UserLogin class, within the loginButton's ActionListener:
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            try {
                if (checkLogin(username, password)) {
                    frame.dispose();  // Close the login window
                    WebPageLayout.main(new String[0]);  // Open the new layout window
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        panel.add(loginButton, gbc);
    
        // Add "Create New Account" button
        gbc.gridy = 5;
        JButton switchToCreateAccountButton = new JButton("Create New Account");
        switchToCreateAccountButton.addActionListener(e -> switchToPanel(createAccountPanel));
        panel.add(switchToCreateAccountButton, gbc);

        gbc.gridy = 6; // Adjust the row position for the Admin button
        JButton adminButton = new JButton("Admin Login");
        adminButton.addActionListener(e -> {
            frame.dispose(); // Close the current login window
            AdminLogin.main(new String[0]); // Open the AdminLogin window
        });
        panel.add(adminButton, gbc);
    
        // Add another empty vertical space at the bottom
        gbc.gridy = 6;
        gbc.weighty = 1.0;
        panel.add(Box.createVerticalStrut(20), gbc);
    
        return panel;
    }
    

    

    private JPanel createAccountPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(300, 400));

        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);
    
        // Add an empty vertical space at the top to center the title better
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0; // Helps center the title vertically
        panel.add(Box.createVerticalStrut(20), gbc);
    
        // Add the title label
        JLabel title = new JLabel("Create New Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0; // Reset weight so it only applies to spacing rows
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
    
        // Add "Email" label and text field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Email:"), gbc);
    
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField emailField = new JTextField(10);
        panel.add(emailField, gbc);
    
        // Add "Password" label and text field
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Password:"), gbc);
    
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JPasswordField passwordField = new JPasswordField(10);
        panel.add(passwordField, gbc);
    
        // Add "Confirm Password" label and text field
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Confirm Password:"), gbc);
    
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JPasswordField confirmPasswordField = new JPasswordField(10);
        panel.add(confirmPasswordField, gbc);
    
        // Add "Create Account" button
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
    
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username, Email, and Password cannot be empty.");
                return;
            }
    
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match. Please try again.");
                return;
            }
    
            try {
                int userId = addUser(username, email, password);
                JOptionPane.showMessageDialog(frame, "Account created successfully! Your User ID is: " + userId);
                switchToPanel(loginPanel);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        panel.add(createAccountButton, gbc);
    
        // Add "Back to Login" button
        gbc.gridy = 7;
        JButton switchToLoginButton = new JButton("Back to Login");
        switchToLoginButton.addActionListener(e -> switchToPanel(loginPanel));
        panel.add(switchToLoginButton, gbc);
    
        // Add another empty vertical space at the bottom
        gbc.gridy = 8;
        gbc.weighty = 1.0;
        panel.add(Box.createVerticalStrut(20), gbc);
    
        return panel;
    }
    

    private void switchToPanel(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    private int addUser(String username, String email, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String query = "INSERT INTO user (username, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("User ID generation failed.");
            }
        }
    }
   // Custom JPanel for displaying background image
   static class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel() {
        backgroundImage = new ImageIcon("C:\\Users\\Rudraksh\\OneDrive\\Desktop\\DR\\src\\main\\java\\Resources\\images (5).jpg").getImage(); // Change to your image path
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
