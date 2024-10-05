
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formouput extends filereport {
    JFrame g1 = new JFrame();
    JButton back;

    Formouput(String subject, String filename) {
        super(subject, filename);

        g1.setTitle("CriminalForm");
        g1.setSize(400, 800);
        titleandsubtitle();
        nameandgender();
        dateandtime();
        image();
        descriptionandaction();
        back = new JButton("Back");
        back.setBounds(10,20,50,30);
        back.setFont(new Font("Arial", Font.BOLD, 14));  // Set a better font
        back.setBackground(new Color(70, 130, 180));     // Set background color (SteelBlue)
        back.setForeground(Color.WHITE);                 // Set text color to white
        back.setFocusPainted(false);                     // Remove focus outline
        back.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));  // Add a border to make it stand out

        // Optionally add padding for a less cramped look
        back.setMargin(new Insets(5, 15, 5, 15));  // Add padding (top, left, bottom, right)

        // Action Listener for the Back button
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g1.dispose();
                new tester(subject);  // Navigate back to the previous screen
            }
        });

        g1.add(back);
        g1.setVisible(true);
        g1.setLayout(null);
    }

    public void titleandsubtitle() {
        JLabel Title = new JLabel("Title: " + subject);  // Use subject in the title
        Title.setBounds(50, 20, 300, 30);  // Set to a wider range and closer to the top
        g1.add(Title);

        JLabel subtitle = new JLabel("Subtitle: " + filename);  // Use filename as a subtitle
        subtitle.setBounds(50, 60, 300, 30);  // Position below the title with proper spacing
        g1.add(subtitle);
    }

    public void dateandtime() {
        JLabel Date = new JLabel("Date: " + date);
        Date.setBounds(50, 100, 300, 30);  // Positioned below the subtitle
        g1.add(Date);

        JLabel Time = new JLabel("Time: " + time);
        Time.setBounds(50, 140, 300, 30);  // Position right below the Date with spacing
        g1.add(Time);
    }

    public void nameandgender() {
        JLabel Name = new JLabel("Name: " + name);
        Name.setBounds(50, 180, 300, 30);  // Positioned below date and time
        g1.add(Name);

        JLabel genders = new JLabel("Gender: " + gender);
        genders.setBounds(50, 220, 300, 30);  // Positioned below the name
        g1.add(genders);
    }

    public void image() {
        // Ensure the image size fits into the form layout, using appropriate scaling if necessary
        ImageIcon image = new ImageIcon(path);
        JLabel photo = new JLabel(image);
        photo.setBounds(50, 260, 200, 200);  // Place the image below gender with enough space
        g1.add(photo);
    }

    public void descriptionandaction() {
        JLabel descrptions = new JLabel("<html>Description: " + description + "</html>");
        descrptions.setBounds(50, 480, 300, 60);  // Positioned below the image, multi-line if necessary
        g1.add(descrptions);

        JLabel actiontaken = new JLabel("<html>Action Taken: " + action + "</html>");
        actiontaken.setBounds(50, 550, 300, 60);  // Positioned below the description with enough space
        g1.add(actiontaken);
    }
}

