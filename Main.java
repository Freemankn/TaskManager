import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
        System.out.println("Would you like view a task: command -> viewT");
        System.out.println("Would you like view all tasks: command -> viewAT");
        lineBreak();

        System.out.println("Would you change the status of the task: command->statusT");
        lineBreak();

        System.out.println("Would you like to add a user: command-> addU");
        System.out.println("Would you like to remove a user: command-> rmU");
        System.out.println("Would you like to remove multiple users: command-> rmMU");
        System.out.println("Would you like to edit a user: command-> editU");
        System.out.println("Would you like to view a user: command-> viewU");
        System.out.println("Would you like to view all users: command-> viewAU");
        lineBreak();

        System.out.println("Would you like to assign a task to a user: command-> asgnU");
        System.out.println("Would you like to assign a task to multiple users: command-> asgnMU");
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
        return TaskInput.forTaskToUsers(taskID, uIDS);
    }

    static TaskInput requestMultipleTaskIDs(Scanner scanner) {
        System.out.println("Enter User ID:");
        int userID = scanner.nextInt();
        scanner.nextLine();
        lineBreak();

        System.out.println("Enter Multiple Tasks IDs (separated by commas) like this 1,2,3 :");
        ArrayList<Integer> taskIDS = new ArrayList<>();
        String[] nums = scanner.nextLine().split(",");

        for (String num : nums) {
            taskIDS.add(Integer.parseInt(num.trim()));
        }
        return TaskInput.forUserToTasks(userID, taskIDS);
    }

    static ArrayList<Integer> requestMultipleIDs(Scanner scanner, String field) {
        System.out.println("Enter multiple " + field + " IDs (separated by commas) like this 1,2,3 :");
        ArrayList<Integer> fieldIDS = new ArrayList<>();
        String[] nums = scanner.nextLine().split(",");

        for (String num : nums) {
            fieldIDS.add(Integer.parseInt(num.trim()));
        }
        return fieldIDS;
    }

    static int requestTaskID(Scanner scanner) {
        System.out.println("Enter Task ID:");
        int taskID = scanner.nextInt();
        scanner.nextLine();
        return taskID;
    }

    static int requestUID(Scanner scanner) {
        System.out.println("Enter User ID:");
        int uID = scanner.nextInt();
        scanner.nextLine();
        return uID;
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
        lineBreak();
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
                        lineBreak();
                        System.out.println("Enter description:");
                        option = scanner.nextLine();
                        task.setDescription(option);
                    }
                    lineBreak();

                    System.out.println("Would you like to set a due date? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        lineBreak();
                        System.out.println("Enter due date:");
                        option = scanner.nextLine();
                        task.setDueDate(option);
                    }
                    tm.addTask(task);
                }

                case "rmT" -> {
                    System.out.println("Enter Task ID:");
                    int taskID = scanner.nextInt();
                    scanner.nextLine();
                    tm.removeTask(taskID);
                }

                case "rmMT" -> {
                    ArrayList<Integer> taskIDs = requestMultipleIDs(scanner, "task");
                    tm.removeMultipleTasks(taskIDs);
                }

                case "editT" -> {
                    System.out.println("Enter Task ID:");
                    int taskID = scanner.nextInt();
                    scanner.nextLine();
                    String title = task.getTitle();
                    String description = task.getDescription();
                    String dueDate = task.getDueDate();
                    lineBreak();

                    System.out.println("Would you like to edit the title? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        lineBreak();
                        System.out.println("Enter Title:");
                        title = scanner.nextLine();
                    }
                    lineBreak();

                    System.out.println("Would you like to edit the description? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        lineBreak();
                        System.out.println("Enter description:");
                        description = scanner.nextLine();
                    }
                    lineBreak();

                    System.out.println("Would you like to edit the due date? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        lineBreak();
                        System.out.println("Enter due date:");
                        dueDate = scanner.nextLine();
                    }
                    tm.editTask(taskID, title, description, dueDate);
                }

                case "statusT" -> {
                    System.out.println("Enter Task ID:");
                    int taskID = scanner.nextInt();
                    scanner.nextLine();
                    lineBreak();
                    System.out.println("Enter status (TODO, IN_PROGRESS, DONE):");
                    TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                    tm.setStatus(taskID, status);
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
                        lineBreak();
                        System.out.println("Enter role:");
                        user.setRole(scanner.nextLine());
                    }
                    tm.addUser(user);
                }

                case "rmU" -> {
                    int uID = requestUID(scanner);
                    tm.removeUser(uID);
                }

                case "rmMU" -> {
                    ArrayList<Integer> taskIDs = requestMultipleIDs(scanner, "user");
                    tm.removeMultipleUsers(taskIDs);
                }

                case "editU" -> {
                    int uID = requestUID(scanner);
                    String name = user.getName();
                    String role = user.getRole();
                    lineBreak();

                    System.out.println("Would you like to edit the name? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        lineBreak();
                        System.out.println("Enter name:");
                        name = scanner.nextLine();
                    }
                    lineBreak();

                    System.out.println("Would you like to edit the role? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        lineBreak();
                        System.out.println("Enter Role:");
                        role = scanner.nextLine();
                    }
                    tm.editUser(uID, name, role);
                }

                // --------------------------------------------
                // 🔗 Assignments & Unassignments
                // --------------------------------------------

                case "asgnU" -> {
                    int taskID = requestTaskID(scanner);
                    lineBreak();
                    int uID = requestUID(scanner);
                    tm.assignTasktoUser(taskID, uID);
                }

                case "asgnMU" -> {
                    TaskInput input = requestMultipleUIDs(scanner);
                    tm.assignTasktoUsers(input.getTaskID(), input.getUIDS());
                }

                case "unasgnU" -> {
                    int taskID = requestTaskID(scanner);
                    lineBreak();
                    int uID = requestUID(scanner);
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
                    int taskID = requestTaskID(scanner);
                    tm.viewTask(taskID);
                }

                case "viewAT" -> {
                    System.out.println(
                            "How would you like to filter the tasks?\n1. By Status\n2. By Due Date\n3. By Assigned User\n4. View All\n\nEnter your choice (1-4):");
                    option = scanner.nextLine();
                    switch (option) {
                        case "1" -> {
                            lineBreak();
                            System.out.println("Enter status (TODO, IN_PROGRESS, DONE):");
                            TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                            tm.viewTasksByStatus(status);
                        }
                        case "2" -> {
                            lineBreak();
                            System.out.println("Enter due date (YYYY-MM-DD):");
                            String dueDate = scanner.nextLine();
                            tm.viewTasksByDueDate(dueDate);
                        }
                        case "3" -> {
                            lineBreak();
                            int uID = requestUID(scanner);
                            tm.viewTasksByUser(uID);
                        }
                        case "4" -> {
                            lineBreak();
                            tm.viewTasks();
                        }
                        default -> System.out.println("Invalid option.");
                    }
                }
                default -> System.out.println("Invalid option.");

                // --------------------------------------------
                // 👀 User Viewing
                // --------------------------------------------

                case "viewU" -> {
                    int uID = requestUID(scanner);
                    tm.viewUser(uID);
                }

                case "viewAU" -> {
                    System.out.println(
                            "How would you like to filter the users?\n1. By Role\n2. View All\n\nEnter your choice (1-2):");
                    option = scanner.nextLine();
                    switch (option) {
                        case "1" -> {
                            System.out.println("Enter Role: ");
                            String role = scanner.nextLine();
                            tm.viewUsersByRole(role);
                        }
                        case "2" -> {
                            lineBreak();
                            tm.viewUsers();
                        }
                        default -> System.out.println("Invalid option.");
                    }
                }
            }

            lineBreak();
            prompt();
            option = scanner.nextLine();
        }

        scanner.close();
        lineBreak();
    }
}
