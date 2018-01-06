package controller.exception;

public class ExistingNumberException extends RuntimeException{
    @Override
    public String getMessage(){
        return "Task with this number already exist";
    }
}
