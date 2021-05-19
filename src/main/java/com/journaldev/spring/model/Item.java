package com.journaldev.spring.model;

public class Item {
    private int _id;
    private String _item;

    public Item(){}

    public Item(int id, String item) {
        _id = id;
        _item = item;
    }

    public void setId(int id) {
        _id = id;
    }

    public int getId() {
        return _id;
    }

    public void setItem(String item) {
        _item = item;
    }

    public String getItem(){
		return _item;
	}
}
