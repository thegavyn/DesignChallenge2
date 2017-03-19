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
public class TaskExporter {
    private List<Task> toShip;
    private List<Task> toShip2;
    private Formatter x;

    public TaskExporter()
    {
        toShip = new ArrayList<>();
        toShip2 = new ArrayList<>();

    }

    public void getTask(List <Task> n)
    {
        toShip = n;
    }
    public void getTask2(List <Task> n)//non-finished tasks
    {
        toShip2 = n;
    }

    public void openFile()
    {
        System.out.println("Checking2");
        try{
            x = new Formatter("Etasks.txt");
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
        Task hold;
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
