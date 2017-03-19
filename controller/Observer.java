package controller;

import model.CalendarModel;
import model.Model;

/**
 * Created by Mina on 3/12/2017.
 */
public interface Observer {

    void update(Subject subject);

}
