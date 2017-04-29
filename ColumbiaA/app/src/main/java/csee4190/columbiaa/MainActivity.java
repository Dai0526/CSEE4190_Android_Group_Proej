package csee4190.columbiaa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherActivityListener ml=new WeatherActivityListener();
        btn_weather=(Button)findViewById(R.id.weatherBtn);
        btn_weather.setOnClickListener(ml);
    }

    private class WeatherActivityListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent myItent = new Intent(MainActivity.this, Weather.class);
            startActivity(myItent);
        }
    }



}
