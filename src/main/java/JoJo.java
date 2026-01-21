import java.util.Scanner;
import java.util.ArrayList;
public class JoJo {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

        ArrayList<Task> list = new ArrayList<>();

        System.out.println(horizontalLine);
        System.out.println(" Hello! I'm JoJo");
        System.out.println(" What can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            else if (input.equalsIgnoreCase("list")) {
                System.out.println(horizontalLine);
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + list.get(i));
                }
            }
            else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                list.get(index).markAsDone();
                System.out.println(horizontalLine);
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + list.get(index));
            }
            else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                list.get(index).unmark();
                System.out.println(horizontalLine);
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + list.get(index));
            }
            else {
                list.add(new Task(input));
                System.out.println(horizontalLine);
                System.out.println(" added: " + input);
            }

            System.out.println(horizontalLine);
        }

        System.out.println(horizontalLine);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
