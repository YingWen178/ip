import java.util.Scanner;
import java.util.ArrayList;
public class JoJo {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

        ArrayList<String> list = new ArrayList<>();

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
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + list.get(i));
                }
            } else {
                list.add(input);
                System.out.println(horizontalLine);
                System.out.println(" added: " + input);
            }
//            if (input.equalsIgnoreCase("jojo")) {
//                System.out.println(horizontalLine);
//                System.out.println("It's me!");
//            }

            System.out.println(horizontalLine);
        }

        System.out.println(horizontalLine);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
