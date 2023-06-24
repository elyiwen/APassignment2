package Patient;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

public class Patient{
    
    // Biodata
    private String patientID;
    private String patientName;
    private String patientIdentityNo;
    private LocalDate doB;
    private String age;
    private String gender;
    private String race_ethnicity;
    private String prefLanguage;
    private String maritalStatus;
    private String status;

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
    private static ArrayList<Patient> patientList = new ArrayList<>();

    //Patient File
    private static File patientFile = new File("patient.json");

    public void setPatientBiodata(String patientName, String patientIdentityNo, LocalDate doB, String race_ethnicity, String gender, String prefLanguage, String maritalStatus, String status) throws IOException{
        this.patientName = patientName;
        this.patientIdentityNo = patientIdentityNo;
        this.doB = doB;
        this.race_ethnicity = race_ethnicity;
        this.gender = gender;
        this.prefLanguage = prefLanguage;
        this.maritalStatus = maritalStatus;
        this.status = status;
        this.patientID = generateID();
        this.age = calAge(doB);
    }

    public void setPatientContactInfo(String address, String country, String state, String city, String zipCode, String patientEmail, String patientContactNo, String emergencyContactNo, String emergencyName, String emergencyRelationship){
        this.address = address;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.patientEmail = patientEmail;
        this.patientContactNo = patientContactNo;
        this.emergencyContactNo = emergencyContactNo;
        this.emergencyName = emergencyName;
        this.emergencyRelationship = emergencyRelationship;
    }

    public String generateID(){
        String idFirst = "P";
        char[] patientNameChar = getPatientName().toCharArray();
        String idMid = patientNameChar[0] + "-" + patientNameChar[1];
        String idLast = getPatientIdentityNo().substring(getPatientIdentityNo().length() - 4);
        String patientID = idFirst + idMid.toUpperCase() + idLast;
        return patientID;
    };

    public String calAge(LocalDate doB){
        int yearDoB = doB.getYear();
        int yearNow = Year.now().getValue();
        int age = yearNow - yearDoB;
        String ageS = Integer.toString(age);
        return ageS;
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
}
