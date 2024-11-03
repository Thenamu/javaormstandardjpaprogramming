## 📓 INDEX

### 개념
- [JPA란?](https://github.com/Thenamu/javaormstandardjpaprogramming/blob/a12bf47ec3c5faa5e13bb1cb446dfa1444f04461/1.%EA%B0%9C%EB%85%90/JPA%EB%9E%80.md)
- [Spring이 없는 JPA](https://github.com/Thenamu/javaormstandardjpaprogramming/blob/a12bf47ec3c5faa5e13bb1cb446dfa1444f04461/1.%EA%B0%9C%EB%85%90/Spring%EC%97%86%EB%8A%94JPA.md)
- [JPQL](https://github.com/Thenamu/javaormstandardjpaprogramming/blob/a12bf47ec3c5faa5e13bb1cb446dfa1444f04461/1.%EA%B0%9C%EB%85%90/JPQL.md)

<br/>

# JPA - Java Persistence API

## 순수 JDBC 등록

과거에는 객체를 데이터베이스에 저장을 하려면 이렇게 복잡한 JDBC AP와 SQL을 한 땀 한 땀 직접 작성해야 했다

## JdbcTemplate

JDBC 템플릿이나 마이바티스 같은 SQL 매퍼가 등장해서 개발 코드는 많이 줄었지만 여전히 개발자가 SQL을 한 땀 한 땀 직접 작성해야 한다

## JPA

JPA를 사용하면 SQL 조차도 작성할 필요가 없어진다. JPA가 개발자 대신에 적절한 SQL을 생성하고 데이터베이스에 실행을 해서 객체를 저장하거나 불러오게 된다.

JPA에서는 객체와 테이블을 잘 설계하고 정확하게 매핑하는 것이 중요

### 객체와 테이블 설계 매핑

| 항목                       | 설명                                       |
|----------------------------|--------------------------------------------|
| 객체와 테이블을 제대로 설계 | 객체와 테이블을 제대로 설계하고 매핑하는 방법 |
| 기본 키과 외래 키 매핑     | 1:N, N:1, 1:1, N:M 매핑                   |

<br/>

## JPA 내부 동작 방식 이해

JPA가 어떤 SQL을 만들어 내는지 이해  
JPA가 언제 SQL을 실행하는지 이해

<br/>

## JPA와 모던 자바 데이터 저장 기술

### SQL 중심적인 개발의 문제점

- 지금 시대는 객체를 관계형 DB에 관리
- SQL 쿼리를 무한 반복해서 작성해야 하고 자바 객체를 SQL로...
- SQL을 자바 객체로...
- 객체 필드를 하나 추가하게 되면 Query문을 전부 수정해야 한다
- 관계형 데이터베이스를 사용하고 관계형 데이터베이스와 통신하려면 결과적으로 SQL의 의존적인 개발을 피하기는 어렵다

### 패러다임의 불일치

**객체 vs 관계형 데이터베이스**

객체랑 관계형 데이터베이스의 테이블은 광장히 다르다

- **객체 지향 프로그래밍**은 추상화, 캡슐화, 정보 은닉, 상속, 다형성 등 시스템의 복잡성을 제어할 수 있는 다양한 장치들을 제공한다.
- 객체를 영구적으로 보관하는 저장소로 현실적인 대안은 관계형 데이터베이스를 사용할 수밖에 없다.
- NoSQL은 보조적인 수단으로 많이 사용한다.

### 객체를 관계형 데이터베이스에 저장

객체 → <SQL 변환> → SQL → RDB

예시로 회원을 DB에 저장하려고 하면 회원 객체를 SQL로 변환해야 하고 조회할 때도 SQL로 조회한 다음에 그걸 객체로 변환해야 한다

개발자가 결국 SQL 매퍼의 일을 하게 된다

<br/>

## 객체와 관계형 데이터베이스의 차이

| 차이점      | 객체                                      | 관계형 데이터베이스                         |
|-------------|-------------------------------------------|--------------------------------------------|
| 1. 상속     | 객체는 상속 관계가 있다                    | 기본적으로 객체에서 생각하는 상속 관계가 없다 |
| 2. 연관관계 | 객체는 참조를 사용 ex) `member.getTeam()`   | 테이블은 외래 키를 사용 ex) `JOIN ON M.TEAM_ID = T.TEAM_ID` |
| 3. 데이터 타입 | 다양한 객체 필드 타입 사용                 | 정해진 데이터 타입 사용                     |
| 4. 데이터 식별 방법 | 객체 참조를 통한 식별                     | 기본 키와 외래 키를 통한 식별               |

### 1. 상속

객체는 상속 관계가 있는데 관계형 데이터베이스에는 기본적으로 객체에서 생각하는 상속관계는 존재하지 않는다. 비슷한 것이 있는 데이터베이스도 있지만 객체랑 완전히 똑같지는 않다.

보통 관계형 데이터베이스에서 이 문제를 해결하는 방법은 부모 테이블과 자식 테이블을 생성해서 데이터를 분리하고 필요할 때 조인해서 가져오는 것이다.  
= 슈퍼 타입, 서브 타입 관계

### 2. 연관관계

- **객체**: 참조를 사용 ex) `member.getTeam()`
- **테이블**: 외래 키를 사용 ex) `JOIN ON M.TEAM_ID = T.TEAM_ID`

