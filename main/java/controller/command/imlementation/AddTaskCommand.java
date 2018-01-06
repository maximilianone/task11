package controller.command.imlementation;

import controller.command.model.AddRequest;
import controller.command.Command;
import java.sql.*;

public class AddTaskCommand implements Command<String ,AddRequest> {
    private static final int NO_SUCH_EMPLOYEE = 1452;
    private static final int TASK_ALREADY_EXIST = 1062;

    public String execute(Connection connection, AddRequest request) throws SQLException {
        String query = "INSERT INTO Task (Task_Number, Task_Description, Employee_Number) Values(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            insert(statement, request.getTaskNumber(),
                    request.getEmployeeNumber(), request.getTaskDescription());
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() == NO_SUCH_EMPLOYEE){
                return "Error! No such employee";
            }else if(e.getErrorCode()== TASK_ALREADY_EXIST) {
                return "Error! Task with this number already exist";
            }else{
                return e.getMessage();
            }
        }
        return "Added";
    }


    private void insert(PreparedStatement ps, int taskN, int employeeN, String taskDescription) throws SQLException{
        ps.setInt(1,taskN);
        ps.setString(2,taskDescription);
        ps.setInt(3,employeeN);
        ps.executeUpdate();
    }

}
