package code.User;

public class Doctor extends Clinician {
    
    private String Department;
    private boolean isDoctor;

    public Doctor(String clinicianID, String firstName, String lastName, String password, String contactInfo, double salary, String Department) {
        super(clinicianID, firstName, lastName, password, contactInfo, salary);
        this.Department = Department;
        this.isDoctor = true;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        if (Department.length() > 0) {
            this.Department = Department;
        }
    }

    public boolean isIsDoctor() {
        return isDoctor;
    }

    public void setIsDoctor(boolean isDoctor) {
        this.isDoctor = isDoctor;
    }

    @Override
    public String toString() {
        super.toString();
        return "Doctor{" + "Department=" + Department + ", isDoctor=" + isDoctor + '}';
    }
}