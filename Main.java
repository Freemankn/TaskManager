import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// ------------------------------------------------------------
// 🚀 Main: CLI Interface for Task Manager
// ------------------------------------------------------------
// - Presents user with command options
// - Handles input/output for task and user operations
// - Interfaces with TaskManager backend logic
// ------------------------------------------------------------

public class Main {

    // Global variable
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    // --------------------------------------------------------
    // 🧱 Utility Methods
    // --------------------------------------------------------

    static void lineBreak() {
        System.out.println();
    }

    public static void pauseThenPrompt() {
        try {
            Thread.sleep(1800); // adjust to your preferred delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void updateTaskMessage(String title, int taskID, boolean isEdited) {
        if (isEdited) {
            System.out.println("Changes to task titled " + title + " with ID " + taskID + " has been made.");
        } else {
            System.out.println("No changes to task titled " + title + " with ID " + taskID + " has been made.");
        }
    }

    static void updateUserMessage(User user, boolean isEdited) {
        if (isEdited) {
            System.out.println("Changes to " + user.getName() + " has been made.");
        } else {
            System.out.println("No changes to " + user.getName() + " has been made.");
        }
    }

    static void prompt() {
        System.out.println("--------------------------------------------");
        System.out.println("Task options");
        System.out.println("--------------------------------------------");

        System.out.println("> Would you like to add a task: command-> addT");
        System.out.println("> Would you like to remove a task: command-> rmT");
        System.out.println("> Would you like to remove multiple tasks: command-> rmMT");
        System.out.println("> Would you like to edit a task: command-> editT");
        System.out.println("> Would you like view a task: command -> viewT");
        System.out.println("> Would you like view all tasks: command -> viewAT");
        System.out.println("> Would you change the status of the task: command->statusT");
        lineBreak();

        System.out.println("--------------------------------------------");
        System.out.println("User options");
        System.out.println("--------------------------------------------");

        System.out.println("> Would you like to add a user: command-> addU");
        System.out.println("> Would you like to remove a user: command-> rmU");
        System.out.println("> Would you like to remove multiple users: command-> rmMU");
        System.out.println("> Would you like to edit a user: command-> editU");
        System.out.println("> Would you like to view a user: command-> viewU");
        System.out.println("> Would you like to view all users: command-> viewAU");
        lineBreak();

        System.out.println("--------------------------------------------");
        System.out.println("Assignment options");
        System.out.println("--------------------------------------------");

        System.out.println("> Would you like to assign a task to a user: command-> asgnU");
        System.out.println("> Would you like to assign a task to multiple users: command-> asgnMU");
        System.out.println("> Would you like to assign multiple tasks to a user: command-> asgnMT");
        System.out.println("> Would you like to assign multiple tasks to multiple user: command-> asgnMTU");
        lineBreak();

        System.out.println("--------------------------------------------");
        System.out.println("Unassignment options");
        System.out.println("--------------------------------------------");

        System.out.println("> Would you like to unassign a task from a user: command-> unasgnU");
        System.out.println("> Would you like to unassign a task from multiple users: command-> unasgnMU");
        System.out.println("> Would you like to unassign multiple tasks from a user: command-> unasgnMT");
        System.out.println("> Would you like to unassign multiple tasks from multiple user: command-> unasgnMTU");
        lineBreak();

        System.out.println("> Type q to quit:");
        lineBreak();
        System.out.println("> Enter your command:");
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
        if (!tm.containsTask(taskID)) {
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

    static boolean askYesOrNo(Scanner scanner, String question) {
        while (true) {
            System.out.println(question + " (y/n):");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y"))
                return true;
            if (input.equals("n"))
                return false;
            System.out.println("Please enter 'y' or 'n'.");
            lineBreak();
        }
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
            pauseThenPrompt();
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
                    boolean isYes = askYesOrNo(scanner, "Would you like to add a description?");
                    if (isYes) {
                        lineBreak();
                        System.out.println("Enter description:");
                        option = scanner.nextLine();
                        task.setDescription(option);
                    }
                    lineBreak();

                    isYes = askYesOrNo(scanner, "Would you like to set a due date?");
                    if (isYes) {
                        lineBreak();
                        try {
                            System.out.println("Enter due date (MM/DD/YYYY):");
                            String date = scanner.nextLine();
                            task.setDueDate(LocalDate.parse(date, FORMATTER));
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format.");
                        }
                    }
                    tm.addTask(task);
                    lineBreak();
                    System.out
                            .println("Task titled " + task.getTitle() + " with ID " + task.getID() + " has been added");
                }

                case "rmT" -> {
                    int taskID = requestTaskID(scanner);
                    if (!validateTask(tm, taskID))
                        break;
                    lineBreak();
                    System.out.println(
                            "Task titled " + task.getTitle() + " with ID " + task.getID() + " has been removed");
                    tm.removeTask(taskID);
                }

                case "rmMT" -> {
                    ArrayList<Integer> taskIDs = requestMultipleIDs(scanner, "task");
                    taskIDs.removeIf(taskid -> !tm.containsTask(taskid));
                    tm.removeMultipleTasks(taskIDs);
                    lineBreak();
                    System.out.println("These tasks have been removed");
                }

                case "editT" -> {
                    int taskID = requestTaskID(scanner);
                    if (!validateTask(tm, taskID))
                        break;

                    // continue with edit
                    String title = task.getTitle();
                    String description = task.getDescription();
                    LocalDate dueDate = task.getDueDate();
                    boolean isEdited = false;
                    lineBreak();

                    boolean isYes = askYesOrNo(scanner, "Would you like to edit the title?");
                    if (isYes) {
                        lineBreak();
                        System.out.println("Enter Title:");
                        title = scanner.nextLine();
                        isEdited = true;
                    }
                    lineBreak();

                    isYes = askYesOrNo(scanner, "Would you like to edit the description?");
                    if (isYes) {
                        lineBreak();
                        System.out.println("Enter description:");
                        description = scanner.nextLine();
                        isEdited = true;
                    }
                    lineBreak();

                    isYes = askYesOrNo(scanner, "Would you like to edit the due date?");
                    if (isYes) {
                        lineBreak();
                        try {
                            System.out.println("Enter due date (MM/DD/YYYY):");
                            String date = scanner.nextLine();
                            dueDate = LocalDate.parse(date, FORMATTER);
                            isEdited = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format.");
                        }
                    }
                    lineBreak();
                    tm.editTask(taskID, title, description, dueDate);
                    updateTaskMessage(tm.getTask(taskID).getTitle(), taskID, isEdited);
                }

                case "statusT" -> {
                    int taskID = requestTaskID(scanner);
                    if (!validateTask(tm, taskID))
                        break;
                    boolean isEdited = false;
                    lineBreak();

                    try {
                        System.out.println("Enter status (TODO, IN_PROGRESS, DONE):");
                        TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                        tm.setStatus(taskID, status);
                        isEdited = true;
                    } catch (IllegalArgumentException e) {
                        lineBreak();
                        System.out.println(
                                "Invalid status. Here are the valid statuses: " + Arrays.toString(TaskStatus.values()));
                    }
                    lineBreak();
                    updateTaskMessage(tm.getTask(taskID).getTitle(), taskID, isEdited);
                }

                // --------------------------------------------
                // 👤 User Management
                // --------------------------------------------

                case "addU" -> {
                    System.out.println("Enter the username:");
                    option = scanner.nextLine();
                    user = new User(option);

                    lineBreak();
                    boolean isYes = askYesOrNo(scanner, "Would you like to add a role?");
                    if (isYes) {
                        lineBreak();
                        System.out.println("Enter role:");
                        user.setRole(scanner.nextLine());
                    }
                    tm.addUser(user);
                    lineBreak();
                    System.out.println("UID: " + user.getuID() + " has been added");
                }

                case "rmU" -> {
                    int uID = requestUID(scanner);
                    if (!validateUser(tm, uID))
                        break;
                    lineBreak();
                    System.out.println("UID: " + user.getuID() + " has been removed");
                    tm.removeUser(uID);
                }

                case "rmMU" -> {
                    ArrayList<Integer> uIDs = requestMultipleIDs(scanner, "user");
                    uIDs.removeIf(uID -> !tm.containsUser(uID));
                    tm.removeMultipleUsers(uIDs);
                    lineBreak();
                    System.out.println("These users have been removed");

                }

                case "editU" -> {
                    int uID = requestUID(scanner);
                    if (!validateUser(tm, uID))
                        break;

                    // continue with edit
                    String name = user.getName();
                    String role = user.getRole();
                    boolean isEdited = false;

                    lineBreak();

                    boolean isYes = askYesOrNo(scanner, "Would you like to edit the name?");
                    if (isYes) {
                        lineBreak();
                        System.out.println("Enter name:");
                        name = scanner.nextLine();
                        isEdited = true;
                    }
                    lineBreak();

                    isYes = askYesOrNo(scanner, "Would you like to edit the role?");
                    if (isYes) {
                        lineBreak();
                        System.out.println("Enter Role:");
                        role = scanner.nextLine();
                        isEdited = true;
                    }
                    tm.editUser(uID, name, role);
                    lineBreak();
                    updateUserMessage(user, isEdited);
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
                    lineBreak();
                    System.out.println("Task assgined to the user " + tm.getUser(uID).getName() + " with UID: " + uID);
                }

                case "asgnMU" -> {
                    TaskInput input = requestMultipleUIDs(scanner);
                    if (!validateTask(tm, input.getTaskID()))
                        break;
                    input.getUIDS().removeIf(uID -> !tm.containsUser(uID));
                    tm.assignTasktoUsers(input.getTaskID(), input.getUIDS());
                    lineBreak();
                    System.out.println("Task assgined to users");
                }

                case "asgnMT" -> {
                    TaskInput input = requestMultipleTaskIDs(scanner);
                    if (!validateTask(tm, input.getUID()))
                        break;
                    input.getTaskIDS().removeIf(taskID -> !tm.containsUser(taskID));
                    tm.assignTaskstoUser(input.getUID(), input.getTaskIDS());
                    lineBreak();
                    System.out.println(
                            "Tasks assgined to the user " + tm.getUser(input.getUID()) + " with UID: "
                                    + input.getUID());
                }

                case "asgnMTU" -> {
                    ArrayList<Integer> taskIDs = requestMultipleIDs(scanner, "task");
                    ArrayList<Integer> uIDs = requestMultipleIDs(scanner, "user");
                    taskIDs.removeIf(taskID -> !tm.containsTask(taskID));
                    uIDs.removeIf(uID -> !tm.containsUser(uID));
                    tm.assignTaskstoUsers(uIDs, taskIDs);
                    lineBreak();
                    System.out.println("Tasks assgined to users");
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
                    tm.unassignTaskAndUser(uID, taskID);
                    lineBreak();
                    System.out.println(
                            "Task unassgined from the user " + tm.getUser(uID).getName() + " with UID: " + uID);
                }

                case "unasgnMU" -> {
                    TaskInput input = requestMultipleUIDs(scanner);
                    if (!validateTask(tm, input.getTaskID()))
                        break;
                    input.getUIDS().removeIf(uID -> !tm.containsUser(uID));
                    tm.unassignUsersFromTask(input.getTaskID(), input.getUIDS());
                    lineBreak();
                    System.out.println("Task unassgined from users");
                }

                case "unasgnMT" -> {
                    TaskInput input = requestMultipleTaskIDs(scanner);
                    if (!validateTask(tm, input.getUID()))
                        break;
                    input.getTaskIDS().removeIf(taskID -> !tm.containsUser(taskID));
                    tm.unassignTasksFromUser(input.getUID(), input.getTaskIDS());
                    lineBreak();
                    System.out.println("Tasks unassgined from " + tm.getUser(input.getUID()) + " with UID: "
                            + input.getUID());

                }

                case "unasgnMTU" -> {
                    ArrayList<Integer> taskIDs = requestMultipleIDs(scanner, "task");
                    ArrayList<Integer> uIDs = requestMultipleIDs(scanner, "user");
                    taskIDs.removeIf(taskID -> !tm.containsTask(taskID));
                    uIDs.removeIf(uID -> !tm.containsUser(uID));
                    tm.unassignTasksFromUsers(uIDs, taskIDs);
                    lineBreak();
                    System.out.println("Tasks unassgined from users");

                }

                // --------------------------------------------
                // 👀 Task Viewing
                // --------------------------------------------

                case "viewT" -> {
                    int taskID = requestTaskID(scanner);
                    if (!validateTask(tm, taskID))
                        break;
                    tm.viewTask(taskID);
                    viewTaskMenu: while (true) {
                        System.out.println("""
                                b. Back to main menu
                                q. Quit
                                """);
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "b" -> {
                                break viewTaskMenu;
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
                    viewUserMenu: while (true) {
                        System.out.println("""
                                b. Back to main menu
                                q. Quit
                                """);
                        String choice = scanner.nextLine();
                        switch (choice) {
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

                case "viewAU" -> {
                    viewUsersMenu: while (true) {
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
                                break viewUsersMenu;
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