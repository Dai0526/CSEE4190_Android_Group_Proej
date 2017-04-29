package csee4190.columbiaa.data;

import org.json.JSONObject;

/**
 * Created by Tianhua on 2017/4/28.
 */

public class unit implements JasonPopulator{

    private String temp;

    public String getTemp() {
        return temp;
    }

    @Override
    public void populate(JSONObject data) {
        temp=data.optString("temperature");
    }
}
