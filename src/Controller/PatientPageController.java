package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Patient.Patient;
import code.Clinician;
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
    private Button btnEdit;

    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnSaveProgress;

    @FXML
    private TableView<Patient> tableView;

    @FXML
    private TableColumn<Patient, String> tcPatientID;

    @FXML
    private TableColumn<Patient, String> tcPatientName;

    @FXML
    private TableColumn<Patient, String> tcPatientContactNo;

    @FXML
    private TableColumn<Patient, String> tcStatus;

    private ObservableList<Patient> patientObservableList = FXCollections.observableArrayList();

    public static Stage stagePatientForm;
    public static Scene scenePatientForm;

    private static Patient selectedPatient;
    private static Clinician user;

    @FXML
    void btnAddClicked(ActionEvent event) throws IOException {
        if (user.getAccountType().equals("Doctor") || user.getAccountType().equals("Nurse")){
            stagePatientForm = new Stage();
            scenePatientForm = new Scene(FXMLLoader.load(getClass().getResource("/Scene/PatientForm.fxml")));
            stagePatientForm.setTitle("Patient Registration Form");
            stagePatientForm.setScene(scenePatientForm);
            stagePatientForm.setResizable(false);
            stagePatientForm.show();
        }
        else {
            Alert alert = new Alert(AlertType.CONFIRMATION, "This Function is Not Allowed", ButtonType.OK);
            alert.setHeaderText("NOTIFICATION");
            alert.setTitle("ALERT");
            alert.showAndWait();
        }
    }

    @FXML
    void btnRefreshClicked(ActionEvent event) {
        patientObservableList.clear();
        patientObservableList.addAll(Patient.getPatientList());
        tableView.setItems(patientObservableList);
    }

    @FXML
    void btnSaveRecord(ActionEvent event) throws IOException{
        
    }

    @FXML
    void btnDeleteClicked(ActionEvent event) throws IOException{
        try{
            Patient deletePatient = tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().remove(deletePatient);
            user.deletePatient(deletePatient);
        }
        catch (NullPointerException npe){
            Alert alert = new Alert(AlertType.CONFIRMATION, "Please Select A Patient to Remove", ButtonType.OK);
            alert.setHeaderText("NOTIFICATION");
            alert.setTitle("ALERT");
            alert.showAndWait();
        }
    }

    @FXML
    void btnViewClicked(ActionEvent event) throws IOException{
        selectedPatient = tableView.getSelectionModel().getSelectedItem();
        if (selectedPatient == null){
            Alert alert = new Alert(AlertType.CONFIRMATION, "Please Select A Patient to View", ButtonType.OK);
            alert.setHeaderText("NOTIFICATION");
            alert.setTitle("ALERT");
            alert.showAndWait();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene/MainScene.fxml"));
            Parent root = loader.load();

            MainSceneController mainSceneController = loader.getController();
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            mainSceneController.switchScene("TreatmentCoursePage");
        }
    }

    @FXML
    void btnEditClicked(ActionEvent event) throws IOException{
        selectedPatient = tableView.getSelectionModel().getSelectedItem();
        if (selectedPatient == null){
            Alert alert = new Alert(AlertType.CONFIRMATION, "Please Select A Patient to Edit", ButtonType.OK);
            alert.setHeaderText("NOTIFICATION");
            alert.setTitle("ALERT");
            alert.showAndWait();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene/PatientForm.fxml"));
            Parent root = loader.load();

            PatientFormController pfc = loader.getController();
            pfc.editBiodata(selectedPatient.getPatientName(),
                            selectedPatient.getPatientIdentityNo(),
                            selectedPatient.getPatientDoB(),
                            selectedPatient.getRace_Ethinicity(),
                            selectedPatient.getGender(),
                            selectedPatient.getPrefLanguage(),
                            selectedPatient.getMaritalStatus(),
                            selectedPatient.getStatus());
            
            pfc.editContactInfo(selectedPatient.getAddress(),
                                selectedPatient.getCity(),
                                selectedPatient.getState(),
                                selectedPatient.getZipCode(),
                                selectedPatient.getCountry(),
                                selectedPatient.getPatientEmail(),
                                selectedPatient.getPatientContactNo(),
                                selectedPatient.getEmergencyName(),
                                selectedPatient.getEmergencyRelationship(),
                                selectedPatient.getEmergencyContactNo());

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = MainSceneController.getUser();

        tcPatientID.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientID"));
        tcPatientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientName"));
        tcPatientContactNo.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientContactNo")); 
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));  

        patientObservableList.clear();
        patientObservableList.addAll(Patient.getPatientList());
        tableView.setItems(patientObservableList);

        FilteredList<Patient> patientFilteredData = new FilteredList<>(patientObservableList, b -> true);

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
}
