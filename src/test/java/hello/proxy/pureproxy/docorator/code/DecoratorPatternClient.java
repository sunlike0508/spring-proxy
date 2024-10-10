package hello.proxy.pureproxy.docorator.code;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
public class DecoratorPatternClient {

    private Component component;


    public DecoratorPatternClient(Component component) {this.component = component;}


    public void execute() {
        String result = component.operation();

        log.info("result: {}", result);
    }
}
