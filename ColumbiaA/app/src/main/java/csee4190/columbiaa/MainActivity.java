package csee4190.columbiaa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_weather;
    private Button btn_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherActivityListener ml=new WeatherActivityListener();
        btn_weather=(Button)findViewById(R.id.weatherBtn);
        btn_weather.setOnClickListener(ml);

        NewsActivityListener nl=new NewsActivityListener();
        btn_news=(Button)findViewById(R.id.news_btn);
        btn_news.setOnClickListener(nl);
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
            startActivity(myItent);
        }
    }



}
