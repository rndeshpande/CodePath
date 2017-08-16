package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
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
        int id = provider.addItem(itemText);
        Item item = new Item();
        item.setText(itemText);
        item.setId(id);
        itemsAdapter.add(item);
        etNewItem.setText("");
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
                        showEditDialog(pos,
                                items.get(pos).id,
                                items.get(pos).getText(),
                                items.get(pos).getDueDay(),
                                items.get(pos).getDueMonth(),
                                items.get(pos).getDueYear(),
                                items.get(pos).getPriority());

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
            String priority = data.getExtras().getString("item_priority");
            int pos = data.getExtras().getInt("item_pos");
            int id = data.getExtras().getInt("item_id");
            int dueYear = data.getExtras().getInt("item_dueYear");
            int dueMonth = data.getExtras().getInt("item_dueMonth");
            int dueDay = data.getExtras().getInt("item_dueDay");

            if(pos > -1) {
                updateItem(pos, id, text, dueDay, dueMonth, dueYear, priority);
            }
        }
    }

    protected  void updateItem(int position, int id, String text, int dueDay, int dueMonth, int dueYear, String priority) {
        Item item = new Item(id, text, dueDay, dueMonth, dueYear, priority);
        provider.updateItem(item);
        items.set(position,item);
        itemsAdapter.notifyDataSetChanged();
    }

    public void buttonClick(int position, int id, String text, int dueDay, int dueMonth, int dueYear, String priority) {
        updateItem(position, id, text, dueDay, dueMonth, dueYear, priority);
        Toast.makeText(this, "Item saved successfully", Toast.LENGTH_SHORT).show();
    }

    private void showEditDialog(int position, int id, String text, int dueDay, int dueMonth, int dueYear, String priority) {
        FragmentManager fm = getSupportFragmentManager();
        EditItemDialogFragment editNameDialogFragment = EditItemDialogFragment.newInstance(position, id, text, dueDay, dueMonth, dueYear, priority);
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

}
