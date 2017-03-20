package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.*;
import sun.applet.Main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Mina on 3/11/2017.
 */
public class AgendaController extends Controller {
    @FXML
    private GridPane agendaGridPane;
    @FXML
    private ToggleButton showFinishedEventsToggleButton;
    @FXML
    private ToggleButton showFinishedTasksToggleButton;

    //private CalendarModel model;
    private List<Agenda> markedForCompletion;
    private List<HBox> agendaEntries;
    private List<Integer> agendaRowIndeces;
    private HashMap<Integer, Agenda> agendaHashMap;
    private boolean finishedEventsFlag;
    private boolean finishedTasksFlag;

    public AgendaController(CalendarModel model) {
        //this.model = model;
		super(model);
        markedForCompletion = new ArrayList<>();
        agendaEntries = new ArrayList<>();
        agendaRowIndeces = new ArrayList<>();
    }

    public void updateAgenda() {

    }

    //    public void markForFinished() {
//
//    }
    public void deleteRow(int row) {
        for (int i = row + 1; i < getRowCount(agendaGridPane); i++) {
//            agendaGridPane.get
        }

        for (Node n : agendaGridPane.getChildren()) {
            if (GridPane.getRowIndex(n) > 0) {
                if (GridPane.getRowIndex(n) >= getRowCount(agendaGridPane) - 1) {
//                    agendaGridPane.
                }
                agendaGridPane.add(n, GridPane.getRowIndex(n) - 1, GridPane.getColumnIndex(n));
            }
        }
    }

    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public int getRowCount(GridPane pane) {
        int numRows = pane.getRowConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if (rowIndex != null) {
                    numRows = Math.max(numRows, rowIndex + 1);
                }
            }
        }
        return numRows;
    }

    public void showFinishedEvents() {
        finishedEventsFlag = showFinishedEventsToggleButton.selectedProperty().get();
        initialize();
    }

    public void showFinishedTasks() {
        finishedTasksFlag = showFinishedTasksToggleButton.selectedProperty().get();
        initialize();
    }

    @FXML
    public void initialize() {
        List<Agenda> agendas = new ArrayList<>();
        LocalDate dateHeader = null;
        agendaHashMap = new HashMap<>();

        agendas.addAll(model.getEventManager().getEvents());
        agendas.addAll(model.getTaskManager().getTodoList());

        if (finishedEventsFlag)
            agendas.addAll(model.getEventManager().getFinishedEvents());
        if (finishedTasksFlag)
            agendas.addAll(model.getTaskManager().getFinishedTasks());

        //agendas = AgendaUtility.getCertainDateAgendas(agendas, MainController.currentDate);
		agendas = AgendaUtility.sortAgendaByDateTime(agendas);
		//for (Agenda agenda : agendas) {
		//	System.out.println(agenda);
		//}
		
        if (agendas.isEmpty()) {
            agendaGridPane.getChildren().clear();

            agendaGridPane.add(new Text("No agendas for today!"), 0, 0);
        } else {
            agendas.clear();
//        agendaRowIndeces = new ArrayList<>();

            // Intialization proper
//        List<Agenda> agendas = new ArrayList<>();
            if (MainController.viewEvents)
                agendas.addAll(model.getEventManager().getEvents());
//
//        if (!agendas.isEmpty())
//            for (Agenda a : agendas)
//                if (a instanceof Event)
//                    if (a.getEndDate().toLocalDate().isBefore(MainController.currentDate))
//                        if (!model.getEventManager().getFinishedEvents().contains(a)) {
//                            model.getEventManager().getFinishedEvents().add((Event) a);
//                            model.getEventManager().getEvents().remove(a);
//                            a.markAsFinished();
//                            model.notifyObservers();
////                            initialize();
//                        }

            if (MainController.viewTasks)
                agendas.addAll(model.getTaskManager().getTodoList());

            if (finishedEventsFlag)
                agendas.addAll(model.getEventManager().getFinishedEvents());
            if (finishedTasksFlag)
                agendas.addAll(model.getTaskManager().getFinishedTasks());

			AgendaUtility.checkForFinishedEvents(agendas, MainController.currentDate);
            agendas = AgendaUtility.sortAgendaByDateTime(agendas);
            //agendas = AgendaUtility.getCertainDateAgendas(agendas, MainController.currentDate);

            String line;

            int row = 0;
            agendaGridPane.getChildren().clear();

            for (Agenda a : agendas) {

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        Agenda agenda = agendaHashMap.get(GridPane.getRowIndex(checkBox));
//                    System.out.println(oldValue);
//                    System.out.println(newValue);
//                    System.out.println();

                        if (agenda instanceof Event) {
                            if (newValue) {
                                if (!model.getEventManager().getFinishedEvents().contains(agenda)) {
                                    model.getEventManager().getFinishedEvents().add(((Event) agenda));
                                    model.getEventManager().getEvents().remove(agenda);
                                    agenda.markAsFinished();

                                    ((Text) getNodeFromGridPane(agendaGridPane, 1, GridPane.getRowIndex(checkBox))).setFill(Color.web("#d3d3d3"));
                                    ((Text) getNodeFromGridPane(agendaGridPane, 2, GridPane.getRowIndex(checkBox))).setFill(Color.web("#d3d3d3"));
                                }
                            } else {
                                if (model.getEventManager().getFinishedEvents().contains(agenda)) {
                                    model.getEventManager().getFinishedEvents().remove(agenda);
                                    model.getEventManager().getEvents().add((Event) agenda);
                                    agenda.unmarkAsFinished();

                                    ((Text) getNodeFromGridPane(agendaGridPane, 1, GridPane.getRowIndex(checkBox))).setFill(AgendaUtility.convertColor(agenda.getColor()));
                                    ((Text) getNodeFromGridPane(agendaGridPane, 2, GridPane.getRowIndex(checkBox))).setFill(AgendaUtility.convertColor(agenda.getColor()));
                                }
                            }
                        } else if (agenda instanceof Task) {
                            if (newValue) {
                                if (!model.getTaskManager().getFinishedTasks().contains(agenda)) {
                                    model.getTaskManager().getFinishedTasks().add(((Task) agenda));
                                    model.getTaskManager().getTodoList().remove(agenda);
                                    agenda.markAsFinished();

                                    ((Text) getNodeFromGridPane(agendaGridPane, 1, GridPane.getRowIndex(checkBox))).setFill(Color.web("#d3d3d3"));
                                    ((Text) getNodeFromGridPane(agendaGridPane, 2, GridPane.getRowIndex(checkBox))).setFill(Color.web("#d3d3d3"));
                                }
                            } else {
                                if (model.getTaskManager().getFinishedTasks().contains(agenda)) {
                                    model.getTaskManager().getFinishedTasks().remove(agenda);
                                    model.getTaskManager().getTodoList().add((Task) agenda);
                                    agenda.unmarkAsFinished();

                                    ((Text) getNodeFromGridPane(agendaGridPane, 1, GridPane.getRowIndex(checkBox))).setFill(AgendaUtility.convertColor(agenda.getColor()));
                                    ((Text) getNodeFromGridPane(agendaGridPane, 2, GridPane.getRowIndex(checkBox))).setFill(AgendaUtility.convertColor(agenda.getColor()));
                                }
                            }
                        }

                        model.notifyObservers();
                    }
                });

                Text timeEntry = new Text();
                timeEntry.setText(a.getStartDate().toLocalTime() + " - " + a.getEndDate().toLocalTime());
                Text textEntry = new Text();
                textEntry.setText(a.getName());
                textEntry.setFill(AgendaUtility.convertColor(a.getColor()));
                timeEntry.setFill(AgendaUtility.convertColor(a.getColor()));
                textEntry.setFont(Font.font("Arial", 16));
                timeEntry.setFont(Font.font("Arial", 16));
                Button deleteButton = new Button("x");
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Agenda agenda = agendaHashMap.get(GridPane.getRowIndex(deleteButton));

                        if (agenda instanceof Event) {
							if (model.getEventManager().getFinishedEvents().contains(agenda))
								model.getEventManager().getFinishedEvents().remove(agenda);
							else
								model.getEventManager().getEvents().remove(agenda);
                        } else if (agenda instanceof Task) {
							if (model.getTaskManager().getFinishedTasks().contains(agenda))
								model.getTaskManager().getFinishedTasks().remove(agenda);
							else
								model.getTaskManager().getTodoList().remove(agenda);
                        }

                        model.notifyObservers();
                        initialize();
                    }
                });


                if (dateHeader == null || !dateHeader.equals(a.getStartDate().toLocalDate())) {
                    dateHeader = a.getStartDate().toLocalDate();
                    Text dateHeaderText = new Text(dateHeader.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
                    agendaGridPane.add(dateHeaderText, 0, row);
                    row++;
                }
                //else if (dateHeader.isBefore(a.getStartDate.toLocalDate())) {
                //dateHeader = a.getStartDate().toLocalDate();
                //Text dateHeaderText = new Text(dateHeader.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
                //agendaGridPane.add(dateHeaderText, 0, row);
                //row++;
                //}
                if (a instanceof Event) {
                    checkBox.setVisible(false);
                }
				
                agendaGridPane.add(checkBox, 0, row);
                agendaGridPane.add(timeEntry, 1, row);
                agendaGridPane.add(textEntry, 2, row);
                agendaGridPane.add(deleteButton, 3, row);

                if (a.isFinished()) {
                    checkBox.selectedProperty().set(true);
                    if (a instanceof Event)
                        checkBox.setVisible(false);

                    ((Text) getNodeFromGridPane(agendaGridPane, 1, GridPane.getRowIndex(checkBox))).setFill(Color.web("#d3d3d3"));
                    ((Text) getNodeFromGridPane(agendaGridPane, 2, GridPane.getRowIndex(checkBox))).setFill(Color.web("#d3d3d3"));
                }

                agendaHashMap.put(row, a);
                row++;
            }

        }
    }
}