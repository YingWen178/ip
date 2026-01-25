package jojo;

import jojo.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toString_newTodo_correctFormat() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void toSaveString_correctFormat() {
        Todo todo = new Todo("return book");
        todo.markAsDone();
        assertEquals("T | 1 | return book", todo.toSaveString());
    }

    @Test
    public void toString_markedTodo_correctFormat() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void markAsUndone_updatesIcon() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        todo.unmark();
        assertEquals("[T][ ] read book", todo.toString());
    }

}