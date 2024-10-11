package hello.proxy.pureproxy.concreteproxy;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic{

    private ConcreteLogic concreteLogic;

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }


    @Override
    public String operation() {
        log.info("Time데코 실행");

        long start = System.currentTimeMillis();
        String result = concreteLogic.operation();
        long end = System.currentTimeMillis();
        log.info("execute Time {} ms", end - start);

        return result;
    }
}
