package org.marketcetera.marketdata.ctp;

import org.marketcetera.core.CoreException;
import org.marketcetera.marketdata.AbstractMarketDataFeedFactory;
import org.marketcetera.marketdata.IFeedComponent;

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
