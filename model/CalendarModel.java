package model;

import controller.Subject;

import java.util.Observable;

/**
 * Created by Mina on 3/11/2017.
 */
public class CalendarModel extends Subject {

    public TaskManager taskManager;

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public EventManager getEventManager() {
        notifyObservers();
        return eventManager;
    }

    public EventManager eventManager;

    public CalendarModel() {
        taskManager = new TaskManager();
        eventManager = new EventManager();
    }

}
