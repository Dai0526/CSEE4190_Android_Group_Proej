package csee4190.columbiaa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class QuoteAct extends AppCompatActivity {

    private TextView quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        DatabaseHandler db = new DatabaseHandler(this);

        quote = (TextView)findViewById(R.id.quote_view);
        String test = db.getIt(3);
        Log.d("Testing db with", test);
    }
}
