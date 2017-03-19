package model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mina on 3/10/2017.
 */
public class TaskManager {
    private List<Task> todoList;
    private List<Task> finishedTasks;
    private TaskExporter tx;
    private TaskImporter ti;
    public List<Task> getTodoList() {
        return todoList;
    }
    public List<Task> getFinishedTasks() { return finishedTasks; }

    public TaskManager() {
        todoList = new ArrayList<>();
        finishedTasks = new ArrayList<>();
        tx = new TaskExporter();
        ti = new TaskImporter();
    }

    public void addTask(Task task) {
        boolean isOverlapping = false;
        Task overlappingTask = null;

        for (Task t : todoList) {
            if (Checker.isDateOverlapping(t.getStartDate(), t.getEndDate(),
                    task.getStartDate(), task.getEndDate()))
                isOverlapping = true;
            overlappingTask = t;
            break;
        }

        if (isOverlapping)
            System.err.println("ERROR: Input task: \n" + task + " \noverlaps with existing task: \n" + overlappingTask);
        else
            todoList.add(task);
    }



    public void finishTasks(List<Task> finishedTasks) {
        for (Task t : finishedTasks) {
            t.markAsFinished();
            this.finishedTasks.add(t);
        }
    }

    public void shipOut()//exports all tasks
    {
        tx.getTask(todoList);
        tx.getTask2(finishedTasks);
        tx.openFile();
        tx.exportAll();
        tx.closeFile();
    }

    public void shipIn()//adds not finished tasks
    {
        int i;
        List <Task> holder;
        holder = new ArrayList<>();
        ti.readFile();
        ti.processData();
        holder = ti.DeliverN();

        for(i = 0; i < holder.size(); i++)
            todoList.add(holder.get(i));

        holder = ti.DeliverF();
        for(i = 0; i < holder.size(); i++)
            finishedTasks.add(holder.get(i));


    }

}
