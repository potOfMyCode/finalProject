package model.entity.myException;

public class ProductOutOfStockException extends RuntimeException{
    public ProductOutOfStockException(String id) {
        super("Product with id \"" + id + "\" out of stock");
    }
}
