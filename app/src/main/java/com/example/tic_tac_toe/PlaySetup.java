package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlaySetup extends AppCompatActivity {
private EditText Player1;
private EditText Player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_setup);
        Player1= findViewById(R.id.editTextTextPersonName);
        Player2= findViewById(R.id.editTextTextPersonName2);
    }

    public void submitNames(View view){
      String Player1Name= Player1.getText().toString();
      String Player2Name= Player2.getText().toString();
        Intent intent= new Intent(this, play_game.class);
        intent.putExtra("Player_Names", new String[]{Player1Name, Player2Name});
        startActivity(intent);
    }

}