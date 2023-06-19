package code.Scene;

import java.io.IOException;
import java.util.Random;

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

public class PatientFormController{

    @FXML
    private TextField tfPatientName;

    @FXML
    private TextField tfIdentityNo;

    @FXML
    private Button btnSave;

    @FXML
    void btnSaveClicked(ActionEvent event) throws IOException {

        // 
        String patientName = tfPatientName.getText();
        String patientIdentityNo = tfIdentityNo.getText();

        if (patientIdentityNo.isEmpty() || patientName.isEmpty()){

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
                // Generate patientID
                Random rand = new Random();
                String random = Integer.toString(rand.nextInt(1000)+1000);
                String idFirst = "P";
                char[] patientNameChar = patientName.toCharArray();
                String idMid = patientNameChar[0] + "-" + patientNameChar[1] + random;
                String idLast = patientIdentityNo.substring(patientIdentityNo.length() - 4);
                String patientID = idFirst + idMid.toUpperCase() + idLast;

                // Add Patient 
                new Patient(patientID, patientName);
                PatientPageController.scenePatientForm = new Scene(FXMLLoader.load(getClass().getResource("PatientForm.fxml")));
                PatientPageController.stagePatientForm.setScene(PatientPageController.scenePatientForm);
            }
        }
    }
}
