package Patient;

import Controller.PatientPageController;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TreatmentCourse {

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    public static void displayTreatmentCourse(Patient selectedPatient, Label labelTreatmentID, Label labelStartingDate,
                                             Label labelEndingDate) {
        /*
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Treatment Course.json";
        String filePath = folderPath + File.separator + filename;
        */

        labelTreatmentID.setText("Ward Number: None" );
        labelStartingDate.setText("Movement Means: None");
        labelEndingDate.setText("Attending Physician: None");

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
}
