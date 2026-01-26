package jojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jojo.command.AddDeadlineCommand;
import jojo.command.AddEventCommand;
import jojo.command.AddTodoCommand;
import jojo.command.Command;
import jojo.command.DeleteCommand;
import jojo.command.ExitCommand;
import jojo.command.ListCommand;
import jojo.command.MarkCommand;
import jojo.command.UnmarkCommand;
import jojo.exception.JoJoException;
import jojo.parser.Parser;

public class ParserTest {

    @Test
    public void parse_exitCommand_success() throws Exception {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
        assertInstanceOf(ExitCommand.class, Parser.parse("BYE"));
    }

    @Test
    public void parse_listCommand_success() throws JoJoException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
    }

    @Test
    public void parse_markCommand_success() throws JoJoException {
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"));
    }

    @Test
    public void parse_markCommand_missingIndex_exceptionThrown() {
        try {
            Parser.parse("mark notANumber");
            fail();
        } catch (JoJoException e) {
            assertEquals(" OOPS!!! Please provide a valid task number.", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkCommand_success() throws JoJoException {
        assertInstanceOf(UnmarkCommand.class, Parser.parse("unmark 1"));
    }

    @Test
    public void parse_todoCommand_success() throws JoJoException {
        assertInstanceOf(AddTodoCommand.class, Parser.parse("todo read book"));
    }

    @Test
    public void parse_todoCommand_emptyDescription_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (JoJoException e) {
            assertEquals(" OOPS!!! The description of a todo cannot be empty, add a space after todo follow by the task.", e.getMessage());
        }
    }

    @Test
    public void parse_eventCommand_withSpaces_trimmed() throws JoJoException {
        Command actual = Parser.parse("event    project meeting    /from Mon 2pm /to 4pm");

        Command expected = new AddEventCommand("project meeting", "Mon 2pm", "4pm");

        assertEquals(expected, actual);
    }

    @Test
    public void parse_deadlineCommand_success() throws JoJoException {
        assertInstanceOf(AddDeadlineCommand.class, Parser.parse("deadline return book /by 2024-01-01"));
    }

    @Test
    public void parse_deadlineCommand_invalidFormat_exceptionThrown() {
        try {
            Parser.parse("deadline return book");
            fail();
        } catch (JoJoException e) {
            assertEquals(" OOPS!!! Use format: deadline [task] /by [date(yyyy-mm-dd)]", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineCommand_correctValues() throws JoJoException {
        Command actual = Parser.parse("deadline return book /by 2024-01-01");

        Command expected = new AddDeadlineCommand("return book", "2024-01-01");

        assertEquals(expected, actual);
    }

    @Test
    public void parse_eventCommand_success() throws JoJoException {
        assertInstanceOf(AddEventCommand.class, Parser.parse("event meeting /from 2pm /to 4pm"));
    }

    @Test
    public void parse_eventCommand_invalidFormat_exceptionThrown() {
        try {
            Parser.parse("event meeting /from 2pm");
            fail();
        } catch (JoJoException e) {
            assertEquals(" OOPS!!! Use format: event [task] /from [start] /to [end]", e.getMessage());
        }
    }

    @Test
    public void parse_eventCommand_correctValues() throws JoJoException {
        Command actual = Parser.parse("event project meeting /from Mon 2pm /to 4pm");

        Command expected = new AddEventCommand("project meeting", "Mon 2pm", "4pm");

        assertEquals(expected, actual);
    }

    @Test
    public void parse_deleteCommand_success() throws JoJoException {
        assertInstanceOf(DeleteCommand.class, Parser.parse("delete 1"));
    }

    @Test
    public void parse_deleteCommand_emptyArgs_exceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (JoJoException e) {
            assertEquals(" OOPS!!! Add a task number after 'delete'.", e.getMessage());
        }
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        try {
            Parser.parse("unknow command");
            fail();
        } catch (JoJoException e) {
            assertEquals(" OOPS!!! I'm sorry, but I don't know what that means :-( you can use command list/mark/unmark/todo/deadline/event/delete/bye.", e.getMessage());
        }
    }
}