package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Patient.Patient;
import User.Clinician;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PatientFormController implements Initializable{

    @FXML
    private Button btnSave;

    @FXML
    private ChoiceBox<String> chbGender;

    @FXML
    private ChoiceBox<String> chbMaritalStatus;

    @FXML
    private ChoiceBox<String> chbPreferredLanguage;

    @FXML
    private ChoiceBox<String> chbStatus;

    @FXML
    private DatePicker dpDob;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfContactNo;

    @FXML
    private TextField tfCountry;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfEmergencyContactNo;

    @FXML
    private TextField tfEmergencyName;

    @FXML
    private TextField tfEmergencyRelationship;

    @FXML
    private TextField tfIdentityNo;

    @FXML
    private TextField tfPatientName;

    @FXML
    private TextField tfRace_Ethnicity;

    @FXML
    private TextField tfState;

    @FXML
    private TextField tfZip;

    private Clinician user = LoginSceneController.getUser();

    private Patient editedPatient;

    @FXML
    void btnSaveClicked(ActionEvent event) throws IOException {
        String patientName = tfPatientName.getText();
        String identityNo = tfIdentityNo.getText();
        String prefLangauge = chbPreferredLanguage.getValue();
        String gender = chbGender.getValue();
        LocalDate doB = dpDob.getValue();
        String race_ethnicity = tfRace_Ethnicity.getText();
        String maritalStatus = chbMaritalStatus.getValue();
        String status = chbStatus.getValue();
        String address = tfAddress.getText();
        String city = tfCity.getText();
        String state = tfState.getText();
        String zipCode = tfZip.getText();
        String country = tfCountry.getText();
        String email = tfEmail.getText();
        String contactNo = tfContactNo.getText();
        String emergencyName = tfEmergencyName.getText();
        String emergencyRelationship = tfEmergencyRelationship.getText();
        String emergencyContactNo = tfEmergencyContactNo.getText();

        if (editedPatient == null){
        Patient newPatient = new Patient();
        newPatient.setPatientBiodata(patientName, identityNo, doB, race_ethnicity, gender, prefLangauge, maritalStatus, status);
        newPatient.setPatientContactInfo(address, country, state, city, zipCode, email, contactNo, emergencyContactNo, emergencyName, emergencyRelationship);
        newPatient.setPatientEssential("None", "None", "None");
        user.addPatient(newPatient);
        user.writeRecord();

        Alert alertSuccess = new Alert(AlertType.CONFIRMATION, "Patient Recorded Successfully", ButtonType.OK, ButtonType.CANCEL);
        alertSuccess.setHeaderText("NOTIFICATION");
        alertSuccess.setTitle("ALERT");
        alertSuccess.showAndWait();

        Parent root = FXMLLoader.load(getClass().getResource("/Scene/PatientForm.fxml"));
        Scene scene = new Scene(root);
        PatientPageController.stage.setScene(scene);
        }
        else if (editedPatient != null){
            editedPatient.setPatientBiodata(patientName, identityNo, doB, race_ethnicity, gender, prefLangauge, maritalStatus, status);
            editedPatient.setPatientContactInfo(address, country, state, city, zipCode, email, contactNo, emergencyContactNo, emergencyName, emergencyRelationship);
            user.writeRecord();

            Alert alertSuccess = new Alert(AlertType.CONFIRMATION, "Patient Edited Successfully", ButtonType.OK, ButtonType.CANCEL);
            alertSuccess.setHeaderText("NOTIFICATION");
            alertSuccess.setTitle("ALERT");
            alertSuccess.showAndWait();
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        chbGender.getItems().removeAll(chbGender.getItems());
        chbGender.getItems().addAll("Male", "Female", "None");
        chbGender.setValue("None");

        chbMaritalStatus.getItems().removeAll(chbMaritalStatus.getItems());
        chbMaritalStatus.getItems().addAll("Single", "Married", "None");
        chbMaritalStatus.setValue("None");

        chbPreferredLanguage.getItems().removeAll(chbPreferredLanguage.getItems());
        chbPreferredLanguage.getItems().addAll("English", "Chinese", "Bahasa Malaysia");
        chbPreferredLanguage.setValue("None");

        chbStatus.getItems().removeAll(chbStatus.getItems());
        chbStatus.getItems().addAll("Outpatient", "Inpatient", "Candidate");
        chbStatus.setValue("Candidate");
    }

    public void setBiodata(String patientName, String identityNo, String prefLanguage, String gender, LocalDate doB, String race_ethinicity, String maritalStatus, String status){
        this.tfPatientName.setText(patientName);
        this.tfIdentityNo.setText(identityNo);
        this.chbPreferredLanguage.setValue(prefLanguage);
        this.chbGender.setValue(gender);
        this.dpDob.setValue(doB);
        this.tfRace_Ethnicity.setText(race_ethinicity);
        this.chbMaritalStatus.setValue(maritalStatus);
        this.chbStatus.setValue(status);
    }

    public void setContactInfo(String address, String city, String state, String zip, String country, String email, String contactNo, String emergencyName, String emergencyRelationship, String emergencyContactNo){
        this.tfAddress.setText(address);
        this.tfCity.setText(city);
        this.tfState.setText(state);
        this.tfZip.setText(zip);
        this.tfCountry.setText(country);
        this.tfEmail.setText(email);
        this.tfContactNo.setText(contactNo);
        this.tfEmergencyName.setText(emergencyName);
        this.tfEmergencyRelationship.setText(emergencyRelationship);
        this.tfEmergencyContactNo.setText(emergencyContactNo);
    }

    public void setEditedPatient(Patient editedPatient){
        this.editedPatient = editedPatient;
    }
}
