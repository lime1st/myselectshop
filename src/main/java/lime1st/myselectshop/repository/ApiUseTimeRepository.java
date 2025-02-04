package lime1st.myselectshop.repository;

import lime1st.myselectshop.entity.ApiUseTime;
import lime1st.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiUseTimeRepository extends JpaRepository<ApiUseTime, Long> {
    Optional<ApiUseTime> findByUser(User user);
}
