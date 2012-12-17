package org.marketcetera.marketdata.ctp;

import org.marketcetera.core.CoreException;
import org.marketcetera.marketdata.DataRequestTranslator;
import org.marketcetera.marketdata.MarketDataRequest;

public class CtpFeedMessageTranslator implements DataRequestTranslator<MarketDataRequest> {
    public MarketDataRequest fromDataRequest(MarketDataRequest inRequest) throws CoreException {
        return inRequest;
    }
}
