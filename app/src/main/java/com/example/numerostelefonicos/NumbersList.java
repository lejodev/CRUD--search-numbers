package com.example.numerostelefonicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NumbersList extends AppCompatActivity {

    EditText et_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_list);

        ListView listView = findViewById(R.id.lv_contactsList);
        ArrayList<Contact> contactsItems = getItems();

        CustomRow customRow = new CustomRow(this, contactsItems);
        listView.setAdapter(customRow);


/*
        DatabaseManagement databaseManagement = new DatabaseManagement(this, "info", null, 1);
        SQLiteDatabase database = databaseManagement.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from contacts ", null);

        if (cursor.moveToFirst()) {
            int dbLength = cursor.getCount();
            String str = "";
            for (int i = 0; i < dbLength; i++) {
                contactsItems.add(cursor.getString(1) + ": " + cursor.getString(0));
                cursor.moveToNext();
            }

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsList);
            listView.setAdapter(adapter);

        }
        else {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }

 */
    }

    // Class to create a list and populate it with the database info

    private ArrayList<Contact> getItems(){

        DatabaseManagement databaseManagement = new DatabaseManagement(this, "info", null, 1);
        SQLiteDatabase database = databaseManagement.getWritableDatabase();

        ArrayList<Contact> items = new ArrayList<>();

        Cursor cursor = database.rawQuery("select * from contacts ", null);

        if(cursor.moveToFirst()){

            int dbLength = cursor.getCount();

            for(int i = 0; i < dbLength; i++){

                items.add(new Contact(cursor.getString(1), cursor.getString(0)));

                cursor.moveToNext();

            }
        }

        return items;

    }

}
