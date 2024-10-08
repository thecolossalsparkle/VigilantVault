//package GUI;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.File;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.Date;
//
//public class tester {
//
//    Frame f1 = new Frame();
//    String CrimePath;
//    JList<String> fileList;
//    String[] files;
//    File[] fileObjects; // Store File objects for sorting
//    JTextField searchField;
//    JButton sortByDateBtn, sortBySizeBtn, searchBtn,back;
//
//    tester(String Crimefieldpath) {
//        this.CrimePath = Crimefieldpath;
//        f1.setTitle("Criminal Files Viewer");
//        f1.setSize(600, 800);
//
//        // Label for the current folder
//        JLabel Home = new JLabel(Crimefieldpath );
//        Home.setBounds(50, 20, 300, 30);
//        f1.add(Home);
//
//        // Initialize buttons and search field
//        initSearchAndSortButtons();
//
//        // Load and display files
//        showFiles();
//        fileList = new JList<>(files);
//        fileList.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {  // Double-click
//                    String selectedFile = fileList.getSelectedValue();
//                    new Formouput(Crimefieldpath, selectedFile);
//                    f1.dispose();
//                    System.out.println("Double-clicked on: " + selectedFile);
//                }
//            }
//        });
//
//        JScrollPane scrollPane = new JScrollPane(fileList);
//        f1.setLayout(new BorderLayout());
//        f1.add(scrollPane, BorderLayout.CENTER);
//
//        f1.setVisible(true);
//    }
//
//    private void initSearchAndSortButtons() {
//        // Search field
//        searchField = new JTextField(20);
//        searchField.setBounds(50, 60, 200, 30);
//        f1.add(searchField);
//
//        // Search button
//        searchBtn = new JButton("Search");
//        searchBtn.setBounds(270, 60, 100, 30);
//        f1.add(searchBtn);
//        searchBtn.addActionListener(e -> searchFiles(searchField.getText()));
//
//        // Sort by date button
//        sortByDateBtn = new JButton("Sort by Date");
//        sortByDateBtn.setBounds(50, 100, 150, 30);
//        f1.add(sortByDateBtn);
//        sortByDateBtn.addActionListener(e -> sortByDate());
//
//        // Sort by size button
//        sortBySizeBtn = new JButton("Sort by Size");
//        sortBySizeBtn.setBounds(220, 100, 150, 30);
//        f1.add(sortBySizeBtn);
//        sortBySizeBtn.addActionListener(e -> sortBySize());
//
//        back = new JButton("back");
//        back.setBounds(220, 100, 500, 30);
//
//        back.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                f1.dispose();
//                new home();
//
//            }
//        });
//        f1.add(back);
//    }
//
//    public void showFiles() {
//        File folder = new File(CrimePath);
//        if (folder.exists() && folder.isDirectory()) {
//            // Store file objects for sorting
//            fileObjects = folder.listFiles();
//            if (fileObjects != null) {
//                files = Arrays.stream(fileObjects).map(File::getName).toArray(String[]::new);
//            } else {
//                files = new String[]{"No files found"};
//            }
//        } else {
//            files = new String[]{"No folder found"};
//        }
//    }
//
//    // Search for files matching the query
//    public void searchFiles(String query) {
//        if (fileObjects != null && !query.isEmpty()) {
//            files = Arrays.stream(fileObjects)
//                    .map(File::getName)
//                    .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
//                    .toArray(String[]::new);
//            updateFileList();
//        }
//    }
//
//    // Sort files by date (last modified)
//    public void sortByDate() {
//        if (fileObjects != null) {
//            Arrays.sort(fileObjects, Comparator.comparingLong(File::lastModified).reversed());
//            files = Arrays.stream(fileObjects).map(File::getName).toArray(String[]::new);
//            updateFileList();
//        }
//    }
//
//    // Sort files by size
//    public void sortBySize() {
//        if (fileObjects != null) {
//            Arrays.sort(fileObjects, Comparator.comparingLong(File::length).reversed());
//            files = Arrays.stream(fileObjects).map(File::getName).toArray(String[]::new);
//            updateFileList();
//        }
//    }
//
//    // Update the JList with the sorted or searched results
//    private void updateFileList() {
//        fileList.setListData(files);
//    }
//
//    public static void main(String[] args) {
//        String path = System.getProperty("user.dir");
//        String mainpath=path+"\\models\\d";
//        new tester(mainpath); // Example path
//    }
//}

package Logic;

