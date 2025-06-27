public class Test {
    public static void main(String[] args) {

        TaskManager tm = new TaskManager();

        Task t1 = new Task("Word");
        Task t2 = new Task("Bond");

        User u1 = new User("Freeman");
        User u2 = new User("Aaron");

        tm.addTask(t2);
        tm.addTask(t1);
        tm.addUser(u1);
        tm.addUser(u2);

        tm.assignTask(t1.getID(), u1.getuID());
        tm.assignTask(t2.getID(), u1.getuID());

        tm.assignTask(t1.getID(), u2.getuID());
        tm.assignTask(t2.getID(), u2.getuID());

        tm.markTaskComplete(t1.getID());
        tm.markTaskInProg(t2.getID());

        System.out.println("Before:\n" + tm.getTask(t1.getID()));

        // tm.deleteTask(t1.getID());
        // tm.deleteTask(t2.getID());

        // tm.unassignTask(t1.getID());

        // System.out.println("After:\n" + tm.getTask(t1.getID()));

        // System.out.println("After\n" + tm.getUser(u1.getuID()));
    }
}
