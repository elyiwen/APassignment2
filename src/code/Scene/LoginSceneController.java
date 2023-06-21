package code.Scene;

import java.io.IOException;

import code.User.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

public class LoginSceneController {

    @FXML
    private Button btnLogIn;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    void btnLogInClicked(ActionEvent event) throws IOException{
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        
        boolean check = false;
        for (Admin a : Admin.getAdminList()){
            if (a.getAdminID().equals(username) && (a.getPassword().equals(password))){
                Alert alertSuccess = new Alert(AlertType.CONFIRMATION, "Login Success", ButtonType.OK);
                alertSuccess.setHeaderText("NOTIFICATION");
                alertSuccess.setTitle("ALERT");
                alertSuccess.showAndWait();
                check = true;
                break;
            }
        }

        if (check == false){
            Alert alertFailed = new Alert(AlertType.CONFIRMATION, "Login Failed", ButtonType.OK);
            alertFailed.setHeaderText("NOTIFICATION");
            alertFailed.setTitle("ALERT");
            alertFailed.showAndWait();
        }
        else if (check == true){
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene mainScene = new Scene(root);
            primaryStage.setResizable(true);
            primaryStage.setMaximized(true);
            primaryStage.setScene(mainScene);
            primaryStage.show();
        }
    }
}
