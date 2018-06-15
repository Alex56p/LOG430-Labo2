package loanmain;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingInterceptor implements MethodInterceptor {
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("LoggingInterceptor -> " + methodInvocation.getMethod().toString());

        return methodInvocation.proceed();
    }
}
