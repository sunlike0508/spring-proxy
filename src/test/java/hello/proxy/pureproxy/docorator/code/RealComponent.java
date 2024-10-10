package hello.proxy.pureproxy.docorator.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealComponent implements Component {

    @Override
    public String operation() {
        log.info("리얼 객체 호출");
        return "data";
    }
}
