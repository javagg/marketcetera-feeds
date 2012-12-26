package org.freequant.marketcetera.marketdata.ctp;

import static org.marketcetera.marketdata.Capability.LATEST_TICK;
import static org.marketcetera.marketdata.Capability.TOP_OF_BOOK;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.marketcetera.core.NoMoreIDsException;
import org.marketcetera.core.publisher.ISubscriber;
import org.marketcetera.core.publisher.PublisherEngine;
import org.marketcetera.event.BidEvent;
import org.marketcetera.event.Event;
import org.marketcetera.event.impl.MarketstatEventBuilder;
import org.marketcetera.event.impl.QuoteEventBuilder;
import org.marketcetera.marketdata.AbstractMarketDataFeed;
import org.marketcetera.marketdata.AssetClass;
import org.marketcetera.marketdata.Capability;
import org.marketcetera.marketdata.DataRequestTranslator;
import org.marketcetera.marketdata.DateUtils;
import org.marketcetera.marketdata.FeedException;
import org.marketcetera.marketdata.MarketDataFeedTokenSpec;
import org.marketcetera.marketdata.MarketDataRequest;
import org.marketcetera.trade.Equity;
import org.marketcetera.util.log.SLF4JLoggerProxy;

import org.freequant.CtpMarketDataProvider;
import org.freequant.MarketDataProviderCallback;

