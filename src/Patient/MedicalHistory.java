package Patient;

import Controller.PatientPageController;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MedicalHistory {

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    public static void displayMedicalHistory(Patient selectedPatient, Label labelFamilyHistory, Label labelAllergies,
                                             Label labelSmoking, Label labelAlcohol, Label labelTriageDetails,
                                             Label labelAdditionalComments) {
        String folderPath = "MedicalHistory";
        String filename = selectedPatient.getPatientID() + " Medical History.txt";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineCount = 0;
                boolean familyHistoryDisplayed = false;
                boolean allergiesDisplayed = false;
                boolean smokingDisplayed = false;
                boolean alcoholDisplayed = false;
                boolean triageDetailsDisplayed = false;
                boolean additionalCommentsDisplayed = false;

                while ((line = reader.readLine()) != null && lineCount < 6) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Family History":
                                if (!familyHistoryDisplayed) {
                                    labelFamilyHistory.setText("Family History: " + fieldValue);
                                    familyHistoryDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Allergies":
                                if (!allergiesDisplayed) {
                                    labelAllergies.setText("Allergies: " + fieldValue);
                                    allergiesDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Smoking":
                                if (!smokingDisplayed) {
                                    labelSmoking.setText("Smoking: " + fieldValue);
                                    smokingDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Alcohol":
                                if (!alcoholDisplayed) {
                                    labelAlcohol.setText("Alcohol: " + fieldValue);
                                    alcoholDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Triage Details":
                                if (!triageDetailsDisplayed) {
                                    labelTriageDetails.setText("Triage Details: " + fieldValue);
                                    triageDetailsDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Additional Comments":
                                if (!additionalCommentsDisplayed) {
                                    labelAdditionalComments.setText("Additional Comments: " + fieldValue);
                                    additionalCommentsDisplayed = true;
                                    lineCount++;
                                }
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

    public void deleteMedicalHistoryFile() {
        String folderPath = "MedicalHistory";
        String filename = selectedPatient.getPatientID() + " Medical History.txt";
        String filePath = folderPath + File.separator + filename;
        File medicalHistory = new File(filePath);
        if (medicalHistory.exists()) {
            medicalHistory.delete();
        }
    }
}
