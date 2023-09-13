package kusitms.hdmedi.controller.announcement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kusitms.hdmedi.dto.request.announcement.AnnouncementRequest;
import kusitms.hdmedi.dto.response.announcement.AnnouncementListResponse;
import kusitms.hdmedi.dto.response.announcement.AnnouncementResponse;
import kusitms.hdmedi.service.announcement.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(name = "announcement", description = "공지사항 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @Operation(description = "모든 공지사항 조회하기", summary = "모든 공지사항 조회")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AnnouncementResponse.class))))
    @GetMapping("")
    @Parameter(name = "page", description = "페이지 번호", in = ParameterIn.QUERY)
    public AnnouncementListResponse getAll(@Parameter(hidden = true) @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return announcementService.getAll(pageable);
    }

    @Operation(description = "특정 공지사항 조회하기", summary = "특정 공지사항 조회")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = AnnouncementResponse.class)))
    @Parameter(name = "announcementId", description = "공지사항 ID")
    @GetMapping("/{announcementId}")
    public AnnouncementResponse get(@PathVariable Long announcementId) {
        return announcementService.get(announcementId);
    }

    @Operation(description = "공지사항 작성하기", summary = "공지사항 작성")
    @PostMapping("")
    public void create(@RequestBody AnnouncementRequest announcementRequest) {
        announcementService.create(announcementRequest);
    }

    @Operation(description = "공지사항 수정하기", summary = "공지사항 수정")
    @Parameter(name = "announcementId", description = "공지사항 ID")
    @PutMapping("/{announcementId}")
    public void update(@PathVariable Long announcementId, @RequestBody AnnouncementRequest announcementRequest) {
        announcementService.update(announcementId, announcementRequest);
    }

    @Operation(description = "공지사항 삭제하기", summary = "공지사항 삭제")
    @Parameter(name = "announcementId", description = "공지사항 ID")
    @DeleteMapping("/{announcementId}")
    public void delete(@PathVariable Long announcementId) {
        announcementService.delete(announcementId);
    }
}
