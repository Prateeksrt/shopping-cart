package shopping.cart;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class CartTest {

    private Product doveSoap = new Product("Dove soap", 39.99, DeliveryMode.OFFLINE);
    private Product axeDeo = new Product("Axe Deo", 99.99, DeliveryMode.OFFLINE);
    private Product windowPro10 = new Product("Windows Pro 10", 4999.99, DeliveryMode.ONLINE);


    @Test
    public void shouldBeAbleToAddProduct(){
        Cart cart = getInitialCartWithTax();

        cart.add(doveSoap);

        assertEquals(cart.numberOfProducts(), 1);
    }

    @Test
    public void shouldGetTotalPriceOf5Soaps() {
        Cart cart = getCartWith5Soaps();

        assertEquals(cart.getTotalPrice(), 199.95, .01);
    }

    @Test
    public void adding3MoreProductsShouldUpdateTheTotalPrice() {
        Cart cart = getCartWith5Soaps();

        cart.add(doveSoap);
        cart.add(doveSoap);
        cart.add(doveSoap);

        assertEquals(cart.getTotalPrice(), 319.92, .01);
    }

    @Test
    public void shouldGetTaxAmountWhenTheTaxRatesAreAdded() {
        Cart cart = getInitialCartWithTax();
        cart.add(doveSoap);

        assertEquals(cart.taxAmount(), 4.99, .01);
    }

    @Test
    public void addingNewProductsShouldUpdateTheTaxAmount() {
        Cart cart = getInitialCartWithTax();
        cart.add(doveSoap);
        cart.add(axeDeo);
        cart.add(doveSoap);
        cart.add(axeDeo);

        assertEquals(35.00, cart.taxAmount(), .01);
    }

    @Test
    public void whenProductOfDifferentDeliveryModeAreAddDeliveryModeShouldBeMixed() throws NoProductException {
        Cart cart = getInitialCartWithTax();

        cart.add(doveSoap);
        cart.add(windowPro10);

        assertEquals(DeliveryMode.MIXED, cart.getDeliveryMode());
    }

    @Test
    public void shouldReturnOfflineWhenAllProductAreOnlineDelivery() throws NoProductException {
        Cart cart = getInitialCartWithTax();

        cart.add(doveSoap);

        assertEquals(DeliveryMode.OFFLINE, cart.getDeliveryMode());
    }

    @Test
    public void getTotalPriceShouldReturn0WhenNoProductsAreAdd() {
        Cart cart = getInitialCartWithTax();

        assertEquals(0.0, cart.getTotalPrice());
    }

    @Test
    public void getDeliveryModeShouldThrowAnExceptionWhenNoProductsAreAdded() {
        Cart cart = getInitialCartWithTax();

        Exception exceptionThrown = null;

        try {
            cart.getDeliveryMode();
        } catch (NoProductException e) {
            exceptionThrown = e;
        }

        assertNotNull(exceptionThrown);
    }

    @Test
    public void getTotalPriceShouldReturnPriceOf2SoapWhenBuy2Get1OfferIsAdded() {
        Cart cart = getInitialCartWithTax();

        cart.addOffers(new Buy2Get1Offer("Dove soap"));
        cart.add(doveSoap);
        cart.add(doveSoap);
        cart.add(doveSoap);

        assertEquals(79.98, cart.getTotalPrice(), 0.01);
    }

    @Test
    public void getTotalPriceShouldReturnPriceOf4SoapWhenBuy2Get1OfferIsAdded() {
        Cart cart = getInitialCartWithTax();

        cart.addOffers(new Buy2Get1Offer("Dove soap"));
        cart.add(doveSoap);
        cart.add(doveSoap);
        cart.add(doveSoap);
        cart.add(doveSoap);
        cart.add(doveSoap);
        cart.add(doveSoap);

        assertEquals(159.96, cart.getTotalPrice(), 0.01);
    }

    @Test
    public void getDiscountShouldReturnTheTotalDiscountOffered() {
        Cart cart = getInitialCartWithTax();

        cart.addOffers(new Buy2Get1Offer("Dove soap"));
        cart.add(doveSoap);
        cart.add(doveSoap);
        cart.add(doveSoap);

        assertEquals(39.99, cart.getDiscount(), 0.01);
    }

    @Test
    public void getTotalPriceShouldApplyOfferForMultipleProducts() {
        Cart cart = getInitialCartWithTax();

        cart.addOffers(new Buy2Get1Offer("Dove soap"));
        cart.addOffers(new Buy2Get1Offer("Axe Deo"));
        cart.add(doveSoap);
        cart.add(doveSoap);
        cart.add(doveSoap);
        cart.add(axeDeo);
        cart.add(axeDeo);
        cart.add(axeDeo);

        assertEquals(279.96, cart.getTotalPrice(), 0.01);
    }

    @Test
    public void getTotalPriceWithTaxShouldApplyOfferAndTax() {
        Cart cart = getInitialCartWithTax();

        cart.addOffers(new Buy2Get1Offer("Dove soap"));
        cart.addOffers(new Buy2Get1Offer("Axe Deo"));
        cart.add(doveSoap);
        cart.add(doveSoap);
        cart.add(doveSoap);
        cart.add(axeDeo);
        cart.add(axeDeo);
        cart.add(axeDeo);

        assertEquals(314.95, cart.getTotalPriceWithTax(), 0.01);
    }

    private Cart getInitialCartWithTax() {
        return new Cart(12.5);
    }

    private Cart getCartWith5Soaps() {
        Cart cart = getInitialCartWithTax();
        for (int i = 0; i < 5; i++) {
            cart.add(doveSoap);
        }
        return cart;
    }
}
