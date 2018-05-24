package loanmain;

import com.google.common.eventbus.EventBus;

public class EventBusManager
{
    static private EventBus eventBus = new EventBus();

    static public EventBus GetBus()
    {
        return eventBus;
    }
}
