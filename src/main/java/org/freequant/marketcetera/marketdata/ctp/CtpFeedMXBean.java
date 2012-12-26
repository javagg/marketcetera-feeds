package org.freequant.marketcetera.marketdata.ctp;

import javax.management.MXBean;

import org.marketcetera.marketdata.AbstractMarketDataModuleMXBean;
import org.marketcetera.module.DisplayName;
import org.marketcetera.util.misc.ClassVersion;

@MXBean(true)
@DisplayName("Management Interface for Ctp Market Data Adapter")
@ClassVersion("$Id: CtpFeedMXBean.java 16154 2012-07-14 16:34:05Z colin $")
public interface CtpFeedMXBean extends AbstractMarketDataModuleMXBean {
    @DisplayName("User's Password")
    public String getPassword();
    public void setPassword(String inPassword);
}
