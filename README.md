# 프록시, 프록시 패턴, 데코레이터 패턴 - 소개

클라이언트와 서버의 개념은 상당히 넓게 사용된다. 

클라이언트는 의뢰인이라는 뜻이고, 서버는 '서비스나 상품을 제공하는 사람이나 물건'을 뜻한다. 

따라서 클라이언트와 서버의 기본 개념을 정의하면 **클라이언트는 서버에 필요한 것을 요청하고, 서버는 클라이언트의 요청을 처리**하는 것이다.

이 개념을 우리가 익숙한 컴퓨터 네트워크에 도입하면 클라이언트는 웹 브라우저가 되고, 요청을 처리하는 서버는 웹 서버가 된다.

이 개념을 객체에 도입하면, 요청하는 객체는 클라이언트가 되고, 요청을 처리하는 객체는 서버가 된다.

<img width="695" alt="Screenshot 2024-10-10 at 23 11 27" src="https://github.com/user-attachments/assets/1ce65641-b9d4-445a-8da1-7e641e706d84">

클라이언트와 서버 개념에서 일반적으로 클라이언트가 서버를 직접 호출하고, 처리 결과를 직접 받는다. 이것을 직접 호출이라 한다.

<img width="695" alt="Screenshot 2024-10-10 at 23 11 23" src="https://github.com/user-attachments/assets/5a52355b-b0d2-437c-bf0a-f3b8c91d195c">

그런데 클라이언트가 요청한 결과를 서버에 직접 요청하는 것이 아니라 어떤 대리자를 통해서 대신 간접적으로 서버에 요청할 수 있다. 

예를 들어서 내가 직접 마트에서 장을 볼 수도 있지만, 누군가에게 대신 장을 봐달라고 부탁할 수도 있다.

여기서 대신 장을 보는 **대리자를 영어로 프록시(Proxy)** 라 한다.

**예시**
재미있는 점은 직접 호출과 다르게 간접 호출을 하면 대리자가 중간에서 여러가지 일을 할 수 있다는 점이다.

엄마에게 라면을 사달라고 부탁 했는데, 엄마는 그 라면은 이미 집에 있다고 할 수도 있다. 

그러면 기대한 것보다 더 빨리 라면을 먹을 수 있다. (접근 제어, 캐싱)

아버지께 자동차 주유를 부탁했는데, 아버지가 주유 뿐만 아니라 세차까지 하고 왔다. 

클라이언트가 기대한 것 외에 세차라는 부가 기능까지 얻게 되었다. (부가 기능 추가)

그리고 대리자가 또 다른 대리자를 부를 수도 있다. 

예를 들어서 내가 동생에게 라면을 사달라고 했는데, 동생은 또 다른 누군가에게 라면을 사달라고 다시 요청할 수도 있다. 

중요한 점은 클라이언트는 대리자를 통해서 요청했기 때문에 그 이후 과정은 모른다는 점이다. 

동생을 통해서 라면이 나에게 도착하기만 하면 된다. (프록시 체인)

<img width="705" alt="Screenshot 2024-10-10 at 23 13 22" src="https://github.com/user-attachments/assets/a7d02cce-4082-4876-ad32-d98833662a87">

**대체 가능**

그런데 여기까지 듣고 보면 아무 객체나 프록시가 될 수 있는 것 같다.

객체에서 프록시가 되려면, 클라이언트는 서버에게 요청을 한 것인지, 프록시에게 요청을 한 것인지 조차 몰라야 한다. 

쉽게 이야기해서 서버와 프록시는 같은 인터페이스를 사용해야 한다. 

그리고 클라이언트가 사용하는 서버 객체를 프록시 객체로 변경해도 클라이언트 코드를 변경하지 않고 동작할 수 있어야 한다.

<img width="682" alt="Screenshot 2024-10-10 at 23 16 17" src="https://github.com/user-attachments/assets/74777d4d-c6c6-4775-8b5c-edca370c5559">

**서버와 프록시가 같은 인터페이스 사용**

클래스 의존관계를 보면 클라이언트는 서버 인터페이스( `ServerInterface` )에만 의존한다. 

그리고 서버와 프록시가 같은 인터페이스를 사용한다. 따라서 DI를 사용해서 대체 가능하다.

<img width="714" alt="Screenshot 2024-10-10 at 23 16 39" src="https://github.com/user-attachments/assets/d307ad5a-1ab9-47e1-ab8d-d344a8d5706c">

<img width="698" alt="Screenshot 2024-10-10 at 23 16 43" src="https://github.com/user-attachments/assets/37370476-e384-4b3a-8c48-872154addc46">

이번에는 런타임 객체 의존 관계를 살펴보자. 

런타임(애플리케이션 실행 시점)에 클라이언트 객체에 DI를 사용해서 `Client -> Server` 에서 `Client -> Proxy` 로 객체 의존관계를 변경해도 클라이언트 코드를 전혀 변경하지 않아도 된다. 

클라이언트 입장에서는 변경 사실 조차 모른다.

DI를 사용하면 클라이언트 코드의 변경 없이 유연하게 프록시를 주입할 수 있다.

**프록시의 주요 기능**
프록시를 통해서 할 수 있는 일은 크게 2가지로 구분할 수 있다.
* 접근 제어
  * 권한에 따른 접근 차단 캐싱
  * 지연 로딩
* 부가 기능 추가
  * 원래 서버가 제공하는 기능에 더해서 부가 기능을 수행한다. 
  * 예) 요청 값이나, 응답 값을 중간에 변형한다.
  * 예) 실행 시간을 측정해서 추가 로그를 남긴다.

프록시 객체가 중간에 있으면 크게 **접근 제어**와 **부가 기능 추가**를 수행할 수 있다.

**GOF 디자인 패턴**
둘다 프록시를 사용하는 방법이지만 GOF 디자인 패턴에서는 이 둘을 의도(intent)에 따라서 프록시 패턴과 데코레이 터 패턴으로 구분한다.

* 프록시 패턴: 접근 제어가 목적
* 데코레이터 패턴: 새로운 기능 추가가 목적
* 
둘다 프록시를 사용하지만, 의도가 다르다는 점이 핵심이다. 

용어가 프록시 패턴이라고 해서 이 패턴만 프록시를 사용하는 것은 아니다. 

데코레이터 패턴도 프록시를 사용한다.

이왕 프록시를 학습하기로 했으니 GOF 디자인 패턴에서 설명하는 프록시 패턴과 데코레이터 패턴을 나누어 학습해보자.

**참고**

프록시라는 개념은 클라이언트 서버라는 큰 개념안에서 자연스럽게 발생할 수 있다.

프록시는 객체안에서의 개념도 있고, 웹 서버에서의 프록시도 있다. 객체안에서 객체로 구현되어있는가, 웹 서버로 구현되어 있는가처럼 규모의 차이가 있을 뿐 근본적인 역할은 같다.

## 프록시 패턴과 데코레이터 패턴 정리 

### GOF 데코레이터 패턴

<img width="693" alt="Screenshot 2024-10-10 at 23 48 59" src="https://github.com/user-attachments/assets/9e8bdd98-2b8f-43d4-8fd7-eb1a03e62257">

여기서 생각해보면 `Decorator` 기능에 일부 중복이 있다. 

꾸며주는 역할을 하는 `Decorator` 들은 스스로 존재할 수 없다. 

항상 꾸며줄 대상이 있어야 한다. 

따라서 내부에 호출 대상인 `component` 를 가지고 있어야 한다. 

그리고 `component` 를 항상 호출해야 한다. 이 부분이 중복이다. 

이런 중복을 제거하기 위해 `component` 를 속성으로 가지고 있는 `Decorator` 라는 추상 클래스를 만드는 방법도 고민할 수 있다. 

이렇게 하면 추가로 클래스 다이어그램에서 어떤 것이 실제 컴포넌트인지, 데코레이터인지 명확하게 구분할 수 있다. 

여기까지 고민한 것이 바로 GOF에서 설명하는데 코레이터 패턴의 기본 예제이다.

**프록시 패턴 vs 데코레이터 패턴** 

여기까지 진행하면 몇가지 의문이 들 것이다.

`Decorator` 라는 추상 클래스를 만들어야 데코레이터 패턴일까?

프록시 패턴과 데코레이터 패턴은 그 모양이 거의 비슷한 것 같은데?

**의도(intent)**
사실 프록시 패턴과 데코레이터 패턴은 그 모양이 거의 같고, 상황에 따라 정말 똑같을 때도 있다. 

그러면 둘을 어떻게 구분하는 것일까?

디자인 패턴에서 중요한 것은 해당 패턴의 겉모양이 아니라 그 패턴을 만든 의도가 더 중요하다. 

따라서 의도에 따라 패 턴을 구분한다.

프록시 패턴의 의도: 다른 개체에 대한 **접근을 제어**하기 위해 대리자를 제공

