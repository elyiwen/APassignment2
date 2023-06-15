package code.Scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MainSceneController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnDoc;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPatient;

    @FXML
    private Button btnSchedule;



    @FXML
    void btnAdminClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminPage" + ".fxml")); 
        borderPane.setCenter(root);
    }

    @FXML
    void btnDocClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DocPage" + ".fxml"));
        borderPane.setCenter(root); 
    }

    @FXML
    void btnHomeClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePage" + ".fxml")); 
        borderPane.setCenter(root);
    }

    @FXML
    void btnPatientClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PatientPage" + ".fxml"));
        borderPane.setCenter(root); 
    }

    @FXML
    void btnScheduleClicked(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("Calendar" + ".fxml")); 
        Parent root2 = FXMLLoader.load(getClass().getResource("Appointment" + ".fxml")); 
        borderPane.setLeft(root1);
        borderPane.setCenter(root2);
    }
}
