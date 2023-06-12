import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public void start(Stage loginStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene loginScene = new Scene(root);
        loginStage.setResizable(false);
        loginStage.setTitle("Taylor's EMR");
        loginStage.setScene(loginScene);
        loginStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
