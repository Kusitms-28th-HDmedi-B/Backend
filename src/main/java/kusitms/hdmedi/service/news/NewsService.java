package kusitms.hdmedi.service.news;

import kusitms.hdmedi.domain.news.News;
import kusitms.hdmedi.dto.request.news.NewsRequest;
import kusitms.hdmedi.dto.response.news.NewsListResponse;
import kusitms.hdmedi.dto.response.news.NewsResponse;
import kusitms.hdmedi.repository.news.NewsRepository;
import kusitms.hdmedi.service.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsListResponse getAll(Pageable pageable) {
        List<NewsResponse> newsResponses = newsRepository.findAll(pageable)
                .map(NewsResponse::new)
                .getContent();
        long maxpage = 0, cnt = newsRepository.count();
        if (cnt > 0)
            maxpage = (cnt - 1) / pageable.getPageSize();
        NewsListResponse newsListResponse = NewsListResponse.builder()
                .maxpage(maxpage)
                .data(newsResponses)
                .build();
        return newsListResponse;
    }

    public NewsResponse get(Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 뉴스입니다"));
        return new NewsResponse(news);
    }

    public void create(NewsRequest newsRequest){
        News news = News.builder()
                .title(newsRequest.getTitle())
                .source(newsRequest.getSource())
                .link(newsRequest.getLink())
                .publishedAt(newsRequest.getPublishedAt())
                .image(newsRequest.getImage())
                .build();
        newsRepository.save(news);
    }

    @Transactional
    public void update(Long newsId, NewsRequest newsRequest){
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 뉴스입니다"));
        news.update(newsRequest.getTitle(), newsRequest.getSource(), newsRequest.getLink(), newsRequest.getPublishedAt(), newsRequest.getImage());
    }

    public void delete(Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 뉴스입니다"));
        newsRepository.delete(news);
    }
}
