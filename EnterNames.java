package com.example.projectmenu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterNames extends AppCompatActivity {

    public static final String EXTRA_TEXT1 = "com.example.projectmenu.EXTRA_TEXT";
    public static final String EXTRA_TEXT2 = "com.example.projectmenu.EXTRA_TEXT2";
    public static final String EXTRA_WIN = "com.example.projectmenu.EXTRA_WIN";



    private Button StartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_names);


        final EditText editText1 = findViewById(R.id.editName1);
        final EditText editText2 = findViewById(R.id.editName2);
        final EditText Win = findViewById(R.id.Win);



        StartGame = findViewById(R.id.StartGame);
        StartGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (editText1.length()==0)
                {
                    editText1.setError("Enter Name!");
                }else if (editText2.length()==0)
                {
                    editText2.setError("Enter Name!");
                }else if (Win.length() == 0){
                    Toast.makeText(EnterNames.this,"Chose valid", Toast.LENGTH_SHORT).show();
                }
                else{
                    startTicTacToe();
                }
            }
        });

    }

    public void startTicTacToe()
    {
        final EditText editText1 = findViewById(R.id.editName1);
        String text = editText1.getText().toString();

        final EditText editText2 = findViewById(R.id.editName2);
        String text2 = editText2.getText().toString();

        final EditText Win = findViewById(R.id.Win);
        int WinPoints = Integer.parseInt(Win.getText().toString());


        Intent intent = new Intent (this, TicTacToeGame.class);
        intent.putExtra(EXTRA_TEXT1, text);
        intent.putExtra(EXTRA_TEXT2, text2);
        intent.putExtra(EXTRA_WIN, WinPoints);

        startActivity(intent);
    }

}
