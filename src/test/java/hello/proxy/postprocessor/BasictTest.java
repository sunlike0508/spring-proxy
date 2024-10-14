package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasictTest {

    @Test
    void basicTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        ClassA a = applicationContext.getBean("beanA", ClassA.class);

        a.helloA();
    }

    @Slf4j
    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public ClassA a() {
            return new ClassA();
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
        public void helloA() {
            log.info("helloB");
        }
    }
}
