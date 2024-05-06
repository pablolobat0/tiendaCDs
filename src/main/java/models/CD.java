package models;

import java.math.BigDecimal;

public class CD implements Product {
  private String name;
  private BigDecimal price;

  public CD(String name, BigDecimal price) {
    this.name = name;
    this.price = price;
  }

  public String getName() { return name; }
  public BigDecimal getPrice() { return price; }
}
