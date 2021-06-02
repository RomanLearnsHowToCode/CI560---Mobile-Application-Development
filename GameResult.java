package com.example.projectmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameResult extends AppCompatActivity {


    private TextView TextView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        Intent intent = getIntent();
        // text string will store value parsed from EnterNames activity
        String text = intent.getStringExtra(TicTacToeGame.Player1Victorious);

        TextView1 = findViewById(R.id.textViewV);
        // Then set value to this variable parsed from previous activity and text colour
        TextView1.setText(text);
        TextView1.setTextColor(Color.rgb(255,153,51));

        final MediaPlayer confetti = MediaPlayer.create(this,R.raw.confetti);
        confetti.start();

        // Reset button will trigger the resetGame method on click
        Button buttonMenu = findViewById(R.id.button_menu);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainMenu();
            }
        });

    }
    public void MainMenu()
    {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
}
