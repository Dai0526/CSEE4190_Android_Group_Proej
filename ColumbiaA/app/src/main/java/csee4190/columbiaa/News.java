package csee4190.columbiaa;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import csee4190.columbiaa.data.Article;
import csee4190.columbiaa.service.NewsService;
import csee4190.columbiaa.service.NewsServiceCallback;

public class News extends AppCompatActivity implements NewsServiceCallback {


    private TextView au;
    private TextView title;
    private TextView desc;
    private ImageView img;

    private NewsService service;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        au=(TextView)findViewById(R.id.au_text);
        title=(TextView)findViewById(R.id.titleText);
        desc=(TextView)findViewById(R.id.despText);
        img=(ImageView)findViewById(R.id.newsImg);

        service = new NewsService(this);
        dialog = new ProgressDialog(this);

        dialog.setMessage("Loading...");
        dialog.show();
        service.refereshNews();

    }


    @Override
    public void serviceSuccess(Article a) {
        dialog.hide();
        String imageLink=a.getImgUrl();
        Picasso.with(this).load(imageLink).into(img);
        au.setText(a.getAuthor());
        title.setText(a.getTitle());
        desc.setText(a.getDesp());


    }

    @Override
    public void serviceFailure(Exception e) {
        dialog.hide();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}
