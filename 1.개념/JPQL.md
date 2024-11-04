# 🔥 JPA 구동 방식

### JPA를 사용하면 엔티티 객체를 중심으로 개발

## 문제는 검색 쿼리

- 검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
- 모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
- 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요

## JPQL(Java Persistence Query Language)

- JPA는 SQL을 추상화한 **JPQL**이라는 객체 지향 쿼리 언어를 제공
- SQL과 문법 유사, `SELECT`, `FROM`, `WHERE`, `GROUP BY`, `HAVING`, `JOIN` 지원
- JPQL은 엔티티 객체를 대상으로 쿼리
- SQL은 데이터베이스 테이블을 대상으로 쿼리
- 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
- JPQL을 한마디로 정의하면 객체 지향 SQL이다
- SQL을 추상화해서 특정 데이터베이스 SQL에 의존하지 않는다


<br/>

## JPQL과 SQL 비교

| 구분    | JPQL                           | SQL                           |
|---------|--------------------------------|-------------------------------|
| 대상    | 엔티티 객체                     | 데이터베이스 테이블            |
| 특징    | 객체 지향, SQL 추상화           | 테이블 기반, 특정 DB SQL 종속    |
| 지원 기능 | SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN | SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN |

