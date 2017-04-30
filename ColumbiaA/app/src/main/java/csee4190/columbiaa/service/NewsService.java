package csee4190.columbiaa.service;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import csee4190.columbiaa.data.Article;
import csee4190.columbiaa.data.channel;

/**
 * Created by Tianhua on 2017/4/29.
 */

public class NewsService {
    private NewsServiceCallback callback;
    private Exception error;

    public NewsService(NewsServiceCallback callback) {this.callback=callback;}

    public void refereshNews(){
        new AsyncTask<String,Void,String>() {


            @Override
            protected String doInBackground(String... params) {
                String endpt = String.format("https://newsapi.org/v1/articles?source=techcrunch&apiKey=642658eb4ebf432aaee64b1bc62913c1");
                try {
                    URL url = new URL(endpt);
                    URLConnection connection = url.openConnection();

                    InputStream is = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder output = new StringBuilder();
                    String l;
                    while ((l = reader.readLine()) != null) {
                        output.append(l);
                    }

                    return output.toString();
                } catch (Exception e) {
                    error = e;
                }
                return null;
            }//dibg

            @Override
            protected void onPostExecute(String s){
                if(s==null && error!=null){
                    callback.serviceFailure(error);

                    return;

                }

                try {
                    JSONObject data = new JSONObject(s);
                    String status=data.optString("status");
                    //Log.d("status info " , status);
                    //JSONObject q=data.optJSONObject("status");
                    //int count=q.optInt("count");
                    if(!status.equals("ok")){
                        callback.serviceFailure(new NewsService.newsConnectionException("No News Info"));
                        return;
                    }

                    JSONArray arry = data.optJSONArray("articles");

                    Article ar=new Article();

                    ar.populate(arry.getJSONObject(0));
                    //Log.d("myPrint " , "article");
                    callback.serviceSuccess(ar);
                    //Log.d("myPrint " , "This is a message");

                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }

            }

        }.execute();

    }

    public class newsConnectionException extends Exception{
        public newsConnectionException(String message){super(message);}
    }

}
