package code.Scene;

import java.io.IOException;
import java.util.ResourceBundle;

import code.Patient.Patient;

import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


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

        cbProfile.getItems().removeAll(cbProfile.getItems());
        cbProfile.getItems().addAll("Profile", "Log Out");
        cbProfile.getSelectionModel().select("Profile");
    }

    @FXML
    void cbProfileClicked(ActionEvent event) throws IOException{
        if (cbProfile.getValue().equals("Log Out")){
            cbProfile.getSelectionModel().select("Profile");
            Alert alertLogOut = new Alert(AlertType.CONFIRMATION, "Are you sure to Log Out. The progress will be save", ButtonType.YES, ButtonType.NO);
            alertLogOut.setHeaderText("NOTIFICATION");
            alertLogOut.setTitle("ALERT");
            if (alertLogOut.showAndWait().get() == ButtonType.YES){
                Patient.writeRecord();
                Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
                Scene loginScene = new Scene(root);
                primaryStage.setResizable(false);
                primaryStage.setMaximized(false);
                primaryStage.setTitle("Taylor's EMR");
                primaryStage.setScene(loginScene);
                primaryStage.show();
            }
        }
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
