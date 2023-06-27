package Patient;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;

public class Patient extends PatientData implements Serializable {

    private static final long serialVersionUID = -4216126862664342211L;

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

    public void setPatientEssential(String wardNumber, String attendingPhysician, String comment){
        this.wardNumber = wardNumber;
        this.attendingPhysician = attendingPhysician;
        this.comment = comment;
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
}
