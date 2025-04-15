package com.dr.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
//import javax.swing.border.Border;
import javax.imageio.ImageIO;

public class IncidentReport extends JFrame {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/USER";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Rudraksh2005.";

    private JTextField usernameField, firstNameField, lastNameField, locationField, descriptionField;
    private JComboBox<String> incidentTypeComboBox;
    private File imageFile = null; // Class-level variable to hold the selected image file

    public IncidentReport() {
        // Set up the main frame
        setTitle("Incident Report");
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Fullscreen mode
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load background image
        String imagePath = "src/main/resources/images/incident_background.jpg";
        Image backgroundImage = null;
        try {
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                System.err.println("Background image not found at: " + imageFile.getAbsolutePath());
            } else {
                backgroundImage = ImageIO.read(imageFile);
                if (backgroundImage == null) {
                    System.err.println("Failed to load background image");
                } else {
                    System.out.println("Successfully loaded background image from: " + imagePath);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            e.printStackTrace();
        }

        BackgroundPanel backgroundLabel = new BackgroundPanel(backgroundImage);

        // Create and set up the main panel with a thicker border
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false); // Make the main panel transparent

        // Create a bordered panel with a white background
        JPanel borderedPanel = new JPanel();
        borderedPanel.setLayout(new GridBagLayout());
        borderedPanel.setBackground(Color.WHITE); // Set white background for the bordered panel
        borderedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3)); // Simple line border

        // Create GridBagConstraints for the borderedPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Set insets (top, left, bottom, right)

        // Title label inside the panel
        JLabel titleLabel = new JLabel("Incident Report Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across both columns
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        borderedPanel.add(titleLabel, gbc);

        // Add fields and labels to the bordered panel
        gbc.gridwidth = 1; // Reset grid width
        gbc.anchor = GridBagConstraints.WEST; // Align labels to the left
        gbc.gridy++; // Move to the next row

        gbc.gridx = 0;
        borderedPanel.add(new JLabel("Username*:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(20);
        borderedPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        borderedPanel.add(new JLabel("First Name*:"), gbc);

        gbc.gridx = 1;
        firstNameField = new JTextField(20);
        borderedPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        borderedPanel.add(new JLabel("Last Name*:"), gbc);

        gbc.gridx = 1;
        lastNameField = new JTextField(20);
        borderedPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        borderedPanel.add(new JLabel("Incident Type*:"), gbc);

        gbc.gridx = 1;
        incidentTypeComboBox = new JComboBox<>(new String[]{"Flood", "Landslide", "Earthquake"});
        borderedPanel.add(incidentTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        borderedPanel.add(new JLabel("Incident Location*:"), gbc);

        gbc.gridx = 1;
        locationField = new JTextField(20);
        borderedPanel.add(locationField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        borderedPanel.add(new JLabel("Incident Description*:"), gbc);

        gbc.gridx = 1;
        descriptionField = new JTextField(20);
        borderedPanel.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        borderedPanel.add(new JLabel("Image (optional):"), gbc);

        gbc.gridx = 1;
        JButton chooseImageButton = new JButton("Choose Image");
        chooseImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JPG Images", "jpg", "jpeg"));
                int option = fileChooser.showOpenDialog(IncidentReport.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    imageFile = fileChooser.getSelectedFile();
                    // Optional: Log the selected file
                    System.out.println("Chosen image file: " + imageFile.getAbsolutePath());
                }
            }
        });
        borderedPanel.add(chooseImageButton, gbc);

        // Submit button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span across two columns to center
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment

        JButton submitButton = new JButton("Submit Report");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitReport();
            }
        });
        borderedPanel.add(submitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span across two columns to center
        gbc.anchor = GridBagConstraints.CENTER;

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WebPageLayout.main(new String[0]); // Create a new instance of WebPageLayout
                dispose(); // Close the current IncidentReport window
            }
        });
        borderedPanel.add(backButton, gbc);

        // Add the bordered panel to the main panel
        mainPanel.add(borderedPanel);

        // Add the main panel to the background label
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(mainPanel, BorderLayout.CENTER);

        // Add the background label to the frame
        add(backgroundLabel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    private void submitReport() {
        // Get values from fields
        String username = usernameField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String incidentType = incidentTypeComboBox.getSelectedItem().toString();
        String location = locationField.getText().trim();
        String description = descriptionField.getText().trim();

        // Validate inputs
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
            incidentType.isEmpty() || location.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all mandatory fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if username exists in the database
        if (!isUsernameValid(username)) {
            JOptionPane.showMessageDialog(this, "Username does not exist. Please enter a valid username.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insert the incident report
        insertIncidentReport(username, firstName, lastName, incidentType, location, description, imageFile);
    }

    private boolean isUsernameValid(String username) {
        String sqlCheckUser = "SELECT 1 FROM user WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sqlCheckUser)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();  // Returns true if the username exists
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void insertIncidentReport(String username, String firstName, String lastName,
                                      String incidentType, String location, String description, File imageFile) {
        String sql = "INSERT INTO Report_Incident (username, first_name, last_name, incident_type, " +
                     "incident_location, incident_description, incident_image) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set values in the SQL query
            pstmt.setString(1, username);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, incidentType);
            pstmt.setString(5, location);
            pstmt.setString(6, description);

            // Check if the image file exists and is readable, and restrict to JPG files
            if (imageFile != null) {
                String filePath = imageFile.getAbsolutePath();
                System.out.println("Selected file: " + filePath);
                System.out.println("File exists: " + imageFile.exists());
                System.out.println("Can read file: " + imageFile.canRead());

                // Validate the file extension
                if (filePath.toLowerCase().endsWith(".jpg") || filePath.toLowerCase().endsWith(".jpeg")) {
                    if (imageFile.exists() && imageFile.canRead()) {
                        try (FileInputStream fis = new FileInputStream(imageFile)) {
                            pstmt.setBinaryStream(7, fis, (int) imageFile.length());
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(this, "Error reading image file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace(); // Log stack trace
                            pstmt.setNull(7, Types.BLOB); // Set to NULL if read fails
                        }
                    } else {
                        pstmt.setNull(7, Types.BLOB); // If file cannot be read, set as NULL
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a valid JPG image.", "Invalid File Type", JOptionPane.ERROR_MESSAGE);
                    pstmt.setNull(7, Types.BLOB); // If file is invalid, set as NULL
                    return; // Exit the method early
                }
            } else {
                pstmt.setNull(7, Types.BLOB); // No image provided
            }

            // Execute insertion
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Incident report inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert the incident report.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Log stack trace
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IncidentReport());
    }

    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
