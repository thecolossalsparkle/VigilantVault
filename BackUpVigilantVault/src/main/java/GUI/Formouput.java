//
//package GUI;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Formouput extends filereport {
//    JFrame g1 = new JFrame();
//    JButton back;
//
//    Formouput(String subject, String filename) {
//        super(subject, filename);
//
//        g1.setTitle("CriminalForm");
//        g1.setSize(400, 800);
//        titleandsubtitle();
//        nameandgender();
//        dateandtime();
//        image();
//        descriptionandaction();
//        back = new JButton("Back");
//        back.setBounds(10,20,50,30);
//        back.setFont(new Font("Arial", Font.BOLD, 14));  // Set a better font
//        back.setBackground(new Color(70, 130, 180));     // Set background color (SteelBlue)
//        back.setForeground(Color.WHITE);                 // Set text color to white
//        back.setFocusPainted(false);                     // Remove focus outline
//        back.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));  // Add a border to make it stand out
//
//        // Optionally add padding for a less cramped look
//        back.setMargin(new Insets(5, 15, 5, 15));  // Add padding (top, left, bottom, right)
//
//        // Action Listener for the Back button
//        back.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                g1.dispose();
//                new tester(subject);  // Navigate back to the previous screen
//            }
//        });
//
//        g1.add(back);
//        g1.setVisible(true);
//        g1.setLayout(null);
//    }
//
//    public void titleandsubtitle() {
//        JLabel Title = new JLabel("Title: " + subject);  // Use subject in the title
//        Title.setBounds(50, 20, 300, 30);  // Set to a wider range and closer to the top
//        g1.add(Title);
//
//        JLabel subtitle = new JLabel("Subtitle: " + filename);  // Use filename as a subtitle
//        subtitle.setBounds(50, 60, 300, 30);  // Position below the title with proper spacing
//        g1.add(subtitle);
//    }
//
//    public void dateandtime() {
//        JLabel Date = new JLabel("Date: " + date);
//        Date.setBounds(50, 100, 300, 30);  // Positioned below the subtitle
//        g1.add(Date);
//
//        JLabel Time = new JLabel("Time: " + time);
//        Time.setBounds(50, 140, 300, 30);  // Position right below the Date with spacing
//        g1.add(Time);
//    }
//
//    public void nameandgender() {
//        JLabel Name = new JLabel("Name: " + name);
//        Name.setBounds(50, 180, 300, 30);  // Positioned below date and time
//        g1.add(Name);
//
//        JLabel genders = new JLabel("Gender: " + gender);
//        genders.setBounds(50, 220, 300, 30);  // Positioned below the name
//        g1.add(genders);
//    }
//
//    public void image() {
//        // Ensure the image size fits into the form layout, using appropriate scaling if necessary
//        ImageIcon image = new ImageIcon(path);
//        JLabel photo = new JLabel(image);
//        photo.setBounds(50, 260, 200, 200);  // Place the image below gender with enough space
//        g1.add(photo);
//    }
//
//    public void descriptionandaction() {
//        JLabel descrptions = new JLabel("<html>Description: " + description + "</html>");
//        descrptions.setBounds(50, 480, 300, 60);  // Positioned below the image, multi-line if necessary
//        g1.add(descrptions);
//
//        JLabel actiontaken = new JLabel("<html>Action Taken: " + action + "</html>");
//        actiontaken.setBounds(50, 550, 300, 60);  // Positioned below the description with enough space
//        g1.add(actiontaken);
//    }
//}
//

package GUI;

import Logic.tester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formouput extends filereport {
    JFrame g1 = new JFrame();
    JButton back;

    public Formouput(String subject, String filename) {
        super(subject, filename);

        g1.setTitle("Criminal Form");
        g1.setSize(600, 800);
        g1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g1.setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); // SteelBlue
        headerPanel.setPreferredSize(new Dimension(600, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JLabel headerLabel = new JLabel("Criminal Form Report");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Footer panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(70, 130, 180)); // SteelBlue
        footerPanel.setPreferredSize(new Dimension(600, 80));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.setBackground(new Color(241, 50, 68)); // OrangeRed
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        back.setMargin(new Insets(10, 20, 10, 20)); // Add padding (top, left, bottom, right)
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g1.dispose();
                new tester(subject);
            }
        });
        footerPanel.add(back);

        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(new Dimension(600, 600));

        titleandsubtitle(contentPanel);
        nameandgender(contentPanel);
        dateandtime(contentPanel);
        image(contentPanel);
        descriptionandaction(contentPanel);

        g1.add(headerPanel, BorderLayout.NORTH);
        g1.add(footerPanel, BorderLayout.SOUTH);
        g1.add(contentPanel, BorderLayout.CENTER);

        g1.setVisible(true);
        g1.setLocationRelativeTo(null);
    }

    public void titleandsubtitle(JPanel panel) {
        JLabel Title = new JLabel( subject2);
        Title.setBounds(50, 20, 500, 30);
        Title.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(Title);

        JLabel subtitle = new JLabel("Subtitle: " + filename);
        subtitle.setBounds(50, 60, 500, 30);
        subtitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(subtitle);
    }

    public void dateandtime(JPanel panel) {
        JLabel Date = new JLabel(date);
        Date.setBounds(50, 100, 500, 30);
        Date.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(Date);

        JLabel Time = new JLabel(time);
        Time.setBounds(50, 140, 500, 30);
        Time.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(Time);
    }

    public void nameandgender(JPanel panel) {
        JLabel Name = new JLabel(name);
        Name.setBounds(50, 180, 500, 30);
        Name.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(Name);

        JLabel genders = new JLabel(gender);
        genders.setBounds(50, 220, 500, 30);
        genders.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(genders);
    }

    public void image(JPanel panel) {
        ImageIcon image = new ImageIcon(path);
        JLabel photo = new JLabel(image);
        photo.setBounds(50, 260, 200, 200);
        panel.add(photo);
    }

    public void descriptionandaction(JPanel panel) {
        JLabel descrptions = new JLabel(description);
        descrptions.setBounds(50, 480, 500, 60);
        descrptions.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(descrptions);

        JLabel actiontaken = new JLabel(action);
        actiontaken.setBounds(50, 550, 500, 60);
        actiontaken.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(actiontaken);
    }


}


