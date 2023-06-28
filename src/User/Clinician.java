package User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Controller.PatientFormController;
import Patient.Candidate;
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

    private static JSONArray candidateArray = Candidate.getCandidateArray();
    
    private static File patientFile = Patient.getPatientFile();

    private static File candidateFile = Candidate.getCandidateFile();

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

    public void addCandidate(Candidate candidate){
        JSONObject objCandidateData = new JSONObject();
        JSONObject objCandidate = new JSONObject();

        objCandidateData.put("Patient ID", candidate.getPatientID());
        objCandidateData.put("Patient Name", candidate.getPatientName());
        objCandidateData.put("Date of Birth", candidate.getPatientDoB().toString());
        objCandidateData.put("Race_Ethinicty", candidate.getRace_Ethinicity());
        objCandidateData.put("Gender", candidate.getGender());
        objCandidateData.put("Preferred Language", candidate.getPrefLanguage());
        objCandidateData.put("Marital Status", candidate.getMaritalStatus());
        objCandidateData.put("Identity No", candidate.getPatientIdentityNo());
        objCandidateData.put("Status", candidate.getStatus());
        objCandidateData.put("Status Update", candidate.getStatusUpdate());
        objCandidate.put("Candidate", objCandidateData);
        candidateArray.add(objCandidate);
    }

    public void addCandidateList(Candidate candidate){
        Candidate.getCandidateList().add(candidate);
    }

    public void deletePatient(Patient selectedPatient) throws IOException{
        for (Patient p : patientList){
            if (p.equals(selectedPatient)){
                patientList.remove(p);
                break;
            }
        }
    }

    public void deleteCandidate(Candidate candidate){
        int len = candidateArray.size();
        for (int i = 0; i < len; i++){
            JSONObject selectedCandidate = (JSONObject)candidateArray.get(i);
            JSONObject c = (JSONObject)selectedCandidate.get("Candidate");
            if (c.get("Patient Name").equals(candidate.getPatientName())){
                candidateArray.remove((selectedCandidate));
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

    public void writeCandidateRecord() throws IOException{
        FileWriter fw = new FileWriter(candidateFile);
        fw.write(candidateArray.toJSONString());
        
        fw.flush();
        fw.close();
    }

    public void readRecord() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(patientFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (fis.available() > 0){
            Patient p = (Patient)ois.readObject();
            patientList.add(p);
        }
        ois.close();
    }

    public void readCandidateRecord() throws IOException, ClassNotFoundException, ParseException{
        FileReader fr = new FileReader(candidateFile);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fr);
        candidateArray = (JSONArray)obj;
        for (Object candidate : candidateArray){
            parseObjCandidate((JSONObject) candidate);
        }
    }

    public static void parseObjCandidate(JSONObject candidate) throws IOException{
        JSONObject objPatient = (JSONObject)candidate.get("Candidate");
        String identityNo = (String)objPatient.get("Identity No");
        String name = (String)objPatient.get("Patient Name");
        LocalDate doB = LocalDate.parse((String)objPatient.get("Date of Birth"));
        String statusUpdate = (String)objPatient.get("Status Update");
        String race_ethinicty = (String)objPatient.get("Race_Ethinicity");
        String gender = (String)objPatient.get("Gender");
        String prefLanguage = (String)objPatient.get("Preferred Language");
        String maritalStatus = (String)objPatient.get("Marital Status");
        String status = (String)objPatient.get("Status");
        Candidate c = new Candidate();
        c.setCandidateBiodata(name, identityNo, doB, race_ethinicty, gender, prefLanguage, maritalStatus, status, statusUpdate);;
        Candidate.getCandidateList().add(c);
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
