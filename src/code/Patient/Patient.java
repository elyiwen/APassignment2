package code.Patient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Patient implements Serializable{
    
    // Demographic
    private String patientID;
    private String patientName;
    //private String identityNo;
    // private String doB;
    // private String age;
    // private String gender;
    // private String race_ethnicity;
    // private String prefLanguage;
    // private String maritalStatus;

    // ContactInfo
    // private String address;
    // private String country;
    // private String state;
    // private String city;
    // private String zipCode;
    // private String patientContactNo;
    // private String emergencyContactNo;
    // private String patientEmail;

    //Patient List
    private static File patientFile = new File("patient.txt");
    private static ArrayList<Patient> patientList = new ArrayList<>();

    public Patient(String patientID, String patientName) throws IOException{
        this.patientID = patientID;
        this.patientName = patientName;
        patientList.add(this);
    }

    public static void writeRecord() throws IOException{
        patientFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(patientFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Patient p : patientList){
            oos.writeObject(p);
        }
        oos.close();
    }
    
    public static void readRecord() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(patientFile);
        if (fis.available() == 0){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("ALERT");
            alert.setHeaderText("NOTIFICATION");
            alert.setContentText("No Patient Record Found! Please Add New Patient");
        }
        else{
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0){
                Patient p = (Patient)ois.readObject();
                patientList.add(p);
            }
            ois.close();
        }
    }

    public static void deletePatient(Patient deletePatient) throws IOException{
        boolean check = false;
        for (Patient p : patientList){
            if (p.patientID.equals(deletePatient.patientID)){
                patientList.remove(p);
                Patient.writeRecord();
                check = true;
                break;
            }
        }
        if (check == false){
            System.out.println("Patient Not Found");
        }
    }

    public String getPatientID(){
        return patientID;
    }

    public String getPatientName(){
        return patientName;
    }

    public static File getPatientFile(){
        return patientFile;
    }

    public static ArrayList<Patient> getPatientList(){
        return patientList;
    }
}
