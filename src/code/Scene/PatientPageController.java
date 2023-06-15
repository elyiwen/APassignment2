package code.Scene;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
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
    public TableView<Patient> tableView;

    @FXML
    public TableColumn<Patient, String> tcPatientID;

    @FXML
    public TableColumn<Patient, String> tcPatientName;

    public static Stage stageRegistrationForm;
    public static Scene sceneRegistrationForm;

    @FXML
    void btnAddPatientClicked(ActionEvent event) throws IOException {
        stageRegistrationForm = new Stage();
        sceneRegistrationForm = new Scene(FXMLLoader.load(getClass().getResource("PatientRegistrationForm.fxml")));
        stageRegistrationForm.setTitle("Patient Registration Form");
        stageRegistrationForm.setScene(sceneRegistrationForm);
        stageRegistrationForm.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        try {
            Patient.readRecord();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("No Record Found");
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
