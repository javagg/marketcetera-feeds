package org.freequant.marketcetera.marketdata.ctp;

import org.marketcetera.util.log.I18NLoggerProxy;
import org.marketcetera.util.log.I18NMessage0P;
import org.marketcetera.util.log.I18NMessage1P;
import org.marketcetera.util.log.I18NMessageProvider;

public interface Messages {
    static final I18NMessageProvider PROVIDER = new I18NMessageProvider("ctp", Messages.class.getClassLoader());
    static final I18NLoggerProxy LOGGER = new I18NLoggerProxy(PROVIDER);
    public static final I18NMessage0P CONNECT_NOT_START = new I18NMessage0P(LOGGER, "not start");
    public static final I18NMessage1P UNKNOWN_EVENT_TYPE = new I18NMessage1P(LOGGER, "unknown_event_type");
    public static final I18NMessage0P PROVIDER_DESCRIPTION = new I18NMessage0P(LOGGER, "provider_description");
}
