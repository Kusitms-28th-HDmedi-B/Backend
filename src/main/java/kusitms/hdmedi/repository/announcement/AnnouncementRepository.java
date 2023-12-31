package kusitms.hdmedi.repository.announcement;

import kusitms.hdmedi.domain.announcement.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Page<Announcement> findAll(Pageable pageable);
}
