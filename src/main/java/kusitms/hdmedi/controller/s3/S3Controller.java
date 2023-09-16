package kusitms.hdmedi.controller.s3;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kusitms.hdmedi.service.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "s3", description = "S3 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/s3")
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping("")
    public String upload(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        return s3Service.upload(multipartFile);
    }

    @DeleteMapping("/{url}")
    public void delete(@RequestParam String url) {
        s3Service.delete(url);
    }
}