데코레이터 패턴의 의도: **객체에 추가 책임(기능)을 동적으로 추가**하고, 기능 확장을 위한 유연한 대안 제공

**정리**

프록시를 사용하고 해당 프록시가 접근 제어가 목적이라면 프록시 패턴이고, 새로운 기능을 추가하는 것이 목적이라면 데코레이터 패턴이 된다.


## 프록시 적용

인터페이스와 구현체가 있는 V1 App에 지금까지 학습한 프록시를 도입해서 `LogTrace` 를 사용해보자.

**프록시를 사용하면 기존 코드를 전혀 수정하지 않고 로그 추적 기능을 도입할 수 있다.** 

V1 App의 기본 클래스 의존 관계와 런타임시 객체 인스턴스 의존 관계는 다음과 같다.

<img width="711" alt="Screenshot 2024-10-11 at 22 48 08" src="https://github.com/user-attachments/assets/afa693c4-1be4-47ae-b843-2be974a0878b">

**V1 프록시 의존 관계 추가**

<img width="687" alt="Screenshot 2024-10-11 at 22 49 28" src="https://github.com/user-attachments/assets/a28dea56-0938-4323-8def-8ed1416f50b7">

**V1 프록시 런타임 객체 의존 관계**

<img width="683" alt="Screenshot 2024-10-11 at 22 49 42" src="https://github.com/user-attachments/assets/7a683529-e400-45df-afd8-8bb2c150cc0d">

그리고 애플리케이션 실행 시점에 프록시를 사용하도록 의존 관계를 설정해주어야 한다. 이 부분은 빈을 등록하는 설정 파일을 활용하면 된다. (그림에서 리포지토리는 생략했다.)

**V1 프록시 런타임 객체 의존 관계 설정**

이제 프록시의 런타임 객체 의존 관계를 설정하면 된다. 

기존에는 스프링 빈이 `orderControlerV1Impl` , `orderServiceV1Impl` 같은 실제 객체를 반환했다. 

하지만 이제는 프록시를 사용해야한다. 

따라서 프록시를 생성하고 **프록시를 실제 스프링 빈 대신 등록한다. 실제 객체는 스프링 빈으로 등록하지 않는다.**

프록시는 내부에 실제 객체를 참조하고 있다. 

예를 들어서 `OrderServiceInterfaceProxy` 는 내부에 실제 대상 객체인 `OrderServiceV1Impl` 을 가지고 있다.

정리하면 다음과 같은 의존 관계를 가지고 있다. 

`proxy -> target``orderServiceInterfaceProxy -> orderServiceV1Impl`

스프링 빈으로 실제 객체 대신에 프록시 객체를 등록했기 때문에 앞으로 스프링 빈을 주입 받으면 **실제 객체 대신에 프록시 객체가 주입**된다.

실제 객체가 스프링 빈으로 등록되지 않는다고 해서 사라지는 것은 아니다. 

프록시 객체가 실제 객체를 참조하기 때문에 프록시를 통해서 실제 객체를 호출할 수 있다. 

쉽게 이야기해서 프록시 객체 안에 실제 객체가 있는 것이다.

<img width="692" alt="Screenshot 2024-10-11 at 23 04 24" src="https://github.com/user-attachments/assets/58c1e723-52f6-433a-8df3-a96186e8d361">

실제 객체가 스프링 빈으로 등록된다. 빈 객체의 마지막에 `@x0..` 라고 해둔 것은 인스턴스라는 뜻이다.

<img width="687" alt="Screenshot 2024-10-11 at 23 04 40" src="https://github.com/user-attachments/assets/f122ed29-9fcd-4da1-a338-93a171d38812">

<img width="690" alt="Screenshot 2024-10-11 at 23 04 58" src="https://github.com/user-attachments/assets/f98623c7-a951-439d-9e63-d55a331ee71e">

`InterfaceProxyConfig` 를 통해 프록시를 적용한 후 스프링 컨테이너에 프록시 객체가 등록된다. 

스프링 컨테이너는 이제 실제 객체가 아니라 프록시 객체를 스프링 빈으로 관리한다.

이제 실제 객체는 스프링 컨테이너와는 상관이 없다. 실제 객체는 프록시 객체를 통해서 참조될 뿐이다.

프록시 객체는 스프링 컨테이너가 관리하고 자바 힙 메모리에도 올라간다. 

반면에 실제 객체는 자바 힙 메모리에는 올라가지만 스프링 컨테이너가 관리하지는 않는다.


**구체 클래스 기반 프록시 - 예제2 클래스 기반 프록시 도입**

지금까지 인터페이스를 기반으로 프록시를 도입했다. 

그런데 자바의 다형성은 인터페이스를 구현하든, 아니면 클래스를 상속하든 상위 타입만 맞으면 다형성이 적용된다. 

쉽게 이야기해서 인터페이스가 없어도 프록시를 만들수 있다는 뜻이다. 

**참고**: 

자바 언어에서 다형성은 인터페이스나 클래스를 구분하지 않고 모두 적용된다. 

해당 타입과 그 타입의 하위 타입은 모두 다형성의 대상이 된다. 

자바 언어의 너무 기본적인 내용을 이야기했지만, 인터페이스가 없어도 프록시가 가능하다는 것을 확실하게 깊고 넘어갈 필요가 있어서 자세히 설명했다.


**구체 클래스 기반 프록시 - 적용**

**클래스 기반 프록시의 단점**

`super(null)` : `OrderServiceV2` : 자바 기본 문법에 의해 자식 클래스를 생성할 때는 항상 `super()` 로 부
모 클래스의 생성자를 호출해야 한다. 

이 부분을 생략하면 기본 생성자가 호출된다. 

그런데 부모 클래스인 `OrderServiceV2` 는 기본 생성자가 없고, 생성자에서 파라미터 1개를 필수로 받는다. 

따라서 파라미터를 넣어 서 `super(..)` 를 호출해야 한다.

프록시는 부모 객체의 기능을 사용하지 않기 때문에 `super(null)` 을 입력해도 된다.

인터페이스 기반 프록시는 이런 고민을 하지 않아도 된다.

### 인터페이스 기반 프록시와 클래스 기반 프록시

**프록시**

프록시를 사용한 덕분에 원본 코드를 전혀 변경하지 않고, V1, V2 애플리케이션에 `LogTrace` 기능을 적용할 수 있었다.

**인터페이스 기반 프록시 vs 클래스 기반 프록시**
* 인터페이스가 없어도 클래스 기반으로 프록시를 생성할 수 있다.
* 클래스 기반 프록시는 해당 클래스에만 적용할 수 있다. 인터페이스 기반 프록시는 인터페이스만 같으면 모든 곳 에 적용할 수 있다.
* 클래스 기반 프록시는 상속을 사용하기 때문에 몇가지 제약이 있다.
  * 부모 클래스의 생성자를 호출해야 한다.(앞서 본 예제)
  * 클래스에 final 키워드가 붙으면 상속이 불가능하다.
  * 메서드에 final 키워드가 붙으면 해당 메서드를 오버라이딩 할 수 없다.
  
이렇게 보면 인터페이스 기반의 프록시가 더 좋아보인다. 맞다. 인터페이스 기반의 프록시는 상속이라는 제약에서 자유롭다. 

프로그래밍 관점에서도 인터페이스를 사용하는 것이 역할과 구현을 명확하게 나누기 때문에 더 좋다.

인터페이스 기반 프록시의 단점은 인터페이스가 필요하다는 그 자체이다. 인터페이스가 없으면 인터페이스 기반 프록시를 만들 수 없다.

참고: 인터페이스 기반 프록시는 캐스팅 관련해서 단점이 있는데, 이 내용은 강의 뒷부문에서 설명한다.

이론적으로는 모든 객체에 인터페이스를 도입해서 역할과 구현을 나누는 것이 좋다. 

이렇게 하면 역할과 구현을 나누어서 구현체를 매우 편리하게 변경할 수 있다. 

하지만 실제로는 구현을 거의 변경할 일이 없는 클래스도 많다. 

인터페이스를 도입하는 것은 구현을 변경할 가능성이 있을 때 효과적인데, 구현을 변경할 가능성이 거의 없는 코드에 무작정 인터페이스를 사용하는 것은 번거롭고 그렇게 실용적이지 않다. 

이런곳에는 실용적인 관점에서 인터페이스를 사용 하지 않고 구체 클래스를 바로 사용하는 것이 좋다 생각한다. 

(물론 인터페이스를 도입하는 다양한 이유가 있다. 여기서 핵심은 인터페이스가 항상 필요하지는 않다는 것이다.)
 
**결론**
실무에서는 프록시를 적용할 때 V1처럼 인터페이스도 있고, V2처럼 구체 클래스도 있다. 따라서 2가지 상황을 모두 대 응할 수 있어야 한다.

