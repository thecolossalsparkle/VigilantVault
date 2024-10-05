import javax.swing.*;
import java.awt.*;
import java.io.*;

public class EditFile extends JDialog {
    private JTextArea editTextArea;
    private File fileToEdit;

    public EditFile(Frame owner, String fileName) {
        super(owner, "Edit File - " + fileName, true);

        String currentDir = System.getProperty("user.dir");
        this.fileToEdit = new File(currentDir, "src/main/java/" + fileName);

        // Set up the text area
        editTextArea = new JTextArea(20, 60);
        editTextArea.setEditable(true);
        loadFileContent();

        JScrollPane scrollPane = new JScrollPane(editTextArea);
        JButton saveButton = new JButton("Save Changes");

        saveButton.addActionListener(e -> saveChanges());

        add(scrollPane, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void loadFileContent() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToEdit))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            editTextArea.setText(content.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
        }
    }

    private void saveChanges() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToEdit))) {
            writer.write(editTextArea.getText());
            JOptionPane.showMessageDialog(this, "File saved: " + fileToEdit.getName());
            dispose(); // Close the dialog after saving
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage());
        }
    }
}
