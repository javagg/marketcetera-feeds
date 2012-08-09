package org.marketcetera.marketdata.mysql;

import org.apache.commons.lang.StringUtils;
import org.marketcetera.core.CoreException;
import org.marketcetera.marketdata.AbstractMarketDataModule;
import org.marketcetera.util.misc.ClassVersion;

/**
 * StrategyAgent module for {@link MySQLFeed}.
 * 
 * @author toli kuznets
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @since 2.1.0
 * @version $Id: MySQLFeedModule.java 16154 2012-07-14 16:34:05Z colin $
 */
@ClassVersion("$Id: MySQLFeedModule.java 16154 2012-07-14 16:34:05Z colin $")
public class MySQLFeedModule
        extends AbstractMarketDataModule<MySQLFeedToken,
        MySQLFeedCredentials>
        implements MySQLFeedMXBean
{
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.mysql.MySQLFeedMXBean#getReplayRate()
     */
    @Override
    public String getReplayRate()
    {
        return String.valueOf(replayRate);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.mysql.MySQLFeedMXBean#setReplayRate(double)
     */
    @Override
    public void setReplayRate(String inReplayRate)
    {
        replayRate = Long.parseLong(StringUtils.trimToNull(inReplayRate));
    }
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.mysql.MySQLFeedMXBean#getMarketdataDirectory()
     */
    @Override
    public String getMarketdataDirectory()
    {
        return marketdataDirectory;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.mysql.MySQLFeedMXBean#setMarketdataDirectory(java.lang.String)
     */
    @Override
    public void setMarketdataDirectory(String inDirectory)
    {
        marketdataDirectory = StringUtils.trimToNull(inDirectory);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.mysql.MySQLFeedMXBean#getEventTranslatorClassName()
     */
    @Override
    public String getEventTranslatorClassName()
    {
        return eventTranslatorClassname;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.mysql.MySQLFeedMXBean#setEventTranslatorClassName(java.lang.String)
     */
    @Override
    public void setEventTranslatorClassName(String inEventTranslatorClassname)
    {
        eventTranslatorClassname = inEventTranslatorClassname;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.mysql.MySQLFeedMXBean#getReplayEvents()
     */
    @Override
    public String getReplayEvents()
    {
        return String.valueOf(replayEvents);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.mysql.MySQLFeedMXBean#setReplayEvents(String)
     */
    @Override
    public void setReplayEvents(String inReplayEvents)
    {
        replayEvents = Boolean.valueOf(StringUtils.trimToNull(inReplayEvents));
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("MySQLFeedModule [translator: %s]", //$NON-NLS-1$
                             eventTranslatorClassname);
    }
    /**
     * Create a new MySQLFeedModule instance.
     * 
     * @throws org.marketcetera.core.CoreException 
     */
    MySQLFeedModule()
            throws CoreException
    {
        super(MySQLFeedModuleFactory.INSTANCE_URN,
              MySQLFeedFactory.getInstance().getMarketDataFeed());
    }
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.AbstractMarketDataModule#getCredentials()
     */
    @Override
    protected MySQLFeedCredentials getCredentials()
        throws CoreException
    {
        return MySQLFeedCredentials.getInstance(replayRate,
                replayEvents,
                marketdataDirectory,
                getEventTranslatorClassName());
    }
    /**
     * the event translator classname to use
     */
    private volatile String eventTranslatorClassname = BasicMySQLFeedEventTranslator.class.getName();
    /**
     * delay between each event
     */
    private volatile long replayRate = 1000;
    /**
     * the directory in which to find marketdata
     */
    private volatile String marketdataDirectory;
    /**
     * indicates if events should be replayed upon completion
     */
    private volatile boolean replayEvents = false;
}
