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




















