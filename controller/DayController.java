package controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Agenda;
import model.AgendaUtility;
import model.CalendarModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 3/11/2017.
 */
public class DayController {

    @FXML private VBox agendaColumn;

    private CalendarModel model;

    public DayController(CalendarModel model) {
        this.model = model;
    }

    public void placeEntry(Agenda agenda) {
        int startIndex = agenda.getStartDate().getHour() * 2 + (agenda.getStartDate().getMinute() == 30 ? 1 : 0);
        int endIndex = (agenda.getEndDate().getHour() * 2 + (agenda.getEndDate().getMinute() == 30 ? 1 : 0));
        Text entry = new Text(agenda.getName());
        entry.setFill(Color.WHITE);
        entry.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        ((HBox)agendaColumn.getChildren().get(startIndex)).getChildren().add(entry);

        for (int i = startIndex; i < endIndex; i++) {
            String color = String.format("#%02x%02x%02x", agenda.getColor().getRed(),
                                                          agenda.getColor().getGreen(),
                                                          agenda.getColor().getBlue());
            agendaColumn.getChildren().get(i).setStyle("-fx-background-color: " + color + "; -fx-border-color: " + color + ";");
//            System.out.println(" wat" + agendaColumn.getChildren().get(i).getStyle());
        }
        System.out.println(agenda.getName() + " " + startIndex + " " + endIndex);
    }

    @FXML
    public void initialize() {
        List<Agenda> agendas = new ArrayList<>();

        if (MainController.viewEvents)agendas.addAll(model.getEventManager().getEvents());

        if (MainController.viewTasks)
            agendas.addAll(model.getTaskManager().getTodoList());

        agendas = AgendaUtility.sortAgendaByTime(agendas);
        agendas = AgendaUtility.getCertainDateAgendas(agendas, MainController.currentDate);

        for (Agenda a : agendas) {
            placeEntry(a);
        }
//        for (Node n : agendaColumn.getChildren()) {
//            System.out.println(n.getId());
//        }
    }

}
