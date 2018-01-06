package controller.command.imlementation;

import controller.command.Command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowTaskCommand implements Command<ResultSet,Integer> {
    public ResultSet execute(Connection connection, Integer request) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT Task_Number, concat(Task_description, ' - employee:'), LastName, FirstName FROM Task"
        +" Inner JOIN Employee on Employee.Employee_Number= Task.Employee_Number";
        if(request!=null){
            query+=" WHERE Task.Employee_Number = "+ request;
        }
        return statement.executeQuery(query);
    }
}
