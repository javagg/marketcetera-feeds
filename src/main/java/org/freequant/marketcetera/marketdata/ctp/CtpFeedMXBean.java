package org.freequant.marketcetera.marketdata.ctp;

import javax.management.MXBean;

import org.marketcetera.marketdata.AbstractMarketDataModuleMXBean;
import org.marketcetera.module.DisplayName;
import org.marketcetera.util.misc.ClassVersion;

/**
 * Exposes {@link CSVFeedCredentials} attributes.
 * 
 * @author toli kuznets
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @since 2.1.0
 * @version $Id: CSVFeedMXBean.java 16154 2012-07-14 16:34:05Z colin $
 */
@MXBean(true)
@DisplayName("Management Interface for CSV Market Data Adapter")
@ClassVersion("$Id: CSVFeedMXBean.java 16154 2012-07-14 16:34:05Z colin $")
public interface CtpFeedMXBean
        extends AbstractMarketDataModuleMXBean
{
    @DisplayName("User's Password")
    public String getPassword();
    public void setPassword(String inPassword);
}
