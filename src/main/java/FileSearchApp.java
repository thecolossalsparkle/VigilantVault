import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileSearchApp extends JFrame {
    private DefaultListModel<String> model;
    private JTextField searchField;

    public FileSearchApp() {
        setTitle("File Search Application");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Model for JList
        model = new DefaultListModel<>();
        loadFiles("/Users/thecolossalsparkle/Desktop"); // Update this path

        // Set up JList
        JList<String> fileList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(fileList);
        add(scrollPane, BorderLayout.CENTER);

        // Search field and button
        JPanel panel = new JPanel();
        searchField = new JTextField(30);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(e -> searchFiles(searchField.getText()));

        panel.add(searchField);
        panel.add(searchButton);
        add(panel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void loadFiles(String directoryPath) {
        File directory = new File(directoryPath);
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
        File directory = new File("/Users/thecolossalsparkle/Desktop"); // Update this path

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
        SwingUtilities.invokeLater(FileSearchApp::new);
    }
}
