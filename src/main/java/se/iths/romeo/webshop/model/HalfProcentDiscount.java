package se.iths.romeo.webshop.model;

// extends betyder att klassen ärver från en annan klass.
// halfProcentDiscount ärver från discount och får tillgång
// till Discounts konstruktor och gemmensamma funkonalitet
public class HalfProcentDiscount extends Discount{

    // det här är konstruktorn för HalfProcentDiscount.
    // den körs när ett objekt av klassen skapas.
    // String descripion är texten som beskriver rabbaten.
    public HalfProcentDiscount(String description) {

        //super anropar konstruktorn i basklassen Discount
        //skriver över en metod som finns i basklassen.
        //implementerar calculatePrice från Discount.
        super(description);
    }

    // @Override betyder att metoden implementerar eller
    // skriver över en metod som finns i basklassen.
    // här implemterar vi calculatePrice från Discount
    @Override
    public double calculatePrice(double originalPrice) {
        // originalPrice är produkttens ursprungliga pris.
        // Vi multiplicera priset med 0.5 för att få 50 % av priset.
        // Exempel: 100 * 0.5 = 50.
        // return skickar tilbaka det beräknade priset.
        return originalPrice * 0.5;
    }
}
