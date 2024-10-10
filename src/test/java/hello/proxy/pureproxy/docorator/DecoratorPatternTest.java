package hello.proxy.pureproxy.docorator;


import java.sql.Time;
import hello.proxy.pureproxy.docorator.code.Component;
import hello.proxy.pureproxy.docorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.docorator.code.MessageDecorator;
import hello.proxy.pureproxy.docorator.code.RealComponent;
import hello.proxy.pureproxy.docorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component component = new RealComponent();

        DecoratorPatternClient client = new DecoratorPatternClient(component);

        client.execute();
    }


    @Test
    void noDecorato2() {
        Component component = new RealComponent();

        MessageDecorator decorator = new MessageDecorator(component);

        DecoratorPatternClient client = new DecoratorPatternClient(decorator);

        client.execute();
    }


    @Test
    void noDecorato3() {
        Component component = new RealComponent();
        MessageDecorator decorator = new MessageDecorator(component);
        TimeDecorator timeDecorator = new TimeDecorator(decorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();
    }
}
