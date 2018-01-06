package controller.exception;

public class NoEmployeeException extends RuntimeException {
    @Override
    public String getMessage(){
        return "Error! No such employee";
    }
}
