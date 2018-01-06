package controller.command.imlementation;

import controller.command.Command;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteEmployeeCommand implements Command<Boolean, String> {
    public Boolean execute(Connection connection, String request) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "DELETE FROM Task WHERE Task.Employee_Number = " + request;
        statement.execute(query);
        query = "DELETE FROM Employee WHERE Employee.Employee_Number = " + request;
        statement.execute(query);
        return true;
    }
}

