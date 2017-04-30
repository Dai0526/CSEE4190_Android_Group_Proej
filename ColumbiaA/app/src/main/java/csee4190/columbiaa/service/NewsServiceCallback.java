package csee4190.columbiaa.service;

/**
 * Created by Tianhua on 2017/4/29.
 */
import csee4190.columbiaa.data.Article;

public interface NewsServiceCallback {
    void serviceSuccess(Article a);
    void serviceFailure(Exception e);

}
