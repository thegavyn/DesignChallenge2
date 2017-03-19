package controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 3/12/2017.
 */
public abstract class Subject {

    public List<Observer> getObserverList() {
        return observerList;
    }

    List<Observer> observerList;

    public Subject() {
        observerList = new ArrayList<>();
    }

    public void attachObserver(Observer observer) {
        observerList.add(observer);
    }

    public void detachObserver(Observer observer) {
        observerList.remove(observer);
    }

    public void notifyObservers() {
        for (Observer o : observerList) {
            o.update(this);
        }
    }

}
