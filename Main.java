import java.util.ArrayList;
import java.util.Scanner; // ✅ Import statements must go OUTSIDE the class
import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class Main {

    static void lineBreak() {
        System.out.println();
    }

    static void prompt() {
        System.out.println("Would you like to add a task: command-> addT");
        System.out.println("Would you like to remove a task: command-> rmT");
        System.out.println("Would you like to edit a task: command-> editT");
        System.out.println("Would you like view all task: command -> viewT");
        lineBreak();

        System.out.println("Would you like to add a user: command-> addU");
        System.out.println("Would you like to remove a user: command-> rmU");
        System.out.println("Would you like to view all users: command-> viewU");
        lineBreak();

        System.out.println("Would you like to assign a task to a user: command-> asgnT");
        System.out.println("Would you like to assign a task to multiple users: command-> asgnMT");
        lineBreak();

        System.out.println("Would you like to unassign a task to a User: command-> unasgnT");
        System.out.println("Would you like to unassign a task to multiple users: command-> unasgnMT");
        lineBreak();

        System.out.println("Type q to quit:");
    }

    static TaskInput requestMultipleUIDs(Scanner scanner) {
        System.out.println("Enter Task ID:");
        int taskID = scanner.nextInt();
        scanner.nextLine();
        lineBreak();
        // User ID
        System.out.println("Enter Multiple User IDs (seperated by commas) like this 1,2,3 :");

        ArrayList<Integer> uIDS = new ArrayList<Integer>();
        String[] nums = scanner.nextLine().split(","); // ← grab exactly one line

        for (String num : nums) {
            uIDS.add(Integer.parseInt(num.trim()));
        }
        return new TaskInput(taskID, uIDS);
    }

    public static void main(String[] args) {

        // Going to be huge while loop
        // Inside the while I get description of the task, task name, due date, the user
        // can add or delete task,
        // Assign to different users.

        Task task = null;
        User user = null;
        TaskManager tm = new TaskManager();

        lineBreak();
        System.out.println("This is the CLI Task Manager tool");
        prompt();

        Scanner scanner = new Scanner(System.in); // create scanner for console
        // scanner.nextLine();
        String option = scanner.nextLine(); // read entire line

        while (!option.equals("q")) {
            lineBreak();
            switch (option) {

                // Adding Tasks
                case "addT" -> {
                    // Title
                    System.out.println("Enter a title:");
                    option = scanner.nextLine();
                    task = new Task(option);

                    // Description
                    lineBreak();
                    System.out.println("Would you like to add a description? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter description:");
                        option = scanner.nextLine();
                        task.setDescription(option);
                    }
                    lineBreak();

                    // Due date
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

                // Removing Tasks
                case "rmT" -> {
                    System.out.println("Enter Task ID:");
                    int taskID = scanner.nextInt();
                    scanner.nextLine();
                    tm.removeTask(taskID);
                }

                // Editing Tasks
                case "editT" -> {
                    System.out.println("Enter Task ID:");

                    // Fields
                    int taskID = scanner.nextInt();
                    scanner.nextLine();
                    String title = task.getTitle();
                    String description = task.getDescription();
                    String dueDate = task.getDueDate();

                    // Title

                    System.out.println("Would you like to edit the title? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter Title:");
                        option = scanner.nextLine();
                        title = option;
                    }
                    // Description
                    System.out.println("Would you like to edit the description? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter description:");
                        option = scanner.nextLine();
                        description = option;
                    }
                    // Due date
                    System.out.println("Would you like to edit the due date? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter due date:");
                        option = scanner.nextLine();
                        dueDate = option;
                    }
                    System.out.println("The task ID is: " + task.getID());
                    tm.editTask(taskID, title, description, dueDate);
                }

                // Adding a User
                case "addU" -> {
                    System.out.println("Enter the username:");

                    // Username
                    option = scanner.nextLine();
                    user = new User(option);

                    // Role
                    lineBreak();
                    System.out.println("Would you like to add a role? (y/n)");
                    option = scanner.nextLine();
                    lineBreak();
                    if (option.equals("y")) {
                        System.out.println("Enter role:");
                        option = scanner.nextLine();
                        user.setRole(option);
                    }
                    lineBreak();
                    System.out.println("The user ID is: " + user.getuID());
                    tm.addUser(user);
                }

                // View All Users
                case "viewU" -> {
                    System.out.println(
                            "How would you like to filter the users?\n" +
                                    "1. By Role\n" +
                                    "2. View All\n\n" +
                                    "Enter your choice (1-2):");
                    option = scanner.nextLine();
                    switch (option) {
                        case "1" -> {
                            System.out.println("Enter Role: ");
                            String role = scanner.nextLine();
                            tm.viewUsersByRole(role);
                        }
                        case "2" -> {
                            tm.viewUsers();
                        }
                        default -> {
                            System.out.println("Invalid option.");
                        }
                    }
                }

                // Assigning a Single User
                case "asgnT" -> {
                    // Task ID
                    System.out.println("Enter Task ID:");
                    int taskID = scanner.nextInt();
                    lineBreak();
                    // User ID
                    System.out.println("Enter User ID:");
                    int uID = scanner.nextInt();
                    scanner.nextLine();
                    tm.assignTasktoUser(taskID, uID);
                }

                case "asgnMT" -> {
                    TaskInput input = requestMultipleUIDs(scanner);
                    tm.assignTasktoUsers(input.getTaskID(), input.getUIDS());
                }

                // Unassigning a Single User
                case "unasgnT" -> {
                    // Task ID
                    System.out.println("Enter Task ID:");
                    int taskID = scanner.nextInt();
                    scanner.nextLine();
                    lineBreak();
                    // User ID
                    System.out.println("Enter User ID:");
                    int uID = scanner.nextInt();
                    scanner.nextLine();
                    tm.unassignTaskFromUser(taskID, uID);

                }

                case "unasgnMT" -> {
                    TaskInput input = requestMultipleUIDs(scanner);
                    tm.unassignTaskFromUsers(input.getTaskID(), input.getUIDS());
                }

                case "viewT" -> {
                    System.out.println(
                            "How would you like to filter the tasks?\n" +
                                    "1. By Status\n" +
                                    "2. By Due Date\n" +
                                    "3. By Assigned User\n" +
                                    "4. View All\n\n" +
                                    "Enter your choice (1-4):");
                    option = scanner.nextLine();
                    switch (option) {
                        case "1" -> {
                            System.out.println("Enter status (TODO, IN_PROGRESS, DONE):");
                            TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                            tm.viewTasksByStatus(status);
                        }
                        case "2" -> {
                            System.out.println("Enter due date (YYYY-MM-DD): ");
                            String dueDate = scanner.nextLine();
                            tm.viewTasksByDueDate(dueDate);
                        }
                        case "3" -> {
                            System.out.println("Enter user UID:");
                            int uID = scanner.nextInt();
                            scanner.nextLine();
                            tm.viewTasksByUser(uID);
                        }
                        case "4" -> {
                            tm.viewTasks();
                        }
                        default -> {
                            System.out.println("Invalid option.");
                        }
                    }
                }

                default -> {
                    System.out.println("Invalid option.");
                }
            }
            lineBreak();
            prompt();
            option = scanner.nextLine(); // read entire line
        }
        scanner.close();
        lineBreak();
        // System.out.println(tm.);
        // Assigning Users
    }
}