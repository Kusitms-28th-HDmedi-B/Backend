package kusitms.hdmedi.service.announcement;

import kusitms.hdmedi.domain.announcement.Announcement;
import kusitms.hdmedi.dto.request.announcement.AnnouncementRequest;
import kusitms.hdmedi.dto.response.announcement.AnnouncementResponse;
import kusitms.hdmedi.repository.announcement.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public List<AnnouncementResponse> getAll(Pageable pageable) {
        List<AnnouncementResponse> announcementResponses = announcementRepository.findAll(pageable)
                .map(AnnouncementResponse::new)
                .getContent();
        return announcementResponses;
    }

    public AnnouncementResponse get(Long announcementId) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지사항입니다"));
        AnnouncementResponse announcementResponse = new AnnouncementResponse(announcement);
        return announcementResponse;
    }

    public void create(AnnouncementRequest announcementRequest) {
        Announcement announcement = Announcement.builder()
                .title(announcementRequest.getTitle())
                .content(announcementRequest.getContent())
                .build();
        announcementRepository.save(announcement);
    }

    @Transactional
    public void update(Long announcementId, AnnouncementRequest announcementRequest) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지사항입니다"));
        announcement.update(announcementRequest.getTitle(), announcementRequest.getContent());
    }

    public void delete(Long announcementId) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지사항입니다"));
        announcementRepository.delete(announcement);
    }
}
