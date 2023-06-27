package User;

import java.util.ArrayList;

public class Nurse extends Clinician{

    private String duty;
    private String unit;

    private ArrayList<Clinician> clinicianList = Clinician.getClinicianList();
    
    public Nurse(String clinicianID, String clinicianName, String clinicianContactNo, String clinicianRNIC, String clinicianPassword, String duty, String unit) {
        super(clinicianID, clinicianName, clinicianContactNo, clinicianRNIC, clinicianPassword);
        this.duty = duty;
        this.unit = unit;
        clinicianList.add(this);
        setAccountType("Nurse");
    }

    //Setter
    public void setDuty(String duty){
        this.duty = duty;
    }
    public void setUnit(String unit){
        this.unit = unit;
    }

    //Getter
    public String getDuty(){
        return duty;
    }
    public String getUnit(){
        return unit;
    }

    @Override
    public String toString(){
        return "Nurse Name:" + getClinicianName() + "\n" + "Nurse ID: " + getClinicianID() + "\n" + "Nurse Unit: " + unit;
    }
}
