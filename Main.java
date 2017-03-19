import controller.AgendaController;
import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import model.Event;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main extends Application {
    CalendarModel model;
    MainController mainController;
    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new CalendarModel();
        mainController = new MainController(model);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
        fxmlLoader.setController(mainController);
        Parent root = fxmlLoader.load();
//        mainBorderPane.setCenter(root);

//        Parent root = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
        primaryStage.setTitle("Productivity Calendar");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
//        EventManager eventManager = new EventManager();
//
//        LocalDateTime startDate1 = LocalDateTime.of(2017, 3, 10, 12, 0);
//        LocalDateTime endDate1 = LocalDateTime.of(2017, 3, 10, 12, 30);
//
//        LocalDateTime startDate2 = LocalDateTime.of(2017, 3, 10, 12, 0);
//        LocalDateTime endDate2 = LocalDateTime.of(2017, 3, 10, 12, 30);
//
//        TaskManager taskManager = new TaskManager();
//
//        taskManager.addTask(new Task("Task1", startDate1, endDate1));
//        taskManager.addTask(new Task("Task2", startDate2, endDate2));
//
//        System.out.println(Checker.isDateOverlapping(startDate1, endDate1, startDate2, endDate2));
    }

    @Override
    public void stop(){
        System.out.println("Stage is closing");
        model.eventManager.shipOut();//Saving events
        model.taskManager.shipOut();//Saving tasks
    }
}
