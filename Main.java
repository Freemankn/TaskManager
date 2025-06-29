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
        System.out.println("Would you like to assign multiple tasks to a user: command-> asgnMT");
        System.out.println("Would you like to assign multiple tasks to multiple user: command-> asgnMTU");
        lineBreak();

        System.out.println("Would you like to unassign a task from a user: command-> unasgnU");
        System.out.println("Would you like to unassign a task from multiple users: command-> unasgnMU");
        System.out.println("Would you like to unassign multiple tasks from a user: command-> unasgnMT");
        System.out.println("Would you like to unassign multiple tasks from multiple user: command-> unasgnMTU");
        lineBreak();

        System.out.println("Type q to quit:");
    }

    // --------------------------------------------------------
    // 🔄 Input Helpers
    // --------------------------------------------------------

    static boolean validateUser(TaskManager tm, int uID) {
        if (!tm.containsUser(uID)) {
            System.out.println("UID not found.");
            return false;
        }
        return true;
    }

    static boolean validateTask(TaskManager tm, int taskID) {
        if (!tm.containsUser(taskID)) {
            System.out.println("Task ID not found.");
            return false;
        }
        return true;
    }

    static boolean validateTaskAndUser(TaskManager tm, int taskID, int uID) {
        if (!tm.containsTask(taskID) || !tm.containsUser(uID)) {
            System.out.println("Invalid Task ID or User ID.");
            return false;
        }
        return true;
    }

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

        Scanner scanner = new Scanner(System.in);
        String option;

        while (true) {
            prompt();
            option = scanner.nextLine();
            if (option.equals("q"))
                break;
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
                    int taskID = requestTaskID(scanner);
                    if (!validateTask(tm, taskID))
                        break;
                    tm.removeTask(taskID);
                }

                case "rmMT" -> {
                    ArrayList<Integer> taskIDs = requestMultipleIDs(scanner, "task");
                    taskIDs.removeIf(taskid -> !tm.containsTask(taskid));
                    tm.removeMultipleTasks(taskIDs);
                }

                case "editT" -> {
                    int taskID = requestTaskID(scanner);
                    if (!validateTask(tm, taskID))
                        break;

                    // continue with edit
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
                    if (!validateTask(tm, taskID))
                        break;
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
                    if (!validateUser(tm, uID))
                        break;
                    tm.removeUser(uID);
                }

                case "rmMU" -> {
                    ArrayList<Integer> uIDs = requestMultipleIDs(scanner, "user");
                    uIDs.removeIf(uID -> !tm.containsUser(uID));
                    tm.removeMultipleUsers(uIDs);
                }

                case "editU" -> {
                    int uID = requestUID(scanner);
                    if (!validateUser(tm, uID))
                        break;

                    // continue with edit
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
                // 🔗 Assignments
                // --------------------------------------------

                case "asgnU" -> {
                    int taskID = requestTaskID(scanner);
                    lineBreak();
                    int uID = requestUID(scanner);
                    if (!validateTaskAndUser(tm, taskID, uID))
                        break;
                    tm.assignTasktoUser(taskID, uID);
                }

                case "asgnMU" -> {
                    TaskInput input = requestMultipleUIDs(scanner);
                    if (!validateTask(tm, input.getTaskID()))
                        break;
                    input.getUIDS().removeIf(uID -> !tm.containsUser(uID));
                    tm.assignTasktoUsers(input.getTaskID(), input.getUIDS());
                }

                case "asgnMT" -> {
                    TaskInput input = requestMultipleTaskIDs(scanner);
                    if (!validateTask(tm, input.getUID()))
                        break;
                    input.getTaskIDS().removeIf(taskID -> !tm.containsUser(taskID));
                    tm.assignTaskstoUser(input.getUID(), input.getTaskIDS());
                }

                case "asgnMTU" -> {
                    ArrayList<Integer> taskIDs = requestMultipleIDs(scanner, "task");
                    ArrayList<Integer> uIDs = requestMultipleIDs(scanner, "user");
                    taskIDs.removeIf(taskID -> !tm.containsTask(taskID));
                    uIDs.removeIf(uID -> !tm.containsUser(uID));
                    tm.assignTaskstoUsers(uIDs, taskIDs);
                }

                // --------------------------------------------
                // 🔗 Unassignments
                // --------------------------------------------

                case "unasgnU" -> {
                    int taskID = requestTaskID(scanner);
                    lineBreak();
                    int uID = requestUID(scanner);
                    if (!validateTaskAndUser(tm, taskID, uID))
                        break;
                    tm.unassignTaskFromUser(uID, taskID);
                }

                case "unasgnMU" -> {
                    TaskInput input = requestMultipleUIDs(scanner);
                    if (!validateTask(tm, input.getTaskID()))
                        break;
                    input.getUIDS().removeIf(uID -> !tm.containsUser(uID));
                    tm.unassignUsersFromTask(input.getTaskID(), input.getUIDS());
                }

                case "unasgnMT" -> {
                    TaskInput input = requestMultipleTaskIDs(scanner);
                    if (!validateTask(tm, input.getUID()))
                        break;
                    input.getTaskIDS().removeIf(taskID -> !tm.containsUser(taskID));
                    tm.unassignTasksFromUser(input.getUID(), input.getTaskIDS());
                }

                case "unasgnMTU" -> {
                    ArrayList<Integer> taskIDs = requestMultipleIDs(scanner, "task");
                    ArrayList<Integer> uIDs = requestMultipleIDs(scanner, "user");
                    taskIDs.removeIf(taskID -> !tm.containsTask(taskID));
                    uIDs.removeIf(uID -> !tm.containsUser(uID));
                    tm.unassignTasksFromUsers(uIDs, taskIDs);
                }

                // --------------------------------------------
                // 👀 Task Viewing
                // --------------------------------------------

                case "viewT" -> {
                    int taskID = requestTaskID(scanner);
                    tm.viewTask(taskID);
                }

                case "viewAT" -> {
                    viewTasksMenu: while (true) {
                        System.out.println("""
                                How would you like to filter tasks?
                                1. By Status
                                2. By Due Date
                                3. By Assigned User
                                4. View All
                                b. Back to main menu
                                q. Quit
                                """);
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "1" -> {
                                lineBreak();
                                System.out.println("Enter status (TODO, IN_PROGRESS, DONE):");
                                TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                                lineBreak();
                                tm.viewTasksByStatus(status);
                            }
                            case "2" -> {
                                lineBreak();
                                System.out.println("Enter due date (YYYY-MM-DD):");
                                String dueDate = scanner.nextLine();
                                lineBreak();
                                tm.viewTasksByDueDate(dueDate);
                            }
                            case "3" -> {
                                lineBreak();
                                int uID = requestUID(scanner);
                                if (!validateUser(tm, uID)) {
                                    break;
                                }
                                lineBreak();
                                tm.viewTasksByUser(uID);
                            }
                            case "4" -> {
                                lineBreak();
                                tm.viewTasks();
                            }
                            case "b" -> {
                                break viewTasksMenu;
                            }
                            case "q" -> {
                                scanner.close();
                                System.exit(0);
                            }
                            default -> System.out.println("Invalid option.");
                        }
                        lineBreak();
                    }
                }

                // --------------------------------------------
                // 👀 User Viewing
                // --------------------------------------------

                case "viewU" -> {
                    int uID = requestUID(scanner);
                    if (!validateUser(tm, uID))
                        break;
                    tm.viewUser(uID);
                }

                case "viewAU" -> {
                    viewUserMenu: while (true) {
                        System.out.println("""
                                How would you like to filter users?
                                1. By Role
                                2. View All
                                b. Back to main menu
                                q. Quit
                                """);
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "1" -> {
                                System.out.println("Enter Role: ");
                                String role = scanner.nextLine();
                                tm.viewUsersByRole(role);
                            }
                            case "2" -> {
                                lineBreak();
                                tm.viewUsers();
                            }
                            case "b" -> {
                                break viewUserMenu;
                            }
                            case "q" -> {
                                scanner.close();
                                System.exit(0);
                            }
                            default -> System.out.println("Invalid option.");
                        }
                        lineBreak();
                    }
                }
                default -> System.out.println("Invalid option.");
            }
            lineBreak();
        }
        scanner.close();
        lineBreak();
    }
}
