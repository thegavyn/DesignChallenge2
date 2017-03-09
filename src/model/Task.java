/**
 * Created by Mark Gavin on 3/8/2017.
 */
public class Task implements Product {
    String name;
    int month;
    int day;
    int year;
    int bigHand;
    int smallHand;

    public Task(String name, int month, int day, int year, int bigHand, int smallHand){
        this.name = name;
        this.month = month;
        this.day = day;
        this.year = year;
        this.bigHand = bigHand;
        this.smallHand = smallHand;
    }
}