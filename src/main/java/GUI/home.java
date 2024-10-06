//package GUI;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//
//public class home  {
//
//    String [] options = {"A","B","C","D"};
//    JFrame j1 =new JFrame();
//    JButton CrimeRegisteration;
//    JButton searchButton;
//    JTextField searchfield;
//    public home(){
//
//        j1.setTitle("CriminalForm");
//        j1.setSize(400, 800);
//        JLabel Home = new JLabel("Home:");
//        Home.setBounds(50, 200, 100, 30);
//        j1.add(Home);
//
//        JLabel search = new JLabel("Search:");
//        search.setBounds(50, 300, 100, 30);
//        j1.add(search);
//
//        searchfield = new JTextField();
//        search.setBounds(150, 300, 100, 30);
//        j1.add(searchfield);
//
//
//
//        for (int i = 0; i < options.length; i++) {
//            String name = options[i];
//            File file=new File(name);
//            if(file.exists() && file.isDirectory()) {
//                JButton button = new JButton(options[i]);
//                button.setBounds(150, 150 + (i * 50), 200, 30);
//                button.addActionListener(new ActionListener() {
//                    @Override //can Change to lambda function
//                    public void actionPerformed(ActionEvent e) {
//                        openfolder(name);
//
//                    }
//                });
//                j1.add(button);
//            }
//
//        }
//        searchButton = new JButton("Search");
//        searchButton.setBounds(100, 300, 100, 30);
//        j1.add(searchButton);
//        searchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                FileSearchApp f1=new FileSearchApp("VigilantVault");
//                f1.searchFiles(searchfield.getText());
//
//            }
//        });
//
//        CrimeRegisteration=new JButton("");
//        CrimeRegisteration.setBounds(100, 200, 100, 30);
//        CrimeRegisteration.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                j1.dispose();
//                new CriminalForm();
//            }
//        });
//
//        j1.add(CrimeRegisteration);
//        j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        j1.setLayout(null);
//        j1.setVisible(true);
//
//    }
//
//    public void openfolder(String name){
//        File folder=new File(name);
//        if (folder.exists() && folder.isDirectory()){
//            new Displayfiles(name);
//        }
//        else {
//            System.out.println("folder doesnot exist");
//        }
//    }
//
//    public static void main(String[] args) {
//        new home();
//    }
//
//
//
//
//
//
//
//
//
//}
//
//
//
//
//
//

package GUI;

import login.LoginGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class home {

    String[] options = {"DSA", "JAVA", "HEADFIRST", "YOUTUBE VIDEOS", "48 LAWS OF POWER", "MASTERY", "LEETCODE"};
    JFrame j1 = new JFrame();
    JButton logout;

    public home() {
        j1.setTitle("Home - Criminal Form");
        j1.setSize(1200, 700);
        j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j1.setLayout(new BorderLayout()); // Use BorderLayout for the main frame
        j1.getContentPane().setBackground(Color.LIGHT_GRAY); // Set background color

        // Create and add header
        Header header = new Header();
        j1.add(header, BorderLayout.NORTH); // Add header to the top

        // Create a JPanel for the button content
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(options.length + 2, 1, 10, 10)); // Use GridLayout for consistent spacing
        buttonPanel.setBackground(Color.LIGHT_GRAY); // Same background color as the JFrame

        // Create a heading for "Register Crime"
        JLabel registerCrimeLabel = new JLabel("Register Crime", JLabel.CENTER);
        registerCrimeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set heading font
        registerCrimeLabel.setForeground(Color.BLACK); // Set heading color
        buttonPanel.add(registerCrimeLabel); // Add heading to the panel

        // Create buttons for the options
        for (String name : options) {
            JButton button = new JButton(name);
            button.setFont(new Font("Arial", Font.PLAIN, 16)); // Set button font
            button.setBackground(Color.WHITE); // Set button background color
            button.setBorder(BorderFactory.createRaisedBevelBorder()); // Add a border
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openFolder("models/" + name);
                    j1.dispose();
                }
            });
            buttonPanel.add(button); // Add button to the panel
        }

        // Logout button
        logout = new JButton("Logout");
        logout.setFont(new Font("Arial", Font.PLAIN, 14)); // Set button font
        logout.setBackground(Color.WHITE); // Set button background color
        logout.setBorder(BorderFactory.createRaisedBevelBorder()); // Add a border
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j1.dispose();
                new LoginGUI();
            }
        });
        buttonPanel.add(logout); // Add to panel

        // Add the button panel to the center of the frame
        j1.add(buttonPanel, BorderLayout.CENTER);

        // Create and add footer
        Footer footer = new Footer();
        j1.add(footer, BorderLayout.SOUTH); // Add footer to the bottom

        j1.setVisible(true);
        j1.setLocationRelativeTo(null);
    }

    public void openFolder(String name) {
        File folder = new File(name);
        if (folder.exists() && folder.isDirectory()) {
            new tester(name);
        } else {
            System.out.println("Folder does not exist");
        }
    }

    public static void main(String[] args) {
        new home();
    }
}





        // Search button
//        searchButton = new JButton("Search");
//        searchButton.setBounds(470, 100, 80, 30); // Placed next to search field for better alignment
//        j1.add(searchButton);
//        searchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                tester3 f1 = new tester3("models");
//                f1.searchFiles(searchfield.getText());
//            }
//        });

//        sortbutton = new JButton("Search");
//        sortbutton.setBounds(470, 200, 80, 30); // Placed next to search field for better alignment
//        j1.add(sortbutton);
//        sortbutton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                FileSort f1 = new FileSort("models");
//
//            }
//        });

        // Crime Registration button
//        CrimeRegisteration = new JButton("Register Crime");
//        CrimeRegisteration.setBounds(150, 400 , 400, 30); // Centered it better
//        CrimeRegisteration.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                j1.dispose();
//                new CriminalForm();
//            }
//        });
//        j1.add(CrimeRegisteration);
//
//        j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        j1.setLayout(null);
//        j1.setVisible(true);
//    }
//
//    public void openfolder(String name) {
//        File folder = new File(name);
//        if (folder.exists() && folder.isDirectory()) {
//            new tester(name);
//        } else {
//            System.out.println("Folder does not exist");
//        }
//    }

//    public static void main(String[] args) {
//
//        new home();
//        Header header = new Header();
//        Footer footer = new Footer();
//
//    }
//}
