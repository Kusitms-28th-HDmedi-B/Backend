package kusitms.hdmedi.service.career;

import kusitms.hdmedi.domain.career.Career;
import kusitms.hdmedi.dto.request.career.CareerRequest;
import kusitms.hdmedi.dto.response.career.CareerResponse;
import kusitms.hdmedi.repository.career.CareerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CareerService {
    private final CareerRepository careerRepository;

    public List<CareerResponse> getAll() {
        List<CareerResponse> careerResponses = careerRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(CareerResponse::new)
                .collect(Collectors.toList());
        return careerResponses;
    }

    public CareerResponse get(Long careerId) {
        Career career = careerRepository.findById(careerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다"));
        return new CareerResponse(career);
    }

    public void create(CareerRequest careerRequest) {
        Career career = Career.builder()
                .title(careerRequest.getTitle())
                .url(careerRequest.getUrl())
                .category(careerRequest.getCategory())
                .build();
        careerRepository.save(career);
    }

    @Transactional
    public void update(Long careerId, CareerRequest careerRequest) {
        Career career = careerRepository.findById(careerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다"));
        career.update(careerRequest.getTitle(), careerRequest.getUrl(), careerRequest.getCategory());
    }

    public void delete(Long careerId) {
        Career career = careerRepository.findById(careerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다"));
        careerRepository.delete(career);
    }
}
