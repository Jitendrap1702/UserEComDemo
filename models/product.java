package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product {

    public static final byte WEIGHT_BASED = 0, VARIANTS_BASED = 1;

    // Compulsory
    public String name;
    public byte type;

    // Weight Based models.Product
    public int pricePerKg;
    public float minQty;

    // models.Variant Based models.Product
    public List<Variant> variants = new ArrayList<>();

    // WeightBased Constructor
    public Product(String name, int pricePerKg, float minQty){
        type = WEIGHT_BASED;
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.minQty = minQty;
    }

    // VariantBased Constructor
    public Product(String name){
        type = VARIANTS_BASED;
        this.name = name;
    }
}
