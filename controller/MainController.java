package controller;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainController implements Observer {
    @FXML private BorderPane mainBorderPane;
    @FXML private VBox miniCalendarVBox;
    @FXML private DatePicker calendarDatePicker;
    @FXML private Label currentDateLabel;

    private DatePicker datePicker;
    private DatePickerContent popupContent;

    @FXML private CheckBox eventCheckBox;
    @FXML private CheckBox taskCheckBox;

    @FXML private ToggleButton dayViewToggleButton;

    private AgendaController agendaController;
    private DayController dayController;
    private CreateController createController;

    private String currentView;

    public static LocalDate currentDate;
    private StringProperty currentDateProperty;
    public static boolean viewTasks;
    public static boolean viewEvents;

    private CalendarModel model;

    public MainController(CalendarModel model) {
        this.model = model;
        model.attachObserver(this);

        viewEvents = false;
        viewTasks = false;
    }

    public void updateSubView() {
        switch (currentView) {
            case "dayView":
                loadDayView();
                break;
            case "agendaView":
                loadAgendaView();
                break;
        }
    }

    public void checkEventCheckBox() {
        viewEvents = eventCheckBox.selectedProperty().get();
        updateSubView();
    }

    public void checkTaskCheckBox() {
        viewTasks = taskCheckBox.selectedProperty().get();
        updateSubView();
    }

    public void loadDayView() {
        currentView = "dayView";
        try {
            dayController = new DayController(model);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dayView.fxml"));
            fxmlLoader.setController(dayController);
            Node root = fxmlLoader.load();
            mainBorderPane.setCenter(root);
//            mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/view/dayView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAgendaView() {
        currentView = "agendaView";
//        Agenda event1 = new Event("Event1", LocalDateTime.of(2017, 3, 11, 10, 30), LocalDateTime.of(2017, 3, 11, 11, 0));
//        Agenda event2 = new Event("Event2", LocalDateTime.of(2017, 3, 11, 10, 0), LocalDateTime.of(2017, 3, 10, 10, 30));
//        Agenda event3 = new Event("Event3", LocalDateTime.of(2017, 3, 11, 8, 30), LocalDateTime.of(2017, 3, 11, 9, 0));
//        Agenda task1 = new Task("Task1", LocalDateTime.of(2017, 3, 11, 4, 30), LocalDateTime.of(2017, 3, 11, 5, 0));
//
//        model = new CalendarModel();
//        model.getEventManager().addEvent((Event)event1);
//        model.getEventManager().addEvent((Event)event2);
//        model.getEventManager().addEvent((Event)event3);
//        model.getTaskManager().addTask((Task)task1);
        List<Agenda> agendas = new ArrayList<>();
        agendas.addAll(model.getEventManager().getEvents());
        agendas.addAll(model.getTaskManager().getTodoList());

        try {
            agendaController = new AgendaController(model);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/agendaView.fxml"));
            fxmlLoader.setController(agendaController);
            Node root = fxmlLoader.load();
            mainBorderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setModel(CalendarModel model) {
        this.model = model;
    }

    public CalendarModel getModel() {

        return model;
    }

    public void loadCreateView() {
        currentView = "createView";
        try {
//            mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/view/createView.fxml")));
            createController = new CreateController(model);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/createView.fxml"));
            fxmlLoader.setController(createController);
            Node root = fxmlLoader.load();
            mainBorderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<DateCell> getAllDateCells(DatePickerContent content) {
        List<DateCell> result = new ArrayList<>();

        for (Node n : content.getChildren())
            if (n instanceof GridPane) {
                GridPane grid = (GridPane) n;
                for (Node gChild : grid.getChildren())
                    if (gChild instanceof DateCell)
                        result.add((DateCell) gChild);
            }

        return result;
    }

    private DateCell findDayCellForDate(LocalDate date) {
        List<DateCell> dateCells = getAllDateCells(popupContent);

        for (DateCell dc : dateCells) {
            if (dc.getItem().equals(date))
                return dc;
        }

        return null;

//        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
//        int nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); // Gets the maximum number of days (i.e February - 28 days)
//        int som = cal.get(GregorianCalendar.DAY_OF_WEEK);
//
//        int row = (i + som - 2) / 7;
//        int column = (i + som - 2) % 7;
//
//        for (int i = 0; i < dayCellDates.length; i++) {
//            if (date.equals(dayCellDates[i])) {
//                return dateCells.get(i);
//            }
//        }
//        return dateCells.get(dateCells.size()/2+1);
    }

    public void setDateToday() {
        currentDate = LocalDate.now();
        currentDateProperty.set(currentDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        popupContent.goToDate(currentDate, true);
//        findDayCellForDate(currentDate);
        datePicker.setValue(currentDate);
        updateSubView();
    }

    @FXML
    public void initialize() {
        model.eventManager.shipInN();
        model.eventManager.shipInF();
        model.taskManager.shipInN();
        model.taskManager.shipInF();
        System.out.println(model.eventManager.finishedEvents.size());
        datePicker = new DatePicker(LocalDate.now());
        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        popupContent = (DatePickerContent)datePickerSkin.getPopupContent();
        List<DateCell> dateCells = getAllDateCells(popupContent);
        for (DateCell cell : dateCells) {
//            System.out.println(cell.getItem());
            if (cell.getItem() != null) {
            }
            cell.addEventHandler(
                    MouseEvent.MOUSE_PRESSED,(e) -> {
//                        System.out.println("Mouse clicked :" + cell.getItem());
                        currentDate = cell.getItem();
                        currentDateProperty.set(currentDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
                        updateSubView();
                    }
            );
        }

        currentDate = LocalDate.now();
//        miniCalendarVBox.getChildren().add(datePicker);
        popupContent.goToDate(currentDate  , true);

//        miniCalendarVBox.getChildren().add(datePickerSkin);
        miniCalendarVBox.getChildren().add(popupContent);

        currentDateProperty = new SimpleStringProperty(currentDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));

//        currentDate = LocalDate.now();
//        currentDateLabel.setText(currentDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        currentDateLabel.textProperty().bind(currentDateProperty);

        dayViewToggleButton.selectedProperty().set(true);
        loadDayView();
    }

    @Override
    public void update(Subject subject) {
        CalendarModel model;
        model = (CalendarModel)subject;
        this.model = model;
    }
}
