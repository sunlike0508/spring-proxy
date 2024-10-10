package hello.proxy.pureproxy.docorator.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component{

    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long start = System.currentTimeMillis();
        String result = component.operation();
        long end = System.currentTimeMillis();
        log.info("execute Time {} ms", end - start);
        return result;
    }
}
