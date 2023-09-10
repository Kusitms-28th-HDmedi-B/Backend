package kusitms.hdmedi.repository.news;

import kusitms.hdmedi.domain.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByOrderByPublishedAtDesc();
}
