package GUI;

import java.io.*;

public class filereport {
    String subject;
    String filename;

    String name;
    String gender;
    String path;
    String date;
    String subject2;
    String time;
    String description;

    String action;

    filereport(String subject,String filename){
        this.subject=subject;
        this.filename=filename;
        getNames();



    }
    public void getNames() {
        String filePath = subject +"/"+ filename;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            this.name = br.readLine();
            this.gender=br.readLine();
            this.path=br.readLine();
            this.date = br.readLine();
            this.time = br.readLine();
            this.subject2=br.readLine();
            this.description=br.readLine();
            this.action=br.readLine();





        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }






}
