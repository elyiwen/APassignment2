package Controller;

import Patient.Patient;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import javafx.stage.Stage;

import javax.swing.*;

public class TreatmentCoursePageController implements Initializable{

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

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
    private Button btnEditPatientHistory;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnViewPatientHistoryClicked;

    public static Stage stagePatientHistoryForm;
    public static Scene scenePatientHistoryForm;

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
    void btnViewPatientHistoryClicked(ActionEvent event)throws IOException{
        String folderPath = "PatientHistory";
        String filename = selectedPatient.getPatientID() + " History.txt";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);

        if (file.exists()) {
            try {
                Desktop.getDesktop().edit(file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error opening file for editing: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "File does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        readPatientHistory();
    }

    public void readPatientHistory() {
        String folderPath = "PatientHistory";
        String filename = selectedPatient.getPatientID() + " History.txt";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                int lineCount = 0;
                boolean wardNumberDisplayed = false;
                boolean movementMeansDisplayed = false;
                boolean attendingPhysicianDisplayed = false;
                boolean majorComplicationDisplayed = false;
                boolean treatmentResultsDisplayed = false;
                boolean specialCommentsDisplayed = false;
                boolean historyIDDisplayed = false;

                while ((line = reader.readLine()) != null && lineCount < 7) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Ward Number":
                                if (!wardNumberDisplayed) {
                                    labelWardNumber.setText("Ward Number: " + fieldValue);
                                    wardNumberDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Movement Means":
                                if (!movementMeansDisplayed) {
                                    labelMovementMeans.setText("Movement Means: " + fieldValue);
                                    movementMeansDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Attending Physician":
                                if (!attendingPhysicianDisplayed) {
                                    labelAttendingPhysician.setText("Attending Physician: " + fieldValue);
                                    attendingPhysicianDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Major Complication":
                                if (!majorComplicationDisplayed) {
                                    labelMajorComplications.setText("Major Complications: " + fieldValue);
                                    majorComplicationDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Treatment Results":
                                if (!treatmentResultsDisplayed) {
                                    labelTreatmentResults.setText("Treatment Results: " + fieldValue);
                                    treatmentResultsDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Special Comments":
                                if (!specialCommentsDisplayed) {
                                    labelSpecialComments.setText("Special Comments: " + fieldValue);
                                    specialCommentsDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "History ID":
                                if (!historyIDDisplayed) {
                                    labelHistoryID.setText("History ID: " + fieldValue);
                                    historyIDDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }

                reader.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}


