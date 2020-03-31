package com.example.numerostelefonicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_name, et_number;
    Button clearNameBtn, clearNumberBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_contactName);
        et_number = findViewById(R.id.et_cellNumber);

        clearNameBtn = findViewById(R.id.clearNameBtn);
        clearNumberBtn = findViewById(R.id.clearNumberBtn);

        clearNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_number.setText("");
            }
        });

        clearNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_name.setText("");
            }
        });

    }

    public void setNumber(View view)
    {
        DatabaseManagement databaseManagement = new DatabaseManagement(this, "info", null, 1);
        SQLiteDatabase database = databaseManagement.getWritableDatabase();

        String contactNumber = et_number.getText().toString(); // number
        String contactName = et_name.getText().toString(); // name

        if(!contactName.isEmpty()&& !contactNumber.isEmpty())
        {
            ContentValues values = new ContentValues();

            values.put("number", contactNumber);
            values.put("name", contactName);

            database.insert("contacts", null, values);

            database.close();

            et_name.setText("");
            et_number.setText("");

            Toast.makeText(this, "Number added successfully", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }

    }

    public void searchNumber(View view)
    {
        DatabaseManagement databaseManagement = new DatabaseManagement(this, "info", null, 1 );
        SQLiteDatabase database = databaseManagement.getWritableDatabase();
        
        String contactName = et_name.getText().toString();
        String contactNumber = et_number.getText().toString();

        if(!contactName.isEmpty() || !contactNumber.isEmpty()){

            if(!contactName.isEmpty()){
                Cursor cursor = database.rawQuery("select number from contacts where name = " + '"' + contactName + '"' , null);
                if(cursor.moveToFirst()){
                    et_number.setText(cursor.getString(0));
                    Toast.makeText(this, "Positive for number", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "No existe un numero registrado con este nombre", Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }else if(!contactNumber.isEmpty()){
                Cursor cursor = database.rawQuery("select name from contacts where number = " + contactNumber, null);
                if(cursor.moveToFirst()){
                    et_name.setText(cursor.getString(0));
                    Toast.makeText(this, "Positive for name", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "No existe un nombre registrado con este numero", Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }
        }
        else
        {
            Toast.makeText(this, "Tienes que rellenar al menos un campo", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateContact(View view)
    {

        DatabaseManagement databaseManagement = new DatabaseManagement(this, "info", null, 1);
        SQLiteDatabase database = databaseManagement.getWritableDatabase();

    }

    public void deleteContact(View view)
    {
        DatabaseManagement databaseManagement = new DatabaseManagement(this, "info", null, 1);
        SQLiteDatabase database = databaseManagement.getWritableDatabase();

        String contactName = et_name.getText().toString();
        String contactNumber = et_number.getText().toString();

        if(!contactName.isEmpty() && !contactNumber.isEmpty()){

            int deleteAction = database.delete("contacts", "number = " + contactNumber, null);

            if(deleteAction == 1){

                Toast.makeText(this, "Has eliminado a " + contactName + " de tu lista", Toast.LENGTH_LONG).show();

            }else{

                Toast.makeText(this, "Estas intentando eliminar un contacto inexistente", Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(this, "Asegurate que los campos esten llenos", Toast.LENGTH_SHORT).show();
        }

    }

}
