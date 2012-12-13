package org.marketcetera.marketdata.mysql;

import org.marketcetera.core.CoreException;
import org.marketcetera.module.ModuleCreationException;
import org.marketcetera.module.ModuleFactory;
import org.marketcetera.module.ModuleURN;
import org.marketcetera.util.misc.ClassVersion;

import static org.marketcetera.marketdata.mysql.Messages.PROVIDER_DESCRIPTION;

/**
 * <code>ModuleFactory</code> implementation for the <code>MySQLFeed</code> market data provider.
 * 
 * @author toli kuznets
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @since 2.1.0
 * @version $Id: MySQLFeedModuleFactory.java 16154 2012-07-14 16:34:05Z colin $
 */
@ClassVersion("$Id: MySQLFeedModuleFactory.java 16154 2012-07-14 16:34:05Z colin $")
public class MySQLFeedModuleFactory
        extends ModuleFactory
{
    /**
     * Create a new MySQLFeedModuleFactory instance.
     */
    public MySQLFeedModuleFactory()
    {
        super(PROVIDER_URN,
              PROVIDER_DESCRIPTION,
              false,
              false);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.module.ModuleFactory#create(java.lang.Object[])
     */
    @Override
    public MySQLFeedModule create(Object... inArg0)
            throws ModuleCreationException
    {
        try {
            return new MySQLFeedModule();
        } catch (CoreException e) {
            throw new ModuleCreationException(e.getI18NBoundMessage());
        }
    }
    public static final String IDENTIFIER = "mysql";  //$NON-NLS-1$
    /**
     * unique provider URN for the CSV feed market data provider
     */
    public static final ModuleURN PROVIDER_URN = new ModuleURN("metc:mdata:" + IDENTIFIER);  //$NON-NLS-1$
    /**
     * instance URN for the CSV feed market data provider
     */
    public static final ModuleURN INSTANCE_URN = new ModuleURN(PROVIDER_URN,
                                                               "single");  //$NON-NLS-1$
}
