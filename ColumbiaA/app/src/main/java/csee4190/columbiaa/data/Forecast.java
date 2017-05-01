package csee4190.columbiaa.data;

import org.json.JSONObject;

/**
 * Created by Tianhua on 2017/4/30.
 */

public class Forecast implements JasonPopulator{

    private String day;
    private String high;
    private String low;
    private String text;

    public String getDay() {
        return day;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getText() {
        return text;
    }

    public void populate(JSONObject data) {
        day=data.optString("date");
        high=data.optString("high");
        low=data.optString("low");
        text=data.optString("text");
    }

}
