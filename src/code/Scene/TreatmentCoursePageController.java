package code.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import code.Patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TreatmentCoursePageController implements Initializable{

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    @FXML
    private Label labelPatientID;

    @FXML
    private Label labelPatientName;

    @FXML
    private Button btnBack;

    @FXML
    void btnBackClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        Parent root = loader.load();

        MainSceneController mainSceneController = loader.getController();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainSceneController.switchScene("PatientPage");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        labelPatientID.setText(selectedPatient.getPatientID());
        labelPatientName.setText(selectedPatient.getPatientName());
    }

}
