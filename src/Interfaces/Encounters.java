package Interfaces;

import Patient.Patient;
import javafx.scene.layout.VBox;


public interface Encounters {

    public void displayAllEncounters(Patient selectedPatient, VBox encounterVBox);

    public void insertEncounterLabels(String encounterContent, VBox encounterVBox);

    public void deleteEncountersFile();
}

