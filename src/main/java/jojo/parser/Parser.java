package jojo.parser;

import jojo.command.*;
import jojo.exception.JoJoException;

public class Parser {

    public static Command parse(String fullCommand) throws JoJoException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toUpperCase();
        String arguments = (parts.length > 1) ? parts[1].trim() : "";

        switch (commandWord) {
            case "BYE":
                return new ExitCommand();

            case "LIST":
                return new ListCommand();

            case "MARK":
                return new MarkCommand(parseIndex(arguments));

            case "UNMARK":
                return new UnmarkCommand(parseIndex(arguments));

            case "TODO":
                if (arguments.isEmpty()) {
                    throw new JoJoException(" OOPS!!! The description of a todo cannot be empty, add a space after todo follow by the task.");
                }
                return new AddTodoCommand(arguments);

            case "DEADLINE":
                if (!arguments.contains(" /by ")) {
                    throw new JoJoException(" OOPS!!! Use format: deadline [task] /by [date(yyyy-mm-dd)]");
                }
                String[] dParts = arguments.split(" /by ");
                return new AddDeadlineCommand(dParts[0], dParts[1]);

            case "EVENT":
                if (!arguments.contains(" /from ") || !arguments.contains(" /to ")) {
                    throw new JoJoException(" OOPS!!! Use format: event [task] /from [start] /to [end]");
                }
                String[] eParts = arguments.split(" /from ");
                String[] tParts = eParts[1].split(" /to ");
                return new AddEventCommand(eParts[0], tParts[0], tParts[1]);

            case "DELETE":
                if (arguments.isEmpty()) {
                    throw new JoJoException(" OOPS!!! Add a task number after 'delete'.");
                }
                return new DeleteCommand(parseIndex(arguments));

            default:
                throw new JoJoException(" OOPS!!! I'm sorry, but I don't know what that means :-( you can use command list/mark/unmark/todo/deadline/event/delete/bye. ");
        }
    }

    private static int parseIndex(String args) throws JoJoException {
        try {
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new JoJoException(" OOPS!!! Please provide a valid task number.");
        }
    }
}