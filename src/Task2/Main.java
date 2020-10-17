package Task2;

public class Main {

    public static void main(String[] args) {
        GroceryItem item1 = new GroceryItem("rice",3);
        GroceryItem item2 = new GroceryItem("apple",2);
        GroceryItem item3 = new GroceryItem("mango",5);
        GroceryItem item4 = new GroceryItem("banana",1);

        Cart cart = new Cart();
        cart.add(GroceryCartItem.createNew(item1,"5kg 0g"))
                .add(GroceryCartItem.createNew(item2,"1kg 500g"))
                .add(GroceryCartItem.createNew(item3,"0kg 500g"))
                .add(GroceryCartItem.createNew(item4,"2kg 250g"));

        System.out.println(cart);

    }
}