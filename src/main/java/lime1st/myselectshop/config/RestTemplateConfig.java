package lime1st.myselectshop.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {
    // RestTemplate 을 주입 받기위해서는 생성자에서 RestTemplateBuilder 을 주입받아 생성해 주어야 한다.
    // 이 프로젝트에서는 생성자 주입 방식을 Lombok 의 애너테이션을 사용하므로 여기에서 직접 Bean 으로 생성해 준다.
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                // RestTemplate 으로 외부 API 호출 시 일정 시간이 지나도 응답이 없을 때
                // 무한 대기 상태 방지를 위해 강제 종료 설정
                .connectTimeout(Duration.ofSeconds(5)) // 5초
                .readTimeout(Duration.ofSeconds(5)) // 5초
                .build();
    }
}