package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JapMain {
    public static void main(String[] args) {
        // EntityManagerFactory는 애플리케이션 로딩 시점에 딱 하나만 만들어 놔야 한다
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        // 일괄적인 트랜잭션 단위를 할 때 마다 EntityManager를 만들어 줘야 한다
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // 회원 등록
            // Member member = new Member();
            // member.setId(2L);
            // member.setName("하호룰라웨이");
            // entityManager.persist(member);

            // 회원 수정
            // entityManager를 Java Collection이라고 이해하면 된다
            // Member member = entityManager.find(Member.class, 1L);
            // member.setName("하호룰라의 모렐로노미콘");

            // 회원 삭제
            // Member member = entityManager.find(Member.class, 1L);
            // entityManager.remove(member);

            // JPQL - 기승전 쿼리
            // JPQL로 전체 회원 조회
            // JPA 입장에서는 코드를 짤때 테이블을 대상으로 코드를 짜지 않는다
            // 아래 쿼리는 멤버 객체를 대상으로 쿼리를 한다고 생각하면 된다
            // 대상이 멤버 테이블이 아닌 멤버 객체가 된다 → 엔티티 객체 대상 쿼리
            List<Member> result = entityManager.createQuery("select m from Member as m", Member.class)
                    // 페이징 
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}

// JPA에서는 transaction이라는 단위가 엄청 중요하다
// JPA에서 데이터를 변경하는 모든 작업은 꼭 transaction 안에서 작업이 이루어져야 한다
// 실제는 Spring이 알아서 다 해주기 때문에 위 같은 정석 코드는 다 없어진다
