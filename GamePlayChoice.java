package com.example.projectmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GamePlayChoice extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_choice);


        // Reset button will trigger the resetGame method on click
        Button buttonPvP = findViewById(R.id.buttonPVP);
        buttonPvP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicTacToeGame();
            }
        });



        // Reset button will trigger the resetGame method on click
        Button buttonPvAI = findViewById(R.id.buttonAI);
        buttonPvAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicTacToeAi();
            }
        });
    }


    public void TicTacToeGame()
    {
        Intent intent = new Intent (this, EnterNames.class);
        startActivity(intent);
    }

    public void TicTacToeAi()
    {
        Intent intent = new Intent (this, TicTacToeAi.class);
        startActivity(intent);
    }
}
