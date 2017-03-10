import java.util.Scanner;
import java.util.ArrayList;
/**
 * Created by Mark Gavin on 3/8/2017.
 */
public class Lister {
    ArrayList <Task>  todoList;
    ArrayList <Event> events;
    Scanner sc = new Scanner(System.in);
    TaskFactory tf;
    EventFactory ef;

    public Lister()
    {

        todoList = new ArrayList <>();
        events = new ArrayList <>();
        tf = new TaskFactory();
        ef = new EventFactory();
    }

    public void addToDo()
    {
        String name;
        int month;
        int day;
        int year;
        int bigHand;
        int smallHand;
        System.out.println("Please Input name of Task: ");
        name = sc.next();
        System.out.println("Please Input month of Task due date(1 - 12): ");
        month = sc.nextInt();
        System.out.println("Please Input day of Task due date: ");
        day = sc.nextInt();
        System.out.println("Please Input Year of Task due date: ");
        year = sc.nextInt();
        System.out.println("Please Input the big hand due time: ");
        bigHand = sc.nextInt();
        System.out.println("Please Input the small hand due time:");
        smallHand = sc.nextInt();
        todoList.add(tf.makingTask(name, month, day, year, bigHand, smallHand));
    }
    public void addEvent()
    {
        String name;
        int month;
        int day;
        int year;
        int bigHand;
        int smallHand;
        System.out.println("Please Input name of Event: ");
        name = sc.next();
        System.out.println("Please Input month of Event date(1 - 12): ");
        month = sc.nextInt();
        System.out.println("Please Input day of Event date: ");
        day = sc.nextInt();
        System.out.println("Please Input Year of Event date: ");
        year = sc.nextInt();
        System.out.println("Please Input the big hand due time: ");
        bigHand = sc.nextInt();
        System.out.println("Please Input the small hand due time:");
        smallHand = sc.nextInt();
        events.add(ef.makingEvent(name, month, day, year, bigHand, smallHand));
    }
}
