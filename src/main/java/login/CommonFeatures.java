package login;

import javax.swing.*;
import java.awt.*;

public class CommonFeatures {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;

    public void setBgColour(JFrame frame, Color color) {
        frame.getContentPane().setBackground(color);
    }
    public static void setFont(JComponent component, String fontName, int style, int size) {
        Font font = new Font(fontName,style,size);
        component.setFont(font);
    }
    public static void setTextColor(JComponent component, Color color) {
        component.setForeground(color);
    }
}
