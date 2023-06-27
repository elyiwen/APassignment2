package Patient;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.json.simple.JSONArray;

public class Candidate extends Patient{

    private String statusUpdate;

    // Candidate File
    private static File candidateFile = new File("File/candidate.json");

    //Candidate Array
    private static JSONArray candidateArray = new JSONArray();

    public void setCandidateBiodata(String patientName, String patientIdentityNo, LocalDate doB, String race_ethnicity, String gender, String prefLanguage, String maritalStatus, String status) throws IOException{
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
    
    //Getters
    public static File getCandidateFile(){
        return candidateFile;
    }

    public static JSONArray getCandidateArray(){
        return candidateArray;
    }
}
