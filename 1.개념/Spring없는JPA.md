# 🔥 Spring 없이 JPA 실습

## JPA 설정하기 - persistence.xml
![3.PNG](..%2Fimg%2F3.PNG)

**표준 위치가 정해져있기때문에 위치가 매우 중요하다**  
`resources/META-INF/persistence.xml`

<br/>

## 데이터베이스 방언

JPA는 특정 데이터베이스에 종속적이지 않도록 설계  
각각의 데이터베이스가 제공하는 SQL 문법과 함수는 조금씩 다르다

| 기능                      | MySQL          | Oracle       | SQL 표준        |
|---------------------------|----------------|--------------|-----------------|
| 가변 문자                 | VARCHAR        | VARCHAR2     |                 |
| 문자열을 자르는 함수     | SUBSTRING()    | SUBSTR()     |                 |
| 페이징                    | LIMIT          | ROWNUM       |                 |

<br/>

## 방언?

SQL 표준을 지키지 않는 특정 데이터베이스만의 고유한 기능

**`<property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>`**  
현재 데이터베이스를 사용하는데 H2 데이터베이스라는 사투리를 써서 개발해  
JPA가 알아서 번역해서 사용

---
