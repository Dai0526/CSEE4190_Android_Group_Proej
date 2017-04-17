package csee4190.final_proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //data field
    private TextView eventList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        eventList = (TextView) findViewById(R.id.showcase);


        //6409761967155540421
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new GraphRequest(
                AccessToken.getCurrentAccessToken(),"6409761967155540421/events",null, HttpMethod.GET,
                new GraphRequest.Callback(){
                    public void onCompleted(GraphResponse response){

                    }
                }
        ).executeAsync();
    }
}
