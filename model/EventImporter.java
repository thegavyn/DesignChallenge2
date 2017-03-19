package model;
import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.lang.Boolean.*;
/**
 * Created by Mark Gavin on 3/19/2017.
 */

public class EventImporter {
    private List<Event> toReturnN;
    private List<Event> toReturnF;
    private Scanner x;
    protected List<String> data;
    protected List<Event> events;

    public EventImporter()
    {
        toReturnN = new ArrayList<>();
        toReturnF = new ArrayList<>();
    }

    public void readFile() {
        BufferedReader fileReader;
        String line;

        System.out.println("Reading data from TXT file...");

        fileReader = null;

        try {
            data = new ArrayList<>();

            fileReader = new BufferedReader(new FileReader("Eevents.txt"));

            while ((line = fileReader.readLine()) != null)
                data.add(line);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                fileReader.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void processData() {
        String n;
        LocalDateTime st, ed;
        st = null;
        ed = st;
        boolean b;
        for (String line : data) {
            String[] parts = line.split(",");

            //Calendar date = Calendar.getInstance();
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                st = LocalDateTime.parse(parts[1]);
            } catch (Exception e) {
                System.out.println("Error 1");
            }
            try {
                ed = LocalDateTime.parse(parts[2]);
            } catch (Exception e) {
                System.out.println("Error 2");
            }
            n = parts[0];
            b = Boolean.parseBoolean(parts[3]);

            Event event = new Event(n, st, ed);

            if(b == false)
                toReturnN.add(event);
            else
                toReturnF.add(event);

        }
    }

    public List <Event> packageDeliveredN()//returns not finished events
    {
        return toReturnN;
    }
    public List <Event> packageDeliveredF()//returns finished events
    {
        return toReturnF;
    }



}
