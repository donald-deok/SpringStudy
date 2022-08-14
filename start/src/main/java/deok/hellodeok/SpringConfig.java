package deok.hellodeok;

import deok.hellodeok.repository.MemberRepository;
import deok.hellodeok.repository.MemoryMemberRepository;
import deok.hellodeok.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