테이블은 객체 레퍼런스 즉, 참조가 없다. 대신 외래키로 조인을 해서 연관관계를 맺게 된다.

#### 객체를 테이블에 맞춰서 모델링

- **객체다운 모델링**: 참조로 연관관계를 맺는다. 객체답게 모델링을 하게 되면 데이터베이스에 Insert 하기가 굉장히 까다로워진다.

##### 객체 그래프 탐색

객체는 자유롭게 객체 그래프를 탐색할 수 있어야 한다.

데이터베이스에 객체를 보관하다 보면 처음에 어떤 SQL을 실행해서 멤버 객체를 만들었냐에 따라서 탐색 범위가 결정되어 버린다.

예를 들어 Member와 Team을 조회하는 Query를 작성하고 연관관계 세팅을 했다면:

- `member.getTeam()`은 가능하지만
- `member.getOrder()`는 null을 반환한다

즉 탐색 범위가 처음 실행하는 SQL에 따라서 결정된다.  
이러면 엔티티 신뢰 문제가 발생한다.

### 계층의 아키텍처

계층의 아키텍처라는 것은 개발자가 다음 계층을 믿고 쓸 수 있어야 한다.

```java
class MemberService {
    public void process() {
        Member member = memberDAO.find(memberId);
        member.getTeam(); // ???
        member.getOrder().getDelivery(); // ???
    }	
}
```

<br/>

## memberDAO의 find메소드를 확인해봐야 한다

다음 계층을 개발자가 코드를 들어가서 확인해봐야 한다.  
개발자가 서비스 로직을 개발하는데 DAO 코드를 열어서 어떤 Query를 가지고 세팅을 했는지를 봐야 탐색 범위를 확인할 수 있다.

## 모든 객체를 미리 로딩할 수는 없다

그렇다면 모든 객체를 미리 로딩할 수 있을까?  
객체에 필드가 전부 존재한다는 가정하에 Query로 객체 그래프 참조를 마음대로 가능하게 할 수 있을까?  
상당히 어렵다. SQL Query가 어마어마하게 길어질 것.  
전부 Join하고 그만큼 많은 Query가 나온다.  
사용하지도 않는 데이터를 무조건 다 퍼올려놔야 하니까 성능도 나오지 않는다.

## 보통 개발할 때는

- SQL을 사용해서 `memberDAO.getMember();` 를 만들면 멤버만 조회
- `memberDAO.getMemberWithTeam`는 멤버랑 팀까지 세팅

결국 계층형 아키텍처, 진정한 의미의 **계층분할**이 어렵다.  
물리적으로는 분할이 되어 있으나 논리적으로는 엮여 있다.

<br/>

## 비교하기

### 객체 비교

```java
String memberId = "100";
Member member1 = memberDAO.getMember(memberId);
Member member2 = memberDAO.getMember(memberId);

member1 == member2; // 인스턴스 비교는 다르다
```
```java
class MemberDAO {
    public Member getMember(String memberId) {
        String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
        
        return new Member(...);
    }
}
```

<br/>

## 비교하기 - 자바 컬렉션에서 조회

```java
String memberId = "100";
Member member1 = list.get(memberId);
Member member2 = list.get(memberId);

member1 == member2; // 같다
```

<br/>

# 객체 지향 모델링과 JPA

**결론**, 객체답게 모델링할수록 매핑 작업만 늘어난다.

---

객체를 **자바 컬렉션**에 저장하듯이 **DB**에 저장할 수는 없을까?

그 문제를 해결해주는 것이 바로 **JPA - Java Persistence API**다.
