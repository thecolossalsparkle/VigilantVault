

package GUI;

import Logic.tester;
import login.LoginGUI;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class home {
    private JFrame frame;
    Header header = new Header();

    String[] options = {"DSA", "Youtube Videos", "HeadFirstJava", "48 Laws Of Power"};
    JButton CrimeRegisteration, logout;

    public home() {
        frame = new JFrame("Home - Criminal Form");
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(208, 208, 202));

        // Add header and footer
        addHeader();
        addFooter();

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel homeLabel = new JLabel("Home:");
        homeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(homeLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        for (int i = 0; i < options.length; i++) {
            String name = options[i];
            String path = System.getProperty("user.dir") + File.separator + "models" + File.separator + name;

            File file = new File(path);
            if (!file.exists() && file.mkdirs()) {
                System.out.println("Directory " + name + " created successfully.");
            }

            if (file.exists() && file.isDirectory()) {
                JButton button = new JButton(options[i]);
                gbc.gridx = 0;
                gbc.gridy++;
                mainPanel.add(button, gbc);
                button.addActionListener(e -> {
                    openfolder("models/" + name);
                    frame.dispose();
                });
            }
        }

        logout = new JButton("Logout");
        logout.setFont(new Font("SansSerif", Font.PLAIN, 14));
        logout.setBackground(new Color(241, 50, 68)); // Steel Blue color
        logout.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(logout, gbc);
        logout.addActionListener(e -> {
            frame.dispose();
            new LoginGUI();
        });

        CrimeRegisteration = new JButton("Register Crime");
        CrimeRegisteration.setFont(new Font("SansSerif", Font.PLAIN, 14));
        CrimeRegisteration.setBackground(new Color(6, 134, 234)); // Steel Blue color
        CrimeRegisteration.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(CrimeRegisteration, gbc);
        CrimeRegisteration.addActionListener(e -> {
            frame.dispose();
            new CriminalForm();
        });

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    public void openfolder(String name) {
        File folder = new File(name);
        if (folder.exists() && folder.isDirectory()) {
            new tester(name);
        } else {
            System.out.println("Folder does not exist");
        }
    }

    private void addHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));

        JLabel logoLabel = new JLabel(new ImageIcon("path_to_your_logo.png")); // Update this with the actual path
        headerPanel.add(logoLabel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("MENTORSHIP CRIME RECORDS MANAGEMENT SYSTEM", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    private void addFooter() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        footerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        footerPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));

        JLabel copyrightLabel = new JLabel("© 2024 Mentorship Crime Management System. All rights reserved.");
        copyrightLabel.setForeground(Color.WHITE);
        footerPanel.add(copyrightLabel);

        // Add social media icons (use placeholders for now)
        JLabel facebookIcon = new JLabel(new ImageIcon("path_to_facebook_icon.png")); // Update with actual path
        footerPanel.add(facebookIcon);

        JLabel twitterIcon = new JLabel(new ImageIcon("path_to_twitter_icon.png")); // Update with actual path
        footerPanel.add(twitterIcon);

        frame.add(footerPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new home();
    }
}
