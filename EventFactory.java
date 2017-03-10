/**
 * Created by Mark Gavin on 3/10/2017.
 */
public class EventFactory implements Factory {
        /*
        @Override
        public static Product makeProd(int type, String name, int month, int day, int year, int bigHand, int smallHand){
            return new Task(name, month, day, year, bigHand, smallHand);

        }
        */
        public Event makingEvent (String name, int month, int day, int year, int bigHand, int smallHand)
        {
            return new Event(name, month, day, year, bigHand, smallHand);
        }
}

