package lime1st.myselectshop.repository;

import lime1st.myselectshop.entity.Folder;
import lime1st.myselectshop.entity.Product;
import lime1st.myselectshop.entity.ProductFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductFolderRepository extends JpaRepository<ProductFolder, Long> {
    Optional<ProductFolder> findByProductAndFolder(Product product, Folder folder);
}
