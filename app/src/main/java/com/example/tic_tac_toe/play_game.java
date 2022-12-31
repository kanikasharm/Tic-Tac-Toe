package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class play_game extends AppCompatActivity {
private ticBoard TicBoard;
private Button playAgainBtn;
private Button homeBtn;
private TextView playerTurn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game);
        TicBoard= findViewById(R.id.ticBoard);
        playAgainBtn= findViewById(R.id.button10);
        playerTurn= findViewById(R.id.textView5);
        homeBtn= findViewById(R.id.button11);
        homeBtn.setVisibility(View.GONE);
        playAgainBtn.setVisibility(View.GONE);

        String[] playerName= getIntent().getStringArrayExtra("Player_Names");
        assert playerName != null;
        if (playerName[0].equals("")){

            playerTurn.setText("Player 1's turn");
        }else{
            playerTurn.setText((playerName[0] + "'s turn"));
        }
        TicBoard.setUpGame(playAgainBtn, homeBtn, playerTurn, playerName);
    }
    
    public void playAgainButton(View view){
        TicBoard.resetGame();
        TicBoard.invalidate();
    }
    
    public void homeButton(View view){
       Intent intent= new Intent(this, MainActivity.class);
       startActivity(intent);
    }
}