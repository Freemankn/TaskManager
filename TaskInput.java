import java.util.ArrayList;

// ------------------------------------------------------------
// 📦 TaskInput: Encapsulates Task ID and Assigned User IDs
// ------------------------------------------------------------
// - Stores the task identifier
// - Stores the list of user IDs assigned to that task
// - Used as a convenient input structure
// ------------------------------------------------------------

public class TaskInput {
    // --------------------------------------------------------
    // 🔒 Fields
    // --------------------------------------------------------
    private int taskID;
    private int uID;
    private ArrayList<Integer> uIDS;
    private ArrayList<Integer> taskIDS;

    // 🔒 Private constructor to prevent direct use
    private TaskInput(int taskID, ArrayList<Integer> uIDS, int uID, ArrayList<Integer> taskIDS) {
        this.taskID = taskID;
        this.uIDS = uIDS;
        this.uID = uID;
        this.taskIDS = taskIDS;
    }

    // 🏗️ Factory method: Assign one task to multiple users
    public static TaskInput forTaskToUsers(int taskID, ArrayList<Integer> uIDS) {
        return new TaskInput(taskID, uIDS, -1, null);
    }

    // 🏗️ Factory method: Assign multiple tasks to one user
    public static TaskInput forUserToTasks(int uID, ArrayList<Integer> taskIDS) {
        return new TaskInput(-1, null, uID, taskIDS);
    }

    // --------------------------------------------------------
    // 📥 Getters
    // --------------------------------------------------------
    public int getTaskID() {
        return taskID;
    }

    public ArrayList<Integer> getUIDS() {
        return uIDS;
    }

    public int getUID() {
        return uID;
    }

    public ArrayList<Integer> getTaskIDS() {
        return taskIDS;
    }
}
