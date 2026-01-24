import java.util.Scanner;

public class Ui {
    private final String horizontalLine = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showHello() {
        System.out.println(horizontalLine);
        System.out.println(" Hello! I'm JoJo");
        System.out.println(" What can I do for you?");
        System.out.println(horizontalLine);
    }

    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println(horizontalLine);
    }

    public void showAddedTask(Task task, int totalTasks) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
    }

    public String readCmd() {
        return scanner.nextLine();
    }

    public void showErr(String msg) {
        System.out.println(msg);
    }

    public void showLoadingError() {
        showLine();
        System.out.println(" No file found. Created new file: jojo.txt");
        showLine();
    }
}
