package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPosttTest {

    @Test
    void basicTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        ClassB a = applicationContext.getBean("beanA", ClassB.class);

        a.helloB();
    }

    @Slf4j
    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public ClassA a() {
            return new ClassA();
        }

        @Bean
        public AToBPost aToBPost() {
            return new AToBPost();
        }
    }

    @Slf4j
    static class ClassA {
        public void helloA() {
            log.info("helloA");
        }
    }


    @Slf4j
    static class ClassB {
        public void helloB() {
            log.info("helloB");
        }
    }

    @Slf4j
    static class AToBPost implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("bean, beanName, {}, {}", bean, beanName);

            if(bean instanceof ClassA) {
                return new ClassB();
            }

            return bean;
        }
    }
}
