package kusitms.hdmedi.dto.response.qna;

import kusitms.hdmedi.domain.qna.Qna;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
public class QnaResponse {

    private Long id;

    private String question;

    private String answer;

    private String createdAt;

    public QnaResponse(Qna qna) {
        id = qna.getId();
        question = qna.getQuestion();
        answer = qna.getAnswer();
        createdAt = qna.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
