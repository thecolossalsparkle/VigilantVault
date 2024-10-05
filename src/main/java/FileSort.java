import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileSort extends JFrame {
    private JList<String> fileList;
    private DefaultListModel<String> model;
    private JTextField searchField;
    private List<File> files; // List to hold file objects for sorting

    public FileSort() {
        setTitle("File Sorter");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load files from directory
        files = loadFiles("/Users/thecolossalsparkle/Desktop"); // Update this path

        // Model for JList
        model = new DefaultListModel<>();
        updateFileList();

        // Set up JList
        fileList = new JList<>(model);
        add(new JScrollPane(fileList), BorderLayout.CENTER); // JList in the center

        // Button panel for sorting at the top
        JPanel buttonPanel = new JPanel();
        JButton sortByNameButton = new JButton("Sort by Name");
        JButton sortBySizeButton = new JButton("Sort by Size");
        JButton sortByDateButton = new JButton("Sort by Last Modified");

        sortByNameButton.addActionListener(e -> sortFilesByName());
        sortBySizeButton.addActionListener(e -> sortFilesBySize());
        sortByDateButton.addActionListener(e -> sortFilesByDate());

        buttonPanel.add(sortByNameButton);
        buttonPanel.add(sortBySizeButton);
        buttonPanel.add(sortByDateButton);
        add(buttonPanel, BorderLayout.NORTH); // Sort buttons at the top

        // Search field below the sorting buttons
        searchField = new JTextField();
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterFiles(searchField.getText());
            }
        });
        add(searchField, BorderLayout.SOUTH); // Search field at the bottom

        setVisible(true);
    }

    private List<File> loadFiles(String directoryPath) {
        File directory = new File(directoryPath);
        File[] filesArray = directory.listFiles();
        List<File> fileList = new ArrayList<>();
        if (filesArray != null) {
            Collections.addAll(fileList, filesArray);
        }
        return fileList;
    }

    private void updateFileList() {
        model.clear();
        for (File file : files) {
            model.addElement(file.getName());
        }
    }

    private void filterFiles(String query) {
        model.clear();
        for (File file : files) {
            if (file.getName().toLowerCase().contains(query.toLowerCase())) {
                model.addElement(file.getName());
            }
        }
    }

    private void sortFilesByName() {
        Collections.sort(files, Comparator.comparing(File::getName));
        updateFileList();
    }

    private void sortFilesBySize() {
        Collections.sort(files, Comparator.comparingLong(File::length));
        updateFileList();
    }

    private void sortFilesByDate() {
        Collections.sort(files, Comparator.comparingLong(File::lastModified));
        updateFileList();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FileSort::new);
    }
}
