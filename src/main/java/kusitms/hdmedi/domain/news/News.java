package kusitms.hdmedi.domain.news;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String source;

    private String link;

    private LocalDateTime publishedAt;

    @Builder
    public News(String title, String source, String link, LocalDateTime publishedAt) {
        this.title = title;
        this.source = source;
        this.link = link;
        this.publishedAt = publishedAt;
    }

    public void update(String title, String source, String link, LocalDateTime publishedAt) {
        this.title = title;
        this.source = source;
        this.link = link;
        this.publishedAt = publishedAt;
    }
}
