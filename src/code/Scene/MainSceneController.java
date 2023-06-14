package code.Scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
        loadPage("AdminPage");
    }

    @FXML
    void btnDocClicked(ActionEvent event) throws IOException {
        loadPage("DocPage");
    }

    @FXML
    void btnHomeClicked(ActionEvent event) throws IOException {
        loadPage("HomePage");
    }

    @FXML
    void btnPatientClicked(ActionEvent event) throws IOException {
        loadPage("PatientPage");
    }

    @FXML
    void btnScheduleClicked(ActionEvent event) throws IOException {
        loadPage("Calendar");   
    }

    public void loadPage(String page) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        borderPane.setCenter(root);
    }
}
