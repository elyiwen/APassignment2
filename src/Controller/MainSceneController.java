package Controller;

import java.io.IOException;
import java.util.ResourceBundle;
import code.Clinician;
import Patient.Patient;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class MainSceneController implements Initializable{

    @FXML
    public BorderPane borderPane;

    @FXML
    private ComboBox<String> cbProfile;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPatient;

    @FXML
    private Button btnSchedule;

    @FXML
    private ImageView imageIcon;

    private Clinician user = LoginSceneController.getUser();

    @FXML
    void cbProfileClicked(ActionEvent event) throws IOException{
        // Log Out Method
        if (cbProfile.getValue().equals("Log Out")){

            cbProfile.getSelectionModel().select(user.getClinicianID());
            
            // Show Confirmation Message before Log Out
            Alert alertLogOut = new Alert(AlertType.CONFIRMATION, "Are you sure to Log Out. The progress will be save", ButtonType.YES, ButtonType.NO);
            alertLogOut.setHeaderText("NOTIFICATION");
            alertLogOut.setTitle("ALERT");

            if (alertLogOut.showAndWait().get() == ButtonType.YES){

                // Load Login Page
                Patient.getPatientList().clear();
                Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/Scene/LoginScene.fxml"));
                Scene loginScene = new Scene(root);
                primaryStage.setMaximized(false);
                primaryStage.setScene(loginScene);
                primaryStage.show();

                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
                primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2.5);
            }
        }
    }
    
    @FXML
    void btnHomeClicked(ActionEvent event) throws IOException {
        switchScene("HomePage");
    }

    @FXML
    void btnPatientClicked(ActionEvent event) throws IOException {
        switchScene("PatientPage");
    }

    @FXML
    void btnScheduleClicked(ActionEvent event) throws IOException {
        refreshPane();
        Parent root1 = FXMLLoader.load(getClass().getResource("/Scene/Calendar.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("/Scene/Appointment.fxml"));
        borderPane.setLeft(root1);
        borderPane.setCenter(root2);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){  

        Image img = new Image("/Image/" + user.getAccountType() + "Icon.png");
        imageIcon.setImage(img);

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Scene/" + "HomePage" + ".fxml"));
            borderPane.setCenter(root); 
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        cbProfile.getItems().removeAll(cbProfile.getItems());
        cbProfile.getItems().addAll(user.getClinicianID(), "Log Out");
        cbProfile.setPromptText(user.getClinicianID());
    }

    public void refreshPane(){
        borderPane.setCenter(null);
        borderPane.setRight(null);
        borderPane.setTop(null);
        borderPane.setLeft(null);
        borderPane.setBottom(null);
    }

    public void switchScene(String scene) throws IOException{
        refreshPane();
        Parent root = FXMLLoader.load(getClass().getResource("/Scene/" + scene + ".fxml"));
        borderPane.setCenter(root); 
    }

    public Clinician getUser(){
        return user;
    }
}
