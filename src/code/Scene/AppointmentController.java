package code.Scene;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> timeComboBox;  // Changed to a ComboBox

    // Populate the timeComboBox options
    @FXML
    public void initialize() {
        timeComboBox.getItems().addAll("1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM",
                "5:00 AM", "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM","5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM",
                "9:00 PM", "10:00 PM", "11:00 PM", "12:00 AM" );
    }

    @FXML
    public void makeAppointment() {
        String name = nameTextField.getText();
        String id = idTextField.getText();
        String date = datePicker.getValue().toString();
        String time = timeComboBox.getValue();  // Retrieve selected time from ComboBox

        String appointmentDetails = "Name: " + name + "\n" +
                "ID: " + id + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/code/Schedule/appointments.txt", true))) {
            writer.write(appointmentDetails);
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Appointment saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving appointment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}