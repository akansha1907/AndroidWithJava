package com.example.relativelayout;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Expenses")
public class Expenses {
    @PrimaryKey (autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @ColumnInfo(name="Title")
    private String title;

    @ColumnInfo(name="Amount")
    private String amount;

    Expenses(int id, String title, String amount) {
        this.id = id;
        this.title = title;
        this.amount = amount;
    }

    //this annotation is define that this constructor is secondary constructor not main
    //without this annotation ww will get error of duplicate constructors
    @Ignore
    Expenses(String title, String amount) {
        this.title = title;
        this.amount = amount;
    }

}
