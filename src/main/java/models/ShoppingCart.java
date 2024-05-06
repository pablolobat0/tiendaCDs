package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
  private List<Product> items;

  public ShoppingCart() { this.items = new ArrayList<>(); }

  public List<Product> getItems() { return items; }

  public void addItem(Product product) { items.add(product); }

  public void removeItem(Product product) { items.remove(product); }

  public void clear() { items.clear(); }

  public BigDecimal getTotalPrice() {
    BigDecimal totalPrice = new BigDecimal(0);
    for (Product item : items) {
      totalPrice.add(item.getPrice());
    }
    return totalPrice;
  }
}
