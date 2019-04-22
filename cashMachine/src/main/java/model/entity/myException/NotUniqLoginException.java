package model.entity.myException;

public class NotUniqLoginException extends RuntimeException{
    public NotUniqLoginException(String login) {
        super("Login \"" + login + "\" is already exist");
    }
}
