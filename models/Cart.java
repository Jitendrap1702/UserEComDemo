package models;

import models.CartItem;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    // Map to save all cartItem
    public Map<String, CartItem> map = new HashMap<>();

    // Map to save VB product's total qty
    public Map<String, Integer> totalVariantQtyMap = new HashMap<>();

    // variable to save total items and total price
    public int noOfItems, subTotal;

    // models.Variant Based models.Product

    /** method to add VB Product to cart
     *
     * @param product Product to be added
     * @param variant Product's variant to be added
    // * @return updated Qty of variant
     */
    public void addVBPToCart(Product product, Variant variant){
        // Unique key
        String key = product.name + " " + variant.name ;
        if (map.containsKey(key)){
            // update map
            map.get(key).qty++;
        }
        else{
            // put in map
            map.put(key, new CartItem(key, variant.price));
        }
        // update summary
        noOfItems++;
        subTotal += variant.price;
        // update total VBP Qty
        if (totalVariantQtyMap.containsKey(product.name)){
            int qty = totalVariantQtyMap.get(product.name) + 1;
            totalVariantQtyMap.put(product.name, qty);
        }else{
            totalVariantQtyMap.put(product.name, 1);
        }

        //return (int)map.get(key).qty;
    }

    /** Method to remove a VB Product from cart
     *
     * @param product Product to be removed
     * @param variant Product's models.Variant to be removed
     //* @return updated Qty of Variant
     */
    public void removeVBPFromCart(Product product, Variant variant){
        // Unique key
        String key = product.name + " " + variant.name ;
        // Update in map
        map.get(key).qty--;
        // check for complete removal
        if(map.get(key).qty == 0){
            map.remove(key);
        }
        // Update Summary
        noOfItems--;
        subTotal -= variant.price;
        // Update total VBP Qty
        int qty = totalVariantQtyMap.get(product.name) - 1;
        totalVariantQtyMap.put(product.name, qty);
        // Check for complete removal
        if (totalVariantQtyMap.get(product.name) == 0) {
            totalVariantQtyMap.remove(key);
        }
        // if totalVariantQtyMap contain product then return qty of product else return 0
        // we can use getOrDefault(key, DefaultValue) for API level > 24
        //return totalVariantQtyMap.containsKey(key) ? (int) map.get(key).qty : 0;
    }

    // Remove all variants from cart
    public void removeAllVariantsFromCart(Product product){
        for (Variant variant : product.variants) {
            String key = product.name + " " + variant.name;

            if(map.containsKey(key)){
                subTotal -= map.get(key).price;
                noOfItems -= map.get(key).qty;
            }
        }
        if (totalVariantQtyMap.containsKey(product.name)){
            totalVariantQtyMap.remove(product.name);
        }
    }

    // Weight Based models.Product

    /** Method to update a WB Product to cart
     *
     * @param product Product to be updated
     * @param qty Quantity of Product
     */
    public void updateWBPQuantity(Product product, float qty){
        // Calculate newPrice
        int newPrice = (int) (product.pricePerKg * qty);

        // Decrement Previous Price
        if (map.containsKey(product.name)){
            subTotal -= map.get(product.name).price;
            // Added for first time, so increment noOfItems
        }else{
            noOfItems++;
        }
        // OverWrite previous info or put new info
        map.put(product.name, new CartItem(product.name, newPrice, qty));
        subTotal += newPrice;
    }

    /** Method to remove a WB product from cart
     *
     * @param product product to be removed
     */
    public void removeWBPFromCart(Product product){
        if(map.containsKey(product.name)){
            subTotal -= map.get(product.name).price;
            noOfItems--;
            map.remove(product.name);
        }
    }

}
