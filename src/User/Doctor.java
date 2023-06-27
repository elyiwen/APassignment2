package User;

import java.util.ArrayList;

public class Doctor extends Clinician{

    private String specialty;
    private String department;

    private ArrayList<Clinician> clinicianList = Clinician.getClinicianList();
    
    public Doctor(String clinicianID, String clinicianName, String clinicianContactNo, String clinicianRNIC, String clinicianPassword, String specialty, String department) {
        super(clinicianID, clinicianName, clinicianContactNo, clinicianRNIC, clinicianPassword);
        this.specialty = specialty;
        this.department = department;
        clinicianList.add(this);
        setAccountType("Doctor");
    }

    //Setter
    public void setSpeciality(String specialty){
        this.specialty = specialty;
    }
    public void setDepartment(String department){
        this.department = department;
    }

    //Getter
    public String getSpeciality(){
        return specialty;
    }
    public String getDepartment(){
        return department;
    }

    @Override
    public String toString(){
        return "Doctor Name:" + getClinicianName() + "\n" + "Doctor ID: " + getClinicianID() + "\n" + "Doctor Specialty: " + specialty;
    }
}
