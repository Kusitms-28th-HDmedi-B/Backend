package kusitms.hdmedi.controller.news;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kusitms.hdmedi.domain.domain.News;
import kusitms.hdmedi.dto.request.news.NewsRequest;
import kusitms.hdmedi.dto.response.news.NewsResponse;
import kusitms.hdmedi.service.news.NewsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "news", description = "뉴스 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    @Operation(description = "뉴스 목록 조회하기")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NewsResponse.class)))
    @GetMapping("")
    @Parameter(name = "page", description = "페이지 번호", example = "0", in = ParameterIn.QUERY)
    public List<NewsResponse> getAll(@Parameter(hidden = true) @PageableDefault(size = 7, sort = "publishedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return newsService.getAll(pageable);
    }

    @Operation(description = "특정 뉴스 조회하기")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NewsResponse.class)))
    @Parameter(name = "newsId", description = "뉴스 ID", example = "7")
    @GetMapping("/{newsId}")
    public NewsResponse get(@PathVariable Long newsId) {
        return newsService.get(newsId);
    }

    @Operation(description = "뉴스 작성하기")
    @PostMapping("")
    public void create(@RequestBody NewsRequest newsRequest) {
        newsService.create(newsRequest);
    }

    @Operation(description = "뉴스 수정하기")
    @Parameter(name = "newsId", description = "뉴스 ID", example = "7")
    @PutMapping("/{newsId}")
    public void update(@PathVariable Long newsId, @RequestBody NewsRequest newsRequest) {
        newsService.update(newsId, newsRequest);
    }

    @Operation(description = "뉴스 삭제하기")
    @Parameter(name = "newsId", description = "뉴스 ID", example = "7")
    @DeleteMapping("/{newsId}")
    public void delete(@PathVariable Long newsId) {
        newsService.delete(newsId);
    }
}
