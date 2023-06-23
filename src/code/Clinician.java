package code;

import java.util.ArrayList;

public class Clinician {
    
    private String clinicianID;
    private String clinicianName;  
    private String clinicianContactNo;
    private String clinicianRNIC;
    private String accountType;

    private String clinicianPassword;

    private static ArrayList<Clinician> clinicianList = new ArrayList<>();

    public Clinician(String clinicianID, String clinicianName, String clinicianContactNo, String clinicianRNIC, String clinicianPassword){
        this.clinicianID = clinicianID;
        this.clinicianName = clinicianName;
        this.clinicianContactNo = clinicianContactNo;
        this.clinicianRNIC = clinicianRNIC;
        this.clinicianPassword = clinicianPassword;
    }

    public static Clinician login(String user, String userID, String password){
        for (Clinician c : Clinician.getClinicianList()){
            if (c.getAccountType().equals(user) && (c.getClinicianID().equals(userID)) && c.getClinicianPassword().equals(password)){
                return c;
            }
        }
        return null;
    }
    
    public static Boolean loginAuthentication(String user, String userID, String password){
        for (Clinician c : Clinician.getClinicianList()){
            if (c.getAccountType().equals(user) && (c.getClinicianID().equals(userID)) && c.getClinicianPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public Clinician getClinician(String userID){
        for (Clinician c : clinicianList){
            if (c.getClinicianID().equals(userID)){
                return c;
            }
        }
        return null;
    }

    public void addPatient(){

    }

    public void deletePatient(){

    }

    public void editPatient(){

    }

    public void viewPatient(){
        
    }

    //Setter
    public void setClinicianID(String clinicianID){
        this.clinicianID = clinicianID;
    }
    public void setClinicianName(String clinicianName){
        this.clinicianName = clinicianName;
    }
    public void setClinicianContactNo(String clinicianContactNo){
        this.clinicianContactNo = clinicianContactNo;
    }
    public void setClinicianRNIC(String clinicianRNIC){
        this.clinicianRNIC = clinicianRNIC;
    }
    public void setClinicianPassword(String clinicianPassword){
        this.clinicianPassword = clinicianPassword;
    }
    public void setAccountType(String accountType){
        this.accountType = accountType;
    }

    //Getter
    public String getClinicianID(){
        return clinicianID;
    }
    public String getClinicianName(){
        return clinicianName;
    }
    public String getClinicianContactNo(){
        return clinicianContactNo;
    }
    public String getClinicianRNIC(){
        return clinicianRNIC;
    }
    public String getClinicianPassword(){
        return clinicianPassword;
    }
    public String getAccountType(){
        return accountType;
    }
    public static ArrayList<Clinician> getClinicianList(){
        return clinicianList;
    }
}
