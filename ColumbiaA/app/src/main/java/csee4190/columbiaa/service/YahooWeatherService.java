package csee4190.columbiaa.service;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import csee4190.columbiaa.data.Forecast;
import csee4190.columbiaa.data.channel;

public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;


    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(final String location) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                //newyork, ny
                String YQL = String.format("Select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")",location);

                String endpt=String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try{
                    URL url = new URL(endpt);
                    URLConnection connection=url.openConnection();

                    InputStream is = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder output=new StringBuilder();
                    String l;
                    while((l=reader.readLine())!=null){
                        output.append(l);
                    }

                    return output.toString();
                }catch (Exception e) {
                    error = e;
                }


                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s==null && error!=null){
                    callback.serviceFailure(error);

                    return;

                }

                try {
                    JSONObject data = new JSONObject(s);
                    JSONObject q=data.optJSONObject("query");
                    int count=q.optInt("count");
                    if(count==0){
                        callback.serviceFailure(new LocationWeatherException("No Weather Info for "+ location));
                        return;
                    }

                    channel ch=new channel();
                    ch.populate(q.optJSONObject("results").optJSONObject("channel"));



                    callback.serviceSuccess(ch);


                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }


            }
        }.execute(location);

    }

    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String message) {
            super(message);
        }
    }
}
