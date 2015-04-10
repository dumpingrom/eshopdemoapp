package com.videodesk.eshopdemoapp;

import java.util.ArrayList;

/**
 * Created by romain@videodesk on 04/03/15.
 *
 * Selected items (added in cart) info are stored here.
 *
 *
 */
public class DataHolder {
    private ArrayList<String[]> cart = new ArrayList<>();

    /**
     *
     * @return
     * ArrayList - Complete current cart content (used for cart layout generation)
     */
    public ArrayList<String[]> getCart(){
        return cart;
    }

    /**
     * Get a specific product from cart
     * @param i
     * The index of the String[] to be returned from the ArrayList
     * @return String[]
     * 0: title
     * 1: short description
     * 2: long description
     * 3: price
     * 4: image path
     */
    public String[] getProduct(int i){
        return cart.get(i);
    }

    /**
     * Add an item to cart
     * @param s the String[] to be added
     */
    public void setCart(String[] s){
        this.cart.add(s);
    }

    /**
     * Removes a specific item from cart
     * @param i
     * The index of the String[] containing the item to be removed
     */
    public void removeFromCart(int i){
        this.cart.remove(i);
    }

    private static final DataHolder holder = new DataHolder();

    /**
     *
     * @return DataHolder
     */
    public static DataHolder getInstance() {return holder;}
}
