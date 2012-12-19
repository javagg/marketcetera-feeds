package org.freequant.marketcetera.ctp;

import org.marketcetera.event.AskEvent;
import org.marketcetera.event.BidEvent;
import org.marketcetera.event.TradeEvent;
import org.marketcetera.marketdata.Content;
import org.marketcetera.marketdata.MarketDataRequest;
import org.marketcetera.marketdata.MarketDataRequestBuilder;
import org.marketcetera.strategy.java.Strategy;

public class CtpReceiver extends Strategy {
    private static final String SYMBOLS = "IF1301,IF1302";
    private static final String MARKET_DATA_PROVIDER = "ctp"; 

    @Override
    public void onStart() {
//        warn("Hello World!");
    	MarketDataRequestBuilder builder = MarketDataRequestBuilder.newRequest();
    	MarketDataRequest request = builder.withSymbols(SYMBOLS).withProvider(MARKET_DATA_PROVIDER).withContent(Content.TOP_OF_BOOK).create();
    	int handle = requestMarketData(request);
        info("Issued Market Data Request " + handle);
    }

    @Override
    public void onAsk(AskEvent inAsk) {
        warn("Ask " + inAsk);
    }

    @Override
    public void onBid(BidEvent inBid) {
        warn("Bid " + inBid);
    }
    
    @Override
    public void onTrade(TradeEvent inTrade) {
        warn("Trade " + inTrade);
    }
    
    @Override
    public void onStop() {
        warn("Good Bye!");
    }
}

