package kusitms.hdmedi.service.announcement;

import com.fasterxml.jackson.databind.ObjectMapper;
import kusitms.hdmedi.domain.announcement.Announcement;
import kusitms.hdmedi.dto.request.announcement.AnnouncementRequest;
import kusitms.hdmedi.dto.response.announcement.AnnouncementResponse;
import kusitms.hdmedi.repository.announcement.AnnouncementRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnnouncementServiceTest {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        announcementRepository.deleteAll();
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

        Pageable pageable = PageRequest.of(0, 5, Sort.by("createdAt").descending());

        //when
        List<AnnouncementResponse> announcementResponses = announcementService.getAll(pageable);

        //then
        assertThat(announcementResponses.size()).isEqualTo(2);
        assertThat(announcementResponses.get(0).getTitle()).isEqualTo(announcement2.getTitle());
    }

    @Test
    @DisplayName("특정 공지사항을 요청할 때")
    void get() throws Exception {
        //given
        Announcement announcement = announcementRepository.save(Announcement.builder()
                .title("공지1")
                .content("공지사항입니다.")
                .build());

        //when
        AnnouncementResponse announcementResponse = announcementService.get(announcement.getId());

        //then
        assertThat(announcementResponse.getTitle()).isEqualTo(announcement.getTitle());
        assertThat(announcementResponse.getContent()).isEqualTo(announcement.getContent());
    }

    @Test
    @DisplayName("공지사항을 새로 작성할 때")
    void create() throws Exception {
        //given
        AnnouncementRequest announcementRequest = AnnouncementRequest.builder()
                .title("공지1")
                .content("공지사항입니다.")
                .build();

        //when
        announcementService.create(announcementRequest);

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

        //when
        announcementService.update(announcement.getId(), announcementRequest);

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
        announcementService.delete(announcement.getId());

        //then
        assertThat(announcementRepository.count()).isEqualTo(0);
    }
}