package loanmain;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class EventInterceptor implements MethodInterceptor {

    private LoanItem item;

    public EventInterceptor(LoanItem item) {
        this.item = item;
    }

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("EventInterceptor -> " + methodInvocation.getMethod().toString());

        LoanChangeEvent event = new LoanChangeEvent(item);
        EventBusManager.GetBus().post(event);

        return methodInvocation.proceed();
    }
}
