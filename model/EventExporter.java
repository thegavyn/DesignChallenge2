package model;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * Created by Mark Gavin on 3/19/2017.
 */
public class EventExporter {

    private List<Event> toShip;
    private List<Event> toShip2;
    private Formatter x;
    public EventExporter()
    {
        toShip = new ArrayList<>();
        toShip2 = new ArrayList<>();

    }

    public void getEvents(List <Event> n)
    {
        toShip = n;
    }
    public void getEvents2(List <Event> n)
    {
        toShip2 = n;
    }

    public void openFile()
    {
        System.out.println("Checking");
        try{
            x = new Formatter("Eevents.txt");
        }
        catch (Exception e)
        {
            System.out.println("Error Found");
        }

    }
    public void exportAll()
    {
        String n;
        LocalDateTime st, ed;
        boolean isDone;
        int i;
        Event hold;
        try{
            for(i = 0; i < toShip.size(); i++)
            {
                hold = toShip.get(i);
                n = hold.getName();
                st = hold.getStartDate();
                ed = hold.getEndDate();
                isDone = hold.isFinished();
                x.format("%s,%s,%s,%s%n", n, st, ed, isDone);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error Found");
        }
        try{
            for(i = 0; i < toShip2.size(); i++)
            {
                hold = toShip2.get(i);
                n = hold.getName();
                st = hold.getStartDate();
                ed = hold.getEndDate();
                isDone = hold.isFinished();
                x.format("%s,%s,%s,%s%n", n, st, ed, isDone);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error Found");
        }
    }
    public void closeFile()
    {
        x.close();
    }


}
