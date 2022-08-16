package deok.hellodeok.repository;

import deok.hellodeok.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository extends를 통해 proxy가 자동으로 구현체를 만들어준다고 함
 * 인터페이스를 통해 기본적인 CRUD가 가능하다
 * 페이징 기능이 제공된다.
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL: select m from Member m where m.name = ?

    /**
     * 즉, findBy${value}로 조건을 만들어 준다
     * Select m from Member m where m.value = ?
     */
    @Override
    Optional<Member> findByName(String name);
}
