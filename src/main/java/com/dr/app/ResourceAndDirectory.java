package com.dr.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import com.dr.app.WebPageLayout;

public class ResourceAndDirectory {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ResourceAndDirectory::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Disaster Management Resources");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the frame to full-screen mode but with the title bar
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false); // Keep window decorations for title bar
        
        // Left panel with image
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the image
                ImageIcon icon = new ImageIcon("C:\\Users\\Rudraksh\\OneDrive\\Desktop\\DR\\src\\main\\java\\Resources\\images (15).jpg"); // Replace with your image path
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        // Set preferred size to make the left panel larger
        leftPanel.setPreferredSize(new Dimension(300, 600)); // Adjust the width as needed

        // Set a background color for the left panel
        leftPanel.setBackground(Color.LIGHT_GRAY); // Change color as needed

        // Right panel with resources and directory using HTML
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        // Set a background color for the right panel
        rightPanel.setBackground(Color.WHITE); // Change color as needed

        // Using JEditorPane to display HTML content
        JEditorPane htmlEditor = new JEditorPane();
        htmlEditor.setContentType("text/html");
        htmlEditor.setText(
            "<html>" +
            "<head>" +
            "<style>" +
            "body { font-size: 18px; background-color: #f0f0f0; }" + // Set background color and font size
            "h1 { color: #333; text-decoration: underline; text-align: center; font-size: 28px; }" +
            "h2 { color: #333; text-decoration: underline; font-size: 20px; }" +  // Center align the main heading and underline
            "h3 { color: #333; text-decoration: underline; font-size: 18px; }" + // Underline subheadings
            "span.name { font-size: 18px; font-weight: bold; }" + // Larger font size for names
            "hr { border: 1px solid #333; margin: 20px 0; }" + // Style for horizontal rules
            "</style>" +
            "</head>" +
            "<body>" +
            "<h1>Resources and Directory</h1>" +
            "<hr>" +
            "<h2>Important Contacts</h2>" +
            "<p>For inquiries related to disaster management, you can reach key members of the National Disaster Management Authority (NDMA):</p>" +
            "<ul>" +
            "<li><h3><span class='name'>Shri M. Shashidhar Reddy, MLA and Vice Chairman:</span></h3>" +
            "<p>+91-11-26701701 or 704, Fax: 011-26701706, Email: <a href='mailto:vc@ndma.gov.in'>vc@ndma.gov.in</a></p></li>" +
            "<li><h3><span class='name'>Shri J K Sinha, Member:</span></h3>" +
            "<p>011-26701743, 011-24122310, 9818384040, Email: <a href='mailto:jksinha@ndma.gov.in'>jksinha@ndma.gov.in</a></p></li>" +
            "<li><h3><span class='name'>Shri B. Bhattacharjee, Member:</span></h3>" +
            "<p>011-26701780/806, 011-23070145, 9971147620, Emails: <a href='mailto:bbhattacharjee@ndma.gov.in'>bbhattacharjee@ndma.gov.in</a>, <a href='mailto:bcharjee@gmail.com'>bcharjee@gmail.com</a></p></li>" +
            "<li><h3><span class='name'>Shri K.M. Singh, Member:</span></h3>" +
            "<p>011-26701735, Email: <a href='mailto:kmsingh100@gmail.com'>kmsingh100@gmail.com</a></p></li>" +
            "<li><h3><span class='name'>Dr. Muzaffar Ahmad, Member:</span></h3>" +
            "<p>011-26701736, Fax: 26701767, Email: <a href='mailto:muzaffarahmad@ndma.gov.in'>muzaffarahmad@ndma.gov.in</a></p></li>" +
            "<li><h3><span class='name'>Shri T Nanda Kumar, Member:</span></h3>" +
            "<p>011-26701782, Email: <a href='mailto:tnandakumar@ndma.gov.in'>tnandakumar@ndma.gov.in</a></p></li>" +
            "<li><h3><span class='name'>Maj. Gen. J K Bansal (Retd.), Member:</span></h3>" +
            "<p>011-26701778, Email: <a href='mailto:jkbansal@ndma.gov.in'>jkbansal@ndma.gov.in</a></p></li>" +
            "<li><h3><span class='name'>Prof. Harsh K Gupta, Member:</span></h3>" +
            "<p>011-26701738, Fax: 011-26701769, Phone: 9999868111, Emails: <a href='mailto:harsh@ndma.gov.in'>harsh@ndma.gov.in</a>, <a href='mailto:harshndma@gmail.com'>harshndma@gmail.com</a></p></li>" +
            "</ul>" +
            "<hr>" +
            "<h2>National Disaster Response Force (NDRF) HQ Contact Details</h2>" +
            "<hr>" +
            "<h3>Control Room Details:-</h3>" +
            "<p>Tele/Fax No.: 011-23438091, 011-23438136<br>" +
            "Email ID: <a href='mailto:hq.ndrf@nic.in'>hq.ndrf@nic.in</a></p>" +
            "<h3>Reception / Exchange Details:-</h3>" +
            "<p>No.: 011-23438017, 011-23438019</p>" +
            "<hr>" +
            "<h2>Emergency Services</h2>" + // Added heading for Emergency Services
            "<hr>" +
            "<p>Air Ambulance: 9540161344<br>" +
            "Disaster Management Services: 108</p>" +
            "<p>For additional support and coordination during disaster response, you can contact the NDRF headquarters or utilize the emergency services provided above.</p>" +
            "</body>" +
            "</html>"
        );
        htmlEditor.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(htmlEditor);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the back button at the bottom of the right panel
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assuming WebPageLayout is another JFrame you want to open
                WebPageLayout.main(new String[0]); // Create a new instance of WebPageLayout
                frame.dispose(); // Close the current ResourceAndDirectory window
            }
        });

        // Add back button to the right panel
        rightPanel.add(backButton, BorderLayout.SOUTH);

        // Set the layout for the frame and add panels
        frame.setLayout(new BorderLayout());
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}
