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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class home {

    String[] options = {"A", "B", "C", "D"};
    JFrame j1 = new JFrame();
    JButton CrimeRegisteration,logout;


    public home() {

        j1.setTitle("Home - Criminal Form");
        j1.setSize(600, 500); // Increased size for better layout

        JLabel Home = new JLabel("Home:");
        Home.setBounds(50, 50, 100, 30); // Adjusted position
        j1.add(Home);


        for (int i = 0; i < options.length; i++) {
            String name = options[i];
            // Use File.separator for cross-platform compatibility
            String nams = System.getProperty("user.dir");
            String finl = nams + File.separator + "models" + File.separator + name;

            System.out.println(finl); // Print the path to verify correctness

            File file = new File(finl);
            // Check if the directory exists or create it
            if (!file.exists()) {
                if (file.mkdirs()) {
                    System.out.println("Directory " + name + " created successfully.");
                } else {
                    System.out.println("Failed to create directory " + name);
                }
            }

            // Now, if the directory exists, add the button
            if (file.exists() && file.isDirectory()) {
                JButton button = new JButton(options[i]);
                button.setBounds(150, 150 + (i * 50), 200, 30); // Adjusted to align buttons vertically
                button.addActionListener(new ActionListener() {
                    @Override // Can change to lambda function
                    public void actionPerformed(ActionEvent e) {
                        openfolder("models/" + name);
                        j1.dispose();
                    }
                });
                j1.add(button);
            }
        }




    logout = new JButton("Logout");
        logout.setBounds(150, 150 , 400, 30); // Adjusted to align buttons vertically
        logout.addActionListener(new ActionListener() {
            @Override // Can change to lambda function
            public void actionPerformed(ActionEvent e) {
                j1.dispose();
                new LoginGUI();
            }
        });
        j1.add(logout);

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
        CrimeRegisteration = new JButton("Register Crime");
        CrimeRegisteration.setBounds(150, 400 , 400, 30); // Centered it better
        CrimeRegisteration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j1.dispose();
                new CriminalForm();
            }
        });
        j1.add(CrimeRegisteration);

        j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j1.setLayout(null);
        j1.setVisible(true);
    }

    public void openfolder(String name) {
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
