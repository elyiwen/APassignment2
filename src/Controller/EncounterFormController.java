package Controller;

import Patient.Patient;
import Patient.Encounters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;


public class EncounterFormController implements Initializable {

    @FXML
    private TextField tfEncounter;

    @FXML
    private DatePicker dpDate;



    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    @FXML
    void btnSaveClicked(ActionEvent event) throws IOException {
        Encounters encounters = new Encounters();
        String encounter = tfEncounter.getText();
        String date = dpDate.getValue().toString();

        if (encounter.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill out all the fields", "Incomplete Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }

        encounters.setPatientHistory(encounter, date);
        String folderPath = "Encounter";
        String filename = selectedPatient.getPatientID() + " Encounter.txt";
        String filePath = folderPath + File.separator + filename;
        String fileContent = "Encounter: " + encounter + "\n" +
                "Date: " + date + "\n";

        File file = new File(filePath);
        if (file.exists()) {
            try {
                String existingContent = new String(Files.readAllBytes(file.toPath()));
                String updatedContent = fileContent + existingContent;
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
