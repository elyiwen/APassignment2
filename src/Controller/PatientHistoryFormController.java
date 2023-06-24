package Controller;

import Patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PatientHistoryFormController implements Initializable{

    @FXML
    private TextField tfWardNumber;

    @FXML
    private TextField tfMovementMeans;

    @FXML
    private TextField tfAttendingPhysician;

    @FXML
    private TextField tfMajorComplication;

    @FXML
    private TextField tfTreatmentResults;

    @FXML
    private TextField tfSpecialComments;

    @FXML
    private TextField tfHistoryID;

    private Patient selectedPatient = PatientPageController.getSelectedPatient();


    @FXML
    void btnSaveClicked(ActionEvent event) throws IOException {
        String wardNumber = tfWardNumber.getText();
        String movementMeans =tfMovementMeans.getText();
        String attendingPhysician =tfAttendingPhysician.getText();
        String majorComplication =tfMajorComplication.getText();
        String treatmentResults =tfTreatmentResults.getText();
        String specialComments =tfSpecialComments.getText();
        String historyID =tfHistoryID.getText();

        selectedPatient.setPatientHistory(wardNumber, movementMeans, attendingPhysician, majorComplication, treatmentResults, specialComments, historyID);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
