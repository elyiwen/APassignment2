package Patient;


import Controller.PatientPageController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Encounters {

    private String encounter;
    private String date;

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    public void setPatientHistory(String encounter, String date) {
        this.encounter = encounter;
        this.date = date;
    }

    public static void displayAllEncounters(Patient selectedPatient, VBox encounterVBox) {
        String folderPath = "Encounter";
        String filename = selectedPatient.getPatientID() + " Encounter.txt";
        String filePath = folderPath + File.separator + filename;
        File file = new File(filePath);

        if (file.exists()){
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Encounter: ")) {
                        String encounter = line.substring(11);
                        String date = reader.readLine().substring(6);

                        Label encounterLabel = new Label("Encounter: " + encounter + " - Date: " + date);
                        encounterLabel.setStyle("-fx-font-size: 12px");
                        VBox.setMargin(encounterLabel, new Insets(5));

                        encounterVBox.getChildren().add(encounterLabel);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getEncounter(){ return encounter; }

    public String getdate(){ return date; }
}

