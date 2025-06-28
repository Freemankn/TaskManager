import java.util.ArrayList;
import java.util.List;

// ------------------------------------------------------------
// 👤 User: Represents a system user
// ------------------------------------------------------------
// - Has a unique ID, name, and role
// - Maintains a list of tasks assigned
// - Supports task assignment and removal
// ------------------------------------------------------------

public class User {
    // --------------------------------------------------------
    // 🔒 Fields
    // --------------------------------------------------------
    private static int nextId = 1;
    private final int uID; // Unique ID for internal reference
    private String name; // User's name (e.g., "Onyx")
    private String role; // Role in the system
    private List<Task> tasks; // Tasks assigned to the user

    // --------------------------------------------------------
    // 🏗️ Constructors
    // --------------------------------------------------------

    // 🔹 Full constructor
    public User(String name, String role, List<Task> tasks) {
        this.uID = nextId++;
        this.name = name;
        this.role = role;
        this.tasks = tasks;
    }

    // 🔹 Default role and empty task list
    public User(String name) {
        this(name, "None", new ArrayList<Task>());
    }

    // --------------------------------------------------------
    // 🧾 Display
    // --------------------------------------------------------

    @Override
    public String toString() {
        return "--- User ID: " + getuID() + " ---\n"
                + "Name: " + getName() + "\n"
                + "Role: " + getRole() + "\n"
                + "UID: " + getuID() + "\n\n"
                + "Tasks assigned:\n"
                + displayTasks();
    }

    public String displayTasks() {
        if (tasks.isEmpty()) {
            return "No tasks assigned.";
        }

        String output = "";
        for (Task t : tasks) {
            output += "[ID: " + t.getID() + "] " + t.getTitle() + " (" + t.getStatus() + ")" + "\n";
        }
        return output;
    }

    // --------------------------------------------------------
    // 📥 Getters
    // --------------------------------------------------------
    public int getuID() {
        return uID;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    // --------------------------------------------------------
    // ✏️ Setters
    // --------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // --------------------------------------------------------
    // 🔗 Task Management
    // --------------------------------------------------------
    public void assignTask(Task task) {
        tasks.add(task);
    }

    public void unassignTask(Task task) {
        tasks.remove(task);
    }
}
