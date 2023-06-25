package Controller.Checklist;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ChecklistController implements Initializable{

    @FXML
    private Button btnAdd;

    @FXML
    private ChoiceBox<String> chbUrgency;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField tfTask;


    @FXML
    void btnAddClicked(ActionEvent event) throws IOException{
        String task = tfTask.getText();
        String date = dpDate.getValue().toString();
        String urgency = chbUrgency.getValue();
        
        Checklist newChecklist = new Checklist(task, urgency, date, "");
        Checklist.checklistList.add(newChecklist);
        Checklist.writeToFile();

        Alert alertSuccess = new Alert(AlertType.CONFIRMATION, "Task Recorded Successfully", ButtonType.OK, ButtonType.CANCEL);
        alertSuccess.setHeaderText("NOTIFICATION");
        alertSuccess.setTitle("ALERT");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chbUrgency.getItems().removeAll(chbUrgency.getItems());
        chbUrgency.getItems().addAll("High", "Medium", "Low");
        chbUrgency.setValue("None");
    }

}
