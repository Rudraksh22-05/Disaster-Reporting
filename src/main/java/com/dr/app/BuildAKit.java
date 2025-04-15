package com.dr.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
//import com.dr.app.WebPageLayout;

public class BuildAKit {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Build a Kit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the frame full screen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false); // Optional: remove window decorations

        // Create the header
        JLabel header = new JLabel("Build a Kit", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBackground(Color.LIGHT_GRAY);
        header.setOpaque(true);
        frame.add(header, BorderLayout.NORTH);

        // Create the right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);

        // Create a JEditorPane for the right panel content
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");

        // Set the HTML content with increased font size
        editorPane.setText("<html>" +
                "<head><style>" +
                "body { font-size: 18px; }" +  // Increase font size here
                "</style></head>" +
                "<body>" +
                "After an emergency, you may need to survive on your own for several days.<br>" +
                "Being prepared means having your own food, water, and other supplies to last for several days.<br>" +
                "A disaster supplies kit is a collection of basic items your household may need in the event of an emergency.<br><br>" +
                "A disaster kit is crucial for:<br>" +
                "<b>1. Immediate Access:</b> Provides essential supplies quickly during emergencies.<br>" +
                "<b>2. Basic Survival:</b> Includes food, water, and first aid for several days.<br>" +
                "<b>3. Reduced Stress:</b> Minimizes anxiety and promotes clear decision-making.<br>" +
                "<b>4. Self-Sufficiency:</b> Supports independence until help arrives.<br>" +
                "<b>5. Family Preparedness:</b> Encourages planning and readiness among family members.<br>" +
                "<b>6. Protection for Vulnerable Groups:</b> Tailored to meet the needs of children, elderly, and disabled individuals.<br>" +
                "<b>7. Risk Mitigation:</b> Proactively addresses potential emergencies.<br>" +
                "<b>8. Community Resilience:</b> Contributes to quicker recovery for the community.<br>" +
                "<b>9. Cost-Effectiveness:</b> Reduces costs compared to last-minute purchases.<br>" +
                "<b>10. Regular Updates:</b> Encourages routine checks and replenishing supplies.<br><br>" +
                "Make sure your emergency kit is stocked with the items on the checklist below.<br>" +
                "Once you take a look at the basic items, consider what unique needs your family might have, such as supplies for pets or seniors.<br><br>" +

                "<h1>Basic Disaster Supplies Kit</h1>" +
                "<p>To assemble your kit, store items in airtight plastic bags and put your entire disaster supplies kit in one or two easy-to-carry containers such as plastic bins or a duffel bag.</p>" +
                "<p>A basic emergency supply kit could include the following recommended items:</p>" +
                "<ul>" +
                "<li>Water (one gallon per person per day for several days, for drinking and sanitation)</li>" +
                "<li>Food (at least a several-day supply of non-perishable food)</li>" +
                "<li>Battery-powered or hand crank radio and a NOAA Weather Radio with tone alert</li>" +
                "<li>Flashlight</li>" +
                "<li>First aid kit</li>" +
                "<li>Extra batteries</li>" +
                "<li>Whistle (to signal for help)</li>" +
                "<li>Dust mask (to help filter contaminated air)</li>" +
                "<li>Plastic sheeting, scissors and duct tape (to shelter in place)</li>" +
                "<li>Moist towelettes, garbage bags and plastic ties (for personal sanitation)</li>" +
                "<li>Wrench or pliers (to turn off utilities)</li>" +
                "<li>Manual can opener (for food)</li>" +
                "<li>Local maps</li>" +
                "<li>Cell phone with chargers and a backup battery</li>" +
                "</ul>" +
                "<h1>Additional Emergency Supplies</h1>" +
                "<p>Consider adding the following items to your emergency supply kit based on your individual needs:</p>" +
                "<ul>" +
                "<li>Soap, hand sanitizer and disinfecting wipes to disinfect surfaces</li>" +
                "<li>Prescription medications. About half of all Americans take a prescription medicine every day. An emergency can make it difficult for them to refill their prescription or to find an open pharmacy. Organize and protect your prescriptions, over-the-counter drugs, and vitamins to prepare for an emergency.</li>" +
                "<li>Non-prescription medications such as pain relievers, anti-diarrhea medication, antacids or laxatives</li>" +
                "<li>Prescription eyeglasses and contact lens solution</li>" +
                "<li>Infant formula, bottles, diapers, wipes and diaper rash cream</li>" +
                "<li>Pet food and extra water for your pet</li>" +
                "<li>Cash or traveler's checks</li>" +
                "<li>Important family documents such as copies of insurance policies, identification and bank account records saved electronically or in a waterproof, portable container</li>" +
                "<li>Sleeping bag or warm blanket for each person</li>" +
                "<li>Complete change of clothing appropriate for your climate and sturdy shoes</li>" +
                "<li>Fire extinguisher</li>" +
                "<li>Matches in a waterproof container</li>" +
                "<li>Feminine supplies and personal hygiene items</li>" +
                "<li>Mess kits, paper cups, plates, paper towels and plastic utensils</li>" +
                "<li>Paper and pencil</li>" +
                "<li>Books, games, puzzles or other activities for children</li>" +
                "</ul>" +
                "</body></html>");

        editorPane.setEditable(false);
        editorPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the editor pane to a scroll pane
        JScrollPane scrollPane = new JScrollPane(editorPane);
        rightPanel.add(scrollPane);

        // Create the button to open PDF
        JButton pdfButton = new JButton("Emergency Supply List");
        pdfButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the button
        pdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Specify the path to your PDF file here
                    File pdfFile = new File("C:\\Users\\Rudraksh\\OneDrive\\Desktop\\DR\\src\\Resources\\resources\\Resources\\kit.pdf");
                    if (pdfFile.exists()) {
                        Desktop.getDesktop().open(pdfFile);
                    } else {
                        JOptionPane.showMessageDialog(frame, "PDF file not found.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Could not open the PDF file.");
                }
            }
        });

        // Add the button to the right panel
        rightPanel.add(pdfButton);

        // Create the Back button and set its size
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(pdfButton.getPreferredSize()); // Match the size of the PDF button
        backButton.setMinimumSize(pdfButton.getMinimumSize()); // Ensure minimum size is the same
        backButton.setMaximumSize(pdfButton.getMaximumSize()); // Ensure maximum size is the same
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assuming WebPageLayout is another JFrame you want to open
                WebPageLayout.main(new String[0]); // Create a new instance of WebPageLayout
                frame.dispose(); // Close the current ResourceAndDirectory window
            }
        });

        // Add the Back button to the right panel
        rightPanel.add(backButton);

        // Create the left panel with a dark gray background
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the image to fit the panel
                try {
                    Image img = Toolkit.getDefaultToolkit().getImage("\"C:\\Users\\Rudraksh\\OneDrive\\Desktop\\DR\\src\\Resources\\images (15).jpg\"");
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        leftPanel.setBackground(Color.DARK_GRAY); // Set dark gray background
        leftPanel.setPreferredSize(new Dimension(250, 0)); // Set preferred size

        // Create a container for the left and right panels
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(leftPanel, BorderLayout.WEST);
        centerPanel.add(rightPanel, BorderLayout.CENTER);

        // Add the center panel to the frame
        frame.add(centerPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}
