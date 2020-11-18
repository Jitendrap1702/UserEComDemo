package models;

import java.security.PublicKey;

public class CartItem {

    public String name;
    public float qty;
    public int price;

    // for variantBased
    public CartItem(String name, int price){
        this.name = name;
        this.price = price;
        qty = 1;
    }
    // for weightBased
    public CartItem(String name, int price, float qty){
        this.name = name;
        this.price = price;
        this.qty = qty;
    }
}
