package code.Scene;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    public void start(Stage loginStage) throws IOException{
        LoginSceneController lsc = new LoginSceneController();
        lsc.initLoginScene(loginStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
