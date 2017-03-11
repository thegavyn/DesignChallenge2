import java.util.Scanner;
/**
 * Created by Mark Gavin on 3/10/2017.
 */
public class Manager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        Lister mod = new Lister();
        Eventer emod = new Eventer();
        String name;
        int month;
        int day;
        int year;
        int bigHand;
        int smallHand;
        boolean finished = false;
        int cont;
        while (!finished) {
            System.out.println("What do you want to add? [1] - Task, [2] - Event: ");
            choice = sc.nextInt();
            if (choice == 1) {
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
                mod.addToDo(name, month, day, year, bigHand, smallHand);
                System.out.println("Do You want to Continue Adding Events/Tasks?: [1] - Yes/ [2] - No");
                cont = sc.nextInt();
                if(cont == 2)
                    finished = true;

            } else {
                System.out.println("Please Input name of Event: ");
                name = sc.next();
                System.out.println("Please Input month of Event date(1 - 12): ");
                month = sc.nextInt();
                System.out.println("Please Input day of Event date: ");
                day = sc.nextInt();
                System.out.println("Please Input Year of Event date: ");
                year = sc.nextInt();
                System.out.println("Please Input the big hand time: ");
                bigHand = sc.nextInt();
                System.out.println("Please Input the small hand time:");
                smallHand = sc.nextInt();
                emod.addEvent(name, month, day, year, bigHand, smallHand);
                System.out.println("Do You want to Continue Adding Events/Tasks?: [1] - Yes/ [2] - No");
                cont = sc.nextInt();
                if(cont == 2)
                    finished = true;
            }
        }
    }
}
