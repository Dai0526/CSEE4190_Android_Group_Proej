package csee4190.columbiaa.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tianhua on 2017/4/28.
 */

public class channel implements JasonPopulator{
    private unit units;
    private item items;

    public unit getUnits() {
        return units;
    }

    public item getItems() {
        return items;
    }



    @Override
    public void populate(JSONObject data) {
        units=new unit();
        units.populate(data.optJSONObject("units"));

        items = new item();
        items.populate(data.optJSONObject("item"));

    }
}
