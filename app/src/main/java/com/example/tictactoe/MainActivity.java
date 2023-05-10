package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;

    // Player representation
    // 0 means X
    // 1 means O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //State meaning
    //    0 means X
    //    1 means O
    //    2 means Null
    int [][] winPositions ={{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8},{0,4,8}, {2,4,6}};

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive) {
            gameReset(view);
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            //check if the player is X, then set the image as cross(close)
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.close);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("0's Turn - Please Tap.");
            }
            else
                {
                img.setImageResource(R.drawable.circle);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Please Tap.");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //winning player
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                //a player has won either X or O
                String winnerStr;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                }
                else
                    {
                    winnerStr = "O has won";
                }
                //update the status after a player has won
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                gameActive=false;
                break;
            }


        }
        boolean isDraw = true;
        //check if all the positions are filled up then return
        for (int state : gameState) {
            if (state == 2) {
                isDraw = false;
                break;
            }
        }
        //if the positions are filled up then the match is draw
        if(isDraw)
        {
            String drawstr="Match Tied!";
            TextView status=findViewById(R.id.status);
            status.setText(drawstr);
            gameActive=false;
        }

    }
    //Reset the game when completed once
        public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Please Tap");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}