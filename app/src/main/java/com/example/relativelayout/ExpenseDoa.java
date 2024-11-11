package com.example.relativelayout;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDoa {
    @Query("select * from Expenses")
    List<Expenses> getAllExpense();

    @Insert
    void addTransaction(Expenses expenses);

    @Update
    void updateTransactions(Expenses expenses);
}
