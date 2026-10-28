package se.iths.romeo.webshop.model;

import java.util.List;
import se.iths.romeo.webshop.model.Product;

public interface ProductStorage {
    void saveProduct(Product product);
    List<Product> getProducts();
    Product getProduct(String articleNumber);
}