import GUI.Formouput;
import GUI.home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class tester {

    JFrame f1 = new JFrame();
    String CrimePath;
    JList<String> fileList;
    String[] files;
    File[] fileObjects; // Store File objects for sorting
    JTextField searchField;
    JButton sortByDateBtn, sortBySizeBtn, searchBtn, back;

    public tester(String Crimefieldpath) {
        this.CrimePath = Crimefieldpath;
        f1.setTitle("Criminal Files Viewer");
        f1.setSize(600, 800);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLayout(new BorderLayout()); // Change to BorderLayout for header and footer

        // Add header and footer
        addHeader();
        addFooter();

        // Panel for search and sorting buttons (use GridBagLayout for flexibility)
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(208, 208, 202)); // Cream white background

        // Label for the current folder
        JLabel homeLabel = new JLabel("Folder: " + Crimefieldpath);
        homeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        homeLabel.setForeground(new Color(47, 79, 79)); // Dark Slate Gray
        addComponent(contentPanel, homeLabel, 0, 0, 2, 1, GridBagConstraints.WEST, new Insets(10, 10, 10, 10));

        // Initialize search field and buttons
        initSearchAndSortButtons(contentPanel);

        // Load and display files
        showFiles();
        fileList = new JList<>(files);
        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {  // Double-click
                    String selectedFile = fileList.getSelectedValue();
                    new Formouput(Crimefieldpath, selectedFile);
                    f1.dispose();
                    System.out.println("Double-clicked on: " + selectedFile);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(fileList);
        addComponent(contentPanel, scrollPane, 0, 4, 2, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));

        f1.add(contentPanel, BorderLayout.CENTER); // Add the content panel in the center

        f1.setLocationRelativeTo(null);
        f1.setVisible(true);
    }

    private void initSearchAndSortButtons(JPanel panel) {
        // Search field
        searchField = new JTextField(20);
        addComponent(panel, searchField, 0, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10));

        // Search button
        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchBtn.setBackground(new Color(70, 130, 180)); // Steel Blue color
        searchBtn.setForeground(Color.WHITE);
        searchBtn.addActionListener(e -> searchFiles(searchField.getText()));
        addComponent(panel, searchBtn, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10));

        // Sort by date button
        sortByDateBtn = new JButton("Sort by Date");
        sortByDateBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        sortByDateBtn.setBackground(new Color(70, 130, 180)); // Steel Blue color
        sortByDateBtn.setForeground(Color.WHITE);
        sortByDateBtn.addActionListener(e -> sortByDate());
        addComponent(panel, sortByDateBtn, 0, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10));

        // Sort by size button
        sortBySizeBtn = new JButton("Sort by Size");
        sortBySizeBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        sortBySizeBtn.setBackground(new Color(70, 130, 180)); // Steel Blue color
        sortBySizeBtn.setForeground(Color.WHITE);
        sortBySizeBtn.addActionListener(e -> sortBySize());
        addComponent(panel, sortBySizeBtn, 1, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10));

        // Back button
        back = new JButton("Back");
        back.setFont(new Font("SansSerif", Font.PLAIN, 14));
        back.setBackground(new Color(178, 34, 34)); // Firebrick color
        back.setForeground(Color.WHITE);
        back.addActionListener(e -> {
            f1.dispose();
            new home();
        });
        addComponent(panel, back, 0, 3, 2, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10));
    }

    // Helper method to add components to a panel with GridBagLayout
    private void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth, int gridheight, int fill, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.fill = fill;
        gbc.insets = insets;
        container.add(component, gbc);
    }

    public void showFiles() {
        File folder = new File(CrimePath);
        if (folder.exists() && folder.isDirectory()) {
            // Store file objects for sorting
            fileObjects = folder.listFiles();
            if (fileObjects != null) {
                files = Arrays.stream(fileObjects).map(File::getName).toArray(String[]::new);
            } else {
                files = new String[]{"No files found"};
            }
        } else {
            files = new String[]{"No folder found"};
        }
    }

    // Search for files matching the query
    public void searchFiles(String query) {
        if (fileObjects != null && !query.isEmpty()) {
            files = Arrays.stream(fileObjects)
                    .map(File::getName)
                    .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                    .toArray(String[]::new);
            updateFileList();
        }
    }

    // Sort files by date (last modified)
    public void sortByDate() {
        if (fileObjects != null) {
            Arrays.sort(fileObjects, Comparator.comparingLong(File::lastModified).reversed());
            files = Arrays.stream(fileObjects).map(File::getName).toArray(String[]::new);
            updateFileList();
        }
    }

    // Sort files by size
    public void sortBySize() {
        if (fileObjects != null) {
            Arrays.sort(fileObjects, Comparator.comparingLong(File::length).reversed());
            files = Arrays.stream(fileObjects).map(File::getName).toArray(String[]::new);
            updateFileList();
        }
    }

    // Update the JList with the sorted or searched results
    private void updateFileList() {
        fileList.setListData(files);
    }

    private void addHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        headerPanel.setPreferredSize(new Dimension(f1.getWidth(), 50));

        JLabel logoLabel = new JLabel(new ImageIcon("path_to_your_logo.png")); // Update this with the actual path
        headerPanel.add(logoLabel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("MENTORSHIP CRIME RECORD MANAGEMENT SYSTEM", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        f1.add(headerPanel, BorderLayout.NORTH);
    }

    private void addFooter() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        footerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        footerPanel.setPreferredSize(new Dimension(f1.getWidth(), 50));

        JLabel copyrightLabel = new JLabel("© 2024 Mentorship Crime Management System. All rights reserved.");
        copyrightLabel.setForeground(Color.WHITE);
        footerPanel.add(copyrightLabel);

        // Add social media icons (use placeholders for now)
        JLabel facebookIcon = new JLabel(new ImageIcon("path_to_facebook_icon.png")); // Update with actual path
        footerPanel.add(facebookIcon);

        JLabel twitterIcon = new JLabel(new ImageIcon("path_to_twitter_icon.png")); // Update with actual path
        footerPanel.add(twitterIcon);

        f1.add(footerPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String mainpath = path + "\\models\\d";
        new tester(mainpath); // Example path
    }
}

