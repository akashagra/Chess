package com.example.avma1997.chess;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout mainLayout;
    MyButton buttons[][];
    LinearLayout rowLayouts[];
    static int n = 8;
    static int count = 0;
    MyButton bt;
     static String a="";
    static int[] xc = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] yc = {-1, 0, 1, -1, 1, -1, 0, 1};
    TextView bl;
    TextView wh;
    static int moves=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        setUpBoard();
         wh=(TextView)findViewById(R.id.whitetv);
         bl=(TextView)findViewById(R.id.blacktv);
        wh.setText("White Turn");

    }

    public void setUpBoard() {
        count=0;
        buttons = new MyButton[n][n];
        rowLayouts = new LinearLayout[n];
        mainLayout.removeAllViews();
        for (int i = 0; i < n; i++) {
            rowLayouts[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f);
            rowLayouts[i].setLayoutParams(params);
            rowLayouts[i].setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.addView(rowLayouts[i]);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j] = new MyButton(this);
                buttons[i][j].x = i;
                buttons[i][j].y = j;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
                buttons[i][j].setLayoutParams(params);
                buttons[i][j].setOnClickListener(this);
                rowLayouts[i].addView(buttons[i][j]);
              //  buttons[i][j].setTextColor(Color.BLUE);
                if (count % 2 == 0)
                    buttons[i][j].setBackgroundColor(Color.BLACK);
                else
                    buttons[i][j].setBackgroundColor(Color.WHITE);
                count++;
            }
            count++;
        }

      //  buttons[0][0].setText("WR");
            //   buttons[0][1].setText("WH");
        buttons[0][0].setImageResource(R.drawable.rook);
        buttons[0][1].setImageResource(R.drawable.knightwhite);
        buttons[0][2].setImageResource(R.drawable.bishop);
        buttons[0][3].setImageResource(R.drawable.queen);
        buttons[0][4].setImageResource(R.drawable.king);
        buttons[0][5].setImageResource(R.drawable.bishop);
        buttons[0][6].setImageResource(R.drawable.knightwhite);
        buttons[0][7].setImageResource(R.drawable.rook);
        for(int j=0;j<8;j++)
            buttons[1][j].setImageResource(R.drawable.pawn);
        buttons[7][0].setImageResource(R.drawable.blackrook);
        buttons[7][1].setImageResource(R.drawable.blackknight);
        buttons[7][2].setImageResource(R.drawable.blackbishop);
        buttons[7][3].setImageResource(R.drawable.blackking);
        buttons[7][4].setImageResource(R.drawable.blackqueen);
        buttons[7][5].setImageResource(R.drawable.blackbishop);
        buttons[7][6].setImageResource(R.drawable.blackknight);
        buttons[7][7].setImageResource(R.drawable.blackrook);
        for(int j=0;j<8;j++)
            buttons[6][j].setImageResource(R.drawable.blackpawn);


       buttons[0][0].text="WR";
        buttons[0][1].text="WK";
       buttons[0][2].text="WB";
        buttons[0][3].text="WQ";
        buttons[0][4].text="WKing";
        buttons[0][5].text="WB";
        buttons[0][6].text="WK";
        buttons[0][7].text="WR";
      for(int j=0;j<8;j++)
      buttons[1][j].text="WP";
        buttons[7][0].text="BR";
        buttons[7][1].text="BK";
        buttons[7][2].text="BB";
        buttons[7][3].text="BKing";
        buttons[7][4].text="BQ";
        buttons[7][5].text="BB";
        buttons[7][6].text="BK";
        buttons[7][7].text="BR";
        for(int j=0;j<8;j++)
            buttons[6][j].text="BP";



    }
    public void clearScreen()
    { count=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (count % 2 == 0)
                    buttons[i][j].setBackgroundColor(Color.BLACK);
                else
                    buttons[i][j].setBackgroundColor(Color.WHITE);
                count++;


            if (buttons[i][j].text.equals(":")) {
                buttons[i][j].text = "";
                buttons[i][j].setImageResource(0);
            }
            if(buttons[i][j].text.equals("CUT")){
                buttons[i][j].text=a;

            }
        }
        count++;
    }


    }


    @Override
    public void onClick(View v) {


        MyButton button = (MyButton) v;
        int X = button.x;
        int Y = button.y;
        // White Turn
        if(moves % 2==0) {
            if (button.text.length() != 0 && button.text.charAt(0) == 'B')
                return;
        }
        //Black Turn
        if(moves %2 !=0) {
            if (button.text.length() != 0 && button.text.charAt(0) == 'W')
                return;
        }
        if (button.text.equals("WR") || button.text.equals("BR")) {
            clearScreen();
            bt = button;
            // Backward Straight Vertical
            for (int i = X - 1; i >= 0; i--) {
                if(buttons[i][Y].text.length()!=0 && button.text.equals("WR") && buttons[i][Y].text.charAt(0)=='B')
                {
                    buttons[i][Y].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][Y].text;
                    buttons[i][Y ].text = "CUT";
                    break;
                }
                if(buttons[i][Y].text.length()!=0 && button.text.equals("BR") && buttons[i][Y].text.charAt(0)=='W')
                {
                    buttons[i][Y].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][Y].text;
                    buttons[i][Y ].text = "CUT";
                    break;
                }
                if (!(buttons[i][Y].text.equals("")))
                    break;
                buttons[i][Y].text = ":";
                buttons[i][Y].setImageResource(R.drawable.more);

            }

            //Forward Straight Vertical

            for (int i = X + 1; i <= 7; i++) {
                if(buttons[i][Y].text.length()!=0 && button.text.equals("WR") && buttons[i][Y].text.charAt(0)=='B')
                {
                    buttons[i][Y].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][Y].text;
                    buttons[i][Y ].text = "CUT";
                    break;
                }
                if(buttons[i][Y].text.length()!=0 && button.text.equals("BR") && buttons[i][Y].text.charAt(0)=='W')
                {
                    buttons[i][Y].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][Y].text;
                    buttons[i][Y ].text = "CUT";
                    break;
                }
                if (!(buttons[i][Y].text.equals("")))
                    break;
                buttons[i][Y].text = ":";
                buttons[i][Y].setImageResource(R.drawable.more);

            }
            //Backward Straight Horizontal

            for (int j = Y - 1; j >= 0; j--) {
                if(buttons[X][j].text.length()!=0 && button.text.equals("WR") && buttons[X][j].text.charAt(0)=='B')
                {
                    buttons[X][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[X][j].text;
                    buttons[X ][j].text = "CUT";
                    break;
                }
                if(buttons[X][j].text.length()!=0 && button.text.equals("BR") && buttons[X][j].text.charAt(0)=='W')
                {
                    buttons[X][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[X][j].text;
                    buttons[X][j ].text = "CUT";
                    break;
                }
                if (!(buttons[X][j].text.equals("")))
                    break;
                buttons[X][j].text = ":";
                buttons[X][j].setImageResource(R.drawable.more);

            }
            // Forward Straight Horizontal
            for (int j = Y + 1; j <=7; j++) {
                if(buttons[X][j].text.length()!=0 && button.text.equals("WR") && buttons[X][j].text.charAt(0)=='B')
                {
                    buttons[X][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+1][Y+1].text;
                    buttons[X + 1][Y + 1].text = "CUT";
                    break;
                }
                if(buttons[X][j].text.length()!=0 && button.text.equals("BR") && buttons[X][j].text.charAt(0)=='W')
                {
                    buttons[X][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[X][j].text;
                    buttons[X][j].text = "CUT";
                    break;
                }
                if (!(buttons[X][j].text.equals("")))
                    break;
                buttons[X][j].text = ":";
                buttons[X][j].setImageResource(R.drawable.more);

            }
        }
        if(button.text.equals("WK")|| button.text.equals("BK")) {
            clearScreen();
            bt=button;
           // X+2,Y-1

            if( X+2<=7 && Y-1>=0) {
                if(buttons[X+2][Y-1].text.length()!=0 && button.text.equals("WK") && buttons[X+2][Y-1].text.charAt(0)=='B')
                {
                    buttons[X+2][Y-1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+2][Y-1].text;
                    buttons[X + 2][Y -1].text = "CUT";

                }
                if(buttons[X+2][Y-1].text.length()!=0 && button.text.equals("BK") && buttons[X+2][Y-1].text.charAt(0)=='W')
                {
                    buttons[X+2][Y-1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+2][Y-1].text;
                    buttons[X+2][Y -1].text = "CUT";

                }
                if (buttons[X + 2][Y - 1].text.equals("")) {
                    buttons[X + 2][Y - 1].text = ":";
                    buttons[X + 2][Y - 1].setImageResource(R.drawable.more);
                }
            }
            if(X+2<=7 &&  Y+1<=7) {
                if(buttons[X+2][Y+1].text.length()!=0 && button.text.equals("WK") && buttons[X+2][Y+1].text.charAt(0)=='B')
                {
                    buttons[X+2][Y+1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+2][Y+1].text;
                    buttons[X + 2][Y + 1].text = "CUT";

                }
                if(buttons[X+2][Y+1].text.length()!=0 && button.text.equals("BK") && buttons[X+2][Y+1].text.charAt(0)=='W')
                {
                    buttons[X+2][Y+1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+2][Y+1].text;
                    buttons[X+2][Y +1].text = "CUT";

                }
                if (buttons[X + 2][Y + 1].text.equals("")) {
                    buttons[X + 2][Y + 1].text = ":";
                    buttons[X + 2][Y + 1].setImageResource(R.drawable.more);
                }
            }
            if(X+1<=7 && Y+2<=7) {
                if(buttons[X+1][Y+2].text.length()!=0 && button.text.equals("WK") && buttons[X+1][Y+2].text.charAt(0)=='B')
                {
                    buttons[X+1][Y+2].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+1][Y+2].text;
                    buttons[X + 1][Y + 2].text = "CUT";

                }
                if(buttons[X+1][Y+2].text.length()!=0 && button.text.equals("BK") && buttons[X+1][Y+2].text.charAt(0)=='W')
                {
                    buttons[X+1][Y+2].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+1][Y+2].text;
                    buttons[X+1][Y+2].text = "CUT";

                }
                if (buttons[X + 1][Y + 2].text.equals("")) {
                    buttons[X + 1][Y + 2].text = ":";
                    buttons[X + 1][Y + 2].setImageResource(R.drawable.more);
                }
            }
            if(X+1<=7 && Y-2>=0) {
                if(buttons[X+1][Y-2].text.length()!=0 && button.text.equals("WK") && buttons[X+1][Y-2].text.charAt(0)=='B')
                {
                    buttons[X+1][Y-2].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+1][Y-2].text;
                    buttons[X + 1][Y -2].text = "CUT";

                }
                if(buttons[X+1][Y-2].text.length()!=0 && button.text.equals("BK") && buttons[X+1][Y-2].text.charAt(0)=='W')
                {
                    buttons[X+1][Y-2].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+1][Y-2].text;
                    buttons[X+1][Y-2 ].text = "CUT";

                }
                if (buttons[X + 1][Y - 2].text.equals("")) {
                    buttons[X + 1][Y - 2].text = ":";
                    buttons[X + 1][Y - 2].setImageResource(R.drawable.more);
                }
            }
            if(X-1>=0 && Y+2<=7) {
                if(buttons[X-1][Y+2].text.length()!=0 && button.text.equals("WK") && buttons[X-1][Y+2].text.charAt(0)=='B')
                {
                    buttons[X-1][Y+2].setBackgroundColor(Color.YELLOW);
                    a=buttons[X-1][Y+2].text;
                    buttons[X -1][Y + 2].text = "CUT";

                }
                if(buttons[X-1][Y+2].text.length()!=0 && button.text.equals("BK") && buttons[X-1][Y+2].text.charAt(0)=='W')
                {
                    buttons[X-1][Y+2].setBackgroundColor(Color.YELLOW);
                    a=buttons[X-1][Y+2].text;
                    buttons[X-1][Y+2 ].text = "CUT";

                }
                if (buttons[X - 1][Y + 2].text.equals("")) {
                    buttons[X - 1][Y + 2].text = ":";
                    buttons[X - 1][Y + 2].setImageResource(R.drawable.more);
                }
            }
            if(X-1>=0 && Y-2>=0) {
                if(buttons[X-1][Y-2].text.length()!=0 && button.text.equals("WK") && buttons[X-1][Y-2].text.charAt(0)=='B')
                {
                    buttons[X-1][Y-2].setBackgroundColor(Color.YELLOW);
                    a=buttons[X-1][Y-2].text;
                    buttons[X -1][Y -2].text = "CUT";

                }
                if(buttons[X-1][Y-2].text.length()!=0 && button.text.equals("BK") && buttons[X-1][Y-2].text.charAt(0)=='W')
                {
                    buttons[X-1][Y-2].setBackgroundColor(Color.YELLOW);
                    a=buttons[X-1][Y-2].text;
                    buttons[X-1][Y -2].text = "CUT";

                }
                if (buttons[X - 1][Y - 2].text.equals("")) {
                    buttons[X - 1][Y - 2].text = ":";
                    buttons[X - 1][Y - 2].setImageResource(R.drawable.more);
                }
            }
            if(X-2>=0 && Y-1>=0) {
                if(buttons[X-2][Y-1].text.length()!=0 && button.text.equals("WK") && buttons[X-2][Y-1].text.charAt(0)=='B')
                {
                    buttons[X-2][Y-1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X-2][Y-1].text;
                    buttons[X -2][Y -1].text = "CUT";

                }
                if(buttons[X-2][Y-1].text.length()!=0 && button.text.equals("BK") && buttons[X-2][Y-1].text.charAt(0)=='W')
                {
                    buttons[X-2][Y-1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X-2][Y-1].text;
                    buttons[X-2][Y-1 ].text = "CUT";

                }
                if (buttons[X - 2][Y - 1].text.equals("")) {
                    buttons[X - 2][Y - 1].text = ":";
                    buttons[X - 2][Y - 1].setImageResource(R.drawable.more);
                }
            }
            if(X-2>=0 && Y+1<=7) {
                if(buttons[X-2][Y+1].text.length()!=0 && button.text.equals("WK") && buttons[X-2][Y+1].text.charAt(0)=='B')
                {
                    buttons[X-2][Y+1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X-2][Y+1].text;
                    buttons[X -2][Y + 1].text = "CUT";

                }
                if(buttons[X-2][Y+1].text.length()!=0 && button.text.equals("BK") && buttons[X-2][Y+1].text.charAt(0)=='W')
                {
                    buttons[X-2][Y+1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X-2][Y+1].text;
                    buttons[X-2][Y+1].text = "CUT";

                }
                if (buttons[X - 2][Y + 1].text.equals("")) {
                    buttons[X - 2][Y + 1].text = ":";
                    buttons[X - 2][Y + 1].setImageResource(R.drawable.more);
                }
            }
        }
        if (button.text.equals("WB") || button.text.equals("BB")) {
            clearScreen();
            bt = button;
            // Daigonal 1
            int i;
            int j;
            for (i = X - 1, j = Y - 1; i >= 0 && j >= 0; i--, j--) {
                if(buttons[i][j].text.length()!=0 && button.text.equals("WB") && buttons[i][j].text.charAt(0)=='B')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j].text = "CUT";
                    break;
                }
                if(buttons[i][j].text.length()!=0 && button.text.equals("BB") && buttons[i][j].text.charAt(0)=='W')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j ].text = "CUT";
                    break;
                }
                if ( !(buttons[i][j].text.equals("")))
                    break;
                buttons[i][j].text = ":";
                buttons[i][j].setImageResource(R.drawable.more);

            }
            //Daigonal 2

            for (i = X + 1, j = Y + 1; i <= 7 && j <= 7; i++, j++) {
                if(buttons[i][j].text.length()!=0 && button.text.equals("WB") && buttons[i][j].text.charAt(0)=='B')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j].text = "CUT";
                    break;
                }
                if(buttons[i][j].text.length()!=0 && button.text.equals("BB") && buttons[i][j].text.charAt(0)=='W')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j].text = "CUT";
                    break;
                }
                if ( !(buttons[i][j].text.equals("")))
                    break;
                buttons[i][j].text = ":";
                buttons[i][j].setImageResource(R.drawable.more);

            }
            //Daigonal 3

            for (i = X - 1, j = Y + 1; i >= 0 && j <= 7; i--, j++) {
                if(buttons[i][j].text.length()!=0 && button.text.equals("WB") && buttons[i][j].text.charAt(0)=='B')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j ].text = "CUT";
                    break;
                }
                if(buttons[i][j].text.length()!=0 && button.text.equals("BB") && buttons[i][j].text.charAt(0)=='W')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j].text = "CUT";
                    break;
                }
                if ( !(buttons[i][j].text.equals("")))
                    break;
                buttons[i][j].text = ":";
                buttons[i][j].setImageResource(R.drawable.more);


            }
            // Daigonal 4
            for (i = X + 1, j = Y - 1; i <= 7 && j >= 0; i++, j--) {
                if(buttons[i][j].text.length()!=0 && button.text.equals("WB") && buttons[i][j].text.charAt(0)=='B')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j ].text = "CUT";
                    break;
                }
                if(buttons[i][j].text.length()!=0 && button.text.equals("BB") && buttons[i][j].text.charAt(0)=='W')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j ].text = "CUT";
                    break;
                }
                if ( !(buttons[i][j].text.equals("")))
                    break;
                buttons[i][j].text = ":";
                buttons[i][j].setImageResource(R.drawable.more);

            }
        }

        if (button.text.equals("WP")) {
            clearScreen();
            bt = button;
            if(X+1<=7 && Y+1<=7) {
                if ( buttons[X+1][Y+1].text.length()!=0 &&buttons[X + 1][Y + 1].text.charAt(0) == 'B') {
                    buttons[X + 1][Y + 1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+1][Y+1].text;
                    buttons[X + 1][Y + 1].text = "CUT";
                }
            }
            if(X+1<=7 && Y-1>=0) {
                if ( buttons[X+1][Y-1].text.length()!=0 &&buttons[X + 1][Y - 1].text.charAt(0) == 'B') {
                    buttons[X + 1][Y - 1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+1][Y-1].text;
                    buttons[X + 1][Y - 1].text = "CUT";

                }
            }
            if (button.x == 1) {
                if (buttons[X + 1][Y].text.equals("")) {
                    buttons[X + 1][Y].text = ":";
                    buttons[X + 1][Y].setImageResource(R.drawable.more);
                }
                if (buttons[X + 2][Y].text.equals("")) {
                    buttons[X + 2][Y].text = ":";
                    buttons[X + 2][Y].setImageResource(R.drawable.more);
                }

            }
            else {

                if (buttons[X + 1][Y].text.equals("")) {
                    buttons[X + 1][Y].text = ":";
                    buttons[X + 1][Y].setImageResource(R.drawable.more);
                }
            }

        }

        if (button.text.equals("BP")) {
            clearScreen();
            bt = button;
            if (X - 1 >= 0 && Y + 1 <= 7) {
                if ( buttons[X-1][Y+1].text.length()!=0 &&buttons[X - 1][Y + 1].text.charAt(0) == 'W') {
                    buttons[X - 1][Y + 1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X-1][Y+1].text;
                    buttons[X - 1][Y + 1].text = "CUT";
                }
            }
            if (X - 1 >= 0 && Y - 1 >= 0) {
                if ( buttons[X-1][Y-1].text.length()!=0 &&buttons[X - 1][Y - 1].text.charAt(0) == 'W') {
                    buttons[X - 1][Y - 1].setBackgroundColor(Color.YELLOW);
                    a=buttons[X-1][Y-1].text;
                    buttons[X - 1][Y - 1].text = "CUT";

                }
            }

            if (button.x == 6) {
                if (buttons[X - 1][Y].text.equals("")) {
                    buttons[X - 1][Y].text = ":";
                    buttons[X - 1][Y].setImageResource(R.drawable.more);
                }
                if (buttons[X - 2][Y].text.equals("")) {
                    buttons[X - 2][Y].text = ":";
                    buttons[X - 2][Y].setImageResource(R.drawable.more);
                }
            }
            else {
                if (buttons[X - 1][Y].text.equals("")) {
                    buttons[X - 1][Y].text = ":";
                    buttons[X - 1][Y].setImageResource(R.drawable.more);
                }
            }

        }
        if (button.text.equals("BKing")|| button.text.equals("WKing")) {
            clearScreen();
            bt=button;

            for (int i = 0; i < 8; i++) {
                if (X + xc[i] >= 0 && X+xc[i]<=7 && Y+yc[i]>=0 && Y + yc[i] <= 7) {
                    if (buttons[X + xc[i]][Y + yc[i]].text.length() != 0 && button.text.equals("WKing") && buttons[X + xc[i]][Y + yc[i]].text.charAt(0) == 'B') {
                        buttons[X + xc[i]][Y + yc[i]].setBackgroundColor(Color.YELLOW);
                        a = buttons[X + xc[i]][Y + yc[i]].text;
                        buttons[X + xc[i]][Y + yc[i]].text = "CUT";
                    }
                  if (buttons[X + xc[i]][Y + yc[i]].text.length() != 0 && button.text.equals("BKing") && buttons[X + xc[i]][Y + yc[i]].text.charAt(0) == 'W') {
                        buttons[X + xc[i]][Y + yc[i]].setBackgroundColor(Color.YELLOW);
                        a = buttons[X + xc[i]][Y + yc[i]].text;
                        buttons[X + xc[i]][Y + yc[i]].text = "CUT";
                    }
                    if (buttons[X + xc[i]][Y + yc[i]].text.equals("")) {
                        buttons[X + xc[i]][Y + yc[i]].text = ":";
                        buttons[X + xc[i]][Y + yc[i]].setImageResource(R.drawable.more);
                    }

                }
            }
        }
        // Queen
        if (button.text.equals("WQ") || button.text.equals("BQ")) {

            clearScreen();
            bt = button;
            // Backward Straight Vertical
            for (int i = X - 1; i >= 0; i--) {
                if(buttons[i][Y].text.length()!=0 && button.text.equals("WQ") && buttons[i][Y].text.charAt(0)=='B')
                {
                    buttons[i][Y].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][Y].text;
                    buttons[i][Y ].text = "CUT";
                    break;
                }
                if(buttons[i][Y].text.length()!=0 && button.text.equals("BQ") && buttons[i][Y].text.charAt(0)=='W')
                {
                    buttons[i][Y].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][Y].text;
                    buttons[i][Y ].text = "CUT";
                    break;
                }
                if (!(buttons[i][Y].text.equals("")))
                    break;
                buttons[i][Y].text = ":";
                buttons[i][Y].setImageResource(R.drawable.more);

            }

            //Forward Straight Vertical

            for (int i = X + 1; i <= 7; i++) {
                if(buttons[i][Y].text.length()!=0 && button.text.equals("WQ") && buttons[i][Y].text.charAt(0)=='B')
                {
                    buttons[i][Y].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][Y].text;
                    buttons[i][Y ].text = "CUT";
                    break;
                }
                if(buttons[i][Y].text.length()!=0 && button.text.equals("BQ") && buttons[i][Y].text.charAt(0)=='W')
                {
                    buttons[i][Y].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][Y].text;
                    buttons[i][Y ].text = "CUT";
                    break;
                }
                if (!(buttons[i][Y].text.equals("")))
                    break;
                buttons[i][Y].text = ":";
                buttons[i][Y].setImageResource(R.drawable.more);

            }
            //Backward Straight Horizontal

            for (int j = Y - 1; j >= 0; j--) {
                if(buttons[X][j].text.length()!=0 && button.text.equals("WQ") && buttons[X][j].text.charAt(0)=='B')
                {
                    buttons[X][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[X][j].text;
                    buttons[X ][j].text = "CUT";
                    break;
                }
                if(buttons[X][j].text.length()!=0 && button.text.equals("BQ") && buttons[X][j].text.charAt(0)=='W')
                {
                    buttons[X][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[X][j].text;
                    buttons[X][j ].text = "CUT";
                    break;
                }
                if (!(buttons[X][j].text.equals("")))
                    break;
                buttons[X][j].text = ":";
                buttons[X][j].setImageResource(R.drawable.more);

            }
            // Forward Straight Horizontal
            for (int j = Y + 1; j <=7; j++) {
                if(buttons[X][j].text.length()!=0 && button.text.equals("WQ") && buttons[X][j].text.charAt(0)=='B')
                {
                    buttons[X][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[X+1][Y+1].text;
                    buttons[X + 1][Y + 1].text = "CUT";
                    break;
                }
                if(buttons[X][j].text.length()!=0 && button.text.equals("BQ") && buttons[X][j].text.charAt(0)=='W')
                {
                    buttons[X][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[X][j].text;
                    buttons[X][j].text = "CUT";
                    break;
                }
                if (!(buttons[X][j].text.equals("")))
                    break;
                buttons[X][j].text = ":";
                buttons[X][j].setImageResource(R.drawable.more);

            }
            int i;
            int j;
            for (i = X - 1, j = Y - 1; i >= 0 && j >= 0; i--, j--) {
                if(buttons[i][j].text.length()!=0 && button.text.equals("WQ") && buttons[i][j].text.charAt(0)=='B')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j].text = "CUT";
                    break;
                }
                if(buttons[i][j].text.length()!=0 && button.text.equals("BQ") && buttons[i][j].text.charAt(0)=='W')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j ].text = "CUT";
                    break;
                }
                if ( !(buttons[i][j].text.equals("")))
                    break;
                buttons[i][j].text = ":";
                buttons[i][j].setImageResource(R.drawable.more);

            }
            //Daigonal 2

            for (i = X + 1, j = Y + 1; i <= 7 && j <= 7; i++, j++) {
                if(buttons[i][j].text.length()!=0 && button.text.equals("WQ") && buttons[i][j].text.charAt(0)=='B')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j].text = "CUT";
                    break;
                }
                if(buttons[i][j].text.length()!=0 && button.text.equals("BQ") && buttons[i][j].text.charAt(0)=='W')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j].text = "CUT";
                    break;
                }
                if ( !(buttons[i][j].text.equals("")))
                    break;
                buttons[i][j].text = ":";
                buttons[i][j].setImageResource(R.drawable.more);

            }
            //Daigonal 3

            for (i = X - 1, j = Y + 1; i >= 0 && j <= 7; i--, j++) {
                if(buttons[i][j].text.length()!=0 && button.text.equals("WQ") && buttons[i][j].text.charAt(0)=='B')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j ].text = "CUT";
                    break;
                }
                if(buttons[i][j].text.length()!=0 && button.text.equals("BQ") && buttons[i][j].text.charAt(0)=='W')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j].text = "CUT";
                    break;
                }
                if ( !(buttons[i][j].text.equals("")))
                    break;
                buttons[i][j].text = ":";
                buttons[i][j].setImageResource(R.drawable.more);


            }

            for ( i = X + 1,  j = Y - 1; i <= 7 && j >= 0; i++, j--) {
                if(buttons[i][j].text.length()!=0 && button.text.equals("WQ") && buttons[i][j].text.charAt(0)=='B')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j ].text = "CUT";
                    break;
                }
                if(buttons[i][j].text.length()!=0 && button.text.equals("BQ") && buttons[i][j].text.charAt(0)=='W')
                {
                    buttons[i][j].setBackgroundColor(Color.YELLOW);
                    a=buttons[i][j].text;
                    buttons[i][j ].text = "CUT";
                    break;
                }
                if ( !(buttons[i][j].text.equals("")))
                    break;
                buttons[i][j].text = ":";
                buttons[i][j].setImageResource(R.drawable.more);

            }




        }


        if (button.text.equals(":")) {
            clearScreen();
            button.text = bt.text;
            if (button.text.equals("WR"))
                button.setImageResource(R.drawable.rook);
            if (button.text.equals("WK"))
                button.setImageResource(R.drawable.knightwhite);
            if (button.text.equals("WB"))
                button.setImageResource(R.drawable.bishop);
            if (button.text.equals("WQ"))
                button.setImageResource(R.drawable.queen);
            if (button.text.equals("WKing"))
                button.setImageResource(R.drawable.king);
            if (button.text.equals("WP"))
                button.setImageResource(R.drawable.pawn);
            if (button.text.equals("BR"))
                button.setImageResource(R.drawable.blackrook);
            if (button.text.equals("BK"))
                button.setImageResource(R.drawable.blackknight);
            if (button.text.equals("BB"))
                button.setImageResource(R.drawable.blackbishop);
            if (button.text.equals("BQ"))
                button.setImageResource(R.drawable.blackqueen);
            if (button.text.equals("BKing"))
                button.setImageResource(R.drawable.blackking);
            if (button.text.equals("BP"))
                button.setImageResource(R.drawable.blackpawn);


            bt.text = "";
            bt.setImageResource(0);
            moves++;
            if (moves % 2 != 0) {
                bl.setText("Black Turn");
                wh.setText("");
            } else {
                wh.setText("White Turn");
                bl.setText("");
            }
        }


        if (button.text.equals("CUT")) {
            clearScreen();
            button.text = bt.text;
            if(button.text.equals("WR"))
                button.setImageResource(R.drawable.rook);
            if(button.text.equals("WK"))
                button.setImageResource(R.drawable.knightwhite);
            if(button.text.equals("WB"))
                button.setImageResource(R.drawable.bishop);
            if(button.text.equals("WQ"))
                button.setImageResource(R.drawable.queen);
            if(button.text.equals("WKing"))
                button.setImageResource(R.drawable.king);
            if(button.text.equals("WP"))
                button.setImageResource(R.drawable.pawn);
            if(button.text.equals("BR"))
                button.setImageResource(R.drawable.blackrook);
            if(button.text.equals("BK"))
                button.setImageResource(R.drawable.blackknight);
            if(button.text.equals("BB"))
                button.setImageResource(R.drawable.blackbishop);
            if(button.text.equals("BQ"))
                button.setImageResource(R.drawable.blackqueen);
            if(button.text.equals("BKing"))
                button.setImageResource(R.drawable.blackking);
            if(button.text.equals("BP"))
                button.setImageResource(R.drawable.blackpawn);



            bt.text = "";
            bt.setImageResource(0);
            moves++;
            if (moves % 2 != 0) {
                bl.setText("Black Turn");
                wh.setText("");
            } else {
                wh.setText("White Turn");
                bl.setText("");
            }
        }
    }






    }

