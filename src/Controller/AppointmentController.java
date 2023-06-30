package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class AppointmentController implements Initializable {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> timeComboBox;

    @FXML
    public void makeAppointment() {  //A method for scheduling an appointment for the exact day
        LocalDate selectedDate = datePicker.getValue();
        String name = nameTextField.getText();
        String id = idTextField.getText();
        String time = timeComboBox.getValue();

        if (selectedDate != null && !name.isEmpty() && !id.isEmpty() && time != null) {
            String date = datePicker.getValue().toString();

            String appointmentDetails = "Date: " + date + "\n" +
                    "Name: " + name + "\n" +
                    "ID: " + id + "\n" +
                    "Time: " + time + "\n";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("File/appointments.txt", true))) {
                writer.write(appointmentDetails);
                JOptionPane.showMessageDialog(null, "Appointment saved successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving appointment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
        else {
            JOptionPane.showMessageDialog(null, "Please enter valid values for all fields.", "Invalid Values", JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    public void editAppointments() {  //Open the txt file to edit the appointment
        try {
            Desktop.getDesktop().edit(new File("File/appointments.txt"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error opening appointments file for editing: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeComboBox.getItems().addAll("1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM",
                "5:00 AM", "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM",
                "9:00 PM", "10:00 PM", "11:00 PM", "12:00 AM");
    }
}




