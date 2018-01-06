package controller;

import controller.command.CommandFactory;
import controller.command.model.AddRequest;
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
    private CommandFactory commandFactory;


    public enum Menu {EMPLOYEES, DEMPLOYEES, TASKS, ADD, ETASKS, DELETE, EXIT, DEFAULT}

    public MainController(MainView mainView, MyScanner scanner, Connection connection) {
        this.mainView = mainView;
        this.scanner = scanner;
        this.connection = connection;
        this.commandFactory = new CommandFactory();
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
    
    private void showAllEmployees() {
        try {
            ResultSet resultSet =commandFactory.getShowEmployeeCommand().execute(connection, null);
            mainView.displayResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAllTasks() {
        try {
            ResultSet resultSet = commandFactory.getShowTaskCommand().execute(connection, null);
            mainView.displayResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showDepartmentEmployees() {
        mainView.displayMessage(MainView.REQUEST_DEPARTMENT);
        try {
            ResultSet resultSet = commandFactory.getShowEmployeeCommand().execute(connection, scanner.readInput());
            mainView.displayResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addTask() {
        Integer employeeNumber = Integer.parseInt(inputValue(MainView.REQUEST_EMPLOYEE_NUMBER, NUMBER_PATTERN));
        Integer taskNumber = Integer.parseInt(inputValue(MainView.REQUEST_TASK_NUMBER, NUMBER_PATTERN));
        String taskDescription = inputValue(MainView.REQUEST_TASK_DESCRIPTION, PATTERN);
        AddRequest request = new AddRequest(employeeNumber, taskNumber, taskDescription);
        try {
            mainView.displayMessage(commandFactory.getAddTaskCommand().execute(connection, request));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showEmployeeTasks() {
        try {
            Integer employeeNumber = Integer.parseInt(inputValue(MainView.REQUEST_EMPLOYEE_NUMBER,NUMBER_PATTERN));
            ResultSet resultSet =commandFactory.getShowTaskCommand().execute(connection, employeeNumber);
            mainView.displayResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteEmployee() {
        try {
            Integer employeeNumber = Integer.parseInt(inputValue(MainView.REQUEST_EMPLOYEE_NUMBER,NUMBER_PATTERN));
            commandFactory.getDeleteCommand().execute(connection, employeeNumber);
            mainView.displayMessage(MainView.DELETED);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        isRunning = false;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
