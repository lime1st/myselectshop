package lime1st.myselectshop.repository;

import lime1st.myselectshop.entity.Product;
import lime1st.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUser(User user);
}
