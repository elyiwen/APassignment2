package Controller;

import Patient.Patient;
import Patient.PatientHistory;
import code.Clinician;
import Patient.MedicalHistory;
import Patient.Encounters;
import Patient.Events;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;



public class TreatmentCoursePageController implements Initializable{

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    private static Clinician user = MainSceneController.getUser();

    @FXML
    private Label labelPatientID;

    @FXML
    private Label labelPatientName;

    @FXML
    private Label labelPatientAge;

    @FXML
    private Label labelPatientDoB;

    @FXML
    private Label labelRace_Ethnicity;

    @FXML
    private Label labelGender;

    @FXML
    private Label labelPrefLanguage;

    @FXML
    private Label labelMaritalStatus;

    @FXML
    private Label labelAddress;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelContactNo;

    @FXML
    private Label labeEmergency;

    @FXML
    private Label labelWardNumber;

    @FXML
    private Label labelMovementMeans;

    @FXML
    private Label labelAttendingPhysician;

    @FXML
    private Label labelMajorComplications;

    @FXML
    private Label labelTreatmentResults;

    @FXML
    private Label labelSpecialComments;

    @FXML
    private Label labelHistoryID;

    @FXML
    private Label labelFamilyHistory;

    @FXML
    private Label labelAllergies;

    @FXML
    private Label labelSmoking;

    @FXML
    private Label labelAlcohol;

    @FXML
    private Label labelTriageDetails;

    @FXML
    private Label labelAdditionalComments;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnEditPatientHistory;

    @FXML
    private Button btnEditMedicalHistory;

    @FXML
    private Button btnViewEncounters;

    @FXML
    private Button btnAddEncounters;

    @FXML
    private Button btnViewEvents;

    @FXML
    private Button btnAddEvents;

    @FXML
    private Button btnViewTreatmentCourse;

    @FXML
    private Button btnAddTreatmentCourse;

    @FXML
    private VBox encountersVBox;

    @FXML
    private VBox eventsVBox;

    public static Stage stagePatientHistoryForm;
    public static Scene scenePatientHistoryForm;
    public static Stage stageMedicalHistoryForm;
    public static Scene sceneMedicalHistoryForm;
    public static Stage stageEncounterForm;
    public static Scene sceneEncounterForm;
    public static Stage stageEventForm;
    public static Scene sceneEventForm;
    public static Stage stageTreatmentCourseForm;
    public static Scene sceneTreatmentCourseForm;

    @FXML
    void btnEditInfoClicked() throws IOException{
        user.editPatient(selectedPatient);
    }

    @FXML
    void btnBackClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene/MainScene.fxml"));
        Parent root = loader.load();

        MainSceneController mainSceneController = loader.getController();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainSceneController.switchScene("PatientPage");
    }

    @FXML
    void btnRefreshClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene/MainScene.fxml"));
        Parent root = loader.load();

        MainSceneController mainSceneController = loader.getController();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainSceneController.switchScene("TreatmentCoursePage");
    }

    @FXML
    void btnEditPatientHistoryClicked(ActionEvent event) throws IOException{
        stagePatientHistoryForm = new Stage();
        scenePatientHistoryForm = new Scene(FXMLLoader.load(getClass().getResource("/Scene/PatientHistoryForm.fxml")));
        stagePatientHistoryForm.setTitle("Patient History Form");
        stagePatientHistoryForm.setScene(scenePatientHistoryForm);
        stagePatientHistoryForm.setResizable(false);
        stagePatientHistoryForm.show();
    }

    @FXML
    void btnEditMedicalHistoryClicked(ActionEvent event) throws IOException{
        stageMedicalHistoryForm = new Stage();
        sceneMedicalHistoryForm = new Scene(FXMLLoader.load(getClass().getResource("/Scene/MedicalHistoryForm.fxml")));
        stageMedicalHistoryForm.setTitle("Medical History Form");
        stageMedicalHistoryForm.setScene(sceneMedicalHistoryForm);
        stageMedicalHistoryForm.setResizable(false);
        stageMedicalHistoryForm.show();
    }

    @FXML
    void btnViewEncountersClicked(ActionEvent event) throws IOException{
            String folderPath = "File";
            String filename = selectedPatient.getPatientID() + " Encounter.json";
            String filePath = folderPath + File.separator + filename;

            File file = new File(filePath);

            if (file.exists()) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error opening file : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }

    @FXML
    void btnAddEncountersClicked(ActionEvent event) throws IOException{
        stageEncounterForm = new Stage();
        sceneEncounterForm = new Scene(FXMLLoader.load(getClass().getResource("/Scene/EncounterForm.fxml")));
        stageEncounterForm.setTitle("Encounter Form");
        stageEncounterForm.setScene(sceneEncounterForm);
        stageEncounterForm.setResizable(false);
        stageEncounterForm.show();
    }

    @FXML
    void btnViewEventsClicked(ActionEvent event) throws IOException{
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Event.json";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);

        if (file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error opening file : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "File does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void btnAddEventsClicked(ActionEvent event) throws IOException{
        stageEventForm = new Stage();
        sceneEventForm = new Scene(FXMLLoader.load(getClass().getResource("/Scene/EventForm.fxml")));
        stageEventForm.setTitle("Event Form");
        stageEventForm.setScene(sceneEventForm);
        stageEventForm.setResizable(false);
        stageEventForm.show();
    }

    @FXML
    void btnViewTreatmentCourseClicked(ActionEvent event) throws IOException{
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Treatment Course.json";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);

        if (file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error opening file : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "File does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    void btnAddTreatmentCourseClicked(ActionEvent event) throws IOException{
        stageTreatmentCourseForm = new Stage();
        sceneTreatmentCourseForm = new Scene(FXMLLoader.load(getClass().getResource("/Scene/TreatmentCourseForm.fxml")));
        stageTreatmentCourseForm.setTitle("Treatment Course Form");
        stageTreatmentCourseForm.setScene(sceneTreatmentCourseForm);
        stageTreatmentCourseForm.setResizable(false);
        stageTreatmentCourseForm.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelPatientID.setText("Patient ID: " + selectedPatient.getPatientID());
        labelPatientName.setText("Patient Name: " + selectedPatient.getPatientName());
        labelPatientAge.setText("Age: " + selectedPatient.getPatientAge());
        labelPatientDoB.setText("DoB: " + selectedPatient.getPatientDoB().toString());
        labelRace_Ethnicity.setText("Race/Ethnicity: " + selectedPatient.getRace_Ethinicity());
        labelGender.setText("Gender: " + selectedPatient.getGender());
        labelPrefLanguage.setText("Preferred Language: " + selectedPatient.getPrefLanguage());
        labelMaritalStatus.setText("Marital Status: " + selectedPatient.getMaritalStatus());
        labelAddress.setText("Address: " + selectedPatient.getFullAddress());
        labelEmail.setText("Email: " + selectedPatient.getPatientEmail());
        labelContactNo.setText("Contact No: " + selectedPatient.getPatientContactNo());
        labeEmergency.setText("Emergency Info: " + selectedPatient.getEmergencyInfo());
        PatientHistory.displayPatientHistory(selectedPatient, labelWardNumber, labelMovementMeans, labelAttendingPhysician, labelMajorComplications, labelTreatmentResults, labelSpecialComments, labelHistoryID);
        MedicalHistory.displayMedicalHistory(selectedPatient, labelFamilyHistory, labelAllergies, labelSmoking, labelAlcohol, labelTriageDetails, labelAdditionalComments);
        Encounters.displayAllEncounters(selectedPatient, encountersVBox);
        Events.displayAllEvents(selectedPatient,eventsVBox);
    }
}


