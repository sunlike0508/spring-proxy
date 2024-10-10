package hello.proxy.pureproxy.docorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {
    private Component target;
    private String message;


    public MessageDecorator(Component component) {
        this.target = component;
    }


    @Override
    public String operation() {
        log.info("데코레이터 실행");

        String operation = target.operation();

        return "***" + operation + "***";
    }
}
