package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Features.Announcement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AnnouncementFormController implements Initializable{
        @FXML
    private Button btnAddAnnouncement;

    @FXML
    private DatePicker dpDateAnnouncement;

    @FXML
    private TextField tfAnnouncement;

    @FXML
    void btnAddClicked(ActionEvent event) throws IOException {
        String announcementMessage = tfAnnouncement.getText();
        String date = dpDateAnnouncement.getValue().toString();

        Announcement newAnnouncementList = new Announcement(date, announcementMessage);
        Announcement.announcementList.add(newAnnouncementList);
        Announcement.writeToFile();

        Alert alertSuccess = new Alert(AlertType.CONFIRMATION, "Successfully Announced", ButtonType.OK, ButtonType.CANCEL);
        alertSuccess.setHeaderText("NOTIFICATION");
        alertSuccess.setTitle("ALERT");
        alertSuccess.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
