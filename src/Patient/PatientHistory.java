package Patient;

import javax.swing.*;
import javafx.scene.control.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PatientHistory {

    private String wardNumber;
    private String movementMeans;
    private String attendingPhysician;
    private String majorComplications;
    private String treatmentResults;
    private String specialComments;
    private String historyID;


    public void setPatientHistory(String wardNumber, String movementMeans, String attendingPhysician, String majorComplications, String treatmentResults, String specialComments, String historyID){
        this.wardNumber = wardNumber;
        this.movementMeans = movementMeans;
        this.attendingPhysician = attendingPhysician;
        this.majorComplications = majorComplications;
        this.treatmentResults = treatmentResults;
        this.specialComments = specialComments;
        this.historyID = historyID;
    }

    public static void readPatientHistory(Patient selectedPatient, Label labelWardNumber, Label labelMovementMeans, Label labelAttendingPhysician, Label labelMajorComplications, Label labelTreatmentResults, Label labelSpecialComments, Label labelHistoryID) {
        String folderPath = "PatientHistory";
        String filename = selectedPatient.getPatientID() + " History.txt";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                int lineCount = 0;
                boolean wardNumberDisplayed = false;
                boolean movementMeansDisplayed = false;
                boolean attendingPhysicianDisplayed = false;
                boolean majorComplicationDisplayed = false;
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
                                if (!majorComplicationDisplayed) {
                                    labelMajorComplications.setText("Major Complications: " + fieldValue);
                                    majorComplicationDisplayed = true;
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

                reader.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public String getWardNumber(){ return wardNumber; }

    public String getMovementMeans(){ return movementMeans; }

    public String getAttendingPhysician(){ return attendingPhysician; }

    public String getMajorComplications(){ return majorComplications; }

    public String getTreatmentResults(){ return treatmentResults; }

    public String getSpecialComments(){ return specialComments; }

    public String getHistoryID(){ return historyID; }
}
