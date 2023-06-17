package code.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import code.Patient.Patient;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PatientPageController implements Initializable{

    @FXML
    private Button btnAddPatient;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnView;

    @FXML
    public TableView<Patient> tableView;

    @FXML
    public TableColumn<Patient, String> tcPatientID;

    @FXML
    public TableColumn<Patient, String> tcPatientName;

    @FXML
    public TableColumn<Patient, String> tcViewButton;

    public static Stage stagePatientForm;
    public static Scene scenePatientForm;

    @FXML
    void btnAddPatientClicked(ActionEvent event) throws IOException {
        stagePatientForm = new Stage();
        scenePatientForm = new Scene(FXMLLoader.load(getClass().getResource("PatientForm.fxml")));
        stagePatientForm.setTitle("Patient Registration Form");
        stagePatientForm.setScene(scenePatientForm);
        stagePatientForm.show();
    }

    @FXML
    void btnRefreshClicked(ActionEvent event) {
        try {
            Patient.readRecord();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            return;
        }
        ObservableList<Patient> patientListTableView = tableView.getItems();
        patientListTableView.clear();
        for (Patient patient : Patient.getPatientListRead()){
            patientListTableView.add(patient);
        }
        tableView.setItems(patientListTableView);
        Patient.getPatientListRead().clear();
    }

    @FXML
    void btnRemoveClicked(ActionEvent event){

    }

    @FXML
    void btnViewClicked(ActionEvent event){

    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        try {
            Patient.readRecord();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            return;
        }
        ObservableList<Patient> patientListTableView = tableView.getItems();
        patientListTableView.clear();
        for (Patient patient : Patient.getPatientListRead()){
            patientListTableView.add(patient);
        }
        tableView.setItems(patientListTableView);
        Patient.getPatientListRead().clear();
        tcPatientID.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientID"));
        tcPatientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientName"));
    }
}
