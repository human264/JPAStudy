package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            hellojpa.Member findMember = em.find(hellojpa.Member.class, 1L);
            List<Member> result = em.createQuery("select m from Member as m", Member.class).setFirstResult(1).setMaxResults(5).getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            tx.commit();

        } catch (Exception e ){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
