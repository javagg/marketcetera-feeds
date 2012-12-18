package org.marketcetera.marketdata.ctp;

import java.util.ArrayList;
import java.util.List;

import org.marketcetera.core.CoreException;
import org.marketcetera.event.Event;
import org.marketcetera.event.EventTranslator;

public class CtpFeedEventTranslator implements EventTranslator {
    @Override
    public List<Event> toEvent(Object inData, String inHandle) throws CoreException {
    	List<Event> events = new ArrayList<Event>();
    	return events;
    }

    @Override
    public Object fromEvent(Event inEvent) throws CoreException {
    	Object object = new Object();
        return object;
    }
}
