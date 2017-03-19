package model;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

/**
 * Created by Mina on 3/10/2017.
 */
public class Event extends Agenda {

//    private String name;
////    private Calendar startDate;
////    private Calendar endDate;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
//    private Color color;

//    public Event(String name, Calendar startDate, Calendar endDate, Color color) {
//        this.name = name;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.color = color;
//    }
//
//    public Calendar getStartDate() {
//        return startDate;
//    }
//
//    public Calendar getEndDate() {
//        return endDate;
//    }

//    public Event(String name, LocalDateTime startDate, LocalDateTime endDate, Color color) {
//        this.name = name;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.color = color;
//    }

    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name, startDate, endDate, Color.BLUE);
    }

}
