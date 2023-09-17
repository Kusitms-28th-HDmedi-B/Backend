package kusitms.hdmedi.service.qna;

import kusitms.hdmedi.domain.announcement.Announcement;
import kusitms.hdmedi.domain.qna.Qna;
import kusitms.hdmedi.dto.request.qna.QnaRequest;
import kusitms.hdmedi.dto.response.announcement.AnnouncementResponse;
import kusitms.hdmedi.dto.response.qna.QnaListResponse;
import kusitms.hdmedi.dto.response.qna.QnaResponse;
import kusitms.hdmedi.repository.qna.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

//    public QnaListResponse getAll(Pageable pageable) {
//        List<QnaResponse> qnaResponses = qnaRepository.findAll(pageable)
//                .map(QnaResponse::new)
//                .getContent();
//
//        long maxpage = 0, cnt = qnaRepository.count();
//        if (cnt > 0) {
//            maxpage = (cnt - 1) / pageable.getPageSize();
//        }
//
//        QnaListResponse qnaListResponse = QnaListResponse.builder()
//                .maxpage(maxpage)
//                .data(qnaResponses)
//                .build();
//
//        return qnaListResponse;
//    }

    public List<QnaResponse> getAll() {
        List<QnaResponse> qnaResponses = qnaRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(QnaResponse::new)
                .collect(Collectors.toList());
        return qnaResponses;
    }

    public void create(QnaRequest qnaRequest) {
        Qna qna = Qna.builder()
                .question(qnaRequest.getQuestion())
                .answer(qnaRequest.getAnswer())
                .build();

        qnaRepository.save(qna);

    }

    public QnaResponse get(Long qnaId) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 질문/답변입니다"));
        QnaResponse qnaResponse = new QnaResponse(qna);
        return qnaResponse;
    }

    @Transactional
    public void update(Long qnaId, QnaRequest qnaRequest) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 질문/답변입니다"));
        qna.update(qnaRequest.getQuestion(), qnaRequest.getAnswer());
    }

    public void delete(Long qnaId) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 질문/답변입니다"));
        qnaRepository.delete(qna);
    }

}
