package org.marketcetera.marketdata.ctp;

import org.marketcetera.core.CoreException;
import org.marketcetera.event.Event;
import org.marketcetera.event.EventTranslator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 12-12-13
 * Time: 上午1:06
 * To change this template use File | Settings | File Templates.
 */
public class CtpFeedEventTranslator implements EventTranslator {
    @Override
    public List<Event> toEvent(Object inData, String inHandle) throws CoreException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object fromEvent(Event inEvent) throws CoreException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
