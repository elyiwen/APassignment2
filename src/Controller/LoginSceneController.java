package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import Features.Announcement;
import Features.Checklist;
import User.Clinician;
import User.Doctor;
import User.Nurse;
import User.Pharmacist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Screen;
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

    private static Clinician user;

    @FXML
    void btnLogInClicked(ActionEvent event) throws ParseException{
        
        try{

        // Get User Login Information
        String accountType = cbAccountType.getValue();
        String userID = tfUsername.getText();
        String password = tfPassword.getText();

        // Login Method with different Users
        switch (accountType) {

            case "Doctor":
                if (Clinician.loginAuthentication(accountType, userID, password) == true) {
                    user = (Doctor) Clinician.login(accountType, userID, password);
                    loginConfirmation(true, event);
                    user.readRecord();
                    user.readCandidateRecord();
                    Announcement.readFromFile();
                    Checklist.readFromFile();
                } 
                else {
                    loginConfirmation(false, event);
                }
                break;

            case "Pharmacist":
                if (Clinician.loginAuthentication(accountType, userID, password) == true) {
                    user = (Pharmacist) Clinician.login(accountType, userID, password);
                    loginConfirmation(true, event);
                    user.readRecord();
                    user.readCandidateRecord();
                    Announcement.readFromFile();
                    Checklist.readFromFile();
                } 
                else {
                    loginConfirmation(false, event);
                }
                break;

            case "Nurse":
                if (Clinician.loginAuthentication(accountType, userID, password) == true) {
                    user = (Nurse) Clinician.login(accountType, userID, password);
                    loginConfirmation(true, event);
                    user.readRecord();
                    user.readCandidateRecord();
                    Announcement.readFromFile();
                    Checklist.readFromFile();
                } 
                else {
                    loginConfirmation(false, event);
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
        catch (IOException ioe){
            Alert alertSuccess = new Alert(AlertType.CONFIRMATION, "No Patient File Recorded", ButtonType.OK);
            alertSuccess.setHeaderText("NOTIFICATION");
            alertSuccess.setTitle("ALERT");
            alertSuccess.showAndWait();
        }
        catch (ClassNotFoundException cnfe){
            Alert alertSuccess = new Alert(AlertType.CONFIRMATION, "No Patient Recorded", ButtonType.OK);
            alertSuccess.setHeaderText("NOTIFICATION");
            alertSuccess.setTitle("ALERT");
            alertSuccess.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Setting Default Users
        new Doctor("doctor", 
        "Alpha", 
        "0112223333", 
        "998877665544", 
        "doctor123", 
        "Neurologist", 
        "Neurology");

        new Pharmacist("pharmacist",
        "Beta",
        "0998887777",
        "112233445566",
        "pharmacist123",
        "Bachelor of Pharmacy, NAPLEX Licensed",
        "Hospital Pharmacist");

        new Nurse("nurse",
        "Gamma",
        "0223334444",
        "223344556677",
        "nurse123",
        "Critical Care Nurse",
        "Medical Intensive Care Unit");

        // Setting ComboBox Selections
        cbAccountType.getItems().removeAll(cbAccountType.getItems());
        cbAccountType.getItems().addAll("Doctor", "Nurse", "Pharmacist");
    }

    // Show Confirmation Message before Login
    public void loginConfirmation(boolean authentication, ActionEvent event) throws IOException{
        if (authentication == true){
                Alert alertSuccess = new Alert(AlertType.CONFIRMATION, "Login Success", ButtonType.OK, ButtonType.CANCEL);
                alertSuccess.setHeaderText("NOTIFICATION");
                alertSuccess.setTitle("ALERT");
                if (alertSuccess.showAndWait().get() == ButtonType.OK){
                    loadMainPage(event);
                }
            }
            else{
                Alert alertFailed = new Alert(AlertType.CONFIRMATION, "Login Failed", ButtonType.OK, ButtonType.CANCEL);
                alertFailed.setHeaderText("NOTIFICATION");
                alertFailed.setTitle("ALERT");
                if (alertFailed.showAndWait().get() == ButtonType.OK){
                    return;
                }
            }
    }

    // Load Main Page
    private void loadMainPage(ActionEvent event) throws IOException{
        Parent mainFXML = FXMLLoader.load(getClass().getResource("/Scene/MainScene.fxml"));

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene mainScene = new Scene(mainFXML);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2); 
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 4);  
    }

    //Getter
    public static Clinician getUser(){
        return user;
    }
}