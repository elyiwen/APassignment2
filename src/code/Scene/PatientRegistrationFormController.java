package code.Scene;

import java.io.IOException;
import java.util.ArrayList;

import code.Patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PatientRegistrationFormController {

    @FXML
    private TextField tfPatientID;

    @FXML
    private TextField tfPatientName;

    @FXML
    private Button btnRegister;

    private static ArrayList<Patient> patientList = Patient.getPatientList();

    @FXML
    void btnRegisterClicked(ActionEvent event) throws IOException {
        String patientID = tfPatientID.getText();
        String patientName = tfPatientName.getText();
        if (patientID.isEmpty() || patientName.isEmpty()){
            Alert alertisEmpty = new Alert(AlertType.INFORMATION, "Please Fill In Required Details", ButtonType.OK);
            alertisEmpty.setHeaderText("NOTIFICATION");
            alertisEmpty.setTitle("ALERT");
            alertisEmpty.showAndWait().get();
        }
        else{
            Alert alertExit = new Alert(AlertType.INFORMATION, "Confirm Patient Detail?", ButtonType.YES, ButtonType.NO);
            alertExit.setHeaderText("NOTIFICATION");
            alertExit.setTitle("ALERT");

            if(alertExit.showAndWait().get() == ButtonType.YES){
                new Patient(patientID, patientName);
                PatientPageController.stage.close();
            }
        }
    }
}
