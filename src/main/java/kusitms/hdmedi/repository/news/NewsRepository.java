package kusitms.hdmedi.repository.news;

<<<<<<< HEAD
import kusitms.hdmedi.domain.news.News;
=======
import kusitms.hdmedi.domain.domain.News;
>>>>>>> bfc8a2c214eba153895fecca239e41f0575e628d
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> bfc8a2c214eba153895fecca239e41f0575e628d
public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findAll(Pageable pageable);
}
