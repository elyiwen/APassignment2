package code.Scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
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
    private TabPane tabsPane;

    @FXML
    private AnchorPane root;

    private boolean homeIsOne = false;

    private CustomTabHome ctHome;


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
    public void btnHomeClicked(ActionEvent e){
        createNewTab("Home", homeIsOne, ctHome);
        setHomeisOne(true);
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

    public void createNewTab(String tabName, boolean isOne, CustomTabHome ct){
        if(isOne == true){
            
        }
        else{
            ct = new CustomTabHome(tabName);
            tabsPane.getTabs().add(ct);
            tabsPane.getSelectionModel().select(ct);
        }
    }

    public void setHomeisOne(boolean homeIsOne){
        this.homeIsOne = homeIsOne;
    }
}
