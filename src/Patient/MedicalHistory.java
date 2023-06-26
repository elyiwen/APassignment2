package Patient;

import Controller.PatientPageController;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MedicalHistory {

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    public static void displayMedicalHistory(Patient selectedPatient, Label labelFamilyHistory, Label labelAllergies,
                                             Label labelSmoking, Label labelAlcohol, Label labelTriageDetails,
                                             Label labelAdditionalComments) {
        String folderPath = "MedicalHistory";
        String filename = selectedPatient.getPatientID() + " Medical History.json";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineCount = 0;

                while ((line = reader.readLine()) != null && lineCount < 6) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Family History":
                                labelFamilyHistory.setText("Family History: " + fieldValue);
                                lineCount++;
                                break;
                            case "Allergies":
                                labelAllergies.setText("Allergies: " + fieldValue);
                                lineCount++;
                                break;
                            case "Smoking":
                                labelSmoking.setText("Smoking: " + fieldValue);
                                lineCount++;
                                break;
                            case "Alcohol":
                                labelAlcohol.setText("Alcohol: " + fieldValue);
                                lineCount++;
                                break;
                            case "Triage Details":
                                labelTriageDetails.setText("Triage Details: " + fieldValue);
                                lineCount++;
                                break;
                            case "Additional Comments":
                                labelAdditionalComments.setText("Additional Comments: " + fieldValue);
                                lineCount++;
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            labelFamilyHistory.setText("Family History: None");
            labelAllergies.setText("Allergies: None");
            labelSmoking.setText("Smoking: None");
            labelAlcohol.setText("Alcohol: None");
            labelTriageDetails.setText("Triage Details: None");
            labelAdditionalComments.setText("Additional Comments: None");
        }
    }

    public void deleteMedicalHistoryFile() {
        String folderPath = "MedicalHistory";
        String filename = selectedPatient.getPatientID() + " Medical History.json";
        String filePath = folderPath + File.separator + filename;
        File medicalHistory = new File(filePath);
        if (medicalHistory.exists()) {
            medicalHistory.delete();
        }
    }

    public static void setTextField(Patient selectedPatient, TextField tfFamilyHistory, TextField tfAllergies,
                                    TextField tfSmoking, TextField tfAlcohol,
                                    TextField tfTriageDetails, TextField tfAdditionalComments) {
        String folderPath = "MedicalHistory";
        String filename = selectedPatient.getPatientID() + " Medical History.json";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineCount = 0;

                while ((line = reader.readLine()) != null && lineCount < 6) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Family History":
                                tfFamilyHistory.setText(fieldValue);
                                lineCount++;
                                break;
                            case "Allergies":
                                tfAllergies.setText(fieldValue);
                                lineCount++;
                                break;
                            case "Smoking":
                                tfSmoking.setText(fieldValue);
                                lineCount++;
                                break;
                            case "Alcohol":
                                tfAlcohol.setText(fieldValue);
                                lineCount++;
                                break;
                            case "Triage Details":
                                tfTriageDetails.setText(fieldValue);
                                lineCount++;
                                break;
                            case "Additional Comments":
                                tfAdditionalComments.setText(fieldValue);
                                lineCount++;
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
}
