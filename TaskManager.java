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
    private HashMap<Integer, Task> taskIDHashMap = new HashMap<>(); // <id, Task>
    private HashMap<Integer, User> userIDHashMap = new HashMap<>(); // <uID, User>

    // getters
    public User getUser(int uID) {
        if (userIDHashMap.getOrDefault(uID, null) != null) {
            return userIDHashMap.get(uID);
        }
        return null;
    }

    public Task getTask(int taskId) {
        if (taskIDHashMap.getOrDefault(taskId, null) != null) {
            return (taskIDHashMap.get(taskId));
        }
        return null;
    }

    // setters

    public void addTask(Task task) {
        taskIDHashMap.put(task.getID(), task);
    }

    public void addUser(User user) {
        userIDHashMap.put(user.getuID(), user);
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

    public void editTask(int taskId, String title, String description, String dueDate) {
        taskIDHashMap.get(taskId).setTitle(title);
        taskIDHashMap.get(taskId).setDescription(description);
        taskIDHashMap.get(taskId).setDueDate(dueDate);
        // taskIDHashMap.get(taskId).setStatus(status);
    }

    public void deleteTask(int taskId) {
        Task task = taskIDHashMap.get(taskId);
        task.getAssignedUser().unassignTask(task);
        taskIDHashMap.remove(taskId);
    }

    public void markTaskComplete(int taskId) {
        /* ... */ }

    // public List<Task> getTasksByStatus(String status) {
    // /* ... */ }
}
