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

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                StringBuilder encounterBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("-----Encounter")) {
                        if (encounterBuilder.length() > 0) {
                            insertEncounterLabels(encounterBuilder.toString(), encounterVBox);
                            encounterBuilder.setLength(0);
                        }
                        encounterBuilder.append(line).append("\n");
                    } else {
                        encounterBuilder.append(line).append("\n");
                    }
                }
                if (encounterBuilder.length() > 0) {
                    insertEncounterLabels(encounterBuilder.toString(), encounterVBox);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertEncounterLabels(String encounterContent, VBox encounterVBox) {
        String[] lines = encounterContent.split("\n");

        String encounter = lines[1].substring(11);
        String date = lines[2].substring(6);

        Label encounterLabel = new Label(encounter + "     " + date);
        encounterLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(encounterLabel, new Insets(5));

        encounterVBox.getChildren().add(encounterLabel);
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

