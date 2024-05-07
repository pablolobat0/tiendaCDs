package exceptions;

public class ProductDoesNotExists extends Exception {
    public ProductDoesNotExists(String message) {
        super(message);
    }
}
