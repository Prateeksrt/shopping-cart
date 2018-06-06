package shopping.cart;

import java.util.List;
import java.util.stream.Collectors;

public class Buy2Get1Offer implements Offer {

    private String productType;

    public Buy2Get1Offer(String productType) {
        this.productType = productType;
    }

    @Override
    public double discount(List<Product> products) {
        List<Product> validProducts = filterValidProducts(products);
        double productPrice = validProducts.get(0).getPrice();
        return (validProducts.size() / 3) * productPrice;
    }

    private List<Product> filterValidProducts(List<Product> products) {
        return products.stream()
                .filter(product -> product.getName().equals(this.productType))
                .collect(Collectors.toList());
    }
}
