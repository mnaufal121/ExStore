package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.homepage.adapter.AlertDialogManager;
import com.example.homepage.session.SessionManager;

public class MenuDashboard extends AppCompatActivity {

    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dashboard);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        ImageButton profilebtn = (ImageButton) findViewById(R.id.profilebtn);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuDashboard.this, MenuProfil.class);
                startActivity(i);
            }
        });

        ImageButton orderbtn = (ImageButton) findViewById(R.id.orderbtn);
        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuDashboard.this, MenuOrder.class);
                startActivity(i);
            }
        });

        ImageButton settingbtn = (ImageButton) findViewById(R.id.settingbtn);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuDashboard.this, MenuSetting.class);
                startActivity(i);
            }
        });

        ImageButton aboutbtn = (ImageButton) findViewById(R.id.aboutbtn);
        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuDashboard.this, aboutapp.class);
                startActivity(i);
            }
        });
    }

    public void btn_whislist(View view) {
        Toast.makeText(getApplicationContext(), "Mohon maaf, fitur ini masih hanya hiasan. Terima Kasih:v.", Toast.LENGTH_LONG).show();
    }
}
