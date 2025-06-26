import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//It acts as the central controller that:
// Knows about both the Task and the User
// Has access to all tasks and users
// Ensures both sides are updated consistently

// Will need a construct later of persistent storage

public class TaskManager {
    // private List<Task> tasks = new ArrayList<>();
    // private List<User> users = new ArrayList<>();
    private HashMap<Integer, Task> taskIDHashMap = new HashMap<>();
    private HashMap<Integer, User> userIDHashMap = new HashMap<>();

    public void addTask(Task task) {
        taskIDHashMap.put(task.getID(), task);
        // this.tasks.add(task);
    }

    public void addUser(User user) {
        userIDHashMap.put(user.getuID(), user);
        // this.users.add(user);
    }

    public void assignTask(int taskId, int uID) {
        if (taskIDHashMap.get(taskId).getAssignedUser() == null) {
            taskIDHashMap.get(taskId).setAssignedUser(userIDHashMap.get(uID));
            userIDHashMap.get(uID).assignTask(taskIDHashMap.get(taskId));
        }
        // else {
        // userIDHashMap.get(uID).assignTask(taskIDHashMap.get(taskId));
        // }
    }

    public void editTask(int taskId, Task updatedTask) {
        /* ... */ }

    public void deleteTask(int taskId) {
        /* ... */ }

    public void markTaskComplete(int taskId) {
        /* ... */ }

    // public List<Task> getTasksByStatus(String status) {
    // /* ... */ }
}
