package kusitms.hdmedi.repository.qna;

import kusitms.hdmedi.domain.announcement.Announcement;
import kusitms.hdmedi.domain.qna.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Long> {
    Page<Qna> findAll(Pageable pageable);
}
