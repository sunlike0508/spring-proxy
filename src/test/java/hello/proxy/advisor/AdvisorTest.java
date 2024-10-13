package hello.proxy.advisor;


import java.lang.reflect.Method;
import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
class AdvisorTest {

    @Test
    void test() {
        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);

        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(defaultPointcutAdvisor);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
    }

    @Test
    void test2() {
        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);

        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());
        proxyFactory.addAdvisor(defaultPointcutAdvisor);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }

    static class MyPointcut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }


        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher {

        @Override
        public boolean matches(Method method, Class<?> targetClass) {

            log.info("포인트 컷 호출 method = {}, targetCalss = {}", method.getName(), targetClass);
            return method.getName().equals("save");
        }


        @Override
        public boolean isRuntime() {
            return false;
        }


        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            throw new UnsupportedOperationException();
        }
    }
}




















