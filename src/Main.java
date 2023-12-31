import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage) throws IOException{

        //Load Login Page
        Parent loginFXML = FXMLLoader.load(getClass().getResource("/Scene/LoginScene.fxml"));
        Scene loginScene = new Scene(loginFXML);
        primaryStage.setScene(loginScene);
        primaryStage.show();

        // Show Confirmation Message before Exiting the program 
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            Alert alertExit = new Alert(AlertType.CONFIRMATION, "Do You Want To Exit", ButtonType.OK, ButtonType.CANCEL);
            alertExit.setHeaderText("You Are About To Exit");
            alertExit.setTitle("EXIT");

            if (alertExit.showAndWait().get() == ButtonType.OK){
                primaryStage.close();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
