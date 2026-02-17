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
    private static final String BYE_CMD = "BYE";
    private static final String LIST_CMD = "LIST";
    private static final String MARK_CMD = "MARK";
    private static final String UNMARK_CMD = "UNMARK";
    private static final String TODO_CMD = "TODO";
    private static final String DEADLINE_CMD = "DEADLINE";
    private static final String EVENT_CMD = "EVENT";
    private static final String DELETE_CMD = "DELETE";
    private static final String FIND_CMD = "FIND";

    private static final String BY_DELIMITER = " /by ";
    private static final String FROM_DELIMITER = " /from ";
    private static final String TO_DELIMITER = " /to ";

    /**
     * Parses the full user command and returns the corresponding Command object.
     *
     * @param fullCommand The full string input entered by the user.
     * @return A Command object corresponding to the user's input.
     * @throws JoJoException If the command format is invalid or unknown.
     */
    public static Command parse(String fullCommand) throws JoJoException {
        assert fullCommand != null : "fullCommand should not be null";
        String trimmedCommand = fullCommand.trim();
        if (trimmedCommand.isEmpty()) {
            throw new JoJoException("NANI?! You didn't enter anything!");
        }
        String[] parts = trimmedCommand.split("\\s+", 2);
        assert parts.length > 0 : "split should return at least one part";
        String commandWord = parts[0].toUpperCase();
        assert !commandWord.isEmpty() : "commandWord should not be empty";
        String arguments = (parts.length > 1) ? parts[1].trim() : "";

        switch (commandWord) {
        case BYE_CMD:
            return new ExitCommand();
        case LIST_CMD:
            return new ListCommand();
        case MARK_CMD:
            return new MarkCommand(parseIndex(arguments));
        case UNMARK_CMD:
            return new UnmarkCommand(parseIndex(arguments));
        case TODO_CMD:
            return parseTodo(arguments);
        case DEADLINE_CMD:
            return parseDeadline(arguments);
        case EVENT_CMD:
            return parseEvent(arguments);
        case DELETE_CMD:
            return parseDelete(arguments);
        case FIND_CMD:
            return parseFind(arguments);
        default:
            throw new JoJoException("NANI?! I'm sorry, but I don't know what that means :-( "
                    + "you can use command list/mark/unmark/todo/deadline/event/delete/bye.");
        }
    }

    private static Command parseTodo(String arguments) throws JoJoException {
        if (arguments.isEmpty()) {
            throw new JoJoException("NANI?! The description of a todo cannot be empty, "
                    + "add a space after todo follow by the task.");
        }
        return new AddTodoCommand(arguments);
    }

    private static Command parseDeadline(String arguments) throws JoJoException {
        if (!arguments.contains("/by")) {
            throw new JoJoException("NANI?! Use format: deadline [task] /by [date(yyyy-mm-dd)]");
        }
        String[] dParts = arguments.split("\\s+/by\\s+", 2);
        if (dParts.length < 2 || dParts[0].trim().isEmpty() || dParts[1].trim().isEmpty()) {
            throw new JoJoException("NANI?! The description and date of a deadline cannot be empty.");
        }
        if (dParts[1].contains("/by")) {
            throw new JoJoException("NANI?! You specified /by multiple times.");
        }
        return new AddDeadlineCommand(dParts[0].trim(), dParts[1].trim());
    }

    private static Command parseEvent(String arguments) throws JoJoException {
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            throw new JoJoException("NANI?! Use format: event [task] /from [start] /to [end]");
        }

        String[] fromParts = arguments.split("\\s+/from\\s+", 2);
        if (fromParts.length < 2 || fromParts[0].trim().isEmpty()) {
            throw new JoJoException("NANI?! Event description or /from part is missing.");
        }

        String[] toParts = fromParts[1].split("\\s+/to\\s+", 2);
        if (toParts.length < 2 || toParts[0].trim().isEmpty() || toParts[1].trim().isEmpty()) {
            throw new JoJoException("NANI?! Event /from or /to content is missing.");
        }

        if (toParts[1].contains("/from") || toParts[1].contains("/to") || toParts[0].contains("/from")) {
            throw new JoJoException("NANI?! You specified /from or /to multiple times.");
        }

        return new AddEventCommand(fromParts[0].trim(), toParts[0].trim(), toParts[1].trim());
    }

    private static Command parseDelete(String arguments) throws JoJoException {
        if (arguments.isEmpty()) {
            throw new JoJoException("NANI?! Add a task number after 'delete'.");
        }
        return new DeleteCommand(parseIndex(arguments));
    }

    private static Command parseFind(String arguments) throws JoJoException {
        if (arguments.isEmpty()) {
            throw new JoJoException("NANI?! Add a keyword after 'find'.");
        }
        return new FindCommand(arguments.trim());
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
            int index = Integer.parseInt(args) - 1;
            if (index < 0) {
                throw new JoJoException("NANI?! Please provide a positive task number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new JoJoException("NANI?! Please provide a valid task number.");
        }
    }
}
