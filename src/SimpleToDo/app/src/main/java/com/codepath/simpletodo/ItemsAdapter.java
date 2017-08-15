package com.codepath.simpletodo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rdeshpan on 8/13/2017.
 */

public class ItemsAdapter extends ArrayAdapter<Item> {
    public ItemsAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvText = (TextView) convertView.findViewById(R.id.tvText);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);

        // Populate the data into the template view using the data object
        tvText.setText(item.getText());
        tvPriority.setText(item.getPriority());
        String dateFormatted = "";

        if(item.getDueDay() > 0)
            dateFormatted = item.getDueMonth() + "/" + item.getDueDay() + "/" + item.getDueYear();

        tvDate.setText(dateFormatted);
        // Return the completed view to render on screen
        return convertView;
    }

}
