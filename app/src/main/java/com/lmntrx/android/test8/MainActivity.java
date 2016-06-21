package com.lmntrx.android.test8;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    //X for 0 adn O for 1
    int activePlayer=0;

    //2=Unplayed,1=Player X,0=Player 0
    int [] gameState={2,2,2,2,2,2,2,2,2};

    //Array of arrays containing all possible winning positions
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    //Indicates whether game is over or not
    boolean gameIsOver=false;

    //OnClick functions of buttons
    public void dropIn(View view) {
        if (gameIsOver==false) {
            //Decalre a general imageView
            ImageView counter = (ImageView) view;
            //Get Tag from imageView
            int playerTag = Integer.parseInt(counter.getTag().toString());
            //Execute logic if imageView is empty
            if (gameState[playerTag] == 2) {
                gameState[playerTag] = activePlayer;
                //counter.setTranslationY(-1000f);
                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.x);
                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.o);
                    activePlayer = 0;
                }
               // counter.animate().translationYBy(1000f).rotation(360).setDuration(500);

                //Victory logic
                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                            gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                            gameState[winningPosition[0]] != 2)
                    {
                        String winnerMessage;
                        if (activePlayer == 1) {
                            winnerMessage = "X";
                        }
                        else
                        {
                            winnerMessage="O";
                        }
                        TextView winnerText = (TextView) findViewById(R.id.winnerMessage);
                        winnerText.setText(winnerMessage + " Wins");
                        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        playAgainLayout.setVisibility(View.VISIBLE);
                        gameIsOver=true;
                    }
                    else
                    {
                        //Draw logic
                        gameIsOver=true;
                        for(int i:gameState) {
                            if (i == 2) {
                                gameIsOver = false;
                            }
                        }
                            if(gameIsOver)
                            {
                                //Display a playAgain Layout
                                TextView winnerText = (TextView) findViewById(R.id.winnerMessage);
                                winnerText.setText("Draw Game");
                                LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                                playAgainLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
            }
        }



    //Play again Logic
    public void playAgain(View view)
    {
        LinearLayout playAgainLayout=(LinearLayout)findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(View.INVISIBLE);
        gameIsOver=false;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        //Remove all images of gridView
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