**너무 많은 프록시 클래스**

지금까지 프록시를 사용해서 기존 코드를 변경하지 않고, 로그 추적기라는 부가 기능을 적용할 수 있었다.

그런데 문제는 프록시 클래스를 너무 많이 만들어야 한다는 점이다. 

잘 보면 프록시 클래스가 하는 일은 `LogTrace` 를 사용하는 것인데, 그 로직이 모두 똑같다. 

대상 클래스만 다를 뿐이다. 만약 적용해야 하는 대상 클래스가 100개라면 프록시 클래스도 100개를 만들어야한다.

프록시 클래스를 하나만 만들어서 모든 곳에 적용하는 방법은 없을까?

바로 다음에 설명할 동적 프록시 기술이 이 문제를 해결해준다.

# 리플렉션

지금까지 프록시를 사용해서 기존 코드를 변경하지 않고, 로그 추적기라는 부가 기능을 적용할 수 있었다. 

그런데 문제 는 대상 클래스 수 만큼 로그 추적을 위한 프록시 클래스를 만들어야 한다는 점이다.

로그 추적을 위한 프록시 클래스들의 소스코드는 거의 같은 모양을 하고 있다.

자바가 기본으로 제공하는 JDK 동적 프록시 기술이나 CGLIB 같은 프록시 생성 오픈소스 기술을 활용하면 프록시 객체를 동적으로 만들어낼 수 있다. 

쉽게 이야기해서 프록시 클래스를 지금처럼 계속 만들지 않아도 된다는 것이다. 

프록시를 적용할 코드를 하나만 만들어두고 동적 프록시 기술을 사용해서 프록시 객체를 찍어내면 된다.

JDK 동적 프록시를 이해하기 위해서는 먼저 자바의 리플렉션 기술을 이해해야 한다.

리플렉션 기술을 사용하면 클래스나 메서드의 메타정보를 동적으로 획득하고, 코드도 동적으로 호출할 수 있다. 

여기서는 JDK 동적 프록시를 이해하기 위한 최소한의 리플렉션 기술을 알아보자.

람다를 사용해서 공통화 하는 것도 가능하다. 여기서는 람다를 사용하기 어려운 상황이라 가정하자. 그리고 리플렉션 학습이 목적이니 리플렉션에 집중하자.

리플렉션을 사용하면 클래스와 메서드의 메타정보를 사용해서 애플리케이션을 동적으로 유연하게 만들 수 있다. 

하지만 리플렉션 기술은 런타임에 동작하기 때문에, 컴파일 시점에 오류를 잡을 수 없다.

예를 들어서 지금까지 살펴본 코드에서 `getMethod("callA")` 안에 들어가는 문자를 실수로 `getMethod("callZ")` 로 작성해도 컴파일 오류가 발생하지 않는다. 

그러나 해당 코드를 직접 실행하는 시점에 발생하는 오류인 런타임 오류가 발생한다.

가장 좋은 오류는 개발자가 즉시 확인할 수 있는 컴파일 오류이고, 가장 무서운 오류는 사용자가 직접 실행할 때 발생하는 런타임 오류다.

따라서 리플렉션은 일반적으로 사용하면 안된다. 

지금까지 프로그래밍 언어가 발달하면서 타입 정보를 기반으로 컴파일 시점에 오류를 잡아준 덕분에 개발자가 편하게 살았는데, 리플렉션은 그것에 역행하는 방식이다.

리플렉션은 프레임워크 개발이나 또는 매우 일반적인 공통 처리가 필요할 때 부분적으로 주의해서 사용해야 한다.

## JDK 동적 프록시

지금까지 프록시를 적용하기 위해 적용 대상의 숫자 만큼 많은 프록시 클래스를 만들었다. 

적용 대상이 100개면 프록시 클래스도 100개 만들었다. 

그런데 앞서 살펴본 것과 같이 프록시 클래스의 기본 코드와 흐름은 거의 같고, 프록시를 어 떤 대상에 적용하는가 정도만 차이가 있었다. 

쉽게 이야기해서 프록시의 로직은 같은데, 적용 대상만 차이가 있는 것이다.

이 문제를 해결하는 것이 바로 동적 프록시 기술이다.

동적 프록시 기술을 사용하면 개발자가 직접 프록시 클래스를 만들지 않아도 된다. 

이름 그대로 프록시 객체를 동적으로 런타임에 개발자 대신 만들어준다. 

그리고 동적 프록시에 원하는 실행 로직을 지정할 수 있다.

JDK 동적 프록시는 인터페이스를 기반으로 프록시를 동적으로 만들어준다. 따라서 인터페이스가 필수이다.

JDK 동적 프록시에 적용할 로직은 `InvocationHandler` 인터페이스를 구현해서 작성하면 된다.

**생성된 JDK 동적 프록시**

`proxyClass=class com.sun.proxy.$Proxy1` 이 부분이 동적으로 생성된 프록시 클래스 정보이다. 

이것은 우리가 만든 클래스가 아니라 JDK 동적 프록시가 이름 그대로 동적으로 만들어준 프록시이다. 

이 프록시는 `TimeInvocationHandler` 로직을 실행한다.

예제를 보면 `AImpl` , `BImpl` 각각 프록시를 만들지 않았다. 

프록시는 JDK 동적 프록시를 사용해서 동적으로 만들고 `TimeInvocationHandler` 는 공통으로 사용했다.

JDK 동적 프록시 기술 덕분에 적용 대상 만큼 프록시 객체를 만들지 않아도 된다. 

그리고 같은 부가 기능 로직을 한번만 개발해서 공통으로 적용할 수 있다. 

만약 적용 대상이 100개여도 동적 프록시를 통해서 생성하고, 각각 필요한 `InvocationHandler` 만 만들어서 넣어주면 된다.

결과적으로 프록시 클래스를 수 없이 만들어야 하는 문제도 해결하고, 부가 기능 로직도 하나의 클래스에 모아서 단일 책임 원칙(SRP)도 지킬 수 있게 되었다.

JDK 동적 프록시 없이 직접 프록시를 만들어서 사용할 때와 JDK 동적 프록시를 사용할 때의 차이를 그림으로 비교해 보자.

**JDK 동적 프록시 도입 전 - 직접 프록시 생성**

<img width="689" alt="Screenshot 2024-10-12 at 15 25 26" src="https://github.com/user-attachments/assets/e076e3e4-35dd-4aeb-a8c9-d35ca3d9a9cb">

**JDK 동적 프록시 도입 후**

<img width="701" alt="Screenshot 2024-10-12 at 15 25 47" src="https://github.com/user-attachments/assets/02a6f75e-beb0-46a4-847d-9c14076e87e1">

<img width="680" alt="Screenshot 2024-10-12 at 15 25 56" src="https://github.com/user-attachments/assets/305358ee-208f-4124-b784-841746b0a24e">

<img width="706" alt="Screenshot 2024-10-12 at 16 08 22" src="https://github.com/user-attachments/assets/4a67c320-c039-4360-a167-9d4a21c18ba0">

<img width="710" alt="Screenshot 2024-10-12 at 16 08 16" src="https://github.com/user-attachments/assets/ed5d2a6a-a252-4edb-81bc-914e8c490b6f">

## CGLIB

**Code Generator Library**
CGLIB는 바이트코드를 조작해서 동적으로 클래스를 생성하는 기술을 제공하는 라이브러리이다.

CGLIB를 사용하면 인터페이스가 없어도 구체 클래스만 가지고 동적 프록시를 만들어낼 수 있다.

CGLIB는 원래는 외부 라이브러리인데, 스프링 프레임워크가 스프링 내부 소스 코드에 포함했다. 

따라서 스프링 을 사용한다면 별도의 외부 라이브러리를 추가하지 않아도 사용할 수 있다.

참고로 우리가 CGLIB를 직접 사용하는 경우는 거의 없다. 

이후에 설명할 스프링의 `ProxyFactory` 라는 것이 이 기술을 편리하게 사용하게 도와주기 때문에, 너무 깊이있게 파기 보다는 CGLIB가 무엇인지 대략 개념만 잡으면 된다.

**MethodInterceptor -CGLIB 제공** 

```java
package org.springframework.cglib.proxy;
public interface MethodInterceptor extends Callback {
   Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable;
}
```
* `obj` : CGLIB가 적용된 객체
* `method` : 호출된 메서드
* `args` : 메서드를 호출하면서 전달된 인수 
* `proxy` : 메서드 호출에 사용

`Enhancer` : CGLIB는 `Enhancer` 를 사용해서 프록시를 생성한다.

`enhancer.setSuperclass(ConcreteService.class)` : CGLIB는 구체 클래스를 상속 받아서 프록시 를 생성할 수 있다. 어떤 구체 클래스를 상속 받을지 지정한다.

`enhancer.setCallback(new TimeMethodInterceptor(target))` : 프록시에 적용할 실행 로직을 할당한다.

