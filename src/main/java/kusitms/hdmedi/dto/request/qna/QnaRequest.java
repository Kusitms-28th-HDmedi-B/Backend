package kusitms.hdmedi.dto.request.qna;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class QnaRequest {

    private String question;

    private String answer;

    @Builder
    public QnaRequest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
