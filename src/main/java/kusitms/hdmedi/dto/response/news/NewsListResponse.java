package kusitms.hdmedi.dto.response.news;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class NewsListResponse {
    private Long maxpage;

    private List<NewsResponse> data;

    @Builder
    public NewsListResponse(Long maxpage, List<NewsResponse> data) {
        this.maxpage = maxpage;
        this.data = data;
    }
}
