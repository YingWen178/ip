package jojo.parser;

import jojo.command.AddDeadlineCommand;
import jojo.command.AddEventCommand;
import jojo.command.AddTodoCommand;
import jojo.command.Command;
import jojo.command.DeleteCommand;
import jojo.command.ExitCommand;
import jojo.command.FindCommand;
import jojo.command.ListCommand;
import jojo.command.MarkCommand;
import jojo.command.UnmarkCommand;
import jojo.exception.JoJoException;

/**
 * Parses user input into executable commands.
 */
public class Parser {

    /**
     * Parses the full user command and returns the corresponding Command object.
     *
     * @param fullCommand The full string input entered by the user.
     * @return A Command object corresponding to the user's input.
     * @throws JoJoException If the command format is invalid or unknown.
     */
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
                throw new JoJoException(" OOPS!!! The description of a todo cannot be empty, "
                        + "add a space after todo follow by the task.");
            }
            return new AddTodoCommand(arguments);

        case "DEADLINE":
            if (!arguments.contains(" /by ")) {
                throw new JoJoException(" OOPS!!! Use format: deadline [task] /by [date(yyyy-mm-dd)]");
            }
            String[] dParts = arguments.split(" /by ");
            return new AddDeadlineCommand(dParts[0].trim(), dParts[1].trim());

        case "EVENT":
            if (!arguments.contains(" /from ") || !arguments.contains(" /to ")) {
                throw new JoJoException(" OOPS!!! Use format: event [task] /from [start] /to [end]");
            }
            String[] eParts = arguments.split(" /from ");
            String[] tParts = eParts[1].split(" /to ");
            return new AddEventCommand(eParts[0].trim(), tParts[0].trim(), tParts[1].trim());

        case "DELETE":
            if (arguments.isEmpty()) {
                throw new JoJoException(" OOPS!!! Add a task number after 'delete'.");
            }
            return new DeleteCommand(parseIndex(arguments));

        case "FIND":
            if (arguments.isEmpty()) {
                throw new JoJoException(" OOPS!!! Add a keyword after 'find'.");
            }
            return new FindCommand(arguments.trim());

        default:
            throw new JoJoException(" OOPS!!! I'm sorry, but I don't know what that means :-( "
                    + "you can use command list/mark/unmark/todo/deadline/event/delete/bye.");
        }
    }

    /**
     * Parses a string argument into a zero-based index.
     *
     * @param args The string containing the index number.
     * @return The zero-based index (input - 1).
     * @throws JoJoException If the input is not a valid integer.
     */
    private static int parseIndex(String args) throws JoJoException {
        try {
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new JoJoException(" OOPS!!! Please provide a valid task number.");
        }
    }
}
