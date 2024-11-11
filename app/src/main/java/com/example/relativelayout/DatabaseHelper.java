package com.example.relativelayout;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Expenses.class,exportSchema = false,version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    private static final String DB_NAME = "expensedb";
    private  static DatabaseHelper instance;

    //synchronized keyboard is used to ensure all operations to be done in synchronize way
    //if multiple people accessing same database in such case the person who open or accessed
    //database first allow to complete his task first it helps to remove ambiguity with db
   //Static method can be called without creating their object
    public static synchronized DatabaseHelper getDb(Context context){
        if(instance == null){       //if database is not yet created
            instance = Room.databaseBuilder(context,DatabaseHelper.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return  instance;

    }
    public abstract ExpenseDoa expenseDoa();
}
