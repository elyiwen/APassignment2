package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Patient.Patient;
import code.Clinician;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientPageController implements Initializable{
    
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnView;

    @FXML
    private TableView<Patient> tableView;

    @FXML
    private TableColumn<Patient, String> tcAttendingPhysician;

    @FXML
    private TableColumn<Patient, String> tcLatestUpdate;

    @FXML
    private TableColumn<Patient, String> tcPatientContactNo;

    @FXML
    private TableColumn<Patient, String> tcPatientID;

    @FXML
    private TableColumn<Patient, String> tcPatientName;

    @FXML
    private TableColumn<Patient, String> tcStatus;

    @FXML
    private TableColumn<Patient, String> tcWardNo;

    @FXML
    private TextField tfSearch;

    private static Clinician user = MainSceneController.getUser();

    @FXML
    void btnAddClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scene/PatientForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnDeleteClicked(ActionEvent event) throws IOException {
        Patient selectedPatient = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(selectedPatient);
        user.deletePatient(selectedPatient);
        user.writeRecord();
    }

    @FXML
    void btnEditClicked(ActionEvent event) {
        
    }

    @FXML
    void btnRefreshClicked(ActionEvent event) {
        ObservableList<Patient> tbPatientList = tableView.getItems();
        tbPatientList.setAll(Patient.getPatientList());
        tableView.setItems(tbPatientList);
    }

    @FXML
    void btnViewClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Patient> tbPatientList = tableView.getItems();
        tbPatientList.setAll(Patient.getPatientList());
        tableView.setItems(tbPatientList);

        tcPatientID.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientID"));
        tcPatientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientName"));
    }
}
