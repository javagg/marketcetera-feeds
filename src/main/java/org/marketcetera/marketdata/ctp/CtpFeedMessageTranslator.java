package org.marketcetera.marketdata.ctp;

import org.marketcetera.core.CoreException;
import org.marketcetera.marketdata.DataRequestTranslator;
import org.marketcetera.marketdata.MarketDataRequest;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 12-12-13
 * Time: 上午12:58
 * To change this template use File | Settings | File Templates.
 */
public class CtpFeedMessageTranslator implements DataRequestTranslator<MarketDataRequest> {
    public MarketDataRequest fromDataRequest(MarketDataRequest inRequest) throws CoreException {
        return inRequest;
    }
}
