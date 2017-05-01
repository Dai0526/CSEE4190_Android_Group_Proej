package csee4190.columbiaa.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Tianhua on 2017/4/28.
 */

public class item implements JasonPopulator{
    public condition getCond() {
        return cond;
    }

    public String getTitle() {
        return title;
    }


    private String title;
    private condition cond;
    private Forecast fc;


    public Forecast getFc() {
        return fc;
    }

    public void populate(JSONObject data) {
        cond=new condition();
        cond.populate(data.optJSONObject("condition"));

        title=data.optString("title");


        JSONArray ja=data.optJSONArray("forecast");
        fc=new Forecast();
        try {
            fc.populate(ja.getJSONObject(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
