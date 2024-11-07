package com.example.memory.model;

public class Item{
    private int position;
    private int id;

    public Item(int position, int id) {
        this.position = position;
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }
}
