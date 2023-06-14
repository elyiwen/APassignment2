package code.Patient;

import java.util.ArrayList;

import javafx.collections.ObservableArray;

public class Patient{
    
    // Biodata
    private String patientID;
    private String patientName;
    private String identityNo;
    private String doB;
    private String age;
    private String gender;
    private String race_ethnicity;
    private String prefLanguage;
    private String maritalStatus;

    // ContactInfo
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String patientContactNo;
    private String emergencyContactNo;
    private String patientEmail;

    //Patient List
    private static ArrayList<Patient> patientList = new ArrayList<>();
    private static ArrayList<Patient> patientListTmp = new ArrayList<>(); 


    public Patient(String patientID, String patientName){
        this.patientID = patientID;
        this.patientName = patientName;
        patientList.add(this);
    }

    public void calAge(){

    }

    public void displayPatient(){
        System.out.println("Patient ID: " + patientID);
        System.out.println("Patient Name: " + patientName);
    }

    public static void displayPatientList(){
        for (Patient p : patientList){
            p.displayPatient();
        }
    }

    public String getPatientID(){
        return patientID;
    }
    public String getPatientName(){
        return patientName;
    }
    public static ArrayList<Patient> getPatientList(){
        return patientList;
    }
    public static ArrayList<Patient> getPatientListTmp(){
        return patientListTmp;
    }
}
