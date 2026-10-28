package se.iths.romeo.webshop.model;

public class TenProcentDiscount extends Discount {
    public TenProcentDiscount(String description) {
        super(description);
    }

    @Override
    public double calculatePrice(double originalPrice) {

        //Gör orginal priset rabatterat med 10%
        return originalPrice * 0.9;
    }
}
