package controller.command.model;

public class AddRequest {
    private Integer employeeNumber;
    private Integer taskNumber;
    private String taskDescription;

    public AddRequest(Integer employeeNumber, Integer taskNumber, String taskDescription){
        this.employeeNumber = employeeNumber;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
}
