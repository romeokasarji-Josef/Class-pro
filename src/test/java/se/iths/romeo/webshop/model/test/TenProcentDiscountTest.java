package se.iths.romeo.webshop.model.test;
import org.junit.jupiter.api.Test;
import se.iths.romeo.webshop.model.TenProcentDiscount;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TenProcentDiscountTest {

    @Test
    void shoulCalculateTenPercentDiscount() {
        TenProcentDiscount discount =
                new TenProcentDiscount("10% Rabatt");

        double price = 100.0;

        double discountedPrice = discount.calculatePrice(price);

        assertEquals(90.0, discountedPrice);
    }

}
