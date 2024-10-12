package hello.proxy.config.v2_dynamicproxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns;

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = patterns;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;

        if(!PatternMatchUtils.simpleMatch(patterns, method.getName())) {

            return method.invoke(target, args);
        }

        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "(" + Arrays.toString(
                    args) + ")";
            status = logTrace.begin(message);

            Object invoke = method.invoke(target, args);

            logTrace.end(status);

            return invoke;
        } catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
