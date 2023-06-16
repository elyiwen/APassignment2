package code.Scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

public class CalendarController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        Map<Integer, List<String>> appointmentsMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = -(rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<String> appointments = appointmentsMap.get(currentDate);
                        if (appointments != null) {
                            createCalendarActivity(appointments, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<String> appointments, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();

        if (!appointments.isEmpty()) {
            String firstAppointment = appointments.get(0);
            String[] lines = firstAppointment.split("\n", 2);
            if (lines.length > 1) {
                String appointmentDetails = lines[1];
                Text text = new Text(appointmentDetails);
                calendarActivityBox.getChildren().add(text);
            }

            if (appointments.size() > 1) {
                if (appointments.size() == 2) {
                    Text moreText = new Text("...");
                    moreText.setFill(Color.BLUE);
                    moreText.setCursor(Cursor.HAND);
                    calendarActivityBox.getChildren().add(moreText);

                    StringBuilder allAppointments = new StringBuilder();
                    for (String appointment : appointments) {
                        allAppointments.append(appointment).append("\n");
                    }

                    moreText.setOnMouseClicked(event -> {
                        JOptionPane.showMessageDialog(null, allAppointments.toString().trim(), "Appointments", JOptionPane.INFORMATION_MESSAGE);
                    });
                } else {
                    Text moreText = new Text();
                    calendarActivityBox.getChildren().add(moreText);

                    StringBuilder allAppointments = new StringBuilder();
                    for (String appointment : appointments) {
                        allAppointments.append(appointment).append("\n");
                    }

                    moreText.setOnMouseClicked(event -> {
                        JOptionPane.showMessageDialog(null, allAppointments.toString().trim(), "Appointments", JOptionPane.INFORMATION_MESSAGE);
                    });
                }
            }
        }

        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");

        stackPane.getChildren().add(calendarActivityBox);
    }


    private Map<Integer, List<String>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        Map<Integer, List<String>> calendarActivityMap = new HashMap<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonthValue();

        String filePath = "appointments.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Appointments file not found: " + file.getAbsolutePath());
            return calendarActivityMap;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Date: ") && line.length() > 6) {
                    LocalDate appointmentDate = LocalDate.parse(line.substring(6));
                    if (appointmentDate.getYear() == year && appointmentDate.getMonthValue() == month) {
                        int dayOfMonth = appointmentDate.getDayOfMonth();
                        String appointmentDetails = "";

                        while ((line = reader.readLine()) != null) {
                            if (line.startsWith("Name: ")) {
                                appointmentDetails += line.substring(6) + "\n";
                            } else if (line.startsWith("ID: ")) {
                                appointmentDetails += line.substring(4) + "\n";
                            } else if (line.startsWith("Time: ")) {
                                appointmentDetails += line.substring(6) + "\n";
                                break; // End of appointment details
                            }
                        }

                        List<String> appointments = calendarActivityMap.getOrDefault(dayOfMonth, new ArrayList<>());
                        appointments.add(appointmentDetails);
                        calendarActivityMap.put(dayOfMonth, appointments);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return calendarActivityMap;
    }
}
