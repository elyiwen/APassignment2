package code.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Admin implements Serializable{

    private String adminID;
    private String password;
    private boolean isAdministrator;
    private static ArrayList<Admin> adminList = new ArrayList<Admin>();
    private static ArrayList<Admin> adminListRead = new ArrayList<Admin>();
    private static File adminFile;

    public Admin(String adminID, String password) {
        this.adminID = adminID;
        this.password = password;
        this.isAdministrator = true;
        adminList.add(this);
    }

    public void displayAdmin(){
        String msg = "Admin ID: " + getAdminID() + "\n" + "Admin Password: " + getPassword();
        JOptionPane.showMessageDialog(new JFrame(), msg, "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayAdminList(){
        for(Admin a : adminListRead){
            a.displayAdmin();
        }
    }

    public static void createAdminFile(){
        adminFile = new File("admin.json");
        try{
            if (adminFile.createNewFile()) {
                JOptionPane.showMessageDialog(new JFrame(), "Admin File Created", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
            } 
            else {
                JOptionPane.showMessageDialog(new JFrame(), "Admin File Already Exist", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(IOException ioe){
            JOptionPane.showMessageDialog(new JFrame(), "Admin File Not Exist", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void writeAdminRecord(){
        try{
            FileOutputStream fos = new FileOutputStream(adminFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Admin a : adminList){
                oos.writeObject(a);
            }
            oos.close();
        }
        catch (IOException ioe){
            JOptionPane.showMessageDialog(new JFrame(), "Admin File Not Exist", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void readAdminRecord(){
        try{
            FileInputStream fis = new FileInputStream(adminFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while(fis.available() > 0){
                Admin tmpAdmin = (Admin)ois.readObject();
                adminListRead.add(tmpAdmin);
            }
            ois.close();
        }
        catch (IOException ioe){
            JOptionPane.showMessageDialog(new JFrame(), "Admin File Not Exist", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (ClassNotFoundException cnfe){
            JOptionPane.showMessageDialog(new JFrame(), "No Admin Record Available", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
        }
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
    public static ArrayList<Admin> getAdminListRead(){
        return adminListRead;
    }

    public static File getAdminFile(){
        return adminFile;
    }

    @Override
    public String toString() {
        return "Admin{" + "adminID=" + adminID + ", password=" + password + ", isAdmin=" + isAdministrator + '}';
    }
}
