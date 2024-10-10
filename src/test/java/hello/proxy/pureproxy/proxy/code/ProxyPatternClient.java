package hello.proxy.pureproxy.proxy.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyPatternClient {

    private Subject subject;

    public ProxyPatternClient(Subject subject) {
        this.subject = subject;
    }


    public void execute() {
        subject.operation();
    }
}
