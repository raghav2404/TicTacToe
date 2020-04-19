package android.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button[][] buttons=new Button[3][3];
private boolean player1turn=true;
private int roundCount;
private TextView t1,t2;
private int player1points;
private  int player2points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.textView1);
        t2=findViewById(R.id.textView2);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String buttonId="button_"+i+j;
                int resourceId=getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j]=findViewById(resourceId);
                buttons[i][j].setOnClickListener(this);
            }
        }

Button reset=findViewById(R.id.button);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              resetGame();
            }
        });
    }

    @Override
    public void onClick(View view) {
  if  (!((Button)view).getText().toString().equals(""))
  {

      return;
  }

    if(player1turn)
    {
        ((Button) view).setText("X");
    }
else
    {
        ((Button) view).setText("O");
    }
roundCount++;
if(checkForWIN()){
    if(player1turn)
        player1wins();
    else
        player2wins();
}
else if(roundCount==9)
    draw();
else
{
    player1turn=!player1turn;
}

  }

   private boolean checkForWIN()
   {
       String[][] field=new String[3][3];
       for(int i=0;i<3;i++)
       {
           for(int j=0;j<3;j++)
           {
               field[i][j]=buttons[i][j].getText().toString();
           }
       }
      for(int i=0;i<3;i++)
      {
          if(field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&&(!field[i][0].equals("")))
              return true;
      }
       for(int i=0;i<3;i++)
       {
           if(field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])&&(!field[0][i].equals("")))
               return true;
       }

       if(field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])&&(!field[0][0].equals("")))
           return true;
       if(field[0][2].equals(field[1][1])&&field[0][2].equals(field[2][0])&&(!field[0][2].equals("")))
           return true;

return false;



   }
   private  void player1wins()
   {
player1points++;
       StyleableToast.makeText(getApplicationContext(),"Player 1 Wins!",Toast.LENGTH_LONG).show();
       updatepPointsText();
       resetBoard();

   }
   private void player2wins()
   {
       player2points++;
       StyleableToast.makeText(getApplicationContext(),"Player 2 Wins!",Toast.LENGTH_LONG).show();
       updatepPointsText();
       resetBoard();
   }
   private  void draw()
   {
       StyleableToast.makeText(getApplicationContext(),"It's a Draw!",Toast.LENGTH_LONG).show();
       resetBoard();
   }
   private void updatepPointsText()
   {
       t1.setText("Player1: "+player1points);
       t2.setText("Player2: "+player2points);
   }
   private  void resetBoard()
   {
       for(int i=0;i<3;i++)
       {
           for(int j=0;j<3;j++)
           {
               buttons[i][j].setText("");
           }
       }
       roundCount=0;
       player1turn=true;
   }
   private void resetGame()
   {
       player1points=0;
       player2points=0;
       updatepPointsText();
       resetBoard();
   }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount);
        outState.putInt("player1points", player1points);
        outState.putInt("player2points", player2points);
        outState.putBoolean("player1turn", player1turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount=savedInstanceState.getInt(String.valueOf(roundCount));
        player1points=savedInstanceState.getInt(String.valueOf(player1points));
        player2points=savedInstanceState.getInt(String.valueOf(player2points));
        player1turn=savedInstanceState.getBoolean(String.valueOf(player1turn));
    }
}
