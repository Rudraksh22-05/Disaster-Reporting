package com.dr.app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    private Image backgroundImage;

    public MainFrame() {
        setTitle("Main Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Create a panel that will draw the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                } else {
                    // If no background, paint a solid color so we can see the panel
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, this.getWidth(), this.getHeight());
                }
            }
        };
        backgroundPanel.setOpaque(true);
        
        setContentPane(backgroundPanel);
        setBackgroundImage();
        
        // Add components (login form, etc.) here
    }

    private void setBackgroundImage() {
        try {
            // Try multiple paths to find the image
            String[] possiblePaths = {
                "/Resources/background.jpg",
                "Resources/background.jpg",
                "src/Resources/background.jpg",
                "src/main/resources/background.jpg"
            };
            
            for (String path : possiblePaths) {
                try {
                    // First try resource stream
                    java.io.InputStream imgStream = getClass().getResourceAsStream(path);
                    if (imgStream != null) {
                        backgroundImage = ImageIO.read(imgStream);
                        System.out.println("Successfully loaded image from: " + path);
                        break;
                    }
                    
                    // Then try direct file
                    File file = new File(path);
                    if (file.exists()) {
                        backgroundImage = ImageIO.read(file);
                        System.out.println("Successfully loaded image from file: " + file.getAbsolutePath());
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Failed to load from " + path + ": " + e.getMessage());
                }
            }
            
            if (backgroundImage == null) {
                System.err.println("Could not find background image in any of these locations:");
                for (String path : possiblePaths) {
                    System.err.println("  - " + path);
                }
                return;
            }
            
            // Scale the image to fit the frame size
            backgroundImage = backgroundImage.getScaledInstance(
                getWidth(), getHeight(), Image.SCALE_SMOOTH);
                
            // Force a repaint to show the background
            repaint();
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
} 