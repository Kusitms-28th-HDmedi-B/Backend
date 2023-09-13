package kusitms.hdmedi.controller.announcement;

import com.fasterxml.jackson.databind.ObjectMapper;
import kusitms.hdmedi.domain.announcement.Announcement;
import kusitms.hdmedi.dto.request.announcement.AnnouncementRequest;
import kusitms.hdmedi.repository.announcement.AnnouncementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
class AnnouncementControllerTest {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        //테스트시 db 데이터가 초기화되서 임시 주석처리
        //announcementRepository.deleteAll();
    }

    @Test
    @DisplayName("공지사항 목록을 요청할 때")
    void getAll() throws Exception {
        //given
        Announcement announcement1 = announcementRepository.save(Announcement.builder()
                .title("공지1")
                .content("공지사항입니다.")
                .build());

        Thread.sleep(100);//0.1초 딜레이를 주어 createAt값 차이 강제로 만들기

        Announcement announcement2 = announcementRepository.save(Announcement.builder()
                .title("공지2")
                .content("공지사항입니다.")
                .build());

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/announcement")
                        .param("page", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.maxpage").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value(announcement2.getTitle()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("특정 공지사항을 요청할 때")
    void get() throws Exception {
        //given
        Announcement announcement = announcementRepository.save(Announcement.builder()
                .title("공지1")
                .content("공지사항입니다.")
                .build());

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/announcement/{id}", announcement.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(announcement.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(announcement.getContent()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("공지사항을 새로 작성할 때")
    void create() throws Exception {
        //given
        AnnouncementRequest announcementRequest = AnnouncementRequest.builder()
                .title("공지1")
                .content("공지사항입니다.")
                .build();

        String requestBody = objectMapper.writeValueAsString(announcementRequest);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/announcement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        //then
        Announcement announcement = announcementRepository.findAll().get(0);
        assertThat(announcement.getTitle()).isEqualTo(announcementRequest.getTitle());
        assertThat(announcement.getContent()).isEqualTo(announcementRequest.getContent());
    }

    @Test
    @DisplayName("공지사항을 수정할 때")
    void update() throws Exception {
        //given
        Announcement announcement = announcementRepository.save(Announcement.builder()
                .title("공지1")
                .content("공지사항입니다.")
                .build());

        AnnouncementRequest announcementRequest = AnnouncementRequest.builder()
                .title("수정된 공지1")
                .content("수정된 공지사항입니다.")
                .build();

        String requestBody = objectMapper.writeValueAsString(announcementRequest);

        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/announcement/{id}", announcement.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        //then
        Announcement updatedAnnouncement = announcementRepository.findById(announcement.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지사항입니다."));
        assertThat(updatedAnnouncement.getTitle()).isEqualTo(announcementRequest.getTitle());
        assertThat(updatedAnnouncement.getContent()).isEqualTo(announcementRequest.getContent());
    }

    @Test
    @DisplayName("공지사항을 삭제할 때")
    void delete() throws Exception {
        //given
        Announcement announcement = announcementRepository.save(Announcement.builder()
                .title("공지1")
                .content("공지사항입니다.")
                .build());

        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/announcement/{id}", announcement.getId()));

        //then
        assertThat(announcementRepository.count()).isEqualTo(0);
    }
}