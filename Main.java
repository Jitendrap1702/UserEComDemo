import models.Cart;
import models.Product;
import models.Variant;

public class Main {
    public static void main(String[] args){
        Cart cart = new Cart();

        // Weight based product
        Product apple = new Product("Apple", 180, 1);
        Product kiwi = new Product("Kiwi", 80, 2);
        Product orange = new Product("Orange", 100, 4);

        // Variant based product
        Product aata = new Product("aashirwadAata");
        Variant aata1 = new Variant("1kg", 50);
        Variant aata2 = new Variant("5kg", 90);
        aata.variants.add(aata1);
        aata.variants.add(aata2);

        Product namkeen = new Product("bikaji");
        Variant namkeen1 = new Variant("500g", 70);
        Variant namkeen2 = new Variant("1kg", 120);
        namkeen.variants.add(namkeen1);
        namkeen.variants.add(namkeen2);

        // add VB products
        cart.addVBPToCart(aata, aata2);
        cart.addVBPToCart(aata, aata1);
        cart.addVBPToCart(namkeen, namkeen1);
        cart.addVBPToCart(namkeen, namkeen2);

        // add WB products
        cart.updateWBPQuantity(apple, 3);
        cart.updateWBPQuantity(kiwi, 7);
        cart.updateWBPQuantity(orange, 1);

        System.out.println("start");
        System.out.println(cart.map);
        System.out.println(cart.subTotal);
        System.out.println(cart.noOfItems);
        System.out.println(cart.totalVariantQtyMap);
        System.out.println();

        // remove products
        cart.removeVBPFromCart(namkeen, namkeen1);
        cart.removeWBPFromCart(kiwi);
        cart.removeWBPFromCart(orange);

        System.out.println("After removing namkeen1, kiwi, orange, kiwi, orange");
        System.out.println(cart.map);
        System.out.println(cart.subTotal);
        System.out.println(cart.noOfItems);
        System.out.println(cart.totalVariantQtyMap);
        System.out.println();

        // remove all variants of aata
        cart.removeAllVariantsFromCart(aata);

        System.out.println("After removing all variants of aata ");
        System.out.println(cart.map);
        System.out.println(cart.subTotal);
        System.out.println(cart.noOfItems);
        System.out.println(cart.totalVariantQtyMap);

    }
}
