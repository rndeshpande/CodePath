package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class EditItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText etEditItem;
    DatePicker dpDate;
    Spinner spPriority;
    Integer pos;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        String text = getIntent().getStringExtra("item_text");
        pos = getIntent().getIntExtra("item_pos",-1);
        id = getIntent().getIntExtra("item_id",-1);
        int dueDay = getIntent().getIntExtra("item_dueDay",0);
        int dueMonth = getIntent().getIntExtra("item_dueMonth",0);
        int dueYear = getIntent().getIntExtra("item_dueYear",0);
        String priority = getIntent().getStringExtra("item_priority");

        etEditItem = (EditText) findViewById(R.id.etEditItem);
        etEditItem.setText(text);

        dpDate = (DatePicker) findViewById(R.id.dpDate);
        dpDate.updateDate(dueYear, dueMonth -1, dueDay);

        spPriority = (Spinner) findViewById(R.id.spPriority);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPriority.setAdapter(adapter);
        spPriority.setSelection(adapter.getPosition(priority));

        spPriority.setOnItemSelectedListener(this);
    }

    public  void onSubmit(View v) {
        Intent data = new Intent();

        data.putExtra("item_text",etEditItem.getText().toString());
        data.putExtra("item_id",id);
        data.putExtra("item_pos",pos);
        data.putExtra("item_dueDay", (dpDate.getDayOfMonth()));
        data.putExtra("item_dueMonth", (dpDate.getMonth() + 1));
        data.putExtra("item_dueYear", (dpDate.getYear()));
        data.putExtra("item_priority", spPriority.getSelectedItem().toString());
        setResult(RESULT_OK, data);

        finish();
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}
