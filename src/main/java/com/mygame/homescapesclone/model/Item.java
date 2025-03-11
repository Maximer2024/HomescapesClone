package com.mygame.homescapesclone.model;

public class Item {
    private int id;
    private String emoji;
    private String name;
    private int price;

    public Item(int id, String emoji, String name, int price) {
        this.id = id;
        this.emoji = emoji;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getEmoji() { return emoji; }
    public String getName() { return name; }
    public int getPrice() { return price; }
}
