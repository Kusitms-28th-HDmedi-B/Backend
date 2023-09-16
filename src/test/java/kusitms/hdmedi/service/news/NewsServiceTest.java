package kusitms.hdmedi.service.news;

<<<<<<< HEAD
import kusitms.hdmedi.domain.news.News;
import kusitms.hdmedi.dto.request.news.NewsRequest;
import kusitms.hdmedi.dto.response.news.NewsListResponse;
import kusitms.hdmedi.dto.response.news.NewsResponse;
import kusitms.hdmedi.repository.news.NewsRepository;
=======
import kusitms.hdmedi.domain.domain.News;
import kusitms.hdmedi.dto.request.news.NewsRequest;
import kusitms.hdmedi.dto.response.news.NewsResponse;
import kusitms.hdmedi.repository.news.NewsRepository;
import org.assertj.core.api.AssertionsForClassTypes;
>>>>>>> bfc8a2c214eba153895fecca239e41f0575e628d
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
<<<<<<< HEAD
=======
import static org.junit.jupiter.api.Assertions.*;
>>>>>>> bfc8a2c214eba153895fecca239e41f0575e628d

@SpringBootTest
class NewsServiceTest {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsService newsService;

    @BeforeEach
    void setUp() {
        //테스트시 db 데이터가 초기화되서 임시 주석처리
        //newsRepository.deleteAll();
    }

    @Test
    @DisplayName("뉴스 목록을 조회할 때")
    void getAll() {
        News news1 = newsRepository.save(News.builder()
                .title("기사1")
                .source("기사 출처")
                .link("www.기사링크1.com")
                .publishedAt(LocalDateTime.parse("2023-07-11T22:22:22"))
                .build());

        News news2 = newsRepository.save(News.builder()
                .title("기사2")
                .source("기사 출처")
                .link("www.기사링크2.com")
                .publishedAt(LocalDateTime.parse("2023-07-11T23:23:23"))
                .build());

        Pageable pageable = PageRequest.of(0, 7, Sort.by("publishedAt").descending());

        NewsListResponse newsListResponse = newsService.getAll(pageable);

        assertThat(newsListResponse.getMaxpage()).isEqualTo(0);
        assertThat(newsListResponse.getData().size()).isEqualTo(2);
        assertThat(newsListResponse.getData().get(0).getTitle()).isEqualTo(news2.getTitle());
    }

    @Test
    @DisplayName("특정 뉴스를 조회할 때")
    void get() {
        News news = newsRepository.save(News.builder()
                .title("기사1")
                .source("기사 출처")
                .link("www.기사링크1.com")
                .publishedAt(LocalDateTime.parse("2023-07-11T22:22:22"))
                .build());

        NewsResponse newsResponse = newsService.get(news.getId());

        assertThat(newsResponse.getTitle()).isEqualTo(news.getTitle());
        assertThat(newsResponse.getSource()).isEqualTo(news.getSource());
    }

    @Test
    @DisplayName("새로운 뉴스를 등록할 때")
    void create() {
        NewsRequest newsRequest = NewsRequest.builder()
                .title("기사1")
                .source("기사 출처")
                .link("www.기사링크1.com")
                .publishedAt(LocalDateTime.parse("2023-07-11T22:22:22"))
                .build();

        newsService.create(newsRequest);

        News news = newsRepository.findAll().get(0);
        assertThat(news.getTitle()).isEqualTo(newsRequest.getTitle());
    }

    @Test
    @DisplayName("뉴스를 수정할 때")
    void update() {
        News news = newsRepository.save(News.builder()
                .title("기사1")
                .source("기사 출처")
                .link("www.기사링크1.com")
                .publishedAt(LocalDateTime.parse("2023-07-11T22:22:22"))
                .build());

        NewsRequest newsRequest = NewsRequest.builder()
                .title("수정된 기사1")
                .source("기사 출처")
                .link("www.기사링크1.com")
                .publishedAt(LocalDateTime.parse("2023-07-11T22:22:22"))
                .build();

        newsService.update(news.getId(), newsRequest);

        News updatedNews = newsRepository.findById(news.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 뉴스입니다"));
        assertThat(updatedNews.getTitle()).isEqualTo(newsRequest.getTitle());
    }

    @Test
    @DisplayName("뉴스를 삭제할 때")
    void delete() {
        News news = newsRepository.save(News.builder()
                .title("기사1")
                .source("기사 출처")
                .link("www.기사링크1.com")
                .publishedAt(LocalDateTime.parse("2023-07-11T22:22:22"))
                .build());

        newsService.delete(news.getId());

        assertThat(newsRepository.count()).isEqualTo(0);
    }
}