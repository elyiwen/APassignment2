package Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Interfaces.Events;
import Interfaces.MedicalHistory;
import Interfaces.PatientHistory;
import Interfaces.TreatmentCourse;
import Patient.Patient;
import User.Clinician;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

    private static Patient selectedPatient;

    private Clinician user = LoginSceneController.getUser();

    private static ObservableList<Patient> tbPatientList = FXCollections.observableArrayList();

    public static Stage stage;
    public static Scene scene;

    @FXML
    void btnAddClicked(ActionEvent event) throws IOException {
        if (user.getAccountType().equals("Pharmacist")){
            Alert alertError = new Alert(AlertType.CONFIRMATION, "No Privilege To Add Patient", ButtonType.OK, ButtonType.CANCEL);
            alertError.setHeaderText("NOTIFICATION");
            alertError.setTitle("ALERT");
            alertError.showAndWait();
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("/Scene/PatientForm.fxml"));
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void btnDeleteClicked(ActionEvent event) throws IOException {
        if (user.getAccountType().equals("Pharmacist")){
            Alert alertError = new Alert(AlertType.CONFIRMATION, "No Privilege To Delete Patient", ButtonType.OK, ButtonType.CANCEL);
            alertError.setHeaderText("NOTIFICATION");
            alertError.setTitle("ALERT");
            alertError.showAndWait();
        }
        else{
            try{
                selectedPatient = tableView.getSelectionModel().getSelectedItem();
                tableView.getItems().remove(selectedPatient);
                user.deletePatient(selectedPatient);
                user.writeRecord();

                selectedPatient.deletePatientHistoryFile();
                selectedPatient.deleteMedicalHistoryFile();
                selectedPatient.deleteEncountersFile();
                selectedPatient.deleteEventFile();
                selectedPatient.deleteTreatmentCourseFile();

            } catch (NullPointerException npe){
                Alert alertError = new Alert(AlertType.CONFIRMATION, "Please Select A Patient", ButtonType.OK, ButtonType.CANCEL);
                alertError.setHeaderText("NOTIFICATION");
                alertError.setTitle("ALERT");
                alertError.showAndWait();
            }
        }
    }

    @FXML
    void btnRefreshClicked(ActionEvent event) {
        tbPatientList.setAll(Patient.getPatientList());
        tableView.setItems(tbPatientList);
    }

    @FXML
    void btnViewClicked(ActionEvent event) throws IOException {
            selectedPatient = tableView.getSelectionModel().getSelectedItem();
            if (selectedPatient == null){
                Alert alertError = new Alert(AlertType.CONFIRMATION, "Please Select A Patient", ButtonType.OK, ButtonType.CANCEL);
                alertError.setHeaderText("NOTIFICATION");
                alertError.setTitle("ALERT");
                alertError.showAndWait();
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene/MainScene.fxml"));
                Parent root = loader.load();

                MainSceneController msc = loader.getController();
                msc.switchScene("TreatmentCoursePage");
                
                Stage primaryStage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
    }

    @FXML
    void btnSearchClicked(ActionEvent event){
        FilteredList<Patient> patientFilteredData = new FilteredList<>(tbPatientList, b -> true);

        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            patientFilteredData.setPredicate(patient -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (patient.getPatientName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else if (patient.getPatientID().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else{
                    return false;
                }
            });
        }
        );

        SortedList<Patient> patientSortedData = new SortedList<>(patientFilteredData);
        patientSortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(patientSortedData);   
    }

    public static Patient getSelectedPatient(){
        return selectedPatient;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tbPatientList = tableView.getItems();
        tbPatientList.setAll(Patient.getPatientList());
        tableView.setItems(tbPatientList);
        tcPatientID.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientID"));
        tcPatientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientName"));
        tcPatientContactNo.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientContactNo"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<Patient, String>("status"));
        tcWardNo.setCellValueFactory(new PropertyValueFactory<Patient, String>("wardNumber"));
        tcAttendingPhysician.setCellValueFactory(new PropertyValueFactory<Patient, String>("attendingPhysician"));
        tcLatestUpdate.setCellValueFactory(new PropertyValueFactory<Patient, String>("comment"));
    }
}
