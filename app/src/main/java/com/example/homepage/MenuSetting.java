package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuSetting extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_setting);
        Button showBahasa = findViewById(R.id.bahasa);
        Button logout = (Button) findViewById(R.id.logout);

        showBahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Membuat Instance/Objek dari PopupMenu
                PopupMenu popupMenu = new PopupMenu(MenuSetting.this, view);
                popupMenu.setOnMenuItemClickListener(MenuSetting.this);
                popupMenu.inflate(R.menu.menu_bahasa);
                popupMenu.show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuSetting.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menu1:
                Toast.makeText(getApplicationContext(),"Anda Memilih Bahas Indonesia",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(getApplicationContext(),"You choose English",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
