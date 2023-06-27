package Patient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.PatientPageController;
import Interfaces.Encounters;
import Interfaces.Events;
import Interfaces.MedicalHistory;
import Interfaces.PatientHistory;
import Interfaces.TreatmentCourse;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Patient implements TreatmentCourse, Encounters, MedicalHistory, PatientHistory, Events, Serializable {

    // Biodata
    private String patientID;
    private String patientName;
    private String patientIdentityNo;
    private LocalDate doB;
    private String age;
    private String gender;
    private String race_ethnicity;
    private String prefLanguage;
    private String maritalStatus;
    private String status;

    // ContactInfo
    private String address;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String patientEmail;
    private String patientContactNo;
    private String emergencyContactNo;
    private String emergencyName;
    private String emergencyRelationship;

    //Essential
    private String wardNumber;
    private String attendingPhysician;
    private String comment;

    //Patient List
    private static ArrayList<Patient> patientList = new ArrayList<>();

    //Patient File
    private static File patientFile = new File("File/patient.json");

    private Patient selectedPatient = PatientPageController.getSelectedPatient();

    private static final long serialVersionUID = -4216126862664342211L;

    public void setPatientBiodata(String patientName, String patientIdentityNo, LocalDate doB, String race_ethnicity, String gender, String prefLanguage, String maritalStatus, String status) throws IOException{
        this.patientName = patientName;
        this.patientIdentityNo = patientIdentityNo;
        this.doB = doB;
        this.race_ethnicity = race_ethnicity;
        this.gender = gender;
        this.prefLanguage = prefLanguage;
        this.maritalStatus = maritalStatus;
        this.status = status;
        this.patientID = generateID();
        this.age = calAge(doB);
    }

    public void setPatientContactInfo(String address, String country, String state, String city, String zipCode, String patientEmail, String patientContactNo, String emergencyContactNo, String emergencyName, String emergencyRelationship){
        this.address = address;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.patientEmail = patientEmail;
        this.patientContactNo = patientContactNo;
        this.emergencyContactNo = emergencyContactNo;
        this.emergencyName = emergencyName;
        this.emergencyRelationship = emergencyRelationship;
    }

    public void setPatientEssential(String wardNumber, String attendingPhysician, String comment){
        this.wardNumber = wardNumber;
        this.attendingPhysician = attendingPhysician;
        this.comment = comment;
    }
    
    public String generateID(){
        String idFirst = "P";
        char[] patientNameChar = getPatientName().toCharArray();
        String idMid = patientNameChar[0] + "-" + patientNameChar[1];
        String idLast = getPatientIdentityNo().substring(getPatientIdentityNo().length() - 4);
        String patientID = idFirst + idMid.toUpperCase() + idLast;
        return patientID;
    };

    public String calAge(LocalDate doB){
        int yearDoB = doB.getYear();
        int yearNow = Year.now().getValue();
        int age = yearNow - yearDoB;
        String ageS = Integer.toString(age);
        return ageS;
    }

    @Override
    public void displayAllEncounters(Patient selectedPatient, VBox encounterVBox) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Encounter.txt";
        String filePath = folderPath + File.separator + filename;
        File file = new File(filePath);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                StringBuilder encounterBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("-----Encounter")) {
                        if (encounterBuilder.length() > 0) {
                            insertEncounterLabels(encounterBuilder.toString(), encounterVBox);
                            encounterBuilder.setLength(0);
                        }
                        encounterBuilder.append(line).append("\n");
                    } else {
                        encounterBuilder.append(line).append("\n");
                    }
                }
                if (encounterBuilder.length() > 0) {
                    insertEncounterLabels(encounterBuilder.toString(), encounterVBox);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertEncounterLabels(String encounterContent, VBox encounterVBox) {
        String[] lines = encounterContent.split("\n");

        String encounter = lines[1].substring(11);
        String date = lines[2].substring(6);

        Label encounterLabel = new Label(encounter + "     " + date);
        encounterLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(encounterLabel, new Insets(5));

        encounterVBox.getChildren().add(encounterLabel);
    }

    @Override
    public void deleteEncountersFile() {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Encounter.txt";
        String filePath = folderPath + File.separator + filename;
        File encounter = new File(filePath);
        if (encounter.exists()) {
            encounter.delete();
        }
    }

    @Override
    public void displayAllEvents(Patient selectedPatient, VBox eventVBox) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Event.txt";
        String filePath = folderPath + File.separator + filename;
        File file = new File(filePath);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                StringBuilder eventBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("-----Event")) {
                        if (eventBuilder.length() > 0) {
                            insertEventLabels(eventBuilder.toString(), eventVBox);
                            eventBuilder.setLength(0);
                        }
                        eventBuilder.append(line).append("\n");
                    } else {
                        eventBuilder.append(line).append("\n");
                    }
                }
                if (eventBuilder.length() > 0) {
                    insertEventLabels(eventBuilder.toString(), eventVBox);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertEventLabels(String eventContent, VBox eventVBox) {
        String[] lines = eventContent.split("\n");

        String event = lines[1].substring(7);
        String time = lines[2].substring(6);
        String date = lines[3].substring(6);

        Label timeDateLabel = new Label(time + " - " + date);
        timeDateLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(timeDateLabel, new Insets(5));

        Label eventLabel = new Label(event);
        eventLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(eventLabel, new Insets(5));

        VBox eventEntryVBox = new VBox();
        eventEntryVBox.getChildren().addAll(timeDateLabel, eventLabel);
        eventVBox.getChildren().add(eventEntryVBox);
    }

    @Override
    public void deleteEventFile() {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Event.txt";
        String filePath = folderPath + File.separator + filename;
        File event = new File(filePath);
        if (event.exists()) {
            event.delete();
        }
    }

    @Override
    public void displayMedicalHistory(Patient selectedPatient, Label labelFamilyHistory, Label labelAllergies,
                                             Label labelSmoking, Label labelAlcohol, Label labelTriageDetails,
                                             Label labelAdditionalComments) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Medical History.txt";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Family History":
                                labelFamilyHistory.setText("Family History: " + fieldValue);
                                break;
                            case "Allergies":
                                labelAllergies.setText("Allergies: " + fieldValue);
                                break;
                            case "Smoking":
                                labelSmoking.setText("Smoking: " + fieldValue);
                                break;
                            case "Alcohol":
                                labelAlcohol.setText("Alcohol: " + fieldValue);
                                break;
                            case "Triage Details":
                                labelTriageDetails.setText("Triage Details: " + fieldValue);
                                break;
                            case "Additional Comments":
                                labelAdditionalComments.setText("Additional Comments: " + fieldValue);
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            labelFamilyHistory.setText("Family History: None");
            labelAllergies.setText("Allergies: None");
            labelSmoking.setText("Smoking: None");
            labelAlcohol.setText("Alcohol: None");
            labelTriageDetails.setText("Triage Details: None");
            labelAdditionalComments.setText("Additional Comments: None");
        }
    }

    @Override
    public void setTextField(Patient selectedPatient, TextField tfFamilyHistory, TextField tfAllergies,
                                    TextField tfSmoking, TextField tfAlcohol,
                                    TextField tfTriageDetails, TextField tfAdditionalComments) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Medical History.txt";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Family History":
                                tfFamilyHistory.setText(fieldValue);
                                break;
                            case "Allergies":
                                tfAllergies.setText(fieldValue);
                                break;
                            case "Smoking":
                                tfSmoking.setText(fieldValue);
                                break;
                            case "Alcohol":
                                tfAlcohol.setText(fieldValue);
                                break;
                            case "Triage Details":
                                tfTriageDetails.setText(fieldValue);
                                break;
                            case "Additional Comments":
                                tfAdditionalComments.setText(fieldValue);
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void deleteMedicalHistoryFile() {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Medical History.txt";
        String filePath = folderPath + File.separator + filename;
        File medicalHistory = new File(filePath);
        if (medicalHistory.exists()) {
            medicalHistory.delete();
        }
    }

    @Override
    public void displayPatientHistory(Patient selectedPatient, Label labelWardNumber, Label labelMovementMeans,
                                             Label labelAttendingPhysician, Label labelMajorComplications,
                                             Label labelTreatmentResults, Label labelSpecialComments,
                                             Label labelHistoryID) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Patient History.txt";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Ward Number":
                                labelWardNumber.setText("Ward Number: " + fieldValue);
                                break;
                            case "Movement Means":
                                labelMovementMeans.setText("Movement Means: " + fieldValue);
                                break;
                            case "Attending Physician":
                                labelAttendingPhysician.setText("Attending Physician: " + fieldValue);
                                break;
                            case "Major Complication":
                                labelMajorComplications.setText("Major Complications: " + fieldValue);
                                break;
                            case "Treatment Results":
                                labelTreatmentResults.setText("Treatment Results: " + fieldValue);
                                break;
                            case "Special Comments":
                                labelSpecialComments.setText("Special Comments: " + fieldValue);
                                break;
                            case "History ID":
                                labelHistoryID.setText("History ID: " + fieldValue);
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            labelWardNumber.setText("Ward Number: None" );
            labelMovementMeans.setText("Movement Means: None");
            labelAttendingPhysician.setText("Attending Physician: None");
            labelMajorComplications.setText("Major Complications: None");
            labelTreatmentResults.setText("Treatment Results: None");
            labelSpecialComments.setText("Special Comments: None");
            labelHistoryID.setText("History ID: None");
        }
    }

    @Override
    public void setTextField(Patient selectedPatient, TextField tfWardNumber, TextField tfMovementMeans,
                                    TextField tfAttendingPhysician, TextField tfMajorComplications,
                                    TextField tfTreatmentResults, TextField tfSpecialComments,
                                    TextField tfHistoryID) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Patient History.txt";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Ward Number":
                                tfWardNumber.setText(fieldValue);
                                break;
                            case "Movement Means":
                                tfMovementMeans.setText(fieldValue);
                                break;
                            case "Attending Physician":
                                tfAttendingPhysician.setText(fieldValue);
                                break;
                            case "Major Complication":
                                tfMajorComplications.setText(fieldValue);
                                break;
                            case "Treatment Results":
                                tfTreatmentResults.setText(fieldValue);
                                break;
                            case "Special Comments":
                                tfSpecialComments.setText(fieldValue);
                                break;
                            case "History ID":
                                tfHistoryID.setText(fieldValue);
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void deletePatientHistoryFile() {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Patient History.txt";
        String filePath = folderPath + File.separator + filename;
        File patientHistory = new File(filePath);
        if (patientHistory.exists()) {
            patientHistory.delete();
        }
    }

    @Override
    public void displayTreatmentCourse(Patient selectedPatient, Label labelTreatmentID, Label labelStartingDate,
                                             Label labelEndingDate, VBox diagnosisVBox, VBox analysisVBox, VBox procedureVBox,
                                              VBox medicationVBox) {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Treatment Course.txt";
        String filePath = folderPath + File.separator + filename;

        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                String disease = " ";
                String dateOfDiagnosis = " ";
                String nameOfAnalysis = " ";
                String contentOfAnalysis = " ";
                String dateOfAnalysis = " ";
                String nameOfProcedure = " ";
                String contentOfProcedure = " ";
                String time = " ";
                String dateOfProcedure = " ";
                String nameOfMedication = " ";
                String dosage = " ";
                String comment = " ";
                String frequency = " ";
                String type = " ";
                String startedDate = " ";
                
                String line = " ";
                boolean treatmentCourseIDDisplayed = false;
                boolean startingDateDisplayed = false;
                boolean endingDateDisplayed = false;
                boolean diseaseDisplayed = false;
                boolean dateOfDiagnosisDisplayed = false;
                boolean nameOfAnalysisDisplayed = false;
                boolean contentOfAnalysisDisplayed = false;
                boolean dateOfAnalysisDisplayed = false;
                boolean nameOfProcedureDisplayed = false;
                boolean contentOfProcedureDisplayed = false;
                boolean timeDisplayed = false;
                boolean dateOfProcedureDisplayed = false;
                boolean nameOfMedicationDisplayed = false;
                boolean dosageDisplayed = false;
                boolean commentDisplayed = false;
                boolean frequencyDisplayed = false;
                boolean typeDisplayed = false;
                boolean startedDateDisplayed = false;


                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        switch (fieldName) {
                            case "Treatment Course ID":
                                if (!treatmentCourseIDDisplayed) {
                                    labelTreatmentID.setText("Treatment Course ID: " + fieldValue);
                                    treatmentCourseIDDisplayed = true;
                                }
                                break;

                            case "Starting Date":
                                if (!startingDateDisplayed) {
                                    labelStartingDate.setText("Starting Date: " + fieldValue);
                                    startingDateDisplayed = true;
                                }
                                break;
                            case "Ending Date":
                                if (!endingDateDisplayed) {
                                    labelEndingDate.setText("Ending Date: " + fieldValue);
                                    endingDateDisplayed = true;
                                }
                                break;
                            case "Disease":
                                if (!diseaseDisplayed) {
                                    disease = fieldValue;
                                    diseaseDisplayed = true;
                                }
                                break;
                            case "Date of Diagnosis":
                                if (!dateOfDiagnosisDisplayed) {
                                    dateOfDiagnosis = fieldValue;
                                    dateOfDiagnosisDisplayed = true;
                                }
                                break;
                            case "Name of Analysis":
                                if (!nameOfAnalysisDisplayed) {
                                    nameOfAnalysis = fieldValue;
                                    nameOfAnalysisDisplayed = true;
                                }
                                break;
                            case "Content Of Analysis":
                                if (!contentOfAnalysisDisplayed) {
                                    contentOfAnalysis = fieldValue;
                                    contentOfAnalysisDisplayed = true;
                                }
                                break;
                            case "Date of Analysis":
                                if (!dateOfAnalysisDisplayed) {
                                    dateOfAnalysis = fieldValue;
                                    dateOfAnalysisDisplayed = true;
                                }
                                break;
                            case "Name of Procedure":
                                if (!nameOfProcedureDisplayed) {
                                    nameOfProcedure = fieldValue;
                                    nameOfProcedureDisplayed = true;
                                }
                                break;
                            case "Content Of Procedure":
                                if (!contentOfProcedureDisplayed) {
                                    contentOfProcedure = fieldValue;
                                    contentOfProcedureDisplayed = true;
                                }
                                break;
                            case "Time":
                                if (!timeDisplayed) {
                                    time = fieldValue;
                                    timeDisplayed = true;
                                }
                                break;
                            case "Date of Procedure":
                                if (!dateOfProcedureDisplayed) {
                                    dateOfProcedure = fieldValue;
                                    dateOfProcedureDisplayed = true;
                                }
                                break;
                            case "Name of Medication":
                                if (!nameOfMedicationDisplayed) {
                                    nameOfMedication = fieldValue;
                                    nameOfMedicationDisplayed = true;
                                }
                                break;
                            case "Dosage":
                                if (!dosageDisplayed) {
                                    dosage = fieldValue;
                                    dosageDisplayed = true;
                                }
                                break;
                            case "Comment":
                                if (!commentDisplayed) {
                                    comment = fieldValue;
                                    commentDisplayed = true;
                                }
                                break;
                            case "Frequency":
                                if (!frequencyDisplayed) {
                                    frequency = fieldValue;
                                    frequencyDisplayed = true;
                                }
                                break;
                            case "C,DC,WH":
                                if (!typeDisplayed) {
                                    type = fieldValue;
                                    typeDisplayed = true;
                                }
                                break;
                            case "Started Date":
                                if (!startedDateDisplayed) {
                                    startedDate = fieldValue;
                                    startedDateDisplayed = true;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
                displayDiagnosis(disease, dateOfDiagnosis, diagnosisVBox);
                displayAnalysis(nameOfAnalysis, contentOfAnalysis, dateOfAnalysis, analysisVBox);
                displayProcedure(nameOfProcedure, contentOfProcedure, time, dateOfProcedure, procedureVBox);
                displayMedication(nameOfMedication, dosage, comment, frequency, type, startedDate, medicationVBox);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
        labelTreatmentID.setText("Ward Number: None" );
        labelStartingDate.setText("Movement Means: None");
        labelEndingDate.setText("Attending Physician: None");
        }
    }

    @Override
    public void deleteTreatmentCourseFile() {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Treatment Course.txt";
        String filePath = folderPath + File.separator + filename;
        File treatmentCourse = new File(filePath);
        if (treatmentCourse.exists()) {
            treatmentCourse.delete();
        }
    }

    @Override
    public void displayDiagnosis(String disease, String dateOfDiagnosis, VBox diagnosisVBox){
        Label diagnosisLabel = new Label("Disease : " + disease + "\n" +
                "Date of Diagnosis : " + dateOfDiagnosis );
        diagnosisLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(diagnosisLabel, new Insets(5));
        diagnosisVBox.getChildren().add(diagnosisLabel);
    }

    @Override
    public void displayAnalysis(String nameOfAnalysis, String contentOfAnalysis, String dateOfAnalysis, VBox analysisVBox){
        Label analysisLabel = new Label(nameOfAnalysis + "\n" +
                contentOfAnalysis + "\n" +
                "Date of Analysis : " + dateOfAnalysis );
        analysisLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(analysisLabel, new Insets(5));
        analysisVBox.getChildren().add(analysisLabel);
    }

    @Override
    public void displayProcedure(String nameOfProcedure, String contentOfProcedure, String time, String dateOfProcedure, VBox procedureVBox){
        Label procedureLabel = new Label(nameOfProcedure + ": " + time + " - " + dateOfProcedure + "\n" +
                "> " +contentOfProcedure);
        procedureLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(procedureLabel, new Insets(5));
        procedureVBox.getChildren().add(procedureLabel);
    }

    @Override
    public void displayMedication(String nameOfMedication, String dosage, String comment, String frequency,
                                          String type, String startedDate, VBox medicationVBox){
        Label medicationLabel = new Label("Name of Medication : " + nameOfMedication + "\n" +
                "Dosage : " + dosage + "\n" +
                "Comment : " + comment + "\n" +
                "Frequency : " + frequency + "\n" +
                "C,DC,WH : " + type + "\n" +
                "Started Date : " + startedDate);
        medicationLabel.setStyle("-fx-font-size: 12px");
        VBox.setMargin(medicationLabel, new Insets(5));
        medicationVBox.getChildren().add(medicationLabel);
    }

    //Getters
    public String getPatientID(){
        return patientID;
    }

    public String getPatientName(){
        return patientName;
    }

    public String getPatientIdentityNo(){
        return patientIdentityNo;
    }

    public LocalDate getPatientDoB(){
        return doB;
    }

    public String getPatientAge(){
        return age;
    }

    public String getRace_Ethinicity(){
        return race_ethnicity;
    }

    public String getGender(){
        return gender;
    }

    public String getPrefLanguage(){
        return prefLanguage;
    }

    public String getMaritalStatus(){
        return maritalStatus;
    }

    public String getStatus(){
        return status;
    }

    public static ArrayList<Patient> getPatientList(){
        return patientList;
    }

    public String getAddress(){
        return address;
    }

    public String getCountry(){
        return country;
    }

    public String getState(){
        return state;
    }
    
    public String getCity(){
        return city;
    }

    public String getZipCode(){
        return zipCode;
    }

    public String getFullAddress(){
        return address + "\n" + city + " " + zipCode + "\n" + state + " " + country;
    }

    public String getPatientEmail(){
        return patientEmail;
    }

    public String getPatientContactNo(){
        return patientContactNo;
    }

    public String getEmergencyName(){
        return emergencyName;
    }

    public String getEmergencyRelationship(){
        return emergencyRelationship;
    }

    public String getEmergencyContactNo(){
        return emergencyContactNo;
    }

    public String getEmergencyInfo(){
        return emergencyName + "\n" + emergencyRelationship + "\n" + emergencyContactNo;
    }

    public static File getPatientFile(){
        return patientFile;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public String getAttendingPhysician() {
        return attendingPhysician;
    }

    public String getComment() {
        return comment;
    }
}
