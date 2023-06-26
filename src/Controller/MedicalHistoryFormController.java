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


public class MedicalHistoryFormController implements Initializable {

    @FXML
    private TextField tfFamilyHistory;

    @FXML
    private TextField tfAllergies;

    @FXML
    private TextField tfSmoking;

    @FXML
    private TextField tfAlcohol;

    @FXML
    private TextField tfTriageDetails;

    @FXML
    private TextField tfAdditionalComments;
    

    private Patient selectedPatient = PatientPageController.getSelectedPatient();



    @FXML
    void btnSaveClicked(ActionEvent event) throws IOException {
        String familyHistory = tfFamilyHistory.getText();
        String allergies = tfAllergies.getText();
        String smoking = tfSmoking.getText();
        String alcohol = tfAlcohol.getText();
        String triageDetails = tfTriageDetails.getText();
        String additionalComments = tfAdditionalComments.getText();

        String folderPath = "MedicalHistory";
        String filename = selectedPatient.getPatientID() + " Medical History.json";
        String filePath = folderPath + File.separator + filename;
        String fileContent = "Family History: " + familyHistory + "\n" +
                "Allergies: " + allergies + "\n" +
                "Smoking: " + smoking + "\n" +
                "Alcohol: " + alcohol + "\n" +
                "Triage Details: " + triageDetails + "\n" +
                "Additional Comments: " + additionalComments;

        File file = new File(filePath);
        if (file.exists()) {
            try {
                String existingContent = new String(Files.readAllBytes(file.toPath()));
                String updatedContent = fileContent + "\n----------Medical History----------\n" + existingContent;
                Files.write(file.toPath(), updatedContent.getBytes());
            } catch (IOException e) {
                System.out.println("An error occurred while updating the file: " + e.getMessage());
            }
        } else {
            try (FileWriter medicalHistory = new FileWriter(file)) {
                medicalHistory.write(fileContent);
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
