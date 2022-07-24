package com.example.csprojedeneme;


public class Item {

    private String id;
    private int cost;
    private String category;

    public Item(){

    }

    public Item(String id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    public Item(String id, int cost, String category) {
        this.id = id;
        this.cost = cost;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public String getCategory() {
        return category;
    }
}
