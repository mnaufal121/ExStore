package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homepage.database.DataHelper;

public class MenuRegister extends AppCompatActivity {

    EditText txtName, txtUsername, txtPassword;
    Button btnDaftar;
    DataHelper dbHelper;
    SQLiteDatabase db;
    String name, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_register);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        dbHelper = new DataHelper(this);
        db = dbHelper.getWritableDatabase();

        txtName = findViewById(R.id.nama);
        txtUsername = findViewById(R.id.email);
        txtPassword = findViewById(R.id.pass);

        btnDaftar = findViewById(R.id.daftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txtName.getText().toString();
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                try {
                    if (username.trim().length() > 0 && password.trim().length() > 0 && name.trim().length() > 0) {
                        dbHelper.open();
                        dbHelper.Register(username, password, name);
                        Toast.makeText(MenuRegister.this, "Daftar berhasil", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(MenuRegister.this, "Daftar gagal, lengkapi form!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MenuRegister.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    }


