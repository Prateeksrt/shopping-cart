package shopping.cart;

import java.util.List;

public interface Offer {
    double discount(List<Product> products);
}
