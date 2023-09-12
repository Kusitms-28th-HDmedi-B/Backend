package kusitms.hdmedi.dto.request.career;

import kusitms.hdmedi.domain.career.CareerField;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CareerRequest {
    private String title;

    private String url;

    private CareerField category;

    @Builder
    public CareerRequest(String title, String url, CareerField category) {
        this.title = title;
        this.url = url;
        this.category = category;
    }
}