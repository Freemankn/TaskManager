import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

// ------------------------------------------------------------
// 🚀 Main: CLI Interface for Task Manager
// ------------------------------------------------------------
// - Presents user with command options
// - Handles input/output for task and user operations
// - Interfaces with TaskManager backend logic
// ------------------------------------------------------------

public class Main {

    // --------------------------------------------------------
    // 🧱 Utility Methods
    // --------------------------------------------------------

    static void lineBreak() {
        System.out.println();
    }

    static void prompt() {
        System.out.println("Would you like to add a task: command-> addT");
        System.out.println("Would you like to remove a task: command-> rmT");
        System.out.println("Would you like to remove multiple tasks: command-> rmMT");
        System.out.println("Would you like to edit a task: command-> editT");
        System.out.println("Would you like view all task: command -> viewT");
        lineBreak();

        System.out.println("Would you like to add a user: command-> addU");
        System.out.println("Would you like to remove a user: command-> rmU");
        System.out.println("Would you like to remove a user: command-> rmMU");
        System.out.println("Would you like to edit a user: command-> editU");
        System.out.println("Would you like to view all users: command-> viewU");
        lineBreak();

        System.out.println("Would you like to assign a task to a user: command-> asgnU");
        System.out.println("Would you like to assign a task to multiple users: command-> asgnMU");
        lineBreak();

        System.out.println("Would you like to unassign a task to a User: command-> unasgnU");
        System.out.println("Would you like to unassign a task to multiple users: command-> unasgnMU");
        lineBreak();

        System.out.println("Type q to quit:");
    }

    // --------------------------------------------------------
    // 🔄 Input Helpers
    // --------------------------------------------------------

    static TaskInput requestMultipleUIDs(Scanner scanner) {
        System.out.println("Enter Task ID:");
        int taskID = scanner.nextInt();
        scanner.nextLine();
        lineBreak();

        System.out.println("Enter Multiple User IDs (separated by commas) like this 1,2,3 :");
        ArrayList<Integer> uIDS = new ArrayList<>();
        String[] nums = scanner.nextLine().split(",");

        for (String num : nums) {
            uIDS.add(Integer.parseInt(num.trim()));
        }
        return new TaskInput(taskID, uIDS);
    }

    static ArrayList<Integer> requestMultipleTaskIDs(Scanner scanner) {
        System.out.println("Enter Multiple Task IDs (separated by commas) like this 1,2,3 :");
        ArrayList<Integer> taskIDS = new ArrayList<>();
        String[] nums = scanner.nextLine().split(",");

        for (String num : nums) {
            taskIDS.add(Integer.parseInt(num.trim()));
        }
        return taskIDS;
    }

    // --------------------------------------------------------
    // 🚦 Main Loop
    // --------------------------------------------------------

    public static void main(String[] args) {
        Task task = null;
        User user = null;
        TaskManager tm = new TaskManager();

        lineBreak();
        System.out.println("This is the CLI Task Manager tool");
        prompt();

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        while (!option.equals("q")) {
            lineBreak();
            switch (option) {

                // --------------------------------------------
                // ➕ Task Management
                // --------------------------------------------

                case "addT" -> {
                    System.out.println("Enter a title:");
                    option = scanner.nextLine();
                    task = new Task(option);

                    lineBreak();
                    System.out.println("Would you like to add a description? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter description:");
                        option = scanner.nextLine();
                        task.setDescription(option);
                    }

                    lineBreak();
                    System.out.println("Would you like to set a due date? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter due date:");
                        option = scanner.nextLine();
                        task.setDueDate(option);
                    }
                    lineBreak();
                    System.out.println("The task ID is: " + task.getID());
                    tm.addTask(task);
                }

                case "rmT" -> {
                    System.out.println("Enter Task ID:");
                    int taskID = scanner.nextInt();
                    scanner.nextLine();
                    tm.removeTask(taskID);
                }

                case "rmMT" -> {
                    ArrayList<Integer> taskIDs = requestMultipleTaskIDs(scanner);
                    tm.removeMultipleTasks(taskIDs);
                }

                case "editT" -> {
                    System.out.println("Enter Task ID:");
                    int taskID = scanner.nextInt();
                    scanner.nextLine();
                    String title = task.getTitle();
                    String description = task.getDescription();
                    String dueDate = task.getDueDate();

                    System.out.println("Would you like to edit the title? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter Title:");
                        title = scanner.nextLine();
                    }

                    System.out.println("Would you like to edit the description? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter description:");
                        description = scanner.nextLine();
                    }