`enhancer.create()` : 프록시를 생성한다. 앞서 설정한 `enhancer.setSuperclass(ConcreteService.class)` 에서 지정한 클래스를 상속 받아서 프록시가 만들어진다.

<img width="691" alt="Screenshot 2024-10-12 at 16 35 03" src="https://github.com/user-attachments/assets/ff58f04a-1fad-4043-99bb-138e80d41ab8">

**CGLIB 제약**

클래스 기반 프록시는 상속을 사용하기 때문에 몇가지 제약이 있다.

부모 클래스의 생성자를 체크해야 한다. CGLIB는 자식 클래스를 동적으로 생성하기 때문에 기본 생성자가 필요하다.

클래스에 `final` 키워드가 붙으면 상속이 불가능하다. CGLIB에서는 예외가 발생한다.

메서드에 `final` 키워드가 붙으면 해당 메서드를 오버라이딩 할 수 없다. CGLIB에서는 프록시 로직이 동작하지 않는다.

**참고**
CGLIB를 사용하면 인터페이스가 없는 V2 애플리케이션에 동적 프록시를 적용할 수 있다. 

그런데 지금 당장 적용 하기에는 몇가지 제약이 있다. 

V2 애플리케이션에 기본 생성자를 추가하고, 의존관계를 `setter` 를 사용해서 주입하면 CGLIB를 적용할 수 있다.

하지만 다음에 학습하는 `ProxyFactory` 를 통해서 CGLIB를 적용하면 이런 단점을 해결하고 또 더 편리하기 때문에, 애플리케이션에 CGLIB로 프록시를 적용하는 것은 조금 뒤에 알아보겠다.

# 프록시 팩토리

앞서 마지막에 설명했던 동적 프록시를 사용할 때 문제점을 다시 확인해보자.

**문제점**

인터페이스가 있는 경우에는 JDK 동적 프록시를 적용하고, 그렇지 않은 경우에는 CGLIB를 적용하려면 어떻게 해야할까?

두 기술을 함께 사용할 때 부가 기능을 제공하기 위해 JDK 동적 프록시가 제공하는 `InvocationHandler` 와 CGLIB가 제공하는 `MethodInterceptor` 를 각각 중복으로 만들어서 관리해야 할까?

특정 조건에 맞을 때 프록시 로직을 적용하는 기능도 공통으로 제공되었으면?

**Q: 인터페이스가 있는 경우에는 JDK 동적 프록시를 적용하고, 그렇지 않은 경우에는 CGLIB를 적용하려면 어떻게 해 야할까?**

스프링은 유사한 구체적인 기술들이 있을 때, 그것들을 통합해서 일관성 있게 접근할 수 있고, 더욱 편리하게 사용할 수 있는 추상화된 기술을 제공한다.

스프링은 동적 프록시를 통합해서 편리하게 만들어주는 프록시 팩토리( `ProxyFactory` )라는 기능을 제공한다. 

이전에는 상황에 따라서 JDK 동적 프록시를 사용하거나 CGLIB를 사용해야 했다면, 이제는 이 프록시 팩토리 하나로 편리하게 동적 프록시를 생성할 수 있다.

프록시 팩토리는 인터페이스가 있으면 JDK 동적 프록시를 사용하고, 구체 클래스만 있다면 CGLIB를 사용한다. 그리고 이 설정을 변경할 수도 있다.

<img width="700" alt="Screenshot 2024-10-13 at 15 17 50" src="https://github.com/user-attachments/assets/cb7b85dd-163b-4bd3-8fa3-34f1fbc781ad">

<img width="700" alt="Screenshot 2024-10-13 at 15 17 50" src="https://github.com/user-attachments/assets/cb7b85dd-163b-4bd3-8fa3-34f1fbc781ad">

**Q: 두 기술을 함께 사용할 때 부가 기능을 적용하기 위해 JDK 동적 프록시가 제공하는 InvocationHandler와 CGLIB가 제공하는 MethodInterceptor를 각각 중복으로 따로 만들어야 할까?**

스프링은 이 문제를 해결하기 위해 부가 기능을 적용할 때 `Advice` 라는 새로운 개념을 도입했다. 

개발자는 `InvocationHandler` 나 `MethodInterceptor` 를 신경쓰지 않고, `Advice` 만 만들면 된다.

결과적으로 `InvocationHandler` 나 `MethodInterceptor` 는 `Advice` 를 호출하게 된다.

프록시 팩토리를 사용하면 `Advice` 를 호출하는 전용 `InvocationHandler` , `MethodInterceptor` 를 내부에서 사용한다.

<img width="694" alt="Screenshot 2024-10-13 at 15 18 33" src="https://github.com/user-attachments/assets/75e06958-2e6d-4459-a6cc-36ffbac78d58">

<img width="699" alt="Screenshot 2024-10-13 at 15 18 38" src="https://github.com/user-attachments/assets/cb4a4835-21bd-435d-8672-519c570d2b95">

**Q: 특정 조건에 맞을 때 프록시 로직을 적용하는 기능도 공통으로 제공되었으면?**

앞서 특정 메서드 이름의 조건에 맞을 때만 프록시 부가 기능이 적용되는 코드를 직접 만들었다. 

스프링은 `Pointcut` 이라는 개념을 도입해서 이 문제를 일관성 있게 해결한다.

## 예제

**Advice 만들기**

`Advice` 는 프록시에 적용하는 부가 기능 로직이다. 

이것은 JDK 동적 프록시가 제공하는 `InvocationHandler` 와 CGLIB가 제공하는 `MethodInterceptor` 의 개념과 유사한다. 

둘을 개념적으로 추상화 한 것이다. 프록시 팩토리를 사용하면 둘 대신에 `Advice` 를 사용하면 된다.

`Advice` 를 만드는 방법은 여러가지가 있지만, 기본적인 방법은 다음 인터페이스를 구현하면 된다. 

**MethodInterceptor - 스프링이 제공하는 코드**

```java
package org.aopalliance.intercept;

public interface MethodInterceptor extends Interceptor {
    Object invoke(MethodInvocation invocation) throws Throwable;
}
```

* `MethodInvocation invocation`
  * 내부에는 다음 메서드를 호출하는 방법, 현재 프록시 객체 인스턴스, `args` , 메서드 정보 등이 포함되어 있다. 
  * 기존에 파라미터로 제공되는 부분들이 이 안으로 모두 들어갔다고 생각하면 된다.
* CGLIB의 `MethodInterceptor` 와 이름이 같으므로 패키지 이름에 주의하자 
  * 참고로 여기서 사용하는 `org.aopalliance.intercept` 패키지는 스프링 AOP 모듈( `spring-aop` ) 안에 들어있다.
* `MethodInterceptor` 는 `Interceptor` 를 상속하고 `Interceptor` 는 `Advice` 인터페이스를 상속한다.


```java
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        
        log.info("time proxy 실행");
        long startTime = System.currentTimeMillis();

        Object invoke = invocation.proceed();

        long endTime = System.currentTimeMillis();

        long resultTime = startTime - endTime;

        log.info("time 종료 resultTime= {}", resultTime);

        return invoke;
    }
}
```

* `TimeAdvice` 는 앞서 설명한 `MethodInterceptor` 인터페이스를 구현한다. 패키지 이름에 주의하자.
* `Object result = invocation.proceed()`
  * `invocation.proceed()` 를 호출하면 `target` 클래스를 호출하고 그 결과를 받는다.
  * 그런데 기존에 보았던 코드들과 다르게 `target` 클래스의 정보가 보이지 않는다. 
  * `target` 클래스의 정보 는 `MethodInvocation invocation` 안에 모두 포함되어 있다.
  * 그 이유는 바로 다음에 확인할 수 있는데, 프록시 팩토리로 프록시를 생성하는 단계에서 이미 `target` 정보 를 파라미터로 전달받기 때문이다.

```java
void test() {
    ServiceInterface serviceInterface = new ServiceImpl();

    ProxyFactory proxyFactory = new ProxyFactory(serviceInterface);
    
    proxyFactory.addAdvice(new TimeAdvice());
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    proxy.save();
    proxy.find();
}
```

* `new ProxyFactory(target)` : 
  * 프록시 팩토리를 생성할 때, 생성자에 프록시의 호출 대상을 함께 넘겨준다. 
  * 프록시 팩토리는 이 인스턴스 정보를 기반으로 프록시를 만들어낸다. 
  * 만약 이 인스턴스에 인터페이스가 있다면 JDK 동적 프록시를 기본으로 사용하고 인터페이스가 없고 구체 클래스만 있다면 CGLIB를 통해서 동적 프록시를 생성한다.
  * 여기서는 `target` 이 `new ServiceImpl()` 의 인스턴스이기 때문에 `ServiceInterface` 인터페이스가 있다. 
  * 따라서 이 인터페이스를 기반으로 JDK 동적 프록시를 생성한다. 

