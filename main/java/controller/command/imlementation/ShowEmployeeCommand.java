package controller.command.imlementation;

import controller.command.Command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowEmployeeCommand implements Command<ResultSet,String> {
    public ResultSet execute(Connection connection, String request) throws SQLException{
        Statement statement = connection.createStatement();
        String query = "SELECT Employee_Number, LastName, FirstName FROM Employee";
        if(request!=null){
            query+=" INNER JOIN Department ON Department.Department_Number = Employee.Department_Number";
            query+=" WHERE Department_Name = \""+ request+"\"";
        }
        return statement.executeQuery(query);
    }
}
