package code.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import code.Patient.Patient;
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
    private Button btnDisplay;

    @FXML
    public TableView<Patient> tableView;

    @FXML
    private Button btnRefresh;

    @FXML
    public TableColumn<Patient, String> tcPatientID;

    @FXML
    public TableColumn<Patient, String> tcPatientName;

    public static Stage stage;
    public static Scene scene;

    @FXML
    void btnAddPatientClicked(ActionEvent event) throws IOException {
        stage = new Stage();
        scene = new Scene(FXMLLoader.load(getClass().getResource("PatientRegistrationForm.fxml")));
        stage.setTitle("Patient Registration Form");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void btnRefreshClicked(ActionEvent event) {
        ObservableList<Patient> patientListTableView = tableView.getItems();
        ArrayList<Patient> newPatientListTmp = PatientRegistrationFormController.getNewPatientList();
        for(Patient newPatient : newPatientListTmp){
            patientListTableView.add(newPatient);

        }
        newPatientListTmp.clear();
        tableView.setItems(patientListTableView);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcPatientID.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientID"));
        tcPatientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientName"));
    }
}