public class CtpFeed
		extends
		AbstractMarketDataFeed<CtpFeedToken, CtpFeedCredentials, CtpFeedMessageTranslator, CtpFeedEventTranslator, MarketDataRequest, CtpFeed> {
	static private void loadLib(final String name) throws IOException {
		String suffix = "";
		String osName = System.getProperty("os.name");
		if (osName.substring(0, 7).equals("Windows")) {
			suffix = ".dll";
		} else if (osName.equals("Linux")) {
			suffix = ".so";
		}
		InputStream in = CtpFeedModule.class.getClass()
				.getResource("/" + name + suffix).openStream();
		File lib = File.createTempFile(name, suffix);
		FileOutputStream out = new FileOutputStream(lib);

		int i;
		byte[] buf = new byte[1024];
		while ((i = in.read(buf)) != -1) {
			out.write(buf, 0, i);
		}

		in.close();
		out.close();
		lib.deleteOnExit();
		System.load(lib.toString());
		System.loadLibrary(lib.toString());
	}

	static {
		try {
			loadLib("freequant-java");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final CtpFeedEventTranslator eventTranslator = new CtpFeedEventTranslator();
	private final CtpFeedMessageTranslator messageTranslator = new CtpFeedMessageTranslator();
	private CtpFeedCredentials credentials;

	private CtpMarketDataProvider ctpMdProvider;
	private MdCallback mdCallback;

	@Override
	public String toString() {
		return String.format("CtpFeed");
	}

	/**
	 * 
	 * Create a new <code>AbstractMarketDataFeed</code> instance.
	 * 
	 * @param inFeedType
	 *            a <code>FeedType</code> value
	 * @param inProviderName
	 *            a <code>String</code> value
	 * @throws org.marketcetera.core.NoMoreIDsException
	 *             if no more ids are available to be assigned to this feed
	 * @throws NullPointerException
	 *             if the <code>FeedType</code> is null
	 */
	protected CtpFeed(FeedType inFeedType, String inProviderName)
			throws NoMoreIDsException {
		super(inFeedType, inProviderName);
		mdCallback = new MdCallback(this);
		ctpMdProvider = new CtpMarketDataProvider("", mdCallback);
	}

	@Override
	protected CtpFeedToken generateToken(MarketDataFeedTokenSpec inTokenSpec)
			throws FeedException {
		return new CtpFeedToken(inTokenSpec, this);
	}

	/**
	 * Executes the market data request represented by the passed value.
	 * 
	 * <p>
	 * The values returned in the handle list must be unique with respect to the
	 * current JVM invocation for this data feed.
	 * 
	 * @param inData
	 *            a <code>D</code> value containing the data returned by the
	 *            corresponding {@link DataRequestTranslator}.
	 * @return a <code>List&lt;String&gt;</code> value containing the set of
	 *         handles to be associated with this request
	 * @throws FeedException
	 *             if the request cannot be transmitted to the feed
	 * @see DataRequestTranslator#fromDataRequest(MarketDataRequest)
	 */
	@Override
	protected synchronized List<String> doMarketDataRequest(
			MarketDataRequest inData) throws FeedException {
		List<String> symbols = new ArrayList<String>();
		for (String symbol : inData.getSymbols()) {
			symbols.add(symbol);
			// CsvFeedRequest request = new CsvFeedRequest(symbol,
			// inData);
			// String handle = request.getHandle();
			// handleList.add(handle);
			// requests.put(handle,
			// request);
		}
		SLF4JLoggerProxy.debug(CtpFeed.class,
				"CtpFeed stopping exchange {}...", symbols.toString());
		System.out.println(symbols.toString());
		return symbols;
	}

	@Override
	public synchronized void start() {
		if (isRunning()) {
			throw new IllegalStateException();
		}

		ticker = executor.scheduleAtFixedRate(new Runnable() {
			public void run() {
				List<Event> events = new ArrayList<Event>();

				QuoteEventBuilder<BidEvent> builder = QuoteEventBuilder
						.bidEvent(new Equity("IF1301"));
				builder.withPrice(new BigDecimal(122.0));
				// MarketstatEventBuilder builder =
				// MarketstatEventBuilder.marketstat(new Equity("IF1301"));
				// builder.withOpenPrice(new BigDecimal(122.0))
				// .withHighPrice(new BigDecimal(134.0))
				// .withLowPrice(new BigDecimal(109.0))
				// .withClosePrice(new BigDecimal(123.0));
				events.add(builder.create());

				// eventsToPublish.addAll(settleBook(inBook));
				// produce statistics
				// eventsToPublish.addAll(getStatistics(ExchangeRequestBuilder.newRequest().withInstrument(inBook.getBook().getInstrument())
				// .withUnderlyingInstrument(inBook.getUnderlyingInstrument()).create()));
				// if(inBook.getInstrument() instanceof Equity) {
				// eventsToPublish.addAll(getDividends(ExchangeRequestBuilder.newRequest().withInstrument(inBook.getBook().getInstrument()).create()));
				// }
				// publishEvents(eventsToPublish);
				for (Event event : events) {
					publisher.publish(event);
				}
			}
		}, 0, 1, TimeUnit.SECONDS);

		// for(int i=0;i<EXCHANGE_COUNT;i++) {
		//            SimulatedExchange exchange = new SimulatedExchange(String.format("%s-%d", //$NON-NLS-1$
		// getProviderName(),
		// i+1),
		//                                                               String.format("BGS%d", //$NON-NLS-1$
		// i+1));
		// SLF4JLoggerProxy.debug(BogusFeed.class,
		//                                   "BogusFeed starting exchange {}...", //$NON-NLS-1$
		// exchange.getCode());
		// exchange.start();
		// exchanges.put(exchange.getCode(),
		// exchange);
		// }

		publisher.subscribe(new ISubscriber() {
			public boolean isInteresting(Object inData) {
				return true;
			}

			public void publishTo(Object inData) {
				SLF4JLoggerProxy.debug(CtpFeed.class, "CtpFeed publishTo {}",
						inData);
				dataReceived("IF1301", inData);
			}
		});
		super.start();
	}

	@Override
	public synchronized void stop() {
		if (!isRunning()) {
			throw new IllegalStateException();
		}
		if (ticker != null) {
			ticker.cancel(true);
			executor.purge();
		}
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
		// try {
		// synchronized(this) {
		// setFeedStatus(FeedStatus.OFFLINE);
		// // provider.connect(true);
		// if(!getFeedStatus().equals(FeedStatus.AVAILABLE)) {
		// throw new FeedException(Messages.CONNECT_NOT_START);
		// }
		// // setIsRunning(true);
		// }
		// } catch (Exception e) {
		// return false;
		// }
		return true;
	}

	@Override
	protected void doLogout() {
		mLoggedIn = false;
	}

	@Override
	protected synchronized void doCancel(String inHandle) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}

	@Override
	protected CtpFeedEventTranslator getEventTranslator() {
		return eventTranslator;
	}

	public Set<Capability> getCapabilities() {
		return EnumSet.of(TOP_OF_BOOK, LATEST_TICK);
	}

	public Set<AssetClass> getSupportedAssetClasses() {
		return EnumSet.of(AssetClass.EQUITY, AssetClass.FUTURE);
	}

	private class MdCallback extends MarketDataProviderCallback {
		private CtpFeed ctpFeed;

		public MdCallback(CtpFeed ctpFeed) {
			this.ctpFeed = ctpFeed;
		}

		public void onConnected() {
		};
	}

	public ISubscriber subscriber = null;
	private boolean mLoggedIn = false;
	private static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(
			1);
	private volatile ScheduledFuture<?> ticker = null;
	private static final PublisherEngine publisher = new PublisherEngine(true);
}
