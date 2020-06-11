package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homepage.database.DataHelper;
import com.example.homepage.session.SessionManager;

import java.util.HashMap;

public class MenuProfil extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    SQLiteDatabase db;
    SessionManager session;
    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profil);

        dbHelper = new DataHelper(this);

        session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getUserDetails();
        email = user.get(SessionManager.KEY_EMAIL);

        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM TB_USER WHERE username = '" + email + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            name = cursor.getString(2);
        }

        TextView lblName = findViewById(R.id.nama);
        TextView lblEmail = findViewById(R.id.email);

        lblName.setText(name);
        lblEmail.setText(email);

        ImageView settingimg = (ImageView) findViewById(R.id.setting);
        settingimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuProfil.this, MenuSetting.class);
                startActivity(i);
            }
        });

    }
}
