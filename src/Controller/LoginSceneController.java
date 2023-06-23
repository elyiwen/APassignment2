package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import code.Clinician;
import code.Doctor;
import code.Nurse;
import code.Pharmacist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

public class LoginSceneController implements Initializable{

    @FXML
    private Button btnLogIn;

    @FXML
    private ComboBox<String> cbAccountType;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUsername;

    private static Doctor newDoctor;
    private static Pharmacist newPharmacist;
    private static Nurse newNurse;

    public static String user;
    public static String userID;

    @FXML
    void btnLogInClicked(ActionEvent event) throws IOException{
        // Get User Input
        try{
        user = cbAccountType.getValue();
        userID = tfUsername.getText();
        String password = tfPassword.getText();

        switch (user){
            case "Doctor":
                if (Clinician.loginAuthentication(user, userID, password) == true){
                    newDoctor = (Doctor)Clinician.login(user, userID, password);
                    loginInfo(true, event);
                }
                else {
                    loginInfo(false, event);
                }
                break;
                
            case "Pharmacist":
                if (Clinician.loginAuthentication(user, userID, password) == true){
                    newPharmacist = (Pharmacist)Clinician.login(user, userID, password);
                    loginInfo(true, event);
                }
                else {
                    loginInfo(false, event);
                }
                break;

            case "Nurse": 
                if (Clinician.loginAuthentication(user, userID, password) == true){
                    newNurse = (Nurse)Clinician.login(user, userID, password);
                    loginInfo(true, event);
                }
                else {
                    loginInfo(false, event);
                }
                break;               
        }
        }
        catch (NullPointerException npe){
            Alert alertSuccess = new Alert(AlertType.CONFIRMATION, "Please Select Account Type", ButtonType.OK);
            alertSuccess.setHeaderText("NOTIFICATION");
            alertSuccess.setTitle("ALERT");
            alertSuccess.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        newDoctor = new Doctor("doctor", 
        "Alpha", 
        "0112223333", 
        "998877665544", 
        "doctor123", 
        "Neurologist", 
        "Neurology");

        newPharmacist = new Pharmacist("pharmacist",
        "Beta",
        "0998887777",
        "112233445566",
        "pharmacist123",
        "Bachelor of Pharmacy, NAPLEX Licensed",
        "Hospital Pharmacist");

        newNurse = new Nurse("nurse",
        "Gamma",
        "0223334444",
        "223344556677",
        "nurse123",
        "Critical Care Nurse",
        "Medical Intensive Care Unit");

        cbAccountType.getItems().removeAll(cbAccountType.getItems());
        cbAccountType.getItems().addAll("Doctor", "Nurse", "Pharmacist");
    }

    public void loginInfo(boolean authentication, ActionEvent event) throws IOException{
        if (authentication == true){
                Alert alertSuccess = new Alert(AlertType.CONFIRMATION, "Login Success", ButtonType.OK);
                alertSuccess.setHeaderText("NOTIFICATION");
                alertSuccess.setTitle("ALERT");
                if (alertSuccess.showAndWait().get() == ButtonType.OK){
                    loadMainPage(event);
                }
            }
            else{
                Alert alertFailed = new Alert(AlertType.CONFIRMATION, "Login Failed", ButtonType.OK);
                alertFailed.setHeaderText("NOTIFICATION");
                alertFailed.setTitle("ALERT");
                if (alertFailed.showAndWait().get() == ButtonType.OK){
                    return;
                }
            }
    }

    private void loadMainPage(ActionEvent event) throws IOException{
        Parent mainFXML = FXMLLoader.load(getClass().getResource("/Scene/MainScene.fxml"));

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene mainScene = new Scene(mainFXML);
        primaryStage.setMaximized(true);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    //Getter
    public static Doctor getDoctor(){
        return newDoctor;
    }
    public static Pharmacist getPharmacist(){
        return newPharmacist;
    }
    public static Nurse getNurse(){
        return newNurse;
    }
}