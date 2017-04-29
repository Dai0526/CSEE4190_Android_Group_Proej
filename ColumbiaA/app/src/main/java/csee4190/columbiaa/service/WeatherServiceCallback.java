package csee4190.columbiaa.service;


import csee4190.columbiaa.data.channel;

/**
 * Created by Tianhua on 2017/4/28.
 */

public interface WeatherServiceCallback {
    void serviceSuccess(channel channel);

    void serviceFailure(Exception exception);
}
