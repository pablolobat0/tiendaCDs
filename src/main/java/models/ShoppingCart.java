package models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.io.Serializable;

public class ShoppingCart implements Serializable {
    private HashMap<Product, Integer> products;

    public ShoppingCart() {
        this.products = new HashMap<>();
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void addItem(Product product, int quantity) {
        products.put(product, quantity);
    }

    public void removeItem(Product product) {
        products.remove(product);
    }

    public void clear() {
        products.clear();
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO; // Inicializamos el total en 0

        for (Product item : products.keySet()) {
            BigDecimal itemsPrice = item.getPrice();
            BigDecimal quantity = new BigDecimal(products.get(item)); // Cantidad de productos
            BigDecimal subtotal = itemsPrice.multiply(quantity); // Subtotal para este producto
            totalPrice = totalPrice.add(subtotal); // Agregar al total
        }

        return totalPrice;
    }
}
