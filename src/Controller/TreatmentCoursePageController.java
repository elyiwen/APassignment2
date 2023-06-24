package Controller;

import Patient.Patient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TreatmentCoursePageController implements Initializable{

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    @FXML
    private Label labelPatientID;

    @FXML
    private Label labelPatientName;

    @FXML
    private Label labelPatientAge;

    @FXML
    private Label labelPatientDoB;

    @FXML
    private Label labelRace_Ethnicity;

    @FXML
    private Label labelGender;

    @FXML
    private Label labelPrefLanguage;

    @FXML
    private Label labelMaritalStatus;

    @FXML
    private Label labelAddress;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelContactNo;

    @FXML
    private Label labeEmergency;

    @FXML
    private Label labelWardNumber;

    @FXML
    private Label labelMovementMeans;

    @FXML
    private Label labelAttendingPhysician;

    @FXML
    private Label labelMajorComplications;

    @FXML
    private Label labelTreatmentResults;

    @FXML
    private Label labelSpecialComments;

    @FXML
    private Label labelHistoryID;

    @FXML
    private Button btnEditPatientHistory;

    @FXML
    private Button btnBack;

    public static Stage stagePatientHistoryForm;
    public static Scene scenePatientHistoryForm;

    @FXML
    void btnBackClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene/MainScene.fxml"));
        Parent root = loader.load();

        MainSceneController mainSceneController = loader.getController();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainSceneController.switchScene("PatientPage");
    }

    @FXML
    void btnRefreshClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene/MainScene.fxml"));
        Parent root = loader.load();

        MainSceneController mainSceneController = loader.getController();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainSceneController.switchScene("TreatmentCoursePage");
    }

    @FXML
    void btnEditPatientHistoryClicked(ActionEvent event) throws IOException{
        stagePatientHistoryForm = new Stage();
        scenePatientHistoryForm = new Scene(FXMLLoader.load(getClass().getResource("/Scene/PatientHistoryForm.fxml")));
        stagePatientHistoryForm.setTitle("Patient History Form");
        stagePatientHistoryForm.setScene(scenePatientHistoryForm);
        stagePatientHistoryForm.setResizable(false);
        stagePatientHistoryForm.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        labelPatientID.setText("Patient ID: " + selectedPatient.getPatientID());
        labelPatientName.setText("Patient Name: " + selectedPatient.getPatientName());
        labelPatientAge.setText("Age: " + selectedPatient.getPatientAge());
        labelPatientDoB.setText("DoB: " + selectedPatient.getPatientDoB().toString());
        labelRace_Ethnicity.setText("Race/Ethnicity: " + selectedPatient.getRace_Ethinicity());
        labelGender.setText("Gender: " + selectedPatient.getGender());
        labelPrefLanguage.setText("Preferred Language: " + selectedPatient.getPrefLanguage());
        labelMaritalStatus.setText("Marital Status: " + selectedPatient.getMaritalStatus());
        labelAddress.setText("Address: " + "\n" + selectedPatient.getAddress());
        labelEmail.setText("Email: " + selectedPatient.getPatientEmail());
        labelContactNo.setText("Contact No: " + selectedPatient.getPatientContactNo());
        labeEmergency.setText("Emergency Info: " + "\n" + selectedPatient.getEmergencyInfo());
        labelWardNumber.setText("Ward Number: " + selectedPatient.getWardNumber());
        labelMovementMeans.setText("Movement Means: " + selectedPatient.getMovementMeans());
        labelAttendingPhysician.setText("Attending Physician: " + selectedPatient.getAttendingPhysician());
        labelMajorComplications.setText("Major Complications: " + selectedPatient.getMajorComplications());
        labelTreatmentResults.setText("Treatment Results: " + selectedPatient.getTreatmentResults());
        labelSpecialComments.setText("Special Comments: " + selectedPatient.getSpecialComments());
        labelHistoryID.setText("History ID: " + selectedPatient.getHistoryID());
    }

}
