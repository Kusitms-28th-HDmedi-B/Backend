package kusitms.hdmedi.service;

import kusitms.hdmedi.domain.Announcement;
import kusitms.hdmedi.dto.request.AnnouncementRequest;
import kusitms.hdmedi.dto.response.AnnouncementResponse;
import kusitms.hdmedi.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public Page<AnnouncementResponse> getAll(Pageable pageable) {
        //domain객체들 response_dto객체들로 변환
        return announcementRepository.findAll(pageable)
                .map(AnnouncementResponse::new);
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
