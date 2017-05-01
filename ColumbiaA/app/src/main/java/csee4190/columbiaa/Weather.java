package csee4190.columbiaa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import csee4190.columbiaa.data.channel;
import csee4190.columbiaa.data.item;
import csee4190.columbiaa.service.WeatherServiceCallback;
import csee4190.columbiaa.service.YahooWeatherService;

public class Weather extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIcon;
    private TextView temperature;
    private TextView condition;
    private TextView location;
    private TextView title;
    private TextView date;
    private TextView highT;
    private TextView lowT;
    private TextView condT;
    //swipe
    private TextView swipe;
    Context context;

    private YahooWeatherService service;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherIcon = (ImageView)findViewById(R.id.weather_icon);
        temperature = (TextView) findViewById(R.id.temperatureText);
        condition = (TextView) findViewById(R.id.conditionText);
        //location = (TextView) findViewById(R.id.locationText);
        title = (TextView) findViewById(R.id.titleText);
        date = (TextView) findViewById(R.id.dateText);
        highT=(TextView) findViewById(R.id.highTemText);
        lowT = (TextView) findViewById(R.id.lowTempText);
        condT = (TextView) findViewById(R.id.condTText);


        service= new YahooWeatherService(this);
        dialog= new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("newyork, NY");


        swipe = (TextView)findViewById(R.id.swipeWeather);
        swipe.setOnTouchListener(new OnSwipeTouchListener(context){
            public void onSwipeLeft(){
                Intent myItent = new Intent(Weather.this, News.class);
                startActivity(myItent);
            }
            public void onSwipeRight(){
                Intent myItent = new Intent(Weather.this, QuoteAct.class);
                startActivity(myItent);
            }
        });

    }


    @Override
    public void serviceSuccess(channel channel) {
        dialog.hide();
        final item itemInfo = channel.getItems();
        int rid = getResources().getIdentifier("drawable/icon_"+ itemInfo.getCond().getCode(),null,getPackageName());
        @SuppressWarnings("deprecation")
        Drawable iconDraw = getResources().getDrawable(rid);

        weatherIcon.setImageDrawable(iconDraw);
        //location.setText(service.getLocation());
        title.setText(itemInfo.getTitle());
        temperature.setText(itemInfo.getCond().getTemperature()+"\u00B0"+channel.getUnits().getTemp());
        condition.setText(itemInfo.getCond().getDesp());
        date.setText(itemInfo.getFc().getDay());
        lowT.setText(itemInfo.getFc().getLow());
        highT.setText(itemInfo.getFc().getHigh());
        condT.setText(itemInfo.getFc().getText());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this,exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}
