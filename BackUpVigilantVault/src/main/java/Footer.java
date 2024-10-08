import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Footer extends JPanel {
    public Footer() {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);  // Set background color of the footer

        // Create a label for the copyright message
        JLabel copyrightLabel = new JLabel("© 2024 Vigilant Vault");
        copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        copyrightLabel.setForeground(Color.WHITE);  // Set text color to white for better contrast
        copyrightLabel.setPreferredSize(new Dimension(getWidth(), 30));
        add(copyrightLabel, BorderLayout.CENTER);

        // Create a panel for the social media icons
        JPanel socialMediaPanel = new JPanel();
        socialMediaPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        socialMediaPanel.setOpaque(false);

        // Add social media buttons
        socialMediaPanel.add(createSocialMediaButton("facebook.png", "https://www.facebook.com"));
        socialMediaPanel.add(createSocialMediaButton("twitter.png", "https://www.twitter.com"));
        socialMediaPanel.add(createSocialMediaButton("instagram.png", "https://www.instagram.com"));

        add(socialMediaPanel, BorderLayout.EAST);
    }

    private JButton createSocialMediaButton(String iconPath, String url) {
        JButton button = new JButton(new ImageIcon(iconPath));
        button.setPreferredSize(new Dimension(30, 30));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new java.net.URI(url));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return button;
    }
}
