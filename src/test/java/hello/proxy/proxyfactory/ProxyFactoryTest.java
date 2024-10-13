package hello.proxy.proxyfactory;


import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
class ProxyFactoryTest {

    @Test
    void test() {
        ServiceInterface serviceInterface = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(serviceInterface);

        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

        log.info("targetCalss {} =", serviceInterface.getClass());
        log.info("proxyCalss {} =", proxy.getClass());

        System.out.println(AopUtils.isAopProxy(proxy));
        System.out.println(AopUtils.isJdkDynamicProxy(proxy));
        System.out.println(AopUtils.isCglibProxy(proxy));
    }


    @Test
    void test2() {
        ConcreteService concreteService = new ConcreteService();

        ProxyFactory proxyFactory = new ProxyFactory(concreteService);

        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

        proxy.call();

        log.info("targetCalss {} =", concreteService.getClass());
        log.info("proxyCalss {} =", proxy.getClass());

        System.out.println(AopUtils.isAopProxy(proxy));
        System.out.println(AopUtils.isJdkDynamicProxy(proxy));
        System.out.println(AopUtils.isCglibProxy(proxy));
    }


    @Test
    void test3() {
        ServiceInterface serviceInterface = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(serviceInterface);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

        log.info("targetCalss {} =", serviceInterface.getClass());
        log.info("proxyCalss {} =", proxy.getClass());

        System.out.println(AopUtils.isAopProxy(proxy));
        System.out.println(AopUtils.isJdkDynamicProxy(proxy));
        System.out.println(AopUtils.isCglibProxy(proxy));
    }
}
