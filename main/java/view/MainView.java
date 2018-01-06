package view;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MainView implements Constants{
    public void showMenu(){
        System.out.println(ALL_EMPLOYEES);
        System.out.println(ALL_TASK);
        System.out.println(EMPLOYEE_TASKS);
        System.out.println(EMPLOYEES);
        System.out.println(ADD_TASK);
        System.out.println(DELETE);
        System.out.println(EXIT_OPTION);
    }

    public void displayMessage(String message){
        System.out.println(message);
    }

    public void displayResultSet(ResultSet resultSet) throws SQLException{
        displayMessage(RESULT);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        while (resultSet.next()){
            for (int i = 1; i<=rsmd.getColumnCount(); i++){
                System.out.print(resultSet.getString(i));
                if (i != rsmd.getColumnCount()){
                    System.out.print(" ");
                }else {
                    System.out.println("");
                }
            }
        }

    }
}
