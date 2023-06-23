package Patient;


public class Outpatient extends Patient{

    public Outpatient(){

    }

    @Override
    public String generateID() {
        String idFirst = "OP";
        char[] patientNameChar = getPatientName().toCharArray();
        String idMid = patientNameChar[0] + "-" + patientNameChar[1];
        String idLast = getPatientIdentityNo().substring(getPatientIdentityNo().length() - 4);
        String patientID = idFirst + idMid.toUpperCase() + idLast;
        return patientID;
    } 
}
