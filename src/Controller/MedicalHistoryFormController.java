package Controller;

import Patient.Patient;
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

import Interfaces.MedicalHistory;


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

        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Medical History.txt";
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
        String filename = selectedPatient.getPatientID() + " Medical History.txt";
        String filePath = folderPath + File.separator + filename;
        File file = new File(filePath);
        if(file.exists()){
            selectedPatient.setTextField(selectedPatient, tfFamilyHistory, tfAllergies, tfSmoking, tfAlcohol, tfTriageDetails, tfAdditionalComments);
        }else {
            tfFamilyHistory.setText("None");
            tfAllergies.setText("None");
            tfSmoking.setText("None");
            tfAlcohol.setText("None");
            tfTriageDetails.setText("None");
            tfAdditionalComments.setText("None");
        }
    }
}
