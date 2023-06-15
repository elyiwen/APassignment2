package code.Scene;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AppointmentController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField timeTextField;

    @FXML
    public void makeAppointment() {
        String name = nameTextField.getText();
        String id = idTextField.getText();
        String date = datePicker.getValue().toString();
        String time = timeTextField.getText();

        String appointmentDetails = "Name: " + name + "\n" +
                "ID: " + id + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Schedule/appointments.txt", true))) {
            writer.write(appointmentDetails);
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Appointment saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving appointment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
