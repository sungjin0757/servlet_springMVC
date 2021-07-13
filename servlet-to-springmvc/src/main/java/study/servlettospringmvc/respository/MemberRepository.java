package study.servlettospringmvc.respository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.servlettospringmvc.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findById(Long memberId){
        return em.find(Member.class,memberId);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }
}
