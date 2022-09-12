package hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * @Filter의 옵션
 * ANNOTATION: 기본값, 어노테이션을 인식해서 동작
 * ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작
 * ASPECTJ: AspectJ 패턴 사용
 * REGEX: 정규 표현식
 * CUSTOM: `TypeFilter` 라는 인터페이스를 구현해서 처리
 * */

/*
 * 강의에서 추가로 말해주신 것
 * 참고:
 * `@Component` 면 충분하기 때문에 굳이 `includeFilter`까지 사용할 일은 거의 없다고 하심
 * `excludeFilters`는 여러가지 이유로 간혹 사용할 때가 있다.
 * 특히 최근 스프링 부트는 컴포넌트 스캔을 기본으로 제공하는데,
 * 개인적으로 옵션을 변경하면서 사용하기 보다는 스프링의 기본 설정에 최대한 맞추어 사용하는 것을 권장하고, 선호한다고 하심
 * */

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);

        assertThat(beanA).isNotNull();

        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = MyIncludeComponent.class
            ),
            excludeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = MyExcludeComponent.class
            )
    )
    static class ComponentFilterAppConfig {

    }
}
