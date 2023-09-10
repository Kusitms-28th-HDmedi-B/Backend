package kusitms.hdmedi.service.news;

import kusitms.hdmedi.domain.domain.News;
import kusitms.hdmedi.dto.request.news.NewsRequest;
import kusitms.hdmedi.dto.response.news.NewsResponse;
import kusitms.hdmedi.repository.news.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public List<NewsResponse> getAll() {
        List<NewsResponse> newsResponses = newsRepository.findAllByOrderByPublishedAtDesc().stream()
                .map(NewsResponse::new)
                .collect(Collectors.toList());
        return newsResponses;
    }

    public NewsResponse get(Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 뉴스입니다"));
        return new NewsResponse(news);
    }

    public void create(NewsRequest newsRequest) {
        News news = News.builder()
                .title(newsRequest.getTitle())
                .source(newsRequest.getSource())
                .link(newsRequest.getLink())
                .publishedAt(newsRequest.getPublishedAt())
                .build();
        newsRepository.save(news);
    }

    @Transactional
    public void update(Long newsId, NewsRequest newsRequest) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 뉴스입니다"));
        news.update(newsRequest.getTitle(), newsRequest.getSource(), news.getLink(), news.getPublishedAt());
    }

    public void delete(Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 뉴스입니다"));
        newsRepository.delete(news);
    }
}
