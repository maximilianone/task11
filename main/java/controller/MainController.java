package controller;

import controller.command.model.AddRequest;
import controller.command.CommandList;
import controller.exception.ExistingNumberException;
import controller.exception.NoEmployeeException;
import input.MyScanner;
import view.MainView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainController implements Patterns {
    private MainView mainView;
    private MyScanner scanner;
    private boolean isRunning = true;
    private Connection connection;


    public enum Menu {EMPLOYEES, DEMPLOYEES, TASKS, ADD, ETASKS, DELETE, EXIT, DEFAULT}

    public MainController(MainView mainView, MyScanner scanner, Connection connection) {
        this.mainView = mainView;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void run() {
        while (isRunning) {
            mainView.showMenu();
            chooseOption(scanner.readInput());
        }
    }


    private void chooseOption(String statement) {
        Menu option;
        try {
            option = Menu.valueOf(statement.toUpperCase());
        } catch (java.lang.IllegalArgumentException e) {
            option = Menu.DEFAULT;
        }
        switch (option) {
            case EMPLOYEES:
                showAllEmployees();
                break;
            case TASKS:
                showAllTasks();
                break;
            case DEMPLOYEES:
                showDepartmentEmployees();
                break;
            case ADD:
                addTask();
                break;
            case ETASKS:
                showEmployeeTasks();
                break;
            case DELETE:
                deleteEmployee();
                break;
            case EXIT:
                exit();
                break;
            case DEFAULT:
                mainView.displayMessage(mainView.WRONG_INPUT);
                break;
        }
    }

    private String inputValue(String request, String patt) {
        while (true) {
            mainView.displayMessage(request);
            String input = scanner.readInput();
            Pattern pattern = Pattern.compile(patt);
            Matcher m = pattern.matcher(input);
            if (m.find()) {
                return input;
            }
            mainView.displayMessage((MainView.WRONG_INPUT));
        }
    }

    @SuppressWarnings("unchecked")
    private void showAllEmployees() {
        try {
            ResultSet resultSet = (ResultSet) CommandList.SHOW_EMPLOYEE.getCommand().execute(connection, null);
            mainView.displayResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void showAllTasks() {
        try {
            ResultSet resultSet = (ResultSet) CommandList.SHOW_TASK.getCommand().execute(connection, null);
            mainView.displayResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void showDepartmentEmployees() {
        mainView.displayMessage(MainView.REQUEST_DEPARTMENT);
        try {
            ResultSet resultSet = (ResultSet) CommandList.SHOW_EMPLOYEE.getCommand().execute(connection, scanner.readInput());
            mainView.displayResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void addTask() {
        String employeeNumber = inputValue(MainView.REQUEST_EMPLOYEE_NUMBER, NUMBER_PATTERN);
        String taskNumber = inputValue(MainView.REQUEST_TASK_NUMBER, NUMBER_PATTERN);
        String taskDescription = inputValue(MainView.REQUEST_TASK_DESCRIPTION, PATTERN);
        AddRequest request = new AddRequest(employeeNumber, taskNumber, taskDescription);
        try {
            CommandList.ADD_TASK.getCommand().execute(connection, request);
            mainView.displayMessage(MainView.ADDED);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NoEmployeeException e){
            mainView.displayMessage(e.getMessage());
        }catch (ExistingNumberException e){
            mainView.displayMessage(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void showEmployeeTasks() {
        mainView.displayMessage(MainView.REQUEST_EMPLOYEE_NUMBER);
        try {
            ResultSet resultSet = (ResultSet) CommandList.SHOW_TASK.getCommand().execute(connection, scanner.readInput());
            mainView.displayResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void deleteEmployee() {
        mainView.displayMessage(MainView.REQUEST_EMPLOYEE_NUMBER);
        try {
            CommandList.DELETE_EMPLOYEE.getCommand().execute(connection, scanner.readInput());
            mainView.displayMessage(MainView.DELETED);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        isRunning = false;
    }


}
