package com.example.ptquynh_giuaki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.email);
        ed3 = findViewById(R.id.phone);
        ed4 = findViewById(R.id.address);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),view.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    public void insert()
    {
        try
        {
            String name = ed1.getText().toString();
            String email = ed2.getText().toString();
            String phone = ed3.getText().toString();
            String address = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SQliteDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,email VARCHAR,phone VARCHAR,address VARCHAR)");

            String sql = "insert into records(name,email,phone,address)values(?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,email);
            statement.bindString(3,phone);
            statement.bindString(4,address);
            statement.execute();
            Toast.makeText(this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Không thể lưu",Toast.LENGTH_LONG).show();
        }
    }
}