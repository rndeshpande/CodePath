package com.codepath.simpletodo;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by rdeshpan on 8/13/2017.
 */

public class DataProvider {

    public ArrayList<Item> readItems() {
        ArrayList<Item> items = (ArrayList) SQLite.select().from(Item.class).queryList();
        return items;
    }

    public int addItem(String text) {
        Item item = new Item();
        item.setText(text);
        return (int) item.insert();
    }

    public  void updateItem(Item item) {
        Item updatedItem = new Item(item.id, item.getText(),item.getDueDay(), item.getDueMonth(), item.getDueYear(), item.getPriority());
        updatedItem.save();
    }

    public  void deleteItem(Item item) {
        Item deletedItem = new Item(item.id, item.getText(),item.getDueDay(), item.getDueMonth(), item.getDueYear(), item.getPriority());
        deletedItem.delete();
    }
}
