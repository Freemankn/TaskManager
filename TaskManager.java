import java.util.ArrayList;
import java.util.List;

//It acts as the central controller that:
// Knows about both the Task and the User
// Has access to all tasks and users
// Ensures both sides are updated consistently

// Will need a construct later of persistent storage

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private List<User> users = new ArrayList<>();;

    public void addTask(Task task) {
        this.tasks.add(task);
        if (task.getAssignedUser() != null) {
            users.add(task.getAssignedUser());
        }
    }

    public void editTask(int taskId, Task updatedTask) {
        /* ... */ }

    public void deleteTask(int taskId) {
        /* ... */ }

    public void assignTask(int taskId, User username) {

    }

    public void markTaskComplete(int taskId) {
        /* ... */ }

    // public List<Task> getTasksByStatus(String status) {
    // /* ... */ }
}
