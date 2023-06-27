package Interfaces;

import Patient.Patient;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface PatientHistory {

    public void displayPatientHistory(Patient selectedPatient, Label labelWardNumber, Label labelMovementMeans,
                                             Label labelAttendingPhysician, Label labelMajorComplications,
                                             Label labelTreatmentResults, Label labelSpecialComments,
                                             Label labelHistoryID);


    public void setTextField(Patient selectedPatient, TextField tfWardNumber, TextField tfMovementMeans,
                                    TextField tfAttendingPhysician, TextField tfMajorComplications,
                                    TextField tfTreatmentResults, TextField tfSpecialComments,
                                    TextField tfHistoryID);

    public void deletePatientHistoryFile();

}



