package hello.proxy.pureproxy.concreteproxy;


import hello.proxy.pureproxy.docorator.code.Component;
import hello.proxy.pureproxy.docorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.docorator.code.MessageDecorator;
import hello.proxy.pureproxy.docorator.code.RealComponent;
import hello.proxy.pureproxy.docorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ConcreteProxyTest {

    @Test
    void noDecorator() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }


    @Test
    void noDecorator2() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteLogic timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.execute();
    }

}
