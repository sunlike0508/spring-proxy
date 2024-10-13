package hello.proxy.config.v3_proxyfactory.advice;

import java.lang.reflect.Method;
import java.util.Arrays;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogTraceAdvice implements MethodInterceptor {

    private final LogTrace logTrace;


    public LogTraceAdvice(LogTrace logTrace) {this.logTrace = logTrace;}


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;

        try {
            Method method = invocation.getMethod();

            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "(" + Arrays.toString(
                    method.getParameters()) + ")";
            status = logTrace.begin(message);

            Object invoke = invocation.proceed();

            logTrace.end(status);

            return invoke;
        } catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
