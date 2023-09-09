package kusitms.hdmedi.controller.announcement;

import kusitms.hdmedi.dto.request.announcement.AnnouncementRequest;
import kusitms.hdmedi.dto.response.announcement.AnnouncementResponse;
import kusitms.hdmedi.service.announcement.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @GetMapping("")
    public List<AnnouncementResponse> getAll(@PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return announcementService.getAll(pageable);
    }

    @GetMapping("/{announcementId}")
    public AnnouncementResponse get(@PathVariable Long announcementId) {
        return announcementService.get(announcementId);
    }

    @PostMapping("")
    public void create(@RequestBody AnnouncementRequest announcementRequest) {
        announcementService.create(announcementRequest);
    }

    @PatchMapping("/{announcementId}")
    public void update(@PathVariable Long announcementId, @RequestBody AnnouncementRequest announcementRequest) {
        announcementService.update(announcementId, announcementRequest);
    }

    @DeleteMapping("/{announcementId}")
    public void delete(@PathVariable Long announcementId) {
        announcementService.delete(announcementId);
    }
}
