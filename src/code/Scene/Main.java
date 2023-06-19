package code.Scene;

import java.io.IOException;

import code.Patient.Patient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage) throws IOException, ClassNotFoundException{
        Patient.getPatientFile().createNewFile();
        Patient.readRecord();

        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene loginScene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Taylor's EMR");
        primaryStage.setScene(loginScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            try {
                exit(primaryStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void exit(Stage primaryStage) throws IOException{
        Alert alert = new Alert(AlertType.CONFIRMATION, "Do You Want To Exit", ButtonType.OK);
        alert.setHeaderText("You Are About To Exit");
        alert.setTitle("EXIT");

        if (alert.showAndWait().get() == ButtonType.OK){
            primaryStage.close();
            //Patient.writeRecord();
        }
    }
}
