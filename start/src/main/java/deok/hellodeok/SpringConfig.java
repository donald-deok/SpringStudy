package deok.hellodeok;

import deok.hellodeok.repository.JdbcTemplateMemberRepository;
import deok.hellodeok.repository.MemberRepository;
import deok.hellodeok.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 결국 Configuration을 사용하면
 * 구현체의 교체가 쉬워지는 느낌임
 * 개방-폐쇄 원칙 (OCP, Open-Closed Principle)
 * Configuration과 같은 조립 부분은 고쳐야 할 필요가 있지만
 * 어느 한 부분이 교체, 즉 확장이 이루어 질 때,
 * 코드의 수정이 필요가 없어진다.
 */
@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
