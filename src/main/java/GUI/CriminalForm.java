
package GUI;

import Logic.RecordsLogicc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CriminalForm {
    JFrame d1 = new JFrame();
    JButton clear, submit, back, uploadimage;
    JTextField Name, Description, ActionsTaken;
    JComboBox<String> combo;
    String[] options = {"DSA", "JAVA", "HEADFIRST", "YOUTUBE VIDEOS","48 LAWS OF POWER", "MASTERY", "LEETCODE"};
    public String gender, path;
    int count = 0;

    public CriminalForm() {
        d1.setTitle("Criminal Form");
        d1.setSize(600, 500);
        d1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d1.setLayout(null);

        labels();
        setGender();
        textFields();

        uploadimage = new JButton("Upload Image");
        uploadimage.setBounds(200, 300, 150, 30);
        d1.add(uploadimage);

        uploadimage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));
                int result = fileChooser.showOpenDialog(d1);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    path = selectedFile.getAbsolutePath();
                    System.out.println(path);
                }
            }
        });

        submit = new JButton("Submit");
        submit.setBounds(200, 350, 150, 30);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            new RecordsLogicc(Name.getText(), gender, path, Description.getText(), (String) combo.getSelectedItem(), ActionsTaken.getText(), count);
                count++;
                d1.dispose();
                new home();
            }
        });
        d1.add(submit);

        clear = new JButton("Clear");
        clear.setBounds(80, 350, 100, 30); // Adjusted position
        d1.add(clear);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Name.setText("");
                Description.setText("");
                ActionsTaken.setText("");
            }
        });

        back = new JButton("Back");
        back.setBounds(370, 350, 100, 30);
      back.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     d1.dispose();
                                     new home();
                                 }
                             }
      );
        d1.add(back);

        d1.setVisible(true);
    }

    public void labels() {
        JLabel name = new JLabel("Name:");
        name.setBounds(50, 50, 100, 30); // Adjusted position
        d1.add(name);

        JLabel subject = new JLabel("Field:");
        subject.setBounds(50, 100, 100, 30); // Adjusted position
        d1.add(subject);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 150, 100, 30); // Adjusted position
        d1.add(genderLabel);

        JLabel description = new JLabel("Description:");
        description.setBounds(50, 200, 100, 30); // Adjusted position
        d1.add(description);

        JLabel actionstaken = new JLabel("Statement:");
        actionstaken.setBounds(50, 250, 100, 30); // Adjusted position
        d1.add(actionstaken);
    }

    public void setGender() {
        JRadioButton male = new JRadioButton("Male");
        male.setBounds(150, 150, 100, 30); // Adjusted position
        JRadioButton female = new JRadioButton("Female");
        female.setBounds(260, 150, 100, 30); // Adjusted position
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);
        d1.add(male);
        d1.add(female);

        male.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gender = male.getText();
                System.out.println(gender);
            }
        });

        female.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gender = female.getText();
            }
        });
    }

    public void textFields() {
        Name = new JTextField();
        Name.setBounds(150, 50, 300, 30); // Adjusted width for better input
        d1.add(Name);

        combo = new JComboBox<>(options);
        combo.setBounds(150, 100, 300, 30); // Adjusted width for better dropdown
        d1.add(combo);

        Description = new JTextField();
        Description.setBounds(150, 200, 300, 30); // Adjusted width
        d1.add(Description);

        ActionsTaken = new JTextField();
        ActionsTaken.setBounds(150, 250, 300, 30); // Adjusted width
        d1.add(ActionsTaken);
    }

    public static void main(String[] args) {
        new CriminalForm();
    }
}
