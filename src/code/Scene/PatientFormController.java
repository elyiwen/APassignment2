package code.Scene;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import code.Patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PatientFormController implements Initializable{

    @FXML
    private TextField tfPatientName;

    @FXML
    private TextField tfIdentityNo;

    @FXML
    private DatePicker dpDob;
    
    @FXML
    private TextField tfRace_Ethnicity;

    @FXML
    private ChoiceBox<String> chbPreferredLanguage;

    @FXML
    private ChoiceBox<String> chbGender;

    @FXML
    private ChoiceBox<String> chbMaritalStatus;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfState;

    @FXML
    private TextField tfZip;

    @FXML
    private TextField tfCountry;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfContactNo;

    @FXML
    private TextField tfEmergencyName;

    @FXML
    private TextField tfEmergencyRelationship;

    @FXML
    private TextField tfEmergencyContactNo;

    @FXML
    private Button btnSave;

    @FXML
    void btnSaveClicked(ActionEvent event) throws IOException {
 
        String patientName = tfPatientName.getText();
        String patientIdentityNo = tfIdentityNo.getText();
        LocalDate patientDoB = dpDob.getValue();
        String patientRace_Ethnicity = tfRace_Ethnicity.getText();
        String patientGender = chbGender.getValue();
        String patientPrefLanguage = chbPreferredLanguage.getValue();
        String patientMaritalStatus = chbMaritalStatus.getValue();

        // String patientAddress = tfAddress.getText();
        // String patientCity = tfCity.getText();
        // String patientState = tfState.getText();
        // String patientZipCode = tfZip.getText();
        // String patientCountry = tfCountry.getText();
        // String patientEmail = tfEmail.getText();
        // String patientContactNo = tfContactNo.getText();
        // String patientEmergencyName = tfEmergencyName.getText();
        // String patientEmergencyRelationship = tfEmergencyRelationship.getText();
        // String patientEmergencyContactNo = tfEmergencyContactNo.getText();

        if (patientIdentityNo.isEmpty() || patientName.isEmpty() || patientDoB == null){

            Alert alertisEmpty = new Alert(AlertType.INFORMATION, "Please Fill In Required Details", ButtonType.OK);
            alertisEmpty.setHeaderText("NOTIFICATION");
            alertisEmpty.setTitle("ALERT");
            alertisEmpty.showAndWait().get();
        }
        else{
            Alert alertExit = new Alert(AlertType.INFORMATION, "Confirm Patient Detail?", ButtonType.YES, ButtonType.NO);
            alertExit.setHeaderText("NOTIFICATION");
            alertExit.setTitle("ALERT");

            if(alertExit.showAndWait().get() == ButtonType.YES){
                // Add Patient 
                Patient newPatient = new Patient(patientName, patientIdentityNo, patientDoB, patientRace_Ethnicity, patientGender, patientPrefLanguage, patientMaritalStatus);
                PatientPageController.scenePatientForm = new Scene(FXMLLoader.load(getClass().getResource("PatientForm.fxml")));
                PatientPageController.stagePatientForm.setScene(PatientPageController.scenePatientForm);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        
        chbPreferredLanguage.getItems().removeAll(chbPreferredLanguage.getItems());
        chbPreferredLanguage.getItems().addAll("English", "Bahasa Melayu", "Chinese", "None");
        chbPreferredLanguage.getSelectionModel().select("None");

        chbGender.getItems().removeAll(chbGender.getItems());
        chbGender.getItems().addAll("Male", "Female", "None");
        chbGender.getSelectionModel().select("None");

        chbMaritalStatus.getItems().removeAll(chbMaritalStatus.getItems());
        chbMaritalStatus.getItems().addAll("Single", "Married", "None");
        chbMaritalStatus.getSelectionModel().select("None");
    }
}
