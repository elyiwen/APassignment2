package code.Scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeSceneController{
    
    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnPatient;

    @FXML
    private Button btnDoc;

    @FXML
    private Button btnHome;

    @FXML
    public void btnAdminClicked(ActionEvent event) {

    }

    @FXML
    public void btnPatientClicked(ActionEvent event) {
        
    }

    @FXML
    public void btnDocClicked(){
      
    }

    @FXML
    public void btnHomeClicked(){
        
    }

    public void initHomeScene(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        Stage homeStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root);
        homeStage.setMaximized(true);
        homeStage.setResizable(true);
        homeStage.setTitle("Taylor's EMR");
        homeStage.setScene(homeScene);
        homeStage.show();
    }
}
