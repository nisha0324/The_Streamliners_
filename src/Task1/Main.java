package Task1;

import static Task1.SpecialProduct.applyOffOnProduct;

public class Main {

    public static void main(String[] args) {
        Product p1 = new Product("Book1",200);
        Product p2 = new Product("Book1",200);
        Product p3 = new Product("Book2",400);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        System.out.println((p1.equals(p2)));

        //applyOffOnProduct(p1, 50);

        System.out.println( applyOffOnProduct(p1, 50));


    }
}