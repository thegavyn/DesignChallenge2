package model;

import controller.MainController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Mina on 3/10/2017.
 */
public class EventManager {
    public List<Event> getEvents() {

//        if (!events.isEmpty())
//            for (Event e : events)
//                    if (e.getEndDate().toLocalDate().isBefore(MainController.currentDate))
//                        if (!finishedEvents.contains(e)) {
//                            finishedEvents.add(e);
//                            events.remove(e);
//                            e.markAsFinished();
////                            initialize();
//                        }

        if (!events.isEmpty())
            for (int i = 0; i < events.size(); i++) {
                Event e = events.get(i);
                if (e.getEndDate().toLocalDate().isBefore(MainController.currentDate))
                    if (!finishedEvents.contains(e)) {
                        finishedEvents.add(e);
                        events.remove(e);
                        e.markAsFinished();
//                            initialize();
                    }
            }
        return events;
    }
    public List<Event> getFinishedEvents() { return finishedEvents; }
    private EventExporter ex;
    private List<Event> events;
    public List<Event> finishedEvents;
    private EventImporter ix;

    public EventManager() {
        events = new ArrayList<>();
        finishedEvents = new ArrayList<>();
        ex = new EventExporter();
        ix = new EventImporter();
    }

    public void addEvent(Event event) {
        boolean isOverlapping = false;
        Event overlappingEvent = null;

        for (Event e : events) {
            if (Checker.isDateOverlapping(e.getStartDate(), e.getEndDate(),
                                  event.getStartDate(), event.getEndDate()))
                isOverlapping = true;
                overlappingEvent = e;
                break;
        }

        if (isOverlapping)
            System.err.println("ERROR: Input event: \n" + event + " \noverlaps with existing event: \n" + overlappingEvent);
        else
            events.add(event);
    }

    public void shipOut()
    {
        ex.getEvents(events);
        ex.getEvents2(finishedEvents);
        ex.openFile();
        ex.exportAll();
        ex.closeFile();
    }

    public void shipIn()
    {
        int i;
        List <Event> holder;
        holder = new ArrayList<>();
        ix.readFile();
        ix.processData();
        holder = ix.packageDeliveredN();

        for(i = 0; i < holder.size(); i++)
            events.add(holder.get(i));

        holder = ix.packageDeliveredF();
        for(i = 0; i < holder.size(); i++)
            finishedEvents.add(holder.get(i));

    }


}
