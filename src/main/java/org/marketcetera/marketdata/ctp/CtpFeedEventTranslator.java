package org.marketcetera.marketdata.ctp;

import java.util.List;

import org.marketcetera.core.CoreException;
import org.marketcetera.event.Event;
import org.marketcetera.event.EventTranslator;

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
