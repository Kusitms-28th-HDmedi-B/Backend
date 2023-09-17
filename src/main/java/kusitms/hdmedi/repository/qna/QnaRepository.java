package kusitms.hdmedi.repository.qna;

import kusitms.hdmedi.domain.announcement.Announcement;
import kusitms.hdmedi.domain.qna.Qna;
import kusitms.hdmedi.dto.response.qna.QnaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {
    List<Qna> findAllByOrderByCreatedAtDesc();
    Page<Qna> findAll(Pageable pageable);

}
