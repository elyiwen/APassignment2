package code.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import code.Patient.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientPageController implements Initializable{

    @FXML
    private Button btnAddPatient;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private TableView<Patient> tableView;

    @FXML
    private TableColumn<Patient, String> tcPatientID;

    @FXML
    private TableColumn<Patient, String> tcPatientName;

    private ObservableList<Patient> patientObservableList = FXCollections.observableArrayList();

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
        
    }

    @FXML
    void btnDeleteClicked(ActionEvent event) throws IOException{
        Patient deletePatient = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(deletePatient);
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
        patientObservableList.addAll(Patient.getPatientListRead());
        Patient.getPatientListRead().clear();
        tableView.setItems(patientObservableList);

        tcPatientID.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientID"));
        tcPatientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientName"));
    }
}
