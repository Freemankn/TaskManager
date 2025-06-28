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
    private ArrayList<Integer> uIDS;

    // --------------------------------------------------------
    // 🏗️ Constructor
    // --------------------------------------------------------
    public TaskInput(int taskID, ArrayList<Integer> uIDS) {
        this.taskID = taskID;
        this.uIDS = uIDS;
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
}
