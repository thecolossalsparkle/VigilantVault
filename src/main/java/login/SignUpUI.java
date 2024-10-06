package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JButton backButton;

    public SignUpUI() {
        frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);

        // Use GridBagLayout for better arrangement
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components

        // Username label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        frame.add(usernameField, gbc);

        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        frame.add(passwordField, gbc);

        // Sign Up button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        signUpButton = new JButton("Sign Up");
        frame.add(signUpButton, gbc);

        // Back button
        gbc.gridy = 3;
        backButton = new JButton("\u2190");
        frame.add(backButton, gbc);

        // Action Listeners
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (PasswordHash.register(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Registration successful");
                    frame.dispose();
                    LoginGUI loginGUI = new LoginGUI();
                    loginGUI.show();
                } else {
                    JOptionPane.showMessageDialog(frame, "Registration failed. Username may already exist.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.show();
            }
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }
}
