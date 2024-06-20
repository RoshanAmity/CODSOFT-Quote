package com.example.quote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {

    public static final String Table = "quotetable";
    public static final String ID = "id";
    public static final String Quote = "quote";

    private static final String DATABASE_NAME = "quotes.db";
    private static final int DATABASE_VERSION = 1;

    public SQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+ Table + "( "+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Quote + " TEXT )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean Enterly(QuotesData quotesAdapter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Quote, quotesAdapter.getQuote());
        long insert = db.insert(Table, null, values);

        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public List<QuotesData> getEveryOne(){
        List<QuotesData> resultList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String retrieve = "SELECT * FROM " + Table;
        Cursor cursor = db.rawQuery(retrieve, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String quote = cursor.getString(1);

                QuotesData quotesAdapter = new QuotesData(id, quote);
                resultList.add(quotesAdapter);
            }while(cursor.moveToNext());
        }
        else{}
        cursor.close();
        db.close();
        return resultList;
    }

    public void delete(QuotesData quotesAdapter , int id){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + Table + " WHERE " + ID + " = "+ id;
        db.execSQL(query);
    }

}
