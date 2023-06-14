package code.User;

public class Pharmacist extends Clinician {
    
    private String Qualification;
    private boolean isPharmacist;

    public Pharmacist(String clinicianID, String firstName, String lastName, String password, String contactInfo, double salary, String Qualification) {
        super(clinicianID, firstName, lastName, password, contactInfo, salary);
        this.Qualification = Qualification;
        this.isPharmacist = true;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String Qualification) {
        if (Qualification.length() > 0) {
            this.Qualification = Qualification;
        }
    }

    public boolean isIsPharmacist() {
        return isPharmacist;
    }

    public void setIsPharmacist(boolean isPharmacist) {
        this.isPharmacist = isPharmacist;
    }

    @Override
    public String toString() {
        super.toString();
        return "Pharmacist{" + "Qualification=" + Qualification + ", isPharmacist=" + isPharmacist + '}';
    }
}
