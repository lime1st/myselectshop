package lime1st.myselectshop.repository;

import lime1st.myselectshop.entity.Folder;
import lime1st.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    // select * from folder where user_id = 1 and name in (1, 2, 3);
    List<Folder> findAllByUserAndNameIn(User user, List<String> folderNames);

    List<Folder> findAllByUser(User user);
}
