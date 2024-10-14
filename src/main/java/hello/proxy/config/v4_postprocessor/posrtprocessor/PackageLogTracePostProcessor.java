package hello.proxy.config.v4_postprocessor.posrtprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final String basePackage;
    private final Advisor advisor;

    public PackageLogTracePostProcessor(String basePackage, Advisor advisor) {
        this.basePackage = basePackage;
        this.advisor = advisor;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("bean, beanName {} {}", bean, beanName.getClass());

        String packageName = bean.getClass().getPackageName();

        if(!packageName.startsWith(basePackage)) {
            return bean;
        } else {
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            proxyFactory.addAdvisor(advisor);
            //proxyFactory.setProxyTargetClass(true);

            Object proxy = proxyFactory.getProxy();

            log.info("proxy {}", proxy.getClass());

            return proxyFactory.getProxy();
        }
    }
}
