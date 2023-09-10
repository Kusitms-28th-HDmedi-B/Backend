package kusitms.hdmedi.controller.news;

import kusitms.hdmedi.domain.domain.News;
import kusitms.hdmedi.dto.request.news.NewsRequest;
import kusitms.hdmedi.dto.response.news.NewsResponse;
import kusitms.hdmedi.service.news.NewsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    @GetMapping("")
    public List<NewsResponse> getAll(){
        return newsService.getAll();
    }

    @GetMapping("/{newsId}")
    public NewsResponse get(@PathVariable Long newsId){
        return newsService.get(newsId);
    }

    @PostMapping("")
    public void create(@RequestBody NewsRequest newsRequest){
        newsService.create(newsRequest);
    }

    @PutMapping("/{newsId}")
    public void update(@PathVariable Long newsId, @RequestBody NewsRequest newsRequest){
        newsService.update(newsId, newsRequest);
    }

    @DeleteMapping("/{newsId}")
    public void delete(@PathVariable Long newsId){
        newsService.delete(newsId);
    }
}
