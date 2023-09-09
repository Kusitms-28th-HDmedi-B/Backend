package kusitms.hdmedi.dto.request.announcement;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AnnouncementRequest {
    private String title;

    private String content;

    @Builder
    public AnnouncementRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
