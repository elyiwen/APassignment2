package Controller;

import Patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TreatmentCourseFormController implements Initializable {

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    //Treament Course
    @FXML
    private TextField tfTreatmentID;
    @FXML
    private DatePicker dpSD;
    @FXML
    private DatePicker dpED;

    //Diagnosis
    @FXML
    private TextField tfDisease;
    @FXML
    private DatePicker dpDoD;

    //Analysis
    @FXML
    private TextField tfAnalysis;
    @FXML
    private TextField tfContentOfAnalysis;
    @FXML
    private DatePicker dpDoA;

    //Procedure
    @FXML
    private TextField tfNameOfProcedure;
    @FXML
    private TextField tfContentOfProcedure;
    @FXML
    private TextField tfTime;
    @FXML
    private DatePicker dpDoP;

    //Medication
    @FXML
    private TextField tfNameOfMedication;
    @FXML
    private TextField tfDosage;
    @FXML
    private TextField tfComment;
    @FXML
    private TextField tfFrequency;
    @FXML
    private DatePicker dpStartedDate;
    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    void btnSaveClicked(ActionEvent event) throws IOException { //A method for creating a file to record treatment course
        String treatmentID = tfTreatmentID.getText();
        String startingDate = null;
        String endingDate = null;
        String disease = tfDisease.getText();
        String dateOfDiagnosis = null;
        String analysis = tfAnalysis.getText();
        String contentOfAnalysis = tfContentOfAnalysis.getText();
        String dateOfAnalysis = null;
        String procedure = tfNameOfProcedure.getText();
        String contentOfProcedure = tfContentOfProcedure.getText();
        String time = tfTime.getText();
        String dateOfProcedure = null;
        String medication = tfNameOfMedication.getText();
        String dosage = tfDosage.getText();
        String comment = tfComment.getText();
        String frequency = tfFrequency.getText();
        String type = typeComboBox.getValue();
        String sDateOfMedication = null;

        if (disease.isEmpty() || analysis.isEmpty() || contentOfAnalysis.isEmpty() || procedure.isEmpty() || contentOfProcedure.isEmpty() || medication.isEmpty()
                || dosage.isEmpty() || comment.isEmpty() || frequency.isEmpty() || type.isEmpty() || treatmentID.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill out all the fields");
            alert.showAndWait();
            return;
        }

        LocalDate SD = dpSD.getValue();
        if (SD != null) {
            startingDate = SD.toString();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please select a starting date");
            alert.showAndWait();
            return;
        }

        LocalDate ED = dpED.getValue();
        if (ED != null) {
            endingDate = ED.toString();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please select a end date");
            alert.showAndWait();
            return;
        }

        LocalDate DOD = dpDoD.getValue();
        if (DOD != null) {
            dateOfDiagnosis = DOD.toString();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please select a date of diagnosis");
            alert.showAndWait();
            return;
        }

        LocalDate DOA = dpDoA.getValue();
        if (DOA != null) {
            dateOfAnalysis = DOA.toString();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please select a date of analysis");
            alert.showAndWait();
            return;
        }

        LocalDate DOP = dpDoP.getValue();
        if (DOP != null) {
            dateOfProcedure = DOP.toString();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please select a date of procedure");
            alert.showAndWait();
            return;
        }

        LocalDate sDate = dpStartedDate.getValue();
        if (sDate != null) {
            sDateOfMedication = sDate.toString();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please select a started date of medication");
            alert.showAndWait();
            return;
        }

        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Treatment Course.txt";
        String filePath = folderPath + File.separator + filename;
        String fileContent = "Treatment Course ID: " + treatmentID + "\n" +
                "Starting Date: " + startingDate + "\n" +
                "Ending Date: " + endingDate + "\n" +
                "Disease: " + disease + "\n" +
                "Date of Diagnosis: " + dateOfDiagnosis + "\n" +
                "Name of Analysis: " + analysis + "\n" +
                "Content Of Analysis: " + contentOfAnalysis + "\n" +
                "Date of Analysis: " + dateOfAnalysis + "\n"+
                "Name of Procedure: " + procedure + "\n" +
                "Content Of Procedure: " + contentOfProcedure + "\n" +
                "Time: " + time + "\n" +
                "Date of Procedure: " + dateOfProcedure + "\n"+
                "Name of Medication: " + medication + "\n" +
                "Dosage: " + dosage + "\n" +
                "Comment: " + comment + "\n" +
                "Frequency: " + frequency + "\n" +
                "C,DC,WH: " + type + "\n" +
                "Started Date: " + sDateOfMedication + "\n";

        File file = new File(filePath);
        if (file.exists()) {
            try {
                String existingContent = new String(Files.readAllBytes(file.toPath()));
                int eventCount = countDiseaseOccurrences(existingContent);
                String updatedContent = "-----Treatment Course " + (eventCount + 1) + "-----\n" + fileContent + existingContent;
                Files.write(file.toPath(), updatedContent.getBytes());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred while updating the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try (FileWriter patientEvent = new FileWriter(file)) {
                patientEvent.write("-----Treatment Course 1-----\n" + fileContent);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred while writing the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    private int countDiseaseOccurrences(String content) {
        int count = 0;
        int index = content.indexOf("-----Treatment Course");
        while (index != -1) {
            count++;
            index = content.indexOf("-----Treatment Course", index + 1);
        }
        return count;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeComboBox.getItems().addAll("C", "DC", "WH");
    }
}
