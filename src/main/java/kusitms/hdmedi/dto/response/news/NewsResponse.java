package kusitms.hdmedi.dto.response.news;

import kusitms.hdmedi.domain.domain.News;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class NewsResponse {
    private Long id;

    private String title;

    private String source;

    private String link;

    private LocalDateTime publishedAt;

    public NewsResponse(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.source = news.getSource();
        this.link = news.getLink();
        this.publishedAt = news.getPublishedAt();
    }
}
