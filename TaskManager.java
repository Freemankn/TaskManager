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
        return userIDHashMap.get(uID);
    }

    public Task getTask(int taskId) {
        return taskIDHashMap.get(taskId);
    }

    public List<User> getUsersAssignedToTask(int taskId) {
        return getTask(taskId).getAssignedUsers();
    }

    public List<Task> getTasksAssignedToUser(int userId) {
        return getUser(userId).getTasks();
    }

    // setters

    public void addTask(Task task) {
        taskIDHashMap.put(task.getID(), task);
    }

    public void addUser(User user) {
        userIDHashMap.put(user.getuID(), user);
    }

    public void assignTask(int taskId, int uID) {
        if (!getTask(taskId).getAssignedUsers().contains(getUser(uID))) {
            getTask(taskId).assignUser(getUser(uID));
            getUser(uID).assignTask(getTask(taskId));
        }
    }

    public void assignTasktoUsers(int taskId, int[] uIDs) {
        for (int uID : uIDs) {
            assignTask(taskId, uID);
        }
    }

    public void editTask(int taskId, String title, String description, String dueDate) {
        getTask(taskId).setTitle(title);
        getTask(taskId).setDescription(description);
        getTask(taskId).setDueDate(dueDate);
    }

    // public void unassignTask(int taskId) {
    // Task task = getTask(taskId);
    // task.getAssignedUsers().unassignTask(task);
    // }

    // public void deleteTask(int taskId) {
    // unassignTask(taskId);
    // taskIDHashMap.remove(taskId);
    // }

    // Status

    public void markTaskComplete(int taskId) {
        getTask(taskId).setStatus(TaskStatus.DONE);
    }

    public void markTaskInProg(int taskId) {
        getTask(taskId).setStatus(TaskStatus.IN_PROGRESS);
    }

    // Due Date

    public void setDueDate(int taskID, String dueDate) {
        getTask(taskID).setDueDate(dueDate);
    }

    // public List<Task> getTasksByStatus(String status) {
    // /* ... */ }
}
