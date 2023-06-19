package code.Scene;

import java.io.IOException;
import java.util.ResourceBundle;

import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class MainSceneController implements Initializable{

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label labelDateTime;

    @FXML
    private ComboBox<String> cbProfile;

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

    @Override
    public void initialize(URL location, ResourceBundle resources){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss \t  dd/MM/yyyy");
        Date date = new Date();
        labelDateTime.setText(formatter.format(date));

        cbProfile.setPromptText("Profile");
        cbProfile.getItems().add("Log Out");
    }

    @FXML
    void cbProfileClicked(ActionEvent event){

    }

    @FXML
    void btnAdminClicked(ActionEvent event) throws IOException {
        refreshPane();
        Parent root = FXMLLoader.load(getClass().getResource("AdminPage" + ".fxml")); 
        borderPane.setCenter(root);
    }

    @FXML
    void btnDocClicked(ActionEvent event) throws IOException {
        refreshPane();
        Parent root = FXMLLoader.load(getClass().getResource("DocPage" + ".fxml"));
        borderPane.setCenter(root); 
    }

    @FXML
    void btnHomeClicked(ActionEvent event) throws IOException {
        refreshPane();
        Parent root = FXMLLoader.load(getClass().getResource("HomePage" + ".fxml")); 
        borderPane.setCenter(root);
    }

    @FXML
    void btnPatientClicked(ActionEvent event) throws IOException {
        refreshPane();
        Parent root = FXMLLoader.load(getClass().getResource("PatientPage" + ".fxml"));
        borderPane.setCenter(root); 
    }

    @FXML
    void btnScheduleClicked(ActionEvent event) {
        refreshPane();
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
            Parent root2 = FXMLLoader.load(getClass().getResource("Appointment.fxml"));
            borderPane.setLeft(root1);
            borderPane.setCenter(root2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void refreshPane(){
        borderPane.setCenter(null);
        borderPane.setRight(null);
        borderPane.setTop(null);
        borderPane.setLeft(null);
        borderPane.setBottom(null);
    }
}
