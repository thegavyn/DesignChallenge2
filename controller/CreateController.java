package controller;

//import com.sun.deploy.panel.RadioPropertyGroup;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import model.Agenda;
import model.CalendarModel;
import model.Event;
import model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Observer;


/**
 * Created by Mina on 3/8/2017.
 */
public class CreateController extends Controller {
    @FXML TextField nameTextField;
    @FXML ToggleGroup typeSelection;
    @FXML DatePicker calendarDatePicker;
//    @FXML TextField startTimeTextField;
//    @FXML TextField endTimeTextField;
    @FXML ChoiceBox startTimeComboBox;
    @FXML ChoiceBox endTimeComboBox;
    @FXML Button saveButton;
    @FXML Button discardButton;

    //private CalendarModel model;
    List<String> timeList;

    public CreateController(CalendarModel model) {
        //this.model = model;
		super(model);
    }

    public void saveFormData(ActionEvent actionEvent) {
        // TODO Save the inputted form data to the model.
        String name = null;
        String type = null;
        LocalDate date = null;
        LocalTime startTime = null;
        LocalTime endTime = null;
        DateTimeFormatter dateTimeFormatter = null;

        if (nameTextField.getText() != "")
            name = nameTextField.getText();
        if (typeSelection.getSelectedToggle() != null)
            type = ((RadioButton)typeSelection.getSelectedToggle()).getText();
        if (calendarDatePicker.getValue() != null) {
            date = calendarDatePicker.getValue();
        }
        if (!startTimeComboBox.getSelectionModel().isEmpty()) {
            dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            startTime = LocalTime.parse((String)startTimeComboBox.getSelectionModel().getSelectedItem(), dateTimeFormatter);
        }
        if (!endTimeComboBox.getSelectionModel().isEmpty()) {
            dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            endTime = LocalTime.parse((String)endTimeComboBox.getSelectionModel().getSelectedItem(), dateTimeFormatter);
        }

        System.out.println("Form data: ");
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Date: " + date);
        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + endTime);

        Agenda agenda = null;

        if (type.equals("Event")) {
            agenda = new Event(name, date.atTime(startTime), date.atTime(endTime));
            model.getEventManager().addEvent((Event)agenda);
        }
        else if (type.equals("Task")) {
            agenda = new Task(name, date.atTime(startTime), date.atTime(endTime));
            model.getTaskManager().addTask((Task)agenda);
        }

        // Test agendas
        /*
        Agenda event1 = new Event("Event1", LocalDateTime.of(2017, 3, 16, 10, 30), LocalDateTime.of(2017, 3, 16, 11, 0));
        Agenda event2 = new Event("Event2", LocalDateTime.of(2017, 3, 16, 10, 0), LocalDateTime.of(2017, 3, 16, 10, 30));
        Agenda event3 = new Event("Event3", LocalDateTime.of(2017, 3, 17, 8, 30), LocalDateTime.of(2017, 3, 17, 9, 0));
        Agenda task1 = new Task("Task1", LocalDateTime.of(2017, 3, 14, 4, 30), LocalDateTime.of(2017, 3, 14, 5, 0));

        List<Agenda> agendas = new ArrayList<>();
        agendas.add(event1);
        agendas.add(event2);
        agendas.add(event3);
        agendas.add(task1);

        model.getEventManager().addEvent((Event)event1);
        model.getEventManager().addEvent((Event)event2);
        model.getEventManager().addEvent((Event)event3);
        model.getTaskManager().addTask((Task)task1);
        */
        model.notifyObservers();
        System.out.println(model.getObserverList());
        // TODO Determine type of data then save to model.
    }

    public void discardFormData(ActionEvent actionEvent) {
        nameTextField.setText("");
        calendarDatePicker.setValue(null);
//        startTimeTextField.setText("");
//        endTimeTextField.setText("");
        startTimeComboBox.getSelectionModel().clearSelection();
        endTimeComboBox.getSelectionModel().clearSelection();

        startTimeComboBox.setItems(FXCollections.observableArrayList(timeList));
        endTimeComboBox.setItems(FXCollections.observableArrayList(timeList));
    }

    @FXML
    public void initialize() {
        timeList = new ArrayList<>(Arrays.asList("00:00", "00:30", "01:30", "01:30", "02:00", "02:30",
                                                 "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
                                                 "06:00", "06:30", "07:00", "07:30", "08:00", "08:30",
                                                 "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                                                 "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
                                                 "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
                                                 "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                                                 "21:00", "21:30", "22:00", "22:30", "23:00", "23:30")
        );

        startTimeComboBox.setItems(FXCollections.observableArrayList(timeList));
        endTimeComboBox.setItems(FXCollections.observableArrayList(timeList));

        startTimeComboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (endTimeComboBox.getSelectionModel().isEmpty() && (int)newValue != -1)
                    endTimeComboBox.setItems(FXCollections.observableArrayList(timeList.subList((Integer) newValue+1, timeList.size())));
            }
        });

        endTimeComboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (startTimeComboBox.getSelectionModel().isEmpty() && (int)newValue != -1)
                    startTimeComboBox.setItems(FXCollections.observableArrayList(timeList.subList(0, (Integer) newValue)));
            }
        });
    }
}
