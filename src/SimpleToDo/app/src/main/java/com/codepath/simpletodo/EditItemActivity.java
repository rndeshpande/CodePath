package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    EditText etEditItem;
    Integer pos;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        etEditItem = (EditText) findViewById(R.id.etEditItem);
        String text = getIntent().getStringExtra("item_text");
        pos = getIntent().getIntExtra("item_pos",-1);
        id = getIntent().getIntExtra("item_id",-1);
        etEditItem.setText(text);
    }

    public  void onSubmit(View v) {
        Intent data = new Intent();

        data.putExtra("item_text",etEditItem.getText().toString());
        data.putExtra("item_id",id);
        data.putExtra("item_pos",pos);
        setResult(RESULT_OK, data);

        finish();
    }
}
