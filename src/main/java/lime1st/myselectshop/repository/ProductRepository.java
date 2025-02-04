package lime1st.myselectshop.repository;

import lime1st.myselectshop.dto.ProductResponseDto;
import lime1st.myselectshop.entity.Product;
import lime1st.myselectshop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByUser(User user, Pageable pageable);

    // select * from product p
    //      left join product_folder pf
    //      on p.id = pf.product_id
    // where p.user_id = ? and pf.folder_id = ?
    // order by p.id pageable.getSort()
    // limit pageable.getPageNumber(), pageable.getPageSize();
    Page<Product> findAllByUserAndProductFolderList_FolderId(User user, Long folderId, Pageable pageable);
}
