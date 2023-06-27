package Patient;

import Controller.PatientPageController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Events {

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    public static void displayAllEvents(Patient selectedPatient, VBox eventVBox) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Event.json";
        String filePath = folderPath + File.separator + filename;
        File file = new File(filePath);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                StringBuilder eventBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("-----Event")) {
                        if (eventBuilder.length() > 0) {
                            insertEventLabels(eventBuilder.toString(), eventVBox);
                            eventBuilder.setLength(0);
                        }
                        eventBuilder.append(line).append("\n");
                    } else {
                        eventBuilder.append(line).append("\n");
                    }
                }
                if (eventBuilder.length() > 0) {
                    insertEventLabels(eventBuilder.toString(), eventVBox);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertEventLabels(String eventContent, VBox eventVBox) {
        String[] lines = eventContent.split("\n");

        String event = lines[1].substring(7);
        String time = lines[2].substring(6);
        String date = lines[3].substring(6);

        Label timeDateLabel = new Label(time + " - " + date);
        timeDateLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(timeDateLabel, new Insets(5));

        Label eventLabel = new Label(event);
        eventLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(eventLabel, new Insets(5));

        VBox eventEntryVBox = new VBox();
        eventEntryVBox.getChildren().addAll(timeDateLabel, eventLabel);
        eventVBox.getChildren().add(eventEntryVBox);
    }

    public void deleteEventFile() {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Event.json";
        String filePath = folderPath + File.separator + filename;
        File event = new File(filePath);
        if (event.exists()) {
            event.delete();
        }
    }
}
