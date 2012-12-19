package org.freequant.marketcetera.marketdata.ctp;

import org.marketcetera.marketdata.AbstractMarketDataFeedToken;
import org.marketcetera.marketdata.MarketDataFeedTokenSpec;

public class CtpFeedToken extends AbstractMarketDataFeedToken<CtpFeed> {
    protected CtpFeedToken(MarketDataFeedTokenSpec inTokenSpec, CtpFeed inFeed) {
        super(inTokenSpec, inFeed);
    }
}
