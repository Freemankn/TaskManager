import java.util.List;

public class User {
    private static int nextId = 1;
    private final int uID; // Unique ID for internal reference
    private String name; // User's name (e.g., "Onyx")
    private String role; // Role in the system
    private List<Task> tasks; // Tasks assigned to the user

    public User(String name, String role, List<Task> tasks) {
        this.uID = nextId++;
        this.name = name;
        this.role = role;
        this.tasks = tasks;
    }

    public User(String name) {
        this(name, "None", null);
    }

    // getters
    public int getuID() {
        return uID;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void assignTask(Task task) {
        this.tasks.add(task);
    }

    // public void removeTask()

}
