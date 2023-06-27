package Patient;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class PatientData {

    // Biodata
    protected String patientID;
    protected String patientName;
    protected String patientIdentityNo;
    protected LocalDate doB;
    protected String age;
    protected String gender;
    protected String race_ethnicity;
    protected String prefLanguage;
    protected String maritalStatus;
    protected String status;

    // ContactInfo
    protected String address;
    protected String country;
    protected String state;
    protected String city;
    protected String zipCode;
    protected String patientEmail;
    protected String patientContactNo;
    protected String emergencyContactNo;
    protected String emergencyName;
    protected String emergencyRelationship;

    //Essential
    protected String wardNumber;
    protected String attendingPhysician;
    protected String comment;

    //Patient List
    protected static ArrayList<Patient> patientList = new ArrayList<>();

    //Patient File
    protected static File patientFile = new File("patient.json");


    //Getters
    public String getPatientID(){
        return patientID;
    }

    public String getPatientName(){
        return patientName;
    }

    public String getPatientIdentityNo(){
        return patientIdentityNo;
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

    public String getGender(){
        return gender;
    }

    public String getPrefLanguage(){
        return prefLanguage;
    }

    public String getMaritalStatus(){
        return maritalStatus;
    }

    public String getStatus(){
        return status;
    }

    public static ArrayList<Patient> getPatientList(){
        return patientList;
    }

    public String getAddress(){
        return address;
    }

    public String getCountry(){
        return country;
    }

    public String getState(){
        return state;
    }
    
    public String getCity(){
        return city;
    }

    public String getZipCode(){
        return zipCode;
    }

    public String getFullAddress(){
        return address + "\n" + city + " " + zipCode + "\n" + state + " " + country;
    }

    public String getPatientEmail(){
        return patientEmail;
    }

    public String getPatientContactNo(){
        return patientContactNo;
    }

    public String getEmergencyName(){
        return emergencyName;
    }

    public String getEmergencyRelationship(){
        return emergencyRelationship;
    }

    public String getEmergencyContactNo(){
        return emergencyContactNo;
    }

    public String getEmergencyInfo(){
        return emergencyName + "\n" + emergencyRelationship + "\n" + emergencyContactNo;
    }

    public static File getPatientFile(){
        return patientFile;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public String getAttendingPhysician() {
        return attendingPhysician;
    }

    public String getComment() {
        return comment;
    }
}
