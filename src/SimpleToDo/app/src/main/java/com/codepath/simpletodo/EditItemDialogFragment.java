package com.codepath.simpletodo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by rdeshpan on 8/15/2017.
 */

public class EditItemDialogFragment extends DialogFragment {
    EditText etEditItem;
    DatePicker dpDate;
    Spinner spPriority;
    Integer position;
    Integer id;
    String priority;
    int dueDay;
    int dueMonth;
    int dueYear;
    String text;

    public EditItemDialogFragment() {

    }

    public static EditItemDialogFragment newInstance(int position, int id, String text, int dueDay, int dueMonth, int dueYear, String priority) {
        EditItemDialogFragment frag = new EditItemDialogFragment();
        Bundle args = new Bundle();

        args.putInt("position", position);
        args.putInt("id", id);
        args.putString("text", text);
        args.putInt("dueDay", dueDay);
        args.putInt("dueMonth", dueMonth);
        args.putInt("dueYear", dueYear);
        args.putString("priority", priority);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_edit_item, container);

        Button button = (Button)v.findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                text = etEditItem.getText().toString();
                dueDay = dpDate.getDayOfMonth();
                dueMonth = dpDate.getMonth() + 1;
                dueYear = dpDate.getYear();
                priority = spPriority.getSelectedItem().toString();

                ((MainActivity)getActivity()).buttonClick(position, id, text, dueDay, dueMonth, dueYear, priority);
                dismiss();
            }
        });

        return  v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.position = getArguments().getInt("position", -1);
        this.id = getArguments().getInt("id", -1);
        this.text = getArguments().getString("text");
        this.dueDay = getArguments().getInt("dueDay", 0);
        this.dueMonth = getArguments().getInt("dueMonth", 0);
        this.dueYear = getArguments().getInt("dueYear", 0);
        this.priority = getArguments().getString("priority");

        etEditItem = (EditText)view.findViewById(R.id.etEditItem);
        etEditItem.setText(text);

        dpDate = (DatePicker) view.findViewById(R.id.dpDate);
        dpDate.updateDate(dueYear, dueMonth -1, dueDay);

        spPriority = (Spinner) view.findViewById(R.id.spPriority);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(((MainActivity)getActivity()).getBaseContext(),
                R.array.priority_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPriority.setAdapter(adapter);
        spPriority.setSelection(adapter.getPosition(priority));

    }
}
