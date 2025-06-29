import java.util.ArrayList;
import java.util.List;

public class Task {
    private static int nextId = 1;
    private final int id;
    private String title;
    private String description;
    private String dueDate; // Keep as String or use LocalDate
    private TaskStatus status; // ✅ Use enum instead of String
    private List<User> assignedUsers;

    public Task(String title, String description, String dueDate, TaskStatus status, List<User> assignedUsers) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.assignedUsers = assignedUsers;
    }

    public Task(String title) {
        this(title, "None", "None", TaskStatus.TODO, new ArrayList<User>());
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "--- Task ID: " + getID() + " ---\n" +
                "Title: " + getTitle() + "\n"
                + "Status: " + getStatus() + "\n"
                + "Description: " + getDescription() + "\n"
                + "Due Date: " + getDueDate() + "\n"
                + "Assigned To: " + displayUsers() + "\n";
    }

    // Anytime, you create an id, the id counter increments by one. Should be static

    // title, description, dueDate, status, user should be part of the constructor

    // private User

    public String displayUsers() {
        if (assignedUsers.isEmpty()) {
            return "No users";
        }
        String output = "";
        for (User u : assignedUsers) {
            output += u.getName();
        }
        return output;
    }

    // getters

    public int getID() {
        return id;
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public static int getNextId() {
        return nextId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    // setters

    public void assignUser(User assignedUser) {
        assignedUsers.add(assignedUser);
    }

    public void unassignUser(User assignedUser) {
        assignedUsers.remove(assignedUser);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}