* `proxyFactory.addAdvice(new TimeAdvice())` : 
  * 프록시 팩토리를 통해서 만든 프록시가 사용할 부가 기능 로직을 설정한다. 
  * JDK 동적 프록시가 제공하는 `InvocationHandler` 와 CGLIB가 제공하는 `MethodInterceptor` 의 개념과 유사하다. 
  * 이렇게 프록시가 제공하는 부가 기능 로직을 어드바이스 ( `Advice` )라 한다. 
  * 번역하면 조언을 해준다고 생각하면 된다. 

* `proxyFactory.getProxy()` : 프록시 객체를 생성하고 그 결과를 받는다.


**proxyTargetClass 옵션**

```java
void test() {
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true); //중요 
    proxyFactory.addAdvice(new TimeAdvice());
}
```

마지막으로 인터페이스가 있지만, CGLIB를 사용해서 인터페이스가 아닌 클래스 기반으로 동적 프록시를 만드는 방법을 알아보자.

프록시 팩토리는 `proxyTargetClass` 라는 옵션을 제공하는데, 이 옵션에 `true` 값을 넣으면 인터페이스가 있어도 강제로 CGLIB를 사용한다. 

그리고 인터페이스가 아닌 클래스 기반의 프록시를 만들어준다.

**프록시 팩토리의 기술 선택 방법**

* 대상에 인터페이스가 있으면: JDK 동적 프록시, 인터페이스 기반 프록시
* 대상에 인터페이스가 없으면: CGLIB, 구체 클래스 기반 프록시
* `proxyTargetClass=true` : CGLIB, 구체 클래스 기반 프록시, 인터페이스 여부와 상관없음

**정리**

프록시 팩토리의 서비스 추상화 덕분에 구체적인 CGLIB, JDK 동적 프록시 기술에 의존하지 않고, 매우 편리하게 동적 프록시를 생성할 수 있다.

프록시의 부가 기능 로직도 특정 기술에 종속적이지 않게 `Advice` 하나로 편리하게 사용할 수 있었다. 

이것은 프록시 팩토리가 내부에서 JDK 동적 프록시인 경우 `InvocationHandler` 가 `Advice` 를 호출하도록 개발해두고, CGLIB인 경우 `MethodInterceptor` 가 `Advice` 를 호출하도록 기능을 개발해두었기 때문이다.

**참고**
스프링 부트는 AOP를 적용할 때 기본적으로 `proxyTargetClass=true` 로 설정해서 사용한다. 

따라서 인터페이스가 있어도 항상 CGLIB를 사용해서 구체 클래스를 기반으로 프록시를 생성한다. 자세한 이유는 강의 뒷 부분에서 설명한다.

## 포인트컷, 어드바이스, 어드바이저


**포인트컷**( `Pointcut` ): 
  * 어디에 부가 기능을 적용할지, 어디에 부가 기능을 적용하지 않을지 판단하는 필터링 로직이다. 
  * 주로 클래스와 메서드 이름으로 필터링 한다. 
  * 이름 그대로 어떤 포인트(Point)에 기능을 적용할지 하지 않을지 잘라서 (cut) 구분하는 것이다.

**어드바이스**( `Advice` ): 이전에 본 것 처럼 프록시가 호출하는 부가 기능이다. 단순하게 프록시 로직이라 생각하면 된다.

**어드바이저**( `Advisor` ): 단순하게 하나의 포인트컷과 하나의 어드바이스를 가지고 있는 것이다. 쉽게 이야기해서 **포인트컷1 + 어드바이스1**이다.

**쉽게 기억하기**

조언( `Advice` )을 어디( `Pointcut` )에 할 것인가?

조언자( `Advisor` )는 어디( `Pointcut` )에 조언( `Advice` )을 해야할지 알고 있다.

**역할과 책임**

이렇게 구분한 것은 역할과 책임을 명확하게 분리한 것이다.

포인트컷은 대상 여부를 확인하는 필터 역할만 담당한다.

어드바이스는 깔끔하게 부가 기능 로직만 담당한다.

둘을 합치면 어드바이저가 된다. 스프링의 어드바이저는 하나의 포인트컷 + 하나의 어드바이스로 구성된다.

해당 단어들에 대한 정의는 지금은 문맥상 이해를 돕기 위해 프록시에 맞추어서 설명했다.

그림은 이해를 돕기 위한 것이고, 실제 구현은 약간 다를 수 있다.

<img width="707" alt="Screenshot 2024-10-13 at 15 51 39" src="https://github.com/user-attachments/assets/9946309a-a9d2-4248-b47f-15f5a479c19a">

### 예제1

어드바이저는 하나의 포인트컷과 하나의 어드바이스를 가지고 있다.

프록시 팩토리를 통해 프록시를 생성할 때 어드바이저를 제공하면 어디에 어떤 기능을 제공할지 알 수 있다.

```java
void test() {
    ServiceInterface target = new ServiceImpl();

    ProxyFactory proxyFactory = new ProxyFactory(target);

    DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
    proxyFactory.addAdvisor(defaultPointcutAdvisor);

    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    proxy.save();
}
```
* `new DefaultPointcutAdvisor` : `Advisor` 인터페이스의 가장 일반적인 구현체이다. 
  * 생성자를 통해 하나 의 포인트컷과 하나의 어드바이스를 넣어주면 된다. 어드바이저는 하나의 포인트컷과 하나의 어드바이스로 구성 된다.
* `Pointcut.TRUE` : 항상 `true` 를 반환하는 포인트컷이다. 이후에 직접 포인트컷을 구현해볼 것이다.
* `new TimeAdvice()` : 앞서 개발한 `TimeAdvice` 어드바이스를 제공한다.
* `proxyFactory.addAdvisor(advisor)` : 프록시 팩토리에 적용할 어드바이저를 지정한다. 
  * 어드바이저는 내부에 포인트컷과 어드바이스를 모두 가지고 있다. 따라서 어디에 어떤 부가 기능을 적용해야 할지 어드바이저 하나로 알 수 있다. 
  * 프록시 팩토리를 사용할 때 어드바이저는 필수이다.
* 그런데 생각해보면 이전에 분명히 `proxyFactory.addAdvice(new TimeAdvice())` 이렇게 어드바이저가 아니라 어드바이스를 바로 적용했다. 
  * 이것은 단순히 편의 메서드이고 결과적으로 해당 메서드 내부에서 지금 코드와 똑같은 다음 어드바이저가 생성된다. 
  * `DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice())`

<img width="699" alt="Screenshot 2024-10-13 at 16 14 21" src="https://github.com/user-attachments/assets/c62ba0de-0474-4277-b022-4e46bab7bd72">

### 예제2

이번에는 `save()` 메서드에는 어드바이스 로직을 적용하지만, `find()` 메서드에는 어드바이스 로직을 적용하지 않도록 해보자.

물론 과거에 했던 코드와 유사하게 어드바이스에 로직을 추가해서 메서드 이름을 보고 코드를 실행할지 말지 분기를 타도 된다. 

하지만 이런 기능에 특화되어서 제공되는 것이 바로 포인트컷이다.

**Pointcut 관련 인터페이스 - 스프링 제공**

```java
public interface Pointcut {
     ClassFilter getClassFilter();
     MethodMatcher getMethodMatcher();
}
public interface ClassFilter {
     boolean matches(Class<?> clazz);
}
public interface MethodMatcher {
     boolean matches(Method method, Class<?> targetClass);
}
```

포인트컷은 크게 `ClassFilter` 와 `MethodMatcher` 둘로 이루어진다. 

이름 그대로 하나는 클래스가 맞는지, 하나 는 메서드가 맞는지 확인할 때 사용한다. 둘다 `true` 로 반환해야 어드바이스를 적용할 수 있다.

일반적으로 스프링이 이미 만들어둔 구현체를 사용하지만 개념 학습 차원에서 간단히 직접 구현해보자.

<img width="710" alt="Screenshot 2024-10-13 at 16 22 31" src="https://github.com/user-attachments/assets/c7671401-2231-4df0-a3f4-ce9a20354afb">

1. 클라이언트가 프록시의 `save()` 를 호출한다.
2. 포인트컷에 `Service` 클래스의 `save()` 메서드에 어드바이스를 적용해도 될지 물어본다.
3. 포인트컷이 `true` 를 반환한다. 따라서 어드바이스를 호출해서 부가 기능을 적용한다.
4. 이후 실제 인스턴스의 `save()` 를 호출한다.

<img width="708" alt="Screenshot 2024-10-13 at 16 24 57" src="https://github.com/user-attachments/assets/049a22cc-4664-4c64-ad31-82f75e408c1d">

