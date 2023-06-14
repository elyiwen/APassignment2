package code.Scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

public class LoginSceneController {

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnLogIn;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    void btnLogInClicked(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene mainScene = new Scene(root);
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

}
