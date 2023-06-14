package code.Scene;

import java.io.IOException;

import code.Patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PatientPageController {

    @FXML
    private Button btnAddPatient;

    @FXML
    private Button btnDisplay;

    public static Stage stage;
    public static Scene scene;

    @FXML
    void btnAddPatientClicked(ActionEvent event) throws IOException {
        stage = new Stage();
        scene = new Scene(FXMLLoader.load(getClass().getResource("PatientRegistrationForm.fxml")));
        stage.setTitle("Patient Registration Form");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnDisplayClicked(ActionEvent event) {
        Patient.displayPatientList();
    }
}
