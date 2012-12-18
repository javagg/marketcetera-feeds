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
import org.marketcetera.util.log.SLF4JLoggerProxy;

public class CtpFeed extends AbstractMarketDataFeed<CtpFeedToken, CtpFeedCredentials,
        CtpFeedMessageTranslator, CtpFeedEventTranslator, MarketDataRequest, CtpFeed> {
    private final CtpFeedEventTranslator eventTranslator = new CtpFeedEventTranslator();
    private final CtpFeedMessageTranslator messageTranslator = new CtpFeedMessageTranslator();
    private CtpFeedCredentials credentials;
    
    private Object ctpMdProvider = null;
    
    @Override
    public String toString() {
        return String.format("CtpFeed");
    }
    
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
        SLF4JLoggerProxy.debug(CtpFeed.class, "CtpFeed stopping exchange {}..." , symbols.toString());
        System.out.println(symbols.toString());
        return symbols;
    }

    @Override
	public synchronized void start() {
        if(isRunning()) {
            throw new IllegalStateException();
        }
//        for(int i=0;i<EXCHANGE_COUNT;i++) {
//            SimulatedExchange exchange = new SimulatedExchange(String.format("%s-%d", //$NON-NLS-1$
//                                                                             getProviderName(),
//                                                                             i+1),
//                                                               String.format("BGS%d", //$NON-NLS-1$
//                                                                             i+1));
//            SLF4JLoggerProxy.debug(BogusFeed.class,
//                                   "BogusFeed starting exchange {}...", //$NON-NLS-1$
//                                   exchange.getCode());
//            exchange.start();
//            exchanges.put(exchange.getCode(),
//                          exchange);
//        }
        super.start();
	}
    
    @Override
    public synchronized void stop() {
        if(!isRunning()) {
            throw new IllegalStateException();
        }
//        for(SimulatedExchange exchange : exchanges.values()) {
//            SLF4JLoggerProxy.debug(BogusFeed.class,
//                                   "BogusFeed stopping exchange {}...", //$NON-NLS-1$
//                                   exchange.getCode());
//            exchange.stop();
//        }
        super.stop();
    }
    
    @Override
    protected CtpFeedMessageTranslator getMessageTranslator() {
        return messageTranslator;
    }

    @Override
    protected synchronized boolean isLoggedIn() {
        return mLoggedIn;
    }

    @Override
    protected boolean doLogin(CtpFeedCredentials inCredentials) {
        credentials = inCredentials;
        mLoggedIn = true;
//        try {
//            synchronized(this) {
//                setFeedStatus(FeedStatus.OFFLINE);
////                provider.connect(true);
//                if(!getFeedStatus().equals(FeedStatus.AVAILABLE)) {
//                    throw new FeedException(Messages.CONNECT_NOT_START);
//                }
////                setIsRunning(true);
//            }
//        } catch (Exception e) {
//            return false;
//        }
        return true;
    }

    @Override
    protected void doLogout() {
        mLoggedIn = false;
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
        return EnumSet.of(TOP_OF_BOOK, LATEST_TICK);
    }

    @Override
    public Set<AssetClass> getSupportedAssetClasses() {
        return EnumSet.of(AssetClass.EQUITY, AssetClass.FUTURE);
    }
    
    private void dataReceCall() {
    	String inHandle = null;
    	Object inData = null;
    	dataReceived(inHandle, inData);
    }
    
    private boolean mLoggedIn = false;
}
