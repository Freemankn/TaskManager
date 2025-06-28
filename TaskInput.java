import java.util.ArrayList;

public class TaskInput {
    private int taskID;
    private ArrayList<Integer> uIDS;

    public TaskInput(int taskID, ArrayList<Integer> uIDS) {
        this.taskID = taskID;
        this.uIDS = uIDS;
    }

    public int getTaskID() {
        return taskID;
    }

    public ArrayList<Integer> getUIDS() {
        return uIDS;
    }
}
