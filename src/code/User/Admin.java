package code.User;

import java.io.Serializable;
import java.util.ArrayList;


public class Admin implements Serializable{

    private String adminID;
    private String password;
    private boolean isAdministrator;

    private static ArrayList<Admin> adminList = new ArrayList<Admin>();

    public Admin(String adminID, String password) {
        this.adminID = adminID;
        this.password = password;
        this.isAdministrator = true;
        adminList.add(this);
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        if (adminID.length() > 0) {
            this.adminID = adminID;
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

    public boolean isIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator(boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    public static ArrayList<Admin> getAdminList(){
        return adminList;
    }
    
    @Override
    public String toString() {
        return "Admin{" + "adminID=" + adminID + ", password=" + password + ", isAdmin=" + isAdministrator + '}';
    }
}
