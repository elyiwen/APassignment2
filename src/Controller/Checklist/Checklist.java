package Controller.Checklist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.control.CheckBox;

public class Checklist implements Serializable{
    
    private String task;
    private String urgency;
    private String date;
    private transient CheckBox checkBox;

    public static ArrayList<Checklist> checklistList = new ArrayList<>();
    private static File patientChecklistFile = new File("patientChecklist.txt");


    public Checklist(String task, String urgency, String date, String value) {
        this.task = task;
        this.urgency = urgency;
        this.date = date;
        this.checkBox = new CheckBox();
    }

    public String getTask() {
        return task;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getDate() {
        return date;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public static ArrayList<Checklist> getChecklistList() {
        return checklistList;
    }

    public static void setChecklistList(ArrayList<Checklist> checklistList) {
        Checklist.checklistList = checklistList;
    }

    public void addChecklist(Checklist checklist) {
        checklistList.add(checklist);
    }

    public static void deleteChecklist(Checklist checklist) {
        checklistList.remove(checklist);
    }

    public static void writeToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(patientChecklistFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Checklist checklist : checklistList) {
                oos.writeObject(checklist);
        }
        oos.close();
    }

    public void readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(patientChecklistFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (fis.available() > 0) {
            Checklist checklist = (Checklist) ois.readObject();
            checklistList.add(checklist);
        }
        ois.close();
    }

    @Override
    public String toString() {
        return "Checklist{" + "task=" + task + ", urgency=" + urgency + ", date=" + date + ", checkBox=" + checkBox + '}';
    }

}
