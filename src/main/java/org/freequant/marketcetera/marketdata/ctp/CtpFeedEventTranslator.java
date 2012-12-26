package org.freequant.marketcetera.marketdata.ctp;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.marketcetera.core.CoreException;
import org.marketcetera.event.Event;
import org.marketcetera.event.EventTranslator;
import org.marketcetera.event.UnsupportedEventException;
import org.marketcetera.util.log.I18NBoundMessage1P;

public class CtpFeedEventTranslator implements EventTranslator {
    public List<Event> toEvent(Object inData, String inHandle) throws CoreException {
        if (!(inData instanceof Event)) {
            throw new UnsupportedEventException(new I18NBoundMessage1P(Messages.UNKNOWN_EVENT_TYPE, 
            	ObjectUtils.toString(inData, null)));
        }
        Event event = (Event)inData;
        return Arrays.asList(new Event[] { event });
    }

    public Object fromEvent(Event inEvent) throws CoreException {
    	Object object = new Object();
        return object;
    }
}
