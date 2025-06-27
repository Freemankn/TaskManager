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

    public ArrayList<Integer> getUIDsAssignedToTask(int taskId) {
        ArrayList<Integer> uIDs = new ArrayList<Integer>();
        for (User user : getTask(taskId).getAssignedUsers()) {
            uIDs.add(user.getuID());
        }
        return uIDs;
    }

    public ArrayList<Integer> getTaskIDsAssignedToUser(int userId) {
        ArrayList<Integer> taskIDs = new ArrayList<Integer>();
        for (Task task : getUser(userId).getTasks()) {
            taskIDs.add(task.getID());
        }
        return taskIDs;
    }

    // setters

    public void addTask(Task task) {
        taskIDHashMap.put(task.getID(), task);
    }

    public void addUser(User user) {
        userIDHashMap.put(user.getuID(), user);
    }

    public void assignTasktoUser(int taskId, int uID) {
        if (!getTask(taskId).getAssignedUsers().contains(getUser(uID))) {
            getTask(taskId).assignUser(getUser(uID));
            getUser(uID).assignTask(getTask(taskId));
        }
    }

    public void assignTasktoUsers(int taskId, ArrayList<Integer> uIDs) {
        for (Integer uID : uIDs) {
            assignTasktoUser(taskId, uID);
        }
    }

    // Edit Task/User

    public void editTask(int taskId, String title, String description, String dueDate) {
        getTask(taskId).setTitle(title);
        getTask(taskId).setDescription(description);
        getTask(taskId).setDueDate(dueDate);
    }

    // Unassign Task/User

    public void unassignTaskFromUser(int taskId, int uID) {
        if (getTask(taskId).getAssignedUsers().contains(getUser(uID))) {
            getTask(taskId).unassignUser(getUser(uID));
            getUser(uID).unassignTask(getTask(taskId));
        }
    }

    public void unassignTaskFromUsers(int taskId, ArrayList<Integer> uIDs) {
        for (Integer uID : uIDs) {
            unassignTaskFromUser(taskId, uID);
        }
    }

    // Remove Task/User

    public void removeTask(int taskId) {
        unassignTaskFromUsers(taskId, getUIDsAssignedToTask(taskId));
        taskIDHashMap.remove(taskId);
    }

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

    // Filtering Tasks

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

    // Filtering Users

    public void viewUsersByRole(String role) {
        System.out.println("All Users with role: " + role);
        for (User user : userIDHashMap.values()) {
            if (user.getRole().equals(role)) {
                System.out.println("[User ID: " + user.getuID() + "] " + user.getName());
            } else {
                continue;
            }
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
}
