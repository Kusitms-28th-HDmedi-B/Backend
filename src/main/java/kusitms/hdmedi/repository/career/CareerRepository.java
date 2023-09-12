package kusitms.hdmedi.repository.career;

import kusitms.hdmedi.domain.career.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerRepository extends JpaRepository<Career,Long> {
    List<Career> findAllByOrderByCreatedAtDesc();
}
