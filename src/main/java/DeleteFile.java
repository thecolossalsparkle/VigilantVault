import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DeleteFile extends JFrame {
    private DefaultListModel<String> model;
    private JTextField searchField;
    private JTextArea fileContentArea;

    public DeleteFile() {
        setTitle("File Search Application");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultListModel<>();
        loadFiles("src/main/java");

        JList<String> fileList = new JList<>(model);
        fileList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                readFileContent(fileList.getSelectedValue());
            }
        });

        JScrollPane scrollPane = new JScrollPane(fileList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        searchField = new JTextField(30);
        JButton searchButton = new JButton("Search");
        JButton deleteButton = new JButton("Delete Selected");

        searchButton.addActionListener(e -> searchFiles(searchField.getText()));
        deleteButton.addActionListener(e -> deleteSelectedFile(fileList));

        panel.add(searchField);
        panel.add(searchButton);
        panel.add(deleteButton);
        add(panel, BorderLayout.NORTH);

        // JTextArea for displaying file content
        fileContentArea = new JTextArea();
        fileContentArea.setEditable(false);
        JScrollPane contentScrollPane = new JScrollPane(fileContentArea);
        add(contentScrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadFiles(String relativePath) {
        String currentDir = System.getProperty("user.dir");
        File directory = new File(currentDir, relativePath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                model.addElement(file.getName());
            }
        } else {
            model.addElement("The directory is empty or invalid.");
        }
    }

    private void searchFiles(String query) {
        model.clear();
        String currentDir = System.getProperty("user.dir");
        File directory = new File(currentDir, "src/main/java");

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().toLowerCase().contains(query.toLowerCase())) {
                    model.addElement(file.getName());
                }
            }
            if (model.isEmpty()) {
                model.addElement("No files found matching: " + query);
            }
        } else {
            model.addElement("The directory is empty or invalid.");
        }
    }

    private void deleteSelectedFile(JList<String> fileList) {
        String selectedFileName = fileList.getSelectedValue();
        if (selectedFileName != null) {
            String currentDir = System.getProperty("user.dir");
            File fileToDelete = new File(currentDir, "src/main/java/" + selectedFileName);

            System.out.println("Attempting to delete file: " + fileToDelete.getAbsolutePath());

            if (fileToDelete.exists()) {
                if (fileToDelete.delete()) {
                    model.removeElement(selectedFileName);
                    JOptionPane.showMessageDialog(this, "File deleted: " + selectedFileName);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete file: " + selectedFileName);
                }
            } else {
                JOptionPane.showMessageDialog(this, "File does not exist: " + selectedFileName);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a file to delete.");
        }
    }

    private void readFileContent(String fileName) {
        if (fileName != null) {
            String currentDir = System.getProperty("user.dir");
            File fileToRead = new File(currentDir, "src/main/java/" + fileName);

            try {
                StringBuilder content = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                }
                fileContentArea.setText(content.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeleteFile::new);
    }
}
