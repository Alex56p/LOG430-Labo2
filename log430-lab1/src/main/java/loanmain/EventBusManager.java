package loanmain;

import com.google.common.eventbus.EventBus;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class EventBusManager
{
    @Inject
    static private EventBus eventBus = new EventBus();

    static private EventBus inject()
    {
        Injector injector = Guice.createInjector(new EventBusInjector());
        return injector.getInstance(EventBus.class);
    }

    static public EventBus GetBus()
    {
        if (eventBus == null)
        {
            eventBus = inject();
        }

        return eventBus;
    }
}
