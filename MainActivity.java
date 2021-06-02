package com.example.projectmenu;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton TicTacToeButton;
    ImageButton AboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// ctrl + b

        AboutButton = findViewById(R.id.About);
        final MediaPlayer AboutSound = MediaPlayer.create(this, R.raw.jingles00);
        AboutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                AboutSound.start();
                startAbout();
        }
        });

        TicTacToeButton = findViewById(R.id.TicTacToe);
        final MediaPlayer TicTacToeSound = MediaPlayer.create(this,R.raw.jingles00);
        TicTacToeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                TicTacToeSound.start();
                startTicTacToe();
            }
        });

    }

    public void startTicTacToe()

    {
        Intent intent = new Intent (this, GamePlayChoice.class);
        startActivity(intent);
    }

    public void startAbout()
    {
        Intent intent = new Intent (this, About.class);
        startActivity(intent);
    }

}
