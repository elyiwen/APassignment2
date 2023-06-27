package Patient;

import Controller.PatientPageController;

import java.io.File;

public class TreatmentCourse {

    private Patient selectedPatient = PatientPageController.getSelectedPatient();
    public void deleteTreatmentCourseFile() {
        String folderPath = "File";
        String filename = selectedPatient.getPatientID() + " Treatment Course.json";
        String filePath = folderPath + File.separator + filename;
        File treatmentCourse = new File(filePath);
        if (treatmentCourse.exists()) {
            treatmentCourse.delete();
        }
    }
}
