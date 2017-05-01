package csee4190.columbiaa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView swipe;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = (TextView)findViewById(R.id.swipeView);
        swipe.setOnTouchListener(new OnSwipeTouchListener(context){
            public void onSwipeLeft(){
                Intent myItent = new Intent(MainActivity.this, Weather.class);
                startActivity(myItent);
            }
            public void onSwipeRight(){
                Intent myItent = new Intent(MainActivity.this, News.class);
                startActivity(myItent);
            }
        });

<<<<<<< HEAD
        QuoteActivityListener qu = new QuoteActivityListener();
        btn_quo = (Button)findViewById(R.id.quo_btn);
        btn_quo.setOnClickListener(qu);

    }

    private class WeatherActivityListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent myItent = new Intent(MainActivity.this, Weather.class);
            startActivity(myItent);
        }
    }

    private class NewsActivityListener implements  View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent myItent = new Intent(MainActivity.this,News.class);
            Log.d("news","click news");
            startActivity(myItent);
        }
    }
=======
    }

/*
>>>>>>> c474bd7c45b599118afa3f3fdef462d0f74536cd

    private class QuoteActivityListener implements  View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent myItent = new Intent(MainActivity.this,QuoteAct.class);
            Log.d("quote","click quote");
            startActivity(myItent);
        }
    }
*/
}
