package Cydric.ui;

import Cydric.storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CydricException;
    public boolean isExit() {
        return false;
    }
}
