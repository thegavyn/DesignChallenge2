package model;

import java.awt.*;
import java.time.LocalDateTime;

/**
 * Created by Mina on 3/10/2017.
 */
public abstract class Agenda {

    protected String name;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected boolean isFinished;

    public Color getColor() {
        return color;
    }

    protected Color color;

    public Agenda(String name, LocalDateTime startDate, LocalDateTime endDate, Color color) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.color = color;
        isFinished = false;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void markAsFinished() {
        isFinished = true;
    }

    public void unmarkAsFinished() {
        isFinished = false;
    }

    public String toString() {
        return startDate.toString() + " - " + endDate.toString() + " : " + name + " (" + color.toString() + ")";
    }

}
