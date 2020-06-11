package com.example.homepage;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuBarang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_barang);
    }

    public void btn_chat(View view) {
        Toast.makeText(getApplicationContext(), "Mohon maaf, fitur ini masih hanya hiasan. Terima Kasih:v.", Toast.LENGTH_LONG).show();
    }

    public void btn_beli(View view) {
        Toast.makeText(getApplicationContext(), "Mohon maaf, fitur ini masih hanya hiasan. Terima Kasih:v.", Toast.LENGTH_LONG).show();
    }
}
