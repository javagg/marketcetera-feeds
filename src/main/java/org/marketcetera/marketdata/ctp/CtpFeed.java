package org.marketcetera.marketdata.ctp;

import static org.marketcetera.marketdata.AssetClass.FUTURE;
import static org.marketcetera.marketdata.Capability.LATEST_TICK;
import static org.marketcetera.marketdata.Capability.TOP_OF_BOOK;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.marketcetera.core.NoMoreIDsException;
import org.marketcetera.marketdata.AbstractMarketDataFeed;
import org.marketcetera.marketdata.AssetClass;
import org.marketcetera.marketdata.Capability;
import org.marketcetera.marketdata.DataRequestTranslator;
import org.marketcetera.marketdata.FeedException;
import org.marketcetera.marketdata.FeedStatus;
import org.marketcetera.marketdata.MarketDataFeedTokenSpec;
import org.marketcetera.marketdata.MarketDataRequest;

public class CtpFeed extends AbstractMarketDataFeed<CtpFeedToken, CtpFeedCredentials,
        CtpFeedMessageTranslator, CtpFeedEventTranslator, MarketDataRequest, CtpFeed> {
    private final CtpFeedEventTranslator eventTranslator;
    private final CtpFeedMessageTranslator messageTranslator;
    private CtpFeedCredentials credentials;

    /**
     *
     * Create a new <code>AbstractMarketDataFeed</code> instance.
     *
     * @param inFeedType     a <code>FeedType</code> value
     * @param inProviderName a <code>String</code> value
     * @throws org.marketcetera.core.NoMoreIDsException
     *                                        if no more ids are available to be assigned to this feed
     * @throws NullPointerException if the <code>FeedType</code> is null
     */
    protected CtpFeed(FeedType inFeedType, String inProviderName) throws NoMoreIDsException {
        super(inFeedType, inProviderName);
        eventTranslator = null;
        messageTranslator = null;
    }

    @Override
    protected CtpFeedToken generateToken(MarketDataFeedTokenSpec inTokenSpec) throws FeedException {
        return new CtpFeedToken(inTokenSpec, this);
    }

    /**
     * Executes the market data request represented by the passed value.
     *
     * <p>The values returned in the handle list must be unique with respect
     * to the current JVM invocation for this data feed.
     *
     * @param inData a <code>D</code> value containing the data returned by
     *   the corresponding {@link DataRequestTranslator}.
     * @return a <code>List&lt;String&gt;</code> value containing the set of
     *   handles to be associated with this request
     * @throws FeedException if the request cannot be transmitted to the feed
     * @see DataRequestTranslator#fromDataRequest(MarketDataRequest)
     */
    @Override
    protected synchronized List<String> doMarketDataRequest(MarketDataRequest inData) throws FeedException {
        List<String> symbols = new ArrayList<String>();
        for(String symbol : inData.getSymbols()) {
            symbols.add(symbol);
//            CsvFeedRequest request = new CsvFeedRequest(symbol,
//                    inData);
//            String handle = request.getHandle();
//            handleList.add(handle);
//            requests.put(handle,
//                    request);
        }
        return symbols;
    }

    @Override
    protected CtpFeedMessageTranslator getMessageTranslator() {
        return messageTranslator;
    }

    @Override
    protected synchronized boolean isLoggedIn() {
        return false;
    }

    @Override
    protected boolean doLogin(CtpFeedCredentials inCredentials) {
        credentials = inCredentials;
        try {
            synchronized(this) {
                setFeedStatus(FeedStatus.OFFLINE);
//                provider.connect(true);
                if(!getFeedStatus().equals(FeedStatus.AVAILABLE)) {
                    throw new FeedException(Messages.CONNECT_NOT_START);
                }
//                setIsRunning(true);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    protected void doLogout() {
//        provider.disconnect(true);
    }

    @Override
    protected synchronized void doCancel(String inHandle) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected CtpFeedEventTranslator getEventTranslator() {
        return eventTranslator;
    }

    @Override
    public Set<Capability> getCapabilities() {
        return EnumSet.of(TOP_OF_BOOK,LATEST_TICK);
    }

    @Override
    public Set<AssetClass> getSupportedAssetClasses() {
        return EnumSet.of(FUTURE);
    }
}
