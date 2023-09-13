package kusitms.hdmedi.dto.response.news;

import kusitms.hdmedi.domain.news.News;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
public class NewsResponse {
    private Long id;

    private String title;

    private String source;

    private String link;

    private String publishedAt;

    public NewsResponse(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.source = news.getSource();
        this.link = news.getLink();
        this.publishedAt = news.getPublishedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
