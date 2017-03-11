import java.util.ArrayList;

/**
 * Created by Mark Gavin on 3/11/2017.
 */
public class Eventer {
    ArrayList<Event> eventClip;
    EventFactory ef;

    public Eventer()
    {
        eventClip = new ArrayList <>();
        ef = new EventFactory();
    }


    public void addEvent(String name, int month, int day, int year, int bigHand, int smallHand)
    {
        eventClip.add(ef.makingEvent(name, month, day, year, bigHand, smallHand));
    }
}
