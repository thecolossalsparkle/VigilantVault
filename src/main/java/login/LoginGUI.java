package login;

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

    LoginGUI() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 320);
        frame.setLayout(new FlowLayout());
        JLabel title = new JLabel("CRIME RECORDS MANAGEMENT SYSTEM");
        CommonFeatures.setTextColor(title, Color.red);
        CommonFeatures.setFont(title, "Arial",Font.PLAIN,14);
        usernameField = new JTextField(30);
        passwordField = new JPasswordField(30);
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");
        frame.add(title);
        frame.add(new JLabel());
        frame.add(new JLabel("Username:"));
        frame.add(usernameField);
        frame.add(new JLabel("Password:"));
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(signUpButton);
        //frame.setBackground(Color.green);
        //frame.getContentPane().setBackground(Color.YELLOW);
        setBgColour(frame, Color.blue);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (PasswordHash.checkPassword(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful");
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
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void show() {
        frame.setVisible(true);
    }

}