1. 클라이언트가 프록시의 `find()` 를 호출한다.
2. 포인트컷에 `Service` 클래스의 `find()` 메서드에 어드바이스를 적용해도 될지 물어본다.
3. 포인트컷이 `false` 를 반환한다. 따라서 어드바이스를 호출하지 않고, 부가 기능도 적용되지 않는다.
4. 실제 인스턴스를 호출한다.

### 예제 코드3 - 스프링이 제공하는 포인트컷

```java
void advisorTest3() {
     ServiceImpl target = new ServiceImpl();
     ProxyFactory proxyFactory = new ProxyFactory(target);
     NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
     pointcut.setMappedNames("save");
     DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());
     proxyFactory.addAdvisor(advisor);
     ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
     proxy.save();
     proxy.find();
}
```

**스프링이 제공하는 포인트컷**

스프링은 무수히 많은 포인트컷을 제공한다. 대표적인 몇가지만 알아보자.

* `NameMatchMethodPointcut` : 메서드 이름을 기반으로 매칭한다. 내부에서는 `PatternMatchUtils` 를 사용한다. 
  * 예) `*xxx*` 허용
* `JdkRegexpMethodPointcut` : JDK 정규 표현식을 기반으로 포인트컷을 매칭한다.
* `TruePointcut` : 항상 참을 반환한다. 
* `AnnotationMatchingPointcut` : 애노테이션으로 매칭한다. 
* `AspectJExpressionPointcut` : aspectJ 표현식으로 매칭한다.

**가장 중요한 것은 aspectJ 표현식**

여기에서 사실 다른 것은 중요하지 않다. 

실무에서는 사용하기도 편리하고 기능도 가장 많은 aspectJ 표현식을 기반으 로 사용하는 `AspectJExpressionPointcut` 을 사용하게 된다.

aspectJ 표현식과 사용방법은 중요해서 이후 AOP를 설명할 때 자세히 설명하겠다.

지금은 `Pointcut` 의 동작 방식과 전체 구조에 집중하자.

### 예제 코드4 - 여러 어드바이저 함께 적용


어드바이저는 하나의 포인트컷과 하나의 어드바이스를 가지고 있다.

만약 여러 어드바이저를 하나의 `target` 에 적용하려면 어떻게 해야할까?

쉽게 이야기해서 하나의 `target` 에 여러 어드바이스를 적용하려면 어떻게 해야할까?

지금 떠오르는 방법은 프록시를 여러게 만들면 될 것 같다.

<img width="695" alt="Screenshot 2024-10-13 at 16 40 08" src="https://github.com/user-attachments/assets/319282cd-9646-43f9-9363-2ac597c05192">

**여러 프록시의 문제**

이 방법이 잘못된 것은 아니지만, 프록시를 2번 생성해야 한다는 문제가 있다. 

만약 적용해야 하는 어드바이저가 10개 라면 10개의 프록시를 생성해야한다.

**하나의 프록시, 여러 어드바이저**

<img width="691" alt="Screenshot 2024-10-13 at 16 49 50" src="https://github.com/user-attachments/assets/ef2046a6-b804-4afb-b82b-1462eed50677">

스프링은 이 문제를 해결하기 위해 하나의 프록시에 여러 어드바이저를 적용할 수 있게 만들어두었다.

```java
@Test
@DisplayName("하나의 프록시, 여러 어드바이저") void multiAdvisorTest2() {
     //proxy -> advisor2 -> advisor1 -> target
     DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
     DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
     ServiceInterface target = new ServiceImpl();
     ProxyFactory proxyFactory1 = new ProxyFactory(target);
     proxyFactory1.addAdvisor(advisor2);
     proxyFactory1.addAdvisor(advisor1);
     ServiceInterface proxy = (ServiceInterface) proxyFactory1.getProxy();
     proxy.save();
}
```

<img width="693" alt="Screenshot 2024-10-13 at 16 55 30" src="https://github.com/user-attachments/assets/f0ae1d38-c798-4ee4-a0fb-a0950a376197">

프록시 팩토리에 원하는 만큼 `addAdvisor()` 를 통해서 어드바이저를 등록하면 된다.

등록하는 순서대로 `advisor` 가 호출된다. 여기서는 `advisor2` , `advisor1` 순서로 등록했다.

**정리**

결과적으로 여러 프록시를 사용할 때와 비교해서 결과는 같고, 성능은 더 좋다.

**중요**

사실 이번 장을 이렇게 풀어서 설명한 이유가 있다. 

스프링의 AOP를 처음 공부하거나 사용하면, AOP 적용 수 만큼 프록시가 생성된다고 착각하게 된다. 

실제 많은 실무 개발자들도 이렇게 생각하는 것을 보았다.

스프링은 AOP를 적용할 때, 최적화를 진행해서 지금처럼 프록시는 하나만 만들고, 하나의 프록시에 여러 어드바이저를 적용한다.

정리하면 하나의 `target` 에 여러 AOP가 동시에 적용되어도, 스프링의 AOP는 `target` 마다 하나의 프록시만 생성한다. 

이부분을 꼭 기억해두자.

## 정리

프록시 팩토리 덕분에 개발자는 매우 편리하게 프록시를 생성할 수 있게 되었다.

추가로 어드바이저, 어드바이스, 포인트컷 이라는 개념 덕분에 **어떤 부가 기능**을 **어디에 적용**할 지 명확하게 이해할 수 있었다.

**남은 문제**

프록시 팩토리와 어드바이저 같은 개념 덕분에 지금까지 고민했던 문제들은 해결되었다. 

프록시도 깔끔하게 적용하고 포인트컷으로 어디에 부가 기능을 적용할지도 명확하게 정의할 수 있다. 

원본 코드를 전혀 손대지 않고 프록시를 통해 부가 기능도 적용할 수 있었다.

그런데 아직 해결되지 않는 문제가 있다.

**문제1 - 너무 많은 설정**

바로 `ProxyFactoryConfigV1` , `ProxyFactoryConfigV2` 와 같은 설정 파일이 지나치게 많다는 점이다.

예를 들어서 애플리케이션에 스프링 빈이 100개가 있다면 여기에 프록시를 통해 부가 기능을 적용하려면 100개의 동적 프록시 생성 코드를 만들어야 한다! 

무수히 많은 설정 파일 때문에 설정 지옥을 경험하게 될 것이다.

최근에는 스프링 빈을 등록하기 귀찮아서 컴포넌트 스캔까지 사용하는데, 이렇게 직접 등록하는 것도 모자라서, 프록시 를 적용하는 코드까지 빈 생성 코드에 넣어야 한다.

**문제2 - 컴포넌트 스캔**

애플리케이션 V3처럼 컴포넌트 스캔을 사용하는 경우 지금까지 학습한 방법으로는 프록시 적용이 불가능하다. 

왜냐하면 실제 객체를 컴포넌트 스캔으로 스프링 컨테이너에 스프링 빈으로 등록을 다 해버린 상태이기 때문이다. 

지금까지 학습한 프록시를 적용하려면, 실제 객체를 스프링 컨테이너에 빈으로 등록하는 것이 아니라 `ProxyFactoryConfigV1` 에서 한 것 처럼, 부가 기능이 있는 프록시를 실제 객체 대신 스프링 컨테이너에 빈으로 등록해야 한다.

**두 가지 문제를 한번에 해결하는 방법이 바로 다음에 설명할 빈 후처리기이다.**

# 빈 후처리기

<img width="691" alt="Screenshot 2024-10-14 at 22 08 42" src="https://github.com/user-attachments/assets/b1cb04ee-e1e4-48a3-8d70-a7ce38243c19">

`@Bean` 이나 컴포넌트 스캔으로 스프링 빈을 등록하면, 스프링은 대상 객체를 생성하고 스프링 컨테이너 내부의 빈 저장소에 등록한다. 

그리고 이후에는 스프링 컨테이너를 통해 등록한 스프링 빈을 조회해서 사용하면 된다.

**빈 후처리기 - BeanPostProcessor**

스프링이 빈 저장소에 등록할 목적으로 생성한 객체를 빈 저장소에 등록하기 직전에 조작하고 싶다면 빈 후처리기를 사용하면 된다.

빈 포스트 프로세서( `BeanPostProcessor` )는 번역하면 빈 후처리기인데, 이름 그대로 빈을 생성한 후에 무언가를 처리하는 용도로 사용한다.

**빈 후처리기 기능**

빈 후처리기의 기능은 막강하다.

객체를 조작할 수도 있고, 완전히 다른 객체로 바꿔치기 하는 것도 가능하다.

**빈 후처리기 과정**

<img width="691" alt="Screenshot 2024-10-14 at 22 09 39" src="https://github.com/user-attachments/assets/006f6038-82b5-4a5e-bc60-83a75462945a">

**빈 등록 과정을 빈 후처리기와 함께 살펴보자**

<img width="689" alt="Screenshot 2024-10-14 at 22 10 34" src="https://github.com/user-attachments/assets/808c103a-bf1a-495c-abd4-da3317ed1af1">

