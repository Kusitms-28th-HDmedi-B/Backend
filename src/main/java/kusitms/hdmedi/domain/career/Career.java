package kusitms.hdmedi.domain.career;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url;

    @Enumerated(EnumType.STRING)
    private CareerField category;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Career(String title, String url, CareerField category) {
        this.title = title;
        this.url = url;
        this.category = category;
    }

    public void update(String title, String url, CareerField category){
        this.title = title;
        this.url = url;
        this.category = category;
    }
}
