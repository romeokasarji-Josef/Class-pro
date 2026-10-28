package se.iths.romeo.webshop.model.test;

import org.junit.jupiter.api.Test;
import se.iths.romeo.webshop.model.HalfProcentDiscount;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HalfProcentDiscountTest {

    @Test
    void shouldCalculateHalfPrice() {
        HalfProcentDiscount discount =
                new HalfProcentDiscount("Halva priset");

        double price = 100.0;
        double discountedPrice = discount.calculatePrice(price);

        assertEquals(50.0, discountedPrice);
    }
}