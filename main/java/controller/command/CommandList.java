package controller.command;

import controller.command.imlementation.AddTaskCommand;
import controller.command.imlementation.DeleteEmployeeCommand;
import controller.command.imlementation.ShowEmployeeCommand;
import controller.command.imlementation.ShowTaskCommand;

public enum CommandList {
    SHOW_EMPLOYEE(new ShowEmployeeCommand()),
    SHOW_TASK(new ShowTaskCommand()),
    ADD_TASK(new AddTaskCommand()),
    DELETE_EMPLOYEE(new DeleteEmployeeCommand());
    private Command command;

    CommandList(Command command) {
        this.command = command;
    }
    public Command getCommand(){
        return command;
    }
}