                    System.out.println("Would you like to edit the due date? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter due date:");
                        dueDate = scanner.nextLine();
                    }

                    System.out.println("The task ID is: " + task.getID());
                    tm.editTask(taskID, title, description, dueDate);
                }

                // --------------------------------------------
                // 👤 User Management
                // --------------------------------------------

                case "addU" -> {
                    System.out.println("Enter the username:");
                    option = scanner.nextLine();
                    user = new User(option);

                    lineBreak();
                    System.out.println("Would you like to add a role? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter role:");
                        user.setRole(scanner.nextLine());
                    }

                    lineBreak();
                    System.out.println("The user ID is: " + user.getuID());
                    tm.addUser(user);
                }

                case "rmU" -> {
                    System.out.println("Enter User ID:");
                    int taskID = scanner.nextInt();
                    scanner.nextLine();
                    tm.removeTask(taskID);
                }

                case "viewU" -> {
                    System.out.println(
                            "How would you like to filter the users?\n1. By Role\n2. View All\n\nEnter your choice (1-2):");
                    option = scanner.nextLine();
                    switch (option) {
                        case "1" -> {
                            System.out.println("Enter Role: ");
                            String role = scanner.nextLine();
                            tm.viewUsersByRole(role);
                        }
                        case "2" -> tm.viewUsers();
                        default -> System.out.println("Invalid option.");
                    }
                }

                // --------------------------------------------
                // 🔗 Assignments & Unassignments
                // --------------------------------------------

                case "asgnU" -> {
                    System.out.println("Enter Task ID:");
                    int taskID = scanner.nextInt();
                    lineBreak();
                    System.out.println("Enter User ID:");
                    int uID = scanner.nextInt();
                    scanner.nextLine();
                    tm.assignTasktoUser(taskID, uID);
                }

                case "asgnMU" -> {
                    TaskInput input = requestMultipleUIDs(scanner);
                    tm.assignTasktoUsers(input.getTaskID(), input.getUIDS());
                }

                case "unasgnU" -> {
                    System.out.println("Enter Task ID:");
                    int taskID = scanner.nextInt();
                    scanner.nextLine();
                    lineBreak();
                    System.out.println("Enter User ID:");
                    int uID = scanner.nextInt();
                    scanner.nextLine();
                    tm.unassignUserFromTask(taskID, uID);
                }

                case "unasgnMU" -> {
                    TaskInput input = requestMultipleUIDs(scanner);
                    tm.unassignUsersFromTask(input.getTaskID(), input.getUIDS());
                }

                // --------------------------------------------
                // 👀 Task Viewing
                // --------------------------------------------

                case "viewT" -> {
                    System.out.println(
                            "How would you like to filter the tasks?\n1. By Status\n2. By Due Date\n3. By Assigned User\n4. View All\n\nEnter your choice (1-4):");
                    option = scanner.nextLine();
                    switch (option) {
                        case "1" -> {
                            System.out.println("Enter status (TODO, IN_PROGRESS, DONE):");
                            TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                            tm.viewTasksByStatus(status);
                        }
                        case "2" -> {
                            System.out.println("Enter due date (YYYY-MM-DD):");
                            String dueDate = scanner.nextLine();
                            tm.viewTasksByDueDate(dueDate);
                        }
                        case "3" -> {
                            System.out.println("Enter user UID:");
                            int uID = scanner.nextInt();
                            scanner.nextLine();
                            tm.viewTasksByUser(uID);
                        }
                        case "4" -> tm.viewTasks();
                        default -> System.out.println("Invalid option.");
                    }
                }

                default -> System.out.println("Invalid option.");
            }
            lineBreak();
            prompt();
            option = scanner.nextLine();
        }

        scanner.close();
        lineBreak();
    }
}
