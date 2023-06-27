package Interfaces;

import Patient.Patient;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface MedicalHistory {

    public void displayMedicalHistory(Patient selectedPatient, Label labelFamilyHistory, Label labelAllergies,
                                             Label labelSmoking, Label labelAlcohol, Label labelTriageDetails,
                                             Label labelAdditionalComments);


    public void setTextField(Patient selectedPatient, TextField tfFamilyHistory, TextField tfAllergies,
                                    TextField tfSmoking, TextField tfAlcohol,
                                    TextField tfTriageDetails, TextField tfAdditionalComments);


    public void deleteMedicalHistoryFile();

}
