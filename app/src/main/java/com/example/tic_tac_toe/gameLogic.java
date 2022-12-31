package com.example.tic_tac_toe;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gameLogic {
    private int[][] gameBoard;
    private Button plaAgainBtn;
    private Button homeBtn;
    private TextView playerTurn;
    private String[] playersName={"Player 1", "Player 2"};
    private int player=1;
    // {row, col, line type}
    private int[] winType= {-1, -1 ,-1};

    gameLogic(){
        gameBoard= new int[3][3];
       for(int r=0; r<3; r++){
          for(int c = 0; c<3; c++){
            gameBoard[r][c]= 0;       //The user can place values only where there are no pre-existing values
          }
       }
    }

    public boolean updateGameBoard(int row, int col){
        if(gameBoard[row-1][col-1]==0){
            gameBoard[row-1][col-1]=player;
            if(player==1){
                playerTurn.setText(playersName[1] + "'s Turn");
            }else{
                playerTurn.setText(playersName[0] + "'s Turn");
            }
            return true;
        }else{
            return false;
        }

    }


    public boolean winnerCheck() {
        boolean isWinner = false;
        for (int r = 0; r < 3; r++) {
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] && gameBoard[r][0] != 0) {
               winType=new int[]{r, 0 , 1};       //Horizontal Win Check (type=1)
                isWinner = true;

            }
        }
        for (int c = 0; c < 3; c++) {
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[0][c] == gameBoard[2][c] && gameBoard[0][c] != 0) {
                winType=new int[]{0, c, 2};   //Vertical Win Check (type=2)
                isWinner = true;
            }
        }

        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] != 0) {
           winType= new int[]{0 , 2 ,3};       //Negative Diagonal Win check (type=3)

            isWinner = true;
        }
        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2] && gameBoard[2][0] != 0) {
            winType= new int[]{2 , 2 ,4};        //Positive Diagonal Win Check(type=4)
            isWinner = true;
        }

       int boardFilled=0;      //FOR TIE-SITUATION
      for(int r=0; r<3; r++){
            for(int c=0; c<3; c++) {
                if (gameBoard[r][c] != 0) {
                    boardFilled +=1;

                }
           }

        }
        if(isWinner){
            plaAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText(playersName[player-1] + " Won!!!!");
            return true;
        }
       else if (boardFilled==9) {
            plaAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText("TIE GAME!!!!!");
            return false;
        }
         else{
             return false;
        }
    }


    public void resetGame() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                gameBoard[r][c] = 0;
            }
        }
        player=1;
        plaAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);

        playerTurn.setText(playersName[0] + "'s Turn");
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setPlaAgainBtn(Button plaAgainBtn) {
        this.plaAgainBtn = plaAgainBtn;
    }

    public void setHomeBtn(Button homeBtn) {
        this.homeBtn = homeBtn;
    }

    public void setPlayersName(String[] playersName) {
        this.playersName = playersName;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public int[] getWinType() {
        return winType;
    }
}
