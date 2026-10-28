package se.iths.romeo.webshop.model;

//Abstract betyder att klassen inte kan skapa egna objekt drekt
// Den fungerar som en grund för andra rabbatklasser
//alla discount klasser ärver ifrån discount te halfprocentdiscount osv
public abstract class Discount {

    // lagrar en beskrivning av rabatten.
    // protected gör att klassen och att subklasserna kan komma åt variabelna
    protected String description;

    // konstruktorn körs när en subklass skapar en rabatt.
    // Den tar emot en beskrivning och sparar den i variabeln
    public Discount(String description) {
        this.description = description;
    }
    // Retunerar beskrivning av rabatten
    public String getDescription(){
        return description;
    }

    // abstract betyder att metoden bara deklareras här.
    // Den har ingen egen kod mellan { } som räknar ut priset.
    // Det beror på att olika rabatter räknar ut priset olika.
    // Klasser som ärver från Discount måste därför implementera metoden.
    //
    // double betyder att metoden returnerar ett tal som kan ha decimaler.
    // calculatePrice är metodens namn.
    // double originalPrice är parametern som tar emot ordinarie pris.
    public abstract double calculatePrice(double originalPrice);
}

