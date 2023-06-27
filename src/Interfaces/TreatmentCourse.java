package Interfaces;

import Patient.Patient;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public interface TreatmentCourse {

    public void displayTreatmentCourse(Patient selectedPatient, Label labelTreatmentID, Label labelStartingDate,
                                             Label labelEndingDate, VBox diagnosisVBox, VBox analysisVBox, VBox procedureVBox,
                                              VBox medicationVBox);

    public void deleteTreatmentCourseFile();

    public void displayDiagnosis(String disease, String dateOfDiagnosis, VBox diagnosisVBox);

    public void displayAnalysis(String nameOfAnalysis, String contentOfAnalysis, String dateOfAnalysis, VBox analysisVBox);

    public void displayProcedure(String nameOfProcedure, String contentOfProcedure, String time, String dateOfProcedure, VBox procedureVBox);

    public void displayMedication(String nameOfMedication, String dosage, String comment, String frequency,
                                          String type, String startedDate, VBox medicationVBox);
}
