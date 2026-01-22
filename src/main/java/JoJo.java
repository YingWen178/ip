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
            try{
                if (input.equalsIgnoreCase("list")) {
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
                else if (input.startsWith("deadline")) {
                    if (input.trim().length() <= 8) {
                        throw new JoJoException(" OOPS!!! The description of a deadline cannot be empty, add a space after todo follow by the task and deadline.");
                    }
                    if (!input.startsWith("deadline ")) {
                        throw new JoJoException(" OOPS!!! Please add a space after 'deadline' follow by the task and deadline.");
                    }
                    String[] str = input.substring(9).split(" /by ");
                    Deadline d = new Deadline(str[0], str[1]);
                    list.add(d);
                    System.out.println(horizontalLine);
                    System.out.println(" Got it. I've added this task:\n   " + d);
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                }
                else if (input.startsWith("todo")) {
                    if (input.trim().length() <= 4) {
                        throw new JoJoException(" OOPS!!! The description of a todo cannot be empty, add a space after todo follow by the task.");
                    }
                    if (!input.startsWith("todo ")) {
                        throw new JoJoException(" OOPS!!! Please add a space after 'todo' follow by the task.");
                    }
                    String str = input.substring(5);
                    Todo t = new Todo(str);
                    list.add(t);
                    System.out.println(horizontalLine);
                    System.out.println(" Got it. I've added this task:\n   " + t);
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");

                }
                else if (input.startsWith("event")) {
                    if (input.trim().length() <= 5) {
                        throw new JoJoException(" OOPS!!! The description of a event cannot be empty, add a space after event follow by the task and start and end date/time.");
                    }
                    if (!input.startsWith("event ")) {
                        throw new JoJoException(" OOPS!!! Please add a space after 'event' follow by the task and start and end date/time.");
                    }
                    String[] str = input.substring(6).split(" /from ");
                    String[] timeStr = str[1].split(" /to ");
                    Event e = new Event(str[0], timeStr[0], timeStr[1]);
                    list.add(e);
                    System.out.println(" Got it. I've added this task:\n   " + e);
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                }
                else {
                    throw new JoJoException(" OOPS!!! I'm sorry, but I don't know what that means :-( you can list or create/edit a task instead");
                }
            }
            catch (JoJoException e) {
                System.out.println(horizontalLine);
                System.out.println(e.getMessage());
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println(horizontalLine);
                System.out.println(" OOPS!!! That task number doesn't exist.");
            }
            catch (NumberFormatException e) {
                System.out.println(horizontalLine);
                System.out.println(" OOPS!!! Please provide a valid task number.");
            }


            System.out.println(horizontalLine);
        }

        System.out.println(horizontalLine);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
