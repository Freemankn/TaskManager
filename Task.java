public class Task {
    private static int nextId = 1;
    private final int id;
    private String title;
    private String description;
    private String dueDate; // Keep as String or use LocalDate
    private TaskStatus status; // ✅ Use enum instead of String
    private User assignedUser;

    public Task(String title, String description, String dueDate, TaskStatus status, User assignedUser) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.assignedUser = assignedUser;
    }

    public Task(String title) {
        this(title, "None", "None", TaskStatus.TODO, null);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Task: " + getTitle() + "\n"
                + "Description: " + getDescription() + "\n"
                + "Due Date: " + getDueDate() + "\n"
                + "Status: " + getStatus() + "\n"
                + "ID: " + getID() + "\n";
    }

    // Anytime, you create an id, the id counter increments by one. Should be static

    // title, description, dueDate, status, user should be part of the constructor

    // private User

    // getters

    public int getID() {
        return id;
    }

    public User getAssignedUser() {
        return assignedUser;
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

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
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