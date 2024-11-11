package com.example.relativelayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MySQLDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "ContactsDB";//it is name of database recommended to make it final so that it can't be change by anyone
    //database name is static because when we make object of MySQLDatabase class it's constructor will call first
    //and it will even before line no. 10 and here we get error it will not find DATABASE_NAME
    //variable so if we make it static it will get memory while creating variable

    private static final int DATABASE_VERSION = 1;
    private static final  String TABLE_CONTACT= "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    private static final String KEY_PHONE_NO = "phone_no";

    /**
     * @Nullable SQLiteDatabase.CursorFactory factory cursor factory required for stub cursor or for making select query or fetching data
     * cursor helps to traverse rows in table, it helps to read data row by row in table
     */


    public MySQLDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //onCreate method called when database is create
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACT + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME+" TEXT,"+ KEY_PHONE_NO +" TEXT"+")" );

        //AUTOINCREMENT means whenever add new row it's id will be autoincrement the value of id by 1 from previous
        //query will look like this after executing
        //CREATE TABLE contacts ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_no TEXT)

        //how to open sqlite database from other class and if required query has been executed it is important to close database
        /***
         *   SQLiteDatabase database = this.getReadableDatabase();
         *         database.delete() or any other query
         *         database.close();
          */


    }

    //this method will be call when we update sny values in db
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACT);
        onCreate(sqLiteDatabase);
    }
    public void addNewDataToDb(String name,String phone_no){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_PHONE_NO,phone_no);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CONTACT,null,values);          //nullColumnHack is used to insert null value in db
    }

    //this method will be call to fetch data from db
    public ArrayList<ContactModelForDb> fetchDataFromDb(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ContactModelForDb> contactModelForDbs = new ArrayList<>();
        Cursor cursor
         = db.rawQuery("SELECT * FROM "+TABLE_CONTACT,null);//by writing * all data from all columns will come
        //selectionArgs here null because we are not fetching data from db conditionally instead fetching all the data
        while(cursor.moveToNext()){     //will traverse till last row of table
            ContactModelForDb contactModel = new ContactModelForDb();
            contactModel.id = cursor.getInt(0);
            contactModel.name = cursor.getString(1);
            contactModel.phone_num = cursor.getString(2);
            contactModelForDbs.add(contactModel);
        }
        return  contactModelForDbs;
    }
    public void updateDbData(ContactModelForDb model){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_NAME,model.name);
    db.update(TABLE_CONTACT,values,KEY_ID +" = "+model.id,null);
    }
    public void deleteDataFromDb(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACT,KEY_NAME+ " = ? ",new String[]{String.valueOf(id)});//String.valueOf() is important
        //when data to be delete is not a string
    }
}
