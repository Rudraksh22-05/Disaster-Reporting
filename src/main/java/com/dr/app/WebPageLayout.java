package com.dr.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.border.Border;
//import com.dr.app.IncidentReport;
//import com.dr.app.Registration;
//import com.dr.app.ReasonsAndSolutions;
//import com.dr.app.ResourceAndDirectory;
//import com.dr.app.DataAndStatistics;

public class WebPageLayout {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Webpage Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        frame.setLayout(new BorderLayout());

        // Create the left panel for buttons
        JPanel leftPanel = createLeftPanel(frame); // Pass the frame reference
        // Create the right panel for displaying information
        JPanel rightPanel = createRightPanel();

        // Add panels to the frame
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    private static JPanel createLeftPanel(JFrame frame) {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        leftPanel.setPreferredSize(new Dimension(250, 0)); // Set the width of the left panel
    
        // Create a panel for buttons with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the button panel
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 7, 20, 7); // Set insets for spacing between buttons
        gbc.gridx = 0; // Start in the first column
    
        // Create buttons
        JButton aboutButton = createButton("About Us");
        JButton reportButton = createButton("Report an Incident");
        JButton volunteerButton = createButton("Volunteer");
        JButton resourcesButton = createButton("Resources and Directory");
        JButton buildButton = createButton("Build A Kit");
        JButton dataButton = createButton("Data And Statistics");
        JButton reasonsButton = createButton("Reasons And Solutions");
    
        // Add action listener for the "About Us" button
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContent("About Us");
            }
        });
    
        // Add action listener for the "Report an Incident" button
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IncidentReport(); // Open the IncidentReport window
                frame.dispose(); // Close the current WebPageLayout frame
            }
        });
    
        // Add action listener for the "Volunteer" button
        volunteerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registration(); // Open the Registration window
                frame.dispose(); // Close the current WebPageLayout frame
            }
        });
    
        // Add action listener for the "Reasons and Solutions" button
        reasonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReasonsAndSolutions.main(new String[0]); // Open ReasonsAndSolutions window
            }
        });

        resourcesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResourceAndDirectory.main(new String[0]); // Open ReasonsAndSolutions window
            }
        });

        dataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataAndStatistics.main(new String[0]); // Open ReasonsAndSolutions window
            }
        });

        buildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuildAKit.main(new String[0]); // Open ReasonsAndSolutions window
            }
        });
    
        // Add buttons to the button panel with GridBagConstraints
        buttonPanel.add(aboutButton, gbc);
        gbc.gridy = 1; // Move to the next row
        buttonPanel.add(reportButton, gbc);
        gbc.gridy = 2; // Move to the next row
        buttonPanel.add(volunteerButton, gbc);
        gbc.gridy = 3; // Move to the next row
        buttonPanel.add(resourcesButton, gbc);
        gbc.gridy = 4; // Move to the next row
        buttonPanel.add(buildButton, gbc);
        gbc.gridy = 5;
        buttonPanel.add(dataButton, gbc);
        gbc.gridy = 6;
        buttonPanel.add(reasonsButton, gbc); // Add the Reasons and Solutions button
    
        // Center the button panel in the left panel
        leftPanel.add(Box.createVerticalGlue()); // Add vertical glue for centering
        leftPanel.add(buttonPanel);
        leftPanel.add(Box.createVerticalGlue()); // Add vertical glue for centering
    
        return leftPanel;
    }

    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(190, 40)); // Set button size
        button.setMinimumSize(new Dimension(190, 40));
        return button;
    }

    private static JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        rightPanel.setLayout(new BorderLayout());

        // Create the About Us section
        JPanel aboutUsPanel = new JPanel();
        aboutUsPanel.setLayout(new BorderLayout());

        // Create the heading label
        JLabel headingLabel = new JLabel("About Us");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        headingLabel.setBackground(Color.LIGHT_GRAY);
        headingLabel.setBorder(BorderFactory.createEmptyBorder(22, 10, 22, 10));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setVerticalAlignment(SwingConstants.TOP); // Center align the heading

        // Create a JTextPane for the "About Us" text
        JTextPane aboutTextPane = new JTextPane();
        aboutTextPane.setEditable(false); // Make it non-editable
        aboutTextPane.setContentType("text/html"); // Set content type to HTML
        aboutTextPane.setText(getAboutUsHtmlText());
        aboutTextPane.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size
        aboutTextPane.setBackground(Color.WHITE); // Set a default background color
        aboutTextPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Wrap the JTextPane in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(aboutTextPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove the border around the scroll pane

        // Add components to the about us panel
        aboutUsPanel.add(headingLabel, BorderLayout.NORTH); // Add heading to the top
        aboutUsPanel.add(scrollPane, BorderLayout.CENTER); // Fill the center with the scroll pane

        // Add about us panel to the right panel
        rightPanel.add(aboutUsPanel, BorderLayout.CENTER); // Use the about us panel
        return rightPanel;
    }

    private static String getAboutUsHtmlText() {
        return "<html><body style='text-align:center; font-family:Arial; font-size:18px; background-color:lightyellow;'>" +
               "<p>• Welcome to CrisisConnect, a dedicated platform designed to empower communities during times of disaster.</p>" +
               "<p>• Our mission is to provide a comprehensive resource for reporting and responding to natural disasters like landslides and earthquakes.</p><br>" +
               "<p>• In recent years, natural disasters have become increasingly frequent and severe. According to the United Nations, " +
               "between 2000 and 2019, the world experienced an average of 387 natural disasters per year, displacing millions " +
               "and causing significant economic and social disruption. Our goal is to create a proactive community that can respond effectively and support one another in times of crisis.</p><br>" +
               "<p>• At CrisisConnect, we offer a range of features to assist users during disasters:</p>" +
               "<p>Disaster Reporting: Quickly and easily report incidents of landslides and earthquakes in your area, helping authorities respond swiftly and efficiently.</p>" +
               "<p>Resource Finder: Access essential resources, including emergency shelters, medical assistance, and food supplies.</p>" +
               "<p>Volunteer Registration: Join our network of volunteers ready to assist during disasters. Your skills and time can make a significant difference in your community.</p>" +
               "<p>Family Communication: Keep your loved ones informed about your safety and well-being with our email feature, ensuring they receive timely updates.</p>" +
               "<p>• We are committed to fostering resilience and preparedness in our communities. By providing the tools and resources " +
               "necessary for effective disaster response, we aim to minimize the impact of natural disasters and promote safety and recovery.</p><br>" +
               "<p>• Join us in making a difference. Together, we can build a safer, more prepared future.</p>" +
               "</body></html>";
    }

    private static void updateContent(String section) {
        // Update content area based on the section selected
        if ("About Us".equals(section)) {
            // Load About Us content
        }
        // Add other section updates here
    }
}
