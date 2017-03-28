package controller;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;

public class MainController {
    @FXML public BorderPane mainBorderPane;
    @FXML public VBox miniCalendarVBox;
    @FXML public DatePicker calendarDatePicker;

    public void loadDayView() {
        try {
            mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/view/dayView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAgendaView() {
        try {
            mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/view/agendaView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCreateView() {
        try {
            mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/view/createView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
        Node popupContent = datePickerSkin.getPopupContent();

        miniCalendarVBox.getChildren().add(popupContent);
        loadDayView();
    }
}
