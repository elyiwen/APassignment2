package code;

import java.util.ArrayList;

public class Pharmacist extends Clinician{

    private String qualification;
    private String expertise;

    private ArrayList<Clinician> clinicianList = Clinician.getClinicianList();
    
    public Pharmacist(String clinicianID, String clinicianName, String clinicianContactNo, String clinicianRNIC, String clinicianPassword, String qualification, String expertise) {
        super(clinicianID, clinicianName, clinicianContactNo, clinicianRNIC, clinicianPassword);
        this.qualification = qualification;
        this.expertise = expertise;
        clinicianList.add(this);
        setAccountType("Pharmacist");
    }

    //Setter
    public void setQualification(String qualification){
        this.qualification = qualification;
    }
    public void setExpertise(String expertise){
        this.expertise = expertise;
    }

    //Getter
    public String getQualification(){
        return qualification;
    }
    public String getExpertise(){
        return expertise;
    }

    @Override
    public String toString(){
        return "Pharmacist Name: " + getClinicianName() + "\n" + "Pharmacist ID: " + getClinicianID() + "\n" + "Pharmacist Expertise: " + expertise;
    }
}
