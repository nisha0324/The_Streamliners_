package Task1;

public class SpecialProduct extends Product{
    int regularPrice, percentageOff;

    public SpecialProduct(String name, int price, int regularPrice, int percentageOff) {
        super(name, price);
        this.regularPrice = regularPrice;
        this.percentageOff = percentageOff;
    }

    public SpecialProduct(String name, int price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return String.format("regularPrice = %d , percentageOff = %d", regularPrice, percentageOff);
    }

    public static SpecialProduct applyOffOnProduct(Product product, int percentageOff)
    {
        int discountedPrice;
        discountedPrice = product.price - ((percentageOff * product.price)/100);

        SpecialProduct s1 = new SpecialProduct(product.name,discountedPrice, discountedPrice, percentageOff);
        // s1.regularPrice = product.price;
        // s1.percentageOff = percentageOff;

        return s1;
    }
}