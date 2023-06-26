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

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    public static void displayAllEncounters(Patient selectedPatient, VBox encounterVBox) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Encounter.json";
        String filePath = folderPath + File.separator + filename;
        File file = new File(filePath);

        if (file.exists()){
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Encounter: ")) {
                        String encounter = line.substring(11);
                        String date = reader.readLine().substring(6);

                        Label encounterLabel = new Label(encounter + "     " + date);
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

    public void deleteEncountersFile() {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Encounter.json";
        String filePath = folderPath + File.separator + filename;
        File encounter = new File(filePath);
        if (encounter.exists()) {
            encounter.delete();
        }
    }
}

