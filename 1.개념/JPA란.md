# 🔥 JPA란?
**Java Persistence API**  
자바 진영의 **ORM 기술 표준**

## ORM?
**Object-relational mapping** (객체 관계 매핑)

- 객체는 객체대로 설계
- 관계형 데이터베이스는 관계형 데이터베이스대로 설계
- ORM 프레임워크가 중간에서 매핑
- 대중적인 언어에는 대부분 ORM 기술이 존재한다

<br/>

## JPA는 애플리케이션과 JDBC 사이에서 동작
![4.PNG](..%2Fimg%2F4.PNG)

Java 애플리케이션에서 DB랑 통신하려면 **JDBC API**를 사용해야 한다. 개발자가 직접 JDBC API를 썼다면 JPA가 대신 처리해준다.

**비교:**

| 프레임워크      | 설명                                |
|-----------------|-------------------------------------|
| JdbcTemplate    | Spring에서 제공하는 JDBC 편의 클래스 |
| MyBatis         | SQL 매핑 프레임워크                  |
| **JPA**         | ORM 표준, 객체 중심 매핑             |

<br/>

## JPA 동작 - 저장
![1.PNG](..%2Fimg%2F1.PNG)

멤버 객체를 저장하는 예시를 생각해보자.

1. 멤버 객체를 멤버 DAO에 넘김
2. 멤버 DAO가 JPA에게 멤버 엔티티를 저장해달라고 요청
3. JPA가 멤버 객체를 분석
4. **INSERT SQL**을 자동으로 생성하고, **JDBC API**를 사용해서 데이터베이스에 **INSERT Query**를 실행

> 더 중요한 것은 **패러다임의 불일치**를 해결해준다.

> 마치 Java Collection을 저장하듯이 한 줄의 코드만 작성하면 된다.

<br/>

## JPA 동작 - 조회
1. JPA에게 아이디만 전달
2. JPA가 멤버 객체를 분석
3. **SELECT SQL**을 자동으로 생성
4. **JDBC API**를 사용해서 **ResultSet**을 매핑
5. 패러다임의 불일치를 해결
6. **Entity Object**를 생성하여 반환

<br/>

## JPA 소개
자바 진영에서 **EJB**라는 것이 있었는데 두 가지 큰 기능을 가지고 있었다:

1. Spring에서 사용하는 컨테이너 기술 같은 **EJB 영역**
2. 지금의 JPA라고 볼 수 있는 **엔티티 빈**

### JPA의 역사
- **JPA**는 엔티티 빈으로부터 출발
- EJB의 **파멸적인 성능과 복잡함** 때문에 오픈소스인 **Hibernate**가 탄생
- 오픈소스 **Hibernate** 출발한 자바 진영 표준이 **JPA**

### JPA = 표준 명세
![2.PNG](..%2Fimg%2F2.PNG)

JPA는 **인터페이스의 모음**

**JPA 2.1 표준 명세를 구현한 3가지 구현체:**

| 구현체        | 설명                       |
|---------------|----------------------------|
| Hibernate     | 가장 널리 사용되는 구현체    |
| EclipseLink   | Eclipse 기반의 구현체       |
| DataNucleus   | 다양한 데이터 소스를 지원  |

> 거의 **Hibernate**를 구현체로 사용한다.

<br/>

## JPA를 왜 사용해야 하는가?

| 이유                              | 설명                                     |
|-----------------------------------|------------------------------------------|
| **SQL 중심적인 개발에서 객체 중심으로 개발** | SQL 중심적인 개발에서 객체 중심으로 전환 |
| **생산성**                        | 간결한 코드로 데이터 조작 가능           |
| **유지보수**                      | 필드 추가 시 SQL 수정 불필요             |
| **패러다임의 불일치 해결**         | 객체와 관계형 DB 간 매핑 자동화           |
| **성능**                          | 다양한 최적화 기능 제공                   |
| **데이터 접근 추상화와 벤더 독립성** | 다양한 DB 벤더와 호환 가능                |
| **표준**                          | 표준 명세를 따르므로 안정성 보장          |

<br/>

## 생산성 - JPA와 CRUD
### CRUD 예시

- **저장:**
```java
  jpa.persist(member); // persist :: 영구보관하다
```

- **조회:**
```java
  Member member = jpa.find(memberId);
```

- **수정:**
```java
  member.setName("변경할 이름");
```

트랜잭션 안에서 해당 데이터를 조회한 다음에 데이터를 변경해주면
트랜잭션이 끝난 시점에 자동으로 업데이트 Query가 DB에 나가게 된다
나간 후에 데이터베이스를 커밋

