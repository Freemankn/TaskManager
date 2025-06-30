import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

// ------------------------------------------------------------
// 🧠 TaskManager: Central Controller for Tasks and Users
// ------------------------------------------------------------
// - Knows about both Task and User
// - Maintains consistent links between them
// - Manages assignment, editing, and filtering
// ------------------------------------------------------------

public class TaskManager {
    // --------------------------------------------------------
    // 🔒 Internal Storage
    // --------------------------------------------------------
    // 🔹 Maps to store tasks and users
    private LinkedHashMap<Integer, Task> taskIDHashMap = new LinkedHashMap<>(); // <id, Task>
    private LinkedHashMap<Integer, User> userIDHashMap = new LinkedHashMap<>(); // <uID, User>

    // --------------------------------------------------------
    // 📥 Getters
    // --------------------------------------------------------
    // 🔹 Fetch Task/User by ID and their assignment mappings
    public User getUser(int uID) {
        return userIDHashMap.get(uID);
    }

    public Task getTask(int taskID) {
        return taskIDHashMap.get(taskID);
    }

    public ArrayList<Integer> getUIDsAssignedToTask(int taskID) {
        ArrayList<Integer> uIDs = new ArrayList<>();
        for (User user : getTask(taskID).getAssignedUsers()) {
            uIDs.add(user.getuID());
        }
        return uIDs;
    }

    public ArrayList<Integer> getTaskIDsAssignedToUser(int userID) {
        ArrayList<Integer> taskIDs = new ArrayList<>();
        for (Task task : getUser(userID).getTasks()) {
            taskIDs.add(task.getID());
        }
        return taskIDs;
    }

    // --------------------------------------------------------
    // 🔗 Link / Unlink Tasks and Users
    // --------------------------------------------------------
    // 🔹 Used internally to maintain two-way relationships
    private void unlinkUserToTask(int taskID, int uID) {
        getTask(taskID).unassignUser(getUser(uID));
        getUser(uID).unassignTask(getTask(taskID));
    }

    private void linkUserToTask(int taskID, int uID) {
        getTask(taskID).assignUser(getUser(uID));
        getUser(uID).assignTask(getTask(taskID));
    }

    // --------------------------------------------------------
    // 🔗 Task and User checker
    // --------------------------------------------------------

    public boolean containsTask(int taskID) {
        Task task = getTask(taskID);
        if (task == null) {
            return false;
        }
        return true;
    }

    public boolean containsUser(int uID) {
        User user = getUser(uID);
        if (user == null) {
            return false;
        }
        return true;
    }

    // --------------------------------------------------------
    // ➕ Add Tasks / Users
    // --------------------------------------------------------
    // 🔹 Insert new Task/User objects into the system
    public void addTask(Task task) {
        taskIDHashMap.put(task.getID(), task);
    }

    public void addUser(User user) {
        userIDHashMap.put(user.getuID(), user);
    }

    // --------------------------------------------------------
    // ✅ Assignments
    // --------------------------------------------------------
    // 🔹 Assign one or many users to a task and vice versa
    public void assignTasktoUser(int taskID, int uID) {
        if (!getTask(taskID).getAssignedUsers().contains(getUser(uID))) {
            linkUserToTask(taskID, uID);
        }
    }

    private void assignUsertoTask(int uID, int taskID) {
        if (!getUser(uID).getTasks().contains(getTask(taskID))) {
            assignTasktoUser(taskID, uID);
        }
    }

    public void assignTasktoUsers(int taskID, ArrayList<Integer> uIDs) {
        for (Integer uID : uIDs) {
            assignTasktoUser(taskID, uID);
        }
    }

    public void assignTaskstoUser(int uID, ArrayList<Integer> taskIDs) {
        for (Integer taskID : taskIDs) {
            assignUsertoTask(uID, taskID);
        }
    }

    public void assignTaskstoUsers(ArrayList<Integer> uIDs, ArrayList<Integer> taskIDs) {
        for (Integer taskID : taskIDs) {
            for (Integer uID : uIDs) {
                assignTasktoUser(taskID, uID);
            }
        }
    }

    // --------------------------------------------------------
    // ❌ Unassignments
    // --------------------------------------------------------

    // 🔹 Unassign Task(s) from User (from user's perspective)
    public void unassignTaskFromUser(int uID, int taskID) {
        if (getUser(uID).getTasks().contains(getTask(taskID))) {
            unlinkUserToTask(taskID, uID);
        }
    }

    // 🔹 Unassign User(s) from Task (from task's perspective)
    private void unassignUserFromTask(int taskID, int uID) {
        if (getTask(taskID).getAssignedUsers().contains(getUser(uID))) {
            unassignUserFromTask(taskID, uID);
        }
    }

