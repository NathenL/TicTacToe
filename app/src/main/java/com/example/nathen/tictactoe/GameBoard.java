package com.example.nathen.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;



public class GameBoard extends AppCompatActivity {
    //private char board[][] = new char[3][3];
    private char turn = 'X';
    private boolean gameover = true;

    private Button board[][] = new Button[3][3];
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView)findViewById(R.id.whosTurn);

        // Row 1 buttons
        board[0][0] = (Button)findViewById(R.id.button1_1);
        board[0][1] = (Button)findViewById(R.id.button1_2);
        board[0][2] = (Button)findViewById(R.id.button1_3);
        // Row 2 buttons
        board[1][0] = (Button)findViewById(R.id.button2_1);
        board[1][1] = (Button)findViewById(R.id.button2_2);
        board[1][2] = (Button)findViewById(R.id.button2_3);
        // Row 3 buttons
        board[2][0] = (Button)findViewById(R.id.button3_1);
        board[2][1] = (Button)findViewById(R.id.button3_2);
        board[2][2] = (Button)findViewById(R.id.button3_3);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
                text.setText("It's "+turn+"s Turn");
                text.setTextSize(90);
                gameover = false;
                Snackbar.make(view, "Starting new game, "+turn+" will go first.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    /**
     *
     */
    private void newGame(){
        Random r = new Random();
        this.turn = "XO".charAt(r.nextInt(2));
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j].setText("");
            }
        }
    }

    /**
     *
     */
    private void checkBoard(){
        String winner = "";
        if ((winner = checkRows()) != "" || (winner = checkCols()) != "" || (winner = checkDiag()) != ""){
            gameover = true;
            text.setTextSize(60);
            text.setText("The winner is "+winner+"s!");
        }
        else {
            boolean tie = true;
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if(board[i][j].getText().equals("")){
                        tie = false;
                        break;
                    }
                }
            }
            if (tie){
                gameover = true;
                text.setText("Tie Game!");
            }
        }
    }

    /**
     * Checks the rows of the board. If they find 3 in a row, return winner character (X,O)
     * Otherwise return empty String ""
     * @return char
     */
    private String checkRows(){
        String winner = "";
        boolean topRow = (board[0][0].getText().equals(board[0][1].getText()) && board[0][1].getText().equals(board[0][2].getText()));
        boolean middleRow = (board[1][0].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[1][2].getText()));
        boolean bottomRow = (board[2][0].getText().equals(board[2][1].getText()) && board[2][1].getText().equals(board[2][2].getText()));

        text.setText(board[1][0].getText() +" | "+ board[1][1].getText()+ " | " +board[1][2].getText());

        if (topRow && !board[0][0].getText().equals("")) {
            winner = board[0][0].getText().toString();
        } else if (middleRow && !board[1][0].getText().equals("")) {
            winner = board[1][0].getText().toString();
        } else if (bottomRow && !board[1][0].getText().equals("")) {
            winner = board[2][0].getText().toString();
        }

        return winner;
    }

    /**
     *
     * @return
     */
    private String checkCols(){
        String winner = "";
        boolean leftCol = (board[0][0].getText().equals(board[1][0].getText()) && board[1][0].getText().equals(board[2][0].getText()));
        boolean midCol = (board[0][1].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[2][1].getText()));
        boolean rightCol = (board[0][2].getText().equals(board[1][2].getText()) && board[1][2].getText().equals(board[2][2].getText()));

        if (leftCol && !board[0][0].getText().equals("")) {
            winner = board[0][0].getText().toString();
        } else if (midCol && !board[0][1].getText().equals("")) {
            winner = board[0][1].getText().toString();
        } else if (rightCol && !board[0][2].getText().equals("")) {
            winner = board[0][2].getText().toString();
        }

        return winner;
    }

    /**
     *
     * @return
     */
    private String checkDiag(){
        String winner = "";
        boolean bSlash = (board[0][0].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[2][2].getText())); //  \ backslash win
        boolean fSlash = (board[0][2].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[2][0].getText())); //  / forwardslash win

        if (bSlash && !board[0][0].getText().equals("")) {
            winner = board[0][0].getText().toString();
        } else if (fSlash && !board[0][2].getText().equals("")) {
            winner = board[0][2].getText().toString();
        }

        return winner;
    }

    /**
     *
     * @param v
     */
    public void onClick(View v) {
        if (!gameover) {
            int id = v.getId();
            Button b = null;

            switch(id){
                case R.id.button1_1:
                    b = board[0][0];
                    break;
                case R.id.button1_2:
                    b = board[0][1];
                    break;
                case R.id.button1_3:
                    b = board[0][2];
                    break;
                case R.id.button2_1:
                    b = board[1][0];
                    break;
                case R.id.button2_2:
                    b = board[1][1];
                    break;
                case R.id.button2_3:
                    b = board[1][2];
                    break;
                case R.id.button3_1:
                    b = board[2][0];
                    break;
                case R.id.button3_2:
                    b = board[2][1];
                    break;
                case R.id.button3_3:
                    b = board[2][2];
                    break;
            }
            if (b != null && b.getText() == "") {
                b.setText(Character.toString(turn));
                //b.setTextColor((turn == 'X') ? "@color/colorPrimary" : "@color/colorOs");
                if (turn == 'X'){
                    b.setTextColor(Color.parseColor("#f44336"));
                }
                else {
                    b.setTextColor(Color.parseColor("#FF42A5F5"));
                }
                checkBoard();
                if (!gameover) {
                    turn = (turn == 'X') ? 'O' : 'X';
                    text.setText("It's "+turn+"s Turn");
                }
            }
        }
    }
}
