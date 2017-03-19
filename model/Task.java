package model;

import java.awt.*;
import java.time.LocalDateTime;

/**
 * Created by Mark Gavin on 3/8/2017.
 */
public class Task extends Agenda implements Product{
//    String name;
//    int month;
//    int day;
//    int year;
//    int bigHand;
//    int smallHand;
//    boolean isFinished;
//
//    public Task(String name, int month, int day, int year, int bigHand, int smallHand){
//        this.name = name;
//        this.month = month;
//        this.day = day;
//        this.year = year;
//        this.bigHand = bigHand;
//        this.smallHand = smallHand;
//        isFinished = false;
//    }

    public Task(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name, startDate, endDate, Color.GREEN);
        isFinished = false;
    }

}