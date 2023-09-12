package kusitms.hdmedi.controller.career;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kusitms.hdmedi.dto.request.career.CareerRequest;
import kusitms.hdmedi.dto.response.career.CareerResponse;
import kusitms.hdmedi.dto.response.news.NewsResponse;
import kusitms.hdmedi.service.career.CareerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "career", description = "채용공고 API")
@RequestMapping("/api/career")
public class CareerController {
    private final CareerService careerService;

    @GetMapping("")
    @Operation(description = "모든 채용공고 조회하기", summary = "모든 채용공고 조회")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CareerResponse.class))))
    public List<CareerResponse> getAll() {
        return careerService.getAll();
    }

    @GetMapping("/{careerId}")
    @Operation(description = "특정 채용공고 조회하기", summary = "특정 채용공고 조회")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CareerResponse.class)))
    @Parameter(name = "careerId", description = "채용공고 ID")
    public CareerResponse get(@PathVariable Long careerId) {
        return careerService.get(careerId);
    }

    @PostMapping("")
    @Operation(description = "채용공고 작성하기", summary = "채용공고 작성")
    public void create(@RequestBody CareerRequest careerRequest) {
        careerService.create(careerRequest);
    }

    @PutMapping("/{careerId}")
    @Operation(description = "채용공고 수정하기", summary = "채용공고 수정")
    @Parameter(name = "careerId", description = "채용공고 ID")
    public void update(@PathVariable Long careerId, @RequestBody CareerRequest careerRequest) {
        careerService.update(careerId, careerRequest);
    }

    @DeleteMapping("/{careerId}")
    @Operation(description = "채용공고 삭제하기", summary = "채용공고 삭제")
    @Parameter(name = "careerId", description = "채용공고 ID")
    public void delete(@PathVariable Long careerId) {
        careerService.delete(careerId);
    }
}
