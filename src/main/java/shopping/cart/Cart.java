package shopping.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {
    private final List<Product> items;
    private final double taxRate;
    private final List<Offer> offers;

    public Cart(double taxRate) {
        this.taxRate = taxRate;
        this.items = new ArrayList<>();
        this.offers = new ArrayList<>();
    }

    public int numberOfProducts(){
        return items.size();
    }

    public void add(Product product) {
        items.add(product);
    }

    public double getTotalPrice() {
        return items.stream()
                .map(Product::getPrice)
                .reduce((a,b) -> a+b)
                .orElse(0.0) - getDiscount();
    }

    public double taxAmount() {
        return (this.taxRate / 100 ) * getTotalPrice() ;
    }

    public DeliveryMode getDeliveryMode() throws NoProductException {
        if (items.isEmpty()) {
            throw new NoProductException();
        }
        List<DeliveryMode> deliveryModes = items.stream()
                .map(Product::getDeliveryMode)
                .distinct().collect(Collectors.toList());
        return deliveryModes.size() > 1 ?
                DeliveryMode.MIXED :
                deliveryModes.get(0);
    }

    public void addOffers(Offer offer) {
        offers.add(offer);
    }

    public double getDiscount() {
        return offers.stream()
                .map(offer -> offer.discount(items))
                .reduce((a,b) -> a+b)
                .orElse(0.0);
    }

    public double getTotalPriceWithTax() {
        return getTotalPrice() + taxAmount();
    }
}
