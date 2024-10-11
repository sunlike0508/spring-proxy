package hello.proxy.config.v1_proxy;


import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderControllerInterfaceProxy(OrderControllerV1 orderControllerV1, LogTrace logTrace) {
        return new OrderControllerInterfaceProxy(orderControllerV1, logTrace);
    }

    @Bean
    public OrderServiceV1 orderServiceInterfaceProxy(OrderServiceV1 orderServiceV1,
            LogTrace logTrace) {
        return new OrderServiceInterfaceProxy(orderServiceV1, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryInterfaceProxy(OrderRepositoryV1 orderRepositoryV1, LogTrace logTrace) {
        return new OrderRepositoryInterfaceProxy(orderRepositoryV1, logTrace);
    }
}
