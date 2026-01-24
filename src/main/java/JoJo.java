import java.util.Scanner;

public class JoJo {
    private static final String FILE_PATH = "jojo.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public JoJo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (java.io.IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showHello();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCmd();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);

                isExit = c.isExit();
            } catch (JoJoException e) {
                ui.showErr(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showErr(" OOPS!!! That task number doesn't exist in your list.");
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new JoJo(FILE_PATH).run();
    }

}
