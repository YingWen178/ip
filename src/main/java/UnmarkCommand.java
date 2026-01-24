public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoJoException {
        tasks.unmark(index);
        ui.showLine();
        System.out.println(" OK, I've marked this task as not done yet:\n   " + tasks.get(index));
        storage.save(tasks);
    }
}
