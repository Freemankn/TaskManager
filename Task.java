import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int nextId = 1;
    private final int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;
    private List<User> assignedUsers;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public Task(String title, String description, LocalDate dueDate, TaskStatus status, List<User> assignedUsers) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.assignedUsers = assignedUsers;
    }

    public Task(String title) {
        this(title, "None", null, TaskStatus.TODO, new ArrayList<User>());
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "--- Task ID: " + getID() + " ---\n" +
                "Title: " + getTitle() + "\n"
                + "Status: " + getStatus() + "\n"
                + "Description: " + getDescription() + "\n"
                + "Due Date: " + (dueDate != null ? dueDate.format(FORMATTER) : "N/A") + "\n"
                + "Assigned To: " + displayUsers() + "\n";
    }

    // Anytime, you create an id, the id counter increments by one. Should be static

    // title, description, dueDate, status, user should be part of the constructor

    // private User

    public String displayUsers() {
        if (assignedUsers.isEmpty()) {
            return "No users";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < assignedUsers.size(); i++) {
            output.append(assignedUsers.get(i).getName());
            if (i != assignedUsers.size() - 1) {
                output.append(", ");
            }
        }

        return output.toString();
    }

    // getters

    public int getID() {
        return id;
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public LocalDate getDueDate() {
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

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}