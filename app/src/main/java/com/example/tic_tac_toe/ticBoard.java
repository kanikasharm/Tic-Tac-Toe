package com.example.tic_tac_toe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import androidx.annotation.Nullable;

public class ticBoard extends View {
    private final int boardColor;
    private final int oColor;
    private final int xColor;
    private final int winningLineColor;
    private final Paint paint = new Paint();
    private int cellSize = getWidth() / 3;
    private boolean winningLine= false;
    private final gameLogic game;


    public ticBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ticBoard, 0, 0);
        try {
            boardColor = a.getInteger(R.styleable.ticBoard_boardColor, 0);
            game = new gameLogic();
            oColor = a.getInteger(R.styleable.ticBoard_oColor, 0);
            xColor = a.getInteger(R.styleable.ticBoard_xColor, 0);
            winningLineColor = a.getInteger(R.styleable.ticBoard_winningLineColor, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(dimension, dimension);
        cellSize = dimension / 3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drawGameBoard(canvas);

        drawMarkers(canvas);
        if (winningLine) {
            paint.setColor(winningLineColor);
            drawWinningLine(canvas);

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float X = event.getX();
        float Y = event.getY();

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {         //to check if the player touches on the game board only
            int row = (int) Math.ceil(Y / cellSize);     //converting the user's touch to rows and cols in the tic tac toe board
            int col = (int) Math.ceil(X / cellSize);
            if (!winningLine) {
                if (game.updateGameBoard(row, col)) {
                    invalidate();
                    if (game.winnerCheck()) {
                        winningLine = true;
                        invalidate();
                    }


            //updating the player's turn

            if (game.getPlayer() % 2 == 0) {
                game.setPlayer(game.getPlayer() - 1);


            } else {
                game.setPlayer(game.getPlayer() + 1);
            }
        }
            }

                    invalidate();
                    return true;
                }
                return false;
            }

    public void drawGameBoard(Canvas canvas) {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for (int c = 1; c < 3; c++) {
            canvas.drawLine(cellSize * c, 0, cellSize * c, canvas.getWidth(), paint);
        }
        for (int r = 1; r < 3; r++) {
            canvas.drawLine(0, cellSize * r, canvas.getWidth(), cellSize * r, paint);
        }
    }
   private void drawWinningLine(Canvas canvas){
        int row= game.getWinType() [0];
       int col= game.getWinType() [1];

       switch(game.getWinType()[2]){
           case 1:
               drawHorizontalLine(canvas, row, col);
               break;
           case 2:
               drawVerticalLine(canvas, row, col);
               break;
           case 3:
               drawDiagonalneg(canvas);
               break;
           case 4:
               drawDiagonalPos(canvas);
               break;
       }
   }



    public void drawMarkers(Canvas canvas) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (game.getGameBoard()[r][c] != 0) {     //no empty space, update position with X and O
                    if (game.getGameBoard()[r][c] == 1) {
                        drawX(canvas, r, c);
                    } else {
                        drawY(canvas, r, c);
                    }
                }
            }
        }
    }

    public void resetGame() {
        game.resetGame();
        winningLine= false;
    }

    public void drawX(Canvas canvas, int row, int col) {
        paint.setColor(xColor);
        canvas.drawLine((float) ((col + 1) * cellSize - cellSize * 0.2), (float) (row * cellSize + cellSize * 0.2), (float) (col * cellSize + cellSize * 0.2), (float) ((row + 1) * cellSize - cellSize * 0.2), paint);

        canvas.drawLine((float) (col * cellSize + cellSize * 0.2), (float) ((row) * cellSize + cellSize * 0.2), (float) ((col + 1) * cellSize - cellSize * 0.2), (float) (
                (row + 1) * cellSize - cellSize * 0.2), paint);
    }

    public void drawY(Canvas canvas, int row, int col) {
        paint.setColor(oColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawOval((float) (col * cellSize + cellSize * 0.2), (float) (row * cellSize + cellSize * 0.2), (float) ((col * cellSize + cellSize) - cellSize * 0.2), (float) ((row * cellSize + cellSize) - cellSize * 0.2), paint);
        }
    }
        private void drawHorizontalLine(Canvas canvas, int row, int col){
            canvas.drawLine(col, row*cellSize+ (float)cellSize/2, cellSize*3, row*cellSize + (float)cellSize/2, paint);
        }
      private void drawVerticalLine(Canvas canvas, int row, int col){
        canvas.drawLine(col*cellSize+ (float)cellSize/2, row, col*cellSize+(float) cellSize/2, cellSize*3, paint);
      }
      private void drawDiagonalPos(Canvas canvas){
        canvas.drawLine(0, cellSize*3, cellSize*3, 0, paint);
      }
      private void drawDiagonalneg(Canvas canvas){
        canvas.drawLine(0,0, cellSize*3, cellSize*3, paint);
      }


    public void setUpGame(Button playAgain, Button home, TextView playerDisplay, String [] names ){
        game.setPlaAgainBtn(playAgain);
        game.setHomeBtn(home);
        game.setPlayerTurn(playerDisplay);
        if (!names[0].equals("") && !names[1].equals("")){
            game.setPlayersName(names);
        }


    }
}



