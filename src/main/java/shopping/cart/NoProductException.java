package shopping.cart;

public class NoProductException extends Exception {
    public NoProductException() {
        super("No products found");
    }
}
