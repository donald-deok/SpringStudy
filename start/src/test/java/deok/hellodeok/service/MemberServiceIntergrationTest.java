package deok.hellodeok.service;

import deok.hellodeok.domain.Member;
import deok.hellodeok.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 순수한 단위 테스트를 많이 해보면 좋다.
 * 통합 테스트는 굉장히 오래 걸린다..
 * 설계가 잘 되 있으면 단위 테스트가 굉장히 잘 이루어져야 한다.
 */
@SpringBootTest // 스프링 컨테이너와 테스트를 실행하기 위한 어노테이션
/**
 * 테스트 이후 롤백하는 어노테이션 @Transactional
 * 이걸 사용하게 되면, Commit 이전까지만 실행이 된다.
 * 따라서 테스트 내의 행동은 리셋됨
 * 커밋하고 싶으면 @Commit 사용하기
 * */
@Transactional
class MemberServiceIntergrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    /**
     * 테스트는 한글로 써도 된다 하심
     */
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}