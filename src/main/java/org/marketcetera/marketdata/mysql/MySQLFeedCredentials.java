package org.marketcetera.marketdata.mysql;

import org.apache.commons.lang.Validate;
import org.marketcetera.marketdata.AbstractMarketDataFeedCredentials;
import org.marketcetera.marketdata.FeedException;
import org.marketcetera.util.log.I18NBoundMessage1P;
import org.marketcetera.util.log.SLF4JLoggerProxy;
import org.marketcetera.util.misc.ClassVersion;

import java.io.File;

import static org.marketcetera.marketdata.mysql.Messages.INVALID_EVENT_TRANSLATOR;

/**
 * Encapsulates the data necessary to initialize an instance of {@link MySQLFeed}.
 * 
 * @author toli kuznets
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @since 2.1.0
 * @version $Id: MySQLFeedCredentials.java 16154 2012-07-14 16:34:05Z colin $
 */
@ClassVersion("$Id: MySQLFeedCredentials.java 16154 2012-07-14 16:34:05Z colin $")
public final class MySQLFeedCredentials
	    extends AbstractMarketDataFeedCredentials
{
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("MySQLFeedCredentials [eventTranslator=%s, millisecondDelay=%s]", //$NON-NLS-1$
                             eventTranslator,
                             replayRate);
    }
    /**
     * Retrieves an instance of <code>MySQLFeedCredentials</code>.
     * 
     * @param inReplayRate a <code>long</code> value containing the rate at which to replay marketdata
     * @param inReplayEvents a <code>boolean</code> value indicating whether to replay events upon completion
     * @param inMarketdataDirectory a <code>String</code> value containing the marketdata files
     * @param inEventTranslatorClassname a <code>String</code> value containing the fully-qualified name of the event translator class
     * @return a <code>MySQLFeedCredentials</code> value
     * @throws FeedException if an error occurs while retrieving the credentials object
     */
    static MySQLFeedCredentials getInstance(long inReplayRate,
                                          boolean inReplayEvents,
                                          String inMarketdataDirectory,
                                          String inEventTranslatorClassname)
            throws FeedException
    {
        SLF4JLoggerProxy.debug(MySQLFeedCredentials.class,
                               "Creating credentials at a replay rate of {}, replay events value of {}, marketdata directory {}, and event translator classname {}", //$NON-NLS-1$
                               inReplayRate,
                               inReplayEvents,
                               inMarketdataDirectory,
                               inEventTranslatorClassname);
        try {
            return new MySQLFeedCredentials(inReplayRate,
                                          inReplayEvents,
                                          inMarketdataDirectory,
                                          inEventTranslatorClassname);
        } catch (FeedException e) {
            throw e;
        } catch (Exception e) {
            INVALID_EVENT_TRANSLATOR.error(MySQLFeedCredentials.class,
                                           e,
                                           inEventTranslatorClassname);
            throw new FeedException(e,
                                    new I18NBoundMessage1P(INVALID_EVENT_TRANSLATOR,
                                                           inEventTranslatorClassname));
        }
    }
    /**
     * Retrieves an instance of <code>MySQLFeedCredentials</code>.
     * 
     * @param inReplayRate a <code>long</code> value containing the rate at which to replay marketdata
     * @param inReplayEvents a <code>boolean</code> value indicating whether to replay events upon completion
     * @param inMarketdataDirectory a <code>String</code> value containing the marketdata files
     * @return a <code>MySQLFeedCredentials</code> value
     * @throws FeedException if an error occurs while retrieving the credentials object
     */
    static MySQLFeedCredentials getInstance(long inReplayRate,
                                          boolean inReplayEvents,
                                          String inMarketdataDirectory,
                                          MySQLFeedEventTranslator inEventTranslator)
            throws FeedException
    {
        SLF4JLoggerProxy.debug(MySQLFeedCredentials.class,
                               "Creating credentials at a replay rate of {}, replay events value of {}, marketdata directory {}, and event translator classname {}", //$NON-NLS-1$
                               inReplayRate,
                               inReplayEvents,
                               inMarketdataDirectory,
                               inEventTranslator);
        try {
            return new MySQLFeedCredentials(inReplayRate,
                                          inReplayEvents,
                                          inMarketdataDirectory,
                                          inEventTranslator);
        } catch (Exception e) {
            INVALID_EVENT_TRANSLATOR.error(MySQLFeedCredentials.class,
                                           e,
                                           inEventTranslator);
            throw new FeedException(e,
                                    new I18NBoundMessage1P(INVALID_EVENT_TRANSLATOR,
                                                           String.valueOf(inEventTranslator)));
        }
    }
    /**
     * Get the marketdataDirectory value.
     *
     * @return a <code>File</code> value
     */
    public File getMarketdataDirectory()
    {
        return marketdataDirectory;
    }
    /**
     * Get the replayRate value.
     *
     * @return a <code>long</code> value
     */
    public long getReplayRate()
    {
        return replayRate;
    }
    /**
     * Get the eventTranslator value.
     *
     * @return a <code>MySQLFeedEventTranslator</code> value
     */
    public MySQLFeedEventTranslator getEventTranslator()
    {
        return eventTranslator;
    }
    /**
     * Get the replayEvents value.
     *
     * @return a <code>boolean</code> value
     */
    public boolean getReplayEvents()
    {
        return replayEvents;
    }
    /**
     * Creates a new <code>MySQLFeedCredentials</code> instance.
     * 
     * @param inReplayRate a <code>long</code> value containing the rate at which to replay marketdata
     * @param inReplayEvents a <code>boolean</code> value indicating whether to replay events upon completion
     * @param inMarketdataDirectory a <code>String</code> value containing the marketdata files
     * @param inEventTranslatorClassname a <code>String</code> value containing the fully-qualified name of the event translator class
     * @throws ClassNotFoundException if the given classname does not exist in the classpath 
     * @throws IllegalAccessException if the class referred to by the classname is not accessible
     * @throws InstantiationException if the class referred to by the classname cannot be instantiated
     * @throws FeedException if the given delay is invalid 
     */
	private MySQLFeedCredentials(long inReplayRate,
                                 boolean inReplayEvents,
                                 String inMarketdataDirectory,
                                 String inEventTranslatorClassname)
	        throws InstantiationException, IllegalAccessException, ClassNotFoundException, FeedException
	{
        this(inReplayRate,
             inReplayEvents,
             inMarketdataDirectory,
             (MySQLFeedEventTranslator)Class.forName(inEventTranslatorClassname).newInstance());
	}
    /**
     * Creates a new <code>MySQLFeedCredentials</code> instance.
     * 
     * @param inReplayRate a <code>long</code> value containing the rate at which to replay marketdata
     * @param inReplayEvents a <code>boolean</code> value indicating whether to replay events upon completion
     * @param inMarketdataDirectory a <code>String</code> value containing the marketdata files
     * @param inEventTranslatorClassname a <code>String</code> value containing the fully-qualified name of the event translator class
     * @throws FeedException if an error occurs while constructing the credentials object
     */
    private MySQLFeedCredentials(long inReplayRate,
                                 boolean inReplayEvents,
                                 String inMarketdataDirectory,
                                 MySQLFeedEventTranslator inEventTranslator)
            throws FeedException 
    {
        if(inEventTranslator == null) {
            throw new NullPointerException();
        }
        replayRate = inReplayRate;
        replayEvents = inReplayEvents;
        marketdataDirectory = new File(inMarketdataDirectory);
        Validate.isTrue(marketdataDirectory.exists(),
                        "Marketdata directory does not exist");
        Validate.isTrue(marketdataDirectory.canRead(),
                        "Marketdata directory is not readable");
        eventTranslator = inEventTranslator;
    }
    /**
     * the directory in which to find marketdata
     */
    private final File marketdataDirectory;
    /**
     * number of milliseconds to delay between events
     */
    private final long replayRate;
    /**
     * indicates whether to replay events upon completion
     */
    private final boolean replayEvents;
    /**
     * the event translator to use 
     */
    private final MySQLFeedEventTranslator eventTranslator;
}
