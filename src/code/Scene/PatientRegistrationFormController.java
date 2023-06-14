package code.Scene;

import java.io.IOException;
import java.util.ArrayList;

import code.Patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PatientRegistrationFormController{

    @FXML
    private TextField tfPatientID;

    @FXML
    private TextField tfPatientName;

    @FXML
    private Button btnRegister;

    private static Patient newPatient;
    private static ArrayList<Patient> newPatientList = new ArrayList<Patient>();

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
                Patient newPatient = new Patient(patientID, patientName);
                setNewPatient(newPatient);
                newPatientList.add(newPatient);
                PatientPageController.scene = new Scene(FXMLLoader.load(getClass().getResource("PatientRegistrationForm.fxml")));
                PatientPageController.stage.setScene(PatientPageController.scene);
            }
        }
    }
    public void setNewPatient(Patient newPatient){
        PatientRegistrationFormController.newPatient = newPatient;
    }
    public static Patient getNewPatient(){
        return newPatient;
    }
    public static ArrayList<Patient> getNewPatientList(){
        return newPatientList;
    }
}
