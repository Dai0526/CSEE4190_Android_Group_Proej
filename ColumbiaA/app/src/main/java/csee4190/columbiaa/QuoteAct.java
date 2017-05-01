package csee4190.columbiaa;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.text.DateFormat;
public class QuoteAct extends AppCompatActivity {

    private TextView quoteView;
    private TextView authView;
    private TextView swipe;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        quoteView = (TextView)findViewById(R.id.quote_view);
        authView = (TextView)findViewById(R.id.authView);
        ArrayList<String> quoteList = new ArrayList<String>();

        swipe = (TextView)findViewById(R.id.quoteSwipe);
        swipe.setOnTouchListener(new OnSwipeTouchListener(context){
            public void onSwipeLeft(){
                Intent myItent = new Intent(QuoteAct.this, Weather.class);
                startActivity(myItent);
            }
            public void onSwipeRight(){
                Intent myItent = new Intent(QuoteAct.this, MainActivity.class);
                startActivity(myItent);
            }
        });

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("quote.csv")));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] RowData = line.split(",");
                for (int i = 0; i < RowData.length; i++) {
                    quoteList.add(RowData[i]);
                }
            }

            quoteResult r = buildHash(quoteList);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            Date today = cal.getTime();
            DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
            String today_str = outputFormatter.format(today);
            Log.d("time",today.toString());
            String author = r.author.get(today_str);
            String quote = r.quote.get(today_str);
            quoteView.setText(quote);
            authView.setText(author);
            //quoteView.setText(quoteList.get(3));
            //Log.d("q",quoteList.get(3));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }
    public quoteResult buildHash(ArrayList<String> quoteList){
        HashMap<String,String> hm_author=new HashMap<String,String>();
        HashMap<String,String> hm_quote=new HashMap<String,String>();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2017);
        c.set(Calendar.MONTH, Calendar.MAY);
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date temp_date = c.getTime();
        DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
        String temp_hash_date = outputFormatter.format(temp_date);
        Log.d("hash",temp_hash_date);

        for (int i = 0; i < (quoteList.size()/4); i=i+4) {
            String quoteID = quoteList.get(i);
            String category = quoteList.get(i+1);
            String author = quoteList.get(i+2);
            String quote = quoteList.get(i+3);

            temp_hash_date = outputFormatter.format(temp_date);
            hm_author.put(temp_hash_date,author);
            hm_quote.put(temp_hash_date,quote);

            c.setTime(temp_date);
            c.add(Calendar.DATE, 1);
            temp_date = c.getTime();
        }
        quoteResult result = new quoteResult();
        result.author = hm_author;
        result.quote = hm_quote;
        return result;
    }

    public class quoteResult {
        HashMap<String,String> author;
        HashMap<String,String> quote;
    }

}
