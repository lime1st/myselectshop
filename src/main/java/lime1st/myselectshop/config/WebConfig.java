package lime1st.myselectshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * PageImpl 은 Spring Data JPA 의 페이징(Page) 객체인데, 이 객체를 직렬화(serialize)할 때 구조가 안정적이지 않다.
 * REST API 응답으로 PageImpl 을 그대로 반환하면, JSON 구조가 변경될 수도 있어서 Spring 에서는 PagedModel 을 사용하라고 권장
 * Spring Boot 3.x 이상에서는 @Configuration 클래스에 @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
 * 코드를 추가하면 자동으로 PagedModel 로 변환됨.
 */
@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class WebConfig {
}
