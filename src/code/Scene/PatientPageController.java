package code.Scene;

import java.io.IOException;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PatientPageController implements Initializable{

    @FXML
    private Button btnAddPatient;

    @FXML
    private Button btnRefresh;

    @FXML
    public TableView<Patient> tableView;

    @FXML
    public TableColumn<Patient, String> tcPatientID;

    @FXML
    public TableColumn<Patient, String> tcPatientName;

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
        refreshPatientList();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        refreshPatientList();

        tcPatientID.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientID"));
        tcPatientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientName"));
    }

    public void refreshPatientList(){
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
        addViewButtonToTable();
        addDeleteButtonToTable();
    }

    public void addViewButtonToTable() {
        TableColumn<Patient, Void> colBtnView = new TableColumn<Patient, Void>("");

        Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>>() {
            @Override
            public TableCell<Patient, Void> call(final TableColumn<Patient, Void> param) {
                final TableCell<Patient, Void> cellView = new TableCell<Patient, Void>() {

                    private final Button btnView = new Button("View");

                    {
                        btnView.setOnAction((ActionEvent event) -> {
                            //Treatment Course of patient
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnView);
                        }
                    }
                };
                return cellView;
            }
        };

        colBtnView.setCellFactory(cellFactory);

        tableView.getColumns().add(colBtnView);

    }
    
    public void addDeleteButtonToTable() {
        TableColumn<Patient, Void> colBtnDelete = new TableColumn<Patient, Void>("");

        Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>>() {
            @Override
            public TableCell<Patient, Void> call(final TableColumn<Patient, Void> param) {
                final TableCell<Patient, Void> cellView = new TableCell<Patient, Void>() {

                    private final Button btnDelete = new Button("Delete");

                    {
                        btnDelete.setOnAction((ActionEvent event) -> {
                            Patient deletePatient = getTableView().getItems().get(getIndex());
                            Patient.deleteRecord(deletePatient);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnDelete);
                        }
                    }
                };
                return cellView;
            }
        };

        colBtnDelete.setCellFactory(cellFactory);

        tableView.getColumns().add(colBtnDelete);

    }

}
