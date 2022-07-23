package com.example.csprojedeneme;


public class Item {

    private String id;
    private int cost;

    public Item(){

    }
    public Item(String id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }
}
