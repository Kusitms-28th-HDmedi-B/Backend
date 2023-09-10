package kusitms.hdmedi.dto.request.news;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class NewsRequest {
    private String title;

    private String source;

    private String link;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime publishedAt;

    @Builder
    public NewsRequest(String title, String source, String link, LocalDateTime publishedAt) {
        this.title = title;
        this.source = source;
        this.link = link;
        this.publishedAt = publishedAt;
    }
}
