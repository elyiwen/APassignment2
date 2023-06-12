package code.Scene;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import code.User.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginSceneController {
    
    @FXML
    private Button btnLogIn;

    @FXML
    private Button btnAdmin;

    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;

    @FXML
    public void btnLogInClicked(ActionEvent e){
        try{
            login(e);
        }
        catch(NullPointerException npe){
            JOptionPane.showMessageDialog(new JFrame(), "No Admin Record Available", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    

    @FXML
    public void btnAdminClicked(ActionEvent e){   // temporary
        Admin.createAdminFile();
        new Admin("admin", "123");
        Admin.writeAdminRecord();
        Admin.readAdminRecord();
        Admin.displayAdminList();
        
        Admin.getAdminListRead().clear();
    }

    public void initLoginScene(Stage loginStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene loginScene = new Scene(root);
        loginStage.setResizable(false);
        loginStage.setTitle("Taylor's EMR");
        loginStage.setScene(loginScene);
        loginStage.show();
    }

    public void login(ActionEvent e){
        //HomeSceneController hsc = new HomeSceneController();
        //hsc.initHomeScene(e);
        try{
            FileInputStream fis = new FileInputStream(Admin.getAdminFile());
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            boolean check = false;
            while(fis.available() > 0){
                Admin tmpAdmin = (Admin)ois.readObject();

                if(tmpAdmin.getAdminID().equals(tfUsername.getText()) && (tmpAdmin.getPassword().equals(tfPassword.getText()))){
                    JOptionPane.showMessageDialog(new JFrame(), "Login Success", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
                    HomeSceneController hsc = new HomeSceneController();
                    hsc.initHomeScene(e);
                    check = true;
                    break;
                }
            }
            if(check == false){
                JOptionPane.showMessageDialog(new JFrame(), "Invalid Login", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (IOException ioe){
            JOptionPane.showMessageDialog(new JFrame(), "Admin File Not Exist", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (ClassNotFoundException cnfe){
            JOptionPane.showMessageDialog(new JFrame(), "No Admin Record Available", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
