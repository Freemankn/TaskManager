import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

//It acts as the central controller that:
// Knows about both the Task and the User
// Has access to all tasks and users
// Ensures both sides are updated consistently

// Will need a construct later of persistent storage

public class TaskManager {
    // private List<Task> tasks = new ArrayList<>();
    // private List<User> users = new ArrayList<>();
    private LinkedHashMap<Integer, Task> taskIDHashMap = new LinkedHashMap<>(); // <id, Task>
    private LinkedHashMap<Integer, User> userIDHashMap = new LinkedHashMap<>(); // <uID, User>

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

    // Filtering

    public void viewTasksByStatus(TaskStatus status) {
        System.out.println("Filtered Tasks with status:" + status);
        for (Task task : taskIDHashMap.values()) {
            if (task.getStatus() == status) {
                System.out.println("[ID: " + task.getID() + "] " + task.getTitle() + " (" + task.getStatus() + ")");
            } else {
                continue;
            }
        }
    }

    public void viewTasksByDueDate(String dueDate) {
        System.out.println("Tasks due on: " + dueDate);
        for (Task task : taskIDHashMap.values()) {
            if (task.getDueDate().equals(dueDate)) {
                System.out.println("[ID: " + task.getID() + "] " + task.getTitle() + " (" + task.getStatus() + ")");
            } else {
                continue;
            }
        }
    }

    public void viewTasksByUser(int uID) {
        System.out.println("Tasks assigned to " + getUser(uID).getName() + ":");
        for (Task task : taskIDHashMap.values()) {
            if (task.getAssignedUsers().contains(getUser(uID))) {
                System.out.println("[ID: " + task.getID() + "] " + task.getTitle() + " (" +
                        task.getStatus() + ")");
            } else {
                continue;
            }
        }
    }

    // View Tasks

    public void viewTask(int taskID) {
        System.out.println(taskIDHashMap.get(taskID));
    }

    public void viewTasks() {
        System.out.println("All Tasks:");
        for (Task task : taskIDHashMap.values()) {
            System.out.println("[ID: " + task.getID() + "] " + task.getTitle() + " (" + task.getStatus() + ")");
        }
    }

    // View User

    public void viewUser(int uID) {
        System.out.println(userIDHashMap.get(uID));
    }

    public void viewUsers() {
        System.out.println("All Users:");
        for (User user : userIDHashMap.values()) {
            System.out.println("[User ID: " + user.getuID() + "] " + user.getName() + " (" + user.getRole() + ")");
        }
    }

    // public List<Task> getTasksByStatus(String status) {
    // /* ... */ }
}
