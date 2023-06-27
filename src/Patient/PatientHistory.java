package Patient;

import Controller.PatientPageController;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class PatientHistory {

    private Patient selectedPatient = PatientPageController.getSelectedPatient();
    public static void displayPatientHistory(Patient selectedPatient, Label labelWardNumber, Label labelMovementMeans,
                                             Label labelAttendingPhysician, Label labelMajorComplications,
                                             Label labelTreatmentResults, Label labelSpecialComments,
                                             Label labelHistoryID) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Patient History.json";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Ward Number":
                                labelWardNumber.setText("Ward Number: " + fieldValue);
                                break;
                            case "Movement Means":
                                labelMovementMeans.setText("Movement Means: " + fieldValue);
                                break;
                            case "Attending Physician":
                                labelAttendingPhysician.setText("Attending Physician: " + fieldValue);
                                break;
                            case "Major Complication":
                                labelMajorComplications.setText("Major Complications: " + fieldValue);
                                break;
                            case "Treatment Results":
                                labelTreatmentResults.setText("Treatment Results: " + fieldValue);
                                break;
                            case "Special Comments":
                                labelSpecialComments.setText("Special Comments: " + fieldValue);
                                break;
                            case "History ID":
                                labelHistoryID.setText("History ID: " + fieldValue);
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            labelWardNumber.setText("Ward Number: None" );
            labelMovementMeans.setText("Movement Means: None");
            labelAttendingPhysician.setText("Attending Physician: None");
            labelMajorComplications.setText("Major Complications: None");
            labelTreatmentResults.setText("Treatment Results: None");
            labelSpecialComments.setText("Special Comments: None");
            labelHistoryID.setText("History ID: None");
        }
    }


    public static void setTextField(Patient selectedPatient, TextField tfWardNumber, TextField tfMovementMeans,
                                    TextField tfAttendingPhysician, TextField tfMajorComplications,
                                    TextField tfTreatmentResults, TextField tfSpecialComments,
                                    TextField tfHistoryID) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Patient History.json";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Ward Number":
                                tfWardNumber.setText(fieldValue);
                                break;
                            case "Movement Means":
                                tfMovementMeans.setText(fieldValue);
                                break;
                            case "Attending Physician":
                                tfAttendingPhysician.setText(fieldValue);
                                break;
                            case "Major Complication":
                                tfMajorComplications.setText(fieldValue);
                                break;
                            case "Treatment Results":
                                tfTreatmentResults.setText(fieldValue);
                                break;
                            case "Special Comments":
                                tfSpecialComments.setText(fieldValue);
                                break;
                            case "History ID":
                                tfHistoryID.setText(fieldValue);
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deletePatientHistoryFile() {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Patient History.json";
        String filePath = folderPath + File.separator + filename;
        File patientHistory = new File(filePath);
        if (patientHistory.exists()) {
            patientHistory.delete();
        }
    }
}



