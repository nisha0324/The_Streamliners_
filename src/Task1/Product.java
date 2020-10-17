package Task1;

import java.util.Objects;

public class Product {
    String name;
    int price;

    // constructor
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name) {
        this.name = name;
    }

    // overriding tostring method
    @Override
    public String toString() {
        return String.format("name = %s , price = %d", name, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Product p = (Product) obj;
        if (!Objects.equals(this.name, p.name)) {
            return false;
        }

        if (this.price != p.price) {
            return false;
        }
        return true;
    }
}