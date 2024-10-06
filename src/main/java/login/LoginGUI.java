package login;

import GUI.home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends CommonFeatures {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;

    public LoginGUI() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        // Use GridBagLayout for better arrangement
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components

        JLabel title = new JLabel("CRIME RECORDS MANAGEMENT SYSTEM");
        CommonFeatures.setTextColor(title, Color.red);
        CommonFeatures.setFont(title, "Arial", Font.PLAIN, 14);

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        frame.add(title, gbc);

        // Username label and field
        gbc.gridwidth = 1; // Reset to single column
        gbc.gridy = 1;
        frame.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(20);
        frame.add(usernameField, gbc);

        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        frame.add(passwordField, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        loginButton = new JButton("Login");
        frame.add(loginButton, gbc);

        // Sign Up button
        gbc.gridy = 4;
        signUpButton = new JButton("Sign Up");
        frame.add(signUpButton, gbc);

        // Set background color
        setBgColour(frame, Color.blue);

        // Action Listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (PasswordHash.checkPassword(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful");
                    frame.dispose();
                    new home();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SignUpUI signUpUI = new SignUpUI();
                signUpUI.show();
            }
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }
}
