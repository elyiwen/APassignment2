package code.Patient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Patient implements Serializable{
    
    // Demographic
    private String patientID;
    private String patientName;
    private String patientIdentityNo;
    private LocalDate doB;
    private String age;
    private String gender;
    private String race_ethnicity;
    private String prefLanguage;
    private String maritalStatus;

    // ContactInfo
    private String address;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String patientEmail;
    private String patientContactNo;
    private String emergencyContactNo;
    private String emergencyName;
    private String emergencyRelationship;

    //Patient List
    private static File patientFile = new File("patient.txt");
    private static ArrayList<Patient> patientList = new ArrayList<>();

    public Patient(String patientName, String patientIdentityNo, LocalDate doB, String race_ethnicity) throws IOException{
        this.patientName = patientName;
        this.patientIdentityNo = patientIdentityNo;
        this.doB = doB;
        this.race_ethnicity = race_ethnicity;
        this.patientID = generateID();
        this.age = calAge(doB);
        patientList.add(this);
    }

    public String generateID(){
        Random rand = new Random();
        String random = Integer.toString(rand.nextInt(1000)+1000);
        String idFirst = "P";
        char[] patientNameChar = patientName.toCharArray();
        String idMid = patientNameChar[0] + "-" + patientNameChar[1] + random;
        String idLast = patientIdentityNo.substring(patientIdentityNo.length() - 4);
        String patientID = idFirst + idMid.toUpperCase() + idLast;
        return patientID;
    }

    public String calAge(LocalDate doB){
        int yearDoB = doB.getYear();
        int yearNow = Year.now().getValue();
        int age = yearNow - yearDoB;
        String ageS = Integer.toString(age);
        return ageS;
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
            Alert alertNotFound = new Alert(AlertType.CONFIRMATION, "No Patient Recorded");
            alertNotFound.setHeaderText("NOTIFICATION");
            alertNotFound.setTitle("ALERT");
            alertNotFound.showAndWait();
        }
    }

    public String getPatientID(){
        return patientID;
    }

    public String getPatientName(){
        return patientName;
    }

    public String getPatientIdentityNo(){
        return patientIdentityNo;
    }

    public static File getPatientFile(){
        return patientFile;
    }

    public LocalDate getPatientDoB(){
        return doB;
    }

    public String getPatientAge(){
        return age;
    }

    public String getRace_Ethinicity(){
        return race_ethnicity;
    }

    public static ArrayList<Patient> getPatientList(){
        return patientList;
    }
}
