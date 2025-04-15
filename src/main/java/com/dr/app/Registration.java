package com.dr.app;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.imageio.ImageIO;
import java.io.File;

public class Registration extends JFrame {

    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/USER"; 
    private static final String DB_USER = "root";  
    private static final String DB_PASSWORD = "Rudraksh2005."; 

    private JTextField userIdField, firstNameField, lastNameField, emailField, phoneField, ageField;
    private JComboBox<String> incidentComboBox; // Dropdown for incidents
    private JRadioButton maleButton, femaleButton, preferNotToSayButton;
    private JButton submitButton;

    public Registration() {
        setTitle("Registration Form");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Custom panel with background image
        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage;

            {
                // Initialize the background image in the instance initializer
                try {
                    File imageFile = new File("src/main/resources/images/registration_background.jpg");
                    if (!imageFile.exists()) {
                        System.err.println("Background image not found at: " + imageFile.getAbsolutePath());
                    } else {
                        backgroundImage = ImageIO.read(imageFile);
                        if (backgroundImage == null) {
                            System.err.println("Failed to load background image");
                        } else {
                            System.out.println("Successfully loaded background image");
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error loading background image: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        // Main panel to hold all components
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false); 
        mainPanel.setLayout(new GridBagLayout());
        backgroundPanel.add(mainPanel);

        // Form panel with GridBagLayout for form fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(500, 500)); 
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); 
        formPanel.setBorder(border);
        formPanel.setOpaque(true); 
        formPanel.setBackground(new Color(211,211,211));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        userIdField = new JTextField();
        incidentComboBox = new JComboBox<>(); // New JComboBox for incidents
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        ageField = new JTextField();

        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        preferNotToSayButton = new JRadioButton("Prefer not to say");

        Color lightGrey = new Color(211, 211, 211); // Light grey color
        maleButton.setBackground(lightGrey);
        femaleButton.setBackground(lightGrey);
        preferNotToSayButton.setBackground(lightGrey);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(preferNotToSayButton);

        submitButton = new JButton("Register");

        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 10, 5);
        JLabel titleLabel = new JLabel("Volunteer Registration", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        formPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("User ID*:"), gbc);
        gbc.gridx = 1;
        formPanel.add(userIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Incident*:"), gbc);  // Updated label for incident
        gbc.gridx = 1;
        formPanel.add(incidentComboBox, gbc);  // Added JComboBox for incidents

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("First Name*:"), gbc);
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Last Name*:"), gbc);
        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Email*:"), gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(new JLabel("Age*:"), gbc);
        gbc.gridx = 1;
        formPanel.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        formPanel.add(new JLabel("Gender*:"), gbc);
        gbc.gridx = 1;

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setOpaque(false);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderPanel.add(preferNotToSayButton);
        formPanel.add(genderPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridwidth = 2;
        formPanel.add(submitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span across two columns to center
        gbc.anchor = GridBagConstraints.CENTER;
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WebPageLayout.main(new String[0]); // Create a new instance of WebPageLayout
                dispose(); // Close the current Registration window
            }
        });
        formPanel.add(backButton, gbc);

        mainPanel.add(formPanel);

        setContentPane(backgroundPanel);

        // Populate incident JComboBox
        populateIncidentComboBox();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertRegistrationData();
            }
        });

        setVisible(true);
    }

    private void populateIncidentComboBox() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("SELECT incident_id, incident_type FROM Report_Incident");
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                int incidentId = rs.getInt("incident_id");
                String incidentType = rs.getString("incident_type");
                incidentComboBox.addItem(incidentType + " (ID: " + incidentId + ")"); // Add incident name and ID to JComboBox
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading incident data.");
        }
    }

    private void insertRegistrationData() {
        try {
            int userId = Integer.parseInt(userIdField.getText().trim());
            String selectedIncident = (String) incidentComboBox.getSelectedItem();
            int incidentId = Integer.parseInt(selectedIncident.replaceAll("[^0-9]", ""));  // Extract ID with regex
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            String phoneNumber = phoneField.getText().isEmpty() ? null : phoneField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String gender = maleButton.isSelected() ? "Male" :
                            femaleButton.isSelected() ? "Female" :
                            "Prefer not to say";
    
            // Database insertion logic
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO Registration (user_id, incident_id, first_name, last_name, email, phone_number, age, gender) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                
                // Set parameters
                pstmt.setInt(1, userId);
                pstmt.setInt(2, incidentId);
                pstmt.setString(3, firstName);
                pstmt.setString(4, lastName);
                pstmt.setString(5, email);
                pstmt.setString(6, phoneNumber);
                pstmt.setInt(7, age);
                pstmt.setString(8, gender);
    
                // Execute update
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "A new registration was inserted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to insert registration.");
                }
    
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for User ID and Age.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting data. SQL Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Registration());
    }
}
