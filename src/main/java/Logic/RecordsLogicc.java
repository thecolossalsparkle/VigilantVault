package Logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RecordsLogicc {

    public  String name;
    public String subject;
    public  String description;
    public  String action;
    public int count;
    public String date;
    public String time;
    public String gender;
    public String path;




    public RecordsLogicc(String name, String gender, String path, String description, String subject, String action, int count){
        this.name=name;
        this.description=description;
        this.subject=subject;
        this.action=action;
        this.count=count;
        this.gender=gender;
        this.path=path;
        checkfolder(subject);
    }

    public void checkfolder(String foldername){

        File folder = new File(foldername);

        if(folder.exists()){
            file();

        }
        else {
            folder.mkdir();
            file();
        }
    }

    public void file(){



        try{

            File f1 = new File("models/"+subject,subject+count+".txt");
            System.out.println("models/"+subject+"/"+subject+count+".txt");
            SetDateAndTime();

            FileWriter writer= new FileWriter(f1);

            writer.write( name+"\n"+gender+"\n"+path+"\n"+date+"\n"+time+"\n"+subject+"\n"+description+"\n"+action+"\n");
            System.out.println(gender + path);
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void SetDateAndTime(){

        LocalDateTime dates = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date=formatter.format(dates);

        LocalTime Time= LocalTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("HH:mm:ss");
        time=format.format(Time);




    }







}
