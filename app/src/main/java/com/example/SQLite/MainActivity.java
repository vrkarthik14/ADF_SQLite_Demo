package com.example.SQLite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText day,note,id;
    Button insert,retreve,del;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        day = findViewById(R.id.day);
        note = findViewById(R.id.note);
        insert = findViewById(R.id.insert);
        retreve = findViewById(R.id.retreve);
        id = findViewById(R.id.id);
        del = findViewById(R.id.del);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res = dbHelper.insert(day.getText().toString(),note.getText().toString());
                if (res ==true)
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data insertion falied.", Toast.LENGTH_SHORT).show();
            }
        });

        retreve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = dbHelper.getAllData();
                if(data.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No data found!!", Toast.LENGTH_SHORT).show();
                    showMessage("data","data not found!!!");
                }
                StringBuffer buffer = new StringBuffer();
                while (data.moveToNext()){
                    buffer.append("ID: "+data.getString(0)+"\n");
                    buffer.append("DAY: "+data.getString(1)+"\n");
                    buffer.append("NOTE: "+data.getString(2)+"\n");
                }
                showMessage("data: ",buffer.toString());
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res = dbHelper.delData(id.getText().toString());
                if (res == true) Toast.makeText(MainActivity.this, "data deleted!!", Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this, "No rows ERROR!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}
