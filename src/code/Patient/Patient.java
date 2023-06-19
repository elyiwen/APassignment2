package code.Patient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

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
    private static File patientFile = new File("patient.json");
    private static ArrayList<Patient> patientList = new ArrayList<>();
    private static ArrayList<Patient> patientListRead = new ArrayList<>();

    public Patient(String patientID, String patientName) throws IOException{
        this.patientID = patientID;
        this.patientName = patientName;
        patientList.add(this);
        writeRecord(this);
    }

    public void calAge(){

    }

    public static void writeRecord() throws IOException{
        FileOutputStream fos = new FileOutputStream(patientFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Patient p : patientList){
            oos.writeObject(p);
        }
        oos.close();
    }

    public static void writeRecord(Patient patient) throws IOException{
        FileOutputStream fos = new FileOutputStream(patientFile, true);
        if(patientFile.length() == 0){
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(patient);
            oos.close();
        }
        else{
            AppendingObjectOutputStream aoos = new AppendingObjectOutputStream(fos);
            aoos.writeObject(patient);
            aoos.close();
        }
    }
    
    public static void readRecord() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(patientFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while(fis.available() > 0){
            Patient patientRecorded = (Patient)ois.readObject();
            patientListRead.add(patientRecorded);
        }
        ois.close();
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

    public static ArrayList<Patient> getPatientListRead(){
        return patientListRead;
    }
}
