package code.User;

import java.io.File;
import java.util.ArrayList;

public class Clinician {
    
    private String clinicianID;
    private String firstName;
    private String lastName;
    private String password;
    private String contactInfo;
    private double salary;
    private boolean isAdministrator;

    private static ArrayList<Clinician> clinicianList = new ArrayList<>();
    private static File clinicianFile = new File("clinician.json");

    public Clinician(String clinicianID, String password){
        this.clinicianID = clinicianID;
        this.password = password;
    }

    public Clinician(String clinicianID, String firstName, String lastName, String password, String contactInfo, double salary) {
        this.clinicianID = clinicianID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.contactInfo = contactInfo;
        this.salary = salary;
        this.isAdministrator = false;
    }

    public String getClinicianID() {
        return clinicianID;
    }

    public void setClinicianID(String clinicianID) {
        this.clinicianID = clinicianID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() > 0) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 0) {
            this.lastName = lastName;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() > 0) {
            this.password = password;
        }
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        if (contactInfo.length() > 0) {
            this.contactInfo = contactInfo;
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        }
    }

    public boolean isIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator(boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    public static ArrayList<Clinician> getClinicianList(){
        return clinicianList;
    }

    public static File getClinicianFile(){
        return clinicianFile;
    }

    @Override
    public String toString() {
        return "Clinician{" + "clinicianID=" + clinicianID + ", firstName=" + firstName + ", lastName=" + lastName + 
        ", password=" + password + ", contactInfo=" + contactInfo + ", salary=" + salary + ", isAdmin=" + isAdministrator + '}';
    }
}
