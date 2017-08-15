package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items;
    ItemsAdapter itemsAdapter;
    ListView lvItems;
    DataProvider provider;
    private final int REQUEST_CODE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvItems);
        provider = new DataProvider();
        items = provider.readItems();
        itemsAdapter = new ItemsAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);

        setupListViewListener();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);

        String itemText = etNewItem.getText().toString();
        Item item = new Item();
        item.setText(itemText);
        itemsAdapter.add(item);
        etNewItem.setText("");
        provider.addItem(itemText);
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public  boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        Item deletedItem = items.get(pos);
                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        provider.deleteItem(deletedItem);
                        Toast.makeText(MainActivity.this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                        return  true;
                    }
                }
        );

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
                        Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                        intent.putExtra("item_id", items.get(pos).id);
                        intent.putExtra("item_pos", pos);
                        intent.putExtra("item_text", items.get(pos).getText());
                        intent.putExtra("item_priority", items.get(pos).getPriority());
                        intent.putExtra("item_dueYear", items.get(pos).getDueYear());
                        intent.putExtra("item_dueMonth", items.get(pos).getDueMonth());
                        intent.putExtra("item_dueDay", items.get(pos).getDueDay());
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String text = data.getExtras().getString("item_text");
            int pos = data.getExtras().getInt("item_pos");
            int id = data.getExtras().getInt("item_id");
            String priority = data.getExtras().getString("item_priority");
            int dueYear = data.getExtras().getInt("item_dueYear");
            int dueMonth = data.getExtras().getInt("item_dueMonth");
            int dueDay = data.getExtras().getInt("item_dueDay");


            if(pos > -1) {
                Item item = new Item(id, text, dueDay, dueMonth, dueYear, priority);
                provider.updateItem(item);
                items.set(pos,item);
                itemsAdapter.notifyDataSetChanged();
            }

            Toast.makeText(this, "Item saved successfully", Toast.LENGTH_SHORT).show();
        }
    }

}
