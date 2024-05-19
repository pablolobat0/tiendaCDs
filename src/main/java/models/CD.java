package models;

import java.math.BigDecimal;
import java.util.Objects;

public class CD implements Product {
    private String name;
    private BigDecimal price;

    public CD(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CD cd = (CD) o;
        if (!this.name.equals(cd.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
