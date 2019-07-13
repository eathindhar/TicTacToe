package com.eathindhar.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: Yellow    1: Red  2: empty
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},
                                {0,3,6},{1,4,7},{2,5,8},
                                {0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void dropIn(View view){
        ImageView counter = (ImageView)view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive){
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if(activePlayer==0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer=1;
            }else{
                counter.setImageResource(R.drawable.red);
                activePlayer=0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(500);
            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]]!=2){
                    //Someone has won!
                    gameActive=false;
                    String winner;
                    if(activePlayer == 1){
                        winner = "Yellow";
                    }else{
                        winner = "Red";
                    }
                    //Toast.makeText(this, winner+" has one!", Toast.LENGTH_SHORT).show();

                    Button restartButton = (Button) findViewById(R.id.restartButton);

                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

                    winnerText.setText(winner+" has Won!");
                    restartButton.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);
                }

            }
        }
    }
    public void playAgain(View view){
        Button restartButton = (Button) findViewById(R.id.restartButton);

        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

        restartButton.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout myGridView = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<myGridView.getChildCount(); i++) {
            ImageView counter = (ImageView)myGridView.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        activePlayer = 0;

        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
