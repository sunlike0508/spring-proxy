package hello.proxy.jdkdynamic;


import java.lang.reflect.Proxy;
import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.jdkdynamic.code.AInterface;
import hello.proxy.jdkdynamic.code.BImpl;
import hello.proxy.jdkdynamic.code.BInterface;
import hello.proxy.jdkdynamic.code.TimeInvocationHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicProxy() {
        AInterface target = new AImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[] {AInterface.class}, handler);

        proxy.call();

        log.info("targetCalls {}", target.getClass());
        log.info("proxyCalls {}", proxy.getClass());
    }

    @Test
    void dynamicProxy2() {
        BInterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[] {BInterface.class}, handler);

        proxy.call();

        log.info("targetCalls {}", target.getClass());
        log.info("proxyCalls {}", proxy.getClass());
    }
}
