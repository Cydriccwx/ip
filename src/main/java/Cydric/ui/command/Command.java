package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.ui.CydricException;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CydricException;
    public boolean isExit() {
        return false;
    }
}
