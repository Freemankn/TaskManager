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
        lineBreak();
    }

    public static void main(String[] args) {
        // Task t1 = new Task();
        // t1.displayInfo();

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

        if (option.equals("addT")) {
            System.out.println("Enter a title:");

            // Title
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

        } else if (option.equals("addU")) {
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
        System.out.println(user.getName() + " " + user.getRole());

        // Assigning Users

    }
}