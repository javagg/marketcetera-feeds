package org.marketcetera.marketdata.mysql;

import org.marketcetera.core.CoreException;
import org.marketcetera.marketdata.DataRequestTranslator;
import org.marketcetera.marketdata.MarketDataRequest;
import org.marketcetera.util.log.I18NBoundMessage1P;
import org.marketcetera.util.misc.ClassVersion;

import static org.marketcetera.marketdata.Content.*;
import static org.marketcetera.marketdata.Messages.UNSUPPORTED_REQUEST;

/**
 * Translates {@link MarketDataRequest} objects to a format that the {@link MySQLFeed} can understand.
 * 
 * @author toli kuznets
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @since 2.1.0
 * @version $Id: MySQLFeedMessageTranslator.java 16154 2012-07-14 16:34:05Z colin $
 */
@ClassVersion("$Id: MySQLFeedMessageTranslator.java 16154 2012-07-14 16:34:05Z colin $")
public class MySQLFeedMessageTranslator
        implements DataRequestTranslator<MarketDataRequest>
{
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.DataRequestTranslator#translate(org.marketcetera.marketdata.DataRequest)
     */
    @Override
    public MarketDataRequest fromDataRequest(MarketDataRequest inRequest)
            throws CoreException
    {
        if(inRequest.validateWithCapabilities(TOP_OF_BOOK,LATEST_TICK,DIVIDEND,MARKET_STAT)) {
            return inRequest;
        }
        throw new CoreException(new I18NBoundMessage1P(UNSUPPORTED_REQUEST,
                                                       String.valueOf(inRequest.getContent())));
    }
    /**
     * Gets a <code>MySQLFeedMessageTranslator</code> instance.
     * 
     * @return a <code>MySQLFeedMessageTranslator</code> value
     */
    static MySQLFeedMessageTranslator getInstance()
    {
        return sInstance;
    }
    /**
     * Create a new MySQLFeedMessageTranslator instance.
     *
     */
    private MySQLFeedMessageTranslator()
    {
    }
    /**
     * static instance
     */
    private static final MySQLFeedMessageTranslator sInstance = new MySQLFeedMessageTranslator();
}
