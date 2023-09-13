package kusitms.hdmedi.controller.news;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kusitms.hdmedi.dto.request.news.NewsRequest;
import kusitms.hdmedi.dto.response.news.NewsListResponse;
import kusitms.hdmedi.dto.response.news.NewsResponse;
import kusitms.hdmedi.service.news.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "news", description = "뉴스 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    @Operation(description = "모든 뉴스 조회하기", summary = "모든 뉴스 조회")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = NewsListResponse.class)))
    @GetMapping("")
    @Parameter(name = "page", description = "페이지 번호", in = ParameterIn.QUERY)
    public NewsListResponse getAll(@Parameter(hidden = true) @PageableDefault(size = 7, sort = "publishedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return newsService.getAll(pageable);
    }

    @Operation(description = "특정 뉴스 조회하기", summary = "특정 뉴스 조회")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = NewsResponse.class)))
    @Parameter(name = "newsId", description = "뉴스 ID")
    @GetMapping("/{newsId}")
    public NewsResponse get(@PathVariable Long newsId) {
        return newsService.get(newsId);
    }

    @Operation(description = "뉴스 작성하기", summary = "뉴스 작성")
    @PostMapping("")
    public void create(@RequestBody NewsRequest newsRequest) {
        newsService.create(newsRequest);
    }

    @Operation(description = "뉴스 수정하기", summary = "뉴스 수정")
    @Parameter(name = "newsId", description = "뉴스 ID")
    @PutMapping("/{newsId}")
    public void update(@PathVariable Long newsId, @RequestBody NewsRequest newsRequest) {
        newsService.update(newsId, newsRequest);
    }

    @Operation(description = "뉴스 삭제하기", summary = "뉴스 삭제")
    @Parameter(name = "newsId", description = "뉴스 ID")
    @DeleteMapping("/{newsId}")
    public void delete(@PathVariable Long newsId) {
        newsService.delete(newsId);
    }
}
