package controller.command;

import controller.command.imlementation.AddTaskCommand;
import controller.command.imlementation.DeleteEmployeeCommand;
import controller.command.imlementation.ShowEmployeeCommand;
import controller.command.imlementation.ShowTaskCommand;
import controller.command.model.AddRequest;

import java.sql.ResultSet;

public class CommandFactory {
    private Command<ResultSet, String> showEmployeeCommand = new ShowEmployeeCommand();
    private  Command<ResultSet, Integer> showTaskCommand = new ShowTaskCommand();
    private Command<String, AddRequest> addTaskCommand = new AddTaskCommand();
    private Command<Boolean, Integer> deleteCommand = new DeleteEmployeeCommand();

    public Command<ResultSet, String> getShowEmployeeCommand() {
        return showEmployeeCommand;
    }

    public Command<ResultSet, Integer> getShowTaskCommand() {
        return showTaskCommand;
    }

    public Command<String, AddRequest> getAddTaskCommand() {
        return addTaskCommand;
    }

    public Command<Boolean, Integer> getDeleteCommand() {
        return deleteCommand;
    }
}
