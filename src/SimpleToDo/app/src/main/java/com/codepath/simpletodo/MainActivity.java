package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
            Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_SHORT).show();
            if(pos > -1) {
                Item item = new Item();
                item.setId(id);
                item.setText(text);
                provider.updateItem(item);
                items.set(pos,item);
                itemsAdapter.notifyDataSetChanged();
            }
            // Toast the name to display temporarily on screen
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

}
