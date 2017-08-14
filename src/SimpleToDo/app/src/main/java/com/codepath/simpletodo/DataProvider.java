package com.codepath.simpletodo;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rdeshpan on 8/13/2017.
 */

public class DataProvider {

    public ArrayList<Item> readItems() {
        ArrayList<Item> items = (ArrayList) SQLite.select().from(Item.class).queryList();
        return items;
    }

    public  void addItem(String text) {
        Item item = new Item();
        item.setText(text);
        item.save();
    }

    public  void updateItem(Item item) {
        Item updatedItem = SQLite.select().from(Item.class).where(Item_Table.id.eq(item.id)).querySingle();
        updatedItem.setText((item.getText()));
        updatedItem.save();
    }

    public  void deleteItem(Item item) {
        Item deletedItem = SQLite.select().from(Item.class).where(Item_Table.id.eq(item.id)).querySingle();
        deletedItem.delete();
    }
}
