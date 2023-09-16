package kusitms.hdmedi.dto.response.qna;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class QnaListResponse {

    private Long maxpage;

    private List<QnaResponse> data;

    @Builder
    public QnaListResponse(Long maxpage, List<QnaResponse> data) {
        this.maxpage = maxpage;
        this.data = data;
    }
}
