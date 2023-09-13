package kusitms.hdmedi.dto.response.announcement;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class AnnouncementListResponse {
    private Long maxpage;

    private List<AnnouncementResponse> data;

    @Builder
    public AnnouncementListResponse(Long maxpage, List<AnnouncementResponse> data) {
        this.maxpage = maxpage;
        this.data = data;
    }
}
