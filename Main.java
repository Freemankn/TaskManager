import java.util.Scanner; // ✅ Import statements must go OUTSIDE the class
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    static void lineBreak() {
        System.out.println();
    }

    static void prompt() {
        System.out.println("Would you like to add a task: command-> addT");
        System.out.println("Would you like to remove a task: command-> rmT");
        System.out.println("Would you like to edit a task: command-> editT");
        lineBreak();

        System.out.println("Would you like to add a user: command-> addU");
        System.out.println("Would you like to remove a user: command-> rmU");
        lineBreak();

        System.out.println("Would you like to assign a task to a User: command-> asgnT");
        System.out.println("Would you like to unassign a task to a User: command-> unasgnT");
        lineBreak();
        System.out.println("Type q to quit:");
    }

    public static void main(String[] args) {

        // Going to be huge while loop
        // Inside the while I get description of the task, task name, due date, the user
        // can add or delete task,
        // Assign to different users.

        Task task = null;
        User user = null;
        TaskManager tm = new TaskManager();

        System.out.println("This is the CLI Task Manager tool");
        prompt();

        Scanner scanner = new Scanner(System.in); // create scanner for console
        // scanner.nextLine();
        String option = scanner.nextLine(); // read entire line

        while (!option.equals("q")) {

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
                    lineBreak();

                    tm.addTask(task);
                }

                // Removing Tasks
                // case "rmT" -> {
                // System.out.println("Enter Task ID:");

                // int taskID = scanner.nextInt();
                // scanner.nextLine();
                // tm.deleteTask(taskID);
                // }

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
                    lineBreak();

                    // Description
                    System.out.println("Would you like to edit the description? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter description:");
                        option = scanner.nextLine();
                        description = option;
                    }
                    lineBreak();

                    // Due date
                    System.out.println("Would you like to edit the due date? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter due date:");
                        option = scanner.nextLine();
                        dueDate = option;
                    }
                    lineBreak();

                    System.out.println("The task ID is: " + task.getID());
                    lineBreak();
                    tm.editTask(taskID, title, description, dueDate);
                }

                // Adding Users
                case "addU" -> {
                    System.out.println("Enter the username:");

                    // Username
                    option = scanner.nextLine();
                    user = new User(option);

                    // Role
                    lineBreak();
                    System.out.println("Would you like to add a role? (y/n)");
                    option = scanner.nextLine();
                    if (option.equals("y")) {
                        System.out.println("Enter role:");
                        option = scanner.nextLine();
                        user.setRole(option);
                    }
                    lineBreak();
                    System.out.println("The user ID is: " + user.getuID());
                    lineBreak();

                    tm.addUser(user);
                }

                // Assigning Users
                case "asgnT" -> {
                    // Task ID
                    System.out.println("Enter Task ID:");
                    int taskID = scanner.nextInt();
                    // User ID
                    System.out.println("Enter User ID:");
                    int uID = scanner.nextInt();
                    lineBreak();

                    tm.assignTask(taskID, uID);
                }

                // Unassigning Users
                // case "unasgnT" -> {
                // System.out.println("Enter Task ID:");
                // int taskID = scanner.nextInt();
                // tm.unassignTask(taskID);
                // }

                default -> {
                    System.out.println("Invalid option.");
                }
            }
            prompt();
            option = scanner.nextLine(); // read entire line
        }
        System.out.println(tm.getUser(1));
        // Assigning Users
    }
}