package Controller;

import Patient.Patient;
import User.Clinician;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;


public class PatientHistoryFormController implements Initializable {

    @FXML
    private TextField tfWardNumber;

    @FXML
    private TextField tfMovementMeans;

    @FXML
    private TextField tfAttendingPhysician;

    @FXML
    private TextField tfMajorComplication;

    @FXML
    private TextField tfTreatmentResults;

    @FXML
    private TextField tfSpecialComments;

    @FXML
    private TextField tfHistoryID;

    private Patient selectedPatient = PatientPageController.getSelectedPatient();
    private Clinician user = LoginSceneController.getUser();

    @FXML
    void btnSaveClicked(ActionEvent event) throws IOException {
        String wardNumber = tfWardNumber.getText();
        String movementMeans = tfMovementMeans.getText();
        String attendingPhysician = tfAttendingPhysician.getText();
        String majorComplication = tfMajorComplication.getText();
        String treatmentResults = tfTreatmentResults.getText();
        String specialComments = tfSpecialComments.getText();
        String historyID = tfHistoryID.getText();

        selectedPatient.setPatientEssential(wardNumber, attendingPhysician, specialComments);
        user.writeRecord();
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Patient History.txt";
        String filePath = folderPath + File.separator + filename;
        String fileContent = "Ward Number: " + wardNumber + "\n" +
                "Movement Means: " + movementMeans + "\n" +
                "Attending Physician: " + attendingPhysician + "\n" +
                "Major Complication: " + majorComplication + "\n" +
                "Treatment Results: " + treatmentResults + "\n" +
                "Special Comments: " + specialComments + "\n" +
                "History ID: " + historyID;

        File file = new File(filePath);
        if (file.exists()) {
            try {
                Files.newBufferedWriter(file.toPath(), StandardOpenOption.TRUNCATE_EXISTING).close();
                Files.write(file.toPath(), fileContent.getBytes());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while updating the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try (FileWriter patientHistory = new FileWriter(file)) {
                patientHistory.write(fileContent);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while writing the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Patient History.txt";
        String filePath = folderPath + File.separator + filename;
        File file = new File(filePath);
        if(file.exists()){
            selectedPatient.setTextField(selectedPatient, tfWardNumber, tfMovementMeans, tfAttendingPhysician, tfMajorComplication, tfTreatmentResults, tfSpecialComments, tfHistoryID);
        }else {
            tfWardNumber.setText("None");
            tfMovementMeans.setText("None");
            tfAttendingPhysician.setText("None");
            tfMajorComplication.setText("None");
            tfTreatmentResults.setText("None");
            tfSpecialComments.setText("None");
            tfHistoryID.setText("None");
        }
    }
}
