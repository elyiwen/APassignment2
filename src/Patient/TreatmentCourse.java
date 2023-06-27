package Patient;

import Controller.PatientPageController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class TreatmentCourse {

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    public static void displayTreatmentCourse(Patient selectedPatient, Label labelTreatmentID, Label labelStartingDate,
                                             Label labelEndingDate, VBox diagnosisVBox, VBox analysisVBox, VBox procedureVBox,
                                              VBox medicationVBox) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Treatment Course.json";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                String disease = " ";
                String dateOfDiagnosis = " ";
                String nameOfAnalysis = " ";
                String contentOfAnalysis = " ";
                String dateOfAnalysis = " ";
                String nameOfProcedure = " ";
                String contentOfProcedure = " ";
                String time = " ";
                String dateOfProcedure = " ";
                String nameOfMedication = " ";
                String dosage = " ";
                String comment = " ";
                String frequency = " ";
                String type = " ";
                String startedDate = " ";
                
                String line = " ";
                boolean treatmentCourseIDDisplayed = false;
                boolean startingDateDisplayed = false;
                boolean endingDateDisplayed = false;
                boolean diseaseDisplayed = false;
                boolean dateOfDiagnosisDisplayed = false;
                boolean nameOfAnalysisDisplayed = false;
                boolean contentOfAnalysisDisplayed = false;
                boolean dateOfAnalysisDisplayed = false;
                boolean nameOfProcedureDisplayed = false;
                boolean contentOfProcedureDisplayed = false;
                boolean timeDisplayed = false;
                boolean dateOfProcedureDisplayed = false;
                boolean nameOfMedicationDisplayed = false;
                boolean dosageDisplayed = false;
                boolean commentDisplayed = false;
                boolean frequencyDisplayed = false;
                boolean typeDisplayed = false;
                boolean startedDateDisplayed = false;


                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Treatment Course ID":
                                if (!treatmentCourseIDDisplayed) {
                                    labelTreatmentID.setText("Treatment Course ID: " + fieldValue);
                                    treatmentCourseIDDisplayed = true;
                                }
                                break;

                            case "Starting Date":
                                if (!startingDateDisplayed) {
                                    labelStartingDate.setText("Starting Date: " + fieldValue);
                                    startingDateDisplayed = true;
                                }
                                break;
                            case "Ending Date":
                                if (!endingDateDisplayed) {
                                    labelEndingDate.setText("Ending Date: " + fieldValue);
                                    endingDateDisplayed = true;
                                }
                                break;
                            case "Disease":
                                if (!diseaseDisplayed) {
                                    disease = fieldValue;
                                    diseaseDisplayed = true;
                                }
                                break;
                            case "Date of Diagnosis":
                                if (!dateOfDiagnosisDisplayed) {
                                    dateOfDiagnosis = fieldValue;
                                    dateOfDiagnosisDisplayed = true;
                                }
                                break;
                            case "Name of Analysis":
                                if (!nameOfAnalysisDisplayed) {
                                    nameOfAnalysis = fieldValue;
                                    nameOfAnalysisDisplayed = true;
                                }
                                break;
                            case "Content Of Analysis":
                                if (!contentOfAnalysisDisplayed) {
                                    contentOfAnalysis = fieldValue;
                                    contentOfAnalysisDisplayed = true;
                                }
                                break;
                            case "Date of Analysis":
                                if (!dateOfAnalysisDisplayed) {
                                    dateOfAnalysis = fieldValue;
                                    dateOfAnalysisDisplayed = true;
                                }
                                break;
                            case "Name of Procedure":
                                if (!nameOfProcedureDisplayed) {
                                    nameOfProcedure = fieldValue;
                                    nameOfProcedureDisplayed = true;
                                }
                                break;
                            case "Content Of Procedure":
                                if (!contentOfProcedureDisplayed) {
                                    contentOfProcedure = fieldValue;
                                    contentOfProcedureDisplayed = true;
                                }
                                break;
                            case "Time":
                                if (!timeDisplayed) {
                                    time = fieldValue;
                                    timeDisplayed = true;
                                }
                                break;
                            case "Date of Procedure":
                                if (!dateOfProcedureDisplayed) {
                                    dateOfProcedure = fieldValue;
                                    dateOfProcedureDisplayed = true;
                                }
                                break;
                            case "Name of Medication":
                                if (!nameOfMedicationDisplayed) {
                                    nameOfMedication = fieldValue;
                                    nameOfMedicationDisplayed = true;
                                }
                                break;
                            case "Dosage":
                                if (!dosageDisplayed) {
                                    dosage = fieldValue;
                                    dosageDisplayed = true;
                                }
                                break;
                            case "Comment":
                                if (!commentDisplayed) {
                                    comment = fieldValue;
                                    commentDisplayed = true;
                                }
                                break;
                            case "Frequency":
                                if (!frequencyDisplayed) {
                                    frequency = fieldValue;
                                    frequencyDisplayed = true;
                                }
                                break;
                            case "C,DC,WH":
                                if (!typeDisplayed) {
                                    type = fieldValue;
                                    typeDisplayed = true;
                                }
                                break;
                            case "Started Date":
                                if (!startedDateDisplayed) {
                                    startedDate = fieldValue;
                                    startedDateDisplayed = true;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
                displayDiagnosis(disease, dateOfDiagnosis, diagnosisVBox);
                displayAnalysis(nameOfAnalysis, contentOfAnalysis, dateOfAnalysis, analysisVBox);
                displayProcedure(nameOfProcedure, contentOfProcedure, time, dateOfProcedure, procedureVBox);
                displayMedication(nameOfMedication, dosage, comment, frequency, type, startedDate, medicationVBox);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
        labelTreatmentID.setText("Ward Number: None" );
        labelStartingDate.setText("Movement Means: None");
        labelEndingDate.setText("Attending Physician: None");
        }
    }

    public void deleteTreatmentCourseFile() {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Treatment Course.json";
        String filePath = folderPath + File.separator + filename;
        File treatmentCourse = new File(filePath);
        if (treatmentCourse.exists()) {
            treatmentCourse.delete();
        }
    }

    private static void displayDiagnosis(String disease, String dateOfDiagnosis, VBox diagnosisVBox){
        Label diagnosisLabel = new Label("Disease : " + disease + "\n" +
                "Date of Diagnosis : " + dateOfDiagnosis );
        diagnosisLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(diagnosisLabel, new Insets(5));
        diagnosisVBox.getChildren().add(diagnosisLabel);
    }

    private static void displayAnalysis(String nameOfAnalysis, String contentOfAnalysis, String dateOfAnalysis, VBox analysisVBox){
        Label analysisLabel = new Label(nameOfAnalysis + "\n" +
                contentOfAnalysis + "\n" +
                "Date of Analysis : " + dateOfAnalysis );
        analysisLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(analysisLabel, new Insets(5));
        analysisVBox.getChildren().add(analysisLabel);
    }

    private static void displayProcedure(String nameOfProcedure, String contentOfProcedure, String time, String dateOfProcedure, VBox procedureVBox){
        Label procedureLabel = new Label(nameOfProcedure + ": " + time + " - " + dateOfProcedure + "\n" +
                "> " +contentOfProcedure);
        procedureLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(procedureLabel, new Insets(5));
        procedureVBox.getChildren().add(procedureLabel);
    }

    private static void displayMedication(String nameOfMedication, String dosage, String comment, String frequency,
                                          String type, String startedDate, VBox medicationVBox){
        Label medicationLabel = new Label("Name of Medication : " + nameOfMedication + "\n" +
                "Dosage : " + dosage + "\n" +
                "Comment : " + comment + "\n" +
                "Frequency : " + frequency + "\n" +
                "C,DC,WH : " + type + "\n" +
                "Started Date : " + startedDate);
        medicationLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(medicationLabel, new Insets(5));
        medicationVBox.getChildren().add(medicationLabel);
    }
}
