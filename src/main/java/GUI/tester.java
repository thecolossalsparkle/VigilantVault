package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class tester {

    Frame f1 = new Frame();
    String CrimePath;
    JList<String> fileList;
    String[] files;
    File[] fileObjects; // Store File objects for sorting
    JTextField searchField;
    JButton sortByDateBtn, sortBySizeBtn, searchBtn,back;

    tester(String Crimefieldpath) {
        this.CrimePath = Crimefieldpath;
        f1.setTitle("Criminal Files Viewer");
        f1.setSize(600, 800);

        // Label for the current folder
        JLabel Home = new JLabel(Crimefieldpath );
        Home.setBounds(50, 20, 300, 30);
        f1.add(Home);

        // Initialize buttons and search field
        initSearchAndSortButtons();

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
        f1.setLayout(new BorderLayout());
        f1.add(scrollPane, BorderLayout.CENTER);

        f1.setVisible(true);
    }

    private void initSearchAndSortButtons() {
        // Search field
        searchField = new JTextField(20);
        searchField.setBounds(50, 60, 200, 30);
        f1.add(searchField);

        // Search button
        searchBtn = new JButton("Search");
        searchBtn.setBounds(270, 60, 100, 30);
        f1.add(searchBtn);
        searchBtn.addActionListener(e -> searchFiles(searchField.getText()));

        // Sort by date button
        sortByDateBtn = new JButton("Sort by Date");
        sortByDateBtn.setBounds(50, 100, 150, 30);
        f1.add(sortByDateBtn);
        sortByDateBtn.addActionListener(e -> sortByDate());

        // Sort by size button
        sortBySizeBtn = new JButton("Sort by Size");
        sortBySizeBtn.setBounds(220, 100, 150, 30);
        f1.add(sortBySizeBtn);
        sortBySizeBtn.addActionListener(e -> sortBySize());

        back = new JButton("back");
        back.setBounds(220, 100, 500, 30);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.dispose();
                new home();

            }
        });
        f1.add(back);
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

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String mainpath=path+"\\models\\d";
        new tester(mainpath); // Example path
    }
}
