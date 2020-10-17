package Task2;

public class GroceryCartItem extends GroceryItem {
    float price;
    float quantity;

    public GroceryCartItem(String name, int pricePerKg, int price, float quantity) {
        super(name, pricePerKg);
        this.price = price;
        this.quantity = quantity;
    }

    public GroceryCartItem(String name, int pricePerKg) {
        super(name, pricePerKg);
    }

    public static float extractQuantity(String quantityStr){

        String s = quantityStr.replace("kg ", ".")
                .replace("kg", ".")
                .replace("g", "");
        int index = s.indexOf(".");
        if (index == -1 && s.length()<3) {
            s = ".0" + s;
//            System.out.println(s);
        }
//        System.out.println(Float.parseFloat(s));
        return Float.parseFloat(s);

    }

    @Override
    public String toString() {
        return String.format("%s ( %d * %f ) = %f",name,pricePerKg,quantity,price);
    }

    public static GroceryCartItem createNew(GroceryItem item, String quantityStr)
    {

        GroceryCartItem cartItem = new GroceryCartItem(item.name, item.pricePerKg);
        cartItem.quantity = extractQuantity(quantityStr);
        cartItem.price = cartItem.pricePerKg * cartItem.quantity;

        return cartItem;

    }
}