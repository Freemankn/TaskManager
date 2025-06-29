import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        TaskManager tm = new TaskManager();

        Task t1 = new Task("Word");
        Task t2 = new Task("Bond");
        Task t3 = new Task("Worde");
        Task t4 = new Task("Bondi");

        User u1 = new User("Freeman");
        User u2 = new User("Aaron");

        u1.setRole("Admin");
        u2.setRole("Admin");

        tm.addTask(t1);
        tm.addTask(t2);
        tm.addTask(t3);
        tm.addTask(t4);
        tm.addUser(u1);
        tm.addUser(u2);

        tm.assignTasktoUser(t1.getID(), u1.getuID());
        tm.assignTasktoUser(t2.getID(), u1.getuID());

        tm.assignTasktoUser(t1.getID(), u2.getuID());
        tm.assignTasktoUser(t2.getID(), u2.getuID());

        tm.setStatus(1, TaskStatus.IN_PROGRESS);

        tm.setDueDate(t1.getID(), "6/25/25");
        tm.setDueDate(t2.getID(), "6/25/25");
        tm.setDueDate(t3.getID(), "6/26/25");
        tm.setDueDate(t4.getID(), "6/26/25");

        tm.viewTasks();
        tm.removeMultipleTasks(new ArrayList<Integer>(List.of(t1.getID(), t2.getID(), t3.getID())));
        tm.viewTasks();

        // tm.viewTask(t1.getID());
        // tm.viewUser(u1.getuID());

        // tm.removeTask(t1.getID());

        // System.out.println();

        // tm.viewTask(t1.getID());

        // tm.viewUser(u1.getuID());

        // tm.viewUsers();

        // tm.viewUser(1);

        // tm.viewUsersByRole("Admin");

        // tm.viewTasksByStatus(TaskStatus.IN_PROGRESS);

        // tm.viewTasksByDueDate("6/25/25");

        // System.out.println("Before:\n" + tm.viewTasks());

        // tm.deleteTask(t1.getID());
        // tm.deleteTask(t2.getID());

        // tm.unassignTask(t1.getID());

        // System.out.println("After:\n" + tm.getTask(t1.getID()));

        // System.out.println("After\n" + tm.getUser(u1.getuID()));
    }
}
