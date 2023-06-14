package code.User;

public class Nurse extends Clinician {
    
    private String Department;
    private String Rank;
    private boolean isNurse;

    public Nurse(String clinicianID, String firstName, String lastName, String password, String contactInfo, double salary, String Department, String Rank) {
        super(clinicianID, firstName, lastName, password, contactInfo, salary);
        this.Department = Department;
        this.Rank = Rank;
        this.isNurse = true;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        if (Department.length() > 0) {
            this.Department = Department;
        }
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String Rank) {
        if (Rank.length() > 0) {
            this.Rank = Rank;
        }
    }

    public boolean isIsNurse() {
        return isNurse;
    }

    public void setIsNurse(boolean isNurse) {
        this.isNurse = isNurse;
    }

    @Override  
    public String toString() {
        super.toString();
        return "Nurse{" + "Department=" + Department + ", Rank=" + Rank + ", isNurse=" + isNurse + '}';
    }
}