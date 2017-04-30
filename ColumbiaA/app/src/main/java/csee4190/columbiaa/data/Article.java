package csee4190.columbiaa.data;

import org.json.JSONObject;

/**
 * Created by Tianhua on 2017/4/29.
 */

public class Article implements JasonPopulator {
    private String author;
    private String title;
    private String desp;
    private String imgUrl;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDesp() {
        return desp;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public void populate(JSONObject data) {
        author=data.optString("author");
        title=data.optString("title");
        desp=data.optString("description");
        imgUrl=data.optString("urlToImage");

    }
}
