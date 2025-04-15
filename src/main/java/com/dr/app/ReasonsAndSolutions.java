package com.dr.app;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionListener;
//import com.dr.app.WebPageLayout;

public class ReasonsAndSolutions {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the frame
            JFrame frame = new JFrame("Reasons and Solutions");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Maximize the frame to occupy the full screen with title bar and controls
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setResizable(true); // Allow resizing if needed
            frame.setLayout(new BorderLayout());

            // Create the main heading
            JLabel mainHeading = new JLabel("Reasons and Solutions", JLabel.CENTER);
            mainHeading.setFont(new Font("Arial", Font.BOLD, 24));
            frame.add(mainHeading, BorderLayout.NORTH);

            // Create a panel for the main content
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());

            // Create a heading for the reasons section
            JLabel reasonsHeading = new JLabel("Why are disasters happening?", JLabel.CENTER);
            reasonsHeading.setFont(new Font("Arial", Font.BOLD, 30));
            contentPanel.add(reasonsHeading, BorderLayout.NORTH);

            // Create a JEditorPane for the content with HTML support
            JEditorPane contentEditorPane = new JEditorPane();
            contentEditorPane.setEditable(false);
            contentEditorPane.setContentType("text/html");
            contentEditorPane.setText("<html>"
                    + "<style>"
                    + "body {background-color: #f0f0f5; color: #333; text-align: center; font-family: Arial, sans-serif; line-height: 1.8; margin: 1; padding: 10px; font-size: 18px;}" // Increased font size
                    + "h2 { font-size: 20px; }" // Adjusted heading sizes for better visual hierarchy
                    + "h3 { font-size: 19px; }"
                    + "h4 { font-size: 17px; }"
                    + "p, ul { font-size: 15px; }"
                    + "hr {border: none; height: 1px; background-color: #ccc; margin: 20px 0;}" // Styling for section separators
                    + "</style>"
                    
                    + "<body>"
                    + "<h2>Disasters happen for many reasons but four main factors are contributing to the increase of disaster risks: climate change, rapid urbanization, poverty, and environmental degradation.</h2>"
                    
                    + "<hr>"
                    + "<h3>Climate change</h3>"
                    + "<p>Climate change will create new hazards such as glacier melting, sea level rise and extreme weather events in proportions never seen before. "
                    + "This will aggravate the existing disaster risks and vulnerabilities and expose millions of people never affected before around the world.</p>"
                    
                    + "<h4>The facts</h4>"
                    + "<ul>"
                    + "<li>In its Fourth Assessment Report, the Intergovernmental Panel on Climate Change (IPCC) predicted that by 2100:</li>"
                    + "<li>Global average surface warming will increase by between 1.1°C and 6.4°C.</li>"
                    + "<li>Sea level will rise by between 18cm and 59cm; sea-level rise, coupled with coastal storms, will increase the risks of flooding and threaten protective ecosystems.</li>"
                    + "<li>Oceans will become more acidic and warmer.</li>"
                    + "</ul>"
                    
                    + "<h4>What can be done?</h4>"
                    + "<ul>"
                    + "<li>Make disaster risk reduction a national and local priority, with strong institutions to implement decisions.</li>"
                    + "<li>Set up early warning systems that reach all people, in time for appropriate action, and accompany the warnings with helpful advice.</li>"
                    + "<li>Incorporate climate risk in all urban planning and water and forest management processes.</li>"
                    + "<li>Maintain and strengthen coastal wave barriers, river levees, flood ways and flood ponds.</li>"
                    + "</ul>"
                    
                    + "<hr>"
                    + "<h3>Rapid and unplanned urbanization</h3>"
                    + "<p>The rapid growth of cities, combined with climate change and the urban population explosion, will create new stresses for urban settlements and make city dwellers increasingly vulnerable.</p>"
                    
                    + "<h4>The facts</h4>"
                    + "<ul>"
                    + "<li>One out of every two people now lives in a city; this proportion will go on rising; by 2030, 5 billion of the planet’s expected 8.1 billion population will be urban.</li>"
                    + "<li>One in three of the urban population lives in marginal settlements or crowded slums with inadequate access to clean water, sanitation, schools, transport and other public services.</li>"
                    + "<li>One city dweller in four lives in absolute poverty; by 2030, two-thirds of humankind will live in cities and three billion in slums.</li>"
                    + "<li>Eight of the 10 most populous cities on the planet are vulnerable to earthquakes; 6 of the 10 are vulnerable to floods, storm surges and tsunamis.</li>"
                    + "</ul>"
                    
                    + "<h4>What can be done?</h4>"
                    + "<ul>"
                    + "<li>Have national and local budgets to systematically integrate disaster risk reduction in all aspects of urban planning.</li>"
                    + "<li>Plan urbanization and avoid building in risk areas.</li>"
                    + "<li>Avoid the development of slums, offering safe lands to low-income families.</li>"
                    + "<li>Have safer schools, hospitals, roads, and bridges that can withstand any type of hazard.</li>"
                    + "</ul>"
                    
                    + "<hr>"
                    + "<h3>Poverty</h3>"
                    + "<p>Poverty and socio-economic inequalities are aggravating disaster factors. They not only make poor people more vulnerable to disasters but they trap them in a vicious circle of poverty.</p>"
                    
                    + "<h4>The facts</h4>"
                    + "<ul>"
                    + "<li>Disasters hit poor people the hardest. It is not only true in developing countries but also in developed countries. Levels of vulnerabilities are highly dependent upon the economic status of individuals, communities, and nations.</li>"
                    + "<li>Fifty-three per cent of affected people by disasters live in developing countries while 1.8 per cent lives in developed countries.</li>"
                    + "<li>Over 95 per cent of the people killed by disasters lived in middle and low-income countries.</li>"
                    + "</ul>"
                    
                    + "<h4>What can be done?</h4>"
                    + "<ul>"
                    + "<li>Establish urban development programmes that reduce the creation of slums in risk areas.</li>"
                    + "<li>Provide the poor with access to lands that are safe.</li>"
                    + "<li>Involve the poorest communities in building their own capacity to resist disaster.</li>"
                    + "<li>Give the poorest people full access to early warning systems, preparedness measures and financial mechanisms.</li>"
                    + "</ul>"
                    
                    + "<hr>"
                    + "<h3>Environmental degradation</h3>"
                    + "<p>Communities can all too often increase the probability and severity of disasters by destroying the forests, coral reefs and wetlands that might have protected them.</p>"
                    
                    + "<h4>The facts</h4>"
                    + "<ul>"
                    + "<li>Forests once covered 46 per cent of the Earth’s land surface – half of these have disappeared.</li>"
                    + "<li>Coral reefs are home to one-fourth of all marine species; 60 per cent of coral reefs could be lost in the next 20-40 years.</li>"
                    + "<li>The expansion of deserts threatens nearly one-quarter of the planet’s land surface.</li>"
                    + "</ul>"
                    
                    + "<h4>What can be done?</h4>"
                    + "<ul>"
                    + "<li>Undertake land-use planning with an ecosystem approach.</li>"
                    + "<li>Recognize the risk reduction function of ecosystems in environmental policies and legislation.</li>"
                    + "<li>Identify and protect natural buffers such as forests, wetlands and coral reefs.</li>"
                    + "</ul>"
                    
                    + "</body>"
                    + "</html>");

            JScrollPane contentScrollPane = new JScrollPane(contentEditorPane);
            
            JButton backButton = new JButton("Back");
            backButton.setFont(new Font("Arial", Font.PLAIN, 18));
            backButton.addActionListener(e -> {
                frame.dispose(); // Close the current window
                WebPageLayout.main(new String[0]); // Reopen the WebPageLayout
            });

            // Add the back button at the bottom
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(backButton);
            contentPanel.add(buttonPanel, BorderLayout.SOUTH);

            contentPanel.add(contentScrollPane, BorderLayout.CENTER);
            frame.add(contentPanel, BorderLayout.CENTER);

            // Set frame visibility
            frame.setVisible(true);
        });
    }
}