* 1. 생성: 스프링 빈 대상이 되는 객체를 생성한다. ( `@Bean` , 컴포넌트 스캔 모두 포함)
* 2. 전달: 생성된 객체를 빈 저장소에 등록하기 직전에 빈 후처리기에 전달한다.
* 3. 후 처리 작업: 빈 후처리기는 전달된 스프링 빈 객체를 조작하거나 다른 객체로 바뀌치기 할 수 있다.
* 4. 등록: 빈 후처리기는 빈을 반환한다. 전달 된 빈을 그대로 반환하면 해당 빈이 등록되고, 바꿔치기 하면 다른 객 체가 빈 저장소에 등록된다.

**BeanPostProcessor 인터페이스 - 스프링 제공** 

```java
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
```
빈 후처리기를 사용하려면 `BeanPostProcessor` 인터페이스를 구현하고, 스프링 빈으로 등록하면 된다. 

`postProcessBeforeInitialization` : 객체 생성 이후에 `@PostConstruct` 같은 초기화가 발생하기 전에 호출되는 포스트 프로세서이다.

`postProcessAfterInitialization` : 객체 생성 이후에 `@PostConstruct` 같은 초기화가 발생한 다음에 호출되는 포스트 프로세서이다.

**정리**

빈 후처리기는 빈을 조작하고 변경할 수 있는 후킹 포인트이다.

이것은 빈 객체를 조작하거나 심지어 다른 객체로 바꾸어 버릴 수 있을 정도로 막강하다.

여기서 조작이라는 것은 해당 객체의 특정 메서드를 호출하는 것을 뜻한다.

일반적으로 스프링 컨테이너가 등록하는, 특히 컴포넌트 스캔의 대상이 되는 빈들은 중간에 조작할 방법이 없는데, 빈 후처리기를 사용하면 개발자가 등록하는 모든 빈을 중간에 조작할 수 있다. 

이 말은 **빈 객체를 프록시로 교체**하는 것도 가능하다는 뜻이다.

**참고 - @PostConstruct의 비밀**

`@PostConstruct` 는 스프링 빈 생성 이후에 빈을 초기화 하는 역할을 한다. 

그런데 생각해보면 빈의 초기화 라는 것이 단순히 `@PostConstruct` 애노테이션이 붙은 초기화 메서드를 한번 호출만 하면 된다. 

쉽게 이야기해서 생성된 빈을 한번 조작하는 것이다.

따라서 빈을 조작하는 행위를 하는 적절한 빈 후처리기가 있으면 될 것 같다.

스프링은 `CommonAnnotationBeanPostProcessor` 라는 빈 후처리기를 자동으로 등록하는데, 여기에서 `@PostConstruct` 애노테이션이 붙은 메서드를 호출한다. 

따라서 스프링 스스로도 스프링 내부의 기능을 확장 하기 위해 빈 후처리기를 사용한다.

## 빈 후처리기 - 적용

<img width="692" alt="Screenshot 2024-10-14 at 22 26 17" src="https://github.com/user-attachments/assets/c8f75692-33bc-42bb-a111-b0de6d64b0b5">

빈 후처리기를 사용해서 실제 객체 대신 프록시를 스프링 빈으로 등록해보자.

이렇게 하면 수동으로 등록하는 빈은 물론이고, 컴포넌트 스캔을 사용하는 빈까지 모두 프록시를 적용할 수 있다. 

더 나아가서 설정 파일에 있는 수 많은 프록시 생성 코드도 한번에 제거할 수 있다.

```java
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final String basePackage;
    private final Advisor advisor;

    public PackageLogTracePostProcessor(String basePackage, Advisor advisor) {
        this.basePackage = basePackage;
        this.advisor = advisor;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("bean, beanName {} {}", bean, beanName.getClass());

        String packageName = bean.getClass().getPackageName();

        if(!packageName.startsWith(basePackage)) {
            return bean;
        } else {
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            proxyFactory.addAdvisor(advisor);
            //proxyFactory.setProxyTargetClass(true);

            Object proxy = proxyFactory.getProxy();

            log.info("proxy {}", proxy.getClass());

            return proxyFactory.getProxy();
        }
    }
}
```

`PackageLogTraceProxyPostProcessor` 는 원본 객체를 프록시 객체로 변환하는 역할을 한다. 

이때 프록시 팩토리를 사용하는데, 프록시 팩토리는 `advisor` 가 필요하기 때문에 이 부분은 외부에서 주입 받도록 했다. 

모든 스프링 빈들에 프록시를 적용할 필요는 없다. 

여기서는 특정 패키지와 그 하위에 위치한 스프링 빈들만 프록 시를 적용한다. 여기서는 `hello.proxy.app` 과 관련된 부분에만 적용하면 된다. 

다른 패키지의 객체들은 원본 객체를 그대로 반환한다.

프록시 적용 대상의 반환 값을 보면 원본 객체 대신에 프록시 객체를 반환한다. 

따라서 스프링 컨테이너에 원본 객 체 대신에 프록시 객체가 스프링 빈으로 등록된다. 원본 객체는 스프링 빈으로 등록되지 않는다.

```java
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessConfig {

    @Bean
    public PackageLogTracePostProcessor packageLogTracePostProcessor(LogTrace logTrace) {

        return new PackageLogTracePostProcessor("hello.proxy.app", getAdvisor(logTrace));
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice ad = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, ad);
    }
}
```
* `@Import({AppV1Config.class, AppV2Config.class})` : V3는 컴포넌트 스캔으로 자동으로 스프링 빈으로 등록되지만, V1, V2 애플리케이션은 수동으로 스프링 빈으로 등록해야 동작한다. 

* `ProxyApplication` 에서 등록해도 되지만 편의상 여기에 등록하자.

* `@Bean logTraceProxyPostProcessor()` : 특정 패키지를 기준으로 프록시를 생성하는 빈 후처리기를 스프링 빈으로 등록한다. 빈 후처리기는 스프링 빈으로만 등록하면 자동으로 동작한다. 여기에 프록시를 적용할 패 키지 정보( `hello.proxy.app` )와 어드바이저( `getAdvisor(logTrace)` )를 넘겨준다.
* 
이제 **프록시를 생성하는 코드가 설정 파일에는 필요 없다.** 

순수한 빈 등록만 고민하면 된다. 

프록시를 생성하고 프 록시를 스프링 빈으로 등록하는 것은 빈 후처리기가 모두 처리해준다.

실행해보면 스프링 부트가 기본으로 등록하는 수 많은 빈들이 빈 후처리기를 통과하는 것을 확인할 수 있다. 

여기에 모두 프록시를 적용하는 것은 올바르지 않다. 

꼭 필요한 곳에만 프록시를 적용해야 한다. 

여기서는 `basePackage` 를 사용해서 v1~v3 애플리케이션 관련 빈들만 프록시 적용 대상이 되도록 했다.

**v1:** 인터페이스가 있으므로 JDK 동적 프록시가 적용된다.

**v2:** 구체 클래스만 있으므로 CGLIB 프록시가 적용된다.

**v3:** 구체 클래스만 있으므로 CGLIB 프록시가 적용된다.

**컴포넌트 스캔에도 적용**

여기서 중요한 포인트는 v1, v2와 같이 수동으로 등록한 빈 뿐만 아니라 컴포넌트 스캔을 통해 등록한 v3 빈들도 프록 시를 적용할 수 있다는 점이다. 

이것은 모두 빈 후처리기 덕분이다.

**프록시 적용 대상 여부 체크**

애플리케이션을 실행해서 로그를 확인해보면 알겠지만, 우리가 직접 등록한 스프링 빈들 뿐만 아니라 스프링 부트 가 기본으로 등록하는 수 많은 빈들이 빈 후처리기에 넘어온다. 

그래서 어떤 빈을 프록시로 만들 것인지 기준이 필요하다.

여기서는 간단히 `basePackage` 를 사용해서 특정 패키지를 기준으로 해당 패키지와 그 하위 패키지의 빈들을 프록시로 만든다.

스프링 부트가 기본으로 제공하는 빈 중에는 프록시 객체를 만들 수 없는 빈들도 있다. 

따라서 모든 객체를 프록시로 만들 경우 오류가 발생한다.

## 빈 후처리기 - 정리

**문제1 - 너무 많은 설정**

프록시를 직접 스프링 빈으로 등록하는 `ProxyFactoryConfigV1` , `ProxyFactoryConfigV2` 와 같은 설정 파일은 프록시 관련 설정이 지나치게 많다는 문제가 있다.

예를 들어서 애플리케이션에 스프링 빈이 100개가 있다면 여기에 프록시를 통해 부가 기능을 적용하려면 100개의 프록시 설정 코드가 들어가야 한다. 

무수히 많은 설정 파일 때문에 설정 지옥을 경험하게 될 것이다.

