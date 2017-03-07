package sample;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller {
    @FXML public BorderPane mainBorderPane;
    @FXML public VBox miniCalendarVBox;
    @FXML public DatePicker calendarDatePicker;

    @FXML
    public void initialize() {
//        LocalDate localDate = LocalDate.now();
//        DatePicker datePicker = new DatePicker(localDate);
//        datePicker.setValue(null);
//        calendarDatePicker.hide();
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
        Node popupContent = datePickerSkin.getPopupContent();
//        datePickerSkin.show();
//        Node popupContent = datePicker;
//        Node popupContent = new DatePicker(LocalDate.now());

        miniCalendarVBox.getChildren().add(popupContent);

        try {
            mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/sample/createView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
