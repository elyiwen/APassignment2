import java.io.IOException;

import Patient.Patient;
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

        Parent loginFXML = FXMLLoader.load(getClass().getResource("/Scene/LoginScene.fxml"));
        Scene loginScene = new Scene(loginFXML);
        primaryStage.setScene(loginScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            try {
                Alert alertExit = new Alert(AlertType.CONFIRMATION, "Do You Want To Exit", ButtonType.OK);
                alertExit.setHeaderText("You Are About To Exit");
                alertExit.setTitle("EXIT");

                if (alertExit.showAndWait().get() == ButtonType.OK){
                    primaryStage.close();
                    Patient.writeRecord();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
