package Interfaces;

import Patient.Patient;
import javafx.scene.layout.VBox;

public interface Events {


    public void displayAllEvents(Patient selectedPatient, VBox eventVBox);

    public void insertEventLabels(String eventContent, VBox eventVBox);

    public void deleteEventFile();
}
