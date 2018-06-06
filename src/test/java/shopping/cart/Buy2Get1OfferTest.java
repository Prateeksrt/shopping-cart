package shopping.cart;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class Buy2Get1OfferTest {

    private Product doveSoap = new Product("Dove soap", 39.99, DeliveryMode.OFFLINE);
    private Product axeDeo = new Product("Axe Deo", 99.99, DeliveryMode.OFFLINE);

    @Test
    public void discountShouldBePriceOf1ProductWhen3ProductsArePassed() {

        Buy2Get1Offer offer = new Buy2Get1Offer("Dove soap");

        double price = offer.discount(Arrays.asList(doveSoap, doveSoap, doveSoap));

        assertEquals(39.99, price, 0.01);
    }

    @Test
    public void when6ProductsPassedDiscountShouldBePriceOf2Products() {

        Buy2Get1Offer offer = new Buy2Get1Offer("Dove soap");

        double price = offer.discount(Arrays.asList(doveSoap, doveSoap, doveSoap,
                doveSoap, doveSoap, doveSoap));

        assertEquals(79.98, price, 0.01);
    }

    @Test
    public void when8ProductsPassedDiscountShouldBePriceOf2Products() {

        Buy2Get1Offer offer = new Buy2Get1Offer("Dove soap");

        double price = offer.discount(Arrays.asList(doveSoap, doveSoap, doveSoap,
                doveSoap, doveSoap, doveSoap, doveSoap, doveSoap));

        assertEquals(79.98, price, 0.01);
    }
}
