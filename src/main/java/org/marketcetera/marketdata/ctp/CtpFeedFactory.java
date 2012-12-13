package org.marketcetera.marketdata.ctp;

import org.marketcetera.core.CoreException;
import org.marketcetera.marketdata.AbstractMarketDataFeedFactory;
import org.marketcetera.marketdata.IFeedComponent;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 12-12-13
 * Time: 下午2:05
 * To change this template use File | Settings | File Templates.
 */
public class CtpFeedFactory extends AbstractMarketDataFeedFactory<CtpFeed, CtpFeedCredentials> {
    @Override
    public CtpFeed getMarketDataFeed() throws CoreException {
        return new CtpFeed(IFeedComponent.FeedType.LIVE, getProviderName());
    }

    @Override
    public String getProviderName() {
        return "Ctp";
     }
}
