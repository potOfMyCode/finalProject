package model.entity.myException;

public class WorkerNotExistException extends RuntimeException{
    public WorkerNotExistException(String id) {
        super("Worker with id \"" + id + "\" not exist");
    }
}