    public void unassignUsersFromTask(int taskID, ArrayList<Integer> uIDs) {
        for (Integer uID : uIDs) {
            unassignUserFromTask(taskID, uID);
        }
    }

    public void unassignTasksFromUser(int uID, ArrayList<Integer> taskIDs) {
        for (Integer taskID : taskIDs) {
            unassignTaskFromUser(uID, taskID);
        }
    }

    void unassignTasksFromUsers(ArrayList<Integer> uIDs, ArrayList<Integer> taskIDs) {
        for (Integer taskID : taskIDs) {
            for (Integer uID : uIDs) {
                unassignTaskFromUser(taskID, uID);
            }
        }
    }

    // --------------------------------------------------------
    // 📝 Editing
    // --------------------------------------------------------
    // 🔹 Modify Task or User details
    public void editTask(int taskID, String title, String description, LocalDate dueDate) {
        getTask(taskID).setTitle(title);
        getTask(taskID).setDescription(description);
        getTask(taskID).setDueDate(dueDate);
    }

    public void editUser(int userID, String name, String role) {
        getUser(userID).setName(name);
        getUser(userID).setRole(role);
    }

    // --------------------------------------------------------
    // 🗑️ Deletion
    // --------------------------------------------------------
    // 🔹 Remove Task/User including cleaning up their links
    public void removeTask(int taskID) {
        unassignUsersFromTask(taskID, getUIDsAssignedToTask(taskID));
        taskIDHashMap.remove(taskID);
    }

    public void removeMultipleTasks(ArrayList<Integer> taskIDs) {
        for (Integer taskID : taskIDs) {
            removeTask(taskID);
        }
    }

    public void removeUser(int uID) {
        unassignTasksFromUser(uID, getTaskIDsAssignedToUser(uID));
        userIDHashMap.remove(uID);
    }

    public void removeMultipleUsers(ArrayList<Integer> uIDs) {
        for (Integer uID : uIDs) {
            removeUser(uID);
        }
    }

    // --------------------------------------------------------
    // 📊 Status / Due Date Updates
    // --------------------------------------------------------
    // 🔹 Modify task progress or deadlines

    public void setStatus(int taskID, TaskStatus status) {
        getTask(taskID).setStatus(status);
    }

    public void setDueDate(int taskID, String dueDate) {
        LocalDate targetDate = LocalDate.parse(dueDate);
        getTask(taskID).setDueDate(targetDate);
    }

    // --------------------------------------------------------
    // 🔍 Filtering
    // --------------------------------------------------------

    // 🔹 Filter by Status
    public void viewTasksByStatus(TaskStatus status) {
        System.out.println("Filtered Tasks with status:" + status);
        for (Task task : taskIDHashMap.values()) {
            if (task.getStatus() == status) {
                System.out.println("[ID: " + task.getID() + "] " + task.getTitle() + " (" + task.getStatus() + ")"
                        + " Assigned to " + task.displayUsers());
            }
        }
    }

    // 🔹 Filter by Due Date
    public void viewTasksByDueDate(String dueDate) {
        LocalDate targetDate = LocalDate.parse(dueDate);
        System.out.println("Tasks due on: " + dueDate);
        for (Task task : taskIDHashMap.values()) {
            if (task.getDueDate() != null && task.getDueDate().equals(targetDate)) {
                System.out.println("[ID: " + task.getID() + "] " + task.getTitle() + " (" + task.getStatus() + ")"
                        + " Assigned to " + task.displayUsers());
            }
        }
    }

    // 🔹 Filter by User
    public void viewTasksByUser(int uID) {
        System.out.println("Tasks assigned to " + getUser(uID).getName() + ":");
        for (Task task : taskIDHashMap.values()) {
            if (task.getAssignedUsers().contains(getUser(uID))) {
                System.out.println("[ID: " + task.getID() + "] " + task.getTitle() + " (" + task.getStatus() + ")"
                        + " Assigned to " + task.displayUsers());
            }
        }
    }

    // 🔹 Filter Users by Role
    public void viewUsersByRole(String role) {
        System.out.println("All Users with role: " + role);
        for (User user : userIDHashMap.values()) {
            if (user.getRole().equals(role)) {
                System.out.println("[User ID: " + user.getuID() + "] " + user.getName());
            }
        }
    }

    // --------------------------------------------------------
    // 👀 Viewing
    // --------------------------------------------------------
    // 🔹 View one or all Tasks and Users
    public void viewTask(int taskID) {
        System.out.println(taskIDHashMap.get(taskID));
    }

    public void viewTasks() {
        System.out.println("All Tasks:");
        for (Task task : taskIDHashMap.values()) {
            System.out.println("[ID: " + task.getID() + "] " + task.getTitle() + " (" + task.getStatus() + ")"
                    + " Assigned to " + task.displayUsers());
        }
    }

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
