package kusitms.hdmedi.controller.qna;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kusitms.hdmedi.dto.request.announcement.AnnouncementRequest;
import kusitms.hdmedi.dto.request.qna.QnaRequest;
import kusitms.hdmedi.dto.response.announcement.AnnouncementListResponse;
import kusitms.hdmedi.dto.response.announcement.AnnouncementResponse;
import kusitms.hdmedi.dto.response.news.NewsListResponse;
import kusitms.hdmedi.dto.response.qna.QnaListResponse;
import kusitms.hdmedi.dto.response.qna.QnaResponse;
import kusitms.hdmedi.service.qna.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "qna", description = "자주하는 질문 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qna")
public class QnaController {

    private final QnaService qnaService;

//    @Operation(description = "자주하는 질문 목록 조회하기(페이징)", summary = "자주하는 질문 목록 조회(페이징)")
//    @ApiResponse(responseCode = "200", description = "OK",
//            content = @Content(schema = @Schema(implementation = QnaListResponse.class)))
//    @GetMapping("")
//    @Parameter(name = "page", description = "페이지 번호", in = ParameterIn.QUERY)
//    public QnaListResponse getAll(@Parameter(hidden = true) @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
//        return qnaService.getAll(pageable);
//    }

    @Operation(description = "자주하는 질문/답변 목록 조회하기", summary = "자주하는 질문/답변 목록 조회")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = QnaListResponse.class)))
    @GetMapping("")
    public List<QnaResponse> getAll() {
        return qnaService.getAll();
    }

    @Operation(description = "특정 자주하는 질문/답변 조회하기", summary = "특정 자주하는 질문/답변 조회")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = QnaResponse.class)))
    @Parameter(name = "qnaId", description = "질문/답변 ID")
    @GetMapping("/{qnaId}")
    public QnaResponse get(@PathVariable Long qnaId) {
        return qnaService.get(qnaId);
    }

    @Operation(description = "자주하는 질문/답변 작성하기", summary = "자주하는 질문/답변 작성")
    @PostMapping("")
    public void create(@RequestBody QnaRequest qnaRequest) {
        qnaService.create(qnaRequest);
    }

    @Operation(description = "자주하는 질문/답변 수정하기", summary = "자주하는 질문/답변 수정")
    @Parameter(name = "qnaId", description = "자주하는 질문/답변 ID")
    @PutMapping("/{qnaId}")
    public void delete(@PathVariable Long qnaId, @RequestBody QnaRequest qnaRequest) {
        qnaService.update(qnaId, qnaRequest);
    }

    @Operation(description = "자주하는 질문/답변 삭제하기", summary = "자주하는 질문/답변 삭제")
    @Parameter(name = "qnaId", description = "자주하는 질문/답변 ID")
    @DeleteMapping("/{qnaId}")
    public void delete(@PathVariable Long qnaId) {
        qnaService.delete(qnaId);
    }


}
