package com.dr.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import com.dr.app.WebPageLayout;

public class DataAndStatistics {

    public static void main(String[] args) {
        // Set the look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            // Create the frame
            JFrame frame = new JFrame("Disaster Statistics");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
            frame.setLayout(new BorderLayout(0, 0)); // Remove space between components

            // Create the heading
            JLabel heading = new JLabel("Disaster Statistics", JLabel.CENTER);
            heading.setFont(new Font("Arial", Font.BOLD, 24));
            frame.add(heading, BorderLayout.NORTH);

            // Create back button
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
            frame.add(backButton, BorderLayout.SOUTH);

            // Create a text pane for additional information with HTML formatting
            JTextPane textArea = new JTextPane();
            textArea.setContentType("text/html");

            // Set the HTML content with image paths
            textArea.setText("<html>"
                + "<head><style>"
                + "body { margin: 0; padding: 0; overflow-x: hidden; }" // Prevent horizontal overflow
                + "img { display: block; width: 80%; height: auto; max-width: 300px; margin: 10px auto; }" // Center images and stack vertically
                + "</style></head>"
                + "<body style='font-size: 18px;'>"
                + "<p><b>1. More than 226 million people are affected by disasters every year.</b> <br> "
                + "In 2010 alone, 373 disasters resulted in the deaths of 226,000 and affected 207,000 persons. "
                + "Over the decade 2000-2010, 400 disasters accounted for 98,000 deaths and 226,000 million affected each year. "
                + "In total, 1,077,683 people lost their lives while 2.4 billion were affected by disasters during the decade.</p>"
                + "<p><b>2. Earthquakes and droughts are the biggest killers.</b> <br> "
                + "More than 680,000 people died in earthquakes between 2000 and 2010 due mainly to poorly built buildings. "
                + "Collapsing buildings and fires following an earthquake are often the main causes of death. "
                + "The highest risk levels occur in middle-income countries that have not adequately planned or regulated urban growth. "
                + "Earthquakes are the deadliest disasters in all continents, but droughts remain the highest disaster killer in Africa. "
                + "Since 1980, drought and associated famine have claimed nearly 558,000 lives and affected more than 1.6 billion people.</p>"
                + "<p><b>3. Floods and storms are hazards that affect most people.</b> <br> "
                + "Disasters resulting from such natural hazards as tropical cyclones, windstorms, floods and related landslides affect the most people. "
                + "Such weather-related disasters represent about 81 percent of all events, 72 percent of all economic losses and 23 percent of fatalities for the period 2000-2010. "
                + "On average, about 37 million people are affected every year by cyclones, hurricanes and typhoons, nearly 366,000 by landslides and 102 million by floods.</p>"
                + "<p><b>4. Asia is most at risk.</b> <br> "
                + "Asia continues to be the most affected continent, with more than 62.5 percent of deaths caused by disasters and 89.7 percent of the affected people. "
                + "Africa, Asia and the Americas together account for 87 percent of the total deaths associated with disasters during the period 2000-2010. "
                + "Europe and North America are less affected in terms of death and injury but more in terms of economic impacts. "
                + "The 66 disasters reported in Europe in 2007 accounted for 28 percent of the world’s economic losses from natural hazards but only five percent of people killed globally.</p>"
                + "<p><b>5. Poor people are the most vulnerable.</b> <br> "
                + "Poor people are more affected by disasters than any other economic group. This is true both in developing and developed countries. "
                + "All countries are vulnerable to natural hazards, but most of the 3.3 million deaths from disasters in the last 40 years have been in poorer nations. "
                + "Poor people are also the ones who suffer the greatest long-term consequences of disasters as they have no insurance and no means to recover quickly; "
                + "they often lose their homes, jobs and livelihoods, which makes them more vulnerable to the next disaster.</p>"
                + "<p><b>6. Women, children and disabled are among the most vulnerable.</b> <br> "
                + "Women and children are 14 times more likely to die than men during a disaster, according to IUCN. "
                + "In industrialized countries, more women than men died during the 2003 European heatwave; many more African-American women were affected by Hurricane Katrina in 2005 than men. "
                + "In many countries, women have subordinate positions, restricted mobility, less educational opportunity, less voice in decision-making and poorer employment, all of which increases vulnerability. "
                + "During Hurricane Mitch in 1998, a disproportionate number of street children in Central America were affected. "
                + "Save the Children reports that more than 50 percent of all those affected by disasters worldwide are children.</p>"
                + "<p><b>7. Economic damage from disasters is on the rise.</b> <br> "
                + "From 2000 to 2010, economic damage as a result of disasters came to about US$ 1 trillion; in 2010 alone, the total estimated damage was US$109 billion. "
                + "Damage in the past two decades is significantly greater than in earlier decades. This could reflect greater exposure, or better reporting, or both. "
                + "Rich countries (United States, Europe, and increasingly Asia) incur greater absolute damage as the value of their infrastructure is higher. "
                + "The damage is least in Africa, where the poor possess little. The 2005 Indian Ocean tsunami cost US$10 billion whereas Hurricane Katrina cost more than US$130 billion in the United States. "
                + "The average cost of a disaster in a highly developed nation is US$636 million, a medium-developed nation US$209 million and low-income nation US$79 million.</p>"
                + "<p><b>8. Less than 0.7 percent of the total relief aid goes to disaster risk reduction.</b> <br> "
                + "Only 0.1 percent of international humanitarian aid went to prevention in 2001 and 0.7 percent in 2008, according to the World Bank. "
                + "At the Second Session of the Global Platform on Disaster Risk Reduction in 2009, participants agreed to target the equivalent of 10 percent of humanitarian relief funds for disaster risk reduction work. "
                + "Similarly, a 10 percent figure has been proposed as a target share of funding for post-disaster reconstruction and recovery projects as well as national preparedness and response plans. "
                + "Calls were made for at least one percent of all national development funding and all development assistance funding to be allocated to risk reduction measures.</p>"
                + "<p><b>Disaster Through a Different Lens:</b></p>"
                + "<img src='file:///C:\\Users\\Rudraksh\\OneDrive\\Desktop\\DR\\src\\main\\java\\Resources\\images (16).jpg'/> <br>"
                + "<img src='file:///C:\\Users\\Rudraksh\\OneDrive\\Desktop\\DR\\src\\main\\java\\Resources\\images (17).jpg'/> <br> "
                + "<img src='file:///C:\\Users\\Rudraksh\\OneDrive\\Desktop\\DR\\src\\main\\java\\Resources\\images (18).jpg'/> <br> "
                + "<img src='file:///C:\\Users\\Rudraksh\\OneDrive\\Desktop\\DR\\src\\main\\java\\Resources\\images (19).jpg'/> <br> "
                + "</body></html>");

            // Make the text area non-editable and set its properties
            textArea.setEditable(false);
            textArea.setCaretPosition(0); // Scroll to top

            JScrollPane scrollPane = new JScrollPane(textArea);
            
            // Add the scroll pane to the frame
            frame.add(scrollPane, BorderLayout.CENTER);

            // Set frame visibility
            frame.setVisible(true);
        });
    }
}
