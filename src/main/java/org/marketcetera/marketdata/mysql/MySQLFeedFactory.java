package org.marketcetera.marketdata.mysql;

import org.marketcetera.util.misc.ClassVersion;
import org.marketcetera.marketdata.AbstractMarketDataFeedFactory;
import org.marketcetera.marketdata.FeedException;
import org.marketcetera.core.CoreException;
import org.marketcetera.core.NoMoreIDsException;

/**
 * Provides instances of {@link MySQLFeed}.
 * 
 * @author toli kuznets
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @since 2.1.0
 * @version $Id: MySQLFeedFactory.java 16154 2012-07-14 16:34:05Z colin $
 */
@ClassVersion("$Id: MySQLFeedFactory.java 16154 2012-07-14 16:34:05Z colin $")
public class MySQLFeedFactory
        extends AbstractMarketDataFeedFactory<MySQLFeed,MySQLFeedCredentials>
{
    /**
     * Gets an instance of <code>MySQLFeedFactory</code>.
     *
     * @return a <code>MySQLFeedFactory</code> instance
     */
    public static MySQLFeedFactory getInstance()
    {
        return sInstance;
    }
    /**
     * Gets the provider name of <code>MySQLFeed</code>.
     * 
     * @return a <code>String</code> value
     */
	public String getProviderName()
	{
		return provider;
	}
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.IMarketDataFeedFactory#getMarketDataFeed()
     */
    @Override
    public MySQLFeed getMarketDataFeed()
            throws CoreException
    {
        try {
            return MySQLFeed.getInstance(getProviderName());
        } catch (NoMoreIDsException e) {
            throw new FeedException(e);
        }
    }
    /**
     * the singleton instance
     */
    private final static MySQLFeedFactory sInstance = new MySQLFeedFactory();
    /**
     * the provider name
     */
    private final static String provider = "Marketcetera (CSV)"; //$NON-NLS-1$
}
