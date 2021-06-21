package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 is yellow and 1 is red, 2 is for empty
    int activePlayer=0;
    boolean gameActive=true;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view) {
        ImageView counter= (ImageView)view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
       if (gameState[tappedCounter]==2 && gameActive) {
           gameState[tappedCounter] = activePlayer;
           counter.setTranslationY(-1500);
           if (activePlayer == 0) {
               counter.setImageResource(R.drawable.yellow);
               activePlayer = 1;
           } else {
               counter.setImageResource(R.drawable.red);
               activePlayer = 0;

           }

           counter.animate().translationYBy(1500).setDuration(300);
           for (int[] winningPosition : winningPositions) {
               if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                   String winner = "";
                   gameActive=false;
                   if (activePlayer == 1) {
                       winner = "Yello";
                   } else {
                       winner = "Red";
                   }
                   Button playButtonAgain=(Button)findViewById(R.id.playAgainButton);
                   TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);
                   winnerTextView.setText(winner + " has won");
                   playButtonAgain.setVisibility(View.VISIBLE);
                   winnerTextView.setVisibility(View.VISIBLE);
               }
           }
       }

    }
    public void playAgain(View view) {
        Button playButtonAgain = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playButtonAgain.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridView = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridView.getChildCount(); i++) {
            ImageView counter = (ImageView) gridView.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer = 0;
        gameActive = true;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}