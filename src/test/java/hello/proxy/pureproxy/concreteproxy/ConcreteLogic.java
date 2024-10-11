package hello.proxy.pureproxy.concreteproxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteLogic {
    public String operation() {
        log.info("콘크리트 실행");
        return "data";
    }
}
