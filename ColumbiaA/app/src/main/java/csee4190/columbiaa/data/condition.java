package csee4190.columbiaa.data;

import org.json.JSONObject;

/**
 * Created by Tianhua on 2017/4/28.
 */

public class condition implements JasonPopulator{

    private int code;
    private int temperature;
    private String desp;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDesp() {
        return desp;
    }

    @Override
    public void populate(JSONObject data) {
        code=data.optInt("code");
        temperature=data.optInt("temp");
        desp=data.optString("text");
    }
}
