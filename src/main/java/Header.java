import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    public Header() {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);  // Set background color of the header

        // Create and add an icon label
        JLabel iconLabel = new JLabel(new ImageIcon("C:\\Users\\Jeff Jacob John\\Downloads\\crime-scene.png"));  // Replace "icon.png" with your icon file path
        iconLabel.setPreferredSize(new Dimension(50, 10));
        add(iconLabel, BorderLayout.WEST);

        // Create and add a title label
        JLabel titleLabel = new JLabel("CRIME MANAGEMENT RECORD SYSTEM", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);  // Set text color to white for better contrast
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));  // Set font size and style
        add(titleLabel, BorderLayout.CENTER);
    }
}

