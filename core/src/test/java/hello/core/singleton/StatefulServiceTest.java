package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자 10000원 주문
        int userA_price = statefulService1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        int userB_price = statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회


        System.out.println("userA_price = " + userA_price);
        System.out.println("userB_price = " + userB_price);


        Assertions.assertThat(userA_price).isEqualTo(10000);
        Assertions.assertThat(userA_price).isNotEqualTo(userB_price);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}