스프링 빈을 편리하게 등록하려고 컴포넌트 스캔까지 사용하는데, 이렇게 직접 등록하는 것도 모자라서, 프록시를 적용 하는 코드까지 빈 생성 코드에 넣어야 했다.

**문제2 - 컴포넌트 스캔**

애플리케이션 V3처럼 컴포넌트 스캔을 사용하는 경우 지금까지 학습한 방법으로는 프록시 적용이 불가능했다. 

왜냐하면 컴포넌트 스캔으로 이미 스프링 컨테이너에 실제 객체를 스프링 빈으로 등록을 다 해버린 상태이기 때문이다. 

좀 더 풀어서 설명하자면, 지금까지 학습한 방식으로 프록시를 적용하려면, 원본 객체를 스프링 컨테이너에 빈으로 등록 하는 것이 아니라 `ProxyFactoryConfigV1` 에서 한 것 처럼, 프록시를 원본 객체 대신 스프링 컨테이너에 빈으로 등록해야 한다. 

그런데 컴포넌트 스캔은 원본 객체를 스프링 빈으로 자동으로 등록하기 때문에 프록시 적용이 불가능하다.

**문제 해결**

빈 후처리기 덕분에 프록시를 생성하는 부분을 하나로 집중할 수 있다. 

그리고 컴포넌트 스캔처럼 스프링이 직접 대상을 빈으로 등록하는 경우에도 중간에 빈 등록 과정을 가로채서 원본 대신에 프록시를 스프링 빈으로 등록할 수 있다. 

덕분에 애플리케이션에 수 많은 스프링 빈이 추가되어도 프록시와 관련된 코드는 전혀 변경하지 않아도 된다. 

그리고 컴포넌트 스캔을 사용해도 프록시가 모두 적용된다.

**하지만 개발자의 욕심은 끝이 없다.**

스프링은 프록시를 생성하기 위한 빈 후처리기를 이미 만들어서 제공한다.

**중요**
프록시의 적용 대상 여부를 여기서는 간단히 패키지를 기준으로 설정했다. 

그런데 잘 생각해보면 포인트컷을 사용하면 더 깔끔할 것 같다.

포인트컷은 이미 클래스, 메서드 단위의 필터 기능을 가지고 있기 때문에, 프록시 적용 대상 여부를 정밀하게 설정 할 수 있다.

참고로 어드바이저는 포인트컷을 가지고 있다. 따라서 어드바이저를 통해 포인트컷을 확인할 수 있다.

뒤에서 학습하겠지만 스프링 AOP는 포인트컷을 사용해서 프록시 적용 대상 여부를 체크한다.

결과적으로 포인트컷은 다음 두 곳에 사용된다.

1. 프록시 적용 대상 여부를 체크해서 꼭 필요한 곳에만 프록시를 적용한다.(빈 후처리기-자동 프록시 생성)
2. 프록시의 어떤 메서드가 호출 되었을 때 어드바이스를 적용할 지 판단한다. (프록시 내부)

## 스프링 빈 후처리기

**build.gradle - 추가** 

```groovy
implementation 'org.springframework.boot:spring-boot-starter-aop' 
```
이 라이브러리를 추가하면 `aspectjweaver` 라는 `aspectJ` 관련 라이브러리를 등록하고, 스프링 부트가 AOP 관련 클래스를 자동으로 스프링 빈에 등록한다. 

스프링 부트가 없던 시절에는 `@EnableAspectJAutoProxy` 를 직접 사용해야 했는데, 이 부분을 스프링 부트가 자동으로 처리해준다. 

`aspectJ` 는 뒤에서 설명한다. 스프링 부트가 활성화하는 빈은 `AopAutoConfiguration` 를 참고하자.

**자동 프록시 생성기 - AutoProxyCreator**

앞서 이야기한 스프링 부트 자동 설정으로 `AnnotationAwareAspectJAutoProxyCreator` 라는 빈 후처리 기가 스프링 빈에 자동으로 등록된다.

이름 그대로 자동으로 프록시를 생성해주는 빈 후처리기이다.

이 빈 후처리기는 스프링 빈으로 등록된 `Advisor` 들을 자동으로 찾아서 프록시가 필요한 곳에 자동으로 프록시를 적용해준다.

`Advisor` 안에는 `Pointcut` 과 `Advice` 가 이미 모두 포함되어 있다. 

따라서 `Advisor` 만 알고 있으면 그 안에 있는 `Pointcut` 으로 어떤 스프링 빈에 프록시를 적용해야 할지 알 수 있다. 

그리고 `Advice` 로 부가 기능을 적용하면 된다.

**참고**

`AnnotationAwareAspectJAutoProxyCreator` 는 @AspectJ와 관련된 AOP 기능도 자동으로 찾아서 처리해준다.

`Advisor` 는 물론이고, `@Aspect` 도 자동으로 인식해서 프록시를 만들고 AOP를 적용해준다. `@Aspect` 에 대한 자세한 내용은 뒤에 설명한다.

**자동 프록시 생성기의 작동 과정 - 그림**


**자동 프록시 생성기의 작동 과정을 알아보자**

1. 생성: 스프링이 스프링 빈 대상이 되는 객체를 생성한다. ( `@Bean` , 컴포넌트 스캔 모두 포함)
2. 전달: 생성된 객체를 빈 저장소에 등록하기 직전에 빈 후처리기에 전달한다.
3. 모든 Advisor 빈 조회: 자동 프록시 생성기 - 빈 후처리기는 스프링 컨테이너에서 모든 `Advisor` 를 조회한다.
4. 프록시 적용 대상 체크: 앞서 조회한 `Advisor` 에 포함되어 있는 포인트컷을 사용해서 해당 객체가 프록시를 적용할 대상인지 아닌지 판단한다. 
   * 이때 객체의 클래스 정보는 물론이고, 해당 객체의 모든 메서드를 포인트컷에 하나하나 모두 매칭해본다. 
   * 그래서 조건이 하나라도 만족하면 프록시 적용 대상이 된다. 
   * 예를 들어서 10개의 메서드 중에 하나만 포인트컷 조건에 만족해도 프록시 적용 대상이 된다.
5. 프록시 생성: 프록시 적용 대상이면 프록시를 생성하고 반환해서 프록시를 스프링 빈으로 등록한다. 
   * 만약 프록 시 적용 대상이 아니라면 원본 객체를 반환해서 원본 객체를 스프링 빈으로 등록한다.
6. 빈 등록: 반환된 객체는 스프링 빈으로 등록된다.

**생성된 프록시**




```java
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

    
    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice ad = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, ad);
    }
}
```

`AutoProxyConfig` 코드를 보면 `advisor1` 이라는 어드바이저 하나만 등록했다.

빈 후처리기는 이제 등록하지 않아도 된다. 

스프링은 자동 프록시 생성기라는 ( `AnnotationAwareAspectJAutoProxyCreator` ) 빈 후처리기를 자동으로 등록해준다.


### 중요: 포인트컷은 2가지에 사용된다.

**1. 프록시 적용 여부 판단 - 생성 단계**

자동 프록시 생성기는 포인트컷을 사용해서 해당 빈이 프록시를 생성할 필요가 있는지 없는지 체크한다. 

클래스 + 메서드 조건을 모두 비교한다. 

이때 모든 메서드를 체크하는데, 포인트컷 조건에 하나하나 매칭해 본다. 

만약 조건에 맞는 것이 하나라도 있으면 프록시를 생성한다.

예) `orderControllerV1` 은 `request()` , `noLog()` 가 있다. 여기에서 `request()` 가 조건에 만족하므로 프록시를 생성한다.

만약 조건에 맞는 것이 하나도 없으면 프록시를 생성할 필요가 없으므로 프록시를 생성하지 않는다.

**2. 어드바이스 적용 여부 판단 - 사용 단계**

프록시가 호출되었을 때 부가 기능인 어드바이스를 적용할지 말지 포인트컷을 보고 판단한다. 

앞서 설명한 예에서 `orderControllerV1` 은 이미 프록시가 걸려있다.

`orderControllerV1` 의 `request()` 는 현재 포인트컷 조건에 만족하므로 프록시는 어드바이스를 먼저 호출하고, `target` 을 호출한다.

`orderControllerV1` 의 `noLog()` 는 현재 포인트컷 조건에 만족하지 않으므로 어드바이스를 호출하지 않고 바로 `target` 만 호출한다.

**참고:** 

프록시를 모든 곳에 생성하는 것은 비용 낭비이다. 꼭 필요한 곳에 최소한의 프록시를 적용해야 한다. 

그래서 자동 프록시 생성기는 모든 스프링 빈에 프록시를 적용하는 것이 아니라 포인트컷으로 한번 필터링해서 어드바이스가 사용될 가능성이 있는 곳에만 프록시를 생성한다.


























