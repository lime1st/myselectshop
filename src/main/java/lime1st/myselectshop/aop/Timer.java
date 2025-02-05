package lime1st.myselectshop.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // 이 애너테이션을 메서드에만 사용하도록 제한한다.
@Retention(RetentionPolicy.RUNTIME) // 기본적으로 자바에서는 실행 중에 애너테이션을 가로챌 수 없다. 이렇게 명시적으로 지정해야 한다.
public @interface Timer {
}
