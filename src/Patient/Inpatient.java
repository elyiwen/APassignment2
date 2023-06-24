package Patient;

public class Inpatient extends Patient{
    
    private String wardNo;

    public Inpatient(String wardNo){
        this.wardNo = wardNo;
    }

    public String generateID() {
        String idFirst = "IP";
        char[] patientNameChar = getPatientName().toCharArray();
        String idMid = patientNameChar[0] + "-" + patientNameChar[1];
        String idLast = getPatientIdentityNo().substring(getPatientIdentityNo().length() - 4);
        String patientID = idFirst + idMid.toUpperCase() + idLast;
        return patientID;
    }

    //Setter
    public void setWardNo(String wardNo){
        this.wardNo = wardNo;
    }

    //Getter
    public String getWardNo(){
        return wardNo;
    }
}
