package code.Scene;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import code.Patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PatientFormController implements Initializable{

    @FXML
    private TextField tfPatientName;

    @FXML
    private TextField tfIdentityNo;

    @FXML
    private DatePicker dpDob;
    
    @FXML
    private TextField tfRace_Ethnicity;

    @FXML
    private ChoiceBox<String> chbPreferredLanguage;

    @FXML
    private Button btnSave;

    @FXML
    void btnSaveClicked(ActionEvent event) throws IOException {
 
        String patientName = tfPatientName.getText();
        String patientIdentityNo = tfIdentityNo.getText();
        LocalDate patientDoB = dpDob.getValue();
        String patientRace_Ethnicity = tfRace_Ethnicity.getText();

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
                // Add Patient 
                new Patient(patientName, patientIdentityNo, patientDoB, patientRace_Ethnicity);
                PatientPageController.scenePatientForm = new Scene(FXMLLoader.load(getClass().getResource("PatientForm.fxml")));
                PatientPageController.stagePatientForm.setScene(PatientPageController.scenePatientForm);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        
        chbPreferredLanguage.getItems().removeAll(chbPreferredLanguage.getItems());
        chbPreferredLanguage.getItems().addAll("English", "Bahasa Melayu", "Chinese");
    }
}
