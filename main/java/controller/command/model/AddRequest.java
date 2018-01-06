package controller.command.model;

public class AddRequest {
    private String employeeNumber;
    private String taskNumber;
    private String taskDescription;

    public AddRequest(String employeeNumber, String taskNumber, String taskDescription){
        this.employeeNumber = employeeNumber;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
}
