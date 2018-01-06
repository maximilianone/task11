package controller.command.imlementation;

import controller.command.model.AddRequest;
import controller.command.Command;
import controller.exception.ExistingNumberException;
import controller.exception.NoEmployeeException;

import java.sql.*;

public class AddTaskCommand implements Command<Boolean,AddRequest> {
    public Boolean execute(Connection connection, AddRequest request) throws SQLException{
        if(!checkEmployee(connection, request.getEmployeeNumber())){
            throw new NoEmployeeException();
        }
        if(!checkTask(connection, request.getTaskNumber())){
            throw new ExistingNumberException();
        }
        String query = "INSERT INTO Task (Task_Number, Task_Description, Employee_Number) Values(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        insert(statement,Integer.parseInt(request.getTaskNumber()),
                Integer.parseInt(request.getEmployeeNumber()), request.getTaskDescription());
        return true;
    }

    private boolean checkEmployee(Connection connection, String request) throws SQLException{
        Statement statement = connection.createStatement();
        String query = "SELECT Employee_Number FROM Employee";
        query+=" WHERE Employee.Employee_Number = "+ request;
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        return resultSet.getRow() != 0;
    }

    private boolean checkTask(Connection connection, String request) throws SQLException{
        Statement statement = connection.createStatement();
        String query = "SELECT Task_Number FROM Task";
        query+=" WHERE Task.Task_Number = "+ request;
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        return resultSet.getRow() == 0;
    }

    private void insert(PreparedStatement ps, int taskN, int employeeN, String taskDescription) throws SQLException{
        ps.setInt(1,taskN);
        ps.setString(2,taskDescription);
        ps.setInt(3,employeeN);
        ps.executeUpdate();
    }
}
