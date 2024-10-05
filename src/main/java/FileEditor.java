import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileEditor extends JFrame {
    private DefaultListModel<String> model;
    private JTextField searchField;

    public FileEditor() {
        setTitle("File Editor Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultListModel<>();
        loadFiles("src/main/java");

        JList<String> fileList = new JList<>(model);
        fileList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Optionally show file content in another area if needed
            }
        });

        JScrollPane scrollPane = new JScrollPane(fileList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        searchField = new JTextField(30);
        JButton searchButton = new JButton("Search");
        JButton editButton = new JButton("Edit Selected");

        searchButton.addActionListener(e -> searchFiles(searchField.getText()));
        editButton.addActionListener(e -> {
            String selectedFileName = fileList.getSelectedValue();
            if (selectedFileName != null) {
                new EditFile(this, selectedFileName); // Open the edit dialog
            } else {
                JOptionPane.showMessageDialog(this, "Please select a file to edit.");
            }
        });

        panel.add(searchField);
        panel.add(searchButton);
        panel.add(editButton);
        add(panel, BorderLayout.NORTH);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FileEditor::new);
    }
}
