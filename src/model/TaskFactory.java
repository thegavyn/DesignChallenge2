/**
 * Created by Mark Gavin on 3/8/2017.
 */
public class TaskFactory implements Factory {

    @Override
    public Product makeProd(int type, String name, int month, int day, int year, int bigHand, int smallHand){
        if(type == 1)
        {
            return new Task(name, month, day, year, bigHand, smallHand);
        }
    }
}
