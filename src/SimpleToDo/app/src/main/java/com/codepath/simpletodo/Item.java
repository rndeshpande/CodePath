package com.codepath.simpletodo;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by rdeshpan on 8/13/2017.
 */

@Table(database = MyDatabase.class)
public class Item extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String text;

    @Column
    Integer dueMonth;

    @Column
    Integer dueDay;

    @Column
    Integer dueYear;

    @Column
    String priority;

    public  Item() {
        Calendar calendar =  Calendar.getInstance();
        this.dueYear = calendar.get(Calendar.YEAR);
        this.dueMonth = calendar.get(Calendar.MONTH) + 1;
        this.dueDay = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public  Item(int id, String text, int dueDay, int dueMonth, int dueYear, String priority) {
        this.id = id;
        this.text = text;
        this.dueDay = dueDay;
        this.dueMonth = dueMonth;
        this.dueYear = dueYear;
        this.priority = priority;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDueDay (int dueDay) {
        this.dueDay = dueDay;
    }
    public void setDueMonth (int dueMonth) {
        this.dueMonth = dueMonth;
    }
    public void setDueYear (int dueYear) {
        this.dueYear = dueYear;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getText() {
        return this.text;
    }

    public Integer getId() {
        return this.id;
    }

    public int getDueDay () {
        return this.dueDay;
    }

    public int getDueMonth () {
        return this.dueMonth;
    }

    public int getDueYear () {
        return this.dueYear;
    }

    public String getPriority() {
        return this.priority;
    }
}
