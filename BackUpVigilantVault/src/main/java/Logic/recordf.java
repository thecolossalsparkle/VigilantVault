package Logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class recordf {

    public String name;
    public String subject;
    public String description;
    public String action;
    public int count;
    public String date;
    public String time;
    public String gender;
    public String path;

    public recordf (String name, String gender, String path, String description, String subject, String action, int count) {
        this.name = name;
        this.gender = gender;
        this.path = path;
        this.description = description;
        this.subject = subject;
        this.action = action;
        this.count = count;
        checkFolder(subject);
    }

    public void checkFolder(String folderName) {
        String basePath = System.getProperty("user.dir") + "\\models\\" + folderName;

        File modelsFolder = new File(System.getProperty("user.dir") + "\\models");
        if (!modelsFolder.exists()) {
            modelsFolder.mkdir(); // Create the "models" folder if it doesn't exist
        }

        File folder = new File(basePath);
        if (!folder.exists()) {
            folder.mkdir(); // Create the subject folder if it doesn't exist
        }

        createFile(basePath);
    }

    public void createFile(String folderPath) {
        try {
            setDateAndTime();

            // Clean the time string to remove invalid characters for file names (like colons)
            String cleanTime = time.replace(":", "-");

            // Construct the full file path
            File file = new File(folderPath, name + "" + cleanTime + "" + count + ".txt");
            System.out.println("File path: " + file.getAbsolutePath());

            // Write data to the file
            FileWriter writer = new FileWriter(file);
            writer.write("Name: " + name + "\n");
            writer.write("Gender: " + gender + "\n");
            writer.write( path + "\n");
            writer.write("Date: " + date + "\n");
            writer.write("Time: " + time + "\n");
            writer.write("Subject: " + subject + "\n");
            writer.write("Description: " + description + "\n");
            writer.write("Action Taken: " + action + "\n");
            writer.close();

            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDateAndTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = currentDateTime.format(dateFormatter);

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        time = currentTime.format(timeFormatter);
    }
}