- **삭제:**
```java
jpa.remove(member);
```

<br/>

## 유지보수

| 기존 방식                      | JPA 방식                           |
|-------------------------------|------------------------------------|
| 필드 변경 시 모든 SQL 수정     | 필드만 추가하면 됨, SQL은 JPA가 처리  |

<br/>

## JPA와 패러다임의 불일치 해결

- 개발자가 직접하던 매핑 과정을 JPA가 대신하게 된다
- JPA와 상속
- JPA와 연관관계
- JPA에서는 연관관계를 참조를 사용할 수 있다
- JPA와 객체 그래프 탐색
- 엔티티 계층을 신뢰할 수 있게 된다

<br/>

## JPA와 비교하기

동일한 트랜잭션에서 조회한 엔티티는 같음을 보장한다

## JPA의 성능 최적화 기능

| 기능                             | 설명                                        |
|----------------------------------|---------------------------------------------|
| 1차 캐시와 동일성(identity) 보장 | 같은 트랜잭션 안에서는 항상 같은 엔티티를 반환 |
| 트랜잭션을 지원하는 쓰기 지연     | INSERT 및 UPDATE/DELETE 시 SQL 지연          |
| 지연 로딩(Lazy Loading)           | 객체가 실제 사용될 때 로딩                   |

### 1차 캐시와 동일성 보장

1. **같은 트랜잭션 안에서는 항상 같은 엔티티를 반환** - 약간의 조회 성능 향상
2. DB Isolation Level이 Read Commit이어도 애플리케이션에서 Repeatable Read 보장

```java
String memberId = "100";
Member member1 = jpa.find(Member.class, memberId); // SQL
Member member2 = jpa.find(Member.class, memberId); // 캐시

println(member1 == member2) // true
// SQL 1번만 실행
```

첫번째 `jpa.find`에서는 SQL Query를 날리고  
두번째 `jpa.find`에서는 JPA가 SQL Query를 날리지 않고 들고있는 메모리 상에서 들고와서 반환을 해준다

## 트랜잭션을 지원하는 쓰기 지연

### INSERT

| 단계 | 설명                                                                    |
|------|-------------------------------------------------------------------------|
| 1    | 데이터를 버퍼로 모으는 것, 트랜잭션을 커밋할 때까지 INSERT SQL을 모음     |
| 2    | JDBC BATCH SQL 기능을 사용해서 한번에 SQL 전송                           |

```java
transaction.begin(); // [트랜잭션] 시작
em.persist(memberA);
em.persist(memberB);
em.persist(memberC);
// 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다

// 커밋하는 순간 데이터베이스에 INSERT SQL을 모아서 보낸다
transaction.commit(); // 트랜잭션 커밋
```

### UPDATE

| 단계 | 설명                                                      |
|------|-----------------------------------------------------------|
| 1    | UPDATE, DELETE로 인한 로우(ROW)락 시간 최소화             |
| 2    | 트랜잭션 커밋 시 UPDATE, DELETE SQL 실행하고 바로 커밋    |

```java
transaction.begin(); // [트랜잭션] 시작
changeMember(memberA);
deleteMember(memberB);
비즈니스_로직_수행(); // 비즈니스 로직 수행 동안 DB 로우 락이 걸리지 않는다

// 커밋하는 순간 데이터베이스에 UPDATE, DELETE SQL을 보낸다
transaction.commit(); // 트랜잭션 커밋
```

<br/>

## 지연 로딩과 즉시 로딩

### 지연 로딩

객체가 실제 사용될 때 로딩

```java
Member member = memberDAO.find(memberId); →  SELECT * FROM MEMBER
Team team = member.getTeam();
String teamName = team.getName(); →  SELECT * FROM TEAM
// 실제 팀이 사용될때 팀 데이터를 가져와서 값을 채워준다
```

### 즉시 로딩

JOIN SQL로 한번에 연관된 객체까지 미리 조회

```java
// 멤버를 조회할 때 팀도 같이 조회해줘
Member member = memberDAO.find(memberId); →  SELECT * FROM MEMBER
Team team = member.getTeam();	                     FROM MEMBER JOIN TEAM..
String teamName = team.getName();
```

보통 JPA를 개발할 때는 지연로딩으로 세팅해놨다가 필요한 부분만 즉시로딩으로 최적화

# 정리

- ORM은 객체랑 관계형 데이터베이스를 중간에서 매핑해주는 기술
- JPA를 잘 다루기 위해서는 객체 지향이라는 개념과 관계형 데이터베이스라는 개념을 두가지 다 잘 알아야 한다


