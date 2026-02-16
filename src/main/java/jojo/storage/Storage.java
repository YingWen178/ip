package jojo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import jojo.task.Deadline;
import jojo.task.Event;
import jojo.task.Task;
import jojo.task.TaskList;
import jojo.task.Todo;

/**
 * Handles the loading and saving of task data to a local file.
 * Responsible for reading from and writing to the hard disk.
 */
public class Storage {
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final String DELIMITER = " \\| ";

    private String filePath;

    /**
     * Creates a new Storage instance.
     *
     * @param filePath The file path where tasks will be stored and retrieved.
     */
    public Storage(String filePath) {
        assert filePath != null : "filePath should not be null";
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file specified during initialization.
     * If the file does not exist, a new empty file is created.
     *
     * @return An ArrayList of Task objects parsed from the storage file.
     * @throws IOException If there is an error creating or reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        assert filePath != null : "filePath should be initialized before load";
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
            return list;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.trim().isEmpty()) {
                    Task t = parseTask(line);
                    assert t != null : "parsed task should not be null for a valid line";
                    list.add(t);
                }
            }
        }
        return list;
    }

    /**
     * Saves the current list of tasks to the storage file.
     * Overwrites the existing file content with the current task list.
     *
     * @param tasks The TaskList containing the tasks to save.
     */
    public void save(TaskList tasks) {
        assert tasks != null : "TaskList to save should not be null";
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task t : tasks.getAll()) {
                assert t != null : "Tasks in the list should not be null";
                fw.write(t.toSaveString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    /**
     * Parses a single line of text from the storage file into a Task object.
     * format: TYPE | IS_DONE | DESCRIPTION | [EXTRA_ARGS]
     *
     * @param line The raw string line from the file.
     * @return A Task object (Todo, Deadline, or Event), or null if the type is unknown.
     */
    private Task parseTask(String line) {
        String[] parts = line.split(DELIMITER);
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case TODO_TYPE:
            task = new Todo(description);
            break;
        case DEADLINE_TYPE:
            task = new Deadline(description, parts[3]);
            break;
        case EVENT_TYPE:
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            return null;
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
