package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Features.Announcement;
import Features.Checklist;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

public class HomePageController implements Initializable {

    @FXML
    private FontAwesomeIcon btnAddPatientCheck;
    
    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnDelete;

    @FXML
    private ScrollPane todaysAppointmentScrollPane;

    @FXML
    private VBox todaysAppointmentVBox;

    @FXML
    private TableView<Checklist> tableCheck;

    @FXML
    private TableColumn<Checklist, CheckBox> colCheck;

    @FXML
    private TableColumn<Checklist, String> colDate;

    @FXML
    private TableColumn<Checklist, String> colTask;

    @FXML
    private TableColumn<Checklist, String> colUrgency;

    @FXML
    private TableView<Announcement> tableAnnouncement;

    @FXML
    private FontAwesomeIcon btnAnnounceAdd;
  
    @FXML
    private TableColumn<Announcement, String> colAnnounceDate;

    @FXML
    private TableColumn<Announcement, String> colAnnouncements;

    private static Checklist selectedChecklist;

    public Stage stagePatientCheck;
    public Scene scenePatientCheck;

    public Stage stageAnnouncement;
    public Scene sceneAnnouncement;


    private void displayTodayAppointments() {
        LocalDate today = LocalDate.now();

        try (BufferedReader reader = new BufferedReader(new FileReader("File/appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Date: ")) {
                    String date = line.substring(6);
                    String name = reader.readLine().substring(6);
                    String id = reader.readLine().substring(4);
                    String time = reader.readLine().substring(6);

                    if (date.equals(today.toString())) {
                        Label appointmentLabel = new Label("Name: " + name + " - ID: " + id + " - Time: " + time);
                        appointmentLabel.setStyle("-fx-font-size: 12px");
                        VBox.setMargin(appointmentLabel, new Insets(5));

                        todaysAppointmentVBox.getChildren().add(appointmentLabel);
                    }
                }
            }
        } catch (IOException e) {
            return;
        }
    }

    @FXML
    void btnAddPatientCheckClicked(MouseEvent event) throws IOException {
            stagePatientCheck = new Stage();
            scenePatientCheck = new Scene(FXMLLoader.load(getClass().getResource("/Scene/Checklist.fxml")));
            stagePatientCheck.setTitle("Patient Checklist");
            stagePatientCheck.setScene(scenePatientCheck);
            stagePatientCheck.setResizable(false);
            stagePatientCheck.show();
    }

    
    @FXML
    void btnDeleteClicked(ActionEvent event) throws IOException {
        try {
            selectedChecklist = tableCheck.getSelectionModel().getSelectedItem();
            tableCheck.getItems().remove(selectedChecklist);
            Checklist.deleteChecklist(selectedChecklist);
        } catch (NullPointerException npe) {
            Alert alertError = new Alert(AlertType.ERROR, "Please select a checklist.", ButtonType.OK);
            alertError.setHeaderText("Notification");
            alertError.setTitle("Error");
            alertError.showAndWait();
        }
    }
    
    @FXML
    void btnAnnouncementAddClicked(MouseEvent event) throws IOException {
            stageAnnouncement = new Stage();
            sceneAnnouncement = new Scene(FXMLLoader.load(getClass().getResource("/Scene/AnnouncementForm.fxml")));
            stageAnnouncement.setTitle("Announcement Form");
            stageAnnouncement.setScene(sceneAnnouncement);
            stageAnnouncement.setResizable(false);
            stageAnnouncement.show();
    }

    @FXML
    void btnRefreshClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene/MainScene.fxml"));
        Parent root = loader.load();

        MainSceneController mainSceneController = loader.getController();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainSceneController.switchScene("HomePage");
    }

    ObservableList<Checklist> patientCheckList = FXCollections.observableArrayList();
    ObservableList<Announcement> announcementList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayTodayAppointments();

        colTask.setCellValueFactory(new PropertyValueFactory<Checklist, String>("Task"));
        colDate.setCellValueFactory(new PropertyValueFactory<Checklist, String>("Date"));
        colUrgency.setCellValueFactory(new PropertyValueFactory<Checklist, String>("Urgency"));
        colCheck.setCellValueFactory(new PropertyValueFactory<Checklist, CheckBox>("Checkbox"));

        patientCheckList.setAll(Checklist.getChecklistList());
        tableCheck.setItems(patientCheckList);

        colAnnouncements.setCellValueFactory(new PropertyValueFactory<Announcement, String>("announcementMessage"));
        colAnnounceDate.setCellValueFactory(new PropertyValueFactory<Announcement, String>("Date"));

        announcementList.setAll(Announcement.getAnnouncementList());
        tableAnnouncement.setItems(announcementList);

    }
}



