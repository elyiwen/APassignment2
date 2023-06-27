package User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Controller.PatientFormController;
import Patient.Patient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Clinician {
    
    private String clinicianID;
    private String clinicianName;  
    private String clinicianContactNo;
    private String clinicianRNIC;
    private String accountType;

    private String clinicianPassword;

    private static ArrayList<Clinician> clinicianList = new ArrayList<>();

    private static ArrayList<Patient> patientList = Patient.getPatientList();
    
    private static File patientFile = Patient.getPatientFile();

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

    public void addPatient(Patient newPatient){
        patientList.add(newPatient);
    }

    public void deletePatient(Patient selectedPatient) throws IOException{
        for (Patient p : patientList){
            if (p.equals(selectedPatient)){
                patientList.remove(p);
                break;
            }
        }
    }

    public void editPatient(Patient selectedPatient) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene/PatientForm.fxml"));
        Parent root = loader.load();

        PatientFormController pfc = loader.getController();
        pfc.setEditedPatient(selectedPatient);
        
        pfc.setBiodata(selectedPatient.getPatientName(), selectedPatient.getPatientIdentityNo(), selectedPatient.getPrefLanguage(), selectedPatient.getGender(), 
                        selectedPatient.getPatientDoB(), selectedPatient.getRace_Ethinicity(), selectedPatient.getMaritalStatus(), selectedPatient.getStatus());
        
        pfc.setContactInfo(selectedPatient.getAddress(), selectedPatient.getCity(), selectedPatient.getState(), selectedPatient.getZipCode(),
                            selectedPatient.getCountry(), selectedPatient.getPatientEmail(), selectedPatient.getPatientContactNo(), 
                            selectedPatient.getEmergencyName(), selectedPatient.getEmergencyRelationship(), selectedPatient.getEmergencyContactNo());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void writeRecord() throws IOException{
        FileOutputStream fos = new FileOutputStream(patientFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Patient p : patientList){
            oos.writeObject(p);
        }
        oos.close();
    }

    public void readRecord() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(patientFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while(fis.available() > 0){
            Patient p = (Patient)ois.readObject();
            patientList.add(p);
        }
        ois.close();
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
