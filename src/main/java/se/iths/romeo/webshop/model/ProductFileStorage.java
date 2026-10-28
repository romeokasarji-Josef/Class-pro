package se.iths.romeo.webshop.model;

import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;

// Klassen hanterar sparning och hämtning av produkter från en fil.
// implements betyder att klassen följer reglerna i interfacet ProductStorage.
public class ProductFileStorage implements ProductStorage{

    // Path representerar en sökväg till en fil.
    // Path.of("products.txt") anger filens namn.
    // Filen används för att spara produkterna så att de finns kvar
    // även efter att programmet har avslutats.
   private Path file = Path.of("products.txt");

    // @Override betyder att metoden implementerar en metod
    // som finns i interfacet ProductStorage.
   @Override
    public void saveProduct(Product product) {
        try {

            // writeString skriver text till filen.
            // product.toFileLine() omvandlar produkten till en textrad.
            // lineSeparator lägger till en radbrytning efter produkten.
            // CREATE skapar filen om den inte redan finns.
            // APPEND lägger till text i slutet utan att radera gammal data.
          Files.writeString(file,
                  product.toFileLine() + System.lineSeparator(),
                  StandardOpenOption.CREATE,
                  StandardOpenOption.APPEND
          );
        }catch (IOException e) {

            // catch körs om ett filfel uppstår.
            // IOException är en typ av fel som kan uppstå vid filhantering.
            // printStackTrace skriver ut information om felet.
            e.printStackTrace();
        }
    }

    // Hämtar alla produkter från filen och returnerar dem som en lista.
    @Override
    public List<Product> getProducts() {

        // Skapar en tom lista som ska innehålla Product-objekt.
        // ArrayList är en lista där vi kan lägga till element.
        List<Product> products = new ArrayList<>();

        // Files.exists kontrollerar om filen finns.
        // ! betyder "inte".
        // Om filen inte finns returnerar vi den tomma listan direkt.
        if (!Files.exists(file)) {
            return products;
        }
        try {

            // Läser alla rader i filen och sparar dem som textsträngar.
            List<String> lines = Files.readAllLines(file);

            // for-each går igenom varje textrad, en i taget.
            for (String line : lines) {

                // Gör om en textrad till ett Product-objekt.
                Product product = Product.fromFileLine(line);

                // add lägger till produkten i listan.
                products.add(product);
            }

        } catch (IOException e) {

            // Hanterar fel som kan uppstå när filen läses.
            e.printStackTrace();
        }
        // Returnerar listan med produkter.
        return products;
    }

    // Letar efter och returnerar en produkt med ett visst artikelnummer.
    @Override
    public Product getProduct(String articleNumber) {

        // Hämtar alla produkter från filen.
      List<Product> products = getProducts();

        // Går igenom varje produkt i listan.
      for (Product product :products) {

          // getArticleNumber hämtar produktens artikelnummer.
          // equals jämför det med artikelnumret vi söker efter.
          if (product.getArticleNumber().equals(articleNumber)) {

              // Returnerar produkten direkt om artikelnumren matchar.
              return product;
          }
      }
        // Om ingen produkt matchar returneras null.
       return null;
    }
}
