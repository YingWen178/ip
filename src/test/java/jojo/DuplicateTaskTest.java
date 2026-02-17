package jojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import jojo.command.AddDeadlineCommand;
import jojo.command.AddEventCommand;
import jojo.command.AddTodoCommand;
import jojo.exception.JoJoException;
import jojo.storage.Storage;
import jojo.task.Deadline;
import jojo.task.Event;
import jojo.task.TaskList;
import jojo.task.Todo;
import jojo.ui.Ui;

public class DuplicateTaskTest {

    @Test
    public void execute_duplicateTodo_returnsErrorMessage() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test_jojo.txt");

        Todo todo = new Todo("read book");
        tasks.add(todo);

        AddTodoCommand cmd = new AddTodoCommand("read book");
        String result = cmd.execute(tasks, ui, storage);

        assertTrue(result.contains("already exists"), "Result should contain duplicate error message");
        assertEquals(1, tasks.size(), "Task list size should not increase");

        // Clean up
        new File("test_jojo.txt").delete();
    }

    @Test
    public void execute_duplicateDeadline_returnsErrorMessage() throws JoJoException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test_jojo.txt");

        Deadline deadline = new Deadline("submit assignment", "2026-02-10");
        tasks.add(deadline);

        AddDeadlineCommand cmd = new AddDeadlineCommand("submit assignment", "2026-02-10");
        String result = cmd.execute(tasks, ui, storage);

        assertTrue(result.contains("already exists"), "Result should contain duplicate error message");
        assertEquals(1, tasks.size(), "Task list size should not increase");

        // Clean up
        new File("test_jojo.txt").delete();
    }

    @Test
    public void execute_duplicateEvent_returnsErrorMessage() throws JoJoException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test_jojo.txt");

        Event event = new Event("concert", "2026-02-10 1800", "2026-02-10 2000");
        tasks.add(event);

        AddEventCommand cmd = new AddEventCommand("concert", "2026-02-10 1800", "2026-02-10 2000");
        String result = cmd.execute(tasks, ui, storage);

        assertTrue(result.contains("already exists"), "Result should contain duplicate error message");
        assertEquals(1, tasks.size(), "Task list size should not increase");

        // Clean up
        new File("test_jojo.txt").delete();
    }
}
