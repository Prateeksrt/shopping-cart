package shopping.cart;

public class Product {
    private String name;
    private double price;
    private DeliveryMode deliveryMode;

    public Product(String name, double price, DeliveryMode deliveryMode) {
        this.name = name;
        this.price = price;
        this.deliveryMode = deliveryMode;
    }

    public double getPrice() {
        return price;
    }

    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }

    public String getName() {
        return name;
    }
}
