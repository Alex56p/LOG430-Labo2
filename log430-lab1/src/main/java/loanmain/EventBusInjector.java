package loanmain;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;

public class EventBusInjector extends AbstractModule
{
    @Override
    protected void configure() {
        bind(EventBus.class).to(EventBus.class);
    }
}


