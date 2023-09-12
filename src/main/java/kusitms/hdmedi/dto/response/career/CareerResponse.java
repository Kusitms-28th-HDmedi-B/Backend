package kusitms.hdmedi.dto.response.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import kusitms.hdmedi.domain.career.Career;
import kusitms.hdmedi.domain.career.CareerField;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
public class CareerResponse {
    private Long id;

    private String title;

    private String url;

    private CareerField category;

    public CareerResponse(Career career) {
        this.id = career.getId();
        this.title = career.getTitle();
        this.url = career.getUrl();
        this.category = career.getCategory();
    }
}
