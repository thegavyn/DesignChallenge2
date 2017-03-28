import java.util.Scanner;

/**
 * Created by Mark Gavin on 3/8/2017.
 */
public class Lister {
    ArrayList <Task>  todoList;
    Scanner sc = new Scanner(System.in);
    public Lister()
    {
        todoList = new ArrayList <Task>();

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

        Factory.makeProd(1, name, month, day, year, bigHand, smallHand);
    }

}
