package Controller;

import Patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class EncounterFormController implements Initializable {

    @FXML
    private TextField tfEncounter;

    @FXML
    private DatePicker dpDate;



    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    @FXML
    void btnSaveClicked(ActionEvent event) throws IOException {
        String encounter = tfEncounter.getText();
        String date = null;

        if (encounter.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill out all the fields");
            alert.showAndWait();
            return;
        }

        LocalDate selectedDate = dpDate.getValue();
        if (selectedDate != null) {
            date = selectedDate.toString();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please select a date");
            alert.showAndWait();
            return;
        }

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
            try (FileWriter patientEncounter = new FileWriter(file)) {
                patientEncounter.write(fileContent);
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
