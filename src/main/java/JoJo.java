import java.util.Scanner;
public class JoJo {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

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
//            if (input.equalsIgnoreCase("jojo")) {
//                System.out.println(horizontalLine);
//                System.out.println("It's me!");
//            }

            System.out.println(horizontalLine);
            System.out.println(" " + input); // Repeat the user's input
            System.out.println(horizontalLine);
        }

        System.out.println(horizontalLine);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
