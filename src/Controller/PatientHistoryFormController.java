package Controller;

import Patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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

    private static Patient selectedPatient = PatientPageController.getSelectedPatient();

    @FXML
    void btnSaveClicked(ActionEvent event) throws IOException {
        String wardNumber = tfWardNumber.getText();
        String movementMeans =tfMovementMeans.getText();
        String attendingPhysician =tfAttendingPhysician.getText();
        String majorComplication =tfMajorComplication.getText();
        String treatmentResults =tfTreatmentResults.getText();
        String specialComments =tfSpecialComments.getText();
        String historyID =tfHistoryID.getText();

        String folderPath = "PatientHistory";
        String filename = selectedPatient.getPatientID() + " Patient History.json";
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
                String existingContent = new String(Files.readAllBytes(file.toPath()));
                String updatedContent = fileContent + "\n----------Patient History----------\n" + existingContent;
                Files.write(file.toPath(), updatedContent.getBytes());
            } catch (IOException e) {
                System.out.println("An error occurred while updating the file: " + e.getMessage());
            }
        } else {
            try (FileWriter patientHistory = new FileWriter(file)) {
                patientHistory.write(fileContent);
            } catch (IOException e) {
                System.out.println("An error occurred while writing the file: " + e.getMessage());
            }
        }

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
