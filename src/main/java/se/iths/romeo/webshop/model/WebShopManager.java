package se.iths.romeo.webshop.model;

import java.util.List;


//klassen hanterar webbshoppen.
//Den kopplar ihop produktlagringen, rabbatkoderna och menyn.
public class WebShopManager {

    //Ett klassatribut som sparar objektet som hanterar produktlagrigen.
    // produkt storage är ett interface,vilket betyder att olika klasser
    // kan implementera hur produkter sparas och hämtas
    private ProductStorage productStorage;

    //En konstruktor som tar emot ett objekt som impemenerar ProductsStorage
    // Objektet Skapas utanför  WebShopMangerger och skickas in här
    public WebShopManager(ProductStorage productStorage) {

        // Sparar de mottagna lagringsobjektet i klassens atribut.
        this.productStorage = productStorage;
    }

    // Sparar en produkt genom att anropa lagringsobjektets metod.
    // void betyder att metoden inte retunerar något värde.
    // Product product är parametern som innehåller produkten som ska sparas
    public void saveProductToStorage(Product product) {
        productStorage.saveProduct(product);
    }
    // Hämtar alla produkter från lagringen.
    // List<Product> betyder att metoden returnerar en lista med Product-objekt.
    public List<Product> getProductsFromStorage() {
        return productStorage.getProducts();

    }

    // Hämtar en produkt genom att söka på dess artikelnummer.
    // String articleNumber är texten med artikelnumret vi letar efter.
    // Metoden returnerar produkten om den hittas, annars null.
    public Product getProductFromStorage(String articleNumber) {
        return productStorage.getProduct(articleNumber);

    }

    // Startar webbshoppen och visar menyn tills användaren väljer att avsluta.
    public void startWebshop() {

        // null betyder att variabeln inte innehåller något rabattobjekt ännu.
        Discount discount = null;

        // Läser in rabattkoden som användaren skriver i konsolen.
        String code = IO.readln("Ange rabattkod");

        // equals jämför textens innehåll med den angivna rabattkoden.
        if (code.equals("HalfPrice")) {

            // skapar ibjekt som ger halvapriset.
            discount = new HalfProcentDiscount("Halva priset");
        } else if (code.equals("TenthDiscount")) {

            //skapar objekt som ger 10% rabatt
            discount = new TenProcentDiscount("10% Rabatt");
        } else {
            System.out.println("Ingen rabatt");
        }

        // while (true) skapar en loop som fortsätter tills den avbryts.
        // Här gör den att menyn visas igen efter varje menyval.
        while (true) {

            // visar alternativen i menyn
            IO.println("1. Lägg till produkt");
            IO.println("2. Lista alla produkter");
            IO.println("3. Visa produktinformation");
            IO.println("4: Avsluta");

            String svar = IO.readln("Välj ett alternativ");

            // switch väljer vilken kod som ska köras beroende på menyvalet.
            switch (svar) {
                case "1":

                    // Läser in produktens artikelnummer, titel, pris och beskrivning.
                    String articleNumber = IO.readln("Ange artikelnummer: ");
                    String title = IO.readln("Ange produktens titel: ");


                    // Double.parseDouble omvandlar texten från inmatningen till double.
                    // Om användaren skriver exempelvis 50 blir priset 50.0.
                    double price = Double.parseDouble(IO.readln("Ange pris: "));

                    String description = IO.readln("Ange beskrivning: ");

                    // Skapar ett nytt Product-objekt med informationen som matats in.
                    Product product = new Product(articleNumber, title, price, description);

                    // Skickar produkten till metoden som sparar den i lagringen.
                    saveProductToStorage(product);

                    IO.println("Produkten har sparats!");

                    // break avslutar detta case och lämnar switch-blocket.
                    // Loopen fortsätter sedan och visar menyn igen.
                    break;

                case "2":

                    // Hämtar alla produkter och sparar dem i en lista.
                    List<Product> products = getProductsFromStorage();

                    // for-each går igenom varje produkt i listan.
                    // product1 representerar produkten i den aktuella iterationen.
                    for (Product product1 : products) {

                        // Skriver ut produktens olika egenskaper.
                        IO.println("Artikelnummer: "
                                + product1.getArticleNumber());
                        IO.println("Titel: " + product1.getTitle());
                        IO.println("Ordinarie pris: "
                                + product1.getPrice());
                        IO.println("Beskrivning: "
                                + product1.getDescription());

                        // Om discount är null finns inget rabattobjekt.
                        if (discount == null) {
                            IO.println("Pris: " + product1.getPrice());
                        } else {

                            // Annars beräknas priset med den valda rabatten.
                            IO.println("Rabatterat pris: "
                                    + discount.calculatePrice(
                                    product1.getPrice()));
                        }

                        // En avdelare gör listan lättare att läsa.
                        IO.println("--------------------");
                    }

                    break;
                case "3":


                    // Frågar efter artikelnumret på produkten som ska hittas
                    String searchNumber =
                            IO.readln("Ange artikelnummer: ");

                    // söker efetr produkten via Webshopmangeger
                    Product foundProduct =
                            getProductFromStorage(searchNumber);
                    // != null betyder att en produkt hittades.
                    if (foundProduct != null) {

                        // Skriver ut information om den hittade produkten.
                        IO.println("Artikelnummer: "
                                + foundProduct.getArticleNumber());
                        IO.println("Titel: "
                                + foundProduct.getTitle());
                        IO.println("Pris: "
                                + foundProduct.getPrice());
                        IO.println("Beskrivning: "
                                + foundProduct.getDescription());
                    } else {

                        // Körs om sökningen inte hittar någon produkt.
                        IO.println("Produkten hittades inte.");
                    }
                    break;
                case "4":
                    // meddelar anvendaren att webbshoppen avslutas
                    IO.println("Webbshoppen avslutas.");
                    return;

                default:
                    IO.println("Ogiltigt val");


            }
        }
    }
}