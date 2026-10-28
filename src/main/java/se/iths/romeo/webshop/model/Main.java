package se.iths.romeo.webshop.model;

public class Main {
    public static void main(String[] args) {
        ProductStorage storage = new ProductFileStorage();
        WebShopManager manager = new WebShopManager(storage);

        manager.startWebshop();
    }
}