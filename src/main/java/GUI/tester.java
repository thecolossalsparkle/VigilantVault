package GUI;

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
    File[] fileObjects;
    JTextField searchField;
    JButton sortByDateBtn, sortBySizeBtn, searchBtn, back, editBtn, deleteBtn;

    tester(String Crimefieldpath) {
        this.CrimePath = Crimefieldpath;
        f1.setTitle("Criminal Files Viewer");
        f1.setSize(600, 800);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLayout(new BorderLayout());
        f1.getContentPane().setBackground(Color.LIGHT_GRAY);

        // Header panel with title
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Criminal Files Viewer", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLUE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Initialize buttons and search field in a separate panel
        initSearchAndSortButtons(headerPanel);

        // Add header panel to the top
        f1.add(headerPanel, BorderLayout.NORTH);

        // Load and display files
        showFiles();
        fileList = new JList<>(files);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(fileList);
        f1.add(scrollPane, BorderLayout.CENTER);

        f1.setVisible(true);
    }

    private void initSearchAndSortButtons(JPanel headerPanel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Search field
        searchField = new JTextField(20);
        buttonPanel.add(searchField);

        // Search button
        searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> searchFiles(searchField.getText()));
        buttonPanel.add(searchBtn);

        // Sort by date button
        sortByDateBtn = new JButton("Sort by Date");
        sortByDateBtn.addActionListener(e -> sortByDate());
        buttonPanel.add(sortByDateBtn);

        // Sort by size button
        sortBySizeBtn = new JButton("Sort by Size");
        sortBySizeBtn.addActionListener(e -> sortBySize());
        buttonPanel.add(sortBySizeBtn);

        // Edit button
        editBtn = new JButton("Edit Selected");
        editBtn.addActionListener(e -> editSelectedFile());
        buttonPanel.add(editBtn);

        // Delete button
        deleteBtn = new JButton("Delete Selected");
        deleteBtn.addActionListener(e -> deleteSelectedFile());
        buttonPanel.add(deleteBtn);

        // Back button
        back = new JButton("Back");
        back.addActionListener(e -> {
            f1.dispose();
            new home();
        });
        buttonPanel.add(back);

        // Add button panel to the header panel
        headerPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void showFiles() {
        File folder = new File(CrimePath);
        if (folder.exists() && folder.isDirectory()) {
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

    public void searchFiles(String query) {
        if (fileObjects != null && !query.isEmpty()) {
            files = Arrays.stream(fileObjects)
                    .map(File::getName)
                    .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                    .toArray(String[]::new);
            updateFileList();
        }
    }

    public void sortByDate() {
        if (fileObjects != null) {
            Arrays.sort(fileObjects, Comparator.comparingLong(File::lastModified).reversed());
            files = Arrays.stream(fileObjects).map(File::getName).toArray(String[]::new);
            updateFileList();
        }
    }

    public void sortBySize() {
        if (fileObjects != null) {
            Arrays.sort(fileObjects, Comparator.comparingLong(File::length).reversed());
            files = Arrays.stream(fileObjects).map(File::getName).toArray(String[]::new);
            updateFileList();
        }
    }

    private void updateFileList() {
        fileList.setListData(files);
    }

    private void editSelectedFile() {
        String selectedFile = fileList.getSelectedValue();
        if (selectedFile != null) {
            new EditFile(f1, selectedFile);
        } else {
            JOptionPane.showMessageDialog(f1, "Please select a file to edit.");
        }
    }

    private void deleteSelectedFile() {
        String selectedFile = fileList.getSelectedValue();
        if (selectedFile != null) {
            int confirmation = JOptionPane.showConfirmDialog(f1,
                    "Are you sure you want to delete " + selectedFile + "?",
                    "Delete Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                File fileToDelete = new File(CrimePath, selectedFile);
                if (fileToDelete.delete()) {
                    JOptionPane.showMessageDialog(f1, "File deleted: " + selectedFile);
                    showFiles(); // Refresh the file list
                    updateFileList();
                } else {
                    JOptionPane.showMessageDialog(f1, "Failed to delete file: " + selectedFile);
                }
            }
        } else {
            JOptionPane.showMessageDialog(f1, "Please select a file to delete.");
        }
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String mainpath = path + "\\models\\d";
        new tester(mainpath); // Example path
    }
}
