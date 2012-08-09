package org.marketcetera.marketdata.mysql;

import org.marketcetera.core.ClassVersion;
import org.marketcetera.marketdata.AbstractMarketDataFeedToken;
import org.marketcetera.marketdata.MarketDataFeedTokenSpec;

/**
 * Token for {@link MySQLFeed}.
 * Dummy implementation, we are not doing anything clever in our subscriptions.
 *
 * @author <a href="mailto:toli@marketcetera.com">Toli Kuznets</a>
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @since 2.1.0
 * @version $Id: MySQLFeedToken.java 16154 2012-07-14 16:34:05Z colin $
 */
@ClassVersion("$Id: MySQLFeedToken.java 16154 2012-07-14 16:34:05Z colin $")
public class MySQLFeedToken
        extends AbstractMarketDataFeedToken<MySQLFeed>
{
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("MySQLFeedToken [%s]", //$NON-NLS-1$
                             getStatus());
    }
    /**
     * Gets a <code>MySQLFeedToken</code> with the given attributes.
     *
     * @param inTokenSpec a <code>MarketDataFeedTokenSpec</code> value
     * @param inFeed a <code>MySQLFeed</code> value
     * @return a <code>MySQLFeedToken</code> value
     */
    static MySQLFeedToken getToken(MarketDataFeedTokenSpec inTokenSpec,
                                   MySQLFeed inFeed)
    {
        return new MySQLFeedToken(inTokenSpec,
                                  inFeed);
    }
    /**
     * Create a new <code>MySQLFeedToken</code> instance.
     *
     * @param inTokenSpec a <code>MarketDataFeedTokenSpec</code> value
     * @param inFeed a <code>MySQLFeed</code> value
     */
    private MySQLFeedToken(MarketDataFeedTokenSpec inTokenSpec,
                           MySQLFeed inFeed)
    {
        super(inTokenSpec,
              inFeed);
    }
}
