package controller;

import model.CalendarModel;

/**
 * Created by Mina on 3/12/2017.
 */
public abstract class Controller {

    public CalendarModel getModel() {
        return model;
    }

    protected CalendarModel model;

    public Controller(CalendarModel model) {
        this.model = model;
    }

}
