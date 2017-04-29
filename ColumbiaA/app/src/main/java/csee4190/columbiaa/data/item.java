package csee4190.columbiaa.data;

import org.json.JSONObject;

/**
 * Created by Tianhua on 2017/4/28.
 */

public class item implements JasonPopulator{
    public condition getCond() {
        return cond;
    }

    private condition cond;
    public void populate(JSONObject data) {
        cond=new condition();
        cond.populate(data.optJSONObject("condition"));
    }
}
