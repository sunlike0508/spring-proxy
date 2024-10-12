package hello.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ReflectionTest {

    @Test
    void reflection() {
        Hello target = new Hello();

        log.info("start");
        String result = target.callA();
        log.info("result:{}", result);

        log.info("start");
        String result2 = target.callB();
        log.info("result2:{}", result2);

    }

    @Test
    void reflection1() throws Exception{
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");

        Object result1 = methodCallA.invoke(target);

        log.info("result1:{}", result1);

        Method methodCalB = classHello.getMethod("callB");

        Object result2 = methodCalB.invoke(target);

        log.info("result2:{}", result2);
    }

    @Test
    void reflection2() throws Exception{
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        Method methodCalB = classHello.getMethod("callB");
        dynamicCall(methodCalB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {

        Object result = method.invoke(target);

        log.info("result:{}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "helloA";
        }

        public String callB() {
            log.info("callB");
            return "helloB";
        }

    }
}
