package com.example.projectmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    // If called open portfolio website
    public void openPortfolio(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://rl445.brighton.domains"));
        startActivity(browserIntent);
    }

}
