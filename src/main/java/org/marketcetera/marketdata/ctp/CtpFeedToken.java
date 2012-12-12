package org.marketcetera.marketdata.ctp;

import org.marketcetera.marketdata.AbstractMarketDataFeedToken;
import org.marketcetera.marketdata.MarketDataFeedTokenSpec;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 12-12-13
 * Time: 上午1:03
 * To change this template use File | Settings | File Templates.
 */
public class CtpFeedToken extends AbstractMarketDataFeedToken<CtpFeed> {
    protected CtpFeedToken(MarketDataFeedTokenSpec inTokenSpec, CtpFeed inFeed) {
        super(inTokenSpec, inFeed);
    }
}
