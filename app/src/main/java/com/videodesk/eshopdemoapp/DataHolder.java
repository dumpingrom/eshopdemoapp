package com.videodesk.eshopdemoapp;

import java.util.ArrayList;

/**
 * Created by romain@videodesk on 04/03/15.
 */
public class DataHolder {
    private ArrayList<String[]> cart = new ArrayList<>();

    public ArrayList<String[]> getCart(){
        return cart;
    }

    public String[] getProduct(int i){
        return cart.get(i);
    }

    public void setCart(String[] s){
        this.cart.add(s);
    }

    public void removeFromCart(int i){
        this.cart.remove(i);
    }

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}
}
