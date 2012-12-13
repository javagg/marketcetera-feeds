package org.marketcetera.marketdata.ctp;

import org.marketcetera.util.log.I18NBoundMessage;
import org.marketcetera.util.log.I18NLoggerProxy;
import org.marketcetera.util.log.I18NMessage0P;
import org.marketcetera.util.log.I18NMessageProvider;


/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 12-12-13
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
public interface Messages {
    static final I18NMessageProvider PROVIDER = new I18NMessageProvider("ctp", Messages.class.getClassLoader());  //$NON-NLS-1$
    static final I18NLoggerProxy LOGGER = new I18NLoggerProxy(PROVIDER);
    public static final I18NMessage0P CONNECT_NOT_START = new I18NMessage0P(LOGGER, "not start");
}
