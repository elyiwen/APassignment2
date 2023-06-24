package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private ScrollPane todaysAppointmentScrollPane;

    @FXML
    private VBox todaysAppointmentVBox;

    private void displayTodayAppointments() {
        LocalDate today = LocalDate.now();

        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Date: ")) {
                    String date = line.substring(6);
                    String name = reader.readLine().substring(6);
                    String id = reader.readLine().substring(4);
                    String time = reader.readLine().substring(6);

                    if (date.equals(today.toString())) {
                        Label appointmentLabel = new Label("Name: " + name + " - ID: " + id + " - Time: " + time);
                        appointmentLabel.setStyle("-fx-font-size: 12px");
                        VBox.setMargin(appointmentLabel, new Insets(5));

                        todaysAppointmentVBox.getChildren().add(appointmentLabel);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayTodayAppointments();
    }
}



