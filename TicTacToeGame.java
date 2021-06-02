package com.example.projectmenu;

// Imports
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToeGame extends AppCompatActivity implements View.OnClickListener {

    public static String Player1Victorious = "com.example.projectmenu.Player1Victory";


    // The double sized array contains buttons grid of 3 x and 3 y dimensions
    private Button[][] buttons = new Button[3][3];
    // Player 1 turn indicator
    private boolean player1Turn = true;
    // Round count indicator
    private int roundCount;
    // points variables which will hold the Text view score Playerx values
    private int player1Points;
    private int player2Points;
    // as mentioned above
    private TextView scorePlayer1;
    private TextView scorePlayer2;
    // text views which will hold EXTRA_TEXT values (text)
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    // variable will hold WinNumber value parsed from EnterNames activity
    private int RoundsToWin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_game);
        // Get values from EnterNames activity
        Intent intent = getIntent();
        // text string will store value parsed from EnterNames activity
        String text = intent.getStringExtra(EnterNames.EXTRA_TEXT1);
        String text2 = intent.getStringExtra(EnterNames.EXTRA_TEXT2);
        // A value parsed from EnterNames activity translated into string
        int WinNumber = intent.getIntExtra(EnterNames.EXTRA_WIN,0);
        // An integer value serve as check for player win condition
        RoundsToWin = (WinNumber);

        // First assign variable to it's corresponding view
        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);
        // Then set value to this variable parsed from previous activity and text colour
        textViewPlayer1.setText(text);
        textViewPlayer1.setTextColor(Color.rgb(255,153,51));
        textViewPlayer2.setText(text2);
        textViewPlayer2.setTextColor(Color.rgb(255,51,153));

        //
        scorePlayer1 = findViewById(R.id.score_player1);
        scorePlayer2 = findViewById(R.id.score_player2);

        // Reset button will trigger the resetGame method on click
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        // Main menu button will trigger the MainActivity() method
        Button buttonMenu = findViewById(R.id.button_menu);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity();
            }
        });

        // Buttons for the grid 3x3
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

    }

    // The button handler
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        // Will create a sounds, play the sound, will change the colour of button and set X text
        if (player1Turn) {
            final MediaPlayer crossSound = MediaPlayer.create(this, R.raw.jingles05);
            crossSound.start();
            ((Button) v).setTextColor(Color.rgb(255,153,51));
            ((Button) v).setText("X");
        } else { // Same as above only with O text
            final MediaPlayer OSound = MediaPlayer.create(this,R.raw.jingles04);
            OSound.start();
            ((Button) v).setTextColor(Color.rgb(255,51,153));
            ((Button) v).setText("O");
        }
        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    /*

    Following is list of checks

    this is to check values in the double sized array

    buttons layout:
        00 01 02
        01 11 12
        20 21 22

    */


    private boolean checkForWin() {
        String[][] field = new String[3][3];
        // buttons 2 size array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        // checks for middle line bottom to top
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }
        //checks for middle line top to bottom
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][1]) && !field[0][i].equals("")) {
                return true;
            }
        }
        // checks for diagonal from top left to bottom right
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }
        // checks for diagonal from top right to bottom left
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }
        // checks for line top left to bottom left
        if (field[0][0].equals(field[1][0])&& field[0][0].equals(field[2][0]) && !field[0][0].equals("")) {
            return true;
        }
        // check for line top right to bottom right
        if (field[0][2].equals(field[1][2])&& field[0][2].equals(field[2][2]) && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    public void player1Wins() {
        player1Points++;
        updatePointsText();
        resetBoard();

        if(player1Points == RoundsToWin){
            final MediaPlayer Wvictory = MediaPlayer.create(this,R.raw.jingles07);
            Wvictory.start();
            Victorious();
        }else{
            final MediaPlayer p1w = MediaPlayer.create(this,R.raw.jingles10);
            p1w.start();
            Toast.makeText(this, textViewPlayer1.getText().toString()+" " +"Wins!", Toast.LENGTH_SHORT).show();
        }

    }

    public void player2Wins() {
        player2Points++;
        updatePointsText();
        resetBoard();

        if(player2Points == RoundsToWin){
            final MediaPlayer Wvictory  = MediaPlayer.create(this,R.raw.jingles07);
            Wvictory.start();
            Victorious();
        } else {
            final MediaPlayer p2w = MediaPlayer.create(this, R.raw.jingles10);
            p2w.start();
            Toast.makeText(this, textViewPlayer2.getText().toString() + " " + "Wins!", Toast.LENGTH_SHORT).show();
        }
    }

    private void draw() {
        final MediaPlayer drawsound = MediaPlayer.create(this,R.raw.jingles12);
        drawsound.start();
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {

        scorePlayer1.setText("Points: " + player1Points);
        scorePlayer2.setText("Points: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");

            }
        }

        roundCount = 0;
        player1Turn = true;

    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");

    }

    public void MainActivity()
    {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    public void Victorious()
    {

        if (player1Points == RoundsToWin)
        {
            //Toast.makeText(this, textViewPlayer1.getText().toString()+" " +"Wins!", Toast.LENGTH_SHORT).show();
           Player1Victorious = textViewPlayer1.getText().toString();

        } else {
            //Toast.makeText(this, textViewPlayer2.getText().toString()+" " +"Wins!", Toast.LENGTH_SHORT).show();

            Player1Victorious = textViewPlayer2.getText().toString();
        }

        /* final EditText editText1 = findViewById(R.id.editName1);
        String text = editText1.getText().toString();*/

        String VictoriousPlayer = Player1Victorious;

        /*final EditText editText2 = findViewById(R.id.editName2);
        String text2 = editText2.getText().toString();*/
        ;
        // reset the values for sanity check
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
        // start GameResults activity

        Intent intent = new Intent (this, GameResult.class);
        intent.putExtra(Player1Victorious, VictoriousPlayer);
        startActivity(intent);


        // Write a method to 1) remember Victorious player name and points
        // Write a method which will take this values to the next screen where will be some animation
    }
}