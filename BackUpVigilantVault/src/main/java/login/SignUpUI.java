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
        frame.setSize(320, 320);
        frame.setLayout(new FlowLayout());

        usernameField = new JTextField(30);
        passwordField = new JPasswordField(30);
        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        signUpButton.setBackground(new Color(70, 130, 180)); // Steel Blue color
        signUpButton.setForeground(Color.WHITE);
        backButton = new JButton("\u2190");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backButton.setBackground(new Color(241, 50, 68)); // Steel Blue color
        backButton.setForeground(Color.WHITE);

        frame.add(new JLabel("Username:"));
        frame.add(usernameField);
        frame.add(new JLabel("Password:"));
        frame.add(passwordField);
        frame.add(signUpButton);
        frame.add(backButton);


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

