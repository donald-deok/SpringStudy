package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
 * 권장하는 방법 :
 * 패키지 최상단에 두고 basePackages를 생략하는게 관례적이라고 한다.
 * 스프링부트를 사용하면 @SpringBootApplication를 루트에 두는 것이 관례다
 * 이 어노테이션 내부에 @ComponentScan이 붙어 있다.
 * */

/*
 * @ComponentScan의 기본 대상
 * @Component : 컴포넌트 스캔에 사용
 * @Controller : 스프링 MVC 컨트롤러에서 사용, 스프링 MVC 컨트롤러로 인식된다.
 * @Service : 스프링 비즈니스 로직에서 사용, 특별한 처리는 하지 않지만, 개발자들이 핵심 비즈니스 로직이 모인 곳이라 인식하는데 도움이 됨.
 * @Repository : 스프링 데이터 접근 계층에서 사용, 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
 * @Configuration : 스프링 설정 정보에서 사용, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
 * */
@Configuration
@ComponentScan(
        /*
        * basePackages를 따로 설정하지 않으면, 최상단 첫째줄의 package를 default로 삼는다.ㅇ
        * */
//        basePackages = "",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION, classes = Configuration.class
        )
)
public class AutoAppConfig {

    /*
     * 아래와 같이 수동 빈이 있을 경우 수동 빈이 자동 빈을 오버라이드 해버린다.
     * 즉, 우선 순위는 수동 빈에 있다.
     * 그러나 보통 오류는 설정 오류에서 나오는 경우가 대부분이였고,
     * 이에 따라 최근 스프링 부트는 수동 빈과 자동 빈이 충돌나면 오류가 발생하도록 기본 값이 바뀌어있다.
     * */

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
