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
        String folderPath = "Event";
        String filename = selectedPatient.getPatientID() + " Event.txt";
        String filePath = folderPath + File.separator + filename;
        File file = new File(filePath);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Event: ")) {
                        String event = line.substring(7);
                        String time = reader.readLine().substring(6);
                        String date = reader.readLine().substring(6);

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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteEventFile() {
        String folderPath = "Event";
        String filename = selectedPatient.getPatientID() + " Event.txt";
        String filePath = folderPath + File.separator + filename;
        File event = new File(filePath);
        if (event.exists()) {
            event.delete();
        }
    }
}
