package Patient;

public class Candidate extends Patient{
    
    private String newStatus;

    public Candidate(String newStatus){
        this.newStatus = newStatus;
    }

    public String generateID() {
        String idFirst = "C";
        char[] patientNameChar = getPatientName().toCharArray();
        String idMid = patientNameChar[0] + "-" + patientNameChar[1];
        String idLast = getPatientIdentityNo().substring(getPatientIdentityNo().length() - 4);
        String patientID = idFirst + idMid.toUpperCase() + idLast;
        return patientID;
    }

    //Setter
    public void setNewStatus(String status){
        this.newStatus = status;
    }

    //Getter
    public String getNewStatus(){
        return newStatus;
    }
}
