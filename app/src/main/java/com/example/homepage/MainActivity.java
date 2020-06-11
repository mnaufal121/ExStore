package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homepage.adapter.AlertDialogManager;
import com.example.homepage.database.DataHelper;
import com.example.homepage.session.SessionManager;

public class MainActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword;
    Button btnLogin;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    DataHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        dbHelper = new DataHelper(this);
        db = dbHelper.getWritableDatabase();

        session = new SessionManager(getApplicationContext());

        txtUsername = findViewById(R.id.email);
        txtPassword = findViewById(R.id.pass);

        btnLogin = findViewById(R.id.signin);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get username, password from EditText
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                // Check if username, password is filled
                try {
                    // Check if username, password is filled
                    if (username.trim().length() > 0 && password.trim().length() > 0) {
                        dbHelper.open();

                        if (dbHelper.Login(username, password)) {
                            session.createLoginSession(username);

                            finish();
                            Intent i = new Intent(getApplicationContext(), MenuDashboard.class);
                            startActivity(i);

                        } else {
                            alert.showAlertDialog(MainActivity.this, "Login gagal..", "Email atau Password salah!", false);

                        }
                    } else {
                        alert.showAlertDialog(MainActivity.this, "Login gagal..", "Form tidak boleh kosong!", false);
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        TextView daftar = (TextView)findViewById(R.id.register);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuRegister.class);
                startActivity(i);
            }
        });

        TextView forgot = (TextView)findViewById(R.id.forgotpassword);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuReset.class);
                startActivity(i);
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
