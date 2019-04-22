package model.entity.myException;

public class ProductNotExistException extends RuntimeException{
    public ProductNotExistException(String id) {
        super("Product \"" + id + "\" not exist");
    }
}
