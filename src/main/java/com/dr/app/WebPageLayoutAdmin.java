package com.dr.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import javax.swing.border.Border;

public class WebPageLayoutAdmin {
    private static JPanel rightPanel; // Declare rightPanel as a static variable
    private static JTable userTable; // Declare the JTable for user details
    private static JTable incidentTable; // Declare the JTable for incident details
    private static JTable registrationTable; // Declare the JTable for registration details
    private static Connection connection; // Database connection

    public static void main(String[] args) {
        // Initialize database connection
        initializeDatabaseConnection();

        // Create the frame
        JFrame frame = new JFrame("Webpage Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        frame.setLayout(new BorderLayout());

        // Create the left panel for buttons
        JPanel leftPanel = createLeftPanel();
        // Create the right panel for displaying information
        rightPanel = createRightPanel(); // Initialize rightPanel

        // Add panels to the frame
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    private static void initializeDatabaseConnection() {
        // Replace with your database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/USER"; // Change database name
        String user = "root"; // Change username
        String password = "Rudraksh2005."; // Change password

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        panel.setBorder(BorderFactory.createTitledBorder("Sections"));
        panel.setPreferredSize(new Dimension(250, 0)); // Set the width of the left panel

        // Create a panel for buttons with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the button panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 7, 20, 7); // Set insets for spacing between buttons
        gbc.gridx = 0; // Start in the first column

        // Create buttons
        JButton reportButton = createButton("User Details");
        JButton volunteerButton = createButton("Incident Details");
        JButton resourcesButton = createButton("Volunteer Details");

        // Add action listeners for buttons
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContent("User Details");
            }
        });
        volunteerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContent("Incident Details"); // Updated action listener
            }
        });
        resourcesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContent("Volunteer Details"); // Updated action listener
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(reportButton, gbc);
        gbc.gridy = 1; // Move to the next row
        buttonPanel.add(volunteerButton, gbc);
        gbc.gridy = 2; // Move to the next row
        buttonPanel.add(resourcesButton, gbc);

        // Center the button panel in the left panel
        panel.add(Box.createVerticalGlue()); // Add vertical glue for centering
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue()); // Add vertical glue for centering

        return panel;
    }

    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(190, 40)); // Set button size
        button.setMinimumSize(new Dimension(190, 40));
        return button;
    }

    private static JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Content Area"));
        panel.setLayout(new BorderLayout());

        // Add a default message to the right panel
        JLabel messageLabel = new JLabel("Select an option from the left panel.", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size and style
        panel.add(messageLabel, BorderLayout.CENTER); // Center the message

        return panel;
    }

    private static void updateContent(String section) {
        // Update content area based on the section selected
        if ("User Details".equals(section)) {
            showUserDetails(); // Show user details in the right panel
        } else if ("Incident Details".equals(section)) {
            showIncidentDetails(); // Show incident details in the right panel
        } else if ("Volunteer Details".equals(section)) {
            showRegistrationDetails(); // Show registration details in the right panel
        } else {
            System.out.println("Selected section: " + section);
        }
    }

    private static void showUserDetails() {
        // Query user data from the database
        List<Object[]> userData = fetchUserData();

        // Create the table with the user data
        String[] columnNames = {"User ID", "Username", "Email", "Password"};
        userTable = new JTable(userData.toArray(new Object[0][]), columnNames);
        userTable.setFillsViewportHeight(true); // Make the table fill the viewport height

        // Clear the right panel and add the table
        rightPanel.removeAll();
        rightPanel.add(new JScrollPane(userTable)); // Add the table to the right panel inside a scroll pane

        // Revalidate and repaint the right panel to reflect the changes
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private static List<Object[]> fetchUserData() {
        List<Object[]> userData = new ArrayList<>();
        String query = "SELECT id, username, email, password FROM user"; // Fetch data from user table

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Retrieve user data and add it to the list
                userData.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password") // Consider not showing passwords in the UI
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userData;
    }

    private static void showIncidentDetails() {
        // Query incident data from the database
        List<Object[]> incidentData = fetchIncidentData();

        // Create the table with the incident data
        String[] columnNames = {"Incident ID", "Username", "First Name", "Last Name", "Incident Type", "Location", "Description", "Image", "Report Date"};
        incidentTable = new JTable(incidentData.toArray(new Object[0][]), columnNames);
        incidentTable.setFillsViewportHeight(true); // Make the table fill the viewport height

        // Clear the right panel and add the table
        rightPanel.removeAll();
        rightPanel.add(new JScrollPane(incidentTable)); // Add the table to the right panel inside a scroll pane

        // Revalidate and repaint the right panel to reflect the changes
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private static List<Object[]> fetchIncidentData() {
        List<Object[]> incidentData = new ArrayList<>();
        String query = "SELECT incident_id, username, first_name, last_name, incident_type, incident_location, incident_description, incident_image, report_date FROM Report_Incident"; // Fetch data from Report_Incident table

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Retrieve incident data and add it to the list
                // Convert BLOB to ImageIcon for display
                Blob blob = rs.getBlob("incident_image");
                ImageIcon imageIcon = null;
                if (blob != null) {
                    byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                    imageIcon = new ImageIcon(imageBytes);
                }

                incidentData.add(new Object[]{
                    rs.getInt("incident_id"),
                    rs.getString("username"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("incident_type"),
                    rs.getString("incident_location"),
                    rs.getString("incident_description"),
                    imageIcon, // Add the ImageIcon to the table data
                    rs.getTimestamp("report_date") // Use Timestamp for date
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidentData;
    }

    private static void showRegistrationDetails() {
        // Query registration data from the database
        List<Object[]> registrationData = fetchRegistrationData();

        // Create the table with the registration data
        String[] columnNames = {"Registration ID", "User ID", "Incident ID", "First Name", "Last Name", "Email", "Phone Number", "Age", "Gender", "Registration Date"};
        registrationTable = new JTable(registrationData.toArray(new Object[0][]), columnNames);
        registrationTable.setFillsViewportHeight(true); // Make the table fill the viewport height

        // Clear the right panel and add the table
        rightPanel.removeAll();
        rightPanel.add(new JScrollPane(registrationTable)); // Add the table to the right panel inside a scroll pane

        // Revalidate and repaint the right panel to reflect the changes
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private static List<Object[]> fetchRegistrationData() {
        List<Object[]> registrationData = new ArrayList<>();
        String query = "SELECT registration_id, user_id, incident_id, first_name, last_name, email, phone_number, age, gender, registration_date FROM Registration"; // Fetch data from Registration table

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Retrieve registration data and add it to the list
                registrationData.add(new Object[]{
                    rs.getInt("registration_id"),
                    rs.getInt("user_id"),
                    rs.getInt("incident_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getTimestamp("registration_date") // Use Timestamp for date
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrationData;
    }
}
