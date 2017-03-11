import java.util.ArrayList;
/**
 * Created by Mark Gavin on 3/8/2017.
 */
public class Lister {
    ArrayList <Task>  todoList;
    TaskFactory tf;


    public Lister()
    {

        todoList = new ArrayList <>();
        tf = new TaskFactory();
    }

    public void addToDo(String name, int month, int day, int year, int bigHand, int smallHand)
    {
        todoList.add(tf.makingTask(name, month, day, year, bigHand, smallHand));
    }
}
