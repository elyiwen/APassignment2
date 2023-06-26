package Patient;

import Controller.PatientPageController;
import javafx.scene.control.Label;
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
        String folderPath = "PatientHistory";
        String filename = selectedPatient.getPatientID() + " Patient History.txt";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineCount = 0;
                boolean wardNumberDisplayed = false;
                boolean movementMeansDisplayed = false;
                boolean attendingPhysicianDisplayed = false;
                boolean majorComplicationsDisplayed = false;
                boolean treatmentResultsDisplayed = false;
                boolean specialCommentsDisplayed = false;
                boolean historyIDDisplayed = false;

                while ((line = reader.readLine()) != null && lineCount < 7) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Ward Number":
                                if (!wardNumberDisplayed) {
                                    labelWardNumber.setText("Ward Number: " + fieldValue);
                                    wardNumberDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Movement Means":
                                if (!movementMeansDisplayed) {
                                    labelMovementMeans.setText("Movement Means: " + fieldValue);
                                    movementMeansDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Attending Physician":
                                if (!attendingPhysicianDisplayed) {
                                    labelAttendingPhysician.setText("Attending Physician: " + fieldValue);
                                    attendingPhysicianDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Major Complication":
                                if (!majorComplicationsDisplayed) {
                                    labelMajorComplications.setText("Major Complications: " + fieldValue);
                                    majorComplicationsDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Treatment Results":
                                if (!treatmentResultsDisplayed) {
                                    labelTreatmentResults.setText("Treatment Results: " + fieldValue);
                                    treatmentResultsDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "Special Comments":
                                if (!specialCommentsDisplayed) {
                                    labelSpecialComments.setText("Special Comments: " + fieldValue);
                                    specialCommentsDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            case "History ID":
                                if (!historyIDDisplayed) {
                                    labelHistoryID.setText("History ID: " + fieldValue);
                                    historyIDDisplayed = true;
                                    lineCount++;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file: " + e.getMessage());
            }
        }
    }

    public void deletePatientHistoryFile() {
        String folderPath = "PatientHistory";
        String filename = selectedPatient.getPatientID() + " Patient History.txt";
        String filePath = folderPath + File.separator + filename;
        File patientHistory = new File(filePath);
        if (patientHistory.exists()) {
            patientHistory.delete();
        }
    }
}



