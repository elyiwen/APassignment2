package Patient;

import javafx.scene.control.Label;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MedicalHistory {

    private String familyHistory;
    private String allergies;
    private String smoking;
    private String alcohol;
    private String triageDetails;
    private String additionalComments;



    public void setPatientHistory(String familyHistory, String allergies, String smoking, String alcohol, String triageDetails, String additionalComments){
        this.familyHistory = familyHistory;
        this.allergies = allergies;
        this.smoking = smoking;
        this.alcohol = alcohol;
        this.triageDetails = triageDetails;
        this.additionalComments = additionalComments;
    }

    public static void readMedicalHistory(Patient selectedPatient, Label labelFamilyHistory, Label labelAllergies, Label labelSmoking, Label labelAlcohol, Label labelTriageDetails, Label labelAdditionalComments) {
        String folderPath = "MedicalHistory";
        String filename = selectedPatient.getPatientID() + " History.txt";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
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
                                if (!triageDetailsDisplayed) {
                                    labelAlcohol.setText("Alcohol: " + fieldValue);
                                    triageDetailsDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Triage Details":
                                if (!alcoholDisplayed) {
                                    labelTriageDetails.setText("Triage Details: " + fieldValue);
                                    alcoholDisplayed = true;
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

                reader.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public String getFamilyHistory(){ return familyHistory; }

    public String getMovementMeans(){ return allergies; }

    public String getAttendingPhysician(){ return smoking; }

    public String getAlcohol(){ return alcohol; }

    public String getTriageDetails(){ return triageDetails; }

    public String getAdditionalComments(){ return additionalComments; }

}
