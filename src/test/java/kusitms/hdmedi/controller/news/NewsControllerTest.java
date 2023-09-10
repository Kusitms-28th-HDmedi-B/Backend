package kusitms.hdmedi.controller.news;

import com.fasterxml.jackson.databind.ObjectMapper;
import kusitms.hdmedi.domain.domain.News;
import kusitms.hdmedi.dto.request.news.NewsRequest;
import kusitms.hdmedi.repository.news.NewsRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTest {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        newsRepository.deleteAll();
    }

    @Test
    @DisplayName("뉴스 목록을 조회할 때")
    void getAll() throws Exception {
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

        mockMvc.perform(MockMvcRequestBuilders.get("/news"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value(news2.getTitle()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("특정 뉴스를 조회할 때")
    void get() throws Exception {
        News news = newsRepository.save(News.builder()
                .title("기사1")
                .source("기사 출처")
                .link("www.기사링크1.com")
                .publishedAt(LocalDateTime.parse("2023-07-11T22:22:22"))
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/news/{id}", news.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("기사1"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("새로운 뉴스를 등록할 때")
    void create() throws Exception {
        NewsRequest newsRequest = NewsRequest.builder()
                .title("기사1")
                .source("기사 출처")
                .link("www.기사링크1.com")
                .publishedAt(LocalDateTime.parse("2023-07-11T22:22:22"))
                .build();

        String requestBody = objectMapper.writeValueAsString(newsRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/news")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        News news = newsRepository.findAll().get(0);
        assertThat(news.getTitle()).isEqualTo(newsRequest.getTitle());
    }

    @Test
    @DisplayName("뉴스를 수정할 때")
    void update() throws Exception {
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

        String requestBody = objectMapper.writeValueAsString(newsRequest);

        mockMvc.perform(MockMvcRequestBuilders.put("/news/{id}", news.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        News updatedNews = newsRepository.findById(news.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 뉴스입니다"));
        assertThat(updatedNews.getTitle()).isEqualTo(newsRequest.getTitle());
    }

    @Test
    @DisplayName("뉴스를 삭제할 때")
    void delete() throws Exception {
        News news = newsRepository.save(News.builder()
                .title("기사1")
                .source("기사 출처")
                .link("www.기사링크1.com")
                .publishedAt(LocalDateTime.parse("2023-07-11T22:22:22"))
                .build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/news/{id}", news.getId()));

        assertThat(newsRepository.count()).isEqualTo(0);
    }
}