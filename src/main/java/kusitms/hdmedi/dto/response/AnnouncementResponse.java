package kusitms.hdmedi.dto.response;

import kusitms.hdmedi.domain.Announcement;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class AnnouncementResponse {
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public AnnouncementResponse(Announcement announcement) {
        id = announcement.getId();
        title = announcement.getTitle();
        content = announcement.getContent();
        createdAt = announcement.getCreatedAt();
        updatedAt = announcement.getUpdatedAt();
    }
}
